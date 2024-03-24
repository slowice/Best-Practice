package xb.spring.event.sampleEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleBlacklistNotifier implements ApplicationListener<SampleBlackListEvent> {
    @Override
    public void onApplicationEvent(SampleBlackListEvent sampleBlackListEvent) {
        log.info("监听到了黑名单，触发黑名单");
        int i = 1/0;
    }
}
