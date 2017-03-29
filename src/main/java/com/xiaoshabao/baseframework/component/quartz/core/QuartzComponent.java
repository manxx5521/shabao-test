package com.xiaoshabao.baseframework.component.quartz.core;

import com.xiaoshabao.baseframework.component.quartz.core.dao.QuartzWorkDao;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzConfig;
/**
 * Quartz组件配置
 * @author manxx
 * @date 2017年3月29日 下午4:46:21
 */
public interface QuartzComponent {
  
  /**
   * 数据操作类
   */
  public QuartzWorkDao getServiceDao();
  
  public QuartzConfig getConfig();
  
  public <T> T getService(String name,Class<T> classz);
  
}
