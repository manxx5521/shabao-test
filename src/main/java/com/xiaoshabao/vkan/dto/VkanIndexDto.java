package com.xiaoshabao.vkan.dto;

import java.util.List;

import com.xiaoshabao.vkan.entity.ProjectEntity;
import com.xiaoshabao.vkan.entity.TagEntity;

public class VkanIndexDto {
	
	private List<ProjectEntity> projectList;
	/**标签 */
	private List<TagEntity> tagList;
	/**文件列表**/
	private List<FileDto> fileList;
	
	public List<ProjectEntity> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<ProjectEntity> projectList) {
		this.projectList = projectList;
	}
	public List<TagEntity> getTagList() {
		return tagList;
	}
	public void setTagList(List<TagEntity> tagList) {
		this.tagList = tagList;
	}
	public List<FileDto> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileDto> fileList) {
		this.fileList = fileList;
	}
	
	
}
