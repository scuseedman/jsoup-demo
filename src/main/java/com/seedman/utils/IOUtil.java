/**
* 文件名：IOUtil.java
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * 功能描述：
 *
 */
public class IOUtil {
	/**
	 * 将给定的集合中的每一个元素的toString返回的
	 * 字符串以行为单位写入到给定的文件中
	 * @param c
	 * @param file
	 * @throws Exception
	 */
	public static void saveCollection(
					  @SuppressWarnings("rawtypes") Collection c,File file)
							throws Exception{
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(file,true));
			for(Object o : c){
				pw.println(o);
			}
		} catch (Exception e) {
			throw e;
		} finally{
			if(pw != null){
				pw.close();
			}
		}
	}
}
