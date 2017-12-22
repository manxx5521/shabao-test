package com.xiaoshabao.vkan.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.vkan.service.FileManagerService;
import com.xiaoshabao.webframework.controller.AbstractController;
import com.xiaoshabao.webframework.dto.AjaxResult;
/**
 * 文件管理
 */
@Controller
@RequestMapping(value="/vkan/file")
public class FileManagerController extends AbstractController{
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
	 * 打开文件
	 * @param fileId
	 * @param prefixPath
	 * @param type 1打开文件，2文件夹
	 * @return
	 */
	@RequestMapping(value="/openFile")
	@ResponseBody
	public AjaxResult openFile(@RequestParam Long fileId,@RequestParam String prefixPath,@RequestParam Integer type) {
		
		return this.fileService.openFile(fileId, prefixPath, type);
	}
	
	/**
	 * 设置项目标识
	 * @param fileId
	 * @param projectTag
	 * @return
	 */
	@RequestMapping(value="/setProjectTag/{fileId}")
	@ResponseBody
	public AjaxResult setProjectTag(@PathVariable Long fileId,Boolean projectTag) {
		if(projectTag==null) {
			projectTag=true;
		}
		return fileService.setProjectTag(fileId, projectTag);
	}
	
	/**
	 * 设置为封面
	 * @param fileId
	 * @param projectTag
	 * @return
	 */
	@RequestMapping(value="/setFileCover")
	@ResponseBody
	public AjaxResult setFileCover(@PathVariable Long fileId) {
//		this.fileService.update(clasz, t, p)
		return null;
	}

}
