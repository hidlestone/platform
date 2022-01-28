package com.fallframework.platform.starter.file.model;

import com.fallframework.platform.starter.api.model.StatusEnum;
import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class FileGroupRequest extends BasePageRequest {

	private static final long serialVersionUID = -3589552693660387478L;

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

	/**
	 * 文件列表
	 */
	private List<FileInfoRequest> fileInfoList;

}
