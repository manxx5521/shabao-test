package com.xiaoshabao.vkan.service;

import com.xiaoshabao.webframework.dto.AjaxResult;

public interface FileManagerService {

	/**
	 * 新增项目
	 * @param projectName 测试项目
	 * @param filePath E:\test\test
	 * @return
	 */
	AjaxResult addProject(String projectName, String filePath);

	/**
	 * 设置项目标识
	 * @param fileId
	 * @param projectTag
	 * @return
	 */
	AjaxResult setProjectTag(Integer fileId, Boolean projectTag);

}
