package xb.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类列举String的各类问题
 */
public class StringDemo {
    // 1.利用正则表来处理String
    public static String matchRex(String str, String rex) {
        String result = "";


        // 匹配双引号间的内容
        str = "issueName => 5,142 counts";
        rex = "\"([^\"]*)\"";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group(0).replaceAll(rex, "$1"));
        }

        // 匹配数字
        str = "\"issueName\" => 5,142 counts";
        str = str.substring(str.indexOf("=>"));
        str = str.replaceAll(",","");
        rex = "[^0-9]+";
        pattern = Pattern.compile(rex);
        matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.replaceAll("").trim());
        }
        return result;
    }


    public static void main(String[] args) {
        matchRex("", "");
    }
}
