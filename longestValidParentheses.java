package HOOT100;
/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
示例 1：
输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"

示例 2：
输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"

示例 3：
输入：s = ""
输出：0

提示：
0 <= s.length <= 3 * 104
s[i] 为 '(' 或 ')'

来源：力扣（LeetCode）

 */
public class longestValidParentheses {
    public static void main(String[] args) {
        String s = ")()())";
        int result = getLongestValidParentheses(s);
        System.out.println("The Result Is : " + result);
    }
    /*
    使用动态规划的思路:
    dp[i]表示以i处结尾的子字符串中最长有效括号长度
    初始化dp[0] 为零，因为不管是哪种括号，单个括号
    都不是有效括号
    状态转移:
        1. i处的字符为 '(' 的话，无法与i之前的字符进行
            配对，因此dp[i] = 0
        2. i处的字符为 ')'的话，查看i-1处的字符
            如果i-1处为 '(' 的话，正好与i处的 ')'配对
            因此dp[i] = dp[i-2]+2
            如果i-1处为 ')'，查看i-1处的 ')'是不是
            属于i处之前的有效括号(既dp[i-1]是否大于零)
            如果dp[i-1] > 0的话，查看dp[i-1]的前一个
            字符(既 i-1 - dp[i-1]+1 - 1)能否与i处的 ')' 配对
            如果能 则 i-1 - dp[i-1]+1 - 1处 记作 pre_left处
            就是 '('  ，因此 dp[i] = dp[i-1]+2，
            重点:因为根据题意求的是连续有效括号的长度
            所以如果满足pre_left处就是 '(' 则需要加上
            dp[pre_left - 1] 因此此时:
            dp[i] = dp[i] + dp[pre_left - 1]
    求最大:
            每次得出dp[i]后更新maxLen, 最后返回   
    */
    public static int getLongestValidParentheses(String s){
        int len = s.length(); int maxLen = 0;
        int[] dp = new int[len];
        //dp[0] = 0
        for(int i = 1; i < len; i++){
            //如果当前i下标字符为左括号，dp[i] 为零,不用特殊处理
            if(s.charAt(i) == ')' ){
            //如果当前i下标字符为右括号
                 //如果i-1下标字符为左括号，正好与i下标字符配对
                if(s.charAt(i-1) == '(' ){
                    if(i < 2){
                        dp[i] = 2;//避免i-2越界
                    }else {
                         dp[i] = dp[i-2] + 2;
                    }
                //如果i-1下标字符也是右括号
                }else {
                    //如果i-1处为有效括号先找出该有效括号的起始括号下标
                    if(dp[i-1] > 0){
                         int pre_left = i - 1 - dp[i-1] + 1;
                    //判断前有效括号的前一个字符是否是左括号
                        if( pre_left >= 1 && s.charAt(pre_left - 1) == '(' ){
                        //如果是，那可以与i下标的右括号配对
                            dp[i] = dp[i-1] + 2;
                        //因为要求连续括号的最大长度，因此此处加上dp[pre_left - 2]
                            if(pre_left - 2 >= 0){
                                dp[i] = dp[i] + dp[pre_left - 2];
                            }
                        }
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
