package com.xiaoshabao.wechat.component;

import javax.servlet.http.HttpSession;

import com.xiaoshabao.webframe.component.ContextHolderUtils;
import com.xiaoshabao.wechat.bean.WechatSession;

/**
 * 获取上下问工具类
 */
public class WechatContextHolder extends ContextHolderUtils{
	/**
	 * 自动登录的帐号标识
	 */
	private final static String ACCOUNT_ID="accountId";
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
	public static Integer getWxAccountId(){
		HttpSession session = getRequest().getSession();
		String accoutId=(String) session.getAttribute(ACCOUNT_ID);
		return Integer.valueOf(accoutId) ;
	}
	
	/**
	 * 为了优雅的URL进行单独创建session
	 * @param accountId
	 */
	public static void createSession(Integer accountId){
		if(accountId!=null){
			HttpSession session=getRequest().getSession();
			Integer id=(Integer) session.getAttribute(ACCOUNT_ID);
			if(id==null||id==0){
				session.setAttribute(ACCOUNT_ID, accountId);
			}
		}
	}
	
}
