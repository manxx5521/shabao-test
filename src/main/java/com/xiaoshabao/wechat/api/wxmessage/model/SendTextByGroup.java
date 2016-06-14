package com.xiaoshabao.wechat.api.wxmessage.model;

import java.util.HashMap;
import java.util.Map;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;

/**
 * 群发图文消息
 */
@ReqType("sendMessage")
public class SendTextByGroup extends MessageByGroup{
	private Map<String,String> text=new HashMap<String,String> ();
	
	/**
	 * 给text 设置值
	 * @param key
	 * @param value
	 */
	public void setText(String key,String value) {
		text.put(key, value);
	}

	public Map<String, String> getText() {
		return text;
	}

	public void setText(Map<String, String> text) {
		this.text = text;
	}
	
}
