package com.fallframework.platform.starter.file.model;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class FileInfoRequest extends BasePageRequest {

	private static final long serialVersionUID = 6662274574728973226L;

	/**
	 * 文件名
	 */
	private String name;

	/**
	 * 文件扩展名
	 */
	private String extension;

	/**
	 * 存储类型
	 */
	private String storageType;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 文件url
	 */
	private String url;

	/**
	 * 文件状态
	 */
	private StatusEnum status;

	/**
	 * 文件业务类型
	 */
	private String category;

	/**
	 * 文件组ID
	 */
	private Long fileGroupId;

}
