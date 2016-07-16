package com.xiaoshabao.wechat.entity;

import java.sql.Timestamp;

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
	private Integer accountId;

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
	private String accessToken;
	/**
	 * 凭证有效时间(秒)
	 */
	private int expiresIn;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;

	/**
	 * js凭证
	 */
	private String jsaccessToken;
	/**
	 * js凭证有效时间
	 */
	private int jsexpiresIn;

	/**
	 * js更新时间
	 */
	private Timestamp jsupdateTime;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getJsaccessToken() {
		return jsaccessToken;
	}

	public void setJsaccessToken(String jsaccessToken) {
		this.jsaccessToken = jsaccessToken;
	}

	public int getJsexpiresIn() {
		return jsexpiresIn;
	}

	public void setJsexpiresIn(int jsexpiresIn) {
		this.jsexpiresIn = jsexpiresIn;
	}

	public Timestamp getJsupdateTime() {
		return jsupdateTime;
	}

	public void setJsupdateTime(Timestamp jsupdateTime) {
		this.jsupdateTime = jsupdateTime;
	}

}
