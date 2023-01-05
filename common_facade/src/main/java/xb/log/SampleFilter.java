package xb.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 配置这个过滤器可以选择性的打印某些log
 */
public class SampleFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        //待排除的包的路径
        String excludePackage = "xb";

        String logger = event.getLoggerName();
        //不包含该路径则不打印
        if (!logger.contains(excludePackage)) {
            return FilterReply.DENY;
        } else {
            return FilterReply.ACCEPT;
        }
    }
}
