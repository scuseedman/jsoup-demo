package com.seedman.schedule;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.seedman.constant.MobielConstant;
import com.seedman.entity.MobileEntity;
import com.seedman.utils.IOUtil;
import com.seedman.utils.ValidEntityUtil;


public class GetFromUrlThreads  implements Runnable{
	private static Logger logger = Logger.getLogger(GetFromUrlThreads.class);
	public static List<MobileEntity> entityLists = new ArrayList<MobileEntity>();
	private String numsSeg;
	
	@Override
	public void run() {
		 
		Set<String> sigleSet = StartCrawl.getMobileSegFromRaf(this.numsSeg);
		for(String str:sigleSet){
			logger.info("执行查询的手机号段为： " + str);
			MobileEntity entity = new MobileEntity();
			try {
				entity = StartCrawl.getMobileEntity(str);
				Thread.sleep(1400);
				boolean flag = ValidEntityUtil.judgeEntityValid(entity);
				if(!flag)continue;
				entityLists.add(entity);
				if(entityLists.size()%200 == 0){
					File saveFile = new File("E:\\mobile_belongto\\succFiles\\" + MobielConstant.SDF.format(new Date()));
					logger.info(" into one xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
//					if(! saveFile.exists())saveFile.createNewFile();
					IOUtil.saveCollection(entityLists,saveFile);
					entityLists =  new ArrayList<MobileEntity>();
				}
				
				
			} catch (Exception e) {
				logger.warn("查询号段出错了xxxxxxxxxxxxxxxxxxxxxx  " ,e);
			}
		}
		File saveFile = new File("E:\\mobile_belongto\\succFiles\\" + MobielConstant.SDF.format(new Date()));
		try {
			IOUtil.saveCollection(entityLists,saveFile);
		} catch (Exception e) {
			logger.warn("获取数据又出错了.......................... ",e);
		}
	}

	public String getNumsSeg() {
		return numsSeg;
	}

	public void setNumsSeg(String numsSeg) {
		this.numsSeg = numsSeg;
	}
	
}
