package com.fallframework.platform.starter.task.xxljob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "xxl_job_lock")
public class XxlJobLock {

	/**
	 * 锁名称
	 */
	@TableId(value = "lock_name", type = IdType.INPUT)
	private String lockName;
	
}