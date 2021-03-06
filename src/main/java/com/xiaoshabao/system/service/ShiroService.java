package com.xiaoshabao.system.service;

import java.util.Set;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.system.dto.LoginUserDto;

public interface ShiroService extends AbstractService{
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public LoginUserDto getByUserName(String loginName);
	
	/**
	 * 通过用户名查询角色信息
	 * @param userName
	 * @return
	 */
	public Set<String> getRoles(String loginName);
	
	/**
	 * 通过用户名查询权限信息
	 * @param userName
	 * @return
	 */
	public Set<String> getPermissions(String loginName);

}
