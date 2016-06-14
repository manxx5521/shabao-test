package com.xiaoshabao.wechat.api.wxbase;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.wxbase.ServiceIpAPI;


public class ServiceIpAPITest {
	private String accessToken=TokenAPITest.accessToken;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetServiceIpList() {
		try {
			List<String> s = ServiceIpAPI.getServiceIpList(accessToken);
			System.out.println(s);
		} catch (WexinReqException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
