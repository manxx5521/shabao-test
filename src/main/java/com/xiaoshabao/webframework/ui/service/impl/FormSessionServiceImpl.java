package com.xiaoshabao.webframework.ui.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.xiaoshabao.webframework.ui.service.FormSessionService;
@Service("formSessionServiceImpl")
public class FormSessionServiceImpl implements FormSessionService {

	@Override
	public Map<String, Object> getSessionMap(HttpServletRequest request) {
		
		return null;
	}

}
