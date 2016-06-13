package com.xiaoshabao.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.exception.DaoException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.entity.UserEntity;
import com.xiaoshabao.system.service.ShiroService;

@Service("shiroService")
public class ShiroServiceImpl extends AbstractServiceImpl implements ShiroService {

	@Override
	public UserEntity getByUserName(String LoginName) {
		UserEntity user = new UserEntity();
		user.setLoginName(LoginName);
		try {
			List<UserEntity> list = this.getData(UserEntity.class, user);
			if (list.isEmpty())
				throw new DaoException("用户名为空");
			return list.get(0);
		} catch (DaoException e) {
			logger.info("用户登录名:" + LoginName + ",没有在数据库取出内容");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<String> getRoles(String LoginName) {
		try {
			List<String> list = this.getData("getshirorole", LoginName);
			Set<String> set = new HashSet<String>();
			set.addAll(list);
			return set;
		} catch (Exception e) {
			logger.info("用户 "+LoginName+" 数据库获取角色失败");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<String> getPermissions(String LoginName) {
		try {
			List<String> list = this.getData("getshiropermission", LoginName);
			Set<String> set = new HashSet<String>();
			set.addAll(list);
			return set;
		} catch (Exception e) {
			logger.info("用户 "+LoginName+" 数据库获取权限失败");
			e.printStackTrace();
			return null;
		}
	}

}
