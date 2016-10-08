package com.xiaoshabao.wechat.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.cxf.frontend.ClientProxy;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.wechat.entity.AccessToken;
import com.xiaoshabao.wechat.interceptor.TokenServiceInterceptorAdd;
import com.xiaoshabao.wechat.webservice.TokenServerWS;
import com.xiaoshabao.wechat.webservice.TokenServerWSImplService;
/**
 * 直接在数据库侧获得
 */
@Service("tokenWsServiceImpl")
public class TokenWsServiceImpl extends TokenAbstractServiceImpl{
	private String key="shbmx123";

	@Override
	public AccessToken getToken(Integer accountId) {
		AccessToken accessToken=new AccessToken();
		try {
			TokenServerWSImplService service=new TokenServerWSImplService();
			TokenServerWS tokenws=service.getTokenServerWSImplPort();
			// 添加自定义拦截器
			org.apache.cxf.endpoint.Client client=ClientProxy.getClient(tokenws);
		    client.getOutInterceptors().add(new TokenServiceInterceptorAdd(this.key)); 
		    
			com.xiaoshabao.wechat.webservice.AccessToken wstoken=tokenws.getToken(accountId);
			BeanUtils.copyProperties(wstoken, accessToken);
			accessToken=this.getRealToken(accountId);
			logger.debug("*成功在webService获得token信息 token:{}",accessToken.getAccessToken());
		} catch (Exception e) {
			logger.error("数据库获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}

	@Override
	public AccessToken getAccessToken(Integer accountId) {
		AccessToken accessToken=new AccessToken();
		try {
			TokenServerWSImplService service=new TokenServerWSImplService();
			TokenServerWS tokenws=service.getTokenServerWSImplPort();
			// 添加自定义拦截器
			org.apache.cxf.endpoint.Client client=ClientProxy.getClient(tokenws);
			client.getOutInterceptors().add(new TokenServiceInterceptorAdd(this.key)); 
					    
			com.xiaoshabao.wechat.webservice.AccessToken wstoken=tokenws.getToken(accountId);
			BeanUtils.copyProperties(wstoken, accessToken);
			accessToken=this.getRealToken(accountId);
			logger.debug("*成功在webService获得token信息 token:{}",accessToken.getAccessToken());
		} catch (Exception e) {
			logger.error("数据库获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}

	@Override
	public AccessToken getJSToken(Integer accountId) {
		AccessToken accessToken=new AccessToken();
		try {
			TokenServerWSImplService service=new TokenServerWSImplService();
			TokenServerWS tokenws=service.getTokenServerWSImplPort();
			// 添加自定义拦截器
			org.apache.cxf.endpoint.Client client=ClientProxy.getClient(tokenws);
			client.getOutInterceptors().add(new TokenServiceInterceptorAdd(this.key));
			
			com.xiaoshabao.wechat.webservice.AccessToken wstoken=tokenws.getToken(accountId);
			BeanUtils.copyProperties(wstoken, accessToken);
			accessToken=this.getRealToken(accountId);
			logger.debug("*成功在webService获得token信息 token:{}",accessToken.getAccessToken());
		} catch (Exception e) {
			logger.error("数据库获取token错误"+e.getMessage());
			e.printStackTrace();
			throw new ServiceException("获取token错误");
		}
		return accessToken;
	}

}
