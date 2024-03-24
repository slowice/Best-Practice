package xb.spring.event.asyncEvent;

import org.springframework.context.ApplicationEvent;

public class AsyncBlackListEvent extends ApplicationEvent  {
    private String address;
    private String content;

    public AsyncBlackListEvent(Object source, String address, String content) {
        super(source);
        this.address = address;
        this.content = content;
    }
}
