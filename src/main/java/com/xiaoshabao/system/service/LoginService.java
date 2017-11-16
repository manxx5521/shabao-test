package com.xiaoshabao.system.service;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.system.entity.SessionUserInfo;

/**
 * 登录的service
 */
public interface LoginService extends AbstractService {

	/**
	 * 管理界面登录
	 * 
	 * @param user_id
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public SessionUserInfo adminLogin(String userId, String password)
			throws Exception;

}
