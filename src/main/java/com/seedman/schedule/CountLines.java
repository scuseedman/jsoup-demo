package com.seedman.schedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.seedman.constant.MobielConstant;
import com.seedman.utils.IOUtil;


public class CountLines {
	public static void main(String[] args) throws Exception {
		int totalLine = 0;
		File dir = new File("E:\\mobile_belongto\\failuerFile");
		File[] files = dir.listFiles();
		Set<String> sets = new HashSet<String>();
		int filesNum = 0;
		for(File f :files ){
			filesNum += 1;
			FileReader fr = new FileReader(f);
			 BufferedReader bf = new BufferedReader(fr);
			 String mobile = "";
			 while((mobile = bf.readLine()) != null){
				 sets.add(mobile);
				 totalLine += 1;
			 }
		}
		System.out.println("总记录数：" + totalLine + " ; 文件个数为：" + filesNum);
		System.out.println("sets 的记录总数是： " + sets.size());
		File failuerFile = new File("E:\\mobile_belongto\\failuerFile\\20161025_21_failer_" + MobielConstant.SDF.format(new Date()));
		IOUtil.saveCollection(sets,failuerFile);
	}
}	
