package HOOT100;
/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例 1：
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.

示例 2：
输入：l1 = [0], l2 = [0]
输出：[0]

示例 3：
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]

来源：力扣（LeetCode）

 */
public class addTwoListNumbers {
    public static void main(String[] args) {
        int[] l1 = {2,4,1,6,3,7,1};
        int[] l2 = {1,9,3,2};
        ListNode node1 = BuildNode.buildListNode(l1);
        ListNode node2 = BuildNode.buildListNode(l2);
        ListNode result = addToListNumber(node1, node2);
        while (result!=null) {
            System.out.print(result.val + "");
            result = result.next;
        }
        
    }

    public static ListNode addToListNumber(ListNode l1, ListNode l2) {
        //创建一个空链表，用于返回结果
        ListNode pre = new ListNode(0);
        //创建一个指针，指向相加后的新节点
        ListNode curr = pre;
        //声明一个变量存放相加后的进位数
        int carry = 0;
        //边界条件：当任意一个链表不为空，或者进位数不为零（假如遍历到最后时两个链表的节点都为空，但进位数不为0）
        while(l1 != null || l2 != null || carry > 0){
            //相加两个节点值以及进位数
            int newVal = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            //获取进位数的值（逢十进一）
            carry = newVal > 9 ? 1 : 0;
            //将两数之和对10取模（获取相加后数字的最低位数）
            newVal %= 10;
            //把最低位数放入新链表中
            curr.next = new ListNode(newVal);
            //两个链表从当前节点向后推进一个节点
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            //更新指针位置
            curr = curr.next;
        }
        //返回刚开始创建的空链表的下一点（当前此链表的头结点为0）
        return pre.next;
    }

}
