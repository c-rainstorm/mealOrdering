package com.chenswe.util;

public class StringUtil {
	
	/**
	 * 判断一个字符串内是否有值
	 * 
	 * @param str
	 * 			带判断字符串
	 * @return
	 * 			false -- 字符串不为空
	 * 			true -- 空串或null
	 */
	public static boolean isEmpty(String str) {
		boolean flag = false;
		if(str == null || str.length() == 0)
			flag = true;
		
		return flag;
	}

//	public static void main(String[] args) {
//		String test = "张宝琛  17839918876";
//		System.out.println(test);
//		System.out.println(test.split(" ")[0]);
//		System.out.println(test.split(" ")[0]);
//		
//	}

}
