package com.fallframework.platform.starter.mq.rabbit.config;

import com.fallframework.platform.starter.mq.entity.MqTraceLog;
import com.fallframework.platform.starter.mq.model.StageEnum;
import com.fallframework.platform.starter.mq.service.MqTraceLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 生产者到RabbitMQ交换机<br/>
 * confirm 主要是用来判断消息是否有正确到达交换机, 如果有, 那么就 ack 就返回 true<br/>
 * 默认的消息投递到broker确认模式<br/>
 * 用来判断消息是否被ACK
 *
 * @author payn
 */
@Component
public class DefaultConfirmCallback implements RabbitTemplate.ConfirmCallback {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfirmCallback.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private MqTraceLogService mqTraceLogService;

	@PostConstruct
	public void init() {
		rabbitTemplate.setConfirmCallback(this);
	}
	
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
			// 发送失败
			LOGGER.info("[fall platform] send message to exchange failed : " + cause + correlationData.toString());
		} else {
			// 更新记录状态
			LOGGER.info("[fall platform] send message to exchange success.");
		}
		// 确认的消息ID
		String id = correlationData.getId();
		if (StringUtils.isBlank(id)) {
			LOGGER.error("correlationData id is not exist.");
			return;
		}
		// 更新该记录的状态
		MqTraceLog mqTraceLog = mqTraceLogService.select(Long.valueOf(id)).getData();
		if (null == mqTraceLog) {
			LOGGER.error("mqTraceLog is not exist, id : {}", id);
		}
		if (ack) {
			mqTraceLog.setStage(StageEnum.DELIVER_EXCHANGE_SUCCESS);
		} else {
			mqTraceLog.setStage(StageEnum.DELIVER_EXCHANGE_FAIL);
		}
		mqTraceLogService.update(mqTraceLog);
	}

}
