package pers.dlx.selfchat.model.core;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页条件
 * 
 * @author dinglingxiang
 *
 */
@Data
@ApiModel(value = "分页条件")
public class PageCond implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页索引，从1开始。1表示第一页。
	 */
	private Integer page = 1;

	/**
	 * 每页件数。
	 */
	private Integer size = 20;

	@Override
	public String toString() {
		return "PageCond [page=" + page + ", size=" + size + "]";
	}

}
