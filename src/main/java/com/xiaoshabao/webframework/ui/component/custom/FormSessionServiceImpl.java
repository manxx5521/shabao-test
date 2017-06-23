package com.xiaoshabao.webframework.ui.component.custom;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.xiaoshabao.system.component.ContextHolderSystem;
import com.xiaoshabao.webframework.ui.service.FormSessionService;

/**
 * 自定义获取系统session实现类
 */
@Service("formSessionServiceImpl")
@Lazy(false)
public class FormSessionServiceImpl implements FormSessionService {

	@Override
	public Map<String, Object> getSessionMap(HttpServletRequest request) {
		return ContextHolderSystem.getSessionMap();
	}

}
