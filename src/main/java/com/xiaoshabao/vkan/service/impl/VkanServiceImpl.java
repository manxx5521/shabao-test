package com.xiaoshabao.vkan.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.FilePagingParams;
import com.xiaoshabao.vkan.dto.IndexDataVo;
import com.xiaoshabao.vkan.dto.VkanIndexDto;
import com.xiaoshabao.vkan.entity.FileEntity;
import com.xiaoshabao.vkan.entity.FileTagEntity;
import com.xiaoshabao.vkan.entity.ProjectEntity;
import com.xiaoshabao.vkan.entity.TagEntity;
import com.xiaoshabao.vkan.service.VkanService;

@Service("vkanServiceImpl")
public class VkanServiceImpl extends AbstractServiceImpl implements VkanService {

	@Override
	public VkanIndexDto getIndexData(IndexDataVo indexData) {
		VkanIndexDto result = new VkanIndexDto();

		// 如果传入父级id
		if (indexData.getParentId() != null) {
			FileEntity req = new FileEntity();
			req.setFileId(indexData.getParentId());
			FileEntity fileEntity = this.baseDao.getDataForObject(FileEntity.class, req);
			if (fileEntity != null) {
				ProjectEntity projectReq = new ProjectEntity();
				projectReq.setProjectId(fileEntity.getProjectId());
				ProjectEntity projectEntity = this.baseDao.getDataForObject(ProjectEntity.class, projectReq);
				if (projectEntity != null) {
					// 所有信息都能查到
					String prefix=indexData.getProjectPrefix();
					if(StringUtils.isEmpty(prefix)) {
						prefix=projectEntity.getPrjectPrefix();
					}
					String path =prefix+  projectEntity.getProjectPath() + fileEntity.getPath();
					File file = new File(path);
					if (file != null) {
						//刷新需要的值
						indexData.setProjectPrefix(prefix);
						indexData.setProjectId(projectReq.getProjectId());
					}
				}
			}
		}

		//获取主页数据
		List<ProjectEntity> projects = this.baseDao.getData(ProjectEntity.class, new HashMap<String, Object>());
		result.setProjectList(projects);

//		Sqlmapper位置 VkanIndexDto.class
		List<String> prefixs = this.baseDao.getData("getProjectPrefix",new HashMap<String, Object>());
		result.setPrefixs(prefixs);

		if (projects != null && projects.size() > 0) {
			// 是否是二次搜索
			if (indexData.getProjectId() == null) {
				result.setProjectPrefix(projects.get(0).getPrjectPrefix());
				result.setProjectId(projects.get(0).getProjectId());
				result.setProjectName(projects.get(0).getProjectName());
				result.setProjectPath(projects.get(0).getProjectPath());
				result.setParentId(projects.get(0).getProjectId());
			} else {
				for (ProjectEntity project : projects) {
					if (project.getProjectId().equals(indexData.getProjectId())) {
						result.setProjectPrefix(project.getPrjectPrefix());
						result.setProjectId(project.getProjectId());
						result.setProjectName(project.getProjectName());
						result.setProjectPath(project.getProjectPath());
						result.setParentId(project.getProjectId());
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
		PageValue<FileDto> pageValue = this.getDataPaging(FileDto.class, params);
		List<FileDto> list = pageValue.getList();
		if (list != null) {
			for (FileDto fileDto : list) {
				List<TagEntity> tagList = this.baseDao.getData("getTagByFileId", fileDto);
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
