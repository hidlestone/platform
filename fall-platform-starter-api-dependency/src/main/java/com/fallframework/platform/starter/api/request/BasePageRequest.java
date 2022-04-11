package com.fallframework.platform.starter.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础分页请求参数
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class BasePageRequest implements Serializable {

	private static final long serialVersionUID = 3355162771426361051L;

	/**
	 * 页码。系统默认：start from 1。
	 */
	private Integer pageNum;

	/**
	 * 条数
	 */
	private Integer pageSize;

	/**
	 * 获取页码减一
	 *
	 * @return 当前页面减一
	 */
	public Integer getPageNumMinusOne() {
		return pageNum - 1;
	}

	/**
	 * 页码【1】开始的，第一条记录行号。
	 */
	public Integer getFirstRowFromNumOne() {
		return (pageNum - 1) * pageSize;
	}

	/**
	 * 页码【0】开始的，第一条记录行号。
	 */
	public Integer getFirstRowFromNumZero() {
		return pageNum * pageSize;
	}

}
