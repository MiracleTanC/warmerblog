
package warmer.star.blog.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloQuence")
public class RabbitMQReceiver {
	@RabbitHandler
    public void process(String message) {
        System.out.println("Receiver : " + message);
    }
}
