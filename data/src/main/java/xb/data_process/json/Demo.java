package xb.data_process.json;

import bean.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import xb.common_utils.commonUtils.StringUtils;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Demo {
    private static User user;

    public static void main(String[] args) {
        user = new User();
        user.setIdUser(StringUtils.getUUID());
        user.setName("abg");
        List<User> users = Arrays.asList(user,user,user);
        //重复引用
        log.info(JSON.toJSONString(users));
        //解决重复引用
        String jsonUser2 = JSON.toJSONString(users, SerializerFeature.DisableCircularReferenceDetect);
        log.info(jsonUser2);
    }
}
