package com.xiaoshabao.webframework.ui.service.impl;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;

import util.SpringTest;

import com.xiaoshabao.webframework.ui.service.FormService;

public class FormServiceImplTest extends SpringTest{
	@Resource(name="formServiceImpl")
	FormService  formService;
	@Test
	public void testGetTemplateData() {
		try {
			String html=this.formService.getTemplateData("test000001").getHtml();
			System.out.println(html);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}

}
