package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.io.IOException;

import javax.servlet.http.Cookie;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.impl.cookie.DefaultCookieSpec;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeiBoZhutu {
	private static Logger logger = LoggerFactory.getLogger(WeiBoZhutu.class);

	@Test
	public void test() {
		String url = "http://weibo.com/p/1005052930285990/photos?from=page_100505&mod=TAB#place";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			// httpClient = HttpClients.createDefault();

			// 采用用户自定义的cookie策略
			// HttpHost proxy = new HttpHost(hostName, port);
			// DefaultProxyRoutePlanner routePlanner = new
			// DefaultProxyRoutePlanner(proxy);
			CookieSpecProvider cookieSpecProvider = new CookieSpecProvider() {
				@Override
				public CookieSpec create(HttpContext context) {
					return new DefaultCookieSpec() {
						@Override
						public void validate(
								org.apache.http.cookie.Cookie cookie,
								CookieOrigin origin)
								throws MalformedCookieException {
							// super.validate(cookie, origin);
						}

					};
				}
			};
			Registry<CookieSpecProvider> r = RegistryBuilder
					.<CookieSpecProvider> create()
					.register(CookieSpecs.BEST_MATCH,
							new BestMatchSpecFactory())
					.register(CookieSpecs.BROWSER_COMPATIBILITY,
							new BrowserCompatSpecFactory())
					.register("easy", cookieSpecProvider).build();
			httpClient = HttpClients.custom().setDefaultCookieSpecRegistry(r)
			// .setRoutePlanner(routePlanner)
					.build();
			RequestConfig requestConfig = RequestConfig.custom()
					.setCookieSpec("easy").setSocketTimeout(5000) // socket超时
					.setConnectTimeout(5000) // connect超时
					.build();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity,
					MZhuatu.DEFAULT_CHARSET);// 获得响应内容
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

		System.out.println(responseContent);
	}

}
