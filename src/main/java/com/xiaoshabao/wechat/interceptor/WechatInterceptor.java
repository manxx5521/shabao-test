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

import com.xiaoshabao.wechat.api.wxbase.AuthAPI;
import com.xiaoshabao.wechat.api.wxbase.result.AuthBaseInfo;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.component.WechatConfig;
import com.xiaoshabao.wechat.entity.AccessToken;
import com.xiaoshabao.wechat.util.WeixinUtil;
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
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("accountId");
		String openid = (String) session.getAttribute("openid");
		String accountId = request.getParameter("accountId");
		String code = request.getParameter("code");
		// 进行微信帐号缓存
		if (account == null) {
			if (StringUtils.isNotEmpty(accountId)) {
				session.setAttribute("accountId", accountId);
			}
		}
		//进行用户登录
		if(StringUtils.isEmpty(openid)){
			if (StringUtils.isNotEmpty(code)) {
				String loginType=wechatConfig.getLoginType();
				if(loginType.equals("code")){
					openid=code;
				}
				if(loginType.equals("openid")){
					AccessToken token=tokenManager.getAccessToken(Integer.valueOf(account));
					AuthBaseInfo result=AuthAPI.getBaseInfo(token.getAppid(), token.getAppsecret(), code);
					openid=result.getOpenid();
				}
				session.setAttribute("openid",openid);
			}
		}
		return true;
	}

	// 后置拦截器
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, 
			Object handler,ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		String accountId = (String) session.getAttribute("accountId");
		try {
			if (StringUtils.isNotEmpty(accountId)) {
				String domain = wechatConfig.getDomain();
				String param = request.getQueryString();
				String url = null;
				if (param == null) {
					url = request.getRequestURL().toString();
				} else {
					url = request.getRequestURL().toString() + "?"+ request.getQueryString();
				}
				logger.info(accountId + "进行数据签名" + "签名url为：" + url);
				Integer account = Integer.valueOf(accountId);
				AccessToken jstoken = tokenManager.getJSToken(account);
				if (StringUtils.isNotEmpty(jstoken.getJsaccessToken())) {
					Map<String, String> jsParams = WeixinUtil.getjsSignStr(jstoken.getAppid(), jstoken.getJsaccessToken(),url);
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
