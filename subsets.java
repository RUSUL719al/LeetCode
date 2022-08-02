package HOOT100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

示例 1：
输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

示例 2：
输入：nums = [0]
输出：[[],[0]]
 

提示：
1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同

来源：力扣（LeetCode）

 */
public class subsets {
    static List<List<Integer>> res = new ArrayList<List<Integer>>();
    static ArrayList<Integer> sub = new ArrayList<Integer>();
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> result = subSets2(nums);
        for(List<Integer> list : result){
            System.out.print("[");
            for(Integer num : list){
                System.out.print(num + " ");
            }
            System.out.print("]");
        }
    }
    /**
     * 思路与算法(没理解)
     * 
    记原序列中元素的总数为 n。原序列中的每个数字 a(i) 的状态可能有两种，即「在子集中」和「不在子集中」。我们用 1 表示「在子集中」，0 表示不在子集中。
那么每一个子集可以对应一个长度为 n 的 0/1 序列，第 i 位表示 a(i) 是否在子集中。例如，n = 3 ，a = { 5, 2, 9 } 时：
    0/1 序列	    子集	    0/1 序列对应的二进制数
    000	            {}	        0
    001	            {9}	        1
    010	            {2}	        2
    011	            {2,9}	    3
    100	            {5}	        4
    101	            {5,9}	    5
    110	            {5,2}	    6
    111	            {5,2,9}	    7
可以发现 0/1 序列对应的二进制数正好从 0 到 2^n - 1。我们可以枚举 mask∈[0,2^n−1]

     * @param nums
     * @return
     */
    public static List<List<Integer>> subSets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = 1 << nums.length;
        for (int mask = 0; mask < len; mask++) {
            List<Integer> sub = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++){
                if ((mask & (1 << i)) != 0) {
                    sub.add(nums[i]);
                }
            }
            res.add(sub);
        }
        return res;
    }

    public static List<List<Integer>> subSets2(int[] nums) {
       // dfs1(0, nums);
       res.add(new ArrayList<>());
       dfs2(nums,0,sub,res);
        return res;
    }
    /**
     * 递归方式实现
     * @param cur  当前遍历下标
     * @param nums 数组
     */
    public static void dfs1(int cur, int[] nums) {
        if(cur == nums.length){
            res.add(new ArrayList<Integer>(sub));
            return;
        }
        sub.add(nums[cur]);
        dfs1(cur+1, nums);
        sub.remove(sub.size()-1);
        dfs1(cur+1, nums);
    }

    /**
     * 可以把当前元素选和不选理解成一个满二叉树的整个路劲，整个二叉树的叶子节点到根节点的路劲就是一个排列组合集合
     * @param nums    数组
     * @param index   当前遍历元素下标
     * @param subset  单个排列组合
     * @param reslist 返回的所有排列组合
     */
    public static void dfs2(int[] nums, int index, ArrayList<Integer> subset, List<List<Integer>> reslist) {
        if(index >= nums.length){
            return;
        }
        subset = new ArrayList<>(subset);
        //DFS，前序遍历
        reslist.add(subset);
        dfs2(nums, index+1, subset, reslist);
        subset.add(nums[index]);
        dfs2(nums, index+1, subset, reslist);

        //DFS，中序遍历
        // subSets3(nums, index+1, subset, reslist);
        // subset.add(nums[index]);
        // reslist.add(subset);
        // subSets3(nums, index+1, subset, reslist);

        //DFS，后序遍历
        // subSets3(nums, index+1, subset, reslist);
        // subset.add(nums[index]);
        // subSets3(nums, index+1, subset, reslist);
        // reslist.add(subset);
    }
}
