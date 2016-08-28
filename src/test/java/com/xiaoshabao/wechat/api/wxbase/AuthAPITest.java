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
			AuthAPI.getAuthURL(appid, "http://www.xiaoshabao.com/signin/init.jhtml", AuthAPI.AUTHURL_SCOP_SNSAPI_BASE, "1001");
		} catch (Exception e) {
			e.printStackTrace();
			fail("获取授权url未通过");
		}
		
	}
	
	@Test
	public void testGetAuthURL_base() {
		try {
			AuthAPI.getAuthURL_base(appid, "http://www.xiaoshabao.com/signin/init.jhtml", "1001");
		} catch (Exception e) {
			e.printStackTrace();
			fail("获取授权url未通过");
		}
	}

	@Test
	public void testGetAuthURL_info() {
		try {
			AuthAPI.getAuthURL_info(appid, "http://www.xiaoshabao.com/signin/init.jhtml", "1001");
		} catch (Exception e) {
			e.printStackTrace();
			fail("获取授权url未通过");
		}
	}

}
