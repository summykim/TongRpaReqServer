package com.skcc.tongrpa;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.skcc.tongrpa.mq.MqMessageListener;



@SpringBootApplication
public class TongrpaApplication {

    
	public final static String MESSAGE_QUEUE_NAME = "tong-rpa-queue";
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
	    return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	@Bean
	Queue queue() {
		return new Queue(MESSAGE_QUEUE_NAME, true,false,false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("tong-rpa-exchange",true,true);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		///임시 
		String bindkey="agent.result.*";
		return BindingBuilder.bind(queue).to(exchange).with(bindkey);// agent id  bind
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(MESSAGE_QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	@Bean
	MessageListenerAdapter listenerAdapter(MqMessageListener receiver) {
		return new MessageListenerAdapter (receiver, "receiveMessage");
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(TongrpaApplication.class, args);
	}

}
