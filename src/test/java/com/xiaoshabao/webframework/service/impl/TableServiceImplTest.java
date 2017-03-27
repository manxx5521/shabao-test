package com.xiaoshabao.webframework.service.impl;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;

import util.SpringTest;

import com.xiaoshabao.webframework.service.TableService;

public class TableServiceImplTest extends SpringTest{
	@Resource(name="tableServiceImpl")
	private TableService TableService;

	@Test
	public void testGetTableInfo() {
		try {
			TableService.getTableInfo();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}

	@Test
	public void testGetTableDesc() {
		fail("Not yet implemented");
	}

}
