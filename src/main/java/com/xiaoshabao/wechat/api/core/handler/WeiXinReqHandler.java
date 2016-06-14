package com.xiaoshabao.wechat.api.core.handler;

import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.req.WeixinReqConfig;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 获取微信接口的信息
 * <p>
 * 所有的微信请求都需要通过此接口发送
 * </p>
 */
public interface WeiXinReqHandler {

	/**
	 * 发送是那个微信请求接口
	 * <p>
	 * 传入参数，发送微信请求。具体的实现接口是在weixin-reqcongfig.xml中配置的。
	 * </p>
	 * 
	 * @param weixinReqParam
	 * @return
	 * @throws WexinReqException
	 */
	public String doRequest(WeixinReqParam weixinReqParam,
			WeixinReqConfig objConfig) throws WexinReqException;

}
