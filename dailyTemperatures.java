package HOOT100;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
示例 1:
输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]

示例 2:
输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]

示例 3:
输入: temperatures = [30,60,90]
输出: [1,1,0]
 
提示：
1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100

来源：力扣（LeetCode）

 */
public class dailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] result = getDailyTemperatures1(temperatures);
        System.out.println(Arrays.toString(result));
    }
    /**
     * 用双指针
     * @param temperatures
     * @return
     */
    public static int[] getDailyTemperatures1(int[] temperatures) {
        int len = temperatures.length;
        int[] result = new int[len];
        for(int i = 0; i < len; i++){
            for(int j = i+1; j < len; j++){
                if(temperatures[j] > temperatures[i]){
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }
    /**
     * 通过栈解决
     * @param temperatures
     * @return
     */
    public static int[] getDailyTemperatures2(int[] temperatures) {
        int len = temperatures.length;
        int[] result = new int[len];
        //声明一个栈，栈中是二维数组, [0][0]表示当天的温度 [0][1]表示当天温度的下标（也可以理解为第i天）
        Stack<int[][]> stack = new Stack<>();
        for(int i = 0; i < len; i++){
            //当栈不为空且当前温度大于栈顶温度时
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0][0]) {
                //弹出栈顶温度对应的二维数组
                int[][] cur = stack.pop();
                //获取栈顶温度对应的下标
                int temperIndex = cur[0][1];
                //计算栈顶温度和当前温度相隔几天
                int days = i - cur[0][1];
                //把得出来的天数赋值给栈顶温度对应的下标位置
                result[temperIndex] = days;
            }
            //如果栈中没数据或者当前温度小于栈顶温度时，把当前温度及对应的第i天下标放进栈里
            stack.push(new int[][]{{temperatures[i], i}});
        }
        return result;
    }
}
