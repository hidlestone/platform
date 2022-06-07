package com.fallframework.platform.starter.logback.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * mybatis-plus sql日志过滤器
 *
 * @author zhuangpf
 */
public class MpSQLLoggerFilter extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {
		return "org.apache.ibatis.logging.stdout.StdOutImpl".equals(event.getLoggerName())
				? FilterReply.DENY : FilterReply.NEUTRAL;
	}

}
