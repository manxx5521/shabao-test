package com.xiaoshabao.wechat.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xiaoshabao.wechat.bean.TokenType;

public class TokenTypeTest {
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test(){
		System.out.println("---------");
		TokenType token=TokenType.JSTOKEN;
		System.out.println(token.getToken_name());
		TokenType token1=TokenType.ACCESSTOKEN;
		System.out.println(token1.getToken_name());
		
		TokenType token3=TokenType.JSTOKEN;
		System.out.println(token3.getToken_name());
		TokenType token4=TokenType.ACCESSTOKEN;
		System.out.println(token4.getToken_name());
		TokenType token5=TokenType.JSTOKEN;
		System.out.println(token5.getToken_name());
		TokenType token6=TokenType.ACCESSTOKEN;
		System.out.println(token6.getToken_name());
	}
	

}
