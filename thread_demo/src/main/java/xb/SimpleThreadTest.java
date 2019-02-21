package xb;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class SimpleThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       test1();
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
        System.out.println("c result is "+result);
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
                System.out.println(Thread.currentThread().getName()+"***"+i);
            }
        }
    }

    static class SimpleThreadB implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"***"+ i);
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
}
