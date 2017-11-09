package com.xiaoshabao.shabaotest.plugins.mzhuatu.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
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
	
	/** 解析当前项目的下载项目链接 **/
	protected List<String> parserProjectUrl = new LinkedList<String>();
	
	protected String urlRoot="http://www.gmtt8.com";

	@Test
	public void test() {
		List<MZhuatuService> zhuatuServices = new ArrayList<MZhuatuService>();
		// 第一层解析分项的信息，找打具体的项目
		zhuatuServices.add(new MZhuatuWaitService() {
			@Override
			public List<MTuInfo> parser(String html, MTuInfo pageInfo,
					final List<String> projects,boolean newProject) {
				if (!pages.contains(pageInfo.getUrl())) {
					pages.add(pageInfo.getUrl());
				}
				final List<MTuInfo> result = new LinkedList<MTuInfo>();
				try {
					Parser parser = Parser.createParser(html, defaultCharset);
					NodeList list = parser.parse(new HasAttributeFilter(
							"rel", "bookmark"));
					
					for (Node node : list.toNodeArray()) {
						if(node instanceof LinkTag&&node.getParent() instanceof HeadingTag){
							HeadingTag h2=(HeadingTag) node.getParent();
							if("entry-title".equals(h2.getAttribute("class"))){
								LinkTag link = (LinkTag) node;
								String href = link.getLink();
								String title = link.childAt(0).getText();
								if (StringUtils.isEmpty(title)||projects.contains(title)) {
									logger.info("未下载" + title + "（已经存在）");
								}else{
									logger.info("准备下载：" + title);
									projects.add(title);
									MTuInfo info = new MTuInfo();
									info.setUrl(href);
									info.setTitle(title);
									result.add(info);
								}
								
							}
							
						}
						
					}
				} catch (Exception e) {
					logger.error("解析出错{}",pageInfo.getUrl(),e);	
				}
				return result;
			}

			@Override
			public String nextPage(String html) {
				try {
					Parser parser = Parser.createParser(html,
							defaultCharset);
					NodeList nexts = parser.parse(new HasAttributeFilter(
							"class", "page-numbers"));
					for (Node node : nexts.toNodeArray()) {
						if(node instanceof LinkTag){
							LinkTag link = (LinkTag) node;
							String nextUrl = link.getLink();
							if(StringUtils.isNotEmpty(nextUrl)){
								if (!pages.contains(nextUrl)) {
									return nextUrl;
								}
							}
						}
						
					}
				} catch (Exception e) {
					logger.error("下一页 解析出错{}",e);	
				}
				return null;
			}

		});

		// 第二层解析具体照片
		zhuatuServices.add(new MZhuatuDownloadService() {
			@Override
			public List<MTuInfo> parser(String html, MTuInfo pageInfo,
					List<String> projects,boolean newProject) {
				if(newProject){
					parserProjectUrl.clear();
				}
				
				if (!pages.contains(pageInfo.getUrl())) {
					pages.add(pageInfo.getUrl());
				}
				List<MTuInfo> result = new LinkedList<MTuInfo>();
				try {
					Parser parser = Parser.createParser(html, defaultCharset);

					NodeList imgs = parser.parse(new HasAttributeFilter("class","size-full"));
					
					//换解析思路
					if(imgs==null||imgs.size()<1){
						imgs = Parser.createParser(html, defaultCharset).parse(new TagNameFilter("img"));
						/*try {
				            File file = new File("E:\\test\\1.html");
				            PrintStream ps = new PrintStream(new FileOutputStream(file));
				            ps.println(html);// 往文件里写入字符串
				            ps.flush();
				            ps.close();
				        } catch (FileNotFoundException e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				        }*/
					}
					
					for (Node node : imgs.toNodeArray()) {
						if(node instanceof ImageTag){
							ImageTag img = (ImageTag) node;
							String src = img.getAttribute("src");
							String alt = img.getAttribute("alt");
							if(StringUtils.isNotEmpty(img.getAttribute("data-echo"))){
								continue;
							}
							if (alt == null || src == null) {
								continue;
							}
							
							//去除部分无用链接
							if(src.startsWith("http://33img.com")){
								continue;
							}
							
							if (!parserTitleName(pageInfo.getTitle()).equals(
									parserTitleName(alt))
									|| parserProjectUrl.contains(src)) {
								continue;
							}
							logger.info("取到下载链接：" + src);
							if (src.endsWith("/")) {
								throw new RuntimeException("获得的图片下载链接错误");
							}
							MTuInfo info = new MTuInfo();
							info.setUrl(src);
							info.setTitle(alt);
							result.add(info);
							parserProjectUrl.add(src);
						}
						
					}
				} catch (Exception e) {
					logger.error("解析出错{}",pageInfo.getUrl(),e);	
				}
				return result;
			}

			@Override
			public String nextPage(String html) {
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
