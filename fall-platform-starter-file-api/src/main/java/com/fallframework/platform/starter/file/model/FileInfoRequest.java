package com.fallframework.platform.starter.file.model;

import com.fallframework.platform.starter.core.entity.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class FileInfoRequest extends BasePageRequest {

	private static final long serialVersionUID = 6662274574728973226L;
	
	private Long fileGroupId;
}
