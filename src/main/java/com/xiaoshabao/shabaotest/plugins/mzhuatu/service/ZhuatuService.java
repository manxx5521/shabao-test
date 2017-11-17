package com.xiaoshabao.shabaotest.plugins.mzhuatu.service;

import java.util.List;

import com.xiaoshabao.shabaotest.plugins.mzhuatu.MTuInfo;
import com.xiaoshabao.shabaotest.plugins.mzhuatu.ZhuatuConfig;


/**
 * 抓图接口
 * <p>重要服务，所有的自定义接口要实现本接口，用来解析HTML页的内容</p>
 */
public interface ZhuatuService {
	/**
	 * 主要解析内容
	 * @param html
	 * @param parentInfo
	 * @param projects 已经下载的项目
	 * @param newProject 是否是新项目（如果false表示是下一页解析）
	 * @return
	 */
	public List<MTuInfo> parser(String html,MTuInfo pageInfo,ZhuatuConfig config/*,List<String> projects,boolean newProject*/) throws Exception;
	
	/**
	 * 解析下一页的URL
	 */
	public String nextPage(String html) throws Exception;

}
