package HOOT100;
/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符

示例 1：
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

示例 2：
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
 
提示：
0 <= word1.length, word2.length <= 500
word1 和 word2 由小写英文字母组成

来源：力扣（LeetCode）

 */
public class minDistance {
    public static void main(String[] args) {
        String word1 = "intention"; String word2 = "execution";
        int result = getMinDistance(word1, word2);
        System.out.println("The Result Is : " + result);
    }
    /**
     * 解题思路：动态规划  dp[i][j]表示 word1的前i个前缀字符串 转换成 word2的前j个前缀字符串所需最少代价
     *          初始化第0行: 不取word1任何前缀转换成word2的代价为依次增加word2的字符，因此代价： 0 ~ word2.lenth 递增
     *          初始化第0列: 取word1的 0 ~ word1.lenth前缀转换成word2的0字符的代价为依次删除word1的字符，因此代价： 0 ~ word1.lenth 递增
     *          状态转移dp[i][j]：
     *                  情况1：word1="abces" word2="abck" i=4 j=3 此时先让word1的前i-1个前缀字符串部分转换成word2的前j个前缀字符串部分，然后删除word1的i处字符 
     *                  因此dp[i][j] = dp[i-1][j] + 删除代价
     * 
     *                  情况2：word1="abce" word2="abcks" i=3 j=4 此时先让word1的前i个前缀字符串部分转换成word2的前j-1个前缀字符串部分，然后增加word2的j处字符 
     *                  因此dp[i][j] = dp[i][j-1] + 增加代价
     * 
     *                  情况1：word1="abcrs" word2="abcek" i=4 j=4 此时先让word1的前i-1个前缀字符串部分转换成word2的前j-1个前缀字符串部分，然后替换word1的i处字符和word2的j处字符 
     *                  因此dp[i][j] = dp[i-1][j-1] + 替换代价
     * 
     *                  情况1：word1="abcrs" word2="abcks" i=4 j=4 此时让word1的前i-1个前缀字符串部分转换成word2的前j-1个前缀字符串部分
     *                  因此dp[i][j] = dp[i-1][j-1] + 保留代价（0）
     * @param word1
     * @param word2
     * @return
     */
    public static int getMinDistance(String word1, String word2) {
        int len1 = word1.length(); int len2 = word2.length();
        char[] chars1 = word1.toCharArray(); char[] chars2 = word2.toCharArray();
        int[][] dp = new int[len1+1][len2+1]; int delete = 1, add = 1, trans = 1;
        //dp[0][0] = 0;空字符串转成另一个空字符串代价为零
        //初始化第0行
        for(int i = 1; i <= len2; i++){
            dp[0][i] = add * i;
        }
        //初始化第0列
        for(int j = 1; j <= len1; j++){
            dp[j][0] = delete * j;
        }
        //状态转移
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                //情况3和情况4的唯一差别是两个前缀字符串的最后字符是否一样
                if(chars1[i-1] == chars2[j-1]){
                    dp[i][j] = dp[i-1][j-1];//情况4
                }else{
                    dp[i][j] = dp[i-1][j-1] + trans;//情况3
                }
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + delete);//pk当前得出的dp[i][j](情况3或情况4) 和 情况1 取最小
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + add);//pk当前最新dp[i][j](情况3和情况1中最小 或 情况4和情况1中最小) 和 情况2 取最小
            }
        }
        return dp[len1][len2];
    }
}
