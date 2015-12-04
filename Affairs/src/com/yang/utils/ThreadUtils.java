package com.yang.utils;

public class ThreadUtils {
	/**
	 * 获取当前的函数名
	 * 仅能返回getCrtMtdName，无法返回调用者的名字
	 */
	public static String getCrtMtdName(){
		return Thread.currentThread().getStackTrace()[2].getMethodName();  
	}
}
