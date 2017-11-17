package com.xiaoshabao.shabaotest.plugins.mzhuatu;

import org.springframework.web.bind.annotation.RequestMethod;

public class ZhuatuConfig {
	
	private String charset="UTF-8";
	
	private String savePath;
	
	private RequestMethod method=RequestMethod.GET;

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public void setMethod(RequestMethod method) {
		this.method = method;
	}
	
}
