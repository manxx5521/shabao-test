package com.xiaoshabao.wechat.api.wxmessage.model;

import java.util.ArrayList;
import java.util.List;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;
import com.xiaoshabao.wechat.api.wxmedia.model.Article;

/**
 * 群发图文消息
 */
@ReqType("messageNews")
public class MessageNews extends WeixinReqParam{
	/**
	 * 图文消息，一个图文消息支持1到8条图文
	 */
	private List<Article> articles =new ArrayList<Article>();

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
