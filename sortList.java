package HOOT100;


/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
示例 1：
输入：head = [4,2,1,3]
输出：[1,2,3,4]

示例 2：
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]

示例 3：
输入：head = []
输出：[]
 
提示：
链表中节点的数目在范围 [0, 5 * 104] 内
-105 <= Node.val <= 105
 
进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

来源：力扣（LeetCode）

 */
public class sortList {
    public static void main(String[] args) {
        int[] nums = {2,1,4,6,3,5,7};
        ListNode head = BuildNode.buildListNode(nums);
        ListNode result = sortListNode1(head);
       // ListNode result = sortListNode2(head);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
    //运用归并排序的思路实现排序（归并排序用递归方式通过不停的分割直到只剩一个元素再比较大小的方式实现）
    public static ListNode sortListNode1(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        //获取中间节点
        ListNode midNode = findMidNode(head);
        //记录后半部分的节点
        ListNode nextNodes = midNode.next;
        //与前半部分断开
        midNode.next = null;
        ListNode left = sortListNode1(head);
        ListNode right = sortListNode1(nextNodes);

        return mergeListNodes(left, right);
    }
    //找到链表的中间节点
    public static ListNode findMidNode(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    //合并排序后的节点
    public static ListNode mergeListNodes(ListNode left, ListNode right) {
        //声明一个哑节点（用于返回排序后的节点）
        ListNode dummy = new ListNode(-1);
        //声明当前节点，用于接收遍历
        ListNode curNode = dummy;
        while (left != null && right != null) {
            if(left.val <= right.val){
                curNode.next = left;
                left = left.next;
            }else{
                curNode.next = right;
                right = right.next;
            }
            curNode = curNode.next;
        }
        if(left != null){
            curNode.next = left;
        }
        if(right != null){
            curNode.next = right;
        }
        return dummy.next;
    }

    //通过快速排序的方式实现（快速排序：随机寻找一个基准数，通过递归让让小于基准数的数组在左边；等于的在中中间；大于的在右边）
    public static ListNode sortListNode2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode h1 = new ListNode();ListNode t1 = h1;//小于区域的头结点及尾结点
        ListNode h2 = new ListNode();ListNode t2 = h2;//等于区域的头结点及尾结点
        ListNode h3 = new ListNode();ListNode t3 = h3;//大于区域的头结点及尾结点
        ListNode midNode = findMidNode(head);//寻找中点节点作为基准节点（快排中的随机选基准数的最佳情况就是每次都选中中间点） 
        ListNode curNode = head;//当前遍历的节点
        while (curNode != null) {
            ListNode curNextNode = curNode.next;//修改当前节点的下一节点
            if(curNode.val < midNode.val){
                curNode.next = null;
                t1.next = curNode;
                t1 = t1.next;
            }else if(curNode.val == midNode.val){
                curNode.next = null;
                t2.next = curNode;
                t2 = t2.next;
            }else if(curNode.val > midNode.val){
                curNode.next = null;
                t3.next = curNode;
                t3 = t3.next;
            }
            curNode = curNextNode;
        }
        //分别递归小于区域和大于区域
        h1 = sortListNode2(h1.next);
        h3 = sortListNode2(h3.next);

        //小于区域可能没值，但等于区域一定有值，因此先连接等于区域和大于区域
        h2 = h2.next;
        t2.next = h3;
        //如果小于区域没值直接返回等于区域的头结点，否则连接小于区域并返回小于区域的头结点
        if(h1 != null){
            t1 = h1;
            while (t1.next != null) {
                t1 = t1.next;
            }
            t1.next = h2;
            return h1;
        }else{
            return h2;
        }
    }
}
