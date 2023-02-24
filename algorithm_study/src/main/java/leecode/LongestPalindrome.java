package leecode;

public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        String sub = "";
        for (int i = 0; i < s.length(); i++) {
            sub = s.substring(i);
            if (isPalidrome(sub)) {
                return sub;
            }
        }
        return sub;
    }

    private static boolean isPalidrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == s.charAt(end)) {
            if (end <= start) {
                return true;
            }
            start++;
            end--;
        }
        return false;
    }

    /**
     * P(i,j) = P(i+1,j-1)&&S(i)==S(j)
     * P(i,i) = true
     * P(i,i+1) = S(i)==S(i+1)
     */
    private static String solution(String s) {
        int length = s.length();
        if (length < 2) { // 长度为1时，一定为回文
            return s;
        }
        boolean[][] result = new boolean[length][length];
        char[] arr = s.toCharArray();
        for (int i = 0; i < length; i++) {
            result[i][i] = true;
        }
        int max = 1;
        int begin = 0;
        for (int L = 2; L <= length; L++) { // 注意此处为<=
            for (int i = 0; i < length; i++) {
                int j = L + i - 1;
                if (j >= s.length()) {
                    break;
                }
                if (arr[i] != arr[j]) {
                    result[i][j] = false;
                } else {
                    if (L == 2) {
                        result[i][j] = true; // 长度为2时，因为arr[i]==arr[i]，所以直接为true
                    } else {
                        result[i][j] = result[i + 1][j - 1];
                    }

                }
                if (result[i][j]) {// 如果结果为回文，则更新max和begin的值
                    max = L;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    public static void main(String[] args) {
        String s = "bb";

        System.out.println(solution(s));
    }
}
