package com.xiaoshabao.upgrade.service.impl;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.upgrade.entity.UpgradeEntity;
import com.xiaoshabao.upgrade.util.UpgradeConstants;
import com.xiaoshabao.upgrade.util.ZipUpgradeUtil;

/**
 * 本地直接升级服务端代码
 */
@Service("serverUpgradeService")
@Scope("prototype")
public class ServerUpgradeService extends UpgradeServiceImpl {
	@Override
	protected void unzip(UpgradeEntity upgradeEntity, String zipFilePath,
			String saveFileDir) {
		ZipUpgradeUtil.unzip(zipFilePath, saveFileDir);
	}

	@Override
	protected void existsDir(UpgradeEntity upgradeEntity) {
		if (StringUtils.isEmpty(upgradeEntity.getUpgradeFileName())) {
			throw new MsgErrorException("要升级的文件为空");
		}
		File file = new File(upgradeEntity.getServerPath() + File.separator
				+ upgradeEntity.getUpgradeFileName());
		if (!file.exists()) {
			throw new MsgErrorException("未找到要执行的文件");
		}

		// 检查解压文件目录
		File tempDir = new File(UpgradeConstants.getFileTempPath(
				upgradeEntity.getServerPath(),
				upgradeEntity.getUpgradeFileName()));
		if (tempDir.exists()) {
			tempDir.delete();
		}
		tempDir.mkdir();
	}

	@Override
	protected void deleteFileTmepDir(UpgradeEntity upgradeEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteFileTmep(UpgradeEntity upgradeEntity) {
		// TODO Auto-generated method stub
		
	}

  @Override
  protected void existsUpgradeFile(UpgradeEntity upgradeEntity) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void deleteServerFile(UpgradeEntity upgradeEntity, String path) {
    // TODO Auto-generated method stub
    
  }


  @Override
  protected void copyServerFileToFile(UpgradeEntity upgradeEntity, String srcPath, String destPath) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected String getSpecialConfigPath(UpgradeEntity upgradeEntity, String specialPath) {
    // TODO Auto-generated method stub
    return null;
  }


}
