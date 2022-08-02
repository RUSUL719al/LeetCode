package HOOT100;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
如果链表中存在环 ，则返回 true 。 否则，返回 false 。

示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。

示例 2：
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。

示例 3:
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
 

提示：
链表中节点的数目范围是 [0, 104]
-105 <= Node.val <= 105
pos 为 -1 或者链表中的一个 有效索引 。
 
进阶：你能用 O(1)（即，常量）内存解决此问题吗？

来源：力扣（LeetCode）

 */


//此处无法创建有环链表，因此两种方法都不能验证代码，注：不能用Set<Integer> 比较的应该是节点对象而不是节点值，因为有可能有重复值（LeetCode上已验证）


public class hasCycle {
    public static void main(String[] args) {
        int[] l1 = {9,2,1,3,2,1,3};
        ListNode node = BuildNode.buildListNode(l1);
        boolean result = hasCycleListNode1(node);
        System.out.println("The Result Is:" + result);
    }

    public static boolean hasCycleListNode1(ListNode node) {
        Set<ListNode> set = new HashSet<ListNode>();
        while (node!=null) {
            if(!set.add(node)){
                return true;
            }else{
                set.add(node);
                node = node.next;
            }
        }
        return false;
    }

    public static boolean hasCycleListNode2(ListNode node) {
        ListNode slow = node,quick = node;
        while (quick!=null && quick.next!=null) {
            slow = slow.next;
            quick = quick.next.next;
            if(slow == quick){
                return true;
            }
        }
        return false;
    }
}
