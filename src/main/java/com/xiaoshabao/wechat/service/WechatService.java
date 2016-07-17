package com.xiaoshabao.wechat.service;

import java.util.Map;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.wechat.util.aes.AesException;


/**
 * 微信核心服务接口
 */
public interface WechatService extends AbstractService {
	/**
	 * 微信接入操作
	 * @param uid
	 * @param signature
	 * @param echostr
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public String getIn(Integer uid,String signature,String msg_signature,String echostr,String timestamp,  String nonce)throws AesException;
	
	/**
	 * 微信被动回复接口
	 * 
	 * @param request
	 * @return
	 */
	public String coreService(Integer uid,Map<String, String> requestMap,String nonce);

}
