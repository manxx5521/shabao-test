package com.xiaoshabao.webframe.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import util.ServiceTest;

import com.xiaoshabao.webframework.service.TreeService;

public class TreeServiceImplTest extends ServiceTest{
	
	@Resource
	TreeService treeService;
	@Test
	public void testGetJSTreeList() {
		treeService.getJSTreeList("100000");
	}

}
