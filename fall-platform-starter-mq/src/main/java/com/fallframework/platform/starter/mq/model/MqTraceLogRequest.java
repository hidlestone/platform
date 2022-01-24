package com.fallframework.platform.starter.mq.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhuangpf
 */
@Getter
@Setter
public class MqTraceLogRequest extends BasePageRequest {

	private static final long serialVersionUID = -5439688402841283381L;

	/**
	 * 阶段
	 */
	private StageEnum stage;

	/**
	 * 消息体JSON
	 */
	private String content;

	/**
	 * 交换机
	 */
	private String exchange;

	/**
	 * 路由
	 */
	private String routeKey;

	/**
	 * 投递TAG
	 */
	private String deliveryTag;

	/**
	 * 消费TAG
	 */
	private String consumerTag;

	/**
	 * ACK模式
	 */
	private String ackMode;

	/**
	 * 发布时间
	 */
	private Date publishTime;

	/**
	 * 消费时间
	 */
	private Date consumeTime;

	/**
	 * 订阅者
	 */
	private String subscriber;

	/**
	 * 请求ID
	 */
	private Long requestId;

	/**
	 * 状态：0-失败，1-成功
	 */
	private Byte status;

	/**
	 * 开始发布时间
	 */
	private Date startPublishTime;

	/**
	 * 结束发布时间
	 */
	private Date endPublishTime;

	/**
	 * 开始消费时间
	 */
	private Date startConsumeTime;

	/**
	 * 结束消费时间
	 */
	private Date endConsumeTime;

}
