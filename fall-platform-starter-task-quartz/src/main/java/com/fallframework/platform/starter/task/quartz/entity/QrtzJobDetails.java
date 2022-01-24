package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "qrtz_job_details")
public class QrtzJobDetails {
	
	/**
	 * 调度名称
	 */
	@TableId(value = "SCHED_NAME", type = IdType.INPUT)
	private String schedName;

	/**
	 * 集群中job的名字
	 */
	@TableId(value = "JOB_NAME", type = IdType.INPUT)
	private String jobName;

	/**
	 * 集群中job的所属组的名字
	 */
	@TableId(value = "JOB_GROUP", type = IdType.INPUT)
	private String jobGroup;

	/**
	 * 详细描述信息
	 */
	@TableField(value = "DESCRIPTION")
	private String description;

	/**
	 * 集群中个notejob实现类的全限定名，quartz就是根据这个路径到classpath找到该job类
	 */
	@TableField(value = "JOB_CLASS_NAME")
	private String jobClassName;

	/**
	 * 是否持久化，把该属性设置为1，quartz会把job持久化到数据库中
	 */
	@TableField(value = "IS_DURABLE")
	private String isDurable;

	/**
	 * 是否并发执行
	 */
	@TableField(value = "IS_NONCONCURRENT")
	private String isNonconcurrent;

	/**
	 * 是否更新数据
	 */
	@TableField(value = "IS_UPDATE_DATA")
	private String isUpdateData;

	/**
	 * 是否接受恢复执行，默认为false，设置了RequestsRecovery为true，则该job会被重新执行
	 */
	@TableField(value = "REQUESTS_RECOVERY")
	private String requestsRecovery;

	/**
	 * 存放持久化job对象
	 */
	@TableField(value = "JOB_DATA")
	private byte[] jobData;

}