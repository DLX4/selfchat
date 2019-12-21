//package pers.dlx.selfchat.task;
//
//import java.util.Date;
//
//import pers.dlx.selfchat.constants.Consts;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//
//import pers.dlx.selfchat.utils.SpringUtil;
//
///**
// * 定时轮询任务调度器
// *
// * @author dinglingxiang
// *
// */
//@Component
//@EnableScheduling
//public class TaskScheduler implements SchedulingConfigurer {
//
//	private final static Logger logger = LoggerFactory.getLogger(TaskScheduler.class);
//
//
//	@Override
//	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//
//		Runnable task = new Runnable() {
//
//			@Override
//			public void run() {
//				Object object = SpringUtil.getBean(Consts.DEFAULT_EXECUTOR);
//				if (object instanceof VisiableThreadPoolTaskExecutor) {
//					VisiableThreadPoolTaskExecutor executor = (VisiableThreadPoolTaskExecutor) object;
//					if (executor.getActiveCount() == 0) {
//						logger.info("任务提交=======>");
//
//						logger.info("任务提交完毕<=======");
//					} else {
//						logger.info("线程池中已有任务执行，跳过本次");
//					}
//				}
//
//			}
//		};
//
//		Trigger trigger = new Trigger() {
//			@Override
//			public Date nextExecutionTime(TriggerContext triggerContext) {
//				// 任务触发，可修改任务的执行周期.
//				// 每一次任务触发，都会执行这里的方法一次，重新获取下一次的执行时间
//				String cron = Consts.DEFAULT_CRON;
//
//				logger.info("cron: " + cron);
//
//				CronTrigger trigger = new CronTrigger(cron);
//				Date nextExec = trigger.nextExecutionTime(triggerContext);
//				return nextExec;
//			}
//		};
//
//		taskRegistrar.addTriggerTask(task, trigger);
//
//	}
//
//}
