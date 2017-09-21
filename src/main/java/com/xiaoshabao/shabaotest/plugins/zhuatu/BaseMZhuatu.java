package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抓图工具
 */
public class BaseMZhuatu {

	private final static Logger logger = LoggerFactory
			.getLogger(BaseMZhuatu.class);

	protected String defaultCharset = "GBK";

	protected String projectName;

	protected String savePath;

	protected List<String> projects = new LinkedList<String>();

	List<MZhuatuService> zhuatuServices;

	/**
	 * 下载线程池
	 */
	private ThreadPoolExecutor executor;

	public void start(String url, String savePath, String charset,
			List<MZhuatuService> zhuatuServices) {
		this.zhuatuServices = zhuatuServices;
		this.savePath = savePath;
		this.defaultCharset = charset;
		File path = new File(savePath);
		if (!path.exists() || path.isFile()) {
			path.mkdir();
		}
		for (File file : path.listFiles()) {
			if (file.isDirectory()) {
				projects.add(file.getName());
			}
		}

		// 初始化线程池
		executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		MTuInfo info = new MTuInfo();
		info.setUrl(url);
		parserPage(info, this.doGet5(url), zhuatuServices.get(0), 0,
				new LinkedList<String>());
		executor.shutdown();

	}

	private void parserPage(MTuInfo parentInfo, String html,
			MZhuatuService zhuatuService, int idx, List<String> downloadURL) {
		List<MTuInfo> list = zhuatuService.parser(html, parentInfo,
				downloadURL, downloadURL);

		for (MTuInfo tuInfo : list) {
			// 需要等待相同内容连接池
			if (zhuatuService instanceof MZhuatuWaitService) {
				// 检查任务是否过多
				boolean flag = true;
				while (flag) {
					if (executor.getActiveCount() < 5) {
						flag = false;
					} else {
						try {
							Thread.sleep(1000 * 2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}

			// 如果时需要下载的url
			if (zhuatuService instanceof MZhuatuDownloadService) {
				String fileName = tuInfo.getUrl().substring(
						tuInfo.getUrl().lastIndexOf("/") + 1,
						tuInfo.getUrl().length());
				DownloadTuTask myTask = new DownloadTuTask(tuInfo.getUrl(),
						savePath + File.separator + tuInfo.getTitle()
								+ File.separator + fileName);
				executor.execute(myTask);
			}

			if (zhuatuServices.size() > idx + 1) {
				parserPage(tuInfo, this.doGet5(tuInfo.getUrl()),
						zhuatuServices.get(idx + 1), idx + 1,
						new LinkedList<String>());// 进行下一层任务
			}
		}

		String nextUrl = zhuatuService.nextPage(html);
		if (StringUtils.isNotEmpty(nextUrl)) {
			MTuInfo tuInfo = new MTuInfo();
			tuInfo.setUrl(nextUrl);
			tuInfo.setTitle(parentInfo.getTitle());
			parserPage(tuInfo, this.doGet5(tuInfo.getUrl()),
					zhuatuServices.get(idx), idx, downloadURL);// 下一页
		}
	}

	private String doGet5(String url) {
		String result = null;
		boolean flag = true;
		int i = 1;
		  do {
		    try {
	      result = MZhuatuHttpUtil.doGet(url,defaultCharset);
		    } catch (Exception e) {
		      logger.error("未能正常访问url：{}",url,e);
		    }
	      if (StringUtils.isNotEmpty(result)) {
	        flag = false;
	      } else {
	        if (i > 5) {
	          logger.error("解析失败" + url + "\n");
	          flag = false;
	        } else {
	          logger.error("解析失败，开始重试第" + i + "次：" + url);
	          i++;
	          try {
	            Thread.sleep(1000 * 3);
	          } catch (InterruptedException e1) {
	          }
	        }
	      }

	    } while (flag);
    
		return result;
	}
	

}
