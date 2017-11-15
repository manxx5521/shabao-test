package com.xiaoshabao.shabaotest.plugins.mzhuatu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadTuTask  implements Runnable{
	
	private static Logger logger = LoggerFactory.getLogger(DownloadTuTask.class);
	
	private String url;
	private String fileNamePath;
	private String charset;
	/**
	 * 下载文件
	 * 
	 * @param url
	 *            http://www.***.org/uploadfile/2017/0915/07/01.jpg
	 * @param fileNamePath
	 *            E:\\test\\gm\\01.jpg
	 */
	public DownloadTuTask(String url, String fileNamePath,String charset) {
		this.url = url;
		this.fileNamePath = fileNamePath;
		this.charset=charset;
	}

	@Override
	public void run() {
		ZhuatuHttpManager.getInstance(charset).download5(url,fileNamePath);
		boolean flag=true;
		int i=1;
		do{
			try {
				ZhuatuHttpManager.getInstance(charset).download5(url,fileNamePath);
				flag=false;
				logger.info("下载成功:" + url);
			} catch (Exception e) {
				if(i>5){
					logger.error("下载失败"+url+"\n预期目录："+fileNamePath,e);
					flag=false;
				}else{
					logger.error("下载失败，开始重试第"+i+"次："+url);
					i++;
					try {
						Thread.sleep(1000*3);
					} catch (InterruptedException e1) {
					}
				}
				
			}
		}while(flag);
	}
	


}
