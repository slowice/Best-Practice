package xb.highlevelConcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Q:解决什么问题？
 * A:线程池技术节约了线程创建的时间
 * Q:怎么解决？
 * A:调用java.util.concurrent.Executors的静态工厂方法：
 * newFixedThreadPool，newCachedThreadPool，newSingleThreadExecutor和他们对应的ScheduledExecutorService版本，
 * 以及构建java.util.concurrent.ThreadPoolExecutor or java.util.concurrent.ScheduledThreadPoolExecutor
 */
public class SimpleThreadPoolTest {
    /**
     * 线程池配置策略
     * （1）CPU（计算）密集型则线程数配置尽可能的少，比如NCPU+1。可以通过Runtime.getRuntime().avaliableProcessors(  )方法获得当前设备的CPU数量；
     * （2）IO密集型需要配置尽可能多的线程数，比如2*NCPU，因为IO处理时线程阻塞的时间很长，导致CPU空闲时间很长，多一点线程可以提高CPU利用率；
     * （3）混合型任务：如果可以拆分，最好拆分成CPU密集型任务+IO密集型任务，只要这两个拆分后的任务执行时间相差没有太大，那么拆分后的吞吐量将高于串行执行的吞吐量，如果时间相差太大，就没有必要分解；
     * （4）优先级不同的任务：使用PriorityQueue作为阻塞队列。（如果一直有优先级高的任务进来，可能导致优先级低的任务无法执行）
     * （5）执行时间不同的任务：可以交给不同规模的线程池来执行；或者使用PriorityQueue作为阻塞队列，把执行时间短的任务优先级设置高一点，让时间短的任务先执行；
     * （6）建议使用有界队列，这样可以保证系统的稳定性，如果队列时无界的，那么一直有任务进来就一直往阻塞队列添加节点，可能导致内存溢出。
     */
    //
    public static void main(String[] args) {

    }
    /*FixedThreadPoll.用的较普遍，指定了使用的线程数量.LinkedBlockingQueue
        当前核心线程池总线程数量小于corePoolSize，那么创建线程并执行任务；
        如果当前线程数量等于corePoolSize，那么把 任务添加到阻塞队列中；
        如果线程池中的线程执行完任务，那么获取阻塞队列中的任务并执行；
     */
    public static void newFixedThreadPoolTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
    }
    /*SynchronousQueue
        首先执行SynchronizedQueue.offer(  )把任务提交给阻塞队列，如果这时候正好在线程池中有空闲的线程执行SynchronizedQueue.poll( )，那么offer操作和poll操作配对，线程执行任务；
        如果执行SynchronizedQueue.offer(  )把任务提交给阻塞队列时maximumPoolSize=0.或者没有空闲线程来执行SynchronizedQueue.poll( )，那么步骤1失败，那么创建一个新线程来执行任务；
        如果当前线程执行完任务则循环从阻塞队列中获取任务，如果当前队列中没有提交（offer）任务，那么线程等待keepAliveTime时间，在CacheThreadPool中为60秒，在keepAliveTime时间内如果有任务提交则获取并执行任务，如果没有则销毁线程，因此最后如果一直没有任务提交了，线程池中的线程数量最终为0。

     */
    public static void newCachedThreadPool(){

    }
    /*
        只有一个线程的线程池,常用于需要让线程顺序执行，并且在任意时间，只能有一个任务被执行，而不能有多个线程同时执行的场景
        当前核心线程池总线程数量小于corePoolSize（1），那么创建线程并执行任务；
        如果当前线程数量等于corePoolSize，那么把 任务添加到阻塞队列中；
        如果线程池中的线程执行完任务，那么获取阻塞队列中的任务并执行；
    */
    public static void newSingleThreadExecutor(){
        ExecutorService service = Executors.newSingleThreadExecutor();
    }
}
