package xb.data_process;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    // 字符串转Map
    public Map<String, String> strToMap(String json){
        return objectMapper.convertValue(json, Map.class);
    }

    public static void main(String[] args) {
        Set<String> s1 = new HashSet<>();
        s1.add("1");
        s1.add("2");
        s1.add("3");
        Set<String> s2 = new HashSet<>();
        s2.add("2");
        s2.add("3");
        s2.add("4");
       s1.removeAll(s2);

        System.out.println(s1);
    }
}
