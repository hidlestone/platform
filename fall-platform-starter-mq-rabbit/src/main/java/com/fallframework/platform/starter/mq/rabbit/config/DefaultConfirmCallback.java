package com.fallframework.platform.starter.mq.rabbit.config;

import com.fallframework.platform.starter.mq.service.MqTraceLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认的消息投递到broker确认模式<br/>
 * 用来判断消息是否被ACK
 *
 * @author payn
 */
@Component
public class DefaultConfirmCallback implements RabbitTemplate.ConfirmCallback {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfirmCallback.class);

	@Autowired
	private MqTraceLogService mqTraceLogService;

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
			// 发送失败
			LOGGER.info("[fall platform] send message failed : " + cause + correlationData.toString());
		} else {
			// 更新记录状态
			LOGGER.info("[fall platform] send message success.");
		}
	}

}
