package com.seedman.schedule;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.seedman.constant.MobielConstant;
import com.seedman.entity.MobileEntity;
import com.seedman.utils.IOUtil;
import com.seedman.utils.ValidEntityUtil;

public class GetFailuerFiles implements Runnable{
	private static Logger logger = Logger.getLogger(GetFailuerFiles.class);
	public static List<String> failuerList = new ArrayList<String>();
	public static List<MobileEntity> succLists = new ArrayList<MobileEntity>();
	public List<String> list;
	public static void main(String[] args) throws IOException, InterruptedException {
		List<GetFailuerFiles> lists = getFailLists("E:\\mobile_belongto\\failuerFile");
		logger.info("the lists size is : " + lists.size());
		ExecutorService  pools =   Executors.newFixedThreadPool(lists.size());
		for(GetFailuerFiles failEntity :lists){
			pools.execute(failEntity);
			Thread.sleep(2000);
		}
		pools.shutdown();
	}

	public List<String> getList() {
		return list;
	}

	public static List<GetFailuerFiles> getFailLists(String fileDir) throws IOException{
		List<GetFailuerFiles> returnEntity = new ArrayList<GetFailuerFiles>();
		GetFailuerFiles entity = new GetFailuerFiles();
		List<String> failLists = new ArrayList<String>();
		File dir = new File(fileDir);
		File[] files = dir.listFiles();
		Set<String> sets = new HashSet<String>();
		BufferedReader bf = null;
		for(File f :files ){
			FileReader fr = new FileReader(f);
			 bf = new BufferedReader(fr);
			 String mobile = "";
			 while((mobile = bf.readLine()) != null){
				 sets.add(mobile);
			 }
		}
		for(String entry:sets){
			failLists.add(entry);
			if(failLists.size() % 5000 == 0){
				entity.setList(failLists);
				returnEntity.add(entity);
				entity = new GetFailuerFiles();
				failLists = new ArrayList<String>();
			}
		}
		entity.setList(failLists);
		returnEntity.add(entity);
		bf.close();
		return returnEntity;
	}
	public void setList(List<String> list) {
		this.list = list;
	}


	@Override
	public void run() {
		for(String mobile:this.list){
			MobileEntity entity = new MobileEntity();
			try {
				entity = StartCrawl.getMobileEntity(mobile);
				logger.info("抓取了了一次 ： " + mobile);
				Thread.sleep(6000);
				boolean flag = ValidEntityUtil.judgeEntityValid(entity);
				if(!flag)continue;
				succLists.add(entity);
				if(succLists.size()%200 == 0){
					File saveFile = new File("E:\\mobile_belongto\\succFiles\\" + MobielConstant.SDF.format(new Date()));
					logger.info(" into one xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
//					if(! saveFile.exists())saveFile.createNewFile();
					IOUtil.saveCollection(succLists,saveFile);
					succLists =  new ArrayList<MobileEntity>();
				}
			} catch (Exception e) {
				failuerList.add(mobile);
				if(failuerList.size() %1000 == 0){
					File failuerFile = new File("E:\\mobile_belongto\\failuerFile\\failuer_" + MobielConstant.SDF.format(new Date()));
					try {
						IOUtil.saveCollection(failuerList,failuerFile);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				logger.error("抓取失败的文件又失败了 xxxxxxxxxxxxxxxxx  : " + mobile);
			}
		}
		File saveFile = new File("E:\\mobile_belongto\\succFiles\\" + MobielConstant.SDF.format(new Date()));
		File failuerFile = new File("E:\\mobile_belongto\\failuerFile\\failuer_" + MobielConstant.SDF.format(new Date()));
			try {
				IOUtil.saveCollection(failuerList,failuerFile);
				IOUtil.saveCollection(succLists,saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
