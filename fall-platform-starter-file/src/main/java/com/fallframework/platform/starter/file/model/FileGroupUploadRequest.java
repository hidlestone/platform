package com.fallframework.platform.starter.file.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class FileGroupUploadRequest extends BasePageRequest {

	private static final long serialVersionUID = -5628554331347879601L;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 存储类型
	 */
	private Byte storageType;

	/**
	 * 请求上传的文件
	 */
	private List<MultipartFile> files;
	
}
