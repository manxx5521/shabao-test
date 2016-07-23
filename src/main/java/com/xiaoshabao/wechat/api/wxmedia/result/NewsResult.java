package com.xiaoshabao.wechat.api.wxmedia.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息获取结果
 */
public class NewsResult {
	List<ArticleResult> news_item = new ArrayList<ArticleResult>();

	public List<ArticleResult> getNews_item() {
		return news_item;
	}

	public void setNews_item(List<ArticleResult> news_item) {
		this.news_item = news_item;
	}
}
