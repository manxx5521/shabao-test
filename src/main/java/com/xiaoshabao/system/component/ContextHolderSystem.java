package com.xiaoshabao.system.component;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.system.entity.SessionUserInfo;
import com.xiaoshabao.webframework.component.ContextHolderUtils;
import com.xiaoshabao.webframework.component.SessionParams;

/**
 * 上下文操作
 *
 */
public class ContextHolderSystem extends ContextHolderUtils implements SessionParams{
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
	public static String getPriFrame() {
		return getSeesionInfo().getPriFrame();
	}

	/** 
	 * 统一获得参数
	 * @return
	 */
	public static Map<String, Object> getSessionMap() {
		SessionUserInfo session = getSeesionInfo();
		if (session == null) {
			return null;
		}
		// 使用Map方式解析
		String jsonData = JSONObject.toJSONString(session, true);
		Map<String, Object> params = JSONObject.parseObject(jsonData);
		return params;
	}
	
	@Override
	public Map<String, Object> getSessionParams() {
		return getSessionMap();
	}
	@Override
	public Object getSessionObject() {
		return getSeesionInfo();
	}
	
}
