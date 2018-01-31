/** 
 * Project Name:jsoup-mobile-belong 
 * File Name:MobileEntity.java 
 * Package Name:com.seed 
 * Date:2018年1月31日下午5:02:46 
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
package com.seed.entity;


/** 
 * ClassName:MobileEntity
 * Date:     2018年1月31日 下午5:02:46 
 * DESC:	txt格式：前缀-号段-省份-城市-运营商-邮编-区号-行政区划编码-类型
 * @author   lwd 
 * @version   
 * @since    JDK 1.7
 * @see       
 */
public class MobileEntity {
	private String pref;
	private String mobile;
	private String provice;
	private String city;
	private String isp;//运营商
	private String postCode;//邮编
	private String cityCode;//区号
	private String area_code;//行政区划 
	private String types;//运营商类型
	private static final String SPLIT = "," ;
	public String getPref() {
		return pref;
	}
	public void setPref(String pref) {
		this.pref = pref;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(pref).append(SPLIT);
		sb.append(mobile).append(SPLIT);
		sb.append(provice).append(SPLIT);
		sb.append(city).append(SPLIT);
		sb.append(isp).append(SPLIT);
		sb.append(postCode).append(SPLIT);
		sb.append(cityCode).append(SPLIT);
		sb.append(area_code).append(SPLIT);
		sb.append(types);
		return sb.toString();
		
	}
	public MobileEntity(String pref, String mobile, String provice,
			String city, String isp, String postCode, String cityCode,
			String area_code, String types) {
		super();
		this.pref = pref;
		this.mobile = mobile;
		this.provice = provice;
		this.city = city;
		this.isp = isp;
		this.postCode = postCode;
		this.cityCode = cityCode;
		this.area_code = area_code;
		this.types = types;
	}
	public MobileEntity() {
		super();
	}
	public MobileEntity(String mobile) {
		this.pref = mobile.substring(0, 4);
		this.mobile = mobile;
	}
	
	
	
}
