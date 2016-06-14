package com.xiaoshabao.wechat.api.wxmedia.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 修改永久图文消息
 */
@ReqType("updateNews")
public class UpdateNews extends WeixinReqParam {

	private String media_id;
	private int index;
	private Article articles;

	
	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Article getArticles() {
		return articles;
	}

	public void setArticles(Article articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "WxArticlesRequest [media_id=" + media_id +"index=" + index +"article=" + articles + "]";
	}

}
