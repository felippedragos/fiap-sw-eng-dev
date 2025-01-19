package br.com.fiap.ExemploCrud.RabbitMQ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

	@Bean
	public Connection rabbitMQConnection() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		return factory.newConnection();
	}
	/*
	 * @Bean public Queue statusQueue() { return new Queue("statusQueue", true); }
	 */

}
