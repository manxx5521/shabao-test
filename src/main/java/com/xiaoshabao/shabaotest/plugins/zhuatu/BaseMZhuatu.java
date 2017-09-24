package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.shabaotest.plugins.zhuatu.service.MZhuatuDownloadService;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.MZhuatuService;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.MZhuatuWaitService;

/**
 * 抓图工具
 */
public class BaseMZhuatu {

	protected final static Logger logger = LoggerFactory
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
		logger.info("开始抓取：{}",url);
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
		parserPage(info, zhuatuServices.get(0), 0,
				true);
		executor.shutdown();

	}

	/**
	 * 解析页面
	 * @param pageInfo
	 * @param zhuatuService
	 * @param idx 当前层次
	 * @param newProject 是否是新项目（如果false表示是下一页解析）
	 */
	protected void parserPage(MTuInfo pageInfo, 
			MZhuatuService zhuatuService, int idx,boolean newProject) {
		String html=this.doGet5(pageInfo.getUrl());
		List<MTuInfo> list = zhuatuService.parser(html, pageInfo,
				projects, newProject);

		Iterator<MTuInfo> iterator=list.iterator();
		while(iterator.hasNext()){//链表用迭代器
			MTuInfo tuInfo=iterator.next();
			// 需要等待相同内容连接池
			if (zhuatuService instanceof MZhuatuWaitService) {
				// 检查任务是否过多
				boolean flag = true;
				while (flag) {
					if (executor.getActiveCount() < 5) {
						flag = false;
						logger.info("开始下载项目:{}  ######################",tuInfo.getTitle());
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
				download(tuInfo);
			}

			if (zhuatuServices.size() > idx + 1) {
				parserPageNextIdx(tuInfo, zhuatuServices.get(idx + 1), idx + 1);// 进行下一层任务
			}
		}

		String nextUrl = zhuatuService.nextPage(html);
		if (StringUtils.isNotEmpty(nextUrl)) {
			MTuInfo tuInfo = new MTuInfo();
			tuInfo.setUrl(nextUrl);
			tuInfo.setTitle(pageInfo.getTitle());
			parserPage(tuInfo, zhuatuServices.get(idx), idx, false);// 下一页
		}
		
	}
	/**
	 * 进行下一层任务
	 */
	protected void parserPageNextIdx(MTuInfo pageInfo, 
			MZhuatuService zhuatuService, int idx) {
		parserPage(pageInfo, zhuatuService, idx,true);
	}
	protected void download(MTuInfo tuInfo){
		String fileName = tuInfo.getUrl().substring(
				tuInfo.getUrl().lastIndexOf("/") + 1,
				tuInfo.getUrl().length());
		DownloadTuTask myTask = new DownloadTuTask(tuInfo.getUrl(),
				savePath + File.separator + tuInfo.getTitle()
						+ File.separator + fileName);
		executor.execute(myTask);
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
