package xb.lowlevelConcurrency;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;
import xb.common_utils.PrintUtil;

import java.util.concurrent.*;

@Slf4j
/**
 * Low-level concurrency
 */
public class SimpleThreadTest {
    private static Object lock = new Object();
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    //当一条线程在持有锁的情况下，调用该锁的wait，会让该线程在该锁上等待,直到其它线程调用该锁的notify/notifyAll方法来唤醒在该锁上等待的线程
    @Test
    public void waitTest(){
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock1){
                    System.out.println("t1持有锁");
                    System.out.println("t1调用lock.wait释放锁");
                    lock1.wait();
                    System.out.println("t1重新持有锁");
                    System.out.println("t1执行完毕");
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1){
                    System.out.println("t2 持有锁");
                    //System.out.println("t2执行唤醒操作");
                    //lock1.notify();
                    //lock1.notifyAll();
                    System.out.println("t2执行完毕");
                }
            }
        });
        t1.start();
        t2.start();
    }

    //测试线程中断。中断线程有2种形式，3种方法
    //1>利用标志位
    volatile boolean flag = true;
    @Test
    public void interruptTest1() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (flag){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("等待中");
                }
                latch.countDown();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(5);
                flag = false;
                latch.countDown();
            }
        });
        t1.start();
        t2.start();
        latch.await();
    }
    //2>利用interrupt()方法来退出某些支持interrupt异常的方法.如wait sleep join
    @Test
    public void interruptTest2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    System.out.println("异常中断");
                    countDownLatch.countDown();
                }
            }
        });
        t.start();
        PrintUtil.CountDown(3);
        t.interrupt();
        countDownLatch.await();
    }
    //3>利用interrupt()方法来退出不支持interrupt异常的方法
    @Test
    public void interruptTest3() throws InterruptedException {
        Thread t = new Thread(){
            @SneakyThrows
            public void run(){
                while (!isInterrupted()){
                    System.out.println("等待中断中");
                }
                System.out.println("线程被中断结束了");
            }
        };
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
         joinTest();
    }

    public static void joinTest() throws InterruptedException {
        Thread innerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=10;i>0;i--){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("内层函数倒计时:" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        innerThread.start();
        //调用join是先执行内层函数倒计时，然后执行外层函数倒计时。注释掉join则是两个倒计时一起执行
        innerThread.join();
        for(int i=10;i>0;i--){
            TimeUnit.SECONDS.sleep(1);
            System.out.println("外层函数倒计时:" + i);
        }
    }

    //简单的测试线程的创建
    public static void test1() throws ExecutionException, InterruptedException {
        Thread a = new SimpleThreadA();
        a.setName("ThreadA");

        Thread b = new Thread(new SimpleThreadB());
        b.setName("ThreadB");

        Callable<String> call = new SimpleThreadC();
        FutureTask<String> task = new FutureTask<>(call);
        Thread c = new Thread(task);
        c.setName("ThreadC");

        a.start();
        b.start();
        c.start();

        // 调用get()阻塞主线程，反之，线程不会阻塞
        String result = task.get();
        log.info("c result is "+result);
    }

    //守护线程测试，守护线程优先级低，主线程结束，守护线程会强制结束
    public static void test2(){
        Thread a = new SimpleThreadA();
        a.setName("ThreadA");
        a.setDaemon(true);
        Thread b = new Thread(new SimpleThreadB());
        b.setName("ThreadB");
        a.start();
        b.start();
    }

    static class SimpleThreadA extends Thread{
        @Override()
        public void run(){
            for(int i=0;i<10;i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(Thread.currentThread().getName()+"***"+i);
            }
        }
    }

    static class SimpleThreadB implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                log.info(Thread.currentThread().getName()+"***"+ i);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class SimpleThreadC implements Callable{
        @Override
        public Object call() throws Exception {
            return "123";
        }
    }

    //一个线程里调用另一个线程的join方法，会让另一个线程执行完，才执行该线程。
    static class joinTestThread extends Thread{
        @Override
        public void run() {
            Thread t = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    for(int i=10;i>0;i--){
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("内层函数倒计时:" + i);
                    }
                }
            });
            t.setName("inner thread");
                try {
                    t.start();
                    //调用join是先执行内层函数倒计时，然后执行外层函数倒计时。注释掉join则是两个倒计时一起执行
                    t.join();
                    for(int i=10;i>0;i--){
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("外层函数倒计时:" + i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

}
