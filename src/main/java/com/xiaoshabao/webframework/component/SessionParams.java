package com.xiaoshabao.webframework.component;

import java.util.Map;

/**
 * web项目获取子项目参数
 */
public interface SessionParams {
	
	/**
	 * 获得所有session参数
	 * @return
	 */
	public Map<String,Object> getSessionParams();	
	
	/**
	 * 获得所有session的类
	 * @return
	 */
	public Object getSessionObject();
}
