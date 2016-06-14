package com.xiaoshabao.wechat.api.wxmenu.model.config;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class WeixinButtonExtend {

	private String type;

	private String name;

	private String key;

	private String url;

	private String value;

	private List<WeixinButtonExtend> sub_button;

	private List<MenuArticleConfig> news_info;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@JSONField(name="list")
	public List<WeixinButtonExtend> getSub_button() {
		return sub_button;
	}
	@JSONField(name="list")
	public void setSub_button(List<WeixinButtonExtend> sub_button) {
		this.sub_button = sub_button;
	}

	public List<MenuArticleConfig> getNews_info() {
		return news_info;
	}

	public void setNews_info(List<MenuArticleConfig> news_info) {
		this.news_info = news_info;
	}
}
