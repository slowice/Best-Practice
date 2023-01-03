package xb.transaction;

import xb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import xb.common_utils.PrintUtil;
import xb.dao.UserMapper;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.springframework.transaction.annotation.Isolation.*;

@Service
public class TransactionTestServiceImpl implements TransactionTestService {
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    TransactionTemplate transactionTemplate;

    public static String DEFAULT_USER_ID="04106a036fad497f97b1ec14ef05c5fd";

    @Resource
    private UserMapper mapper;

    @Transactional
    @Override
    public void updateUserNameWithException() {
        User u = new User();
        u.setIdUser(DEFAULT_USER_ID);
        u.setName("A1");
        mapper.updateByPrimaryKey(u);
        PrintUtil.CountDown(10);
        int i = 1/0;
    }

    @Transactional
    @Override
    public void updateUserName() throws InterruptedException {
        String before = mapper.selectByPrimaryKey(DEFAULT_USER_ID).getName();
        System.out.println("更新前name is "+before);
        User u = new User();
        u.setIdUser(DEFAULT_USER_ID);
        u.setName("A1");
        mapper.updateByPrimaryKey(u);
        String after = mapper.selectByPrimaryKey(DEFAULT_USER_ID).getName();
        System.out.println("更新后name is "+after);
    }

    @Override
    public void testRollBackForException(User user) {
        mapper.insert(user);
        throw new NullPointerException();
    }

    @Transactional
    @Override
    public void testRollBackForExceptionWithAnnotation(User user) {
        mapper.insert(user);
        throw new NullPointerException();
    }

    @Override
    public void testReadUncommitted() {
        //不方便自己调用自己，测试方法见测试类。
    }
    @Transactional(isolation = READ_UNCOMMITTED)
    @Override
    public void getUserNameUncommitted(){
        String userName = mapper.selectByPrimaryKey(DEFAULT_USER_ID).getName();
        System.out.println("userName is : "+userName);
    }

    @Override
    public void testReadCommitted() {
        //不方便自己调用自己，测试方法见测试类。
    }
    @Transactional(isolation = READ_COMMITTED)
    @Override
    public void getUserNameCommitted(){
        String userName = mapper.selectByPrimaryKey(DEFAULT_USER_ID).getName();
        System.out.println("userName is : "+userName);
    }

    //@Transactional(isolation = READ_COMMITTED)
    @Override
    public void testUnRepeatableRead() throws InterruptedException {
        /*
        在代码中测试不可重复读失败，在控制台中测试成功。
        SET TRANSACTION ISOLATION LEVEL READ COMMITED;
        START TRANSACTION;
        SELECT *******查看结果
        改变对应数据
        再select查看结果
        发现两次结果数据不同。但是在REPEATABLE READ的事务隔离级别下，两次查询的结果相同。
         */



//        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
//        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
//        //TransactionStatus status = transactionManager.getTransaction(definition);
//        try {
//            List<User> before = mapper.selectAll();
//            System.out.println("the before unrepeatable name is " + before);
//            PrintUtil.CountDown(10);
//            List<User> after = mapper.selectAll();
//            System.out.println("the after unrepeatable name is " + after);
//        } catch (Exception ex) {
//           // transactionManager.rollback(status);
//            throw ex;
//        }
        //transactionManager.commit(status);

//        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(TransactionStatus status) {
//                try {
//                    List<User> before = mapper.selectAll();
//                    System.out.println("the before unrepeatable name is " + before);
//                    PrintUtil.CountDown(10);
//                    List<User> after = mapper.selectAll();
//                    System.out.println("the after unrepeatable name is " + after);
//                }catch (Exception e){
//                    status.setRollbackOnly();
//                }
//            }
//        });
    }
    @Transactional(isolation = REPEATABLE_READ)
    @Override
    public void testRepeatableRead() throws InterruptedException {
        String userName1 = mapper.selectByPrimaryKey(DEFAULT_USER_ID).getName();
        System.out.println("the repeatable name1 is "+ userName1);
        TimeUnit.SECONDS.sleep(10);
        String userName2 = mapper.selectByPrimaryKey(DEFAULT_USER_ID).getName();
        System.out.println("the repeatable name2 is "+ userName2);
    }

    @Transactional(isolation = SERIALIZABLE)
    @Override
    public void testSerializable() {

    }

    @Override
    public void testRequired() {

    }

    @Override
    public void testRequiredNew() {

    }

    @Override
    public void testNested() {

    }
}
