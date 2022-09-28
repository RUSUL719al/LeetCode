package HOOT100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。

示例 1:
输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。

示例 2:
输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。

提示:
1 <= s.length, p.length <= 3 * 104
s 和 p 仅包含小写字母

来源：力扣（LeetCode）

 */
public class findAnagrams {
    public static void main(String[] args) {
        String s = "cbebabacd"; String p = "abc";
        List<Integer> result = getAnagrams(s, p);
        System.out.println(result.toString());
    }
    //不会做
    public static List<Integer> getAnagrams(String s, String p) {
        char[] charS = s.toCharArray();
        char[] charP = p.toCharArray();
        int lenP = charP.length;
        int lenS = charS.length;
        // int[] pCunt = new int[26];
        // int[] sCunt = new int[26];
        List<Integer> list = new ArrayList<>();
        // for(char ch : p.toCharArray()){
        //    pCunt[ch - 'a']++;
        // }
        // for(char ch : s.toCharArray()){
        //     sCunt[ch - 'a']++;
        //  }
        //  if(Arrays.equals(pCunt, sCunt)){
        //     list.add(0);
        //  }
         
        for(int i = 0, j = i+lenP; j < lenS;){
            char[] cur = new char[lenP];
            s.getChars(i++, j++, cur, 0);
            if(Arrays.equals(cur, charP)){
                list.add(i);
            }
        }
        
        return list;
    }
}
