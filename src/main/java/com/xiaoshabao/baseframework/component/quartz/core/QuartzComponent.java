package com.xiaoshabao.baseframework.component.quartz.core;

import com.xiaoshabao.baseframework.component.quartz.core.dao.QuartzWorkDao;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzConfig;
public interface QuartzComponent {
  
  /**
   * 数据操作类
   */
  public QuartzWorkDao getServiceDao();
  
  public QuartzConfig getConfig();
  
  public <T> T getService(String name,Class<T> classz);
  
}
