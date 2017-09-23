package com.xiaoshabao.shabaotest.plugins.zhuatu.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.NodeVisitor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.shabaotest.plugins.zhuatu.BaseMZhuatu;
import com.xiaoshabao.shabaotest.plugins.zhuatu.MTuInfo;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.MZhuatuDownloadService;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.MZhuatuService;
import com.xiaoshabao.shabaotest.plugins.zhuatu.service.MZhuatuWaitService;

public class ZhuTuGmtt8 {

	private final static Logger logger = LoggerFactory
			.getLogger(ZhuTuGmtt8.class);

	private String defaultCharset = "UTF-8";

	List<String> pages = new LinkedList<String>();
	
	protected String urlRoot="http://www.gmtt8.com";

	@Test
	public void test() {
		List<MZhuatuService> zhuatuServices = new ArrayList<MZhuatuService>();
		// 第一层解析分项的信息，找打具体的项目
		zhuatuServices.add(new MZhuatuWaitService() {
			@Override
			public List<MTuInfo> parser(String html, MTuInfo pageInfo,
					final List<String> projects,final List<String> downloadURL) {
				if (!pages.contains(pageInfo.getUrl())) {
					pages.add(pageInfo.getUrl());
				}
				final List<MTuInfo> result = new LinkedList<MTuInfo>();
				try {
					Parser parser = Parser.createParser(html, defaultCharset);
					NodeList list = parser.parse(new HasAttributeFilter(
							"class", "ulPic"));
					Node body = list.elementAt(0);
					body.accept(new NodeVisitor() {
						@Override
						public void visitTag(Tag tag) {
							if (tag instanceof LinkTag) {
								LinkTag link = (LinkTag) tag;
								String href = link.getLink();
								String title = link.getAttribute("title");
								if (projects.contains(title)
										|| downloadURL.contains(href)) {
									logger.info("未下载" + title + "（已经存在）");
									return;
								}
								logger.info("准备下载：" + title);
								projects.add(title);

								MTuInfo info = new MTuInfo();
								info.setUrl(urlRoot+href);
								info.setTitle(title);
								result.add(info);
								downloadURL.add(href);
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			@Override
			public String nextPage(String html) {
				try {
					Parser parser = Parser.createParser(html,
							defaultCharset);
					NodeList nexts = parser.parse(new HasAttributeFilter(
							"class", "a1"));
					for (Node node : nexts.toNodeArray()) {
						LinkTag link = (LinkTag) node;
						String nextUrl = link.getLink();
						if(StringUtils.isNotEmpty(nextUrl)){
							nextUrl=urlRoot+nextUrl;
							if (!pages.contains(nextUrl)) {
								return nextUrl;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

		});

		// 第二层解析具体照片
		zhuatuServices.add(new MZhuatuDownloadService() {
			@Override
			public List<MTuInfo> parser(String html, MTuInfo pageInfo,
					List<String> projects, List<String> downloadURL) {
				if (!pages.contains(pageInfo.getUrl())) {
					pages.add(pageInfo.getUrl());
				}
				List<MTuInfo> result = new LinkedList<MTuInfo>();
				try {
					Parser parser = Parser.createParser(html, defaultCharset);

					NodeList imgs = parser.parse(new TagNameFilter("img"));
					for (Node node : imgs.toNodeArray()) {
						ImageTag img = (ImageTag) node;
						String src = img.getAttribute("src");
						String alt = img.getAttribute("alt");
						if (alt == null || src == null) {
							continue;
						}
						if (!parserTitleName(pageInfo.getTitle()).equals(
								parserTitleName(alt))
								|| downloadURL.contains(src)) {
							continue;
						}
						logger.info("取到下载链接：" + src);
						if (src.endsWith("/")) {
							throw new RuntimeException("获得的图片下载链接错误");
						}
						MTuInfo info = new MTuInfo();
						info.clear();
						info.setUrl(src);
						info.setTitle(alt);
						result.add(info);
						downloadURL.add(src);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

			@Override
			public String nextPage(String html) {
				try {
					Parser parser = Parser.createParser(html,
							defaultCharset);
					NodeList nexts = parser.parse(new HasAttributeFilter(
							"class", "a1"));
					for (Node node : nexts.toNodeArray()) {
						LinkTag link = (LinkTag) node;
						String nextUrl = link.getLink();
						if(StringUtils.isNotEmpty(nextUrl)){
							nextUrl=urlRoot+nextUrl;
							if (!pages.contains(nextUrl)) {
								return nextUrl;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

		});

		// 装载抓图任务
		BaseMZhuatu zhuatu = new BaseMZhuatu();
		zhuatu.start("http://www.gmtt8.com/archives/category/%E5%9B%BD%E6%A8%A1%E5%A5%97%E5%9B%BE/",
				"E:\\test\\shabao-m\\resources\\plugins\\mm\\gmtt8", defaultCharset,
				zhuatuServices);
	}

	/**
	 * 去除可能存在的特殊字符(目前只做等于配置使用)
	 * 
	 * @return
	 */
	protected String parserTitleName(String title) {
		return title.trim().replace("amp;", "");
	}

}
