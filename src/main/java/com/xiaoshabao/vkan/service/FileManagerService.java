package com.xiaoshabao.vkan.service;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.webframework.dto.AjaxResult;

public interface FileManagerService extends AbstractService{

	/**
	 * 新增项目
	 * @param projectName 测试项目
	 * @param filePath E:\test\test
	 * @return
	 */
	AjaxResult addProject(String projectName, String filePath);
	
	/**
	 * 打开文件
	 * @param fileId
	 * @param prefixPath
	 * @param type type 1打开文件，2文件夹
	 * @return
	 */
	AjaxResult openFile(Long fileId,String prefixPath, Integer type);

	/**
	 * 设置项目标识
	 * @param fileId
	 * @param projectTag
	 * @return
	 */
	AjaxResult setProjectTag(Long fileId, Boolean projectTag);

}
