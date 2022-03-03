package com.fallframework.platform.starter.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "s_role_permission")
public class RolePermission extends BaseEntity {

	private static final long serialVersionUID = 6105909801790260982L;

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
	 * 权限ID
	 */
	@TableField(value = "permission_id")
	private Long permissionId;

}