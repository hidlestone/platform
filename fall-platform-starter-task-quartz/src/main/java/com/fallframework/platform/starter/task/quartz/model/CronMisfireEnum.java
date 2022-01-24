package com.fallframework.platform.starter.task.quartz.model;

/**
 * CORN触发器失败策略枚举
 */
public enum CronMisfireEnum {

	IGNORE_MISFIRES("-1", "重做错过的所有频率周期"),
	FIRE_ONCE_NOW("1", "以当前时间为触发频率立刻触发一次执行"),
	DO_NOTHING("2", "不触发立即执行");

	private String key;
	private String value;

	CronMisfireEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
