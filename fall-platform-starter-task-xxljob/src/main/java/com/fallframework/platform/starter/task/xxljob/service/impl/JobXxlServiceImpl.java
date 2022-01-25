package com.fallframework.platform.starter.task.xxljob.service.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.task.xxljob.entity.XxlJobInfo;
import com.fallframework.platform.starter.task.xxljob.service.JobXxlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhuangpf
 */
@Service
public class JobXxlServiceImpl implements JobXxlService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobXxlServiceImpl.class);


	@Value("${xxl.job.admin.address}")
	private static String baseUri;

	private final static String JOB_INFO_URI = "/jobinfo";
	private final static String JOB_GROUP_URI = "/jobgroup";

	@Override
	public ResponseResult saveJob(XxlJobInfo jobInfo) {
		JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(jobInfo));
		HttpResponse response = HttpUtil.createGet(baseUri + JOB_INFO_URI + "/add").form(jsonObject).execute();
		return ResponseResult.success();
	}

	@Override
	public ResponseResult deleteJob(XxlJobInfo jobInfo) {
		return null;
	}

	@Override
	public ResponseResult updateJob(XxlJobInfo jobInfo) {
		return null;
	}
}
