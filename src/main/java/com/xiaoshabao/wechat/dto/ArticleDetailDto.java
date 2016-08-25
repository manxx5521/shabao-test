package com.xiaoshabao.wechat.dto;

import java.util.List;

import com.xiaoshabao.webframe.dto.ImageDto;
import com.xiaoshabao.wechat.entity.ArticleEntity;
import com.xiaoshabao.wechat.entity.MediaNewsEntity;

/**
 * 文章描述信息
 */
public class ArticleDetailDto extends ArticleEntity {
	/**
	 * 微信帐号信息
	 */
	private List<AccountValue> accounts;
	
	private MediaNewsEntity news;
	/**
	 * 封面图片
	 */
	private ImageDto image;

	public List<AccountValue> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountValue> accounts) {
		this.accounts = accounts;
	}

	public MediaNewsEntity getNews() {
		return news;
	}

	public void setNews(MediaNewsEntity news) {
		this.news = news;
	}

	public ImageDto getImage() {
		return image;
	}

	public void setImage(ImageDto image) {
		this.image = image;
	}
	
}
