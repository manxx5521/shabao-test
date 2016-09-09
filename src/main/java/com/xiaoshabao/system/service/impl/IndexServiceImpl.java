package com.xiaoshabao.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.DaoException;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.component.SystemConfig;
import com.xiaoshabao.system.entity.MenuEntity;
import com.xiaoshabao.system.service.IndexService;

@Service("indexServiceImpl")
public class IndexServiceImpl extends AbstractServiceImpl implements IndexService {

	@Resource(name = "systemConfig")
	private SystemConfig config;
	
	//用来获取菜单
	@Override
	public List<MenuEntity> getMenuList(Integer user_id) throws DaoException {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("group_id", config.getGroup_id_admin());
		params.put("user_id", user_id);
		return this.getData(MenuEntity.class, params);
	}
}
