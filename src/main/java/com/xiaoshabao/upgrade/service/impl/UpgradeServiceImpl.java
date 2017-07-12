package com.xiaoshabao.upgrade.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.upgrade.entity.UpgradeEntity;
import com.xiaoshabao.upgrade.service.UpgradeService;
import com.xiaoshabao.webframework.dto.AjaxResult;

public class UpgradeServiceImpl implements UpgradeService {
  protected Logger logger=LoggerFactory.getLogger(UpgradeServiceImpl.class);

  @Resource(name = "mybatisBaseDao")
  protected BaseDao baseDao;

  @Override
  public AjaxResult upgradeApplication(Integer upgradeId, String path) {
    UpgradeEntity upgradeEntity=this.baseDao.getDataById(UpgradeEntity.class, upgradeId);
    if(upgradeEntity==null){
      return new AjaxResult("未找到要升级的项目信息");
    }
    return null;
  }

}
