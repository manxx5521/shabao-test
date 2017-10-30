package com.xiaoshabao.shabaotest.module.codec;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class MD5Test {
	@Test
	public void testCommon() {
		String md5 = DigestUtils.md5Hex("620105ai620105AI");
		System.out.println(md5);
	}

}
