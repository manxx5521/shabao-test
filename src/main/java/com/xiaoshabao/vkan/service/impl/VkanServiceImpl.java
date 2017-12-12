package com.xiaoshabao.vkan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.FilePagingParams;
import com.xiaoshabao.vkan.dto.VkanIndexDto;
import com.xiaoshabao.vkan.entity.FileTagEntity;
import com.xiaoshabao.vkan.entity.ProjectEntity;
import com.xiaoshabao.vkan.entity.TagEntity;
import com.xiaoshabao.vkan.service.VkanService;

@Service("vkanServiceImpl")
public class VkanServiceImpl extends AbstractServiceImpl implements VkanService {

	@Override
	public VkanIndexDto getIndexData(String[] tagIds, String search, String projectPrefix, Integer projectId) {
		VkanIndexDto result = new VkanIndexDto();

		List<ProjectEntity> projects = this.baseDao.getData(ProjectEntity.class, new HashMap<String, Object>());
		result.setProjectList(projects);

		List<String> prefixs = this.baseDao.getData("getProjectPrefix", new HashMap<String, Object>());
		result.setPrefixs(prefixs);

		if (projects != null && projects.size() > 0) {
			//是否是二次搜索
			if (projectId == null) {
				result.setProjectPrefix(projects.get(0).getPrjectPrefix());
				result.setProjectId(projects.get(0).getProjectId());
				result.setProjectName(projects.get(0).getProjectName());
			} else {
				for (ProjectEntity project : projects) {
					if (project.getProjectId().equals(projectId)) {
						result.setProjectPrefix(project.getPrjectPrefix());
						result.setProjectId(project.getProjectId());
						result.setProjectName(project.getProjectName());
					}
				}
			}

		}

		/*
		 * Map<String,Object> params=new HashMap<String,Object>(); params.put("tagIds",
		 * tagIds); params.put("search", search); List<FileDto>
		 * fileList=this.baseDao.getData(FileDto.class, params);
		 * result.setFileList(fileList);
		 */

		return result;
	}

	@Override
	public List<TagEntity> getTagList(Integer parentId) {
		TagEntity tagEntity = new TagEntity();
		tagEntity.setParentId(parentId);
		return this.baseDao.getData(TagEntity.class, tagEntity);
	}

	@Override
	public PageValue<FileDto> getFileDto(FilePagingParams params) {
		PageValue<FileDto> pageValue=this.getDataPaging(FileDto.class, params);
		List<FileDto> list=pageValue.getList();
		if(list!=null) {
			for(FileDto fileDto:list) {
				List<TagEntity> tagList=this.baseDao.getData("getTagByFileId", fileDto);
				fileDto.setTagList(tagList);
			}
		}
		
		return pageValue;
	}

	@Override
	public void setTagByParentId(Long parentId, String tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("tagId", tagId);
		this.baseDao.insert("insertTagByParentId", FileTagEntity.class, params);
	}

	@Override
	public void setTagById(Long fileId, String tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("tagId", tagId);
		this.baseDao.insert("insertTagById", FileTagEntity.class, params);

	}

	@Override
	public void deleteTagByParentId(Long parentId, String tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("tagId", tagId);
		this.baseDao.delete("deleteFileTagEntity", FileTagEntity.class, params);

	}

	@Override
	public void deleteTagById(Long fileId, String tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("tagId", tagId);
		this.baseDao.delete("deleteFileTagEntity", FileTagEntity.class, params);
	}

}
