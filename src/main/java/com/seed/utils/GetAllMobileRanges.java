package com.seed.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class GetAllMobileRanges {
	public static List<String> getAllRanges(){
		String mobiles_range = "134 135 136 137 138 139 147 150 151 152 157 158 159 172 178 182 183 184 187 188 198 130 131 132 145 155 156 166 171 175 176 185 186 133 149 153 173 177 180 181 189 199 170";
		List<String> mobiles = Arrays.asList(mobiles_range.split(" "));
		List<String> ranges = new ArrayList<String>();
		for(String mobile:mobiles){
			for(int i = 0; i < 10000; i ++){
				if(i < 10){
					ranges.add(mobile + "000" + i);
				}else if(i < 100){
					ranges.add(mobile + "00" + i);
				}else if(i < 1000){
					ranges.add(mobile + "0" + i);
				}else{
					ranges.add(mobile + i);
				}
			}
//			IOUtil.saveCollection(ranges, new File("./all_mobile_ranges.txt"));
		}
		return ranges;
		
	}
}
 