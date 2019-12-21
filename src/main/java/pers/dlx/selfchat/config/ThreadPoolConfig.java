package pers.dlx.selfchat.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import pers.dlx.selfchat.constants.Consts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import pers.dlx.selfchat.task.VisiableThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

	/**
	 * 系统默认线程池
	 * 
	 * @return
	 */
	@Bean(Consts.DEFAULT_EXECUTOR)
	public Executor defectDigestExecutor() {
		// 单线程的线程池
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();

		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setAwaitTerminationSeconds(60);

		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("default-service-");

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		// DiscardPolicy : 直接忽略执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		// 执行初始化
		executor.initialize();

		return executor;
	}

}
