package com.xiaoshabao.wechat.api.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.xiaoshabao.wechat.api.core.config.WechatApiConfig;
import com.xiaoshabao.wechat.api.core.config.WeiXinConstant;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.handler.WeiXinReqHandler;
import com.xiaoshabao.wechat.api.core.req.WeixinReqConfig;


/**
 * 获取微信接口的信息
 */
public class WeiXinReqUtil {

	private static Logger logger = Logger.getLogger(WeiXinReqUtil.class);

	/**
	 * 缓存请求配置
	 */
	private static Map<String, WeixinReqConfig> REQ_MAPPING = new HashMap<String, WeixinReqConfig>();

	/**
	 * 缓存处理请求handler
	 */
	private static Map<String, WeiXinReqHandler> MAPPING_HANDLER = new HashMap<String, WeiXinReqHandler>();

	/**
	 * 文件类型content_type
	 */
	private static Properties file_content_type = null;
	/**
	 * 通过名称获取对象
	 * 
	 * @param className
	 * @return
	 */
	public static Object getObjectByClassName(String className) {
		Class<?> obj = null;
		try {
			obj = Class.forName(className);
			return obj.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 初始化映射请求
	 * 
	 * @param configName
	 * @throws JDOMException
	 * @throws IOException
	 * @throws WexinReqException 
	 */
	public static void initReqConfig(String configName) throws JDOMException,
			IOException, WexinReqException {
		logger.debug("开始初始化weixin-reqconfig.xml文件");
		try {
			InputStream is = WeiXinReqService.class.getClassLoader()
					.getResourceAsStream(configName);
			SAXBuilder xmlBuilder = new SAXBuilder();
			Document doc = xmlBuilder.build(is);
			Element objRoot = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> lstMapping = objRoot.getChildren("req");
			WeixinReqConfig objConfig = null;
			for (Element mapping : lstMapping) {
				String key = mapping.getAttribute("key").getValue();
				String method=WechatApiConfig.DEFAULT_METHOD;
				Attribute methodAttribute=mapping.getAttribute("method");
				if(methodAttribute!=null){
					method=methodAttribute.getValue();
				}
				String url = mapping.getAttribute("url").getValue();
				String mappingHandler = WechatApiConfig.DEFAULT_HANDLER;
				String dataType = WeiXinConstant.DATA_TYPE_MAP;
				if (mapping.getAttribute("mappingHandler") != null) {
					mappingHandler = WechatApiConfig.HANDLER_PATH+mapping.getAttribute("mappingHandler")
							.getValue();
				}
				if (mapping.getAttribute("dataType") != null) {
					dataType = mapping.getAttribute("dataType").getValue();
				}
				objConfig = new WeixinReqConfig();
				objConfig.setKey(key);
				objConfig.setMappingHandler(mappingHandler);
				objConfig.setMethod(method);
				objConfig.setUrl(url);
				objConfig.setDataType(dataType);
				logger.debug("解析xml:"+key+" 成功");
				WeiXinReqUtil.registerMapping(key, objConfig);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WexinReqException("解析weixin-reqconfig.xml 出现异常");
		}
		logger.debug("完成weixin-reqconfig.xml文件初始化");
	}

	/**
	 * 注册映射服务处理
	 * 
	 * @param key
	 * @param weixinReqConfig
	 */
	public static void registerMapping(String key,
			WeixinReqConfig weixinReqConfig) {
		WeiXinReqUtil.REQ_MAPPING.put(key, weixinReqConfig);
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key
	 * @return
	 */
	public static WeixinReqConfig getWeixinReqConfig(String key) {
		return WeiXinReqUtil.REQ_MAPPING.get(key);
	}

	/**
	 * 注册映射服务处理
	 * 
	 * @param key
	 * @param weixinReqConfig
	 */
	public static WeiXinReqHandler getMappingHander(String className) {
		WeiXinReqHandler handler = WeiXinReqUtil.MAPPING_HANDLER.get(className);
		if (handler == null) {
			handler = (WeiXinReqHandler) getObjectByClassName(className);
			WeiXinReqUtil.MAPPING_HANDLER.put(className, handler);
		}
		return handler;
	}
	
	/**
	 * 获取文件对应的 HTTP流类型
	 * 
	 * @param fileSuffix 文件扩展名
	 * @return 返回HTTP对应的Content-Type
	 */
	public static String getFileContentType(String fileSuffix) {
		if (file_content_type == null) {
			file_content_type = new Properties();
			logger.info("读取文件扩展名配置文件fie-content-type.properties");
			InputStream in = WeiXinReqService.class.getClassLoader().getResourceAsStream("fie-content-type.properties");
			try {
				file_content_type.load(in);
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				file_content_type = new Properties();
			}
		}
		return (String) file_content_type.get(fileSuffix);
	}

}
