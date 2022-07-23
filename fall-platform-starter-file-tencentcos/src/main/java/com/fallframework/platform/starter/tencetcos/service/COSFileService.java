package com.fallframework.platform.starter.tencetcos.service;

import com.fallframework.platform.starter.tencetcos.model.CosDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhuangpf
 */
public interface COSFileService {

	String fileUpload(MultipartFile file, CosDto dto);

}
