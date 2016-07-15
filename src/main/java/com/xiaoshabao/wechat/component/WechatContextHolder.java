package com.xiaoshabao.wechat.component;

import javax.servlet.http.HttpSession;

import com.xiaoshabao.webframe.component.ContextHolderUtils;
import com.xiaoshabao.wechat.bean.WechatSession;

/**
 * 获取上下问工具类
 */
public class WechatContextHolder extends ContextHolderUtils{
	/**
	 * 获得wecahtSession
	 * @return
	 */
	public static WechatSession getWechatSession(){
		HttpSession session = getRequest().getSession();
		Object obj=session.getAttribute("wecahtSession");
		if(obj!=null){
			return (WechatSession)obj; 
		}else{
			return null;
		}
		
	}
	/**
	 * 获得wecahtSession
	 * @return
	 */
	public static String getWxAccountId(){
		HttpSession session = getRequest().getSession();
		return (String) session.getAttribute("accountId");
	}
	
}
