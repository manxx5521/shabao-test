package com.xiaoshabao.wechat.api.wxmedia.result;

import java.util.ArrayList;
import java.util.List;

import com.xiaoshabao.wechat.api.wxmedia.model.Article;

/**
 * 图文消息获取结果
 */
public class NewsResult {
	List<Article> news_item = new ArrayList<Article>();

	public List<Article> getNews_item() {
		return news_item;
	}

	public void setNews_item(List<Article> news_item) {
		this.news_item = news_item;
	}

}
