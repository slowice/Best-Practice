package xb;

/**
 * low-level
 * 1.什么是同步，什么是异步。
 * 2.什么是串行，什么是并行。
 * 3.什么是Concurrency（并发）。
 *
 *  常用的和线程有关的方法
 *  sleep:必须捕捉InterruptedException异常(当父线程执行完了，子线程还在沉睡时)。常用于：暂停线程，让程序先执行其它任务。
 *  wait
 *  join    当一个线程执行t.join(),会让该线程暂停，直到t运行结束。
 *  yield
 *  Interrupts：该方法是告诉线程应该停止正在执行的任务。具体是否停止由程序执行。常用于停止一个线程
 *  sleep和join都可以设置超时时间，但是到达超时后，需要等到CPU调度，才能变成运行状态。
 *
 *  线程是支持中断的
 *      1.InterruptedException，若捕捉到该异常，则执行中断方法
 *      2.  调用Thread.interrupt()方法设置中断标志，然后
 *      if (Thread.interrupted()) {
 *              throw new InterruptedException();
 *          }
 *      3.Thread.interrupted和isInterrupted的异同：都是用来检查中断标志的。前者是静态方法，并且会清楚中断标志。后者不会清除中断标志
 *
 * 创建线程实例有三种方法：
 *  1.新建一个类，继承Thread，然后实例化该类，用start()方法启动该类。
 *  2.新建一个类，实现Runnable接口，然后实例化一个Thread类，并将前者的实例作为构造器参数传给Thread
 *  3.新建一个类，实现Callable接口，然后
 * 三种方法的优劣：
 *  方法1：代码简单，但是占用了继承位置
 *  方法2：和方法1比，将任务和线程分离出来，更加灵活，并且也比较适合High-level线程管理API。和方法三比，不能抛出异常
 *  方法3：可以抛出异常，并且有返回值
 *
 * 线程的运行状态：
 *  1.new。新建状态
 *  2.Runnable。可运行状态。调用start()进入该状态
 *  3.Running。运行状态。可运行状态的线程获取了cpu时间片后，进入运行状态。
 *  4.blocked。阻塞状态。线程因为某些原因放弃了当前的cpu使用权，也让出了cpu时间片，直到重新进入可运行状态，才有机会获得时间片从而转到运行状态
 *      进入阻塞状态的原因可以分为三种：
 *      1>等待阻塞：运行中线程调用了o.wait(),jvm则会把该线程放入等待队列中。
 *      2>同步阻塞：运行中线程在尝试获取锁，而锁被其它线程占有，jvm会将该线程放入锁池中。
 *      3>其它阻塞：运行中线程执行了sleep，join方法或者发出了i/o请求时，线程会进入阻塞状态，当sleep超时，join等待线程终止或者超时，io处理完毕时，线程重新转入可运行状态
 *  5.dead。线程方法体运行结束或因异常而退出
 *
 * 线程同步：
 * 线程通信主要靠共享字段和共享对象引用？但是会出现线程冲突和内存一致性错误。解决这些问题的方法，就是同步。同步会造成线程争夺的问题，多个线程尝试获取同一资源时，程序执行速度会变慢。
 * 线程冲突：当多条线程同时交错操作一条数据时，会发生不可预知的错误。
 * 内存一致性错误：不同线程读取同一数据出现不同结果。造成这种错误的原因是复杂的，要知道如果避免这种问题。避免这种问题的关键就在于理解happens-before原则。
 *
 * java提供2种同步方式：同步方法和同步代码块。二者都是获取对象锁。对象锁支持重复获取。
 * 同步方法  在方法前添加synchronized关键字。
 *          1>有2个用途：1.一条线程访问该方法时，其它线程都会阻塞。2.先访问该方法的happens-before后访问的。
 *          2>保证了同步方法中所改变的状态的内存可见性。另外构造方法不能添加关键字。因为只有创建该对象的线程才能完成这个操作。同步方法简单好用，但是会造成活性问题liveness。
 *          3>当一条线程调用同步方法，则自动持有该方法所属对象的锁。如果该方法是静态方法，则是持有class object对应的锁
 * 同步代码块
 *      这种方式更加细颗粒化，需要单独提供对象锁。
 *
 * 原子操作
 *      指要么操作全部成功，要么全部不成功。
 * 死锁
 *      多条线程互相等待对方释放锁，导致一直处于阻塞状态就是死锁
 *      1.避免一条线程同时获取多把锁
 *      2.一个锁只占用一个资源
 *      3.尝试使用定时锁，使用lock.trylock来代替使用隐式锁
 *      4.对于数据库锁，加锁和解锁在同一链接里
 *
 * 饥饿
 *      假如一个同步方法需要很长时间执行。当一条线程经常访问这个方法时，其它线程就很难获取锁
 * 活锁
 *      两条线程，会对对方的响应而做出响应，因此导致线程一直忙碌。
 * 不可变对象
 *      如何构建不可变对象？https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html
 */

/**
 * high-level
 * jdk提供五个高级特性
 * 1.LockObject
 * 2.Executor Framework
 * 3.Concurrent Collections
 * 4.Atomic variables
 * 5.ThreadLocalRandom
 *
 * 资源限制的挑战
 * 并发编程时，程序的执行速度受限于计算机硬件资源或软件资源。比如服务器带宽只有2mb/s，某资源下载速度是1mb/s，系统启用10条线程进行下载，下载速度也不会变为10mb/s
 *
 * 1>Lock Object。
 *      一条线程同一时间只能持有一个锁。Lock和Condition搭配使用也可以实现等待通知模型
 *      和隐式锁对比，最大的优势是，提供返回机制。如tryLock，lockInterruptibly
 * 2>ExecutorFramework
 *      1.Executor框架提供三个接口
 *      Executor：基础接口，提供execute方法.用于执行任务
 *      ExecutorService：Executor的子接口添加了管理任务和执行器本身生命周期的方法。有execute和submit两个方法
 *      ScheduledExecutorService：ExecutorService的子接口，ExecutorService的子接口。支持Future和周期性任务执行
 *      2.Thread Pools
 *      concurrent包提供Executors工厂，用于创建线程池
 *      3.Fork/Join
 *      //todo
 * 3>Concurrent Collections
 *      concurrent包对java集合框架做了一些补充，用于在多线程环境下使用
 *      BlockingQueue
 *      ConcurrentHashMap
 *      ConcurrentNavigableMap
 *      ConcurrentSkipListMap
 * 4>concurrent.atomic包提供了对单一变量的原子操作
 */

/**
 * Further Reading
 * Concurrent Programming in Java: Design Principles and Pattern (2nd Edition) by Doug Lea. A comprehensive work by a leading expert, who's also the architect of the Java platform's concurrency framework.
 * Java Concurrency in Practice by Brian Goetz, Tim Peierls, Joshua Bloch, Joseph Bowbeer, David Holmes, and Doug Lea. A practical guide designed to be accessible to the novice.
 * Effective Java Programming Language Guide (2nd Edition) by Joshua Bloch. Though this is a general programming guide, its chapter on threads contains essential "best practices" for concurrent programming.
 * Concurrency: State Models & Java Programs (2nd Edition), by Jeff Magee and Jeff Kramer. An introduction to concurrent programming through a combination of modeling and practical examples.
 * Java Concurrent Animated: Animations that show usage of concurrency features
 */
public class ConcurrencyOverview {
}
