package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "qrtz_calendars")
public class QrtzCalendars {
	
	/**
	 * 调度名称
	 */
	@TableId(value = "SCHED_NAME", type = IdType.INPUT)
	private String schedName;

	/**
	 * 日历名称
	 */
	@TableId(value = "CALENDAR_NAME", type = IdType.INPUT)
	private String calendarName;

	/**
	 * 一个blob字段，存放持久化calendar对象
	 */
	@TableField(value = "CALENDAR")
	private byte[] calendar;

}