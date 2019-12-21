package pers.dlx.selfchat.controller.export;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import pers.dlx.selfchat.utils.LoginUtils;

/**
 * 导出监控管理类（控制并发槽位，根据session查询导出进度信息）
 * 
 * @author dinglingxiang
 *
 */
public class ExportManager {

	// 导出并发槽位
	private final int capacity;
	private final List<ExportMonitor> slots = new ArrayList<ExportMonitor>();
	private final static int MAX_CAPACITY = 5;

	private final static Logger logger = LoggerFactory.getLogger(ExportManager.class);

	public ExportManager(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 把导出server添加到并发槽位
	 * 
	 * @param exportServer
	 * @return 如果并发槽位已满，返回false；如果重复添加也返回false;否则加入到并发槽位
	 */
	public synchronized boolean addExportServer(ExportMonitor exportServer) {
		if (slots.size() >= capacity) {
			return false;
		}

		for (ExportMonitor server : slots) {
			if (server == exportServer) {
				return false;
			}
		}
		slots.add(exportServer);
		logger.info("[并发槽位+] " + this.toString());
		return true;
	}

	/**
	 * 导出完成时移除导出监控管理类
	 * 
	 * @param exportServer
	 */
	public synchronized void removeExportServer(ExportMonitor exportServer) {
		slots.removeAll(Lists.newArrayList(exportServer));
		logger.info("[并发槽位-] " + this.toString());
	}

	/**
	 * 获取当前session下的所有的导出任务的导出进度
	 * 
	 * @return
	 */
	public List<ExportProgressVo> getProgress() {
		List<ExportProgressVo> list = Lists.newArrayList();

		String sessionId = LoginUtils.getSessionId();
		for (ExportMonitor server : slots) {
			// 返回同一个session的所有的export server
			if (Objects.equal(server.getSessionId(), sessionId)) {
				list.add(server.getProgress());
			}
		}

		return list;
	}

	@Override
	public String toString() {
		return "ExportManager [capacity=" + capacity + ", slots=" + slots + "]";
	}

	// 单例
	private static class SingletonHolder {
		private final static ExportManager instance = new ExportManager(MAX_CAPACITY);
	}

	public static ExportManager getSingletonInstance() {
		return SingletonHolder.instance;
	}

}
