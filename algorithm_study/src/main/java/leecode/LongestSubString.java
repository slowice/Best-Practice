package leecode;

import org.springframework.util.CollectionUtils;

import java.util.*;

/*
    此题其实考察的是max的用法，将字符串遍历一遍确实能得出最长值，关键是利用max来规避重复判断的问题
 */
public class LongestSubString {
    public static int lengthOfLongestSubstring_1(String s){
        char[] charArr = s.toCharArray();
        Set<Character> temp = new HashSet<>();
        int count = 0, max=0;
        for(int i=0;i<charArr.length;i++){
            if(temp.add(charArr[i])){
                count++;
                if(max < count){
                    max = count;
                }
            } else {
                count=0;
                temp.clear();
                temp.add(charArr[i]);
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring_2(String s){
        char[] charArr = s.toCharArray();
        Set<Character> temp = new LinkedHashSet<>();

        for(int i=0;i<charArr.length;i++){
            if(!temp.add(charArr[i])){
                temp.remove(charArr[i]);
                temp.add(charArr[i]);
            }
        }
        return temp.size();
    }

    public static int lengthOfLongestSubstring_right(String s){
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0; // 从1开始的
        for(int i = 0; i < s.length(); i ++){ // i从0开始的
            char currentChar = s.charAt(i); // 当前字符
            if(map.containsKey(currentChar)){ // 如果出现重复字符
                left = Math.max(left, map.get(currentChar) + 1); // 左游标更新为当前值和当前字符的的坐标值的最大值
            }
            map.put(currentChar, i); // 将当前字符和对应坐标存入map
            max = Math.max(max, (i+1)-left); // 更新最大长度为当前值和
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "pwakewa";
        System.out.println(lengthOfLongestSubstring_2(s));
    }
}
