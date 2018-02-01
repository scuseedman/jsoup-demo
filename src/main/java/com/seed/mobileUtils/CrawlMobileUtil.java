/** 
 * Project Name:jsoup-mobile-belong 
 * File Name:CrawlMobileUtil.java 
 * Package Name:com.seed.mobileUtils 
 * Date:2018年1月31日下午4:02:49 
 * Copyright (c) 2018,  All Rights Reserved. 
 * 
* 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃ 　
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　... ┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　　　　　　　　　　
 * 　　　　　　　　　┃　　　┃   神兽无影，BUG无踪！
 * 　　　　　　　　　┃　　　┃　　　　　　　　　　　
 * 　　　　　　　　　┃　　　┃  　　　　　　
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　　　　　　　　　　
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
*
*
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽无影，BUG无踪！
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * ━━━━━━感觉萌萌哒━━━━━━
*/ 
package com.seed.mobileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.seed.entity.MobileEntity;
import com.seed.utils.IOUtil;

/** 
 * ClassName:CrawlMobileUtil
 * Date:     2018年1月31日 下午4:02:49 
 * DESC:	
 * @author   lwd 
 * @version   
 * @since    JDK 1.7
 * @see       
 */
public class CrawlMobileUtil {
	private static Logger logger = Logger.getLogger(CrawlMobileUtil.class);
	static File succFile = null;
	static File successMobilesFile = null;
	static Pattern pattern = Pattern.compile(" ");
	static{
		succFile = new File("./another_crawler_success");
		successMobilesFile = new File("./all_success");
	}
	public void start(String base_url, String[] mobiles) {
		for(String mobileRange :mobiles){
			List<String> mos = new ArrayList<String>();
			for(int i = 0; i < 10000; i ++){
				String mobile = "";
				if(i < 10){
					mobile = base_url + mobileRange + "000" + i;
					mos.add(mobile);
				}else if(i < 100){
					mobile = base_url + mobileRange + "00" + i;
					mos.add(mobile);
				}else if(i < 1000){
					mobile = base_url + mobileRange + "0" + i;
					mos.add(mobile);
				}else{
					mobile = base_url + mobileRange + i;
					mos.add(mobile);
				}
				
			}
			crawlerMobiles(mos,base_url);//开始爬取
			break;
		}
	}

	/**
	 * 爬取每一个号段
	 * @param mos
	 * @param base_url 
	 * @throws Exception 
	 */
	public void crawlerMobiles(List<String> mos, String base_url) {
		Document doc;
		List<MobileEntity> success = new ArrayList<MobileEntity>();
		List<String> succ_mobiles = new ArrayList<String>();
		MobileEntity entity = null;
		String isp = null;
		for(String mobile:mos){
			entity = new MobileEntity(mobile);
			try {
				doc = Jsoup.connect(base_url + mobile).get();
				Elements eles = doc.getElementsByClass("tdc");
				logger.info("mobile ===>>> " + mobile + " >>> the size of elements ===>>> " + eles.size());
				if(eles.size() != 8 ){
					logger.warn(mobile + "===>>> error !!! will continue !!!");
					continue;
				}
				entity.setProvice(eles.get(4).select("td").get(1).text().substring(0,2));//前两位默认省
				entity.setCity(eles.get(4).select("td").get(1).text().substring(3));//3位后为市
				
				entity.setTypes(eles.get(5).select("td").get(1).text());
				entity.setCityCode(eles.get(6).select("td").get(1).text());
				entity.setPostCode(eles.get(7).select("td").get(1).text().replaceAll("[^0-9]", ""));
				isp = entity.getTypes().contains("移动")?"移动":entity.getTypes().contains("联通")?"联通":"电信";
				entity.setIsp(isp);
				success.add(entity);
				succ_mobiles.add(entity.getMobile());//成功的号段
				Thread.sleep(300);
				if(success.size() % 100 == 0 ){
					IOUtil.saveCollection(success, succFile);
					IOUtil.saveCollection(succ_mobiles, successMobilesFile);
					success = new ArrayList<MobileEntity>();
					succ_mobiles = new ArrayList<String>();
					Thread.sleep(3000);
				}
			}  catch (Exception e) {
				logger.warn(mobile + ">>> failed");
			}
			
		}
		try {
			IOUtil.saveCollection(success, succFile);
			IOUtil.saveCollection(succ_mobiles, successMobilesFile);
			success = new ArrayList<MobileEntity>();
			succ_mobiles = new ArrayList<String>();
		} catch (Exception ex) {
			logger.warn("save collections failed >>>" + succ_mobiles);
		}
		
	}

}
