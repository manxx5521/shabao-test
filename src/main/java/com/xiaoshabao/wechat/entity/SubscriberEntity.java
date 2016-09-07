package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 关注取消实体类
 */
public class SubscriberEntity {
	/** id */
	private Integer id;
	/** 微信系统帐号 */
	private Integer accountId;
	/** 类型1关注、2取消关注 */
	private Integer type;
	/** 微信用户id */
	private String openid;
	/** 性别 */
	private String sex;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 昵称 */
	private String nickname;
	/**微信的头像url */
	private String headimgurl;
	/** 头像本地地址 */
	private String portrait;
	/** 操作时间 */
	private Date updateTime;

	public SubscriberEntity() {
	}

	public SubscriberEntity(Integer accountId, String openid, Integer type) {
		this.accountId = accountId;
		this.openid = openid;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
}
