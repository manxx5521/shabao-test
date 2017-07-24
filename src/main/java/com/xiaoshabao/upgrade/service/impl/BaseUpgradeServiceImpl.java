package com.xiaoshabao.upgrade.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.upgrade.entity.UpgradeEntity;
import com.xiaoshabao.upgrade.service.UpgradeService;
import com.xiaoshabao.upgrade.util.UpgradeConstants;
import com.xiaoshabao.webframework.dto.AjaxResult;

public abstract class BaseUpgradeServiceImpl implements UpgradeService {
  protected Logger logger = LoggerFactory.getLogger(BaseUpgradeServiceImpl.class);

  @Resource(name = "mybatisBaseDao")
  protected BaseDao baseDao;

  @Override
  public AjaxResult upgradeApplication(Integer upgradeId) {
    UpgradeEntity upgradeEntity=getUpgradeEntity(upgradeId);
    return upgradeApplication(upgradeId,upgradeEntity);
  }
  
  protected AjaxResult upgradeApplication(Integer upgradeId,UpgradeEntity upgradeEntity) {
    existsDir(upgradeEntity);// 检查文件夹是否存在
    existsUpgradeFile(upgradeEntity);// 检查升级文件是否再temp目录
    deleteFileTmepDir(upgradeEntity);// 删除解压的错误文件
    unzip(upgradeEntity, UpgradeConstants.getFilePath(upgradeEntity.getServerPath()),
        UpgradeConstants.getFileTempPath(upgradeEntity.getServerPath(),
            upgradeEntity.getUpgradeFileName()));// 解压文件
    
    overrideFiles(upgradeEntity);

    exeSql(upgradeEntity);
    
    clearFileTmep(upgradeEntity);
    return new AjaxResult(true,"成功执行");
  }
  /**
   * 执行sql
   */
  protected void exeSql(UpgradeEntity upgradeEntity) {
    String sqlPath = UpgradeConstants.getSqlFilePath(upgradeEntity
        .getServerPath());
    File sqlFileRoot = new File(sqlPath);
    if (!sqlFileRoot.exists()) {
      
    }

  }
  /**
   * 覆盖文件
   */
  protected void overrideFiles(UpgradeEntity upgradeEntity){
    try {
      File configFile=new File(getSpecialConfigPath(upgradeEntity, UpgradeConstants.SPECIAL_CONFIG_PATH));
      if(configFile.exists()){
        String zipDir=UpgradeConstants.getFileTempPath(upgradeEntity.getServerPath(),
          upgradeEntity.getUpgradeFileName())+UpgradeConstants.separator+FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName());
        SAXReader reader = new SAXReader();
        InputStream is = new FileInputStream(configFile);
        Document document = reader.read(is);
        Element root = document.getRootElement();
        Iterator<?> it = root.elementIterator();
        while (it.hasNext()) {
          Element element = (Element) it.next();
          if(UpgradeConstants.XML_EXCLUDE_NAME.equals(element.getName())){
            String src=element.attributeValue(UpgradeConstants.XML_ATTR_SRC);
            if(StringUtils.isNotEmpty(src)){
              deleteServerFile(upgradeEntity,zipDir+src);
            }
            
          }
          
          if(UpgradeConstants.XML_OVERRIDE_NAME.equals(element.getName())){
            String src=element.attributeValue(UpgradeConstants.XML_ATTR_SRC);
            if(StringUtils.isNotEmpty(src)){
              String to=element.attributeValue(UpgradeConstants.XML_ATTR_TO);
              copyServerFileToFile(upgradeEntity, zipDir+src, zipDir+to);
            }
          }
        }
      }
    } catch (Exception e) {
      throw new MsgErrorException("解析特殊文件时出现异常",e);
    }
  }

  
  protected UpgradeEntity getUpgradeEntity(Integer upgradeId){
    UpgradeEntity upgradeEntity = this.baseDao.getDataById(
      UpgradeEntity.class, upgradeId);
    if (upgradeEntity == null) {
      throw new MsgErrorException("未找到要升级的项目信息");
    }
    return upgradeEntity;
  }

  /**
   * 获得特殊文件配置
   * @param specialPath 特殊文件目录
   * @return
   */
  protected abstract String getSpecialConfigPath(UpgradeEntity upgradeEntity,String specialPath);
  /**
   * 判断所需目录是否存在
   * @param upgradeEntity
   */
  protected abstract void existsDir(UpgradeEntity upgradeEntity);
  
  /**
   * 判断所需升级文件是否存在
   * @param upgradeEntity
   */
  protected abstract void existsUpgradeFile(UpgradeEntity upgradeEntity);

  /**
   * 把zip文件解压到指定的文件夹
   * 
   * @param zipFilePath
   *            zip文件路径, 如 "D:/test/aa.zip"
   * @param saveFileDir
   *            解压后的文件存放路径, 如"D:/test/" ()
   */
  protected abstract void unzip(UpgradeEntity upgradeEntity,
      String zipFilePath, String saveFileDir);
  
  /**
   * 删除文件临时目录
   * @param upgradeEntity
   */
  protected abstract void deleteFileTmepDir(UpgradeEntity upgradeEntity);
  
  /**
   * 清空文件文件
   * @param upgradeEntity
   */
  protected void clearFileTmep(UpgradeEntity upgradeEntity){
    deleteFileTmepDir(upgradeEntity);//删除解压目录
  }
  
  /**
   * 删除文件临时
   * @param upgradeEntity
   */
  protected abstract void deleteFileTmep(UpgradeEntity upgradeEntity);
  
  /**
   * 删除服务端文件
   * @param upgradeEntity
   * @param zipDir 解压目录
   * @param path 目录相对于 解压目录
   */
  protected abstract void deleteServerFile(UpgradeEntity upgradeEntity,String path);
  
  
  protected abstract void copyServerFileToFile(UpgradeEntity upgradeEntity,String srcPath, String destPath);
  
  @Override
  public AjaxResult getUpgradeList(Integer upgradeId, String path) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AjaxResult getLogList(Integer upgradeId) {
    // TODO Auto-generated method stub
    return null;
  }

}
