package HOOT100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
示例 1:
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 
提示：
0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成

来源：力扣（LeetCode）
 */
public class lengthOfLongestSubstring {
    public static void main(String[] args) {
        String str = "abcabcbb";
        int result = lengthOfLongestSubstring2(str);
        System.out.println("The Result Is : " + result);
        
    }
    public static int lengthOfLongestSubstring1(String str) {
        int length = str.length();
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int left = 0, right = 0; right < length; right++){
            char currChar = str.charAt(right);
            if(map.containsKey(currChar)){
                left = Math.max(map.get(currChar) + 1, left);
            }
            max = Math.max(max, right - left + 1);
            map.put(currChar, right);
        }
        return max;
    }

    public static int lengthOfLongestSubstring2(String str) {
        int left = 0, right = 0, len = 0, maxLen = 0;
        List<Character> list = new ArrayList<>();
        while(right < str.length()){
            if(!list.contains(str.charAt(right))){
                list.add(str.charAt(right));
                len ++;
                maxLen = Math.max(len, maxLen);
                right ++;
            }else{
                while (list.contains(str.charAt(right))) {
                    list.remove(list.indexOf(str.charAt(left)));
                    len --;
                    left ++;
                }
                list.add(str.charAt(right));
                len ++;
                right ++;
            }
        }
        return maxLen;
    }
}
