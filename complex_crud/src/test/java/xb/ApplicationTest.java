package xb;

import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.context.WebApplicationContext;
import xb.entity.User;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTest {
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void testAdd() throws Exception {
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
}