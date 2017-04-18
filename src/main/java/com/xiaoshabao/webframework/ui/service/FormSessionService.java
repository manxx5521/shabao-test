package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * session借口，不同系统可实现借口获得session
 */
public interface FormSessionService {
	
	public Map<String,Object> getSessionMap(HttpServletRequest request);

}
