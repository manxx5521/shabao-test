package com.xiaoshabao.wechat.api.core.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * HostnameVerifier
 * <p>
 * 解决微信访问https文件下载等功能时，报异常 No subject alternative DNS name matching
 * file.api.weixin.qq.com found
 * </p>
 */
public class CustomizedHostnameVerifier implements HostnameVerifier {

	@Override
	public boolean verify(String arg0, SSLSession arg1) {
		return true;
	}

}