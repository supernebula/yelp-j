package example.mq.domain.event;

import example.mq.domain.model.UserBean;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class UserRegisterEvent extends ApplicationEvent {

    private UserBean user;

    public UserRegisterEvent(Object source, UserBean user) {
        super(source);
        this.user = user;
    }
}
