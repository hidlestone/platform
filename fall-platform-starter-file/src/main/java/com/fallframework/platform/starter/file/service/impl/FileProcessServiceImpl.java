package com.fallframework.platform.starter.file.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.file.entity.FileInfo;
import com.fallframework.platform.starter.file.model.FileGroupRequest;
import com.fallframework.platform.starter.file.model.FileGroupUploadRequest;
import com.fallframework.platform.starter.file.model.FileInfoRequest;
import com.fallframework.platform.starter.file.service.FileGroupService;
import com.fallframework.platform.starter.file.service.FileInfoService;
import com.fallframework.platform.starter.file.service.FileProcessService;
import com.fallframework.platform.starter.file.util.FileStarterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * SpringBoot实现文件的上传下载
 *
 * @author zhuangpf
 */
@Service
public class FileProcessServiceImpl implements FileProcessService {

	/**
	 * 文件上传的位置，在yml中进行配置
	 */
	@Value("${fall.file.upload-file-path}")
	private String uploadFilePath;
	@Autowired
	private FileGroupService fileGroupService;
	@Autowired
	private FileInfoService fileInfoService;

	@Transactional
	@Override
	public ResponseResult uploadFileGroup(FileGroupUploadRequest uploadRequest) {
		// // 所有待上传的文件 MultipartFile
		List<MultipartFile> files = uploadRequest.getFiles();
		if (CollectionUtil.isEmpty(files)) {
			return ResponseResult.fail("files are not exist.");
		}
		FileGroupRequest fileGroupRequest = new FileGroupRequest();
		List<FileInfoRequest> fileInfoRequestList = new ArrayList<>();
		fileGroupRequest.setFileInfoList(fileInfoRequestList);
		fileGroupRequest.setDesc(uploadRequest.getDesc());
		try {
			for (MultipartFile multipartFile : files) {
				String fname = multipartFile.getOriginalFilename();
				// 上传文件的类型：application/xml
				String contentType = multipartFile.getContentType();
				UUID uuid = UUID.randomUUID();
				// 获取文件后缀名
				String fkey = uuid + "." + FileStarterUtil.getFileExt(fname);
				// 设置文件保存的路径，不存在则创建文件夹
				String filepath = FileStarterUtil.getFilePath(fkey, uploadFilePath);
				File file = new File(filepath);
				// 进行对上传文件的IO拷贝操作
				FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));

				FileInfoRequest infoRequest = new FileInfoRequest();
				infoRequest.setName(fname);
				infoRequest.setNonsenseName(fkey);
				infoRequest.setExtension(FileStarterUtil.getFileExt(fname));
				infoRequest.setStorageType(uploadRequest.getStorageType());
				//infoRequest.setFileType();
				infoRequest.setContentType(contentType);
				fileInfoRequestList.add(infoRequest);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileGroupService.saveGroupAndInfoList(fileGroupRequest);
	}

	@Override
	public ResponseResult downloadFile(Long fileInfoId, HttpServletResponse response) {
		FileInfo fileInfo = fileInfoService.select(fileInfoId).getData();
		if (null == fileInfo) {
			return ResponseResult.fail("file is not exist.");
		}
		File file = new File(uploadFilePath + "/" + fileInfo.getNonsenseName());
		if (!file.exists()) {
			return ResponseResult.fail("file is not exist.");
		}
		response.setContentType("application/octet-stream");
		response.setCharacterEncoding("utf-8");
		response.setContentLength((int) file.length());
		// 设置强制下载不打开
		response.setContentType("application/force-download");
		// 设置文件名
		response.addHeader("Content-Disposition", "attachment;fileName=" + fileInfo.getNonsenseName());
		byte[] buffer = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 做关闭操作
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ResponseResult.success();
	}

}
