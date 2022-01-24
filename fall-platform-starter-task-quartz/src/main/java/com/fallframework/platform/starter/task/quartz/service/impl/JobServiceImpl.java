package com.fallframework.platform.starter.task.quartz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.fallframework.platform.starter.api.response.ResponseResult;
import com.fallframework.platform.starter.task.quartz.model.JobRequest;
import com.fallframework.platform.starter.task.quartz.model.TaskRequest;
import com.fallframework.platform.starter.task.quartz.model.TaskResponse;
import com.fallframework.platform.starter.task.quartz.service.JobService;
import com.fallframework.platform.starter.task.quartz.util.QrtzTaskUtil;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhuangpf
 */
@Service
public class JobServiceImpl implements JobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

	@Autowired
	private Scheduler scheduler;
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * 保存定时任务
	 *
	 * @param request 请求参数
	 * @return 是否保存成功
	 */
	@Override
	public ResponseResult saveJob(JobRequest request) {
		// 请求参数
		String jobName = request.getJobName();
		String jobGroup = request.getJobGroup();
		String jobClass = request.getJobClass();
		String jobDescription = request.getJobDescription();
		Map<String, Object> jobDataMapInfo = request.getJobDataMap();
		try {
			// 校验该triggerKey是否存在
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			boolean existFlag = scheduler.checkExists(triggerKey);
			if (existFlag) {
				return ResponseResult.fail("Job is alread exist. jobName:" + jobName + ", jobGroup:" + jobGroup + "");
			}
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			// 根据触发器类型选择触发器(默认是表达式触发器)
			Trigger trigger = QrtzTaskUtil.constructTrigger(triggerKey, request);
			//jobDataMap
			JobDataMap jobDataMap = new JobDataMap();
			if (CollectionUtil.isNotEmpty(jobDataMapInfo)) {
				jobDataMap = new JobDataMap(jobDataMapInfo);
			}
			// JobDetail
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobClass);
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).requestRecovery().usingJobData(jobDataMap).build();
			// 调度
			scheduler.scheduleJob(jobDetail, trigger);
			String schedulerName = scheduler.getSchedulerName();
			LOGGER.info("schedulerName:{}, jobGroup:{}, jobName:{}, jobClass:{} save success.", schedulerName, jobGroup, jobName, jobClass);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("scheduler exception.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(jobClass + " is not found.");
		}
		return ResponseResult.success();
	}

	/**
	 * 删除定时任务
	 *
	 * @param request 请求参数
	 * @return 是否删除成功
	 */
	@Override
	public ResponseResult deleteJob(JobRequest request) {
		// 请求参数
		String jobName = request.getJobName();
		String jobGroup = request.getJobGroup();
		try {
			// 校验该triggerKey是否存在
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			boolean existFlag = scheduler.checkExists(triggerKey);
			if (existFlag) {
				return ResponseResult.fail("Job is alread exist. jobName:" + jobName + ", jobGroup:" + jobGroup + "");
			}
			scheduler.pauseTrigger(triggerKey);
			scheduler.unscheduleJob(triggerKey);
			String schedulerName = scheduler.getSchedulerName();
			LOGGER.info("schedulerName:{}, jobGroup:{}, jobName:{} delete success.", schedulerName, jobGroup, jobName);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("scheduler exception.");
		}
		return ResponseResult.success();
	}

	/**
	 * 更新定时任务
	 *
	 * @param request 请求参数
	 * @return 是否更新成功
	 */
	@Override
	public ResponseResult updateJob(JobRequest request) {
		// 请求参数
		String jobName = request.getJobName();
		String jobGroup = request.getJobGroup();
		String jobClass = request.getJobClass();
		String jobDescription = request.getJobDescription();
		Map<String, Object> jobDataMapInfo = request.getJobDataMap();

		try {
			// 校验该triggerKey是否存在
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			boolean existFlag = scheduler.checkExists(triggerKey);
			if (existFlag) {
				return ResponseResult.fail("Job is alread exist. jobName:" + jobName + ", jobGroup:" + jobGroup + "");
			}
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			// 先remove job
			scheduler.pauseTrigger(triggerKey);
			scheduler.unscheduleJob(triggerKey);
			// 根据触发器类型选择触发器(默认是表达式触发器)
			Trigger trigger = QrtzTaskUtil.constructTrigger(triggerKey, request);
			//jobDataMap
			JobDataMap jobDataMap = new JobDataMap();
			if (CollectionUtil.isNotEmpty(jobDataMapInfo)) {
				jobDataMap = new JobDataMap(jobDataMapInfo);
			}
			// JobDetail
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobClass);
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).requestRecovery().usingJobData(jobDataMap).build();
			// 调度
			scheduler.scheduleJob(jobDetail, trigger);
			String schedulerName = scheduler.getSchedulerName();
			LOGGER.info("schedulerName:{}, jobGroup:{}, jobName:{}, jobClass:{} save success.", schedulerName, jobGroup, jobName, jobClass);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("scheduler exception.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(jobClass + " is not found.");
		}
		return ResponseResult.success();
	}

	/**
	 * 查询所有的任务列表
	 *
	 * @return 所有任务列表
	 */
	@Override
	public ResponseResult<List<TaskResponse>> jobList() {
		List<TaskResponse> taskResponseList = new ArrayList();
		try {
			// 组
			List<String> jobGroupNameList = scheduler.getJobGroupNames();
			for (String groupJob : jobGroupNameList) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupJob))) {
					List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
					List<TaskResponse> reslutList = getTaskList(triggers, jobKey);
					taskResponseList.addAll(reslutList);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("scheduler exception.");
		}
		return ResponseResult.success(taskResponseList);
	}

	@Override
	public ResponseResult pause(TaskRequest request) {
		// 请求参数
		String jobName = request.getJobName();
		String jobGroup = request.getJobGroup();
		try {
			// 校验该triggerKey是否存在
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			boolean existFlag = scheduler.checkExists(triggerKey);
			if (existFlag) {
				return ResponseResult.fail("Job is alread exist. jobName:" + jobName + ", jobGroup:" + jobGroup + "");
			}
			scheduler.pauseTrigger(triggerKey);
			String schedulerName = scheduler.getSchedulerName();
			LOGGER.info("schedulerName:{}, jobGroup:{}, jobName:{} pause success.", schedulerName, jobGroup, jobName);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("scheduler exception.");
		}
		return ResponseResult.success();
	}

	@Override
	public ResponseResult resume(TaskRequest request) {
		// 请求参数
		String jobName = request.getJobName();
		String jobGroup = request.getJobGroup();
		try {
			// 校验该triggerKey是否存在
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			boolean existFlag = scheduler.checkExists(triggerKey);
			if (existFlag) {
				return ResponseResult.fail("Job is alread exist. jobName:" + jobName + ", jobGroup:" + jobGroup + "");
			}
			scheduler.resumeTrigger(triggerKey);
			String schedulerName = scheduler.getSchedulerName();
			LOGGER.info("schedulerName:{}, jobGroup:{}, jobName:{} resume success.", schedulerName, jobGroup, jobName);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new RuntimeException("scheduler exception.");
		}
		return ResponseResult.success();
	}

	/**
	 * 根据触发器、jobkey查询任务明细
	 *
	 * @param triggers 触发器
	 * @param jobKey   jobkey
	 * @return 任务列表
	 * @throws SchedulerException 异常
	 */
	private List<TaskResponse> getTaskList(List<? extends Trigger> triggers, JobKey jobKey) throws SchedulerException {
		List<TaskResponse> resultList = new ArrayList<>();
		String cronExpression = "";
		String createTime = "";
		String nextFileTime = "";
		String previousFireTime = "";
		String triggerDescription = "";
		int priority = 0;
		for (Trigger trigger : triggers) {
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				cronExpression = cronTrigger.getCronExpression();
				triggerDescription = cronTrigger.getDescription();
				priority = cronTrigger.getPriority();
				if (cronTrigger.getNextFireTime() != null) {
					nextFileTime = DateUtil.formatDateTime(cronTrigger.getNextFireTime());
				}
				if (cronTrigger.getPreviousFireTime() != null) {
					previousFireTime = DateUtil.formatDateTime(cronTrigger.getPreviousFireTime());
				}
				createTime = DateUtil.formatDateTime(cronTrigger.getStartTime());
			}
			TaskResponse taskResponse = new TaskResponse();
			taskResponse.setSchedulerName(scheduler.getSchedulerName());
			taskResponse.setSchedulerInstanceId(scheduler.getSchedulerInstanceId());
			taskResponse.setJobName(jobKey.getName());
			taskResponse.setJobGroup(jobKey.getGroup());
			taskResponse.setJobClass(jobDetail.getJobClass().getName());
			taskResponse.setJobDescription(jobDetail.getDescription());
			taskResponse.setJobDataMap(jobDetail.getJobDataMap());
			taskResponse.setJobStatus(triggerState.name());
			taskResponse.setCronExpression(cronExpression);
			taskResponse.setTriggerPriority(priority);
			taskResponse.setTriggerDescription(triggerDescription);
			taskResponse.setCreateTime(createTime);
			taskResponse.setNextFileTime(nextFileTime);
			taskResponse.setPreviousFireTime(previousFireTime);
			resultList.add(taskResponse);
		}
		return resultList;
	}
	
}
