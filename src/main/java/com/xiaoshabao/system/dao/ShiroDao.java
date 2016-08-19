package com.xiaoshabao.system.dao;

import com.xiaoshabao.system.dto.LoginUserDto;

/**
 * 安全相关数据处理
 */
public interface ShiroDao {
	/**
	 * 获得登录人的信息
	 * @param loginName 登录名
	 * @return
	 */
	public LoginUserDto getLoginUser(String loginName);
	
}
