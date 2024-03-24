package xb.persistence.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xb.common.entity.User;
import xb.persistence.mybatis.dao.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;
    @Autowired
    UserService service;
    @Autowired
    UserService2 service2;
    @Autowired
    UserService3 service3;

    @Override
    //切记要加rollbackFor，或者抛出runtimeException
    @Transactional(rollbackFor = Exception.class)
    /**
     * 测试注意0 如果异常被catch，则事务无效.所以异常要抛出，先 调用内层服务，然后抛出外层异常
     * 测试注意1 注解与异常同时存在，同时消失
     * 测试注意2 情况只分两种，外层有事务，外层无事务。分析基于这两种情况，对内层事务的影响(传播特性改变是写在内层的注解上的)
     * 若接口分离 A调用B。
     * 1.REQUIRES
     * 外层有事务，则不论内外，只要有异常都回滚。
     * 外层无事务,则会在外层新建一个事务，内外事务统一，只要有异常，都会回滚
     * 2.REQUIRES_NEW
     * 内外事务独立分开，互不影响
     * 外层无事务,内层有异常会回滚，不影响外层
     * 外层有事务，外层异常不会干预内层,哪边有异常，哪边回滚
     * 3.Propagation.NESTED
     * 外层无事务,则会在外层新建一个事务，内外事务统一;
     * 外层有事务，外层回滚会影响内层。内层如果有异常回滚，则由于异常打断程序执行，所以外层的操作也无效了
     */
    public void proganationTest(List<User> user) throws Exception {
        User user1 = new User();
        user1.setIdUser("04106a036fad497f97b1ec14ef05c5fd");
        user1.setName("user1");
        User user2 = new User();
        user2.setIdUser("0f18b56d73f24659b305d0af486c3f74");
        user2.setName("user2");
        User user3 = new User();
        user3.setIdUser("xx118b56d73f24659b305d0af6c3f74");
        user3.setName("user3");
        service3.updateTransactional(user3);

        service2.updateTransactional(user2);
        updateTransactional(user1);
        //int i=1/0;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTransactional(User user) throws Exception{
        mapper.updateByPrimaryKey(user);
        //int i=1/0;
    }
}
