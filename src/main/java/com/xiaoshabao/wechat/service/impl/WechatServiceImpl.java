package com.xiaoshabao.wechat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.wechat.bean.message.Article;
import com.xiaoshabao.wechat.bean.message.NewsMessage;
import com.xiaoshabao.wechat.bean.message.TextMessage;
import com.xiaoshabao.wechat.entity.WXUserLogEntity;
import com.xiaoshabao.wechat.service.WechatService;
import com.xiaoshabao.wechat.util.MessageUtil;

@Service("wechatService")
public class WechatServiceImpl extends AbstractServiceImpl implements
		WechatService {

	@Override
	public String coreService(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			logger.info("wxlog:uese open_id:" + fromUserName
					+ "--- message type:" + "msgType");

			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			StringBuffer contentMsg = new StringBuffer();
			contentMsg.append("欢迎访问<a href=\"http://baidu.com\">个人主页</a>")
					.append("\n");
			contentMsg.append("菜单消息：").append("\n\n");
			contentMsg.append("1  天气预报").append("\n");
			contentMsg.append("2  公交查询").append("\n");
			contentMsg.append("3  周边搜索").append("\n");
			contentMsg.append("4  歌曲点播").append("\n");
			contentMsg.append("5  经典游戏").append("\n");
			contentMsg.append("6  美女电台").append("\n");
			contentMsg.append("7  人脸识别").append("\n");
			contentMsg.append("8  聊天唠嗑").append("\n");
			contentMsg.append("9  电影排行榜").append("\n");
			contentMsg.append("10 Q友圈").append("\n\n");
			textMessage.setContent(contentMsg.toString());
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);

			// 根据事件类型返回不同消息
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");

				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if ("1".equals(content)) {

					// 创建图文消息
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setFuncFlag(0);

					// 创建图文连接
					Article article = new Article();
					article.setTitle("欢迎");
					article.setDescription("微信服务开通了");
					article.setPicUrl("http://123.57.224.99:80/resources/wechat/images/2015102601.jpg");
					article.setUrl("http://123.57.224.99:80/admin/login.jhtml");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	@Override
	public String textService(String fromUserName, String toUserName,
			String content) {
		String respMessage = null;
		// 接收用户发送的文本消息内容
		List<Article> articleList = new ArrayList<Article>();
		// 单图文消息
		if ("1".equals(content)) {

			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			// 创建图文连接
			Article article = new Article();
			article.setTitle("欢迎");
			article.setDescription("微信服务开通了");
			article.setPicUrl("http://123.57.224.99:80/resources/wechat/images/2015102601.jpg");
			article.setUrl("http://123.57.224.99:80/admin/login.jhtml");
			articleList.add(article);
			// 设置图文消息个数
			newsMessage.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合
			newsMessage.setArticles(articleList);
			// 将图文消息对象转换成xml字符串
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		} else {
			respMessage = defaultService(fromUserName, toUserName);
		}
		return respMessage;
	}

	// 事件推送
	@Override
	public String eventService(String fromUserName, String toUserName,
			String event, String eventKey) {
		String respContent = null;
		try {
			// 关注
			if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				respContent = "谢谢您的关注！";
				WXUserLogEntity bean = new WXUserLogEntity();
				bean.setOpen_id(fromUserName);
				bean.setType("1");
				this.baseDao.insert(WXUserLogEntity.class, bean);
			}
			// 取消订阅
			else if (event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
				// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				WXUserLogEntity bean = new WXUserLogEntity();
				bean.setOpen_id(fromUserName);
				bean.setType("2");
				this.baseDao.insert(WXUserLogEntity.class, bean);
			}
			// 自定义菜单点击事件
			else if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {
				if (eventKey.equals("menutitle21")) {
					respContent = "菜单项被点击！";
				} else if (eventKey.equals("menutitle22")) {
					respContent = "菜单项被点击！";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.sendTextService(fromUserName, toUserName, respContent);
	}

	// 默认类型回复
	@Override
	public String defaultService(String fromUserName, String toUserName) {
		// 默认回复此文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
		StringBuffer contentMsg = new StringBuffer();
		contentMsg.append("欢迎访问<a href=\"http://baidu.com\">个人主页</a>").append(
				"\n");
		contentMsg.append("菜单消息：").append("\n\n");
		contentMsg.append("1  天气预报").append("\n");
		contentMsg.append("2  公交查询").append("\n");
		contentMsg.append("3  周边搜索").append("\n");
		contentMsg.append("4  歌曲点播").append("\n");
		contentMsg.append("5  经典游戏").append("\n");
		contentMsg.append("6  美女电台").append("\n");
		contentMsg.append("7  人脸识别").append("\n");
		contentMsg.append("8  聊天唠嗑").append("\n");
		contentMsg.append("9  电影排行榜").append("\n");
		contentMsg.append("10 Q友圈").append("\n\n");
		textMessage.setContent(contentMsg.toString());
		// 将文本消息对象转换成xml字符串
		return MessageUtil.textMessageToXml(textMessage);
	}

	// 发送文本信息
	@Override
	public String sendTextService(String fromUserName, String toUserName,
			String content) {
		// 默认回复此文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		if (content.isEmpty()) {
			content = "正在学习，无法回复你您！";
		}
		textMessage.setContent(content);
		// 将文本消息对象转换成xml字符串
		return MessageUtil.textMessageToXml(textMessage);
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	@SuppressWarnings("unused")
	private String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

}
