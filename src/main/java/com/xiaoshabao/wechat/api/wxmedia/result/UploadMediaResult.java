package com.xiaoshabao.wechat.api.wxmedia.result;

/**
 * 永久素材 其他返回结果
 */
public class UploadMediaResult extends UploadResult{
	
	/**
	 * 返回 地址
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
