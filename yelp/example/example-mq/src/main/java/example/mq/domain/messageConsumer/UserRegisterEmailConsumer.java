package example.mq.domain.messageConsumer;

import example.mq.domain.message.UserRegisterEmailMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import example.mq.web.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

/**
 * 这里模拟消息消费者（通常为另一个系统）
 */
@Component
@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_EMAIL, containerFactory="rabbitListenerContainerFactory")
public class UserRegisterEmailConsumer{

    /**
     * 注意：传递自定义对象消息，比如User, User类一定要实现Serializable接口，否则会报如下错误：
     *  [cTaskExecutor-1] s.a.r.l.ConditionalRejectingErrorHandler : Execution of Rabbit message listener failed.
     * Caused by: org.springframework.amqp.AmqpException: No method found for class [B
     * 并可能导致死循环。
     * @param message
     */
    @RabbitHandler
    public void process(UserRegisterEmailMessage message) {
        System.out.println("Receiver : " + message);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}
