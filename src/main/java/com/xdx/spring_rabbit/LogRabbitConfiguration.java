package com.xdx.spring_rabbit;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志管理的Rabbit配置类的具体实现类
 * 
 * @author xdx
 *
 */
//@Configuration
public class LogRabbitConfiguration extends AbstractRabbitConfiguration {
	protected static String LOG_EXCHANGE_NAME = "warrior.exchange.log";// topic
																		// exchange的名称
	protected static String LOG_QUEUE_NAME = "warrior.queue.log";// 接收消息的queue
	protected static String LOG_ROUTING_KEY = LOG_QUEUE_NAME;
	@Autowired
	private LogRabbitRecHandler logRabbitRecHandler;// 监听器的委托类，委托其处理接收到的消息

	/**
	 * 设置Exchange为LOG_EXCHANGE_NAME，RoutingKey为LOG_ROUTING_KEY，这样将信息发送到
	 * Exchange为LOG_EXCHANGE_NAME，RouteKey为LOG_ROUTING_KEY的通道中
	 */
	@Override
	protected void configureRabbitTemplate(RabbitTemplate template) {
		System.err.println("创建一个RabbitTemplate，名字是 " + template);
		template.setExchange(LOG_EXCHANGE_NAME);
		template.setRoutingKey(LOG_ROUTING_KEY);
	}

	/**
	 * 用于接收日志消息的Queue,默认绑定自己的名称
	 * 
	 * @return
	 */
	@Bean
	public Queue logQueue() {
		return new Queue(LOG_QUEUE_NAME);
	}

	/**
	 * 定义一个topExchange
	 * 
	 * @return
	 */
	@Bean
	public TopicExchange logExchange() {
		return new TopicExchange(LOG_EXCHANGE_NAME);
	}

	/**
	 * 定义一个绑定日志接收的Queue的binding
	 * 
	 * @return
	 */
	@Bean
	public Binding logQueueBinding() {
		return BindingBuilder.bind(logQueue()).to(logExchange())
				.with(LOG_ROUTING_KEY);
	}

	/**
	 * 这个bean为监听适配器，用于日志消息，并交由logRabbitRecHandler处理
	 * 
	 * @return
	 */
	@Bean
	public MessageListenerAdapter messageListenerAdapter() {
		return new MessageListenerAdapter(logRabbitRecHandler,
				jsonMessageConverter());
	}

	/**
	 * 这个bean用于监听服务端发过来的消息，监听的Queue为logQueue()，
	 * 因为该Queue绑定了logExchange和logRouteKey， 所以它可以接收到我们发送的日志消息
	 * @return
	 */
	@Bean
	public SimpleMessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(
				rabbit_connectionFactory());
		container.setConcurrentConsumers(5);
		container.setQueues(logQueue());
		container.setMessageListener(messageListenerAdapter());
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return container;
	}
}
