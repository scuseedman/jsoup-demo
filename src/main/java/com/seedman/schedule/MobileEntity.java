/**
* 文件名：MobileEntity.java
* 创建日期： 2016年10月12日
* 作者：     lwd
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016年10月12日
*   修改人：lwd
*   修改内容：
*/
package com.seedman.schedule;

import com.seedman.constant.MobielConstant;


/**
 * 功能描述：
 *
 */
public class MobileEntity {
	//号段
	private String mobileSeg;
	//归属地
	private String belongTo;
	//卡类型
	private String cardType;
	//电话区号
	private String phoneAreaCode;
	//邮政编码
	private String postalCode;
	public String getMobileSeg() {
		return mobileSeg;
	}
	public void setMobileSeg(String mobileSeg) {
		this.mobileSeg = mobileSeg;
	}
	public String getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}
	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(mobileSeg).append(MobielConstant.SPLITER);
		sb.append(belongTo).append(MobielConstant.SPLITER);
		sb.append(cardType).append(MobielConstant.SPLITER);
		sb.append(phoneAreaCode).append(MobielConstant.SPLITER);
		sb.append(postalCode);
		return sb.toString();
	}
}
