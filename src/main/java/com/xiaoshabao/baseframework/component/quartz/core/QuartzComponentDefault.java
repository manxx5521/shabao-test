package com.xiaoshabao.baseframework.component.quartz.core;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.component.quartz.core.dao.QuartzWorkDao;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzConfig;
@Service("quartzComponent")
public class QuartzComponentDefault implements QuartzComponent{
  @Autowired
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
    return ApplicationContextUtil.getBean(name, classz);
  }


}
