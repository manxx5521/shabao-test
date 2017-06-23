package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * session借口，不同系统可实现借口获得session
 * <p>只实现一个</p>
 */
public interface FormSessionService {
	
	public Map<String,Object> getSessionMap(HttpServletRequest request);

}
