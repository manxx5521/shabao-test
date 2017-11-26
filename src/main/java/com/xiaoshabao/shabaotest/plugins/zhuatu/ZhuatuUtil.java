package com.xiaoshabao.shabaotest.plugins.zhuatu;

public class ZhuatuUtil {
	/**
	 * 去除可能存在的特殊字符(目前只做等于配置使用)
	 * 
	 * @return
	 */
	public static String parserTitleName(String title) {
		title = title.replace("/", "");
		title = title.replace("\\", "");
		title = title.replace("|", "");
		title = title.replace("?", "");
		title = title.replace(":", "");
		title = title.replace("*", "");
		title = title.replace("<", "");
		title = title.replace(">", "");
		title = title.replace("amp;", "");
		return title;
	}
}
