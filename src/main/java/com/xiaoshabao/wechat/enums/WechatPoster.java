package com.xiaoshabao.wechat.enums;

public enum WechatPoster {
	/**
	 * 投票
	 */
	VOTE("vote");
	
	private String value;
	
	private WechatPoster(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}
}
