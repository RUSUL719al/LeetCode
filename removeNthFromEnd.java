package HOOT100;
/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

示例 1：
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：
输入：head = [1], n = 1
输出：[]

示例 3：
输入：head = [1,2], n = 1
输出：[1]
 
提示：
链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 
来源：力扣（LeetCode）

 */
public class removeNthFromEnd {
    public static void main(String[] args) {
        int[] nums = {1};
        ListNode node = BuildNode.buildListNode(nums);
        int num = 1;
        ListNode result = reMoveNthFromEnd(node, num);
        while (result != null) {
            System.out.print(" " + result.val);
            result = result.next;
        }
    }

    public static ListNode reMoveNthFromEnd(ListNode head , int n) {
        ListNode dumhead = new ListNode();
        dumhead.next = head;
        ListNode left = dumhead;
        ListNode right = dumhead;
        for(int i=0;i<=n;i++){
            right = right.next;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        ListNode res = dumhead.next;
        return res;
    }
}
