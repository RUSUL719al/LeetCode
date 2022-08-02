package HOOT100;

public class CreateTree {
    public static TreeNode createTree(int[] arrs,int num) {
        TreeNode head = new TreeNode(arrs[num]);
        if(num*2+1<arrs.length){
            head.left = createTree(arrs,num*2+1);
        }
        if(num*2+2<arrs.length){
            head.right = createTree(arrs,num*2+2);
        }
        return head;
    }
}