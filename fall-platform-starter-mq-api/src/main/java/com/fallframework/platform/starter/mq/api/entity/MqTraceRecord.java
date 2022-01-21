package com.fallframework.platform.starter.mq.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fallframework.platform.starter.data.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName(value = "s_mq_trace_record")
public class MqTraceRecord extends BaseEntity {

	private static final long serialVersionUID = 2820447711594611227L;
	
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	/**
	 * 阶段
	 */
	@TableField(value = "stage")
	private String stage;

	/**
	 * 消息体JSON
	 */
	@TableField(value = "content")
	private String content;

	/**
	 * 交换机
	 */
	@TableField(value = "exchange")
	private String exchange;

	/**
	 * 路由
	 */
	@TableField(value = "route_key")
	private String routeKey;

	/**
	 * 投递TAG
	 */
	@TableField(value = "delivery_tag")
	private String deliveryTag;

	/**
	 * 消费TAG
	 */
	@TableField(value = "consumer_tag")
	private String consumerTag;

	/**
	 * ACK模式
	 */
	@TableField(value = "ack_mode")
	private String ackMode;

	/**
	 * 发布时间
	 */
	@TableField(value = "publish_time")
	private Date publishTime;

	/**
	 * 消费时间
	 */
	@TableField(value = "consume_time")
	private Date consumeTime;

	/**
	 * 订阅者
	 */
	@TableField(value = "subscriber")
	private String subscriber;

	/**
	 * 创建用户ID
	 */
	@TableField(value = "create_user_id")
	private Long createUserId;

	/**
	 * 修改用户ID
	 */
	@TableField(value = "modify_user_id")
	private Long modifyUserId;

	/**
	 * 创建时间
	 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**
	 * 更改时间
	 */
	@TableField(value = "gmt_modified")
	private Date gmtModified;
	
}