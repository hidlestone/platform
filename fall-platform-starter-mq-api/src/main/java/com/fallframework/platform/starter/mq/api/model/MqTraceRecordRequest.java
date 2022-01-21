package com.fallframework.platform.starter.mq.api.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MqTraceRecordRequest extends BasePageRequest {

	/**
	 * 阶段
	 */
	private String stage;

	/**
	 * 交换机
	 */
	private String exchange;

	/**
	 * 路由
	 */
	private String routeKey;

}
