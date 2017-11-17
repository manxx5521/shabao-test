package com.xiaoshabao.shabaotest.plugins.mzhuatu;

import java.io.Serializable;

public class MTuInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String url;
	private String title;

	public MTuInfo() {
	}

	public MTuInfo(String url, String title) {
		this.url = url;
		this.title = title;
	}

	public void clear() {
		this.url = null;
		this.title = null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
