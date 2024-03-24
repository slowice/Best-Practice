package xb.common.utils;

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

    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    public static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
