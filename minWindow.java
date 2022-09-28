package HOOT100;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
注意：
对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
 
示例 1：
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"

示例 2：
输入：s = "a", t = "a"
输出："a"

示例 3:
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
 
提示：
1 <= s.length, t.length <= 105
s 和 t 由英文字母组成
 
进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？

来源：力扣（LeetCode）

 */
public class minWindow {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC"; String t = "ABC"; //输出 ："BANC"
        //String s = "cabwefgewcwaefgcf"; String t = "cae"; //输出："cwae"
        //String s = "A"; String t = "B"; //输出 ：""
        String result = getMinWindow1(s, t);
        //String result = getMinWindow2(s, t);
        System.out.println("The Result Is : " + result);
    }
    /**
     * 待完善
     * @param s
     * @param t
     * @return
     */
    public static String getMinWindow1(String s, String t) {
        if(s == null || t == null || s.length() < t.length()){
            return "空";
        }
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        int[] mapChar = new int[123];
        for(char curChar : charT){
            mapChar[curChar]++;
        }
        int left = 0; int right = 0; int tCharsAll = charT.length; int beginIndex = 0;
        int minWindow = Integer.MAX_VALUE;
        while (right < charS.length) {
            mapChar[charS[right]]--;
            if(mapChar[charS[right]] >= 0){
                tCharsAll--;
            }
            if(tCharsAll == 0){
                while (mapChar[charS[left]] < 0) {
                    if(right - left < minWindow){
                        minWindow = right - left;
                        beginIndex = left;
                    }
                    mapChar[charS[left++]]++;
                }
                tCharsAll++;
                mapChar[charS[left++]]++;
            }
            right++;
        }
        //int endIndex = beginIndex + minWindow;
        return minWindow == Integer.MAX_VALUE ? "" : s.substring(beginIndex, beginIndex + minWindow);
    }

    /**
     * 可行方案
     * @param s
     * @param t
     * @return
     */
    public static String getMinWindow2(String s, String t){
        if(s == null || t == null || s.length() < t.length()){
            return "空";
        }
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for(char ch : t.toCharArray() ){
            need.put(ch, need.getOrDefault(ch, 0) +1 );
        }
        int left = 0, right = 0, count = 0, beginIndex = 0,  len = Integer.MAX_VALUE;
        while(right < s.length() ){
            char chR = s.charAt(right);
            right++;
            if(need.containsKey(chR) ){
                window.put(chR, window.getOrDefault(chR, 0) +1 );
                if(need.get(chR).equals(window.get(chR)) ){
                    count++;
                }
            }
            while(count == need.size() ){
                if(right - left < len){
                    len = right - left;
                    beginIndex = left;
                }
                char chL = s.charAt(left);
                left++; 
                if(need.containsKey(chL) ){
                    if(need.get(chL).equals(window.get(chL)) ){
                        count--;
                    }
                    window.put(chL, window.getOrDefault(chL, 0) -1 );
                }
            }
        }
        return len > s.length() ? "" : s.substring(beginIndex, beginIndex + len);
    }

}

    
