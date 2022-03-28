package com.fallframework.platform.starter.api.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuangpf
 */
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String column;
	private boolean asc = true;

	public static OrderItem asc(String column) {
		return build(column, true);
	}

	public static OrderItem desc(String column) {
		return build(column, false);
	}

	public static List<OrderItem> ascs(String... columns) {
		return (List) Arrays.stream(columns).map(OrderItem::asc).collect(Collectors.toList());
	}

	public static List<OrderItem> descs(String... columns) {
		return (List) Arrays.stream(columns).map(OrderItem::desc).collect(Collectors.toList());
	}

	private static OrderItem build(String column, boolean asc) {
		return new OrderItem(column, asc);
	}

	public String getColumn() {
		return this.column;
	}

	public boolean isAsc() {
		return this.asc;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof OrderItem)) {
			return false;
		} else {
			OrderItem other = (OrderItem) o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$column = this.getColumn();
				Object other$column = other.getColumn();
				if (this$column == null) {
					if (other$column == null) {
						return this.isAsc() == other.isAsc();
					}
				} else if (this$column.equals(other$column)) {
					return this.isAsc() == other.isAsc();
				}

				return false;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof OrderItem;
	}

	public String toString() {
		return "OrderItem(column=" + this.getColumn() + ", asc=" + this.isAsc() + ")";
	}

	public OrderItem() {
	}

	public OrderItem(String column, boolean asc) {
		this.column = column;
		this.asc = asc;
	}
}
