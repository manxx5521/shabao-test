package com.xiaoshabao.vkan.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.baseframework.util.SnowflakeUtil;
import com.xiaoshabao.vkan.entity.FileEntity;
import com.xiaoshabao.vkan.entity.ProjectEntity;
import com.xiaoshabao.vkan.enums.FileType;
import com.xiaoshabao.vkan.enums.ReaderFileType;
import com.xiaoshabao.vkan.service.FileManagerService;
import com.xiaoshabao.webframework.dto.AjaxResult;

@Service("fileManagerServiceImpl")
public class FileManagerServiceImpl extends AbstractServiceImpl implements FileManagerService {

	// 新增项目
	@Override
	@Transactional
	public AjaxResult addProject(String projectName, String filePath) {
		if (!filePath.endsWith(File.separator)) {
			filePath = filePath + File.separator;
		}
		File file = new File(filePath);
		if (!file.isDirectory()) {
			throw new MsgErrorException("添加项目时错误，父级目录不是文件夹！");
		}
		// 统一名字
		filePath = file.getAbsolutePath();
		if (!filePath.endsWith(File.separator)) {
			filePath = filePath + File.separator;
		}
		String prefix = FilenameUtils.getPrefix(filePath);
		String path = FilenameUtils.getPath(filePath);

		ProjectEntity project = new ProjectEntity();
		project.setProjectName(projectName);
		project.setProjectPrefix(prefix);
		project.setProjectPath(path);
		/*
		ProjectEntity temp=this.baseDao.getDataSingle(ProjectEntity.class, project);
		if(temp!=null) {
			throw new MsgErrorException("当前项目已经存在");
		}*/
		int i = this.baseDao.insert(ProjectEntity.class, project);
		Integer projectId = project.getProjectId();
		if (i < 1 || projectId == null) {
			throw new MsgErrorException("项目信息插入失败");
		}

		readParserFiles(project, projectId.longValue(), file, ReaderFileType.ADD);
		return new AjaxResult(true,"导入项目成功！");
	}
	
	//设置项目标识
	@Override
	public AjaxResult setProjectTag(Integer fileId, Boolean projectTag) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("fileId", fileId);
		params.put("projectTag", projectTag?1:0);
		this.baseDao.update("setProjectTag",FileEntity.class, params);
		return new AjaxResult(true,"更新成功");
	}

	/**
	 * 读取文件入口-递归解析文件
	 * 
	 * @param projectId
	 * @param parentFile
	 * @param parentId
	 */
	private void readParserFiles(ProjectEntity project, Long parentId, File parentFile, ReaderFileType type) {
		File[] files = parentFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				operFile(project, file, true, parentId, type);
			} else if (file.isDirectory()) {
				Long fileId = operFile(project, file, false, parentId, type);
				readParserFiles(project, fileId, file, type);
			}
		}
	}

	/**
	 * 根据不同的操作，进行文件处理
	 * 
	 * @param file
	 * @param isFile
	 *            是否是文件
	 * @param parentId
	 * @return
	 */
	private Long operFile(ProjectEntity project, File file, boolean isFile, Long parentId, ReaderFileType type) {
		FileEntity fileEntity = new FileEntity();
		String fileName = file.getName();
		String path = file.getAbsolutePath();
		path=path.replace(project.getProjectPrefix() + project.getProjectPath(), "");
		fileEntity.setProjectId(project.getProjectId());
		fileEntity.setFileName(fileName);
		fileEntity.setPath(path);
		
		switch (type) {
		case UPDATE:
			//如果存在 跳出
			FileEntity temp=this.baseDao.getDataForObject(FileEntity.class, fileEntity);
			if(temp!=null&&temp.getFileId()!=null) {
				return temp.getFileId();
			}
		case ADD:
			String md5 = null;
			int fileTypeCode = 9;
			if (isFile) {
				try (InputStream inputStream = new FileInputStream(file)) {
					md5 = DigestUtils.md5Hex(inputStream);
				} catch (IOException e) {
					logger.error("记录文件{}时出现错误", path, e);
				}
				fileTypeCode = FileType.getCodeByName(file.getName());
			} else {
				fileTypeCode = FileType.DIRECTORY.getCode();
			}
			
			long fileId = SnowflakeUtil.nextId();
			fileEntity.setFileId(fileId);
			fileEntity.setMd5(md5);
			fileEntity.setFileType(fileTypeCode);
			fileEntity.setParentId(parentId);

			this.baseDao.insert(FileEntity.class, fileEntity);
			return fileId;
		default:
			throw new MsgErrorException("读取类型错误");
		}
	}

	

}
