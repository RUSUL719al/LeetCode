package HOOT100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

示例 1：
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2：
输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 
提示：
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104

来源：力扣（LeetCode）
 */
public class mergeNums {
    public static void main(String[] args) {
        int[][] nums = {{1,4},{3,9},{10,14},{14,20}};
        int[][] result = mergeNums1(nums);
        for(int[] re : result){
            System.out.print("[");
            for(int num : re){
                System.out.print(num + " ");
            }
            System.out.print("]");
        }
    }

    public static int[][] mergeNums1(int[][] nums) {
        if(nums.length == 0){
            return new int[0][2];
        }
        //先按照数组左端元素升序排序（便于遍历）
        Arrays.sort(nums, Comparator.comparing(a -> a[0]));
        //声明返回用的集合
        List<int[]> result = new ArrayList<int[]>();
        for(int i=0; i<nums.length; i++){
            //获取当前遍历的数组的左端元素和右端元素
            int left = nums[i][0]; int right = nums[i][1];
            //如果用于返回的集合不为空，且集合中最后一个数组的右端元素值大于等于当前遍历数组的左端值
            if(result.size() != 0 && result.get(result.size()-1)[1] >= left){
                //更新集合中最后一个数组的右端元素值  （一定比较取最大值 》》  反例 -> 集合中：[1,4]  当前遍历的数组：[2,3]）
                result.get(result.size()-1)[1] = Math.max(result.get(result.size()-1)[1], right);
            }else{
                //否则把当前数组放入集合中
                result.add(new int[]{left,right});
            }
            // if(result.size() == 0 || result.get(result.size()-1)[1] < left){
            //     result.add(new int[]{left,right});
            // }else{
            //     result.get(result.size()-1)[1] = Math.max(result.get(result.size()-1)[1], right);
            // }
        }
        return result.toArray(new int[result.size()][]);
    }
}
