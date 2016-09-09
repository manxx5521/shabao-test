package com.xiaoshabao.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.dao.ShiroDao;
import com.xiaoshabao.system.dto.LoginUserDto;
import com.xiaoshabao.system.service.ShiroService;

@Service("shiroService")
public class ShiroServiceImpl extends AbstractServiceImpl implements ShiroService {
	@Autowired
	private ShiroDao shiroDao;
	@Override
	public LoginUserDto getByUserName(String LoginName) {
		try {
			LoginUserDto loginUser=shiroDao.getLoginUser(LoginName);
			return loginUser;
		} catch (Exception e) {
			logger.error("用户登录名:" + LoginName + ",没有在数据库取出内容");
			e.printStackTrace();
			throw new UnknownAccountException();// 没找到帐号
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
