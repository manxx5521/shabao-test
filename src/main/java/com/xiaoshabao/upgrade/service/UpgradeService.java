package com.xiaoshabao.upgrade.service;

import com.xiaoshabao.webframework.dto.AjaxResult;

public interface UpgradeService {
  
  public AjaxResult upgradeApplication(Integer upgradeId,String path);
}
