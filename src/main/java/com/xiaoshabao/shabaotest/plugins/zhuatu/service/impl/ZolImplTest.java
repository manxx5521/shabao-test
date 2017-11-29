package com.xiaoshabao.shabaotest.plugins.zhuatu.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.xiaoshabao.shabaotest.plugins.zhuatu.MTuInfo;
import com.xiaoshabao.shabaotest.plugins.zhuatu.ZhuatuConfig;
import com.xiaoshabao.shabaotest.plugins.zhuatu.core.ZhuatuFactory;
import com.xiaoshabao.shabaotest.plugins.zhuatu.http.ZhuatuHttpUnitManager;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.ZhuatuDownloadService;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.ZhuatuService;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.ZhuatuWaitService;

/**
 * 中关村在线抓取（使用jsoup，暂时只能下载两张htmlunit无法多次加载）
 */
public class ZolImplTest {

	private final static Logger log = LoggerFactory.getLogger(ZolImplTest.class);

	private String indexUrl = "http://bbs.zol.com.cn/dcbbs/d14_pic.html#c";
	private String urlRoot = "http://bbs.zol.com.cn";

	@Test
	public void test() {

		// 第一任务 界面解析任务
		List<ZhuatuService> zhuatuServices = new ArrayList<ZhuatuService>();
		// 第一层解析分项的信息，找打具体的项目
		zhuatuServices.add(new ZhuatuWaitService() {

			@Override
			public List<MTuInfo> parser(String html, MTuInfo pageInfo, ZhuatuConfig config) throws ParserException {
				List<MTuInfo> result = new LinkedList<MTuInfo>();
				Document doc = Jsoup.parse(html);
				Elements divs = doc.select("div.pic-infor");
				for (Element div : divs) {
					Elements links = div.select("a.listbook");
					if (links.size() > 0) {
						Element link = links.get(0);
						String href = link.attr("href");
						String title = link.text();
						MTuInfo info = new MTuInfo();
						info.setUrl(urlRoot + href);
						info.setTitle(title);
						result.add(info);
					}
				}
				return result;
			}

			@Override
			public String nextPage(String html, ZhuatuConfig config) {
				Document doc = Jsoup.parse(html);
				Elements links = doc.select("a.loadding-more");
				if (links.size() > 0) {
					Element link = links.get(0);
					String href = link.attr("href");
					return href;
				}
				return null;
			}
		});
		// 第二层解析具体照片
		zhuatuServices.add(new ZhuatuDownloadService() {

			@Override
			public List<MTuInfo> parser(String html, MTuInfo pageInfo, ZhuatuConfig config) throws Exception {
				List<MTuInfo> result = new ArrayList<MTuInfo>();

				Document doc = Jsoup.parse(html);
				Elements links = doc.select("ul#change > li");

				HtmlPage page = ZhuatuHttpUnitManager.getInstance().getPage(pageInfo.getUrl());
				for (int i = 0; i < links.size(); i++) {
					HtmlImage img = (HtmlImage) page.getElementById("bigPicHome");
					String href = img.getSrcAttribute();
					String title = img.getAttribute("alt");
					MTuInfo info = new MTuInfo();
					info.setUrl(href);
					info.setTitle(title);
					result.add(info);
					log.info("取到下载链接:{}", info.getUrl());

					// 不是最后一次点击下一页
					if (i < links.size() - 1) {
						page.getElementById("nextBtn").click();
					}
				}
				return result;
			}

			@Override
			public String nextPage(String html, ZhuatuConfig config) {
				return null;
			}
		});
		ZhuatuFactory.createDownloadZhuatu().start(indexUrl, zhuatuServices, "E:\\test\\test\\zol", "gb2312");
	}

}
