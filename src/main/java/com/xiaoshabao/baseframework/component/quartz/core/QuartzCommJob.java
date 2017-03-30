package com.xiaoshabao.baseframework.component.quartz.core;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.component.quartz.core.dao.QuartzWorkDao;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzStatus;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzTaskType;
import com.xiaoshabao.baseframework.component.quartz.core.dto.TaskEntity;
import com.xiaoshabao.baseframework.component.quartz.core.dto.TaskInfoDto;

public class QuartzCommJob implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    Integer workId =null;
    String id=null;
    try {
      //获取参数
      //    JobKey key = context.getJobDetail().getKey();
      id=QuartzUtil.getId();
      JobDataMap dataMap = context.getJobDetail().getJobDataMap();
      workId = dataMap.getInt("workId");
      QuartzComponent quartzComponent =ApplicationContextUtil.getBean("quartzComponent", QuartzComponent.class);
      QuartzWorkDao dao = quartzComponent.getServiceDao();
      List<TaskInfoDto> taskList = dao.getTaskInfoList(workId);

      if (taskList == null || taskList.size() < 1) {
        QuartzUtil.infoTask(id,workId, QuartzStatus.STATUS_40, "未找到task任务");
        return;
      }
      taskList = QuartzUtil.sortList(taskList);
      int startId = -1;
      int size = 0;
      CountDownLatch countDownLatch = null;
      for (TaskInfoDto task : taskList) {
        if (task.getStartTaskId() != startId) {
          countDownLatch = new CountDownLatch(task.getEqnum());
          startId = task.getStartTaskId();
          size = task.getEqnum();
        }
        TaskWorkThread thread = new TaskWorkThread(quartzComponent, task, countDownLatch);
        thread.start();
        size--;
        if (size == 0) {
          countDownLatch.await();
        }
      }
    } catch (Exception e) {
      QuartzUtil.infoTask(id,workId, QuartzStatus.STATUS_40, "未找到task任务");
    }
  }

  /**
   * 子线程用来启动任务
   */
  public static class TaskWorkThread extends Thread {

    private CountDownLatch countDownLatch;

    private QuartzComponent quartzComponent;

    private TaskInfoDto taskInfo;

    public TaskWorkThread(QuartzComponent quartzComponent, TaskInfoDto taskInfo, CountDownLatch countDownLatch) {
      this.countDownLatch = countDownLatch;
      this.quartzComponent = quartzComponent;
      this.taskInfo = taskInfo;
    }

    @Override
    public void run() {
      try {
        TaskEntity task = taskInfo.getTask();
        int taskType = task.getTaskType();
        if (taskType == QuartzTaskType.PROCEDURE) {

        } else if (taskType == QuartzTaskType.JAVA) {
          QuartzService service = quartzComponent.getService(task.getContent(), QuartzService.class);
          service.execute();
        } else {
          //暂无处理
          QuartzUtil.debug(taskInfo.getWorkId(), QuartzStatus.STATUS_40, "task任务" + task.getTaskId() + "任务类型陪你错误，找不到类型");
        }
      } finally {
        countDownLatch.countDown();
      }

    }
    

  }
  
  public class QTException{
    
  }

}
