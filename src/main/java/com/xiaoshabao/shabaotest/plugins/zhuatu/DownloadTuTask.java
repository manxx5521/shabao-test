package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadTuTask  implements Runnable{
	
	private static Logger logger = LoggerFactory.getLogger(DownloadTuTask.class);
	
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
	public DownloadTuTask(String url, String fileNamePath) {
		this.url = url;
		this.fileNamePath = fileNamePath;
	}

	@Override
	public void run() {
		boolean flag=true;
		int i=1;
		do{
			try {
				this.download();
				flag=false;
				logger.info("下载成功:" + url);
			} catch (Exception e) {
				if(i>5){
					logger.error("下载失败"+url+"\n预期目录："+fileNamePath);
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
	
	public void download() throws ClientProtocolException, IOException{
		HttpGet httpGet = new HttpGet(url);// 创建get请求
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			httpClient = HttpClients.createDefault();
			/*自定义超时时间等
			RequestConfig requestConfig = RequestConfig.custom()
					.setCookieSpec("easy").setSocketTimeout(5000) // socket超时
					.setConnectTimeout(5000) // connect超时
					.build();
			httpGet.setConfig(requestConfig);*/
			
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				byte[] image = IOUtils.toByteArray(instream);
				FileUtils.writeByteArrayToFile(new File(fileNamePath),
						image);
				IOUtils.closeQuietly(instream);
			}
		/*} catch (Exception e) {
			logger.error("文件下载失败:" + url, e);*/
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
	}

}
