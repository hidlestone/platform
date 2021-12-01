package com.fallframework.platform.starter.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 键值匹配
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KV {
	/**
	 * 键
	 */
	private String key;

	/**
	 * 值
	 */
	private Object value;
}
