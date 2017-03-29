package com.xiaoshabao.baseframework.component.quartz.core;

import java.util.Date;

import com.xiaoshabao.baseframework.component.quartz.core.dao.QuartzWorkDao;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzConfig;
public class QuartzComponentDefault implements QuartzComponent{

  private QuartzWorkDao dao;
  
  public void setDao(QuartzWorkDao dao) {
    this.dao = dao;
  }

  public QuartzWorkDao getDao() {
    return dao;
  }
  
  /**
   * 数据操作类
   */
  @Override
  public QuartzWorkDao getServiceDao(){
    return dao;
  }
  
  @Override
  public QuartzConfig getConfig(){
    QuartzConfig config=new QuartzConfig();
    config.setNowDate(new Date());
    return config;
  }

  @Override
  public <T> T getService(String name, Class<T> classz) {
    // TODO Auto-generated method stub
    return null;
  }


}
