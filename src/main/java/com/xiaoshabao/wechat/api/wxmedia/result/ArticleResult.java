package com.xiaoshabao.wechat.api.wxmedia.result;

import com.xiaoshabao.wechat.api.wxmedia.model.Article;

/**
 * 图文消息-详细<br>
 * 素材上传处
 */
public class ArticleResult extends Article{
	/**
	 * 微信图文消息URL
	 */
	private String url;

	public String getUrl() {
		return url;
	}
	/**
	 * 微信图文消息URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
