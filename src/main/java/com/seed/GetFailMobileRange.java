package com.seed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.seed.mobileUtils.CrawlMobileUtil;
import com.seed.utils.GetAllMobileRanges;
/**
 * 爬取程序主入口
 * @author lwd
 * @date: 2018年2月1日 下午2:52:41
 */
public class GetFailMobileRange {
	private static Logger logger = Logger.getLogger(GetAllMobileRanges.class);
	public static void main(String[] args) throws Exception {
		System.out.println(System.currentTimeMillis());
		List<String> success = getSuccessList();
		List<String> all_mobiles = GetAllMobileRanges.getAllRanges();
		
		Set<String> all_failure = getDiffMobileRanges(success,all_mobiles);
		logger.info("length of success ===>>> " + success.size());
		logger.info("length of all_mobiles ===>>> " + all_mobiles.size());
		logger.info("length of all_failure ===>>> " + all_failure.size());
		String base_url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=";
		
		new CrawlMobileUtil().crawlerMobiles(all_failure,base_url);
	}
	private static Set<String> getDiffMobileRanges(List<String> success,
			List<String> all_mobiles) throws Exception {
		Set<String> fail = new HashSet<String>();
		Map<String,Integer> succ_map = new HashMap<String,Integer>();
		Set<String> succ_set = new HashSet<String>();
		
		for(String mobile:success){
			succ_map.put(mobile, 1);
			succ_set.add(mobile);
		}
		for(String mobile:all_mobiles){
			if(succ_map.get(mobile) != null)continue;
			fail.add(mobile);
		}
		return fail;
	}
	private static List<String> getSuccessList() {
		BufferedReader bf = null;
		FileReader fr;
		List<String> succ = new ArrayList<String>();
		try {
			fr = new FileReader(new File("./all_success"));
			bf = new BufferedReader(fr);
			String mobile = "";
			while((mobile = bf.readLine()) != null){
				succ.add(mobile);
			 }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null != bf){
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		return succ;
	}
	
}
