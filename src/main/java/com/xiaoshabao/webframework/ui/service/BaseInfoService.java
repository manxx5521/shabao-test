package com.xiaoshabao.webframework.ui.service;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BaseInfoDto;
import com.xiaoshabao.webframework.ui.dto.BaseInfoListDto;

public interface BaseInfoService {

  BaseInfoListDto getInfoView(String tableId);

  AjaxResult addBaseInfo(String tableId, BaseInfoDto baseInfo);
  
  
  AjaxResult updateBaseInfo(String tableId, BaseInfoDto baseInfo);

}
