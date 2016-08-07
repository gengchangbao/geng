package com.data.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App {

	public static void main(String[] args) throws Exception {
			/*StringBuffer buffer=new StringBuffer();
			char[] chars={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			String src="123456";
			byte[]	bytes=src.getBytes();
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] targ=md.digest(bytes);
			for(byte b:targ){
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[b & 0x0F]);
				
			}
			System.out.println(buffer.toString());*/
		String key ="q12_1";
		System.out.println(Integer.parseInt(key.substring(1,key.lastIndexOf("_"))));
	}

}
