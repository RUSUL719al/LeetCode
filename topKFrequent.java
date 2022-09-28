package HOOT100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

 示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]

示例 2:
输入: nums = [1], k = 1
输出: [1]
 
提示：
1 <= nums.length <= 105
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 
进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。

来源：力扣（LeetCode）

 */
public class topKFrequent {
    public static void main(String[] args) {
        int[] nums = {2,2,3,4,5,2,1,3,3,1,2};
        int k = 2;
        int[] result = getTopKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
    }

    public static int[] getTopKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int[]{};
        }
        if(nums.length == 1 && k == 1){
            return new int[]{nums[0]};
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }else{
                map.put(nums[i], map.get(nums[i])+1);
            } 
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                return map.get(a) - map.get(b);
            }
        });
        
        for(Integer key : map.keySet()){
            if(queue.size() < k){
                queue.add(key);
            }else if(map.get(key) > map.get(queue.peek())){
                queue.remove();
                queue.add(key);
            }
        }
        
        int[] res = new int[k];
        int i = 0;
        while(!queue.isEmpty()){
            res[i++] = queue.remove();
        }
        return res;
    }

    
}
