package com.fallframework.platform.starter.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应数据库表公共字段：<br>
 * `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID'<br>
 * `modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID'<br>
 * `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'<br>
 * `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间'<br>
 *
 * @author payn
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -2019856722533574712L;

	/**
	 * 创建用户ID
	 */
	private Long createUserId;

	/**
	 * 修改用户ID
	 */
	private Long modifyUserId;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 更改时间
	 */
	private Date gmtModified;

}
