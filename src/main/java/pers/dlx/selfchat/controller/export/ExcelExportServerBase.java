package pers.dlx.selfchat.controller.export;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import pers.dlx.selfchat.model.core.PageCond;
import pers.dlx.selfchat.model.core.PageQueryCond;
import pers.dlx.selfchat.utils.DateUtils;
import pers.dlx.selfchat.utils.LoginUtils;

import cn.afterturn.easypoi.handler.inter.IExcelExportServer;

/**
 * export server
 * 
 * @author dinglingxiang
 *
 */
public class ExcelExportServerBase<Q, R> implements ExportMonitor, IExcelExportServer {

	// 当前导出行
	private long currentRowNum;
	// 总共数据行数
	private long total;

	// 开始导出时间
	private Date startTime;

	// session id
	private final String sessionId;

	// 下载任务描述
	private final String description;

	// 分页查询函数
	private final Function<PageQueryCond<Q>, PageInfo<R>> pageQueryFunction;

	private final static Logger logger = LoggerFactory.getLogger(ExcelExportServerBase.class);

	/**
	 * 创建一个export server，如果当前导出任务超过系统最大导出并发数，会立即抛出异常
	 * 
	 * @param pageQueryFunction
	 * @param description
	 * @throws ExportLimitException
	 */
	public ExcelExportServerBase(Function<PageQueryCond<Q>, PageInfo<R>> pageQueryFunction, String description)
			throws ExportLimitException {
		this.pageQueryFunction = pageQueryFunction;
		this.startTime = new Date();
		this.sessionId = LoginUtils.getSessionId();
		this.description = description;

		// 开始下载，添加到并发槽位中
		if (false == ExportManager.getSingletonInstance().addExportServer(this)) {
			throw new ExportLimitException("导出任务并发已满，请稍后再试");
		}
	}

	/**
	 * 获取sessionId
	 * 
	 * @return
	 */
	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public List<Object> selectListForExcelExport(Object queryParams, int page) {
		List<Object> list = Lists.newArrayList();

		try {
			@SuppressWarnings("unchecked")
			PageQueryCond<Q> cond = (PageQueryCond<Q>) queryParams;
			PageCond pageCond = cond.getPageCond();
			pageCond.setPage(page);
			pageCond.setSize(1000);

			logger.info("[export-pre] {}", page);

			PageInfo<R> res = pageQueryFunction.apply(cond);
			this.total = res.getTotal();
			this.currentRowNum = res.getEndRow();

			logger.info("[export-info] {}", getPageInfo(res));

			res.getList().forEach((o) -> list.add(o));
		} catch (Exception e) {
			// ignore
			logger.error("error: {}", e.getMessage(), e);
		} finally {
			// 数据已经完全导出，释放并发槽位 ；每次都触发检查，避免因为网络原因导致异常从而影响导出流程
			if (CollectionUtils.isEmpty(list)) {
				ExportManager.getSingletonInstance().removeExportServer(this);
			}
		}

		return list;
	}

	@Override
	public ExportProgressVo getProgress() {
		ExportProgressVo progress = new ExportProgressVo();
		progress.setCurrent(this.currentRowNum);
		progress.setTotal(this.total);
		progress.setTimeUsed(DateUtils.secondsBetween(startTime, new Date()));
		progress.setDescription(this.description);
		return progress;
	}

	@Override
	public void cancel() {
		// do nothing
	}

	@Override
	public String toString() {
		return "ExcelExportServerBase [startTime=" + startTime + ", sessionId=" + sessionId + ", description="
				+ description + "]";
	}

	private static String getPageInfo(@SuppressWarnings("rawtypes") PageInfo pageInfo) {
		final StringBuilder sb = new StringBuilder("PageInfo{");
		sb.append("pageNum=").append(pageInfo.getPageNum());
		sb.append(", pageSize=").append(pageInfo.getPageSize());
		sb.append(", size=").append(pageInfo.getSize());
		sb.append(", startRow=").append(pageInfo.getStartRow());
		sb.append(", endRow=").append(pageInfo.getEndRow());
		sb.append(", total=").append(pageInfo.getTotal());
		sb.append(", pages=").append(pageInfo.getPages());

		sb.append(", prePage=").append(pageInfo.getPrePage());
		sb.append(", nextPage=").append(pageInfo.getNextPage());

		return sb.toString();
	}

}
