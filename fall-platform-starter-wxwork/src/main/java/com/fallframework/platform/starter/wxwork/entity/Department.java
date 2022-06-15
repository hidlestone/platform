package com.fallframework.platform.starter.wxwork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("wxwork_department")
public class Department extends BaseEntity {

	private static final long serialVersionUID = -1340420751736336503L;

	/**
	 * ID
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 部门名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 部门英文名称
	 */
	@TableField("name_en")
	private String nameEn;

	/**
	 * 部门负责人的UserID；第三方仅通讯录应用可获取
	 */
	@TableField("department_leader")
	private String departmentLeader;

	/**
	 * 父部门id。根部门为1
	 */
	@TableField("parentid")
	private Long parentid;

	/**
	 * 在父部门中的次序值。order值大的排序靠前。值范围是[0, 2^32)
	 */
	@TableField("order")
	private Long order;

}