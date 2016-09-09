package com.xiaoshabao.system.service;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.system.entity.UserEntity;
import com.xiaoshabao.webframework.dto.AjaxResult;

public interface UserService extends AbstractService {
	
	public AjaxResult addUser(UserEntity user);
	
	public AjaxResult updateUser(UserEntity user);
	

}
