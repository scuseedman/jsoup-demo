package com.seed;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.seed.utils.IOUtil;

public class WriteAllMobileRanges {
	public static void main(String[] args) throws Exception {
		String mobiles_range = "134 135 136 137 138 139 147 150 151 152 157 158 159 172 178 182 183 184 187 188 198 130 131 132 145 155 156 166 171 175 176 185 186 133 149 153 173 177 180 181 189 199 170";
		Set<String> mobiles = new HashSet<String>();
		List<String> ranges = Arrays.asList(mobiles_range.split(" "));
		for(String range :ranges){
			for(int i = 0; i < 10000; i ++){
				if(i < 10){
					mobiles.add(range + "000" + i);
				}else if(i < 100){
					mobiles.add(range + "00" + i);
				}else if(i < 1000){
					mobiles.add(range + "0" + i);
				}else{
					mobiles.add(range + i);
				}
			}
		}
		IOUtil.saveCollection(mobiles, new File("./all_mobile_ranges.txt"));
	}
}
