package com.xdx.spring_rabbit;
/**
 * 定义一个泛型接口，用于发送消息，T为要发送的消息类型
 * @author xdx
 *
 * @param <T>
 */
public interface RabbitSend<T> {
	void send(T t);
}
