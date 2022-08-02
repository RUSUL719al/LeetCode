package HOOT100;
/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。

示例 1：
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。

示例 2：
输入：s = "cbbd"
输出："bb"
 

提示：
1 <= s.length <= 1000
s 仅由数字和英文字母组成

来源：力扣（LeetCode）
 */
public class longestPalindrome {
    public static void main(String[] args) {
        String str = "abcdaeghgkiy";
        String result = longestPalindrome1(str);
        System.out.println("The Result Is : " + result);
    }
    //中心扩散法
    public static String longestPalindrome1(String str) {
        if (str == null || str.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < str.length(); i++) {
            int len1 = expandAroundCenter(str, i, i);
            int len2 = expandAroundCenter(str, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return str.substring(start, end + 1);

    }
    public static int expandAroundCenter(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

}
