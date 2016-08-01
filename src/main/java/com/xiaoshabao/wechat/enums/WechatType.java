package com.xiaoshabao.wechat.enums;

public enum WechatType {
	/** 投票 */
	VOTE("vote"),
	/** 砍价 */
	BARGAIN("bargain");

	private String value;

	private WechatType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
