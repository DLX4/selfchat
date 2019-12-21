package pers.dlx.selfchat.task;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * selfchat系统轮询任务 回滚任务专用线程池
 * 
 * @author dinglingxiang
 *
 */
public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(VisiableThreadPoolTaskExecutor.class);

	private void showThreadPoolInfo(String prefix) {
		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

		if (null == threadPoolExecutor) {
			return;
		}

		logger.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
				this.getThreadNamePrefix(), prefix, threadPoolExecutor.getTaskCount(),
				threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getActiveCount(),
				threadPoolExecutor.getQueue().size());
	}

	@Override
	public void execute(Runnable task) {
		showThreadPoolInfo("[do execute]");
		super.execute(task);
	}

	@Override
	public void execute(Runnable task, long startTimeout) {
		showThreadPoolInfo("[do execute]");
		super.execute(task, startTimeout);
	}

	@Override
	public Future<?> submit(Runnable task) {
		showThreadPoolInfo("[do submit]");
		return super.submit(task);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		showThreadPoolInfo("[do submit]");
		return super.submit(task);
	}

	@Override
	public ListenableFuture<?> submitListenable(Runnable task) {
		showThreadPoolInfo("[do submitListenable]");
		return super.submitListenable(task);
	}

	@Override
	public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
		showThreadPoolInfo("[do submitListenable]");
		return super.submitListenable(task);
	}

}
