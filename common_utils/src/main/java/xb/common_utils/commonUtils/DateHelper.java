package xb.common_utils.commonUtils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

public class DateHelper {
    //日期转字符串
    public static String printDate(Date date){
        return (FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss").format(date));
    }

//    public static void parseDate(Long time){
//        System.out.println(FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss").format(new Date(time)));
//    }
    //字符串转日期
}
