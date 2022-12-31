package xb.mybatis.dao;

import bean.User;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import dto.UserDTO;
import dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import xb.ApplicationTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class UserMapperExternalTest extends ApplicationTests {
    //04106a036fad497f97b1ec14ef05c5fd
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserMapperExternal userMapperExternal;

    static final int COUNT = 5;
    static List<User> users;

    //@Before
    public void setUp() throws Exception {
        //RandomDataFactory userFactory = new RandomDataFactory(User.class);
        //users = userFactory.create(COUNT);
        //userMapperExternal.insertBatch(users);
    }

    //@After
    public void tearDown() throws Exception {
       // userMapperExternal.deleteBatch(users);
    }

    //@Test
    public void deleteBatchTest(){
        //删除
        int i =userMapperExternal.deleteBatch(Arrays.asList(users.get(0),users.get(1)));
        Assert.assertEquals(2, i);
        //删完再查，确保没了
        List<User> userList = userMapperExternal.selectBatchById(Arrays.asList(users.get(0),users.get(1)));
        Assert.assertTrue(CollectionUtils.isEmpty(userList));
    }

    //@Test
    /**
     * 值得注意的是，在if设置条件的时候，传递值要单引号在外层，双引号在内层！如 <if test='status == "1"'>
     */
    public void ifTest(){
        UserRequest query = new UserRequest();
        query.setTypeList(Arrays.asList("1"));
        List<User> result = userMapperExternal.ifTest(query);
        Assert.assertFalse(CollectionUtils.isEmpty(result));
        log.info(JSON.toJSONString(result));
    }

    //@Test
    /**
     * 设置map-underscore-to-camel-case=true，可以在sql中不设置select ** as **中的as **，
     * 可以自动根据数据库中的字段，根据驼峰命名来匹配实体类
     */
    public void autoMappingTest(){
        User query = new User();
        query.setIdUser("04106a036fad497f97b1ec14ef05c5fd");
        UserDTO user = userMapperExternal.autoMappingTest(query);
        Assert.assertFalse(StringUtils.isEmpty(user.getMobilePhone()));
    }

    //@Test
    /**
     * #{}采用的是预编译模式，会自动进行类型替换。适用于传递参数，可以防sql注入攻击。
     * ${}采用的是字符串拼接，适用于字段名或者表明。用这个方式想传递参数需要在外层加引号
     */
    public void sharpAndDollorTest(){
        List<UserDTO> user = userMapperExternal.sharpSelect("04106a036fad497f97b1ec14ef05c5fd");
        log.info("*************" + user.get(0).getIdUser());

        List<UserDTO> user2 = userMapperExternal.dollorSelect("04106a036fad497f97b1ec14ef05c5fd");
        log.info("*************" + user2.get(0).getIdUser());
    }

    //@Test
    /**
     * complex query
     * 包含has one， has many，discriminator，constructor的测试
     */
    public void complexQueryTest(){
        UserRequest request = new UserRequest();
        request.setId("04106a036fad497f97b1ec14ef05c5fd");
        List<UserDTO> result = userMapperExternal.complexQueryTest(request);
        log.info(JSON.toJSONString(result));
    }

    //@Test
    public void selectPagedTest(){
        PageHelper.startPage(2,2);
        List<UserDTO> userDTOS = userMapperExternal.selectAllPaged();
        Assert.assertTrue(userDTOS.size()>0);
    }

    //@Test
    public void myTest(){
        List<UserDTO> userDTOS = userMapperExternal.test();
        Assert.assertTrue(userDTOS.size()>0);
    }

    private ExecutorService threadpool = Executors.newFixedThreadPool(30);

    //@Test
    public void threadTest(){
        CompletionService<List<UserDTO>> execComp = new ExecutorCompletionService<>(threadpool);
        List<Callable<List<UserDTO>>> list = new ArrayList<>();
    }
}