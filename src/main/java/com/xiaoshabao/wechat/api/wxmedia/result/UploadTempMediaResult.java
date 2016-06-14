package com.xiaoshabao.wechat.api.wxmedia.result;

/**
 * 临时素材上传返回结果
 */
public class UploadTempMediaResult extends UploadResult{
	
	private String type;

	private String created_at;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
