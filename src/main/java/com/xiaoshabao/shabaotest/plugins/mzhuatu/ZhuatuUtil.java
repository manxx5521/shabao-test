package com.xiaoshabao.shabaotest.plugins.mzhuatu;

public class ZhuatuUtil {
	/**
	 * 去除可能存在的特殊字符(目前只做等于配置使用)
	 * 
	 * @return
	 */
	public static String parserTitleName(String title) {
		return title.trim().replace("amp;", "");
	}
}
