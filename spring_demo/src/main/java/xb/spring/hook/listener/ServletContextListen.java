package xb.spring.hook.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * servletContext创建于销毁的监听
 * 利用WebListener监听
 */
@Component
@WebListener
@Slf4j
public class ServletContextListen implements ServletContextListener {
    private Timer timer;
    public void contextDestroyed(ServletContextEvent arg0) {
        // 该方法在Web容器关闭时执行
        log.info("Web应用程序启动关闭...");
    }
    public void contextInitialized(ServletContextEvent arg0) {
        // 在Web容器启动时自动执行该方法
        log.info("Web应用程序启动...");
        timer = new Timer();//②-1:创建一个Timer，Timer内部自动创建一个背景线程
        TimerTask task = new SimpleTimerTask();
        timer.schedule(task, 1000L, 5000L); //②-2:注册一个5秒钟运行一次的任务
    }
    class SimpleTimerTask extends TimerTask {//③任务
        private int count;
        public void run() {
           log.info("father****************");
        }
    }
}
