package com.fallframework.platform.starter.task.xxljob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName(value = "xxl_job_log_report")
public class XxlJobLogReport {

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 调度-时间
	 */
	@TableField(value = "trigger_day")
	private Date triggerDay;

	/**
	 * 运行中-日志数量
	 */
	@TableField(value = "running_count")
	private Integer runningCount;

	/**
	 * 执行成功-日志数量
	 */
	@TableField(value = "suc_count")
	private Integer sucCount;

	/**
	 * 执行失败-日志数量
	 */
	@TableField(value = "fail_count")
	private Integer failCount;

	@TableField(value = "update_time")
	private Date updateTime;
    
}