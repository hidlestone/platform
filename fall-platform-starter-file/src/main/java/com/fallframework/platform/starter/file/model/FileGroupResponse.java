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
public class FileGroupResponse extends BaseEntityResponse {

	private static final long serialVersionUID = -8904656121945265073L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 文件组状态
	 */
	private StatusEnum status;
	
}
