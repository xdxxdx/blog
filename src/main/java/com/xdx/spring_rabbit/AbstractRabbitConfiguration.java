package com.xdx.spring_rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * 抽象类，rabbitMQ的主配置类
 * 
 * @author xdx
 *
 */
public abstract class AbstractRabbitConfiguration {

	@Value("${amqp.port:5672}")
	private int port = 5672;

	protected abstract void configureRabbitTemplate(RabbitTemplate template);

	/**
	 * 由于connectionFactory会与项目中的redis的connectionFactory命名冲突，
	 * 所以这边改名为rabbit_connectionFactory
	 * 
	 * @return
	 */
	@Bean
	public ConnectionFactory rabbit_connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
				"192.168.1.195");
		connectionFactory.setUsername("xdx");
		connectionFactory.setPassword("wonyen@1602");
		connectionFactory.setPort(port);
		return connectionFactory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(rabbit_connectionFactory());
		template.setMessageConverter(jsonMessageConverter());
		configureRabbitTemplate(template);
		return template;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbit_connectionFactory());
		return rabbitAdmin;
	}
}
