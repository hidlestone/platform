package com.fallframework.platform.starter.ureport.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.mp.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName(value = "ureport_file")
public class UreportFile extends BaseEntity {

	private static final long serialVersionUID = -6369976836338095708L;
	
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	/**
	 * 文件名
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 内容
	 */
	@TableField(value = "content")
	private byte[] content;

	/**
	 * 创建用户ID
	 */
	@TableField(value = "create_user_id")
	private Long createUserId;

	/**
	 * 修改用户ID
	 */
	@TableField(value = "modify_user_id")
	private Long modifyUserId;

	/**
	 * 创建时间
	 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**
	 * 更改时间
	 */
	@TableField(value = "gmt_modified")
	private Date gmtModified;

}