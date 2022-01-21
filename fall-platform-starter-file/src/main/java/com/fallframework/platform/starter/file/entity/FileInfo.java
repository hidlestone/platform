package com.fallframework.platform.starter.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "s_file_info")
public class FileInfo extends BaseEntity {

	private static final long serialVersionUID = 9008324671673485598L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 文件名
	 */
	@TableField(value = "`name`")
	private String name;

	/**
	 * 文件扩展名
	 */
	@TableField(value = "extension")
	private String extension;

	/**
	 * 存储类型
	 */
	@TableField(value = "storage_type")
	private String storageType;

	/**
	 * 备注
	 */
	@TableField(value = "remark")
	private String remark;

	/**
	 * 文件url
	 */
	@TableField(value = "url")
	private String url;

	/**
	 * 文件状态
	 */
	@TableField(value = "`status`")
	private Byte status;

	/**
	 * 文件业务类型
	 */
	@TableField(value = "category")
	private String category;

	/**
	 * 文件组ID
	 */
	@TableField(value = "file_group_id")
	private Long fileGroupId;

}