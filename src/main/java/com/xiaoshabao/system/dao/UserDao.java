package com.xiaoshabao.system.dao;

import com.xiaoshabao.system.entity.UserEntity;

/**
 * 用户数据操作
 */
public interface UserDao {
	
	/**
	 * 添加
	 */
	public int addUser(UserEntity user);
	
	/**
	 * 修改
	 */
	public int updateUser(UserEntity user);
	
	/**
	 * 根据id查询
	 */
	public UserEntity getUserById(Integer userId);
	
	
	

}
