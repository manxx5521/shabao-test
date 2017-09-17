package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抓图工具
 */
public class MZhuatu {

	private static Logger logger = LoggerFactory.getLogger(MZhuatu.class);

	protected final static String DEFAULT_CHARSET = "GBK";

	protected String projectName;

	protected String urlRoot;

	protected String savePath;

	protected List<String> projects = new LinkedList<String>();

	@Test
	public void test() {
		this.projectName = "gm";
		savePath = "E:\\test\\shabao-m\\resources\\plugins\\mm\\99renti";
		start("http://www.99renti.wang/html/guomosipai/");
	}

	public void start(String url) {
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

			URL urlEntity = new URL(url);
			urlRoot = urlEntity.getProtocol() + "://" + urlEntity.getHost();

			Parser parser = Parser.createParser(this.doGet(url),
					MZhuatu.DEFAULT_CHARSET);
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
							logger.debug("未下载" + title + "（已经存在）");
							return;
						}

						logger.info("开始解析：" + title + "-----------------------");
						List<MTuInfo> downloads = new LinkedList<MTuInfo>();
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
							logger.debug("下载失败：" + title
									+ "**********************************");
						}
						if (downloads.size() > 0) {
							MDownloadThreePool pool = new MDownloadThreePool();
							pool.execute(downloads);
							logger.debug("完成下载" + title
									+ "-----------------------");
						}
					}
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 打开详情界面
	 * 
	 * @throws ParserException
	 */
	public void opDetail(String href, final String title,
			List<MTuInfo> downloads, List<String> pages) throws ParserException {

		String html = this.doGet(urlRoot + href);
		Parser parser = Parser.createParser(html, MZhuatu.DEFAULT_CHARSET);

		NodeList imgs = parser.parse(new TagNameFilter("img"));
		for (Node node : imgs.toNodeArray()) {
			ImageTag img = (ImageTag) node;
			String src = img.getAttribute("src");
			String alt = img.getAttribute("alt");
			if (!title.equals(alt) || downloads.contains(src)) {
				continue;
			}
			logger.debug("取到下载链接：" + src);
			if (src.endsWith("/")) {
				throw new RuntimeException("获得的图片下载链接错误");
			}
			String fileName = src.substring(src.lastIndexOf("/") + 1,
					src.length());
			// getFile(src, savePath + File.separator + title + File.separator+
			// fileName);
			downloads.add(new MTuInfo(src, savePath + File.separator + title
					+ File.separator + fileName));
		}

		pages.add(href);

		parser = Parser.createParser(html, MZhuatu.DEFAULT_CHARSET);
		NodeList nexts = parser.parse(new HasAttributeFilter("class", "a1"));
		for (Node node : nexts.toNodeArray()) {
			LinkTag link = (LinkTag) node;
			String nextUrl = link.getLink();
			if (!pages.contains(nextUrl)) {
				opDetail(nextUrl, title, downloads, pages);
			}
		}

	}

	public String doGet(String url) {
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
		return responseContent;

	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            http://www.99pic.org/uploadfile/2017/0915/07/01.jpg
	 * @param fileNamePath
	 *            E:\\test\\gm\\01.jpg
	 * @return
	 *//*
	public String getFile(String url, String fileNamePath) {
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
				FileUtils.writeByteArrayToFile(new File(fileNamePath), image);
				IOUtils.closeQuietly(instream);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
		return fileNamePath;
	}*/

}
