package com.fallframework.platform.starter.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "s_role_menu")
public class RoleMenu extends BaseEntity {

	private static final long serialVersionUID = 3223207478935463520L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 角色ID
	 */
	@TableField(value = "role_id")
	private Long roleId;

	/**
	 * 菜单ID
	 */
	@TableField(value = "menu_id")
	private Long menuId;
	
}