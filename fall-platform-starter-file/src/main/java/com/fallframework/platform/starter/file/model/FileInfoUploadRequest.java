package com.fallframework.platform.starter.file.model;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class FileInfoUploadRequest extends BasePageRequest {

	private static final long serialVersionUID = -5996741803151842686L;

	/**
	 * 文件名
	 */
	private String name;

	/**
	 * 存储类型
	 */
	private StorageTypeEnum storageType;

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

	/**
	 * 文件
	 */
	private File file;

}
