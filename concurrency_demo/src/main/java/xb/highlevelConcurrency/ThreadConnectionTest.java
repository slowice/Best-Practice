package xb.highlevelConcurrency;

import lombok.SneakyThrows;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 线程通信测试
 */
public class ThreadConnectionTest {
    public static void main(String[] args) throws Exception {
        //countDownLatchTest();
        cyclicBarrierTest();
    }

    //CountDownLatch
    public static void countDownLatchTest() throws InterruptedException {
        //5条线程ABCDE，ABCD执行完，执行E
        CountDownLatch countDownLatch = new CountDownLatch(4);
        for(int i=1;i<=4;i++){
            Thread t = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    for(int i=5;i>0;i--){
                        System.out.printf("%s doing work %s %n",Thread.currentThread().getName(),i);
                        TimeUnit.SECONDS.sleep(1);
                    }
                    countDownLatch.countDown();
                }
            });
            t.setName("thread"+i);
            t.start();
        }
        countDownLatch.await();
        Thread e = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for(int i=5;i>0;i--){
                    System.out.printf("%s doing work %s %n",Thread.currentThread().getName(),i);
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        });
        e.setName("threadE");
        e.start();
    }

    //CyclicBarrier可以控制线程执行顺序
    public static void cyclicBarrierTest() throws Exception{
        CyclicBarrier barrier1 = new CyclicBarrier(2);
        CyclicBarrier barrier2 = new CyclicBarrier(2);

        //按照ACB的顺序执行线程
        //A执行完会释放栏杆1，B执行到方法前会释放栏杆1.栏杆1顺利打开，A完毕，C继续。C执行完会释放栏杆2，B开头会释放栏杆2.栏杆2顺利打开，B继续,C完毕
        Thread A = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for(int i=5;i>0;i--){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.printf("%s 正在工作 %s %n",Thread.currentThread().getName(),i);
                }
                barrier1.await();
            }
        });
        A.setName("ThreadA");
        Thread B = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                barrier2.await();
                for(int i=5;i>0;i--){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.printf("%s 正在工作 %s %n",Thread.currentThread().getName(),i);
                }
            }
        });
        B.setName("ThreadB");
        Thread C = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                barrier1.await();
                for(int i=5;i>0;i--){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.printf("%s 正在工作 %s %n",Thread.currentThread().getName(),i);
                }
                barrier2.await();

            }
        });
        C.setName("ThreadC");
        A.start();
        B.start();
        C.start();
    }
}
