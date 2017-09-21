package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.util.List;

/**
 * 抓图接口
 * <p>重要服务，所有的自定义接口要实现本接口，用来解析HTML页的内容</p>
 */
public interface MZhuatuService {
	/**
	 * 主要解析内容
	 * @param html
	 * @param parentInfo
	 * @param projects 已经下载的项目
	 * @param downloadURL 当前项目已经下载的链接
	 * @return
	 */
	public List<MTuInfo> parser(String html,MTuInfo parentInfo,List<String> projects,List<String> downloadURL);
	
	/**
	 * 解析下一页的URL
	 */
	public String nextPage(String html);

}