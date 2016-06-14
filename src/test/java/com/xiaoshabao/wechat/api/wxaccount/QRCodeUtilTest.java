package com.xiaoshabao.wechat.api.wxaccount;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.xiaoshabao.wechat.api.wxaccount.QRCodeUtil;

public class QRCodeUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//生成二维码
	@Test
	public void testEncode() {
		String filePostfix="png";
		File file = new File("E://test//test_QR_CODE."+filePostfix);
		QRCodeUtil.encode("http://www.baidu.com", file,filePostfix, BarcodeFormat.QR_CODE, 500, 500, null);
	}


	@Test
	public void testDecode() {
		File file = new File("E://test//test_QR_CODE.png");
		QRCodeUtil.decode(file);
	}

}
