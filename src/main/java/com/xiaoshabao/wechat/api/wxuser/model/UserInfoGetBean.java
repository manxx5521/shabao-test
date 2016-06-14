package com.xiaoshabao.wechat.api.wxuser.model;


/**
 * 获取用户基本信息
 */
public class UserInfoGetBean{

	private String openid;

	/**
	 * 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 */
	private String lang;
	
	public UserInfoGetBean(){
	}
	/**
	 * 构造,默认语言为zh_CN
	 */
	public UserInfoGetBean(String openid){
		this.openid=openid;
		this.lang="zh_CN";
	}
	/**
	 * 构造
	 */
	public UserInfoGetBean(String openid,String lang){
		this.openid=openid;
		this.lang=lang;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
 

}
