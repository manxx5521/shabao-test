package com.xiaoshabao.wechat.api.wxaccount;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xiaoshabao.wechat.api.wxaccount.AccountAPI;
import com.xiaoshabao.wechat.api.wxaccount.result.QrcodeResult;
import com.xiaoshabao.wechat.api.wxbase.TokenAPITest;

public class AccountAPITest {
	private String accessToken=TokenAPITest.accessToken;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//生成二维码
	@Test
	public void testCreateQrcode() throws Exception {
		try {
			//临时
			QrcodeResult result=AccountAPI.createQrcode(accessToken, "100", null, AccountAPI.QRCODE_TYPE_SCENE, "2592000");
			System.out.println("临时二维码测试通过");
			System.out.println("临时二维码地址为: "+result.getQRurl());
			result=AccountAPI.createQrcode(accessToken, "100", "100", AccountAPI.QRCODE_TYPE_LIMIT, null);
			System.out.println("永久二维码测试通过");
			System.out.println("永久二维码地址为: "+result.getQRurl());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	//**生成短连接
	@Test
	public void testGetShortUrl() throws Exception {
		try {
			//临时
			String result=AccountAPI.getShortUrl(accessToken, "http://www.360.com");
			System.out.println("测试通过");
			System.out.println("短连接地址为: "+result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
