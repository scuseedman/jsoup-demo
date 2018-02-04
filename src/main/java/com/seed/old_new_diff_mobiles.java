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

public class old_new_diff_mobiles {
	public static void main(String[] args) throws Exception {
		List<String> old_success = getMobilesFromFile(new File("./old_success_mobiles"));
		List<String> new_success_mobiles = getMobilesFromFile(new File("./another_crawler_success"));
		Map<String,Integer> new_success_mobiles_map = new HashMap<String,Integer>();
		for(String mobile : new_success_mobiles){
			new_success_mobiles_map.put(mobile, 1);
		}
		System.out.println("old_success ===>>> " + old_success.size() + " ; new_success_mobiles ===>>> " + new_success_mobiles.size());
		Set<String> diff_mobiles = new HashSet<String>();
		for(String mobile : old_success){
			if(null != new_success_mobiles_map.get(mobile)) diff_mobiles.add(mobile);
		}
		System.out.println("size of diff_mobiles ===>>> " + diff_mobiles.size());
		
	}

	private static Map<String, Integer> getNewSuccessMobiles(File file) throws Exception {
		Map<String,Integer> map = new HashMap<String,Integer>();
		BufferedReader br = null;
		String line = null;
		br = new BufferedReader(new FileReader(file));
		while(null != (line = br.readLine())){
			map.put(line, 1);
		}
		return map;
	}

	private static List<String> getMobilesFromFile(File file) {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(file));
			while(null != (line = br.readLine())){
				list.add(line);
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
