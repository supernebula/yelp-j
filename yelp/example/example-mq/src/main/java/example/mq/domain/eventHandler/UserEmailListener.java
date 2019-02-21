package example.mq.domain.eventHandler;


import example.mq.domain.event.UserRegisterEvent;
import example.mq.domain.message.UserRegisterEmailMessage;
import example.mq.domain.model.UserBean;
import example.mq.web.config.RabbitMQConfig;
import org.apache.catalina.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.UUID;

@Component
public class UserEmailListener implements  RabbitTemplate.ConfirmCallback{

    RabbitTemplate rabbitTemplate;

    @Autowired
    public UserEmailListener(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送邮件监听实现
     * @param userRegisterEvent 用户注册事件
     */
    @EventListener
    public void sendMail(UserRegisterEvent userRegisterEvent)
    {
        UserBean user = userRegisterEvent.getUser();
        System.out.println(UserRegisterListener.class + ":@EventListener邮件消息，" + user.getUsername() +  "用户注册成功，发送邮件" + user.getEmail() + "。");

        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);

        UserRegisterEmailMessage msg = new UserRegisterEmailMessage();
        msg.setUsername(user.getUsername());
        msg.setEmail(user.getEmail());
        msg.setSubject("恭喜！注册成功！");
        msg.setContent(user.getUsername() + ", 恭喜！ 注册成功！" + (new Date()).getTime());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.EMAIL_ROUTINGKEY, msg,
                correlationId);
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
