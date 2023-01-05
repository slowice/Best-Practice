package xb.log.configuration;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogbackFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        boolean fromXB = iLoggingEvent.getLoggerName().startsWith("xb");
        if(fromXB){
            return FilterReply.ACCEPT;
        }
        return FilterReply.DENY;
    }
}
