/**
* 文件名：ValidEntity.java
* 创建日期： 2016年10月13日
* 作者：     lwd
* Copyright (c) 2009-2016 架构平台组
* All rights reserved.
 
* 修改记录：
* 	1.修改时间：2016年10月13日
*   修改人：lwd
*   修改内容：
*/
package com.seedman.utils;

import com.seedman.entity.MobileEntity;


/**
 * 功能描述：
 *
 */
public class ValidEntityUtil {
	/**
	 * @param entity
	 * 判断获取的值是否为null，null则不添加
	 * @return
	 */
	public static boolean judgeEntityValid(MobileEntity entity) {
		if(null == entity.getBelongTo())return false;
		if(null == entity.getCardType())return false;
		if(null == entity.getMobileSeg())return false;
		if(null == entity.getPhoneAreaCode())return false;
		if(null == entity.getPostalCode())return false;
		return true;
	}
}
