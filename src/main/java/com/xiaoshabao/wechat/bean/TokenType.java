package com.xiaoshabao.wechat.bean;

/**
 * Token类型信息
 */
public enum TokenType {
	/**
	 * token的全部信息
	 */
	TOKEN("token"),
	/**
	 * 只是accessToken
	 */
	ACCESSTOKEN("accesstoken"),
	/**
	 * 只是jsToken
	 */
	JSTOKEN("jstoken");

	private String value;

	/**
	 * 换取token的URL
	 */
	private String token_URL;
	/**
	 * 换取token是的名字
	 */
	private String token_name;

	/**
	 * token有效时间
	 */
	private String expires_in_name;

	// 初始化方法填充值，有多少个变量初始化几次。
	private TokenType(String s) {
		// System.out.println("初始化一次");
		this.value = s;

		switch (s) {
		case "token":
			break;
		case "accesstoken":
			this.token_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
			this.token_name = "access_token";
			this.expires_in_name = "expires_in";
			break;
		case "jstoken":
			this.token_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
			this.token_name = "ticket";
			this.expires_in_name = "expires_in";
			break;
		}
	}

	public String getValue() {
		return value;
	}

	/**
	 * 换取token的URL
	 */
	public String getToken_URL() {
		return token_URL;
	}

	/**
	 * 换取token时的名字
	 */
	public String getToken_name() {
		return token_name;
	}

	/**
	 * token有效时间
	 */
	public String getExpires_in_name() {
		return expires_in_name;
	}

}
