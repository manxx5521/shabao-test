package com.xiaoshabao.wechat.api.wxbase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.wxbase.TokenAPI;

/**
 * 微信token获取接口测试
 */
public class TokenAPITest{
	
	public final static String accessToken="TMjZ_e4Z-u_GSF1RriVVecy_bpyOm-nnsIeO8fGJiCnSoCzwpsIwHizJGmRrGO8vV_O8CkgwXuiJoohr5ItQMznLmAI5vKsVEIa8SZGt91XFT_rRTsSzlTMu51AIRw8lSHGjAAAIXW";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAccessToken() throws Exception {
		try {
			String s = TokenAPI.getAccessToken("wx07e34f9575809866","d8c5dae813951b0c31599c1a8aebf410"); //自己
			System.out.println(s);
			//i4NLqMizqTXaDLmD6m397hj1XTQfRFcRASb3HMi8TbLTvBS9wuKKvFf1fXWUB9mMjfw3fJNPwqoHIFTY3NVmDAgRJCz8Kx1tFEOxGmv3ydSkdCXCDZDZIdP695nUw0-bCHKiAHAWBE
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	@Test
	public void testGetjsToken() throws Exception {
		try {
			String s = TokenAPI.getJSTokenAll(accessToken).getToken(); 
			System.out.println(s);
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
