package com.xiaoshabao.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.entity.SessionUserInfo;
import com.xiaoshabao.system.entity.UserEntity;
import com.xiaoshabao.system.service.LoginService;

@Service("loginServiceImpl")
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService {

	// @Transactional(rollbackFor = Exception.class)
	@Override
	public SessionUserInfo adminLogin(String login_name, String password)
			throws Exception {
		if (login_name == null || login_name.equals("")) {
			throw new ServiceException("登录名不能为空！");
		}
		if (password == null || password.equals("")) {
			throw new ServiceException("密码不能为空！");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("login_name", login_name);
		param.put("user_password", password);
		List<UserEntity> list = this.baseDao.getData(UserEntity.class, param);
		if (list.size() != 1) {
			throw new ServiceException("用户名或密码不正确！");
		}
		UserEntity user = list.get(0);
		SessionUserInfo sessionUserInfo=new SessionUserInfo();
		sessionUserInfo.setUserId(user.getUserId());
		sessionUserInfo.setUserName(user.getUserName());
		return sessionUserInfo;
	}

}
