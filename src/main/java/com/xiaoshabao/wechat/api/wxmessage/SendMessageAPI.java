package com.xiaoshabao.wechat.api.wxmessage;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxmedia.model.Article;
import com.xiaoshabao.wechat.api.wxmessage.model.MessageFilter;
import com.xiaoshabao.wechat.api.wxmessage.model.MessageNews;
import com.xiaoshabao.wechat.api.wxmessage.model.SendNewsByGroup;
import com.xiaoshabao.wechat.api.wxmessage.model.SendTextByGroup;
import com.xiaoshabao.wechat.api.wxmessage.result.MessageUploadResult;
import com.xiaoshabao.wechat.api.wxmessage.result.NewsMessResult;

public class SendMessageAPI {
	/**
	 * 上传群发图文消息素材
	 * @param articles 图文消息，一个图文消息支持1到8条图文
	 * @return
	 * @throws WexinReqException 
	 */
	public static MessageUploadResult uploadNews(String accessToken,List<Article> articles) throws WexinReqException{
		MessageNews upload =new MessageNews();
		upload.setAccess_token(accessToken);
		upload.setArticles(articles);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		return JSONObject.toJavaObject(result, MessageUploadResult.class);
	}
	
	/**
	 * 群发图文消息-通过分组
	 * @param accessToken
	 * @param media_id
	 * @param group_id 要发送到的分组
	 * @return
	 * @throws WexinReqException
	 */
	public static NewsMessResult sendNewsMessByGroup(String accessToken,String media_id,String group_id) throws WexinReqException{
		SendNewsByGroup upload =new SendNewsByGroup();
		upload.setAccess_token(accessToken);
		upload.setFilter(new MessageFilter (group_id));
		upload.setMpnews("media_id", media_id);
		upload.setMsgtype("mpnews");
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		return JSONObject.toJavaObject(result, NewsMessResult.class);
	}
	
	/**
	 * 群发文本消息-通过分组
	 * @param accessToken
	 * @param content 文本内容
	 * @param group_id 要发送到的组
	 * @return msg_id 消息发送任务的ID
	 * @throws WexinReqException
	 */
	public static String sendMessTextByGroup(String accessToken,String content,String group_id) throws WexinReqException{
		SendTextByGroup upload =new SendTextByGroup();
		upload.setAccess_token(accessToken);
		upload.setFilter(new MessageFilter (group_id));
		upload.setText("content", content);
		upload.setMsgtype("text");
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		return result.getString("msg_id");
	}
}
