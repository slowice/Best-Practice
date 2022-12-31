package xb;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xb.bean.User;
import xb.crud.UserController;
import xb.crud.UserService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplexCrudTest {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test() throws Exception{
        testAdd();
        testGet();
        testDel();
    }

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setIdUser("123456789");
        user.setName("abg");
        ResultActions ra = mockMvc.perform(MockMvcRequestBuilders
                .post("/crud_add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(user)));
        MvcResult mvcResult = ra.andReturn();
    }

    @Test
    public void testGet() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get(new URI("/crud_query"))
                        .param("idUser","123456789"));
        MvcResult mvcResult = resultActions.andReturn();
        String name = mvcResult.getResponse().getContentAsString();
        System.out.println(name);
    }

    @Test
    public void testDel(){
        User u1 = new User();
        u1.setName("123456");
        List<User> userList = new ArrayList<>();
        userList.add(u1);
        System.out.println(JSONObject.toJSONString(userList));
        u1.setName("654321");
        System.out.println(JSONObject.toJSONString(userList));
    }
}
