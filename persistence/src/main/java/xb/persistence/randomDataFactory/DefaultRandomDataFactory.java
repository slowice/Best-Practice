package xb.persistence.randomDataFactory;


import xb.common.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DefaultRandomDataFactory<T> implements RandomDataFactory{
    Class classType;
    public DefaultRandomDataFactory(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        this.classType = clazz;
    }
    //1.遍历字段 2.根据字段类型，调用set方法
    @Override
    public T create() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        T target = (T) classType.newInstance();
        Field[] fields = classType.getDeclaredFields();
        Method[] methods = classType.getDeclaredMethods();
        Map<String, Method> methodsMap = new HashMap<>();
        for (Method method : methods) {
            methodsMap.put(method.getName(), method);
        }
        for (Field f : fields) {
            String fieldName = f.getName();
            String setFiledName = "set" + StringUtils.captureName(fieldName);
            Class type = f.getType();
            Object value = "";
            if (type == String.class) {
                value = "abg";
                if (f.getName().contains("id") || f.getName().contains("Id")) {
                    value = StringUtils.getUUID();
                }
            }
            if (type == Date.class) {
                value = new Date();
            }
            if (type == int.class || type == Integer.class) {
                value = 123;
            }
            if (!org.springframework.util.StringUtils.isEmpty(value)) {
                Method method = methodsMap.get(setFiledName);
                method.invoke(target, value);
            }
        }
        return target;
    }

    @Override
    public List<T> create(int count) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        List<T> results = new ArrayList<>(count);
        for(int i=0;i<count;i++){
            T result = this.create();
            results.add(result);
        }
        return results;
    }
}
