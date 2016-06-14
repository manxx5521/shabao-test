package com.xiaoshabao.wechat.api.wxmedia.result;

/**
 * 图文列表内容
 */
public class NewsItem {
	
	private String media_id;
	private String update_time;
	private NewsResult content;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public NewsResult getContent() {
		return content;
	}

	public void setContent(NewsResult content) {
		this.content = content;
	}

}
