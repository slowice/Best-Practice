package xb.common_utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.common_utils.commonUtils.DateHelper;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DateHelperTests {

    @Test
    public void printDate(){
        Date date = new Date();
        String dateStr = DateHelper.printDate(date);
        System.out.println(dateStr);
    }

    @Test
    public void strToDate() throws ParseException {
        String str = "2000-01-01 12:12:21";
        //String str2 = "2000-01-01"; 日期格式不合规会报错
        Date date = DateHelper.strToDate(str);
        System.out.println(DateHelper.printDate(date));
    }

    @Test
    public void localDate(){
        LocalDate localDate = LocalDate.now();
        log.info(localDate.toString());
    }

}
