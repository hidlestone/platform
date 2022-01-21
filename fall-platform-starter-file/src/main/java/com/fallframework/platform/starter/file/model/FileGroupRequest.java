package com.fallframework.platform.starter.file.model;

import com.fallframework.platform.starter.api.request.BaseEntityRequest;
import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class FileGroupRequest extends BasePageRequest {

	private static final long serialVersionUID = -3589552693660387478L;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 文件组状态
	 */
	private Byte status;

}
