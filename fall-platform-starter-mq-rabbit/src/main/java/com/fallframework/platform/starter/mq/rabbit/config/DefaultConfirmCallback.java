package com.fallframework.platform.starter.mq.rabbit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认的消息投递到broker确认模式
 *
 * @author payn
 */
public class DefaultConfirmCallback implements RabbitTemplate.ConfirmCallback {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfirmCallback.class);

	@Autowired
//	private MqLogService mqLogService;

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
			// 发送失败
			LOGGER.info("[fall platform] send message failed : " + cause + correlationData.toString());
		} else {
			// 更新记录状态
//			mqLogService.updatetateByMessageId(correlationData.getId(), MqLogStatusEnum.PUBLISH);
			LOGGER.info("[fall platform] send message success.");
		}
	}

}
