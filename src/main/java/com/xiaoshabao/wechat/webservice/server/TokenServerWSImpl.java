package com.xiaoshabao.wechat.webservice.server;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.wechat.component.WechatConfig;
import com.xiaoshabao.wechat.entity.AccessToken;
import com.xiaoshabao.wechat.service.TokenService;
/**
 * token服务
 * 生成客户端代码命令
 * wsdl2java -p com.xiaoshabao.wechat.webservice -d E:/test -client http://www.xiaoshabao.com/ws/token?wsdl
 */
@WebService
@Component("tokenServerWSImpl")
public class TokenServerWSImpl implements TokenServerWS{
	@Resource(name="wechatConfig")
	private WechatConfig wechatConfig;
	@Override
	public AccessToken getToken(Integer accountId) {
		if(accountId==null||accountId==0){
			throw new ServiceException("帐号错误");
		}
		String serviceName=wechatConfig.getTokenService();
		TokenService tokenService=ApplicationContextUtil.getBean(serviceName,TokenService.class);
		return tokenService.getToken(accountId);
	}
	
}
