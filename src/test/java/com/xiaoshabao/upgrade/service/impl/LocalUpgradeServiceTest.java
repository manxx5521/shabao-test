package com.xiaoshabao.upgrade.service.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.xiaoshabao.upgrade.service.UpgradeService;

import util.SpringTest;

public class LocalUpgradeServiceTest extends SpringTest {

	@Resource(name = "localUpgradeService")
	private UpgradeService upgradeService;

	@Test
	public void testUpgradeApplication() {
		try {
			upgradeService.upgradeApplication(1001, "");
		} catch (Exception e) {
			e.printStackTrace();
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
