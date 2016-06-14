package com.xiaoshabao.wechat.api.core.config;

/**
 * api配置类
 * <p>
 * 存放一些功能相关配置，如果换项目或包等需要修改的配置信息
 * </p>
 */
public class WechatApiConfig {
	/**
	 * 默认解析的handler
	 */
	public final static String DEFAULT_HANDLER = "com.xiaoshabao.framework.wechat.api.core.handler.impl.WeixinReqDefaultHandler";
	
	/**
	 *	handler的路径名用来拼接全路径名
	 */
	public final static String HANDLER_PATH = "com.xiaoshabao.framework.wechat.api.core.handler.impl.";
	
	/**
	 *	handler的路径名用来拼接全路径名
	 */
	public final static String DEFAULT_METHOD = "post";

}
