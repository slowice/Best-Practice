package xb.data_process;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class DataConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    // 字符串转Map
    public Map<String, String> strToMap(String json){
        return objectMapper.convertValue(json, Map.class);
    }
}
