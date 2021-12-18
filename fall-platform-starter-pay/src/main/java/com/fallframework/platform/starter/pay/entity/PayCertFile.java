package com.fallframework.platform.starter.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "pay_cert_file")
public class PayCertFile extends BaseEntity {

	private static final long serialVersionUID = 872549022564183564L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	/**
	 * 文件数据
	 */
	@TableField(value = "data")
	private byte[] data;

	/**
	 * 文件名称
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 文件大小
	 */
	@TableField(value = "size")
	private Long size;

	/**
	 * 文件类型
	 */
	@TableField(value = "file_type")
	private String fileType;

}