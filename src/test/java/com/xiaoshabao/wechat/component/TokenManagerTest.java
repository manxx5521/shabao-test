package com.xiaoshabao.wechat.component;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;

import util.SpringTest;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.entity.AccessToken;

public class TokenManagerTest extends SpringTest{
	@Resource(name="tokenManager")
	TokenManager tokenManager;
	private Integer accountId=100001;
	@Test
	public void testGetToken() {
		try {
			AccessToken token=tokenManager.getToken(accountId);
			System.out.println("结果："+JSONObject.toJSONString(token));
		} catch (Exception e) {
			fail("测试未通过");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAccessToken() {
		try {
			AccessToken token=tokenManager.getAccessToken(accountId);
			System.out.println("结果："+JSONObject.toJSONString(token));
		} catch (Exception e) {
			fail("测试未通过");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetJSTokenInteger() {
		try {
			AccessToken token=tokenManager.getJSToken(accountId);
			System.out.println("结果："+JSONObject.toJSONString(token));
		} catch (Exception e) {
			fail("测试未通过");
			e.printStackTrace();
		}
	}

}
