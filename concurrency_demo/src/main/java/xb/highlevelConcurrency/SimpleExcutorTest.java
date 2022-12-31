package xb.highlevelConcurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * High-level concurrency
 * Executor框架包含3部分
 * 1.任务:实现Runnable接口或者Callable接口。后者有返回值
 * 2.执行器:
 *  2.1.Executor,ExecutorService,ScheduledExecutorService三个接口，具体关系见图"Executor接口关系"
 *  2.2.Executor是基础接口，提供execute方法。ExecutorService是Executor的子接口，可以管理执行任务和执行器本身的生命周期。
 *  ScheduledExecutorService是ExecutorService的子接口，支持future任务和周期性任务
 * 3.异步计算的结果。包括Future接口及实现了Future接口的FutureTask类。
 */
@Slf4j
public class SimpleExcutorTest {
    public static void main(String[] args) throws Exception{
        threadPoolExecutorTest();
    }

    static class SimpleCallableTask implements Callable {
        @Override
        public Object call() throws Exception {
            String result = "callable test";
            return result;
        }
    }
    static class SimpleRunnableTask implements Runnable {
        @Override
        public void run() {

        }
    }

    /**
     * ThreadPoolExecutor是Executor接口的一个重要的实现类，是线程池的具体实现，用来执行被提交的任务。
     */
    public static void threadPoolExecutorTest() throws Exception{
        /**
        corePoolSize：核心线程池大小
         maximumPoolSize：线程池大小
         keepAliveTime：线程空闲后，线程存活时间
        timeUnit：存活时间的单位
         runnalbleTaskQueue：阻塞队列.还有最后一个参数是饱和此处没写是用的默认策略。
        AbortPolicy：这是默认的策略，直接抛出异常；CallerRunsPolicy：只是用调用者所在线程来运行任务；
        DiscardOldestPolicy：丢弃队列中最老的任务，并执行当前任务；DiscardPolicy：不处理，直接把当前任务丢弃；
        */
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 10,
                100, MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        Future<String> future = tpe.submit(new SimpleCallableTask());
        try{
            System.out.println(future.get());
        } catch (Exception e){

        } finally {
            tpe.shutdown();//关闭线程池
        }
    }
}
