package com.xiaoshabao.wechat.service.impl;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.wechat.entity.AccessToken;
import com.xiaoshabao.wechat.interceptor.TokenServiceInterceptorAdd;
import com.xiaoshabao.wechat.webservice.TokenServerWS;
import com.xiaoshabao.wechat.webservice.TokenServerWSImplService;
/**
 * 直接在webService侧获得
 */
@Service("tokenWsServiceImpl")
public class TokenWsServiceImpl extends TokenAbstractServiceImpl{
	private String key="shbmx123";

	@Override
	public AccessToken getToken(Integer accountId) {
		return this.getwsToken(accountId);
	}

	@Override
	public AccessToken getAccessToken(Integer accountId) {
		return this.getwsToken(accountId);
	}

	@Override
	public AccessToken getJSToken(Integer accountId) {
		return this.getwsToken(accountId);
	}
	
	
	private AccessToken getwsToken(Integer accountId){
		AccessToken accessToken=new AccessToken();
		try {
			TokenServerWSImplService service=new TokenServerWSImplService();
			TokenServerWS tokenws=service.getTokenServerWSImplPort();
			// 添加自定义拦截器
			org.apache.cxf.endpoint.Client client=ClientProxy.getClient(tokenws);
			client.getOutInterceptors().add(new TokenServiceInterceptorAdd(this.key));
			// 添加out日志拦截器
			client.getOutInterceptors().add(new LoggingOutInterceptor()); 
					    
			com.xiaoshabao.wechat.webservice.AccessToken wstoken=tokenws.getToken(accountId);
			accessToken.setAccessToken(wstoken.getAccessToken());
			accessToken.setJsaccessToken(wstoken.getJsaccessToken());
			logger.debug("*成功在webService获得token信息 token:{}",accessToken.getAccessToken());
		} catch (Exception e) {
			logger.error("数据库获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}

}
