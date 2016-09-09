package com.xiaoshabao.wechat.component;

import javax.servlet.http.HttpSession;

import com.xiaoshabao.webframework.component.ContextHolderUtils;
import com.xiaoshabao.wechat.bean.WechatSession;
/**
 * 获取上下问工具类
 */
public class ContextHolderWechat extends ContextHolderUtils{
	/**微信获得session标识*/
	public final static String WECHAT_SESSION="wechat";
	/**
	 * 获得wecahtSession
	 * @return
	 */
	public static WechatSession getWechatSession(){
		HttpSession session = getRequest().getSession();
		Object obj=session.getAttribute(WECHAT_SESSION);
		if(obj!=null){
			return (WechatSession)obj; 
		}else{
			return null;
		}
	}
	/**
	 * 设置wecahtSession
	 * @return
	 */
	public static void setWechatSession(WechatSession info){
		HttpSession session = getRequest().getSession();
		if(info!=null){
			session.setAttribute(WECHAT_SESSION, info);
		}
	}
	/**
	 * 获得wecahtSession
	 * @return
	 */
	public static Integer getAccountId(){
		WechatSession info=getWechatSession();
		if(info==null){
			return null;
		}
		return info.getAccountId();
	}
	/**
	 * 获得openId
	 * @return
	 */
	public static String getOpenid(){
		WechatSession info=getWechatSession();
		if(info==null){
			return null;
		}
		return info.getOpenid();
	}
	
}
