package com.xiaoshabao.wechat.entity;

import java.sql.Timestamp;

import com.xiaoshabao.wechat.bean.TokenType;

/**
 * 微信帐号表对应wx_accout表信息
 * <p>
 * 用来存储微信的各类凭证信息, 比如token的串码和过期时间等
 * </p>
 */
public class AccessToken {

	/**
	 * 这个微信下的应用id
	 */
	private Integer account_id;

	/**
	 * 微信帐号的id
	 */
	private String appid;
	/**
	 * 微信给的加密
	 */
	private String appsecret;

	/**
	 * 同一个appid下的应用id
	 */
	private String id;

	/**
	 * 凭证
	 */
	private String access_token;
	/**
	 * 凭证有效时间(秒)
	 */
	private int expires_in;
	/**
	 * 更新时间
	 */
	private Timestamp update_time;

	/**
	 * js凭证
	 */
	private String jsaccess_token;
	/**
	 * js凭证有效时间
	 */
	private int jsexpires_in;

	/**
	 * js更新时间
	 */
	private Timestamp jsupdate_time;

	/**
	 * 判断token是否有
	 * 
	 * @param tokenType
	 *            传入token类型
	 * @return 有返回false，没有返回true
	 */
	public boolean isTokenEmpty(TokenType tokenType) {
		switch (tokenType) {
		case TOKEN:
			if (access_token == null || expires_in == 0 || update_time == null)
				return true;
			if (jsaccess_token == null || jsexpires_in == 0
					|| jsupdate_time == null)
				return true;
			break;
		case ACCESSTOKEN:
			if (access_token == null || expires_in == 0 || update_time == null)
				return true;
			break;
		case JSTOKEN:
			if (jsaccess_token == null || jsexpires_in == 0
					|| jsupdate_time == null)
				return true;
			break;
		}
		return false;
	}

	/**
	 * 根据枚举类型获得对应时间戳
	 * 
	 * @param tokenType
	 * @return
	 */
	public long getTokenUpdateTime(TokenType tokenType) {
		switch (tokenType) {
		case ACCESSTOKEN:
			return getUpdate_time().getTime();
		case JSTOKEN:
			return getJsupdate_time().getTime();
		default:
			return 0;
		}
	}

	/**
	 * 根据枚举类型获得对应有效时间
	 * 
	 * @param tokenType
	 * @return
	 */
	public long getTokenExpires_in(TokenType tokenType) {
		switch (tokenType) {
		case ACCESSTOKEN:
			return getExpires_in();
		case JSTOKEN:
			return getJsexpires_in();
		default:
			return 0;
		}
	}

	/**
	 * 设置token，根据枚举类
	 * 
	 * @param token
	 * @param tokenType
	 */
	public void setToken(String token, TokenType tokenType) {
		switch (tokenType) {
		case ACCESSTOKEN:
			this.access_token = token;
			break;
		case JSTOKEN:
			this.jsaccess_token = token;
			break;
		default:
			break;
		}
	}

	/**
	 * 设置有效时间，根据枚举类
	 * 
	 * @param expires_in
	 * @param tokenType
	 */
	public void setExpires_in(int expires_in, TokenType tokenType) {
		switch (tokenType) {
		case ACCESSTOKEN:
			this.expires_in = expires_in;
			break;
		case JSTOKEN:
			this.jsexpires_in = expires_in;
			break;
		default:
			break;
		}
	}

	/**
	 * 设置有效时间，根据枚举类
	 * 
	 * @param expires_in
	 * @param tokenType
	 */
	public void setTokenUpdateTime(Timestamp time, TokenType tokenType) {
		switch (tokenType) {
		case ACCESSTOKEN:
			this.update_time = time;
			break;
		case JSTOKEN:
			this.jsupdate_time = time;
			break;
		default:
			break;
		}
	}

	/********************************************/
	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public String getJsaccess_token() {
		return jsaccess_token;
	}

	public void setJsaccess_token(String jsaccess_token) {
		this.jsaccess_token = jsaccess_token;
	}

	public int getJsexpires_in() {
		return jsexpires_in;
	}

	public void setJsexpires_in(int jsexpires_in) {
		this.jsexpires_in = jsexpires_in;
	}

	public Timestamp getJsupdate_time() {
		return jsupdate_time;
	}

	public void setJsupdate_time(Timestamp jsupdate_time) {
		this.jsupdate_time = jsupdate_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
