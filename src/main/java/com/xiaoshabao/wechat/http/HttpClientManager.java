package com.xiaoshabao.wechat.http;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClient工具类
 * <p>
 * 使用单例模式<br/>
 * 统一封装了get和post的 HTTP 和 HTTPS 方法
 * </p>
 */
public class HttpClientManager {
	private static Logger logger = LoggerFactory
			.getLogger(HttpClientManager.class);

	// 实现单例模式
	private static HttpClientManager instance = null;

	private HttpClientManager() {

	}

	public static HttpClientManager getInstance() {
		if (instance == null) {
			instance = new HttpClientManager();
		}
		return instance;
	}

	/**
	 * 创建SSL安全连接
	 *
	 * @return
	 */
	private static SSLConnectionSocketFactory createSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null,
					new TrustManager[] { new MyX509TrustManager() }, null);
			sslsf = new SSLConnectionSocketFactory(sslContext,
					NoopHostnameVerifier.INSTANCE);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			logger.info("https连接时，创建ssl连接失败！", e);
		}
		return sslsf;
	}

	/**
	 * 发送get请求
	 * <p>
	 * 一般通过本方法进行URL访问,URL要以http://开头
	 * <p/>
	 * 
	 * @param url
	 *            要访问的URL,比如"http://www.baidu.com"
	 * @return String 返回字符串，可转换成JSON
	 */
	public String doGet(String url) {
		return doGet(new HttpGet(url));
	}

	/**
	 * 发送get请求
	 * <p>
	 * 一般用{@link #sendHttpGet(String) sendHttpGet} 这个传入方式发起get请求<br/>
	 * 这个方法被调用
	 * </p>
	 * 
	 * @param httpGet
	 * @return String 返回字符串，可转换成JSON
	 */
	public String doGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");// 获得响应内容
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("HttpClientUtil 发送http get请求未能正常建立连接或者访问资源！！", e);
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("HttpClientUtil 发送http get请求时资源未能正常关闭！！", e);
			}
		}
		return responseContent;

	}

	/**
	 * 发送 get请求Https
	 * 
	 * @param httpUrl
	 *            要访问的URL,比如"https://www.baidu.com"
	 */
	public String doGetSSL(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return doGetSSL(httpGet);
	}

	/**
	 * 发送Get请求Https
	 * 
	 * @param httpGet
	 * @return String
	 */
	private String doGetSSL(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader
					.load(new URL(httpGet.getURI().toString()));
			DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(
					publicSuffixMatcher);
			httpClient = HttpClients.custom()
					.setSSLHostnameVerifier(hostnameVerifier).build();
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
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
		return responseContent;
	}

	/**
	 * 发送post请求
	 * <p>
	 * 一般通过本方法进行URL访问
	 * <p/>
	 * 
	 * @param url
	 *            要访问的URL,比如"http://www.baidu.com"
	 * @return String 返回字符串，可转换成JSON
	 */
	public String doPost(String url) {
		return doPost(new HttpPost(url));
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            要访问的URL,比如"http://www.baidu.com"
	 * @param params
	 *            参数(格式:key1=value1&key2=value2)
	 */
	public String doPost(String httpUrl, String params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            要访问的URL,比如"http://www.baidu.com"
	 * @param maps
	 *            参数
	 */
	public String doPost(String httpUrl, Map<String, String> maps) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doPost(httpPost);
	}

	/**
	 * 发送 post请求（带文件）
	 * 
	 * @param httpUrl
	 *            要访问的URL,比如"http://www.baidu.com"
	 * @param maps
	 *            参数
	 * @param fileLists
	 *            附件
	 */
	public String doPost(String httpUrl, Map<String, String> maps,
			List<File> fileLists) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key),
					ContentType.TEXT_PLAIN));
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("files", fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return doPost(httpPost);
	}

	/**
	 * 发送post请求
	 * <p>
	 * 一般用{@link #sendHttpPost(String) sendHttpPost} 这个传入方式发起get请求<br/>
	 * 这个方法被调用
	 * </p>
	 * 
	 * @param httpGet
	 * @return String 返回字符串，可转换成JSON
	 */
	public String doPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");// 获得响应内容
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("HttpClientUtil 发送http get请求未能正常建立连接或者访问资源！！");
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("HttpClientUtil 发送http get请求时资源未能正常关闭！！");
			}
		}
		return responseContent;

	}

	/**
	 * 发送post的https请求
	 * 
	 * @param url
	 *            要访问的URL,比如"https://www.baidu.com"
	 * @return
	 */
	public String doPostSSL(String url) {
		return doPost(new HttpPost(url));
	}

	/**
	 * 发送post的https请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求带的参数
	 * @return
	 */
	public String doPostSSL(String url, Map<String, String> params) {
		HttpPost httpPost = new HttpPost(url);
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : params.keySet()) {
			meBuilder.addPart(key, new StringBody(params.get(key),
					ContentType.TEXT_PLAIN));
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return doPostSSL(httpPost);
	}

	/**
	 * 发送post 的https请求
	 * 
	 * @param httpPost
	 * @return
	 */
	public String doPostSSL(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String responseContent = null;

		try {
			httpClient = HttpClients.custom()
					.setSSLSocketFactory(createSocketFactory()).build();

			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			responseContent = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseContent;
	}

}
