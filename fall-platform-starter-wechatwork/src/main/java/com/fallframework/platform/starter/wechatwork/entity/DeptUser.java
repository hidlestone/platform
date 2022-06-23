package com.fallframework.platform.starter.wechatwork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("wxwork_dept_user")
public class DeptUser extends WXWorkBaseEntity {

	private static final long serialVersionUID = -8997054058852381289L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	/**
	 * 成员userid
	 */
	@TableField("userid")
	private String userid;

	/**
	 * 部门ID
	 */
	@TableField("deptid")
	private Long deptid;

	/**
	 * 部门内的排序值
	 */
	@TableField("order")
	private Long order;

	/**
	 * 所在的部门内是否为部门负责人
	 */
	@TableField("is_leader_in_dept")
	private Byte isLeaderInDept;

}