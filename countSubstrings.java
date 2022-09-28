package HOOT100;
/**
 * 给你一个字符串s，请你统计并返回这个字符串中回文子串的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束为止的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 
 * 示例1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串："a", "b", "c"
 * 
 * 示例2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：六个回文子串："a", "a", "a", "aa", "aa", "aaa"
 * 
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
public class countSubstrings {
    public static void main(String[] args) {
        String s = "abccba";
        int result = getCountSubStrings2(s);
        System.out.println("The Result Is : " + result);
    }
    /**
     * 解题思路：动态规划
     * 声明一个二维数组dp[][] ,dp[i][j]表示在 i 到 j 范围内的子串是否是回文串
     * dp[][]递推规则：
     *  （1）s[i]与s[j]不相等时dp[i][j] = false
     *  （2）s[i]与s[j]相等时：1. i 与 j 指向同一个字符（如：a） 2. i 与 j 相差为1（如：aa） 3. i 与 j 相差大于1（如：abcba）,此时要看s[i+1]与s[j-1]
     */
    public static int getCountSubStrings1(String s) {
        int len = s.length();
        int result = 0;
        if(s == null || len < 1){
            return 0;
        }
        //声明一个布尔型二位数组，dp[i][j]表示在 i 到 j 范围内的子串是否是回文串
        boolean[][] dp = new boolean[len][len];
        for(int j = 0; j < len; j++){
            for(int i = 0; i <= j; i++){
                char ci = s.charAt(i);
                char cj = s.charAt(j);
                if(ci == cj){
                    if(j - i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }else{
                    dp[i][j] = false;
                }
            }
        }
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(dp[i][j]){
                    result++;
                }
            }
        }
        return result;
    }
    /**
     * 解题思路：中心扩散法
     * 让每一个字符成为中心轴（为了考虑长度为偶数的字符串，让每两个连续字符也成为中心轴）然后向两边扩散。判断两边的字符是否一样，一样则是回文子串，否则不是
     * 中心轴：让每一个字符，以及让每两个连续的字符都成为中心轴。因此中心轴的个数是 2*s.length - 1
     * @param s
     * @return
     */
    public static int getCountSubStrings2(String s) {
        int len = s.length();
        int result = 0;
        for(int i = 0; i < 2*len - 1; i++){
            int left = i / 2; int right = i / 2 + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--; right++; result++; 
            } 
        }
        return result;
    }
}
