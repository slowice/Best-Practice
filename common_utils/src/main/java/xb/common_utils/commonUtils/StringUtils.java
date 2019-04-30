package xb.common_utils.commonUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StringUtils {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return (uuid.toString().replaceAll("-",""));
    }

    //string to char[]
    public static Set<Character> strToChars(String str){
        Set<Character> set = new HashSet<>();
        for(int i=0;i<str.length();i++){
            set.add(str.charAt(i));
        }
        return set;
    }
}
