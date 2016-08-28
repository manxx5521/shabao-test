package com.xiaoshabao.webframe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframe.component.ApplicationContextUtil;
import com.xiaoshabao.baseframe.component.SysConfig;
import com.xiaoshabao.wechat.api.core.exception.WeixinReqException;

public class ResourceManager {
	private static Logger logger = LoggerFactory.getLogger(ResourceManager.class);
	private static ResourceManager instance = null;

	private ResourceManager() {
		try {
			SysConfig sysConfig=ApplicationContextUtil.getBean("sysConfig",SysConfig.class);
			ResourceUtil.initConfig("resource-congfig.xml", sysConfig.getResourceType());
		} catch (Exception e) {
			logger.error("资源配置文件解析失败");
			e.printStackTrace();
		}
	}

	/**
	 * 获取公共的调用处理
	 * @param type 
	 * @return
	 * @throws WeixinReqException
	 */
	public static ResourceManager getInstance() throws WeixinReqException {
		if (instance == null) {
			// 同步块，线程安全的创建实例
			synchronized (ResourceManager.class) {
				// 再次检查实例是否存在，如果不存在才真正的创建实例
				if (instance == null) {
					instance = new ResourceManager();
				}
			}
		}
		return instance;
	}
	/**
	 * 获得标签
	 * @param key
	 * @return
	 */
	public String getTag(String key,String type){
		String value=ResourceUtil.RESOURCE_TAG.get(key+type);
		if(value==null){
			value="";
		}
		return value;
	}
	/**
	 * 获得标签
	 * @param key
	 * @return
	 */
	public String getJSTag(String key){
		String value=ResourceUtil.RESOURCE_TAG.get(key+"js");
		if(value==null){
			value="";
		}
		return value;
	}
	/**
	 * 获得标签
	 * @param key
	 * @return
	 */
	public String getCssTag(String key){
		String value=ResourceUtil.RESOURCE_TAG.get(key+"css");
		if(value==null){
			value="";
		}
		return value;
	}

	
}
