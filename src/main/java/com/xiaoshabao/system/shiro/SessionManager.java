package com.xiaoshabao.system.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.xiaoshabao.system.entity.SessionUserInfo;

public class SessionManager {
	private static SessionManager instance = new SessionManager();

	/**
	 * 私有默认构造子
	 */
	private SessionManager() {
 
	}

	/**
	 * 静态工厂方法
	 */
	public static SessionManager getInstance() {
		return instance;
	}

	/**
	 * 获取Shiro的用户session信息
	 */
	public SessionUserInfo getSeesionInfo() {
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
	public Integer getUserId() {
		return this.getSeesionInfo().getUserId();
	}
	/**
	 * 获取Shiro的用户权限frame信息
	 */
	public String getPriFrame(){
		return this.getSeesionInfo().getPriFrame();
	}
}
