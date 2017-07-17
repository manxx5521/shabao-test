package com.xiaoshabao.upgrade.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
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
import com.xiaoshabao.webframework.util.ResourceUtil;

public abstract class UpgradeServiceImpl implements UpgradeService {
	protected Logger logger = LoggerFactory.getLogger(UpgradeServiceImpl.class);

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

    exeSql();
    return null;
  }
	
	/**
	 * 执行sql
	 */
	protected void exeSql(){
	  
	}
	/**
	 * 覆盖文件
	 */
	protected void overrideFiles(UpgradeEntity upgradeEntity){
	  try {
	    File configFile=new File(UpgradeConstants.getSpecialConfigPath(upgradeEntity.getServerPath()));
	    if(configFile.exists()){
	      SAXReader reader = new SAXReader();
	      InputStream is = new FileInputStream(configFile);
	      Document document = reader.read(is);
	      Element root = document.getRootElement();
	      Iterator it = root.elementIterator();
	      while (it.hasNext()) {
	        Element element = (Element) it.next();
	        if(element.getName().equals("resource")){
	          
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
