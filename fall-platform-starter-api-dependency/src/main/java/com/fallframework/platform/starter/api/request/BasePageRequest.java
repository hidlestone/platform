package com.fallframework.platform.starter.api.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础分页请求参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class BasePageRequest extends BaseEntityRequest {

	private static final long serialVersionUID = 3355162771426361051L;

	/**
	 * 页码。系统默认：start from 1。
	 */
	private Integer pageNum = 1;

	/**
	 * 条数
	 */
	private Integer pageSize = 10;

	/**
	 * 第一条记录行号
	 */
	public Integer firstRowNum() {
		return (pageNum - 1) * pageSize;
	}

}
