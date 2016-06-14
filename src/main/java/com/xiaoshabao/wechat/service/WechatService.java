package com.xiaoshabao.wechat.service;

import javax.servlet.http.HttpServletRequest;

import com.xiaoshabao.baseframe.service.AbstractService;


/**
 * 微信核心服务接口
 */
public interface WechatService extends AbstractService {

	/**
	 * 微信被动回复接口
	 * 
	 * @param request
	 * @return
	 */
	public String coreService(HttpServletRequest request);

	/**
	 * 文本消息 处理
	 * 
	 * @param fromUserName
	 *            发送方帐号（open_id）
	 * @param toUserName
	 *            公众帐号
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public String textService(String fromUserName, String toUserName,
			String content);

	/**
	 * 事件推送接口
	 * 
	 * @param fromUserName
	 *            发送方帐号（open_id）
	 * @param toUserName
	 *            公众帐号
	 * @param content
	 *            文本消息内容
	 * @param EventKey
	 *            自定义菜单key值
	 * @return
	 */
	public String eventService(String fromUserName, String toUserName,
			String event, String eventKey);

	/**
	 * 默认回复接口
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public String defaultService(String fromUserName, String toUserName);

	/**
	 * 回复接口
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return
	 */
	public String sendTextService(String fromUserName, String toUserName,
			String content);

}
