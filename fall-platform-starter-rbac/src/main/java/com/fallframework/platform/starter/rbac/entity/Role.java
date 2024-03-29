package com.fallframework.platform.starter.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@TableName(value = "s_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = -5039739542727465737L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 角色编码
	 */
	@TableField(value = "role_code")
	private String roleCode;

	/**
	 * 角色名称
	 */
	@TableField(value = "role_name")
	private String roleName;

	/**
	 * 角色描述
	 */
	@TableField(value = "role_desc")
	private String roleDesc;

	/**
	 * 菜单列表
	 */
	@TableField(exist = false)
	private List<Menu> menus;

	/**
	 * 权限列表
	 */
	@TableField(exist = false)
	private List<Permission> permissions;
	
}