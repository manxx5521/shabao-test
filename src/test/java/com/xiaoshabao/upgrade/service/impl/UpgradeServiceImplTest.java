package com.xiaoshabao.upgrade.service.impl;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;

import util.SpringTest;

import com.xiaoshabao.upgrade.service.UpgradeService;

public class UpgradeServiceImplTest extends SpringTest{
	
	@Resource(name="upgradeServiceImpl")
	private UpgradeService upgradeService;

	@Test
	public void testUpgradeApplication() {
		try {
			upgradeService.upgradeApplication(1001, "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		fail("Not yet implemented");
	}

	@Test
	public void testGetUpgradeList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLogList() {
		fail("Not yet implemented");
	}

}
