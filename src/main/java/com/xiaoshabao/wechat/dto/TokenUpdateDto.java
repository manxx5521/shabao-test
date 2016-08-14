package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.wechat.entity.AccessToken;
/**
 * 调度任务更新token
 */
public class TokenUpdateDto extends AccessToken {
	/**
	 * 状态1更新，0不更新
	 */
	private int tokenState;
	/**
	 * 状态1更新，0不更新
	 */
	private int jsState;

	public int getTokenState() {
		return tokenState;
	}

	public void setTokenState(int tokenState) {
		this.tokenState = tokenState;
	}

	public int getJsState() {
		return jsState;
	}

	public void setJsState(int jsState) {
		this.jsState = jsState;
	}

}
