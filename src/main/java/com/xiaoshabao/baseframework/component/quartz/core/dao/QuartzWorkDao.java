package com.xiaoshabao.baseframework.component.quartz.core.dao;

import java.util.List;

import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzWorkEntity;
import com.xiaoshabao.baseframework.component.quartz.core.dto.TaskInfoDto;

/**
 * work数据接口
 */
public interface QuartzWorkDao {
  /**
   * 获得所有work(要执行的)
   */
  public List<QuartzWorkEntity> getWorkList();
  
  /**
   * 获得任务列表
   */
  public List<TaskInfoDto> getTaskInfoList(Integer workId);
  
  public int updateWork(QuartzWorkEntity work);
  
}
