package pers.dlx.selfchat.controller.export;

/**
 * 导出进度信息
 * 
 * @author dinglingxiang
 *
 */
public class ExportProgressVo {

	// 当前导出数量
	private long current;
	// 总共数量
	private long total;
	// 耗时
	private int timeUsed;

	// 下载任务描述
	private String description;

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(int timeUsed) {
		this.timeUsed = timeUsed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
