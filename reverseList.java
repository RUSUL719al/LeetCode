package HOOT100;

import java.util.Stack;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

示例 1：
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]

示例 2：
输入：head = [1,2]
输出：[2,1]

示例 3：
输入：head = []
输出：[]
 
提示：
链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000
 
进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？

来源：力扣（LeetCode）
 */
public class reverseList {
    public static void main(String[] args) {
        int[] l1 = {9,2,1,3,2,1,3};
        ListNode node = BuildNode.buildListNode(l1);
        ListNode result = reverseListNode3(node);
        while (result!=null) {
            System.out.print(" " + result.val);
            result = result.next;
        } 
    }
    //用栈实现
    public static ListNode reverseListNode1(ListNode head) {
        if(head == null){
            return null;
        }
        Stack<ListNode> stack = new Stack<>(); 
        while (head!=null) {
            stack.push(head);
            head = head.next;
        }
        ListNode node = stack.pop();
        ListNode res = node;
        while(!stack.isEmpty()){
            node.next = stack.pop();
            node = node.next;
        }
        node.next = null;
        return res;
    }

    //用迭代法实现
    public static ListNode reverseListNode2(ListNode head) {
        ListNode cur = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = cur;
            cur = head;
            head = next;
        }
        return cur;
    }

    //用递归的方式实现
    public static ListNode reverseListNode3(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode cur = reverseListNode3(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }
}
