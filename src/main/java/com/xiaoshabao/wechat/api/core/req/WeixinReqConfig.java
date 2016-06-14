package com.xiaoshabao.wechat.api.core.req;

/**
 * 配置信息，存放XML读取的信息
 */
public class WeixinReqConfig {

	
	 private String key;
	 
	 private String url;
	 
	 private String method;
	 
	 private String mappingHandler;
	 
	 private String dataType;

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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMappingHandler() {
		return mappingHandler;
	}

	public void setMappingHandler(String mappingHandler) {
		this.mappingHandler = mappingHandler;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}
