package com.fallframework.platform.starter.core.entity.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础分页请求参数
 *
 * @author payn
 */
@Getter
@Setter
public class BasePageRequest implements Serializable {

	private static final long serialVersionUID = 3355162771426361051L;

	/**
	 * 页码。start from 1
	 */
	private Integer pageNum;

	/**
	 * 条数
	 */
	private Integer pageSize;

}
