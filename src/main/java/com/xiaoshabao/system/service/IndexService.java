package com.xiaoshabao.system.service;

import java.util.List;

import com.xiaoshabao.baseframe.exception.DaoException;
import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.system.entity.MenuEntity;

/**
 * 主页面的service
 */
public interface IndexService extends AbstractService {
	
	/**
	 * 获得菜单列表
	 * @param user_id
	 * @return
	 * @throws DaoException
	 */
	public List<MenuEntity> getMenuList(Integer user_id)throws DaoException;

}
