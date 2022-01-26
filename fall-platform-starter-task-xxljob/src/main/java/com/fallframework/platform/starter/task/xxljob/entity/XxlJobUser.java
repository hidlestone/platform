package com.fallframework.platform.starter.task.xxljob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "xxl_job_user")
public class XxlJobUser {

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 账号
	 */
	@TableField(value = "username")
	private String username;

	/**
	 * 密码
	 */
	@TableField(value = "password")
	private String password;

	/**
	 * 角色：0-普通用户、1-管理员
	 */
	@TableField(value = "role")
	private Byte role;

	/**
	 * 权限：执行器ID列表，多个逗号分割
	 */
	@TableField(value = "permission")
	private String permission;

}