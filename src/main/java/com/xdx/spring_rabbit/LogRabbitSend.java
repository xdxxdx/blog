package com.xdx.spring_rabbit;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.xdx.entity.TLog;

/**
 * 用于发送日志消息的通用实现类
 * 
 * @author xdx
 *
 */
//@Component("logRabbitSend")
public class LogRabbitSend implements RabbitSend<TLog> {
	@Resource(name = "rabbitTemplate")
	private RabbitTemplate rabbitTemplate;

	@Override
	public void send(TLog log) {
		rabbitTemplate.convertAndSend(log);
		System.err.println("发送消息：" + log);
	}
}
