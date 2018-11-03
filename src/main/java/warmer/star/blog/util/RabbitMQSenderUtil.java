package warmer.star.blog.util;

import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
/*public class RabbitMQSenderUtil implements RabbitTemplate.ConfirmCallback{

	@Autowired
	private RabbitTemplate rabbitTemplate;
 
	public void send(String topic, String message) {
		rabbitTemplate.setConfirmCallback(this);
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		
		System.out.println("消息id:" + correlationData.getId());
		//用RabbitMQ发送MQTT需将exchange配置为amq.topic
		this.rabbitTemplate.convertAndSend("amq.topic", topic, message, correlationData);
	}
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("消息id:" + correlationData.getId());
		if (ack) {
			System.out.println("消息发送确认成功");
		} else {
			System.out.println("消息发送确认失败:" + cause);
		}
	}

}*/
