/**
* 文件名：StartCrawl.java
* 创建日期： 2016年10月11日
* 作者：     lwd
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016年10月11日
*   修改人：lwd
*   修改内容：
*/
package com.seedman.schedule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.seedman.constant.MobielConstant;
import com.seedman.entity.MobileEntity;
import com.seedman.utils.IOUtil;


/**
 * 功能描述：攫取网页内容
 *
 */
public class StartCrawl {
	private static Logger logger = Logger.getLogger(StartCrawl.class);
	public static List<String> failuerList = new ArrayList<String>();
	public static void main(String[] args) throws Exception {
		String mobileSeg = "134|135|136|137|138|139|147";
//		String mobileSeg = "134|135|136|137|138|139|147|150|151|152|157|158|159|182|183|184|187|188|178|170|130|131|132|133|145|155|156|185|186|175|171|176|170|153|177|180|181|189|170";
		String[] segs = mobileSeg.split(MobielConstant.MOBILESPLITER);
		ExecutorService  pools =   Executors.newFixedThreadPool(7);
		for(String seg:segs){
			GetFromUrlThreads singleThread = new GetFromUrlThreads();
			singleThread.setNumsSeg(seg);
			pools.execute(singleThread);
		}
		logger.info("xxxxxxxxxxxxxxxxxx 程序执行完毕 xxxxxxxxxxxx 共执行了：xxxxxxxxxxxxxxxxxxxx   ");
		File failuerFile = new File("E:\\mobile_belongto\\failuerFile\\failuer_" + MobielConstant.SDF.format(new Date()));
		IOUtil.saveCollection(failuerList,failuerFile);
		pools.shutdown();
//		Set<String> set = getMobileSegFromRaf();
//		logger.info("the set length is : "+ set.size());
//		List<MobileEntity> entityLists = new ArrayList<MobileEntity>();
//		int total = 0;
//		for(String str:set){
//		MobileEntity entity = getMobileEntity(str);
//		total += 1;
//		boolean flag = ValidEntityUtil.judgeEntityValid(entity);
//		if(!flag)continue;
//		entityLists.add(entity);
//		if(entityLists.size()%1000 == 0){
//			File saveFile = new File("E:\\mobile_belongto\\" + MobielConstant.SDF.format(new Date()) + "_" + System.currentTimeMillis());
//			logger.info(" into one xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
//			if(! saveFile.exists())saveFile.createNewFile();
//			IOUtil.saveCollection(entityLists,saveFile);
//			entityLists =  new ArrayList<MobileEntity>();
//		}
//		}
		
//		File saveFile = new File("E:\\mobile_belongto\\" + MobielConstant.SDF.format(new Date())+ "" + System.currentTimeMillis());
//		if(! saveFile.exists())saveFile.createNewFile();
//		IOUtil.saveCollection(entityLists,saveFile);
//		logger.info("xxxxxxxxxxxxxxxxxx 程序执行完毕 xxxxxxxxxxxx 共执行了：xxxxxxxxxxxxxxxxxxxx  " + total + "  次");
//		MobileEntity entity = getMobileEntity(mobile);
//		System.out.println(entity);
	}


	


	/**
	 * @return
	 * 随机获取手机号段
	 */
	@SuppressWarnings("unused")
	private static Set<String> getMobileSegFromRaf() {
		String mobileSeg = "134|135|136|137|138|139|147";
//		String mobileSeg = "134|135|136|137|138|139|147|150|151|152|157|158|159|182|183|184|187|188|178|170|130|131|132|133|145|155|156|185|186|175|171|176|170|153|177|180|181|189|170";
		String[] segs = mobileSeg.split(MobielConstant.MOBILESPLITER);
		logger.info("segs's length is : " + segs.length);
		int times = 0;
		Set<String> mobileSet = new HashSet<String>();
		for(String mo:segs){
			String fullSeg = "";
			for(int i = 0; i < 10000 ; i ++){
				times ++;
				String index = String.valueOf(i);
				int length = index.length();
				switch(length){
				case (1):
					fullSeg = "000" + index;
				break;
				case (2):
					fullSeg = "00" + index;
				break;
				case (3):
					fullSeg = "0" + index;
				break;
				default:
					fullSeg = index;
				}
				mobileSet.add(mo + fullSeg);
				
			}
		}
		logger.info(" total times is : " + times );
		return mobileSet;
	}

	/**
	 * @param mobile
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static MobileEntity getMobileEntity(String mobile) throws Exception {
		MobileEntity entity = new MobileEntity();
		
		try {
			String url = "http://www.ip138.com:8080/search.asp?mobile=" + mobile + "&action=mobile";
			Document doc = Jsoup.connect(url).get();
			Elements trs = doc.select("table");
			if(trs.size() == 2){
				Elements second = trs.get(1).select("td");
				for(int k = 0 ; k < second.size(); k ++){
					String text = second.get(k).text();
	                if(text.startsWith("手机号码") ||text.startsWith("++") || text.trim().equals(""))continue;
	                if(text.startsWith("您查询的手机号码段")){
	                	text = second.get(k).nextElementSibling().text();
	                	entity.setMobileSeg(text.replaceAll(" 测吉凶\\(新\\)", ""));
	                	continue;
	                }
	                if(text.startsWith("卡号归属地")){
	                	text = second.get(k).nextElementSibling().text();
	                	entity.setBelongTo(text);
	                	continue;
	                }
	                if(text.startsWith("卡 类 型")){
	                	text = second.get(k).nextElementSibling().text();
	                	entity.setCardType(text);
	                	continue;
	                }
	                if(text.startsWith("区 号")){
	                	text = second.get(k).nextElementSibling().text();
	                	entity.setPhoneAreaCode(text);
	                	continue;
	                }
	                if(text.startsWith("邮 编")){
	                	text = second.get(k).nextElementSibling().text();
	                	entity.setPostalCode(text.replaceAll("更详细的..", "").trim());
	                	continue;
	                }
				}
			}
		} catch (IOException e) {
			failuerList.add(mobile);
			if(failuerList.size() %1000 == 0){
				File failuerFile = new File("E:\\mobile_belongto\\failuerFile\\failuer_" + MobielConstant.SDF.format(new Date()));
				IOUtil.saveCollection(failuerList,failuerFile);
//				failuerList = new ArrayList<String>();
			}
			logger.warn("parse the mobile belong to Error .......",e);
			logger.error("程序又失败一次了............." + mobile);
		}
		return entity;
	}





	public static Set<String>  getMobileSegFromRaf(String prifNumsSeg) {
		Set<String> mobileSet = new HashSet<String>();
		String fullSeg = "";
		for(int i = 0; i < 10000 ; i ++){
			String index = String.valueOf(i);
			int length = index.length();
			switch(length){
			case (1):
				fullSeg = "000" + index;
			break;
			case (2):
				fullSeg = "00" + index;
			break;
			case (3):
				fullSeg = "0" + index;
			break;
			default:
				fullSeg = index;
			}
			mobileSet.add(prifNumsSeg + fullSeg);
			
		}
	
		return mobileSet;
	}
}
