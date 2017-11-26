package com.xiaoshabao.shabaotest.plugins.zhuatu.core;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xiaoshabao.shabaotest.plugins.zhuatu.DownloadTuTask;
import com.xiaoshabao.shabaotest.plugins.zhuatu.MTuInfo;
import com.xiaoshabao.shabaotest.plugins.zhuatu.ZhuatuConfig;
import com.xiaoshabao.shabaotest.plugins.zhuatu.ZhuatuDownloadPool;
import com.xiaoshabao.shabaotest.plugins.zhuatu.ZhuatuUtil;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.ZhuatuService;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.able.LoadFileAble;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.able.ProjectAble;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.able.ZhuatuDownloadAble;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.able.ZhuatuWaitAble;

public class DownloadZhuatuImpl extends ZhuatuToHeavy {
	/** 是否需要下载池 */
	private boolean isNeedPool = false;
	/** 存储已经下载的项目列表 */
	protected List<String> projects = new LinkedList<String>();

	/**
	 * 初始化服务列表
	 */
	@Override
	protected void initBeforSerivce(ZhuatuService service) {
		if (service instanceof ZhuatuDownloadAble) {
			isNeedPool = true;
		}

		// 加载本地文件
		if (service instanceof LoadFileAble) {
			if (StringUtils.isEmpty(this.config.getSavePath())) {
				log.error("启用了加载本地文件接口，但是没有配置本地文件目录");
				return;
			}
			File path = new File(config.getSavePath());
			if (!path.exists() || path.isFile()) {
				path.mkdir();
			}
			for (File file : path.listFiles()) {
				if (file.isDirectory()) {
					projects.add(file.getName());
				}
			}
		}
		
		if (service instanceof ZhuatuDownloadAble) {
			ZhuatuDownloadPool.init();
		}
	}

	/**
	 * 解析当前页项目
	 */
	@Override
	protected boolean exeCurrPageProjet(ZhuatuService service, MTuInfo tuInfo) {

		// 如果是项目服务，进行项目比对排重
		if (service instanceof ProjectAble) {
			if(projects.contains(ZhuatuUtil.parserTitleName(tuInfo.getTitle()))) {
				log.warn("项目 {} 未下载（项目已经存在）。", tuInfo.getTitle());
				return false;
			}else {
				log.warn("**开始下载项目 {}。(目录：{})***********", ZhuatuUtil.parserTitleName(tuInfo.getTitle()),config.getSavePath());
			}
		}

		// 需要等待相同内容连接池
		if (isNeedPool && service instanceof ZhuatuWaitAble) {
			// 等待现成
			ZhuatuDownloadPool.getInstance().waitActiveThread();
		}

		// 如果是需要下载的url
		if (service instanceof ZhuatuDownloadAble) {
			String fileNameUrl = tuInfo.getUrl();
			if (fileNameUrl.contains("?")) {
				fileNameUrl = fileNameUrl.substring(0, fileNameUrl.indexOf("?"));
			}
			String fileName = fileNameUrl.substring(fileNameUrl.lastIndexOf("/") + 1, fileNameUrl.length());
			
			String downloadUrl=parserDowloadUrl(tuInfo.getUrl());
			DownloadTuTask myTask = new DownloadTuTask(downloadUrl, config.getSavePath() + File.separator
					+ZhuatuUtil.parserTitleName(tuInfo.getTitle()) + File.separator + ZhuatuUtil.parserTitleName(fileName));
			ZhuatuDownloadPool.getInstance().execute(myTask);
		}
		return true;
	}

	@Override
	public void start(String url, List<ZhuatuService> zhuatuServices, ZhuatuConfig config) {
		super.start(url, zhuatuServices, config);
		ZhuatuDownloadPool.getInstance().shutdown();
	}
	
	/**
	 * 根据配置config信息，调整下载url
	 * @param url
	 * @return
	 */
	private String parserDowloadUrl(String url) {
		if(config.getDownlaodUrlParser()!=null) {
			url=config.getDownlaodUrlParser().apply(url);
		}
		return url;
	}
	
}