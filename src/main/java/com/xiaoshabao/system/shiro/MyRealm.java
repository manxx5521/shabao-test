package com.xiaoshabao.system.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.xiaoshabao.system.dto.LoginUserDto;
import com.xiaoshabao.system.entity.SessionUserInfo;
import com.xiaoshabao.system.service.ShiroService;


public class MyRealm extends AuthorizingRealm {

	@Resource(name = "shiroService")
	private ShiroService shiroService;

	/**
	 * 为当限前登录的用户授予角色和权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(shiroService.getRoles(userName));
		authorizationInfo.setStringPermissions(shiroService
				.getPermissions(userName));
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		LoginUserDto user = shiroService.getByUserName(userName);
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
				user.getLoginName(), user.getPassword(), "xx");
		// 当验证都通过后，把用户信息放在session里
		Session session = SecurityUtils.getSubject().getSession();
		SessionUserInfo userSession = new SessionUserInfo();
		userSession.setUserId(user.getUserId());
		userSession.setUserName(user.getUserName());
		userSession.setPriDepart(user.getDepart().getDepartId());
		userSession.setDepartId(user.getDepart().getDepartId());
		userSession.setPriFrame("'"+user.getDepart().getDepartFrame()+"%'");
		session.setAttribute("userSession", userSession);
		return authcInfo;
	}

}
