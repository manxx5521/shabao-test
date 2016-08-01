package com.xiaoshabao.wechat.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.wechat.bean.message.Article;
import com.xiaoshabao.wechat.bean.message.NewsMessage;
import com.xiaoshabao.wechat.bean.message.TextMessage;
import com.xiaoshabao.wechat.dao.AccountWxDao;
import com.xiaoshabao.wechat.dao.SubscriberDao;
import com.xiaoshabao.wechat.entity.AccountEntity;
import com.xiaoshabao.wechat.entity.SubscriberEntity;
import com.xiaoshabao.wechat.service.WechatService;
import com.xiaoshabao.wechat.util.MessageUtil;
import com.xiaoshabao.wechat.util.aes.AesException;
import com.xiaoshabao.wechat.util.aes.WXBizMsgCrypt;

@Service("wechatService")
public class WechatServiceImpl extends AbstractServiceImpl implements
		WechatService {
	@Autowired
	private AccountWxDao accountDao;
	@Autowired
	private SubscriberDao subscriberDao;
	/**
	 * 请求的系统帐号id
	 */
	private Integer accountId;

	// 接入操作
	@Override
	public String getIn(Integer uid, String signature, String msg_signature,
			String echostr, String timestamp, String nonce) throws AesException {
		AccountEntity account = accountDao.getAccountById(uid);
		if (StringUtils.isEmpty(account.getAppid())) {
			logger.info("用户" + uid + "接入失败，数据库未取到相应信息！");
			return null;
		}
		String encodingAESKey = account.getEncodingAESKey();
		String accessToken = account.getAccessToken();
		if (StringUtils.isEmpty(encodingAESKey)) {
			// 明文
			String[] str = { accessToken, timestamp, nonce };
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
			// SHA1加密
			String digest = DigestUtils.sha1Hex(bigStr);// 使用commos包进行SHA1加密

			// 确认请求来至微信
			if (digest.equals(signature)) {
				return echostr;
			}
		} else {
			// 加密
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(accessToken,
					encodingAESKey, account.getAppid());
			String sEchoStr; // 需要返回的明文
			sEchoStr = wxcpt
					.verifyUrl(msg_signature, timestamp, nonce, echostr);
			return sEchoStr;
		}
		return null;
	}

	// 相应微信请求
	@Override
	public String coreService(Integer uid, Map<String, String> requestMap,
			String nonce) {
		this.accountId = uid;
		String result;
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 消息类型
		String msgType = requestMap.get("MsgType");

		AccountEntity account = accountDao.getAccountById(uid);
		if (StringUtils.isEmpty(account.getAppid())) {
			logger.info("用户" + uid + "接入失败，数据库未取到相应信息！");
			return "error";
		}

		// 文本消息
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			result = this.textService(fromUserName, toUserName,
					requestMap.get("Content"));
		}
		// 事件推送
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			String event = requestMap.get("Event");
			// 如果是点击自定义菜单
			if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {
				result = this.eventService(fromUserName, toUserName, event,
						requestMap.get("EventKey"));
			} else {
				result = this.eventService(fromUserName, toUserName, event,
						null);
			}
		} else {
			result = this.defaultService(fromUserName, toUserName);
		}
		if (StringUtils.isNotEmpty(account.getEncodingAESKey())) {
			try {
				WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(
						account.getAccessToken(), account.getEncodingAESKey(),
						account.getAppid());
				String timestamp = Long
						.toString(System.currentTimeMillis() / 1000);
				return wxcpt.encryptMsg(result, timestamp, nonce);
			} catch (AesException e) {
				logger.error("相应微信时加密错误");
				e.printStackTrace();
			}
		}
		return result;
	}

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
	public String eventService(String fromUserName, String toUserName,
			String event, String eventKey) {
		String respContent = null;
		try {
			// 关注
			if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				respContent = "谢谢您的关注！";
				List<SubscriberEntity> list = subscriberDao.getSubscriber(
						this.accountId, fromUserName);
				SubscriberEntity bean = new SubscriberEntity(
						this.accountId, fromUserName, 1);
				int i = 0;
				if(list.isEmpty()||list.size()<1){
					i = subscriberDao.insert(bean);
				}else{
					i = subscriberDao.update(bean);
				}
				if (i < 1) {
					logger.error("用户" + fromUserName + "关注时未能正常更新数据");
				}
			}
			// 取消订阅
			else if (event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
				// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				SubscriberEntity bean = new SubscriberEntity(
						this.accountId, fromUserName, 2);
				int i = subscriberDao.update(bean);
				if (i < 1) {
					logger.error("用户" + fromUserName + "取消关注时未能正常更新数据");
				}
				respContent = "success";
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
	public String sendTextService(String fromUserName, String toUserName,
			String content) {
		// 默认回复此文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		if (StringUtils.isEmpty(content)) {
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
