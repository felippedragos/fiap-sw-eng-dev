package br.com.fiap.ExemploCrud.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Service
public class StatusPublisher {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void publishStatus(String status) throws Exception {
		RabbitMQConfig config = new RabbitMQConfig();		
		Connection connection = config.rabbitMQConnection();
		Channel channel = connection.createChannel();
		String queueName = "statusQueue";
		channel.queueDeclare(queueName, true, false, false, null);
		channel.basicPublish("", queueName, null, status.getBytes());
		rabbitTemplate.convertAndSend("statusQueue", status);
	}
}
