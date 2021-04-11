package xb.temp;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
/**
 * 1.事务放顶头，这样的话，用this调用方法是否可以去掉注入自己来完成事务
 * 2.async注解这样用是否有效，如果有效，连接池是在哪里配置的
 * 3.
 */
public class Temp{
    @Async
    public void test(String msg){
        this.handle(msg);
    }

    private void handle(String msg){
        //distributelock.lock();
        doo(msg);
        //distributelock.unlock();
    }

    private void doo(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
        System.out.println("5"+2);
    }
}
