package com.fallframework.platform.starter.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 返回前端的分页查询数据
 *
 * @author zhuangpf
 */
@Getter
@Setter
public class Leaf<T> implements Serializable {

	private static final long serialVersionUID = -4371588110597992171L;

	protected List<T> records;
	protected long total;
	protected long current;

}
