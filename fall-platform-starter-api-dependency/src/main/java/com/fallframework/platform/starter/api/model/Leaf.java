package com.fallframework.platform.starter.api.model;

import com.fallframework.platform.starter.api.request.BasePageRequest;
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

	/**
	 * 分页数据
	 */
	protected List<T> records;
	/**
	 * 总记录数
	 */
	protected Integer total;
	/**
	 * 总页数
	 */
	protected Integer size;
	/**
	 * 当前页码
	 */
	protected Integer current;

	public Leaf() {
	}

	public Leaf(List<T> records, Integer total, Integer size, Integer current) {
		this.records = records;
		this.total = total;
		this.size = size;
		this.current = current;
	}

	public Leaf(List<T> records, Integer total, BasePageRequest request) {
		this.records = records;
		this.total = total;
		this.size = total % request.getPageSize() + 1;
		this.current = request.getPageNum();
	}

}
