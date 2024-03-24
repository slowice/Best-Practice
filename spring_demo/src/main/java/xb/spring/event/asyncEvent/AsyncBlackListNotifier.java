package xb.spring.event.asyncEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncBlackListNotifier {


    @EventListener
    @Async
    public void test(AsyncBlackListEvent event) {
        log.info("监听到了事件！!!!!!{}",event);
        int i = 1/0;
    }
}
