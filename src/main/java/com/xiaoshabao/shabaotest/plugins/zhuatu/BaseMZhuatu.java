package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抓图工具
 */
public abstract class BaseMZhuatu {

	private final static Logger logger = LoggerFactory
			.getLogger(BaseMZhuatu.class);

	protected String defaultCharset = "GBK";

	protected String projectName;

	protected String urlRoot;

	protected String savePath;

	protected List<String> projects = new LinkedList<String>();

	/**
	 * 下载线程池
	 */
	private ThreadPoolExecutor executor;

	public void start(String url, String savePath, String charset) {
		this.savePath = savePath;
		this.defaultCharset = "charset";
		try {
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
			executor = new ThreadPoolExecutor(10, 10, 0L,
					TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

			URL urlEntity = new URL(url);
			urlRoot = urlEntity.getProtocol() + "://" + urlEntity.getHost();

			parserPage(url);
		} catch (MalformedURLException e1) {
			logger.error("url初始化时解析错误：{}", url, e1);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void parserPage(String url) {
		try {
			Parser parser = Parser.createParser(this.doGet5(url),
					defaultCharset);
			NodeList list = parser.parse(new HasAttributeFilter("class",
					"ulPic"));
			Node body = list.elementAt(0);
			body.accept(new NodeVisitor() {
				@Override
				public void visitTag(Tag tag) {

					if (tag instanceof LinkTag) {
						LinkTag link = (LinkTag) tag;
						String href = link.getLink();
						String title = link.getAttribute("title");
						// logger.debug("取到详情链接：" + href + "----取到标题" + title);
						if (projects.contains(title)) {
							logger.info("未下载" + title + "（已经存在）");
							return;
						}

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

						logger.info("开始解析：" + title + "-----------------------");
						List<String> downloads = new LinkedList<String>();
						List<String> pages = new LinkedList<String>();
						try {
							projects.add(title);
							opDetail(href, title, downloads, pages);
						} catch (Exception e) {
							File file = new File(savePath + File.separator
									+ projectName);
							if (file.isDirectory()) {
								file.delete();
							}
							logger.error("解析失败：" + title
									+ "**********************************");
						}
						logger.info("完成解析" + title + "-----------------------");
					}
				}
			});
			executor.shutdown();// 最后要等带下载完关闭主进程
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract void parserPageDetailUrl();

	/**
	 * 打开详情界面
	 * 
	 * @throws ParserException
	 */
	public void opDetail(String href, final String title,
			List<String> downloads, List<String> pages) throws ParserException {

		String html = this.doGet5(urlRoot + href);
		Parser parser = Parser.createParser(html, defaultCharset);

		NodeList imgs = parser.parse(new TagNameFilter("img"));
		for (Node node : imgs.toNodeArray()) {
			ImageTag img = (ImageTag) node;
			String src = img.getAttribute("src");
			String alt = img.getAttribute("alt");
			if (alt == null || src == null) {
				continue;
			}
			if (!parserTitleName(title).equals(parserTitleName(alt))
					|| downloads.contains(src)) {
				continue;
			}
			logger.info("取到下载链接：" + src);
			if (src.endsWith("/")) {
				throw new RuntimeException("获得的图片下载链接错误");
			}
			String fileName = src.substring(src.lastIndexOf("/") + 1,
					src.length());
			DownloadTuTask myTask = new DownloadTuTask(src, savePath
					+ File.separator + title + File.separator + fileName);
			executor.execute(myTask);

			downloads.add(src);
		}

		pages.add(href);

		parser = Parser.createParser(html, defaultCharset);
		NodeList nexts = parser.parse(new HasAttributeFilter("class", "a1"));
		for (Node node : nexts.toNodeArray()) {
			LinkTag link = (LinkTag) node;
			String nextUrl = link.getLink();
			if (!pages.contains(nextUrl)) {
				opDetail(nextUrl, title, downloads, pages);
			}
		}

	}

	/**
	 * 去除可能存在的特殊字符(目前只做等于配置使用)
	 * 
	 * @return
	 */
	protected String parserTitleName(String title) {
		return title.trim().replace("amp;", "");
	}

	private String doGet5(String url) {
		String result = null;
		boolean flag = true;
		int i = 1;
		do {
			result = this.doGet(url);
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

	private String doGet(String url) {
		HttpGet httpGet = new HttpGet(url);
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
			responseContent = EntityUtils.toString(entity, defaultCharset);// 获得响应内容
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("HttpClientUtil 发送http get请求未能正常建立连接或者访问资源！！\n访问url："
					+ url, e);
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

}
