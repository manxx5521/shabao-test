package com.xiaoshabao.webframe.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.wechat.component.WechatContextHolder;

public class ResourceUtil {
	private static Logger logger = LoggerFactory
			.getLogger(ResourceManager.class);
	/**
	 * 缓存标签
	 */
	public static Map<String, String> RESOURCE_TAG = new HashMap<String, String>();
	/**
	 * 缓存标签
	 */
	public static Map<String, String> RESOURCE_CONFIG = new HashMap<String, String>();
	/**
	 * 版本信息
	 */
	public static String VERSION="1.0";
	
	/**
	 * 初始化配置文件
	 * @throws Exception 
	 */
	public static void initConfig(String name,String type) throws Exception {
		String contextPath = WechatContextHolder.getSession().getServletContext().getContextPath();
		if(StringUtils.isEmpty(type)){
			type="1";
		}
		initConfig(name,contextPath,type);
	}
	/**
	 * 初始化配置文件
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static void initConfig(String name,String contextPath,String type) throws Exception {
		logger.info("开始获得Resource相关xml信息");
		/**需要解析替换的字符串*/
		String regex="\\$\\{ctx\\}";
		SAXReader reader = new SAXReader();
		InputStream is = ResourceUtil.class.getClassLoader().getResourceAsStream(name);
		Document document = reader.read(is);
		Element root = document.getRootElement();
		Iterator it = root.elementIterator();
		while (it.hasNext()) {
			Element element = (Element) it.next();
			String eName=element.getName();
			if(eName.equals("config")){
				logger.debug("装载配置信息");
				Iterator configs = element.elementIterator();
				while (configs.hasNext()) {
					Element config = (Element) configs.next();
					if(config.getName().equals("version")){
						VERSION=config.getText();
					}
					RESOURCE_CONFIG.put(config.getName(), config.getText());
					logger.debug("装载配置为:"+config.getName(), config.getText());
				}
			}
			if(eName.equals("resource")){
				logger.debug("装载resource标签信息");
				Iterator list = element.elementIterator();
				while (list.hasNext()) {
					Element resource = (Element) list.next();
					String eType=resource.attributeValue("type");
					//判断类型是否符合
					if(StringUtils.isNotEmpty(eType)){
						if(!eType.equals(type)){
							continue;
						}
					}
					if(resource.getName().equals("js")){
						String key=resource.attributeValue("key");
						String init=RESOURCE_TAG.get(key+"js");
						StringBuffer sb=new StringBuffer();
						if(StringUtils.isNotEmpty(init)){
							sb.append(init);
						}
						String src=resource.attributeValue("src");
						if(StringUtils.isEmpty(src)){
							String content=resource.getText();
							content=content.replaceAll(regex, contextPath);
							sb.append(content).append("\n");
						}else{
							src=src.replaceAll(regex, contextPath);
							sb.append("<script src=\"").append(src).append("?v=").append(VERSION);
							sb.append("\"></script>").append("\n");
						}
						RESOURCE_TAG.put(key+"js", sb.toString());
						logger.debug("装载标签为:"+key+"-> "+sb.toString());
					}else if(resource.getName().equals("css")){
						String key=resource.attributeValue("key");
						String init=RESOURCE_TAG.get(key+"css");
						StringBuffer sb=new StringBuffer();
						if(StringUtils.isNotEmpty(init)){
							sb.append(init);
						}
						String src=resource.attributeValue("src");
						if(StringUtils.isEmpty(src)){
							String content=resource.getText();
							content=content.replaceAll(regex, contextPath);
							sb.append(content).append("\n");
						}else{
							src=src.replaceAll(regex, contextPath);
							sb.append("<link href=\"").append(src).append("?v=").append(VERSION);
							sb.append("\" rel=\"stylesheet\" />").append("\n");
						}
						RESOURCE_TAG.put(key+"css", sb.toString());
						logger.debug("装载标签为:"+key+"-> "+sb.toString());
					}
				}
			}
			logger.info("资源配置解析成功");
		}
	}
	
}
