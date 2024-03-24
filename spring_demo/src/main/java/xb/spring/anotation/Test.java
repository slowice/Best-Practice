package xb.spring.anotation;

import xb.common.entity.User;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
public class Test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        user.setIdUser("123");
        boolean b = validateBean(user);
        log.info(JSON.toJSONString(b));
    }

    //根据实体类的注解来校验对应字段规则
    private static boolean validateBean(Object obj) throws InvocationTargetException, IllegalAccessException {
        Field[] fieldArr = obj.getClass().getDeclaredFields();
        for(Field field:fieldArr){
            //字段名称
            String fieldName = field.getName();
            //get方法拼接
            String getName = "get" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length());
            Method[] methods = obj.getClass().getMethods();
            Object filedValue = null;
            //校验NotEmpty
            if(field.isAnnotationPresent(NotEmpty.class)){
                for(Method method:methods){
                    if(method.getName().equals(getName)){
                        filedValue = method.invoke(obj);
                        return !StringUtils.isEmpty(filedValue);
                    }
                }
            }
        }
        return true;
    }

    private static boolean validateParam(Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = obj.getClass().getMethods();
        for(Method method:methods){
            if(method.isAnnotationPresent(ParamNotNull.class)){
                Parameter[] orgs = method.getParameters();
                for(Parameter p : orgs){
                    if(null == p ){
                        throw new RuntimeException("1231231231");
                    }
                }
            }
        }
        return true;
    }
}
