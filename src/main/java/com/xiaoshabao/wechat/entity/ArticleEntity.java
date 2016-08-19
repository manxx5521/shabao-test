package com.xiaoshabao.wechat.entity;

import java.sql.Timestamp;

/**
 * 微信端文章实体类
 */
public class ArticleEntity {
	/**
	 * 文章id
	 */
	private Integer article_id;
	/**
	 * 发动到的帐号
	 */
	private Integer account_id;
	/**
	 * 文章题目
	 */
	private String title;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Timestamp create_time;
	/**
	 * 创建帐号
	 */
	private Integer create_staff;
	/**
	 * 更新时间
	 */
	private Timestamp update_time;
	/**
	 * 更新帐号
	 */
	private Integer update_staff;
	
	/**
	 * 取数据用来补充用户名
	 */
	private String user_name;

	public Integer getArticle_id() {
		return article_id;
	}

	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Integer getCreate_staff() {
		return create_staff;
	}

	public void setCreate_staff(Integer create_staff) {
		this.create_staff = create_staff;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public Integer getUpdate_staff() {
		return update_staff;
	}

	public void setUpdate_staff(Integer update_staff) {
		this.update_staff = update_staff;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
