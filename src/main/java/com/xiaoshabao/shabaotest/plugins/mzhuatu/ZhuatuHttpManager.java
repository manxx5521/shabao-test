package com.xiaoshabao.shabaotest.plugins.mzhuatu;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP管理
 */
public class ZhuatuHttpManager {
	protected Logger log = LoggerFactory.getLogger(getClass());
	private volatile static ZhuatuHttpManager instance = null;

	private ZhuatuHttpManager() {

	}

	public static ZhuatuHttpManager getInstance() {
		if (instance == null) {
			synchronized (ZhuatuHttpManager.class) {
				if (instance == null) {
					new ZhuatuHttpManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 默认自动请求5次
	 * 
	 * @return 返回响应内容
	 */
	public String doGetAuto5(String url) {
		return new RetryFactory<String, String>(url, "访问URL").execute(tempUrl -> {
			return this.doGetAuto(tempUrl);
		});
	}

	private String doGetAuto(String url) {
		try {
			return this.doGet(url);
		} catch (Exception e) {
			log.error("请求失败异常终止,url为{}", url, e);
		}
		return null;
	}

	public void download5(String url, String pathName) {
		Boolean result = new RetryFactory<DownloadInfo, Boolean>(new DownloadInfo(url, pathName), "访问URL")
				.execute(info -> {
					try {
						this.download(info.url, info.pathName);
					} catch (IOException e) {
						log.warn("下载失败开始重试，重试第{}次，url为{}", url);
					}
					return Boolean.FALSE;
				});
		if (!result) {
			log.error("下载文件失败，目录{}\n\t url为{}", pathName, url);
		}

	}

	class DownloadInfo {
		String url;
		String pathName;

		public DownloadInfo(String url, String pathName) {
			this.url = url;
			this.pathName = pathName;
		}
	}

	private void download(String url, String pathName) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);// 创建get请求
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		// 自定义超时时间等
		RequestConfig requestConfig = RequestConfig.custom()

				.setSocketTimeout(1000 * 60 * 15) // socket超时
				.setConnectTimeout(5000) // connect超时
				.build();
		httpGet.setConfig(requestConfig);

		httpGet.setHeader("Accept", "text/html, */*; q=0.01");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate,sdch");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36)");

		try {
			httpClient = HttpClients.createDefault();

			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				byte[] image = IOUtils.toByteArray(instream);
				FileUtils.writeByteArrayToFile(new File(pathName), image);
				IOUtils.closeQuietly(instream);
			}
			/*
			 * } catch (Exception e) { logger.error("文件下载失败:" + url, e);
			 */
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		}
	}

	private String doGet(String url) throws ClientProtocolException, IOException {
		return doGet(url, "UTF-8");
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @param charset
	 * @throws ClientProtocolException
	 *             发送http get请求未能正常建立连接或者访问资源
	 * @throws IOException
	 *             发送http get请求时资源未能正常关闭！！
	 */
	public static String doGet(String url, String charset) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;

		httpGet.setHeader("Accept", "text/html, */*; q=0.01");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate,sdch");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36)");

		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, charset);// 获得响应内容
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		}
		return responseContent;

	}

}
