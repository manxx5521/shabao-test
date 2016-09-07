package com.xiaoshabao.wechat.api.wxbase;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 授权测试
 */
public class AuthAPITest {
	
	private String appid=TokenAPITest.appid;

	@Test
	public void testGetBaseInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBaseInfoforJson() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserInfoForJson() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAuthURL() {
		try {
			String url=AuthAPI.getAuthURL(appid, "http://shabao.tunnel.qydev.com/", AuthAPI.AUTHURL_SCOP_SNSAPI_BASE, "1001");
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
			fail("获取授权url未通过");
		}
		
	}
	
	@Test
	public void testGetAuthURL_base() {
		try {
			String url=AuthAPI.getAuthURL_base(appid, "http://wechat.xiaoshabao.com/wechat/vote/10000001/list", "100001");
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
			fail("获取授权url未通过");
		}
	}

	@Test
	public void testGetAuthURL_info() {
		try {
			String url=AuthAPI.getAuthURL_info(appid, "http://wechat.xiaoshabao.com/wechat/vote/10000001/list", "100001");
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
			fail("获取授权url未通过");
		}
	}

}
