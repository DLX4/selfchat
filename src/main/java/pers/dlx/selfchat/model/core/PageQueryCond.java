package pers.dlx.selfchat.model.core;

import org.codehaus.jackson.annotate.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页查询条件。包括排序条件、分页条件。
 * 
 * @author qiu-fl Date 2014-4-2
 */
@Data
@ApiModel(value = "分页查询条件")
public class PageQueryCond<T> {

	private T cond;

	private PageCond pageCond;

	private String orderByClause;

}