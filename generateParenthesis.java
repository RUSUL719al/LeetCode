package HOOT100;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

示例 1：
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]

示例 2：
输入：n = 1
输出：["()"]
 
提示：
1 <= n <= 8

来源：力扣（LeetCode）

 */
public class generateParenthesis {
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        int num = 3;
        List<String> result = generateParenthesis1(num);
        System.out.print("[ ");
        for(String str : result){
            System.out.print(" " + str);
        }
        System.out.print(" ]");
    }

    public static List<String> generateParenthesis1(int num) {
        if(num == 0){
            return list;
        }
        dfs("", num, num, list);
        return list;
    }
    /**
     * 递归方式实现(深度优先法则)
     * @param str    当前递归得到的结果
     * @param left   左括号剩余数量
     * @param right  右括号剩余数量
     * @param list   需要返回的集合
     */
    public static void dfs(String str, int left, int right, List<String> list) {
        //只有左括号和右括号剩余数都为0时才放入到集合中
        if(left == 0 && right == 0){
            list.add(str);
        }
        //左括号剩余数大于右括号剩余数时跳出
        if(left > right){
            return;
        }
        if(left > 0){
            dfs(str + "(", left - 1, right, list);
        }
        if(right > 0){
            dfs(str + ")", left, right - 1, list);
        }
    }
}
