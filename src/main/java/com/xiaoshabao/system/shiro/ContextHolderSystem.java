package com.xiaoshabao.system.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.xiaoshabao.system.entity.SessionUserInfo;

public class ContextHolderSystem {

	/**
	 * 获取Shiro的用户session信息
	 */
	public static SessionUserInfo getSeesionInfo() {
		Session session = SecurityUtils.getSubject().getSession();
		Object obj = session.getAttribute("userSession");
		if (obj != null) {
			return (SessionUserInfo) obj;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取Shiro的用户userId信息
	 */
	public static Integer getUserId() {
		return getSeesionInfo().getUserId();
	}
	/**
	 * 获取Shiro的用户权限frame信息
	 */
	public static String getPriFrame(){
		return getSeesionInfo().getPriFrame();
	}
}
