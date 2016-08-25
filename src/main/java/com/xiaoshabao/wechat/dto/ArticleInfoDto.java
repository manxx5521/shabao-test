package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.wechat.entity.ArticleEntity;
import com.xiaoshabao.wechat.entity.MediaNewsEntity;

/**
 * 文章列表
 */
public class ArticleInfoDto extends ArticleEntity {
	private String createName;
	private MediaNewsEntity news;

	public MediaNewsEntity getNews() {
		return news;
	}

	public void setNews(MediaNewsEntity news) {
		this.news = news;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

}
