package com.fallframework.platform.starter.ureport.service.impl;

import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.fallframework.platform.starter.ureport.ftp.FTPClientUtil;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author zhuangpf
 */
public class FTPProvider implements ReportProvider {

	private static final String NAME = "ftp-provider";
	private String basePath = "ureport_file/";
	private String prefix = "ftp:";

	private boolean disabled;

	@Autowired
	private FTPClientUtil ftpUtil;

	@Override
	public InputStream loadReport(String s) {
		return ftpUtil.downloadFile(getCorrectName(s));
	}

	@Override
	public void deleteReport(String s) {
		ftpUtil.delete(getCorrectName(s));
	}

	@Override
	public List<ReportFile> getReportFiles() {
		List<FTPFile> fileList = ftpUtil.getFileList(basePath);
		List<ReportFile> reportFile = new ArrayList<>();
		for (FTPFile ftpFile : fileList) {
			Calendar timestamp = ftpFile.getTimestamp();
			reportFile.add(new ReportFile(ftpFile.getName(), timestamp.getTime()));
		}
		return reportFile;
	}

	@Override
	public void saveReport(String file, String content) {
		ftpUtil.uploadFile(getCorrectName(file), new ByteArrayInputStream(content.getBytes()));
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean disabled() {
		return disabled;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	/**
	 * 获取没有前缀的文件名并加上FTP下存放Ureport文件夹前缀
	 */
	private String getCorrectName(String name) {
		if (name.startsWith(prefix)) {
			name = name.substring(prefix.length(), name.length());
		}
		return basePath + name;
	}
	
}
