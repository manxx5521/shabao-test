package com.xiaoshabao.vkan.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.vkan.service.FileManagerService;
import com.xiaoshabao.webframework.dto.AjaxResult;
/**
 * 文件管理
 */
@Controller
@RequestMapping(value="/vkan/file")
public class FileManagerController {
	@Resource(name="fileManagerServiceImpl")
	private FileManagerService fileService;
	
	/**
	 * 插入项目文件
	 * @param projectName
	 * @param filePath
	 * @return
	 */
	@RequestMapping(value="/insert")
	@ResponseBody
	public AjaxResult addProject (String projectName,String filePath){
		if(StringUtils.isEmpty(projectName)||StringUtils.isEmpty(filePath)) {
			return new AjaxResult("项目名或项目路径不能为空");
		}
		return fileService.addProject(projectName, filePath);
	}
	
	/**
	 * 设置项目标识
	 * @param fileId
	 * @param projectTag
	 * @return
	 */
	@RequestMapping(value="/setProjectTag/{fileId}")
	@ResponseBody
	public AjaxResult setProjectTag(@PathVariable Integer fileId,Boolean projectTag) {
		if(projectTag==null) {
			projectTag=true;
		}
		return fileService.setProjectTag(fileId, projectTag);
	}

}
