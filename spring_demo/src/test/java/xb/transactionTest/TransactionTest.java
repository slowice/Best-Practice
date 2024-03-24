package xb.transactionTest;

import org.junit.jupiter.api.Test;
import xb.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import xb.ApplicationTest;
import xb.spring.transaction.TransactionTestService;

import java.util.concurrent.TimeUnit;

public class TransactionTest extends ApplicationTest {
    @Autowired
    TransactionTestService transactionTestService;

    //测试基于xml的事务配置，抛nullpointer exception 数据插入会失效。expected属性会捕捉异常使测试成功
    @Test()
    public void testRollBackForException(){
        User u = new User();
        u.setIdUser("X1106a036fad497f97b1ec14ef05c5fd");
        u.setName("测试插入异常回滚");
        transactionTestService.testRollBackForException(u);
    }

    //测试基于注解的事务配置
    public void testRollBackForExceptionWithAnnotation(){
        User u = new User();
        u.setIdUser("X1106a036fad497f97b1ec14ef05c5fd");
        u.setName("X1");
        transactionTestService.testRollBackForExceptionWithAnnotation(u);
    }

    //测试读未提交和脏读现象.先启动读线程。数据库中数据为A。然后启动写线程，将数据改为A1但是没提交，而此时读线程也读到这个没提交到的A1。然后写线程回滚。读线程此时读到的数据又变为A
    @Test
    public void testReadUncommitted() throws Exception {
        Thread change = new Thread(new Runnable() {
            @Override
            public void run() {
                transactionTestService.updateUserNameWithException();
            }
        });
        //不能再service内部循环查询。这样会得到相同的查询结果。在外部循环调用service的话，就可以看到change线程还未提交的数据改变
        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0;i<20;i++){
                        transactionTestService.getUserNameUncommitted();
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        read.start();
        TimeUnit.SECONDS.sleep(3);
        change.start();
        TimeUnit.SECONDS.sleep(25);
    }

    //和测试读未提交的逻辑基本一致，只是将隔离级别改为读未提交，此时写线程改变数据，但是不提交的话，读线程是读不到的
    @Test
    public void testReadCommitted() throws Exception {
        Thread change = new Thread(new Runnable() {
            @Override
            public void run() {
                transactionTestService.updateUserNameWithException();
            }
        });
        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0;i<20;i++){
                        transactionTestService.getUserNameCommitted();
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        read.start();
        TimeUnit.SECONDS.sleep(3);
        change.start();
        TimeUnit.SECONDS.sleep(25);
    }

    //todo
    @Test
    public void testUnRepeatableRead() throws InterruptedException {
//        Thread repeatable_read = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    transactionTestService.testRepeatableRead();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Thread unrepeatable_read = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    transactionTestService.testUnRepeatableRead();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        Thread wtrite = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    transactionTestService.updateUserName();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        unrepeatable_read.start();
//        TimeUnit.SECONDS.sleep(2);
//        wtrite.start();
//        TimeUnit.SECONDS.sleep(20);

  //      transactionTestService.testUnRepeatableRead();
//        TimeUnit.SECONDS.sleep(2);
//        transactionTestService.updateUserName();
//        TimeUnit.SECONDS.sleep(20);
    }

    //todo
    @Test
    public void testHuanRead(){

    }

    public void testPropagation(){

    }
}
