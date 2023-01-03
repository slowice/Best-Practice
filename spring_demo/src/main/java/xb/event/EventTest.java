package xb.event;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.test.context.junit4.SpringRunner;
import xb.event.asyncEvent.AsyncBlackListEvent;
import xb.event.sampleEvent.SampleBlackListEvent;

@SpringBootTest
@Slf4j
public class EventTest implements ApplicationEventPublisherAware{
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Test
    public void test1(){
        publisher.publishEvent(new SampleBlackListEvent(this,"",""));
    }

    @Test
    public void test2(){
        publisher.publishEvent(new AsyncBlackListEvent(this,"",""));
    }
}
