package com.fallframework.platform.starter.file.model;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.response.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class FileInfoResponse extends BaseEntityResponse {

	private static final long serialVersionUID = -4207904722393330558L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 文件组ID
	 */
	private Long fileGroupId;

	/**
	 * 文件名
	 */
	private String name;

	/**
	 * 无意义名称
	 */
	private String nonsenseName;

	/**
	 * 文件扩展名
	 */
	private String extension;

	/**
	 * 存储类型
	 */
	private Byte storageType;

	/**
	 * 文件类型
	 */
	private Byte fileType;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 文件url
	 */
	private String url;

	/**
	 * 文件业务类型
	 */
	private String category;

	/**
	 * 文件状态
	 */
	private StatusEnum status;

}
