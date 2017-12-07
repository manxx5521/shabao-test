package com.xiaoshabao.vkan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.VkanIndexDto;
import com.xiaoshabao.vkan.entity.FileTagEntity;
import com.xiaoshabao.vkan.entity.ProjectEntity;
import com.xiaoshabao.vkan.entity.TagEntity;
import com.xiaoshabao.vkan.service.VkanService;
@Service("vkanServiceImpl")
public class VkanServiceImpl extends AbstractServiceImpl implements VkanService{

	@Override
	public VkanIndexDto getIndexData(String[] tagIds,String search) {
		VkanIndexDto result=new VkanIndexDto();
		
		List<ProjectEntity> projects=this.baseDao.getData(ProjectEntity.class, new HashMap<String,Object>());
		result.setProjectList(projects);
		
		List<TagEntity> tagList=this.baseDao.getData(TagEntity.class,null);
		result.setTagList(tagList);
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("tagIds", tagIds);
		params.put("search", search);
		List<FileDto> fileList=this.baseDao.getData(FileDto.class, params);
		result.setFileList(fileList);
		
		return result;
	}

	@Override
	public List<FileDto> getFileDto(String parentId) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("parentId", parentId);
		return this.baseDao.getData(FileDto.class, params);
	}

	@Override
	public void setTagByParentId(Long parentId,String tagId) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("parentId", parentId);
		params.put("tagId", tagId);
		this.baseDao.insert("insertTagByParentId", FileTagEntity.class, params);
	}

	@Override
	public void setTagById(Long fileId,String tagId) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("fileId", fileId);
		params.put("tagId", tagId);
		this.baseDao.insert("insertTagById", FileTagEntity.class, params);
		
	}

	@Override
	public void deleteTagByParentId(Long parentId, String tagId) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("parentId", parentId);
		params.put("tagId", tagId);
		this.baseDao.delete("",FileTagEntity.class, params);
		
	}

	@Override
	public void deleteTagById(Long fileId, String tagId) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("fileId", fileId);
		params.put("tagId", tagId);
		this.baseDao.delete("insertTagById", FileTagEntity.class, params);		
	}

}
