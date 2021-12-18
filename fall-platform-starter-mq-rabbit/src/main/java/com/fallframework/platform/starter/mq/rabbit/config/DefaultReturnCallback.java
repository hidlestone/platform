package com.fallframework.platform.starter.mq.rabbit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认的消息投递到queue失败回退模式
 *
 * @author payn
 */
public class DefaultReturnCallback implements RabbitTemplate.ReturnCallback {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReturnCallback.class);

//	@Autowired
//	private MqLogService mqLogService;

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		// 发送失败，更新mq日志状态 TODO
		// 这样如果未能投递到目标 queue 里将调用 returnCallback ，可以记录下详细到投递数据，定期的巡检或者自动纠错都需要这些数据。
//		mqLogService.update(null);
		LOGGER.info("[wordplay] message loss:exchange({}),route({}),replyCode({}),replyText({}),message:{}",
				exchange, routingKey, replyCode, replyText, message);
	}
}
