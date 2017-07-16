package com.xiaoshabao.upgrade.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.upgrade.entity.UpgradeEntity;
import com.xiaoshabao.upgrade.service.UpgradeService;
import com.xiaoshabao.upgrade.util.UpgradeConstants;
import com.xiaoshabao.webframework.dto.AjaxResult;

public abstract class UpgradeServiceImpl implements UpgradeService {
	protected Logger logger = LoggerFactory.getLogger(UpgradeServiceImpl.class);

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;

	@Override
	public AjaxResult upgradeApplication(Integer upgradeId, String path) {
		UpgradeEntity upgradeEntity = this.baseDao.getDataById(
				UpgradeEntity.class, upgradeId);
		if (upgradeEntity == null) {
			return new AjaxResult("未找到要升级的项目信息");
		}
		existsDir(upgradeEntity);// 检查文件夹是否存在
		unzip(upgradeEntity, UpgradeConstants.getFilePath(upgradeEntity.getServerPath()),
				UpgradeConstants.getFileTempPath(upgradeEntity.getServerPath(),
						upgradeEntity.getUpgradeFileName()));// 解压文件

		return null;
	}


	/**
	 * 判断所需目录是否存在
	 * @param upgradeEntity
	 */
	protected abstract void existsDir(UpgradeEntity upgradeEntity);

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
