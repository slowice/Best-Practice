package xb.data_process;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class CollectionHelper {
    public static void main(String[] args) {
        List<String> whole = Arrays.asList("1","2","3","3");
        Set<String> set = new HashSet<>(whole);
        log.info(JSON.toJSONString(set));
    }

    @Data
    private static class Student{
        private String name;
        private String id;

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Student)){
                return false;
            }
            if(this.id.equals(((Student)obj).getId())){
                return true;
            }
            return false;
        }
    }
}
