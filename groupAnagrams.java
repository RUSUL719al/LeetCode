package HOOT100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

示例 1:
输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

示例 2:
输入: strs = [""]
输出: [[""]]

示例 3:
输入: strs = ["a"]
输出: [["a"]]
 
提示：
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] 仅包含小写字母

来源：力扣（LeetCode）

 */
public class groupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = gropSameStr(strs);
        for(List<String> list : result){
            System.out.print("[");
            for(String str : list){
                System.out.print(" " + str);
            }
            System.out.print("]");
        }
    }
    //把每一个字符串进行排序，如果存在字母异位词，那他们的排序结果一定一样。
    //声明一个集合，key为排序后的字符串，value为组合字母异位词的集合
    public static List<List<String>> gropSameStr(String[] strs) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length ; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            //String key = chars.toString();    数组转字符串不能用toString
            String key = String.valueOf(chars);  
            if (!map.containsKey(key)) {
                //如果map中不存在则把排序后的字符串当做key，空list当做value放进去
                map.put(key, new ArrayList<>());
            }
            //不管在不在map集合中，获取排序后的字符串对应的集合，并把当前遍历到的字符串放进去
            map.get(key).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }
}
