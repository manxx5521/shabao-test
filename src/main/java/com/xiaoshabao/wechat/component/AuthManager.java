package com.xiaoshabao.wechat.component;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 微信授权管理
 */
@Component("authManager")
public class AuthManager {
	private static Logger logger = LoggerFactory.getLogger(AuthManager.class);
	@Resource(name = "wechatConfig")
	private WechatConfig wechatConfig;

	/**
	 * 通过authcode 获取用户信息
	 */
	public static String createSessionWithAuthCode(String accountId, String code) {
		String openid = "";
		try {

		} catch (Exception e) {
			logger.info("error:", e);
			e.printStackTrace();
		}
		return openid;
	}
}
