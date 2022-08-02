package HOOT100;
/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

来源：力扣（LeetCode）
 */
public class findMedianSortedArrays {
    public static void main(String[] args) {
        int [] nums1 = {1,2};
        int [] nums2 = {3,4,5,6,7,8};
        double result = findMedianSortedArray2(nums1, nums2);
        System.out.println("The Result Is : " + result);
    }
    //暴力解法，通过归并排序方式合并两个数组并开辟新总数组，再取中位数
    public static double findMedianSortedArray1(int[] nums1,int[] nums2) {
         int m = nums1.length;int n = nums2.length;
         int[] nums = new int[m + n];
         //边界判断
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {
                return nums2[n / 2];
            }
        }
        //边界判断
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }
        int count = 0;
        int i = 0, j = 0;
        //遍历完两个数组
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }
            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }
        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }
    //通过归并排序方式合并两个数组但不开辟新数组，再取中位数
    public static double findMedianSortedArray2(int[] nums1,int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }
    /**
     * 通过获取第k小数的思路，以递归的方式找出中位数
     * 思路：假设len长度的有序数组nums，如果长度为奇数则它的中位数=nums[len/2];如果长度为偶数则它的中位数=(nums[len/2 -1] + nums[len/2])/2
     *       此时可以把 len/2 看作 k ，并寻找数组中第k个元素。
     *       当前有两个长度分别为m1, m2的有序数组nums1 , nums2。把k分给两个数组寻找个数组的第 k/2 个元素，比较两个结果元素。
     *       如果nums1[k/2] < nums2[k/2],则说明第k元素不可能在(nums[0]~nums[k/2])范围内。因此不再考虑此范围的元素。用递归的方式在剩下的元素中寻找第(k - k/2)个元素 
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArray3(int[] nums1,int[] nums2) {
        int n = nums1.length;
    int m = nums2.length;
    int left = (n + m + 1) / 2;
    int right = (n + m + 2) / 2;
    //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
    return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;  

    }

    public static int getKth(int[] nums1,int left1,int right1,int[] nums2,int left2,int right2,int k) {
        int len1 = (right1 - left1) + 1;int len2 = (right2 - left2) + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2){
            return getKth(nums2, left2, right2, nums1, left1, right1, k);
        } 
        if (len1 == 0){
            return nums2[left2 + k - 1];
        }
        if (k == 1){
            return Math.min(nums1[left1], nums2[left2]);
        } 
        int i = left1 + Math.min(len1, k / 2) - 1;
        int j = left2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, left1, right1, nums2, j + 1, right2, k - (j - left2 + 1));
        }
        else {
            return getKth(nums1, i + 1, right1, nums2, left2, right2, k - (i - left1 + 1));
        }
    }
}
