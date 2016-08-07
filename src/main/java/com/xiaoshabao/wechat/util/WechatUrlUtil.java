package com.xiaoshabao.wechat.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.xiaoshabao.baseframe.exception.ServiceException;
/**
 * auth2.0授权的URL
 */
public class WechatUrlUtil {
	private final static String BASEURL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx07e34f9575809866&redirect_uri=";
	private final static String PARAMS="&response_type=code&scope=snsapi_base&state=100001#wechat_redirect";
	
	/**
	 * 获得auth2.0授权的URL
	 * @param domain http://aa.kongjianweb.com
	 * @param url /signin/init.jhtml
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getAuthURL_base(String domain,String url){
		StringBuffer sb=new StringBuffer();
		try {
			sb.append(BASEURL);
			String temp=URLEncoder.encode(domain+url,"utf-8");
			sb.append(temp);
			sb.append(PARAMS);
		} catch (Exception e) {
			throw new ServiceException("获得auth2.0授权的URL 失败",e);
		}
		return sb.toString();
	}
	
	@Test
	public void test(){
		String s=getAuthURL_base("http://shabao.tunnel.qydev.com","/shabao-test/wechat/bargain/11221111/bargain");
		System.out.println(s);
	}
}
