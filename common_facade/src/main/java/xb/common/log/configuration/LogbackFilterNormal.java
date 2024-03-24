package xb.common.log.configuration;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogbackFilterNormal extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        boolean fromXB = iLoggingEvent.getLoggerName().startsWith("xb");
        if(fromXB){
            return FilterReply.DENY;
        }
        return FilterReply.ACCEPT;
    }
}
