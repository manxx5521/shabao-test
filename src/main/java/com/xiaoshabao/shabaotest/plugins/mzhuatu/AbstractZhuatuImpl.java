package com.xiaoshabao.shabaotest.plugins.mzhuatu;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.ZhuatuService;

/**
 * 抓图抽象类
 */
public abstract class AbstractZhuatuImpl implements ZhuatuAble {

	protected Logger log = LoggerFactory.getLogger(getClass());

	protected List<ZhuatuService> zhuatuServices;
	protected ZhuatuConfig config;

	@Override
	public void start(String url, List<ZhuatuService> zhuatuServices, String savePath) {
		ZhuatuConfig config = new ZhuatuConfig();
		config.setSavePath(savePath);
		this.start(url, zhuatuServices, config);
	}

	@Override
	public void start(String url, List<ZhuatuService> zhuatuServices, String savePath, String charset) {
		ZhuatuConfig config = new ZhuatuConfig();
		config.setSavePath(savePath);
		config.setCharset(charset);
		this.start(url, zhuatuServices, config);
	}

	@Override
	public void start(String url, List<ZhuatuService> zhuatuServices, ZhuatuConfig config) {
		log.info("开始抓取：{}", url);
		if (StringUtils.isEmpty(url) || zhuatuServices == null || zhuatuServices.size() < 1) {
			log.error("错误的初始化信息，没有url或者为设置抓取服务实现");
			return;
		}
		this.zhuatuServices = zhuatuServices;
		this.config = config;

		// 预先加载服务
		for (ZhuatuService service : zhuatuServices) {
			initBySerivce(service);
		}

		MTuInfo info = new MTuInfo();
		info.setUrl(url);
		parserPage(info,0, true);
	}
	
	/**
	 * 根据加载服务的不同类型进行初始化操作
	 * @param service
	 */
	protected void initBySerivce(ZhuatuService service) {
		
	}

	/**
	 * 解析页面
	 * 
	 * @param pageInfo
	 * @param zhuatuService
	 * @param idx
	 *            当前层次
	 * @param newProject
	 *            是否是新项目（如果false表示是下一页解析）
	 */
	protected void parserPage(MTuInfo pageInfo, int idx, boolean newProject) {
		ZhuatuService zhuatuService=this.zhuatuServices.get(idx);
		
		// 访问url
		String html = ZhuatuHttpManager.getInstance().doGetAuto5(pageInfo.getUrl());
		// 访问失败跳出
		if (html == null)
			return;

		List<MTuInfo> list = zhuatuService.parser(html, pageInfo);

		Iterator<MTuInfo> iterator = list.iterator();
		while (iterator.hasNext()) {// 链表用迭代器
			MTuInfo tuInfo = iterator.next();
			
			exeCurrPageProjet(zhuatuService,tuInfo);//扩展操作
			
			if (zhuatuServices.size() > idx + 1) {
				parserPageNextIdx(tuInfo, zhuatuServices.get(idx + 1), idx + 1);// 进行下一层任务
			}
		}

		String nextUrl = zhuatuService.nextPage(html);
		if (StringUtils.isNotEmpty(nextUrl)) {
			MTuInfo tuInfo = new MTuInfo();
			tuInfo.setUrl(nextUrl);
			tuInfo.setTitle(pageInfo.getTitle());
			parserPage(tuInfo, idx, false);// 下一页
		}

	}
	
	/**
	 * 进行下一层任务
	 */
	protected void parserPageNextIdx(MTuInfo pageInfo, ZhuatuService zhuatuService, int idx) {
		parserPage(pageInfo, idx, true);
	}
	
	
	/**
	 * 对当前页解析出的项目操作
	 * @param service
	 * @param tuInfo 解析出的项目（列表中的一个）
	 */
	protected void exeCurrPageProjet(ZhuatuService service,MTuInfo tuInfo) {
		
	}
}
