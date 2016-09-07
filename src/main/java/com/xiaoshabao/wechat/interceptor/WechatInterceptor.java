package com.xiaoshabao.wechat.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.wxbase.AuthAPI;
import com.xiaoshabao.wechat.bean.WechatSession;
import com.xiaoshabao.wechat.component.ContextHolderWechat;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.component.WechatConfig;
import com.xiaoshabao.wechat.entity.AccessToken;
import com.xiaoshabao.wechat.entity.SubscriberEntity;
import com.xiaoshabao.wechat.util.WechatUtil;
/**
 * 微信服务拦截器
 */
public class WechatInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(WechatInterceptor.class);
	@Resource(name = "wechatConfig")
	private WechatConfig wechatConfig;
	@Resource(name = "tokenManager")
	private TokenManager tokenManager;

	// 前置拦截器
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {
			HttpSession session = request.getSession();
			String loginType=wechatConfig.getLoginType();
			Integer account=null;
			String openid=null;
			Object obj=session.getAttribute(ContextHolderWechat.WECHAT_SESSION);
			//oauth2.0接口带的参数
			String state=request.getParameter("state");
			String type =request.getParameter("type");
			if(StringUtils.isNotEmpty(state)){
				account=Integer.valueOf(state);
			}
			boolean createSession=true;
			if(obj!=null){
				WechatSession sessionInfo=(WechatSession) obj;
				Integer accountId=sessionInfo.getAccountId();
				if(accountId!=null){
					if(account==null){
						createSession=false;
					}else if(accountId.equals(account)){
						createSession=false;
					}
				}
				if(sessionInfo.getType()!=2&&StringUtils.isNotEmpty(type)&&type.equals("info")){
					createSession=true;
				}
			}
			if(createSession){
				logger.info("\n ->微信端session信息失效，需要重新换取\n");
				String code = request.getParameter("code");
				String resultOpenid=null;
				WechatSession userSession=new WechatSession();
				AccessToken token=tokenManager.getAccessToken(account);
				JSONObject result=AuthAPI.getBaseInfoforJson(token.getAppid(), token.getAppsecret(), code);
				String scope=result.getString("scope");
				resultOpenid=result.getString("openid");
				userSession.setType(1);
				if(StringUtils.isNotEmpty(resultOpenid)&&StringUtils.isNotEmpty(scope)&&scope.equals(AuthAPI.AUTHURL_SCOP_SNSAPI_USERINFO)){
					JSONObject userJson=AuthAPI.getUserInfoForJson(resultOpenid, result.getString("access_token"));
					if(StringUtils.isEmpty(userJson.getString("errcode"))||userJson.getString("errcode").equals("0")){
						SubscriberEntity wechatInfo=new SubscriberEntity();
						wechatInfo.setAccountId(account);
						wechatInfo.setOpenid(resultOpenid);
						wechatInfo.setNickname(userJson.getString("nickname"));
						wechatInfo.setHeadimgurl(userJson.getString("headimgurl"));
						wechatInfo.setSex(userJson.getString("sex"));
						wechatInfo.setProvince(userJson.getString("province"));
						wechatInfo.setCity(userJson.getString("city"));
						userSession.setInfo(wechatInfo);
						userSession.setType(2);
					}
				}
				if(StringUtils.isEmpty(resultOpenid)){
					if(loginType.equals("code")){
						openid=code;
					}else{
						logger.error("自动登陆错误,未正常获得openid。\n"+result.toString());
					}
				}else{
					openid=resultOpenid;
				}
				userSession.setAccountId(account);
				userSession.setOpenid(openid);
				session.setAttribute(ContextHolderWechat.WECHAT_SESSION, userSession);
			}
		} catch (Exception e) {
			logger.error("自动登陆异常结束");
			e.printStackTrace();
		}
		return true;
	}

	// 后置拦截器
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, 
			Object handler,ModelAndView modelAndView) throws Exception {
		Integer accountId =ContextHolderWechat.getAccountId();
		try {
			if (accountId!=null) {
				String domain = wechatConfig.getDomain();
				String param = request.getQueryString();
				String url = null;
				if (param == null) {
					url = request.getRequestURL().toString();
				} else {
					url = request.getRequestURL().toString() + "?"+ request.getQueryString();
				}
				logger.info(accountId + "进行数据签名--签名url为：" + url);
				Integer account = Integer.valueOf(accountId);
				AccessToken jstoken = tokenManager.getJSToken(account);
				if (StringUtils.isNotEmpty(jstoken.getJsaccessToken())) {
					Map<String, String> jsParams = WechatUtil.getjsSignStr(jstoken.getAppid(), jstoken.getJsaccessToken(),url);
					request.setAttribute("domain", domain);
					request.setAttribute("jsParams", jsParams);
				}
			}
		} catch (Exception e) {
			logger.error("微信js签名时出现错误");
			e.printStackTrace();
		}
	}
}
