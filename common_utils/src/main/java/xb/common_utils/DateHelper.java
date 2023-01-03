package xb.common_utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

public class DateHelper {
    /**
        日期转字符串 yyyy-MM-dd hh:mm:ss
        use lang3
     */
    public static String printDate(Date date){
        return FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss").format(date);
    }

    /**
     * 字符串转日期
     * use lang3
     */
    public static Date strToDate(String str) throws ParseException {
        return FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss").parse(str);
    }

    /**
        见测试类
     */
    public static void LocalDateDemo(){};
}
