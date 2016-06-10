package com.xiaoshabao.system.service;

import java.util.Set;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.system.entity.UserEntity;

public interface ShiroService extends AbstractService{
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public UserEntity getByUserName(String LoginName);
	
	/**
	 * 通过用户名查询角色信息
	 * @param userName
	 * @return
	 */
	public Set<String> getRoles(String LoginName);
	
	/**
	 * 通过用户名查询权限信息
	 * @param userName
	 * @return
	 */
	public Set<String> getPermissions(String LoginName);

}
