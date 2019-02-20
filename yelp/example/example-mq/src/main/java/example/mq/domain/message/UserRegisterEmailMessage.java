package example.mq.domain.message;

import lombok.Data;

@Data
public class UserRegisterEmailMessage {
    private String email;
    private String username;
    private String subject;
    private String content;
}
