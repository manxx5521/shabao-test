package com.xiaoshabao.wechat.api.wxmessage.model;

import java.util.HashMap;
import java.util.Map;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;

/**
 * 群发图文消息
 */
@ReqType("sendMessage")
public class SendNewsByGroup extends MessageByGroup{
	private Map<String,String> mpnews=new HashMap<String,String> ();
	
	/**
	 * 给mpnews 设置值
	 * @param key
	 * @param value
	 */
	public void setMpnews(String key,String value) {
		mpnews.put(key, value);
	}
	
	
	public Map<String, String> getMpnews() {
		return mpnews;
	}
	public void setMpnews(Map<String, String> mpnews) {
		this.mpnews = mpnews;
	}
}
