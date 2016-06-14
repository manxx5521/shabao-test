package com.xiaoshabao.wechat.api.core.req;

/**
 * 微信下载请求参数父类
 */
public class WeixinDownParam extends WeixinReqParam {

	/**
	 * 要下载素材的id
	 */
	private String media_id;
	
	/**
	 * 下载存储的文件目录
	 */
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
