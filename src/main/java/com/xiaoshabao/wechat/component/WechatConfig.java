package com.xiaoshabao.wechat.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 微信相关设置
 * @author mxv
 */
@Component("wechatConfig")
public class WechatConfig {
	/**
	 * 网址
	 */
	@Value("${wechat.domain}")
	private String domain;

	public String getDomain() {
		return domain;
	}

}
