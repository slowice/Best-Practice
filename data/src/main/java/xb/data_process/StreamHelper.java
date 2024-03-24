package xb.data_process;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xb.common.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Slf4j
public class StreamHelper {
    private static List<User> users = new ArrayList<>();
    static {
        User u1 = new User();
        u1.setIdUser("1");
        u1.setName("a");

        User u2 = new User();
        u2.setIdUser("1");
        u2.setName("b");

        User u3 = new User();
        u3.setIdUser("2");
        u3.setName("c");

        users.add(u1);
        users.add(u2);
        users.add(u3);
    }

    public static void main(String[] args) throws JsonProcessingException {
        Map<String,List<User>> result = users.stream().collect(Collectors.groupingBy(User::getIdUser));
        Result r = new Result();
        r.setData(new ObjectMapper().writeValueAsString(result));
        r.setResult("123");
        log.info(JSON.toJSONString(r));
    }

    @Data
    private static class Result{
        String result;
        Object data;
    }

}
