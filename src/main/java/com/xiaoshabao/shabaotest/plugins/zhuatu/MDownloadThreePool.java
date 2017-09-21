package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 下载线程池(只做展示)
 */
public class MDownloadThreePool {
	private static Logger logger = LoggerFactory
			.getLogger(DownloadTuTask.class);
	
	private ExecutorService executor;
	
	public MDownloadThreePool(){
		init();
	}
	
	public void init(){
		executor=Executors.newFixedThreadPool(5);    
	}
	
	public void execute(List<MTuInfo> downloads){
		
		if(downloads==null){
			return;
		}
		for(MTuInfo info:downloads){
			
			DownloadTask myTask = new DownloadTask(info.getUrl(),info.getFileNamePath());
            executor.execute(myTask);
        }
        executor.shutdown();
		
	}
	

	class DownloadTask implements Runnable {

		private String url;
		private String fileNamePath;

		/**
		 * 下载文件
		 * 
		 * @param url
		 *            http://www.99pic.org/uploadfile/2017/0915/07/01.jpg
		 * @param fileNamePath
		 *            E:\\test\\gm\\01.jpg
		 */
		public DownloadTask(String url, String fileNamePath) {
			this.url = url;
			this.fileNamePath = fileNamePath;
		}

		@Override
		public void run() {
			HttpGet httpGet = new HttpGet(url);// 创建get请求
			CloseableHttpClient httpClient = null;
			CloseableHttpResponse response = null;
			HttpEntity entity = null;
			try {
				httpClient = HttpClients.createDefault();
				response = httpClient.execute(httpGet);
				entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					byte[] image = IOUtils.toByteArray(instream);
					FileUtils.writeByteArrayToFile(new File(fileNamePath),
							image);
					IOUtils.closeQuietly(instream);
				}
			} catch (Exception e) {
				logger.error("文件下载失败:" + url, e);
			} finally {
				try {
					if (response != null) {
						response.close();
					}
					if (httpClient != null) {
						httpClient.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			logger.error("下载成功:" + url);
		}
	}
}
