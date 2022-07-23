package com.fallframework.platform.starter.tencetcos.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class CosUploadDto {

	/**
	 * 目录
	 */
	private String dir;

	/**
	 * 文件
	 */
	private MultipartFile file;

}
