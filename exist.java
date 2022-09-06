package HOOT100;
/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例 1：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true

示例 2：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
输出：true

示例 3：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
 
提示：
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board 和 word 仅由大小写英文字母组成
 
进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？

来源：力扣（LeetCode）

 */
public class exist {
    public static void main(String[] args) {
        //char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        //String word = "ABCB";
        char[][] board = {{'a'}};
        String word = "a";
        boolean result = findWord(board, word);
        System.out.println("The Result Is : " + result);
    }

    public static boolean findWord(char[][] board,String word) {
        int m = board.length;int n = board[0].length;
        if(m*n < word.length()){
            return false;
        }
        //声明一个二位数组记录当前元素是否已被访问
        boolean[][] visited = new boolean[m][n];
        //把board[0][0]位置当做开始位置依次判断是否匹配目标字符串的第0位置，若匹配则继续找下一个匹配字符，否则回退到上一个字符（当前字符访问记录回改成未访问）按照上右下左的顺序递归匹配
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(dfs(board, i, j, 0, word, visited) == true){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] chars, int row, int col, int index, String word, boolean[][] visited) {
        if(index >= word.length()){
            return true;
        }
        if(!inArea(chars, row, col)){
            return false;
        }
        if(visited[row][col] == true){
            return false;
        }
        if(chars[row][col] != word.charAt(index)){
            return false;
        }
        visited[row][col] = true;
        boolean b = dfs(chars, row+1, col, index+1, word, visited) || dfs(chars, row-1, col, index+1, word, visited) || dfs(chars, row, col+1, index+1, word, visited) || dfs(chars, row, col-1, index+1, word, visited);
        visited[row][col] = false;
        return b;
    }
    /**
     * 判断当前元素是否越界
     * @param chars  二位字符表
     * @param row    当前元素行标
     * @param col    当前元素列标
     * @return
     */
    public static boolean inArea(char[][] chars, int row, int col) {
        return row >= 0 && row < chars.length && col >= 0 && col < chars[0].length;
    }
    
}
