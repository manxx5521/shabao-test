package com.xiaoshabao.baseframework.component.quartz.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzStatus;
import com.xiaoshabao.baseframework.component.quartz.core.dto.TaskInfoDto;

public class QuartzUtil {
  /**
   * 按先后排序（需要列表已经按startTaskId排序）
   */
  public static List<TaskInfoDto> sortList(List<TaskInfoDto> taskList) {
    List<TaskInfoDto> rs = new ArrayList<TaskInfoDto>(taskList.size());
    int size = 0;
    int taskId = 0;
    while (size == rs.size()) {

      List<Integer> tr = new ArrayList<Integer>();
      int count = 0;//相同任务数
      //查找相同startid的任务
      int endTaskId = 0;
      for (int i = 0; i < taskList.size(); i++) {
        TaskInfoDto task = taskList.get(i);
        if (task.getStartTaskId() == taskId) {
          rs.add(task);
          endTaskId = task.getEndTaskId();
          tr.add(size);
          size++;
          count++;
        }
      }
      //设置相同任务数
      for (TaskInfoDto task : rs) {
        task.setEqnum(count);
      }
      taskId = endTaskId;
    }
    return rs;
  }
  /**
   * 获得不重复的id
   */
  public synchronized static String getId(){
    String id=null;
    try {
      Thread.sleep(1000);
    } catch(Exception e){
      //不处理
    }finally{
      id= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
    return id;
  }
  /**
   * 调试信息
   */
  public static void debug(Integer workId, QuartzStatus status, String message) {

  }
  /**
   * 调试信息
   */
  public static void debug(String id,Integer workId, QuartzStatus status, String message) {

  }
  /**
   * work信息（每个work每次只有一条记录）
   */
  public static void infoWork(Integer workId, QuartzStatus status, String message) {
//    debug();
  }
  /**
   * 调试信息（每个task每次只有一条记录）
   */
  public static void infoTask(String id,Integer workId, QuartzStatus status, String message) {

  }
  
  

}
