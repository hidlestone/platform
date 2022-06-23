package com.fallframework.platform.starter.wechatwork.model;

/**
 * @author zhuangpf
 */
public enum SynStrategyEnum {

	/**
	 * 定时全量同步
	 */
	TIMEDFULL,
	/**
	 * 全量同步增量回调修改
	 */
	FULLCALLBACK;

}
