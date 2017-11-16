package com.xiaoshabao.shabaotest.plugins.mzhuatu.core;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xiaoshabao.shabaotest.plugins.mzhuatu.DownloadTuTask;
import com.xiaoshabao.shabaotest.plugins.mzhuatu.MTuInfo;
import com.xiaoshabao.shabaotest.plugins.mzhuatu.ZhuatuDownloadPool;
import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.ZhuatuService;
import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.able.ZhuatuDownloadAble;
import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.able.ZhuatuWaitAble;
import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.able.loadFileAble;

public class DownloadZhuatuImpl extends ZhuatuToHeavy {
	/** 是否需要下载池 */
	private boolean isNeedPool = false;
	/** 存储已经下载的项目列表 */
	protected List<String> projects = new LinkedList<String>();

	@Override
	protected void initBeforSerivce(ZhuatuService service) {
		if (service instanceof ZhuatuDownloadAble) {
			isNeedPool = true;
		}

		// 加载本地文件
		if (service instanceof loadFileAble) {
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
	}

	@Override
	protected void exeCurrPageProjet(ZhuatuService service, MTuInfo tuInfo) {
		// 需要等待相同内容连接池
		if (isNeedPool && service instanceof ZhuatuWaitAble) {
			// 等待现成
			ZhuatuDownloadPool.getInstance().waitActiveThread();
		}

		// 如果是需要下载的url
		if (service instanceof ZhuatuDownloadAble) {
			String fileName = tuInfo.getUrl().substring(tuInfo.getUrl().lastIndexOf("/") + 1, tuInfo.getUrl().length());
			DownloadTuTask myTask = new DownloadTuTask(tuInfo.getUrl(),
					config.getSavePath() + File.separator + tuInfo.getTitle() + File.separator + fileName);
			ZhuatuDownloadPool.getInstance().execute(myTask);
		}
	}

}
