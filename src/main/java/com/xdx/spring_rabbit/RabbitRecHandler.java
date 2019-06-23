package com.xdx.spring_rabbit;

/**
 * 用于处理监听到的消息的消息处理器接口，T为接收到的消息的类型
 * 
 * @author xdx
 *
 * @param <T>
 */
public interface RabbitRecHandler<T> {
	void handleMessage(T t);
}
