package warmer.star.blog.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.host}")
	private String addresses;
 
	@Value("${spring.rabbitmq.port}")
	private String port;
 
	@Value("${spring.rabbitmq.username}")
	private String username;
 
	@Value("${spring.rabbitmq.password}")
	private String password;
 
	@Value("${spring.rabbitmq.virtual-host}")
	private String virtualHost;
 
	@Value("${spring.rabbitmq.publisher-confirms}")
	private boolean publisherConfirms;
 
	@Value("${spring.rabbitmq.queues}")
	private String queues;
 
	final static String EXCHANGE_NAME = "amq.topic";
	final static String QUEUE_NAME = "helloQuence";
	final static String ROUTING_KEY = "helloQuence";

 
	@Bean
	public ConnectionFactory connectionFactory() {
 
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(addresses + ":" + port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		/** 如果要进行消息回调，则这里必须要设置为true */
		connectionFactory.setPublisherConfirms(publisherConfirms);
		return connectionFactory;
	}
 
	@Bean
	/** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		return template;
	}
 
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}
 
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true);
	}
 
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
	}
	
 
	@Bean
	public SimpleMessageListenerContainer messageContainer() {
		String[] queueArray=queues.split(",");
		Queue[] q = new Queue[queueArray.length];
		for (int i = 0; i < queueArray.length; i++) {
			q[i] = new Queue(queueArray[i]);
		}
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
		container.setQueues(queue());
		//container.setQueues(q);
		container.setExposeListenerChannel(true);
		container.setMaxConcurrentConsumers(1);
		container.setConcurrentConsumers(1);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(new ChannelAwareMessageListener() {
 
			public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
				try {
					System.out.println(
							"消费端接收到消息:" + message.getMessageProperties() + ":" + new String(message.getBody()));
					System.out.println("topic:"+message.getMessageProperties().getReceivedRoutingKey());
					// deliveryTag是消息传送的次数，我这里是为了让消息队列的第一个消息到达的时候抛出异常，处理异常让消息重新回到队列，然后再次抛出异常，处理异常拒绝让消息重回队列
					/*if (message.getMessageProperties().getDeliveryTag() == 1
							|| message.getMessageProperties().getDeliveryTag() == 2) {
						throw new Exception();
					}*/
 
					channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // false只确认当前一个消息收到，true确认所有consumer获得的消息
				} catch (Exception e) {
					e.printStackTrace();
 
					if (message.getMessageProperties().getRedelivered()) {
						System.out.println("消息已重复处理失败,拒绝再次接收...");
						channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
					} else {
						System.out.println("消息即将再次返回队列处理...");
						channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // requeue为是否重新回到队列
					}
				}
			}
		});
		return container;
	}

}
