package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 微信二维码
 */
public class QrcodeEntity {
	private Integer qrcodeId;
	/** 描述 **/
	private String des;
	/** 请求时的名字 */
	private String actionName;
	/** 二维码带的参数数字 */
	private String sceneId;
	/** 二维码带的参数字符串，只对永久二维码有效 */
	private String sceneStr;
	/** 有效时间（秒） */
	private Integer expireSeconds;
	/** 可以换取url */
	private String ticket;
	/** 二维码解析后的地址,可自行生成二维码 */
	private String url;
	/**
	 * 二维码图片地址，可用于展示
	 */
	private String qrUrl;
	/** 创建时间 */
	private Date createTime;
	public Integer getQrcodeId() {
		return qrcodeId;
	}
	public void setQrcodeId(Integer qrcodeId) {
		this.qrcodeId = qrcodeId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	public String getSceneId() {
		return sceneId;
	}
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	public String getSceneStr() {
		return sceneStr;
	}
	public void setSceneStr(String sceneStr) {
		this.sceneStr = sceneStr;
	}
	public Integer getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getQrUrl() {
		return qrUrl;
	}
	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}
}
