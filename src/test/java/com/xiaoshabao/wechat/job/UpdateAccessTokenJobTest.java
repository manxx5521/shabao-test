package com.xiaoshabao.wechat.job;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import util.SpringTest;

public class UpdateAccessTokenJobTest extends SpringTest{
	@Autowired
	UpdateAccessTokenJob updateAccessTokenJob;
	@Test
	public void testWork() {
		try {
			updateAccessTokenJob.work();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
		
	}

}
