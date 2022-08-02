package HOOT100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
对于给定的输入，保证和为 target 的不同组合数少于 150 个。

示例 1：
输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。

示例 2：
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]

示例 3：
输入: candidates = [2], target = 1
输出: []

提示：
1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都 互不相同
1 <= target <= 500

来源：力扣（LeetCode）

 */
public class combinationSum {
    public static void main(String[] args) {
        int[] nums = {2,3,5};
        int target = 8;
        List<List<Integer>> result = findCobinationSumNumbers(nums, target);
        for(List<Integer> list : result){
            System.out.print("[");
            for(Integer num : list){
                System.out.print(" " + num);
            }
            System.out.print("]");
        }
    }

    public static List<List<Integer>> findCobinationSumNumbers(int[] nums, int target) {
        //定义一个返回结果的集合
        List<List<Integer>> res =new ArrayList<>();
        //定义一个存储树路径上的节点值
        Deque<Integer> path = new ArrayDeque<>();
        //定义一个表示数组的长度的变量
        int len = nums.length;
        //深度搜索
        dfs(nums, len,0, target, path,res);
        //返回结果
        return res;
    }
    public static void dfs(int[] candidates, int len,int begin,int target, Deque<Integer> path, List<List<Integer>> res ){
        //如果此时目标元素经过几次深度递归，减值，
        //就说明，数组中不存在能相加等于目标数组的元素集合
        if(target < 0){
            return;
        }
        //如果刚好减到0，说明此时路径上的元素，相加等于目标元素。
        //此时路径上的元素就符合条件，将他们加入返回结果中，并退出此次递归
        if(target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        //遍历元素，这里的i 必须要跟递归层数保持一致，要不然剪枝时，会造成重复元素
        for(int i = begin; i<len; i++){
            //将路径上的元素加入结果集合中
            path.add(candidates[i]);
            //在进行一轮剪枝到根节点的时候，下一轮的搜索的启点就不能包括上一次搜索的下标了
            //此时在拼接重复元素的时候，起点只能是大于等于当前元素的下标。
            dfs(candidates, len,i, target-candidates[i],path,res);
            //将元素进行删除，也叫剪枝，
            //这里必须从队列的尾部开始删除，这样才能达到从底层逐层删除
            path.removeLast();
        }
    }
}
