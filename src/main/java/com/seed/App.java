/** 
 * Project Name:jsoup-mobile-belong 
 * File Name:App.java 
 * Package Name:com.seed 
 * Date:2018年1月31日下午3:50:54 
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
package com.seed;

import com.seed.mobileUtils.CrawlMobileUtil;

/** 
 * ClassName:App
 * Date:     2018年1月31日 下午3:50:54 
 * DESC:	
 * @author   lwd 
 * @version   
 * @since    JDK 1.7
 * @see       
 */
public class App {
	public static void main(String[] args) {
		String base_url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=";
//		134 135 136 137 138 139 147 150 151 152 157 158 159 172 178 182 183 184 187 188 198 130 131 132 145 155 156 166 171 175 176 185 186 133 149 153 173 177 180 181 189 199 170
		String mobiles_range = "134 135 136 137 138 139 147 150 151 152 157 158 159 172 178 182 183 184 187 188 198 130 131 132 145 155 156 166 171 175 176 185 186 133 149 153 173 177 180 181 189 199 170";
		String[] mobiles = mobiles_range.split(" ");
		System.out.println("length of mobiles ===>>> " + mobiles.length);
//		381409 20180201 others
		new CrawlMobileUtil().start(base_url,mobiles);
				
	}
}	
