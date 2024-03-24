package xb.spring.event;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Data
@Slf4j
public class EmailService implements ApplicationEventPublisherAware {
    private static List<String> blackList;
    private ApplicationEventPublisher publisher;

    static{
        blackList = Arrays.asList("123.com","456.com");
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void sendEmail(String address,String content){
        log.info("邮件发送开始");

        if(blackList.contains(address)){
            //publisher.publishEvent(new AsyncBlackListEvent<User>(new User()));
            //return;
        }
        log.info("邮件发送成功");
    }
}
