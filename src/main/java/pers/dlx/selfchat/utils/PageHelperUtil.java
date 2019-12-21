package pers.dlx.selfchat.utils;

import java.util.List;

import com.github.pagehelper.Page;

/**
 * page helper插件使用相关的工具类
 * 
 * @author dinglingxiang
 *
 */
public class PageHelperUtil {

	/**
	 * 替换Page的数据，保留字段信息
	 * 
	 * @param page
	 * @param list
	 * @return
	 */
	public static <T> Page<T> adapt(Page<?> page, List<T> list) {
		Page<T> pageNew = new Page<T>();
		pageNew.addAll(list);

		pageNew.setPageNum(page.getPageNum());
		pageNew.setPageSize(page.getPageSize());
		pageNew.setStartRow(page.getStartRow());
		pageNew.setEndRow(page.getEndRow());
		pageNew.setTotal(page.getTotal());
		pageNew.setPages(page.getPages());
		pageNew.setReasonable(page.getReasonable());
		pageNew.setPageSizeZero(page.getPageSizeZero());
		pageNew.setCountColumn(page.getCountColumn());
		pageNew.setOrderBy(page.getOrderBy());

		return pageNew;
	}
}
