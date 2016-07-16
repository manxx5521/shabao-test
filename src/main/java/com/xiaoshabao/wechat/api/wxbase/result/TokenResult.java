package com.xiaoshabao.wechat.api.wxbase.result;
/**
 * token返回结果
 */
public class TokenResult {
	private String token;
	private Integer expires_in;
	
	public TokenResult(){
		
	}
	
	public TokenResult(String token, Integer expires_in) {
		this.token = token;
		this.expires_in = expires_in;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

}
