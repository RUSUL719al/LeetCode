package HOOT100;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
请你将所有链表合并到一个升序链表中，返回合并后的链表。

示例 1：
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6

示例 2：
输入：lists = []
输出：[]

示例 3：
输入：lists = [[]]
输出：[]

提示：
k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按 升序 排列
lists[i].length 的总和不超过 10^4

来源：力扣（LeetCode）

 */
public class mergeKLists {
    public static void main(String[] args) {
        ListNode list1 = BuildNode.buildListNode(new int[]{1,3,4,5,8});
        ListNode list2 = BuildNode.buildListNode(new int[]{1,2,5,8,10});
        ListNode list3 = BuildNode.buildListNode(new int[]{3,4,9,12,16});
        ListNode[] lists = new ListNode[]{list1,list2,list3};
        ListNode result = getMergeKLists(lists);
        System.out.print( "The Result Is : ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
    /**
     * 解题思路：声明一个小根堆（升序-小的在前）
     *          1.先把每个头结点扔进小根堆里（此时按照头结点的值升序排序好了）
     *          2.查看堆是否为空，如果为空则返回空
     *          3.弹出小根堆的堆顶节点作为所有链表的头结点（所有节点各自升序排序的，小根堆也是升序排序的）
     *          4.如果所有链表的头结点有下一个节点，则把下一个节点也放入堆中（如果没有这一步就进入while循环的话头结点对应的链表遍历不到）
     *          5.进入while循环（循环条件：堆不为空）
     *          6.弹出堆顶节点，如果该节点有下一个节点，把它的下一个节点放进堆中。直到堆为空为止
     * @param lists
     * @return
     */
    public static ListNode getMergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        //声明一个小根堆（升序-小的在前）注意引用重写的比较器
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new ListNodeComparator());
        //把所有的链表都扔进小跟堆中
        for(ListNode curNode : lists){
            if(curNode != null){
                queue.add(curNode);
            }
        }
        //查看一下小根堆里有没有东西
        if(queue.isEmpty()){
            return null;
        }
        //弹出小根堆的堆顶链表，它的头结点就是所有链表的头结点
        ListNode result = queue.poll();
        //用另一个ListNode接收头结点，用于后续的遍历
        ListNode per = result;
        //如果当前弹出的链表头结点有next节点，则把next节点也放入小根堆中（如果跳过此步骤，会丢失当前弹出链表的后续所有节点）
        if(per.next != null){
            queue.add(per.next);
        }
        //进入循环
        while (!queue.isEmpty()) {
            //获取弹出的节点
            ListNode curNode = queue.poll();
            //让弹出的节点接在头结点之后
            per.next = curNode;
            //如果当前弹出的节点有next节点，则把next节点放入小根堆中
            if(curNode.next != null){
                queue.add(curNode.next);
            }
            //让头结点指针移到当前节点
            per = curNode;
        }
        return result;
    }
    //重写比较器方法
    //compare方法返回值大于0，会交换前后两个数位置
    //compare方法返回值小于等于0，位置不交换
    public static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }
}
