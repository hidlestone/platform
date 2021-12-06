package com.fallframework.platform.starter.mq.api;

public interface IMQPublisher<T> {

	boolean publish(T info);

}
