package com.xiaoshabao.webframework.ui.service.impl;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;

import util.SpringTest;

import com.xiaoshabao.webframework.ui.service.WebElementService;

public class WebElementServiceImplTest extends SpringTest{
	@Resource(name="webElementServiceImpl")
	private WebElementService webElementService;

	@Test
	public void testGetSelectValues() {
		try {
			webElementService.getSelectValues(100002);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
