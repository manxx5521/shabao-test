package com.xiaoshabao.webframework.component;

import java.util.Map;

public interface IContextSession {
	/**
	 * 获得所有session参数
	 * @return
	 */
	public Map<String,Object> getSessionParams();

}
