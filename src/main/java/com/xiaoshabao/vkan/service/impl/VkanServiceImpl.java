package com.xiaoshabao.vkan.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.FilePagingParams;
import com.xiaoshabao.vkan.dto.IndexDataVo;
import com.xiaoshabao.vkan.dto.VkanIndexDto;
import com.xiaoshabao.vkan.entity.FileEntity;
import com.xiaoshabao.vkan.entity.ProjectEntity;
import com.xiaoshabao.vkan.entity.TagEntity;
import com.xiaoshabao.vkan.enums.FileType;
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
						prefix=projectEntity.getProjectPrefix();
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
		ProjectEntity projectReq=new ProjectEntity();
		List<ProjectEntity> projects = this.baseDao.getData(ProjectEntity.class, projectReq);
		result.setProjectList(projects);

//		Sqlmapper位置 VkanIndexDto.class 
		List<String> prefixs = this.baseDao.getData("getProjectPrefix",new HashMap<String, Object>());
		result.setPrefixs(prefixs);

		if (projects != null && projects.size() > 0) {
			// 是否是二次搜索
			if (indexData.getProjectId() == null) {
				result.setProjectPrefix(projects.get(0).getProjectPrefix());
				result.setProjectId(projects.get(0).getProjectId());
				result.setProjectName(projects.get(0).getProjectName());
				result.setProjectPath(projects.get(0).getProjectPath());
				result.setParentId(indexData.getParentId()==null?Long.valueOf(projects.get(0).getProjectId()):indexData.getParentId());
			} else {
				for (ProjectEntity project : projects) {
					if (project.getProjectId().equals(indexData.getProjectId())) {
						result.setProjectPrefix(project.getProjectPrefix());
						result.setProjectId(project.getProjectId());
						result.setProjectName(project.getProjectName());
						result.setProjectPath(project.getProjectPath());
						result.setParentId(indexData.getParentId()==null?Long.valueOf(project.getProjectId()):indexData.getParentId());
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
	public PageValue<FileDto> getFileDto(FilePagingParams params) {
		PageValue<FileDto> pageValue = this.getDataPaging(FileDto.class, params);
		List<FileDto> list = pageValue.getList();
		if (list != null) {
			for (FileDto fileDto : list) {
				//标签
				List<TagEntity> tagList = this.baseDao.getData("getlabelTag",TagEntity.class, fileDto);
				fileDto.setTagList(tagList);
				
				//设置封面
				Long coverId=fileDto.getCoverId();
				if(coverId!=null&&fileDto.getFileType()!=FileType.IMAGE.getCode()) {
					FileEntity coverReq=new FileEntity();
					coverReq.setFileId(coverId);
					FileEntity cover=this.baseDao.getDataForObject(FileEntity.class, coverReq);
					if(cover!=null&&cover.getPath()!=null) {
						fileDto.setCoverPath(cover.getPath());
					}
				}
			}
		}

		return pageValue;
	}
	
}
