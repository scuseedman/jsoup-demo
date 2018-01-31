package com.seed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.seed.utils.IOUtil;

public class GetFailMobileRange {
	public static void main(String[] args) throws Exception {
		List<String> success = getSuccess();
		List<String> all_mobiles = GetAllMobileRanges.getAllRanges();
		System.out.println("length of success ===>>> " + success.size());
		
		System.out.println("length of all_mobiles ===>>> " + all_mobiles.size());
		IOUtil.saveCollection(success, new File("./all_success"));
	}
	private static List<String> getSuccess() {
		List<String> succ = new ArrayList<String>();
		File dir = new File("E:\\formax_workspace\\spark\\spark-scala\\all_success.txt"); 
		File[] files = dir.listFiles();
		BufferedReader bf = null;
		for(File f:files){
			if(f.getName().startsWith("."))continue;
			try {
			FileReader fr = new FileReader(f);
			 bf = new BufferedReader(fr);
			 String mobile = "";
				while((mobile = bf.readLine()) != null){
					succ.add(mobile);
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return succ;
	}
	
}
