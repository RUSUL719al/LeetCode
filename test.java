package HOOT100;
/**
 * 日常测试类
 */
public class test {
    /**
     * 打印一个正整数的32位二进制
     */
    public static void main(String[] args) {
        System.out.println((1 << 31)-1);
        int num = 2323;
        for(int i = 31; i>=0; i--){
            //1向左移动i位后和目标数取与。根据与运算符的特性就能确定目标数该i位置是0还是1
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
    }
}
