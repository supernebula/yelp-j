package example.mq.domain.message;

import lombok.Data;
import java.io.Serializable;

/**
 *
 *
 * 注意：传递自定义对象消息，比如User, User类一定要实现Serializable接口，否则会报如下错误：
 *  [cTaskExecutor-1] s.a.r.l.ConditionalRejectingErrorHandler : Execution of Rabbit message listener failed.
 * Caused by: org.springframework.amqp.AmqpException: No method found for class [B
 * 并可能导致死循环。
 *
 *
 *
 */
@Data
public class UserRegisterEmailMessage implements Serializable {
    private String email;
    private String username;
    private String subject;
    private String content;
}
