package com.data.util;

import java.security.MessageDigest;

/**
 * 数据工具类
 * @author Administrator
 *
 */
public class DataUtil {
	/**
	 * 使用MD5算法进行加密
	 * @param src
	 * @return
	 */
	public static String md5(String src){
		try{
			StringBuffer buffer=new StringBuffer();
			char[] chars={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			 src="123456";
			byte[]	bytes=src.getBytes();
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] targ=md.digest(bytes);
			for(byte b:targ){
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[b & 0x0F]);
				
			}
			return buffer.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
