package com.xiaoshabao.wechat.api.wxmedia.model;

import java.util.ArrayList;
import java.util.List;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 上传永久图文消息req
 */
@ReqType("uploadNews")
public class UploadNews extends WeixinReqParam {
	
	List<Article> articles = new ArrayList<Article>();

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "WxArticlesRequest [articles=" + articles + "]";
	}

}
