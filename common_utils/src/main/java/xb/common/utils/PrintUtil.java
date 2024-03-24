package xb.common.utils;

import java.util.concurrent.TimeUnit;

public class PrintUtil {
    /**
     * 倒计时
     * @param time
     */
    public static void CountDown(int time){
        for(int i=time;i>0;i--){
            try {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
