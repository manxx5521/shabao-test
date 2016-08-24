package com.xiaoshabao.webframe.component;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("sessionManager")
public class SessionManager {
	
	@Resource(name="contextSession")
	private IContextSession contextSession;
	
	/**
	 * 获得所有session参数
	 * @return
	 */
	public Map<String,Object> getSessionParams(){
		return contextSession.getSessionParams();
	}
	
	

}
