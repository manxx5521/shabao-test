package com.xiaoshabao.vkan.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.baseframework.util.UUIDGenerator;
import com.xiaoshabao.vkan.entity.FileEntity;
import com.xiaoshabao.vkan.service.FileManagerService;

@Service("fileManagerServiceImpl")
public class FileManagerServiceImpl extends AbstractServiceImpl implements FileManagerService {
  protected Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void initFiles(String parentPath) {
    initFiles(parentPath,UUIDGenerator.generate());
  }
  
  public void initFiles(String parentPath,String parentId) {
    if (StringUtils.isEmpty(parentPath)) {
      throw new MsgErrorException("更新文件时，没有获得父级目录！");
    }
    File file = new File(parentPath);
    if (!file.isDirectory()) {
      throw new MsgErrorException("更新文件时，父级目录不是文件夹！");
    }
    
    readFiles(file,parentId);
  }

  protected void readFiles(File parentFile,String parentId) {
    File[] files = parentFile.listFiles();
    for (File file : files) {
      if (file.isFile()) {
        insertFile(file,true,parentId);
      } else if (file.isDirectory()) {
        String fileId=insertFile(file,false,parentId);
        readFiles(file,fileId);
      }
    }
  }

  protected String insertFile(File file,boolean isFile,String parentId) {
    String fileId=UUIDGenerator.generate();
    String fileName = file.getName();
    String path = file.getAbsolutePath();
    String md5 = null;
    if(isFile){
      try {
        md5 = DigestUtils.md5Hex(new FileInputStream(file));
      } catch (IOException e) {
        logger.error("记录文件{}时出现错误", path, e);
      }
    }
    FileEntity fileEntity=new FileEntity();
    fileEntity.setFileId(fileId);
    fileEntity.setFileName(fileName);
    fileEntity.setPath(path);
    fileEntity.setMd5(md5);
    fileEntity.setParentId(parentId);
    FileEntity hasbean=this.baseDao.getDataSingle(FileEntity.class, fileEntity);
    if(hasbean==null){
      this.baseDao.insert(FileEntity.class, fileEntity);
      return fileId;
    }else{
      return hasbean.getFileId();
    }
  }

}
