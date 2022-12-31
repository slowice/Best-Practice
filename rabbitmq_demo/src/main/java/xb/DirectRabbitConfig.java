package xb;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue TestDirectQueue(){
        return  new Queue("TestDirectQueue",true);
    }


}
