package com.fallframework.platform.starter.task.xxljob.service;

import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.task.xxljob.entity.XxlJobInfo;

/**
 * @author zhuangpf
 */
public interface JobXxlService {

	/**
	 * 保存任务
	 *
	 * @param jobInfo 任务信息
	 * @return 是否保存成功
	 */
	ResponseResult saveJob(XxlJobInfo jobInfo);

	/**
	 * 删除任务
	 *
	 * @param jobInfo 任务信息
	 * @return 是否删除成功
	 */
	ResponseResult deleteJob(XxlJobInfo jobInfo);

	/**
	 * 更新任务
	 *
	 * @param jobInfo 任务信息
	 * @return 是否更新成功
	 */
	ResponseResult updateJob(XxlJobInfo jobInfo);

	
	
	
}
