package xb;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import xb.crud.UserService;
import xb.entity.User;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableTransactionManagement
class ApplicationTest {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Assert.assertNotNull(userService);
    }

    @Test
    @Order(1)
    public void testAdd() throws Exception {
        log.info("hahahahahah");
        User user = new User();
        user.setIdUser("123456789");
        user.setName("abg");
        MvcResult mvcResult = mockMvc.perform(
                        post("/crud_add")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(JSONObject.toJSONString(user)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(2)
    public void testGet() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(new URI("/crud_query"))
                .param("idUser", "123456789"));
        MvcResult mvcResult = resultActions.andReturn();
        String name = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals(name, "abg");
    }

    @Test
    @Order(3)
    public void testDel() throws Exception {
        User user = new User();
        user.setIdUser("123456789");
        MvcResult mvcResult = mockMvc.perform(
                        post("/crud_delete")
                        .param("idUser", "123456789")
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * 由于id重复此处批量新增会导致异常，从而触发回滚
     * 另外如果使用了JPA的addall(),会强制要求开启事务管理
     */
    @Test
    @Transactional
    public void testAddBatch() throws Exception{
        User u1 = new User();
        u1.setIdUser("1");
        User u2 = new User();
        u2.setIdUser("2");
        User u3 = new User();
        u3.setIdUser("2");
        List<User> userList = Arrays.asList(u1,u2,u3);
        userService.addBatch(userList);
    }
}