package com.xiaoshabao.wechat.api.wxmessage.model;

import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 群发图文消息
 */
public class MessageByGroup extends WeixinReqParam{
	private MessageFilter filter;
	private String msgtype;
	
	public MessageFilter getFilter() {
		return filter;
	}
	public void setFilter(MessageFilter filter) {
		this.filter = filter;
	}
	
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
}
