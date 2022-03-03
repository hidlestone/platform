package com.fallframework.platform.starter.ureport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.fallframework.platform.starter.ureport.entity.UreportFile;
import com.fallframework.platform.starter.ureport.service.UreportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ureport 报表服务
 *
 * @author zhuangpf
 */
@Component
@ConfigurationProperties(prefix = "ureport.mysql.provider")
public class MySQLProvider implements ReportProvider {

	private static final String NAME = "mysql-provider";
	private String prefix = "mysql:";
	private boolean disabled;

	@Autowired
	private UreportFileService ureportFileService;

	/**
	 * 加载报表
	 */
	@Override
	public InputStream loadReport(String s) {
		QueryWrapper<UreportFile> wrapper = new QueryWrapper();
		wrapper.eq("name", getCorrectName(s));
		UreportFile ureportFile = ureportFileService.getOne(wrapper);
		byte[] content = ureportFile.getContent();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
		return inputStream;
	}

	/**
	 * 删除报表
	 */
	@Override
	public void deleteReport(String s) {
		QueryWrapper<UreportFile> wrapper = new QueryWrapper();
		wrapper.eq("name", getCorrectName(s));
		ureportFileService.remove(wrapper);
	}

	/**
	 * 查询所有报表文件
	 */
	@Override
	public List<ReportFile> getReportFiles() {
		List<UreportFile> ureportFileList = ureportFileService.getUreportFileInfo();
		List<ReportFile> reportList = new ArrayList<>();
		for (UreportFile ureportFile : ureportFileList) {
			reportList.add(new ReportFile(ureportFile.getName(), ureportFile.getGmtModified()));
		}
		return reportList;
	}

	/**
	 * 保存报表
	 */
	@Override
	public void saveReport(String file, String content) {
		String name = this.getCorrectName(file);
		QueryWrapper<UreportFile> wrapper = new QueryWrapper();
		wrapper.eq("name", name);
		UreportFile ureportFile = ureportFileService.getOne(wrapper);
		if (null == ureportFile) {
			UreportFile entity = new UreportFile();
			entity.setName(name);
			entity.setContent(content.getBytes());
			ureportFileService.save(entity);
		} else {
			ureportFile.setContent(content.getBytes());
			ureportFileService.save(ureportFile);
		}
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
	 * 获取没有前缀的文件名
	 */
	private String getCorrectName(String name) {
		if (name.startsWith(prefix)) {
			name = name.substring(prefix.length(), name.length());
		}
		return name;
	}
}
