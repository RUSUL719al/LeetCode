package HOOT100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

示例 2：
输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。

提示：
1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
prerequisites[i] 中的所有课程对 互不相同

来源：力扣（LeetCode）

 */
public class canFinish {
    public static void main(String[] args) {
        int numCourses = 6;
        int[][] prerequisites = {{3,0},{3,1},{4,1},{4,2},{5,3},{5,4}};
        boolean result = canIFinish(numCourses, prerequisites);
        System.out.println("The Result Is : " + result);
        
    }
    /**
     * 解题思路：
     * 1.这道题是经典的拓扑排序题（拓扑排序是指把有向无环图按照逻辑顺序排成线性顺序）
     * 2.每门课都有先修课，说明该课依赖于先修课；且该课的入度跟该课的先修课数量一致。
     * 3.示例：n = 6，先决条件表：[[3, 0], [3, 1], [4, 1], [4, 2], [5, 3], [5, 4]]
     *  课 0, 1, 2 没有先修课，可以直接选。其余的课，都有两门先修课。
     * 4.此题需要处理两个问题：（1）记录每门课的入度（用Map<Integr,Integer>）；（2）记录每门课的后续课程（用Map<Integer,List<integer>>）
     * 5.遍历每门课把入度为0（没有依赖任何课程的课）的课放入队列中
     * 6.出队列时遍历该课的后续课程减少入度更新Map，同时查看入度减少后是否为0，为0则加入队列
     * 7.最后遍历Map，如果有入度不为0，则表示课程上不完

     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canIFinish(int numCourses, int[][] prerequisites) {
        //声明一个Map<Integer,Integer>存储课程编号和对应的入度
        Map<Integer,Integer> ruduMap = new HashMap<Integer,Integer>();
        //初始化入度表
        for(int i = 0; i < numCourses; i++){
            ruduMap.put(i, 0);
        }
        //声明一个Map<Integer,List<Integer>>存储课程和对应的邻接表（即依赖该课程的后续课程集合）
        Map<Integer,List<Integer>> linjieList = new HashMap<Integer,List<Integer>>();
        //初始化邻接表和借此更新入度表 ([A,B] 说明要上A课程必须先上B课程  因此拓扑图为 B -> A   B为当前课；A为下一个课)
        for(int[] cur : prerequisites){
            int curClass = cur[1]; int nextClass = cur[0];
            //更新入度表
            ruduMap.put(nextClass, ruduMap.get(nextClass) + 1);
            //更新邻接表
            if(!linjieList.containsKey(curClass)){
                linjieList.put(curClass, new ArrayList<>());
            }
            linjieList.get(curClass).add(nextClass);
        }
        //声明一个队列，把入度为零的课程放进去
        Queue<Integer> queue = new LinkedList<>();
        for(int keyClass : ruduMap.keySet()){
            if(ruduMap.get(keyClass) == 0){
                queue.offer(keyClass);
            }
        }
        //依次出队列，出队列说明该课已经上了，因此需要遍历该课的后续课程并后续课程的入度减一，同时判断减一后入度是否等于0，若是加入队列
        while (!queue.isEmpty()) {
            //出队列（表示该课已完成）
            int finishClass = queue.poll();
            //获取当前出队列课程的后续课程
            if(!linjieList.containsKey(finishClass)){
                continue;
            }
            List<Integer> afterFinishClass = linjieList.get(finishClass);
            //遍历当前出队列课程的后续课程
            for(int afterClass : afterFinishClass){
                //入度减一
                ruduMap.put(afterClass, ruduMap.get(afterClass) - 1);
                //判断减一后入度是否等于0，若是加入队列里
                if(ruduMap.get(afterClass) == 0){
                    queue.offer(afterClass);
                }
            }
        }
        //最后遍历入度表，如果存在入度不为零的课程，说明该课程总数无法完成
        for(int finish : ruduMap.keySet()){
            if(ruduMap.get(finish) != 0){
                return false;
            }
        }
        return true;
    }
}
