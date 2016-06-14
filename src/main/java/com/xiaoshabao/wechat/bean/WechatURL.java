package com.xiaoshabao.wechat.bean;

/**
 * 微信相关URL信息
 */
public class WechatURL {

	/** 获取access_token的URL {@value} */
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/** 获取js_token的URL {@value} */
	public final static String JS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

}
