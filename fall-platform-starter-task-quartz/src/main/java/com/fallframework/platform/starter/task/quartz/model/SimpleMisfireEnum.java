package com.fallframework.platform.starter.task.quartz.model;

/**
 * 中断恢复策略枚举
 *
 * @author zhuangpf
 */
public enum SimpleMisfireEnum {

	IGNORE_MISFIRES("-1", "以当前时间为触发频率立刻触发一次执行"),
	FIRE_NOW("1", "失效之后再恢复并马上执行"),
	NOW_WITH_EXISTING_COUNT("2", "以当前时间为触发频率立即触发执行"),
	NOW_WITH_REMAINING_COUNT("3", "以当前时间为触发频率立即触发执行,执行至FinalTIme的剩余周期次数"),
	NEXT_WITH_REMAINING_COUNT("4", "不触发立即执行,等待下次触发频率周期时刻"),
	NEXT_WITH_EXISTING_COUNT("5", "不触发立即执行,等待下次触发频率周期时刻");

	private String key;
	private String value;

	SimpleMisfireEnum(String key, String value) {
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
