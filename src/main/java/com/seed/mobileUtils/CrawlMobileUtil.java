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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
	static File failFile = null;
	static Pattern pattern = Pattern.compile(" ");
	static{
		succFile = new File("");
		failFile = new File("");
	}
	public void start(String base_url, String[] mobiles) {
		// TODO Auto-generated method stub
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
			System.out.println("length of mos ===>>> " + mos.size());
//			for(String mobile:mos){
//				System.out.println(mobile);
//			}
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
	private void crawlerMobiles(List<String> mos, String base_url) {
		// TODO Auto-generated method stub
		Document doc;
		List<String> success = new ArrayList<String>();
		List<String> faileds = new ArrayList<String>();
		MobileEntity entity = null;
		for(String mobile:mos){
			mobile = "1568410";
			entity = new MobileEntity(mobile);
			try {
				System.out.println("======================>>>>>>> " + mobile);
				doc = Jsoup.connect(base_url + mobile).get();
				Elements eles = doc.getElementsByClass("tdc");
				System.out.println(eles.size());
				for(int i = 4; i< 8; i ++){//4-8 为我所需
					System.out.println(eles.get(i).select("td").get(1).text());
					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				}
				entity.setProvice(pattern.split(eles.get(4).select("td").get(1).text())[0]);
				entity.setCity(pattern.split(eles.get(4).select("td").get(1).text())[1]);
				break;
			} catch (IOException e) {
				logger.warn(mobile + ">>> failed");
				faileds.add(mobile);
				if(faileds.size() % 100 == 0){
					try {
						IOUtil.saveCollection(faileds,failFile );
						faileds = new ArrayList<String>();
					} catch (Exception ex) {
						logger.warn("save collections failed >>>" + faileds);
					}
				}
			}finally{
				if(faileds.size() != 0){
					try {
						IOUtil.saveCollection(faileds,failFile );
					} catch (Exception e) {
						logger.warn("save collections failed >>>" + faileds);
					}
				}
			}
			
		}
	}

}
