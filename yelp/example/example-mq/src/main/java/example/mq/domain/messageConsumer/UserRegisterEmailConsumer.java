package example.mq.domain.messageConsumer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterEmailConsumer implements RabbitTemplate.ConfirmCallback{


    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UserRegisterEmailConsumer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" 邮件消息，回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功消费: 邮件已发送");
        } else {
            System.out.println("消息消费失败:" + cause+"\n重新发送");

        }
    }
}
