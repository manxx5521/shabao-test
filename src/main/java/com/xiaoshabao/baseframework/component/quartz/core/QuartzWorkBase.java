package com.xiaoshabao.baseframework.component.quartz.core;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.xiaoshabao.baseframework.component.quartz.core.dao.QuartzWorkDao;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzConfig;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzStatus;
import com.xiaoshabao.baseframework.component.quartz.core.dto.QuartzWorkEntity;

/**
 * 调度工作
 */
public class QuartzWorkBase   implements ApplicationListener<ApplicationEvent>{
  @Resource(name = "quartzComponent")
  private QuartzComponent quartzComponent;
  
  /**
   * 启动后执行
   */
  @Override
  public void onApplicationEvent(ApplicationEvent arg0) {
    init();
  }
  /**
   * 初始化方法
   */
  public void init() {
    try {
      QuartzWorkDao dao = quartzComponent.getServiceDao();
      List<QuartzWorkEntity> works = dao.getWorkList();
      //如果是空表返回
      if (works == null || works.isEmpty()) {
        QuartzUtil.debug(0, QuartzStatus.STATUS_0, "任务列表为空，没有任何任务可执行！");
        return;
      }

      //初始化配置
      QuartzConfig config = quartzComponent.getConfig();

      SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

      Scheduler sched = schedFact.getScheduler();

      sched.start();

      for (QuartzWorkEntity work : works) {
        runWork(sched, dao, work, config);
      }
    } catch (Exception e) {
      QuartzUtil.debug(0, QuartzStatus.STATUS_40, "创建定时任务错误，主程序未能创建！");
    }
  }

  private void runWork(Scheduler sched, QuartzWorkDao dao, QuartzWorkEntity work, QuartzConfig config) {
    try {
      //任务已经过期
      if (work.getEndTime().before(config.getNowDate())) {
        work.setStatus(QuartzStatus.STATUS_L.getCode());
        dao.updateWork(work);
        QuartzUtil.debug(work.getWorkId(), QuartzStatus.STATUS_L, "任务过期，不再执行。");
        return;
      }
      JobDetail job = JobBuilder.newJob(QuartzCommJob.class).withIdentity("Job" + work.getWorkId(), work.getSysType())
        .usingJobData("workId", work.getWorkId()) //传入JobDataMap 传入job用的参数
        .build();
      int exeNum = work.getExeNum();
      ScheduleBuilder<?> scheduleBuilder = null;
      if (exeNum > 0) {
//        int month=work.getExpMonth()==null?0:work.getExpMonth();
        int day=work.getExpDay()==null?0:work.getExpDay();
        int hours=work.getExpHours()==null?0:work.getExpHours();
        int minutes=work.getExpMinutes()==null?0:work.getExpMinutes();
        int seconds=work.getExpSeconds()==null?0:work.getExpSeconds();
        
        int number=seconds+minutes*60+hours*60*60+day*24*60*60;
        scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(exeNum)
          .withIntervalInSeconds(number).repeatForever();
      } else {
        scheduleBuilder = CronScheduleBuilder.cronSchedule(work.getExpression());
//        CronExpression b=new CronExpression(cronExpression)
      }
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + work.getWorkId(), work.getSysType())
        .startAt(work.getStartTime()).endAt(work.getEndTime()).withSchedule(scheduleBuilder).startNow().build();
      sched.scheduleJob(job, trigger);
    } catch (Exception e) {
      QuartzUtil.debug(work.getWorkId(), QuartzStatus.STATUS_40, "任务装载时错误，无法正常执行！！！");
    }
  }

}
