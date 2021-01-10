package xb.event.sampleEvent;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class SampleBlackListEvent extends ApplicationEvent {
    private String address;
    private String content;

    public SampleBlackListEvent(Object source, String address, String content) {
        super(source);
        this.address = address;
        this.content = content;
    }
}
