package com.xiaoshabao.system.service;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.system.entity.UserEntity;
import com.xiaoshabao.webframe.dto.AjaxResult;

public interface UserService extends AbstractService {
	
	public AjaxResult addUser(UserEntity user);
	
	public AjaxResult updateUser(UserEntity user);
	

}
