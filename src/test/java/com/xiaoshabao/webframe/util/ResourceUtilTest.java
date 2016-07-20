package com.xiaoshabao.webframe.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceUtilTest {

	@Test
	public void testInitConfigStringString() {
		try {
			ResourceUtil.initConfig("resource-congfig.xml","http:/13222","1");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
