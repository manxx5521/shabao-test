package com.xiaoshabao.vkan.service.impl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.xiaoshabao.vkan.service.FileManagerService;

import util.SpringTest;

public class FileManagerServiceImplTest extends SpringTest{
	@Resource(name="fileManagerServiceImpl")
	private FileManagerService fileService;

	@Test
	public void testAddProject() {
		try {
			this.fileService.addProject("测试项目", "E:\\test\\test");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}

}
