package com.xiaoshabao.wechat.api.core.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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

import com.xiaoshabao.wechat.api.core.util.WeiXinReqUtil;

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

	public static synchronized HttpClientManager getInstance() {
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
	 * 发送 get请求Https
	 * 
	 * @param httpUrl
	 *            要访问的URL,比如"https://www.baidu.com"
	 * @param jsonData
	 *            参数是key1=value1,key2=value2。value为String类型
	 */
	public String doGetSSL(String httpUrl, Map<String, Object> maps) {
		StringBuffer params = new StringBuffer();
		params.append(httpUrl);
		for (String key : maps.keySet()) {
			params.append("&");
			params.append(key);
			params.append("=");
			params.append(maps.get(key));
		}
		HttpGet httpGet = new HttpGet(params.toString());// 创建get请求
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
	 * 通过get的HTTPS形式下载文件
	 */
	public String getFileGetSSL(String url, Map<String ,Object> params,String path) {
		return getFileGetSSL(url, params,path,null);
	}
	
	/**
	 * 通过get的HTTPS形式下载文件
	 * @param url
	 * @param params
	 * @param path
	 * @param fileName
	 * @return
	 */
	public String getFileGetSSL(String url, Map<String ,Object> params,String path,String fileName) {
		StringBuffer maps = new StringBuffer();
		maps.append(url);
		for (String key : params.keySet()) {
			maps.append("&");
			maps.append(key);
			maps.append("=");
			maps.append(params.get(key));
		} 
		//解决SSLProtocolException: handshake alert: unrecognized_name异常
		System.setProperty("jsse.enableSNIExtension", "false");
		HttpGet httpGet = new HttpGet(maps.toString());// 创建get请求
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			httpClient = HttpClients.custom().setSSLHostnameVerifier(new CustomizedHostnameVerifier()).build();
			
			// 执行请求
			response = httpClient.execute(httpGet);
			// 获得头文件中的文件名
			if(StringUtils.isEmpty(fileName)){
				fileName = getDisposition(response,"filename");
			}
			String f=File.separator;
			if (!path.endsWith(f)) {
				path = path + f;
			}
			File storeFile = new File(path + fileName);
			FileOutputStream output = new FileOutputStream(storeFile);
			// 得到网络资源的字节数组,并写入文件
			entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					byte b[] = new byte[1024];
					int j = 0;
					while ((j = instream.read(b)) != -1) {
						output.write(b, 0, j);
					}
					output.flush();
					output.close();
				} catch (IOException ex) {
					throw ex;
				} catch (RuntimeException ex) {
					httpGet.abort();
					throw ex;
				} finally {
					try {
						instream.close();
					} catch (Exception ignore) {
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
		return fileName;
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
	public String doPost(String httpUrl, Map<String, Object> maps,
			List<File> fileLists) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key).toString(),
					ContentType.TEXT_PLAIN));
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart("files", fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		httpPost.setHeader("Connection", "Keep-Alive");
		httpPost.setHeader("Charset", "UTF-8");
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
		return doPostSSL(new HttpPost(url));
	}

	/**
	 * 发送 post https请求
	 * 
	 * @param httpUrl
	 *            要访问的URL,比如"https://www.baidu.com"
	 * @param params
	 *            参数(格式:key1=value1&key2=value2)
	 */
	public String doPostSSL(String httpUrl, String params) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doPostSSL(httpPost);
	}

	/**
	 * 发送 post HTTPS请求,参数为JSON形式
	 * 
	 * @param httpUrl
	 *            要访问的URL,比如"http://www.baidu.com"
	 * @param accessToken token,解决JSON传递token失效
	 * @param jsonData
	 *            参数为JSON像是{k1:value1,k2:value2}
	 */
	public String doPostSSLByJSON(String httpUrl, String jsonData) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(jsonData,"utf-8");
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doPostSSL(httpPost);
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
	public String doPostSSL(String url, Map<String, Object> params) {
		HttpPost httpPost = new HttpPost(url);
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : params.keySet()) {
			meBuilder.addPart(key, new StringBody(params.get(key).toString(),
					ContentType.TEXT_PLAIN));
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return doPostSSL(httpPost);
	}
	
	/**
	 * 发送 post请求（带文件）HTTPS请求
	 * 
	 * @param httpUrl
	 *            要访问的URL,比如"https://www.baidu.com"
	 * @param maps
	 *            参数
	 * @param fileLists
	 *            附件
	 */
	public String doPostSSL(String httpUrl, Map<String, Object> maps,
			List<File> fileLists) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		System.setProperty("jsse.enableSNIExtension", "false");//解决SSLProtocolException: handshake alert: unrecognized_name异常
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		
		for (String key : maps.keySet()) {
			meBuilder.addPart(key, new StringBody(maps.get(key).toString(),
					ContentType.TEXT_PLAIN));
//					ContentType.create("text/plain", "utf-8")));//此方式可以指定编码
		}
		for (File file : fileLists) {
			String content =WeiXinReqUtil.getFileContentType(file.getName().substring(file.getName().lastIndexOf(".") + 1));
			FileBody fileBody = new FileBody(file,ContentType.create(content, "utf-8"));
			meBuilder.addPart("media", fileBody);//名字设置成media要不上传永久素材报错
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
	
	/**
	 * 通过post的HTTPS形式下载文件
	 */
	public String getFilePostSSL(String url, Map<String ,Object> params,String path) {
		return getFilePostSSL(url, params,path,null);
	}
	
	/**
	 * 通过post的HTTPS形式下载文件
	 * @param url
	 * @param params
	 * @param path
	 * @param fileName
	 * @return
	 */
	public String getFilePostSSL(String url, Map<String ,Object> params,String path,String fileName) {
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		System.setProperty("jsse.enableSNIExtension", "false");//解决SSLProtocolException: handshake alert: unrecognized_name异常
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		for (String key : params.keySet()) {
			meBuilder.addPart(key, new StringBody(params.get(key).toString(),
					ContentType.TEXT_PLAIN));
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			httpClient = HttpClients.custom().setSSLHostnameVerifier(new CustomizedHostnameVerifier()).build();
			
			// 执行请求
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			
			String contentType=response.getFirstHeader("Content-Type").getValue();
			if(contentType.equals("text/plain")){
				logger.debug(EntityUtils.toString(entity, "utf-8"));
				return EntityUtils.toString(entity, "utf-8");
			}
			
			// 获得头文件中的文件名
			if(StringUtils.isEmpty(fileName)){
				fileName = getDisposition(response,"filename");
			}
			if(StringUtils.isEmpty(fileName)){
				fileName = "file"+System.currentTimeMillis()+".jpg";
			}
			String f=File.separator;
			if (!path.endsWith(f)) {
				path = path + f;
			}
			File storeFile = new File(path + fileName);
			FileOutputStream output = new FileOutputStream(storeFile);
			// 得到网络资源的字节数组,并写入文件

			InputStream instream = entity.getContent();
			try {
				byte b[] = new byte[1024];
				int j = 0;
				while ((j = instream.read(b)) != -1) {
					output.write(b, 0, j);
				}
				output.flush();
				output.close();
			} catch (IOException ex) {
				throw ex;
			} catch (RuntimeException ex) {
				httpPost.abort();
				throw ex;
			} finally {
				try {
					instream.close();
				} catch (Exception ignore) {
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
		return fileName;
	}
	
	/**
	 * 获取response header中Content-Disposition中的某个参数的值
	 * 
	 * @param response
	 * @return
	 */
	public static String getDisposition(HttpResponse response,String paramName) {
		Header contentHeader = response.getFirstHeader("Content-Disposition");
		String filename = null;
		if (contentHeader != null) {
			HeaderElement[] values = contentHeader.getElements();
			if (values.length == 1) {
				NameValuePair param = values[0].getParameterByName(paramName);
				if (param != null) {
					try {
						filename = param.getValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return filename;
	}
	

}
