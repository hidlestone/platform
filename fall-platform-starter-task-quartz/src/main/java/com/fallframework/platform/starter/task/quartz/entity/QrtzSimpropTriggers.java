package com.fallframework.platform.starter.task.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@TableName(value = "qrtz_simprop_triggers")
public class QrtzSimpropTriggers {

	/**
	 * 调度名称
	 */
	@TableField(value = "SCHED_NAME")
	private String schedName;

	/**
	 * qrtz_triggers表trigger_ name的外键
	 */
	@TableField(value = "TRIGGER_NAME")
	private String triggerName;

	/**
	 * qrtz_triggers表trigger_group的外键
	 */
	@TableField(value = "TRIGGER_GROUP")
	private String triggerGroup;

	/**
	 * String类型的trigger的第一个参数
	 */
	@TableField(value = "STR_PROP_1")
	private String strProp1;

	/**
	 * String类型的trigger的第二个参数
	 */
	@TableField(value = "STR_PROP_2")
	private String strProp2;

	/**
	 * String类型的trigger的第三个参数
	 */
	@TableField(value = "STR_PROP_3")
	private String strProp3;

	/**
	 * int类型的trigger的第一个参数
	 */
	@TableField(value = "INT_PROP_1")
	private Integer intProp1;

	/**
	 * int类型的trigger的第二个参数
	 */
	@TableField(value = "INT_PROP_2")
	private Integer intProp2;

	/**
	 * long类型的trigger的第一个参数
	 */
	@TableField(value = "LONG_PROP_1")
	private Long longProp1;

	/**
	 * long类型的trigger的第二个参数
	 */
	@TableField(value = "LONG_PROP_2")
	private Long longProp2;

	/**
	 * decimal类型的trigger的第一个参数
	 */
	@TableField(value = "DEC_PROP_1")
	private BigDecimal decProp1;

	/**
	 * decimal类型的trigger的第二个参数
	 */
	@TableField(value = "DEC_PROP_2")
	private BigDecimal decProp2;

	/**
	 * Boolean类型的trigger的第一个参数
	 */
	@TableField(value = "BOOL_PROP_1")
	private String boolProp1;

	/**
	 * Boolean类型的trigger的第二个参数
	 */
	@TableField(value = "BOOL_PROP_2")
	private String boolProp2;

}