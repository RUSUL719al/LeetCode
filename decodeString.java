package HOOT100;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

示例 1：
输入：s = "3[a]2[bc]"
输出："aaabcbc"

示例 2：
输入：s = "3[a2[c]]"
输出："accaccacc"

示例 3：
输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"

示例 4：
输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"

提示：
1 <= s.length <= 30
s 由小写英文字母、数字和方括号 '[]' 组成
s 保证是一个 有效 的输入。
s 中所有整数的取值范围为 [1, 300] 

来源：力扣（LeetCode）

 */
public class decodeString {
    public static void main(String[] args) {
        String str = "2[abc3[cd]c]ef";
        String result = getDecodeString(str);
        System.out.println("The Result Is : " + result);
    }
    public static String getDecodeString(String str) {
        char[] chars = str.toCharArray();
        String[] result = dp(chars, 0);
        //String[] result = dfs(str, 0);
        return result[0]; 
    }
    
    /**
     * 创建一个递归函数：
     * 1.参数1为给定字符串的字符数组；参数2为下标开始位置
     * 2.返回值说明：递归函数返回从index位置开始到右括号或者遍历完的结果和遍历到的位置 
     * @param chars
     * @param index
     * @return
     */
    public static String[] dp(char[] chars, int index) {
        StringBuilder resSb = new StringBuilder();
        int curNum = 0;
        while (index < chars.length) {
            if(chars[index] >= '0' && chars[index] <= '9'){
                curNum = curNum * 10 + chars[index++] - '0';
            }else if(chars[index] == '['){
                String[] res = dp(chars, index+1);
                index = Integer.parseInt(res[1]) + 1;
                while (curNum > 0) {
                    resSb.append(res[0]);
                    curNum --;
                }
            }else if(chars[index] == ']'){
                return new String[]{resSb.toString(), String.valueOf(index)};
            }else{
                resSb.append(chars[index++]);
            }
        }
        return new String[]{resSb.toString(), String.valueOf(index)};
    }
}
