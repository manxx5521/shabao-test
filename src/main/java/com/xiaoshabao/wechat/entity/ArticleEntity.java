package com.xiaoshabao.wechat.entity;

import java.sql.Timestamp;

/**
 * 微信端文章实体类
 */
public class ArticleEntity {
	/**
	 * 文章id
	 */
	private Integer articleId;
	/**
	 * 发动到的帐号
	 */
	private Integer accountId;
	
	private String mediaId;
	/**
	 * 类型 1图文消息
	 */
	private Integer type;
	/**
	 * 0未同步到微信，1已同步
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 创建帐号
	 */
	private Integer createUser;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;
	/**
	 * 更新帐号
	 */
	private Integer updateUser;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
}
