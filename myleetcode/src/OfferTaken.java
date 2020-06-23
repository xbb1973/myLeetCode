import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author ：xbb
 * @date ：Created in 2020/6/20 3:33 下午
 * @description：剑指offer题目
 * @modifiedBy：
 * @version:
 */
// https://github.com/CyC2018/CS-Notes/blob/master/notes/剑指%20Offer%20题解%20-%20目录.md
public class OfferTaken {

    // 3. 数组中重复的数字
    // 题目描述
    // 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
    // Input:
    // {2, 3, 1, 0, 2, 5}
    // Output:
    // 2
    // 解题思路
    // 要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。
    // 对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。本题要求找出重复的数字，因此在调整过程中，如果第 i 位置上已经有一个值为 i 的元素，就可以知道 i 值重复。
    // 以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，因此可以知道 2 重复：
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || length <= 0) {
            return false;
        }
        // 调整数组将数值为i的数放到index i上
        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {
                if (numbers[numbers[i]] == numbers[i]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                swapInt(numbers, numbers[i], i);
            }
        }
        return false;
    }

    private void swapInt(int[] numbers, int a, int b) {
        int temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }


    // 4. 二维数组中的查找
    // 题目描述
    // 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
    // Consider the following matrix:
    // [
    //   [1,   4,  7, 11, 15],
    //   [2,   5,  8, 12, 19],
    //   [3,   6,  9, 16, 22],
    //   [10, 13, 14, 17, 24],
    //   [18, 21, 23, 26, 30]
    // ]
    //
    // Given target = 5, return true.
    // Given target = 20, return false.
    // 解题思路
    // 要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。
    // 该二维数组中的一个数，小于它的数一定在其左边，大于它的数一定在其下边。
    // 因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间，
    // 当前元素的查找区间为左下角的所有元素。
    public boolean Find(int target, int[][] array) {
        // 从0,0开始找会出现不知道往右还是往左的选择
        // 而从0,M-1开始找，则小左，大右，类似折半查找
        int M = array.length;
        int N = array[0].length;
        if (M <= 0 || N <= 0) {
            return false;
        }
        int mIndex = 0;
        int nIndex = N - 1;
        while (mIndex < M && nIndex >= 0) { // ⚠️循环条件需要同时满足，否则会出现一个越界
            if (array[mIndex][nIndex] > target) {
                nIndex--;
            } else if (array[mIndex][nIndex] < target) {
                mIndex++;
            } else {
                return true;
            }
        }
        return false;
    }

    // 5. 替换空格
    // 题目描述
    // 将一个字符串中的空格替换成 "%20"。
    // Input:
    // "A B"
    // Output:
    // "A%20B"
    // 解题思路
    // ① 在字符串尾部填充任意字符，使得字符串的长度等于替换之后的长度。因为一个空格要替换成三个字符（%20），所以当遍历到一个空格时，需要在尾部填充两个任意字符。
    // ② 令 P1 指向字符串原来的末尾位置，P2 指向字符串现在的末尾位置。P1 和 P2 从后向前遍历，当 P1 遍历到一个空格时，就需要令 P2 指向的位置依次填充 02%（注意是逆序的），否则就填充上 P1 指向字符的值。从后向前遍是为了在改变 P2 所指向的内容时，不会影响到 P1 遍历原来字符串的内容。
    // ③ 当 P2 遇到 P1 时（P2 <= P1），或者遍历结束（P1 < 0），退出。
    public String replaceSpace(StringBuffer str) {
        // 1. 可变性
        // String 不可变
        // StringBuffer 和 StringBuilder 可变
        // 2. 线程安全
        // String 不可变，因此是线程安全的
        // StringBuilder 不是线程安全的
        // StringBuffer 是线程安全的，内部使用 synchronized 进行同步
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == ' ') {
                ans.append("%20");
            } else {
                ans.append(charAt);
            }
        }
        return ans.toString();

        // 不适用StringBuffer的append，也不使用新的空间
        // P1的值是
        int P1 = str.length() - 1;
        // 1、有几个空格就append几个%20的占位符，加上空格需要再开辟2个空格空间
        for (int i = 0; i <= P1; i++) {
            if (str.charAt(i) == ' ')
                str.append("  ");
        }
        // 2、P2的值是扩展后的长度
        int P2 = str.length() - 1;
        while (P1 >= 0 && P2 > P1) {
            // 其他逻辑类似
            char c = str.charAt(P1--);
            if (c == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            } else {
                str.setCharAt(P2--, c);
            }
        }
        return str.toString();
    }

    // 6. 从尾到头打印链表
    // 题目描述
    // 从尾到头反过来打印出每个结点的值。
    // 解题思路
    // 1. 使用递归
    // 要逆序打印链表 1->2->3（3,2,1)，可以先逆序打印链表 2->3(3,2)，
    // 最后再打印第一个节点 1。而链表 2->3 可以看成一个新的链表，
    // 要逆序打印该链表可以继续使用求解函数，也就是在求解函数中调用自己，这就是递归函数。
    // 2. 使用头插法
    // 头插法顾名思义是将节点插入到头部：在遍历原始链表时，
    // 将当前节点插入新链表的头部，使其成为第一个节点。
    // 链表的操作需要维护后继关系，
    // 例如在某个节点 node1 之后插入一个节点 node2，我们可以通过修改后继关系来实现：
    // node3 = node1.next;
    // node2.next = node3;
    // node1.next = node2;
    // ⚠️为了能将一个节点插入头部，我们引入了一个叫头结点的辅助节点，
    // 该节点不存储值，只是为了方便进行插入操作。
    // 不要将头结点与第一个节点混起来，第一个节点是链表中第一个真正存储值的节点。
    // 3. 使用栈
    // 栈具有后进先出的特点，在遍历链表时将值按顺序放入栈中，最后出栈的顺序即为逆序。
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 解法1、递归
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ans = new ArrayList<>();
        ListNode head = listNode;
        // ListNode tail = head;
        if (head == null) {
            return ans;
        }
        // 解法1、递归
        if (head != null) {
            // 注意调用顺序，需要先调用递归的方法，再使用add方法
            // 可以新建一个函数处理递归，也可以直接使用该方法作为递归方法
            // 学习到ArrayList的addAll方法
            recursionTravel(head.next, ans);
            // ans.addAll(printListFromTailToHead(head.next));
            ans.add(head.val);
        }
        return ans;
    }

    private void recursionTravel(ListNode p, ArrayList<Integer> ans) {
        if (p != null) {
            // 注意调用顺序，需要先调用递归的方法，再使用add方法
            recursionTravel(p.next, ans);
            ans.add(p.val);
        }
    }

    // 解法2、头查法更改顺序
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ans = new ArrayList<>();
        ListNode newTail = new ListNode(-1);
        newTail.next = null;
        if (listNode == null) {
            return ans;
        }
        while (listNode != null) {
            // 先保存指向下一个结点的结点
            ListNode next = listNode.next;
            // 对head.next之前的结点进行头插
            listNode.next = newTail.next;
            // ⚠️使用头结点的好处在于这个位置的代码，可以使用next迭代下去
            newTail.next = listNode;
            // 将head指回原来保存的结点
            listNode = next;
        }
        while (newTail.next != null) {
            newTail = newTail.next;
            ans.add(newTail.val);
        }
        return ans;
    }

    // 解法3、栈
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }
        return ans;
    }

    // 7. 重建二叉树
    // 题目描述
    // 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。
    // 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    // 解题思路
    // 前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，
    // 左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。
    // 然后分别对左右子树递归地求解。
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 缓存中序遍历数组每个值对应的索引
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        // 将in的索引数据保存
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }
        // 对pre进行递归，默认一开始只有左子树
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preLow, int preHigh, int inLeft) {
        // inLeft > pre.length没有该条件会报越界异常
        // if (preLow > preHigh || inLeft > pre.length) {
        //     return null;
        // }
        if (preLow > preHigh) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLow]);
        // inRootIndex 找出拆分当前二叉树的root结点
        int inRootIndex = indexForInOrders.get(root.val);
        // inRootIndex - inLeft的值等于root的左子树有多少个结点
        int leftNodeSize = inRootIndex - inLeft;


        root.left = reConstructBinaryTree(pre, preLow + 1, preLow + leftNodeSize, inLeft);
        root.right = reConstructBinaryTree(pre, preLow + leftNodeSize + 1, preHigh, inLeft + leftNodeSize + 1);
        return root;
    }

    // 8. 二叉树的下一个结点，红黑树中的找后继
    // 题目描述
    // 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回 。
    // 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
    // 解题思路
    // 我们先来回顾一下中序遍历的过程：先遍历树的左子树，再遍历根节点，最后再遍历右子树。所以最左节点是中序遍历的第一个节点。
    // void traverse(TreeNode root) {
    //     if (root == null) return;
    //     traverse(root.left);
    //     visit(root);
    //     traverse(root.right);
    // }
    // ① 如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点；
    // ②  ⚠️关键点。否则，向上找第一个左链接指向的树包含该节点的祖先节点。

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;  // 指向父结点的指针

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        // ① 如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点；
        if (pNode.right != null) {
            TreeLinkNode next = pNode.right;
            while (next.left != null) {
                next = next.left;
            }
            return next;
        }
        // ② ⚠️关键点。否则，向上找第一个左链接指向的树包含该节点的祖先节点。
        if (pNode.right == null) {
            while (pNode.next != null) {
                if (pNode.next.left == pNode) {
                    return pNode.next;
                }
                pNode = pNode.next;
            }
        }
        return null;
    }

    // 9. 用两个栈实现队列
    // 题目描述
    // 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
    // 解题思路
    // in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。
    // 一个元素进入 in 栈之后，出栈的顺序被反转。
    // 当元素要出栈时，需要先进入 out 栈，此时元素出栈顺序再一次被反转，
    // 因此出栈顺序就和最开始入栈顺序是相同的，先进入的元素先退出，这就是队列的顺序。
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        // ⚠️关键代码，只有当stack2没有元素时才把stakc1导入
        //      这样才可以保证顺序性
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }


    // 10.1 斐波那契数列
    // 10.2 矩形覆盖
    // 10.3 跳台阶
    // 10.4 变态跳台阶
    // 10系列的题目都是斐波那契数列的变形。

    // 10.1 斐波那契数列
    // 题目描述
    // 大家都知道斐波那契数列，现在要求输入一个整数n，
    // 请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
    // n<=39
    // 求斐波那契数列的第 n 项，n <= 39。
    // 解题思路
    // 1、如果使用递归求解，会重复计算一些子问题。例如，计算 f(4) 需要计算 f(3) 和 f(2)，
    // 计算 f(3) 需要计算 f(2) 和 f(1)，可以看到 f(2) 被重复计算了。
    // 递归是将一个问题划分成多个子问题求解，
    // 2、动态规划也是如此，但是动态规划会把子问题的解缓存起来，从而避免重复求解子问题。
    // 考虑到第 i 项只与第 i-1 和第 i-2 项有关，因此只需要存储前两项的值就能求解第 i 项，
    // 从而将空间复杂度由 O(N) 降低为 O(1)。
    // 由于待求解的 n 小于 40，因此可以将前 40 项的结果先进行计算，之后就能以 O(1) 时间复杂度得到第 n 项的值。

    /**
     * 1、递归
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    /**
     * 2、动态规划
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // int[] f = new int[n + 1];
        // f[0] = 0;
        // f[1] = 1;
        int f0 = 0;
        int f1 = 1;
        int f2 = 0;
        for (int i = 2; i < n + 1; i++) {
            // f[i] = f[i - 2] + f[i - 1];
            f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }
        return f2;
    }


    // 10.2 矩形覆盖
    // 题目描述
    // 我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。
    // 请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？
    // 解题思路
    // 当 n 为 1 时，只有一种覆盖方法：
    // 当 n 为 2 时，有两种覆盖方法：
    // 要覆盖 2*n 的大矩形，可以先覆盖 2*1 的矩形，再覆盖 2*(n-1) 的矩形；
    // 或者先覆盖 2*2 的矩形，再覆盖 2*(n-2) 的矩形。
    // 而覆盖 2*(n-1) 和 2*(n-2) 的矩形可以看成子问题。
    // 该问题的递推公式如下：
    // fn = {       1,  n=1;
    //              2,  n=2;
    //        fn-1+fn-2,n>2;
    public int RectCover(int n) {
        if (n <= 2) {
            return n;
        }
        int f0 = 1;
        int f1 = 2;
        int f2 = 0;
        for (int i = 2; i < n; i++) {
            f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }
        return f2;
    }

    // 10.3 跳台阶
    // 题目描述
    // 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
    // 解题思路
    // 当 n = 1 时，只有一种跳法：
    // 当 n = 2 时，有两种跳法：
    // ⚠️跳 n 阶台阶，可以先跳 1 阶台阶，再跳 n-1 阶台阶；或者先跳 2 阶台阶，再跳 n-2 阶台阶。
    // 而 n-1 和 n-2 阶台阶的跳法可以看成子问题，该问题的递推公式为：
    // fn = {       1,  n=1;
    //              2,  n=2;
    //        fn-1+fn-2,n>2;
    public int JumpFloor(int n) {
        if (n <= 2) {
            return n;
        }
        int f0 = 1;
        int f1 = 2;
        int f2 = 0;
        for (int i = 2; i < n; i++) {
            f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }
        return f2;
    }

    // 10.4 变态跳台阶
    // 题目描述
    // 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。
    // 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
    // 解题思路
    // 动态规划
    // public int JumpFloorII(int target) {
    //     int[] dp = new int[target];
    //     Arrays.fill(dp, 1);
    //     for (int i = 1; i < target; i++)
    //         for (int j = 0; j < i; j++)
    //             dp[i] += dp[j];
    //     return dp[target - 1];
    // }
    // 数学推导
    // 跳上 n-1 级台阶，可以从 n-2 级跳 1 级上去，也可以从 n-3 级跳 2 级上去...，那么
    // f(n-1) = f(n-2) + f(n-3) + ... + f(0)
    // 同样，跳上 n 级台阶，可以从 n-1 级跳 1 级上去，也可以从 n-2 级跳 2 级上去... ，那么
    // f(n) = f(n-1) + f(n-2) + ... + f(0)
    // 综上可得
    // f(n) - f(n-1) = f(n-1)
    // 即f(n) = 2*f(n-1)
    // 所以 f(n) 是一个等比数列
    // public int JumpFloorII(int target) {
    //     return (int) Math.pow(2, target - 1);
    // }
    // 1、动态规划，没想通
    public int JumpFloorII(int target) {
        // n-  1..2..3..4..5....n;
        // f(n) = f(n-1) + f(n-2) + ... + f(0)
        // f(n) = f1+f(n-1) + f2+f(n-2) + ... + f(0)
        // 直接对上述公式进行动态规划
        int n = target + 1;
        int[] dp = new int[n];
        // ⚠️此处fill需要为1，边界
        Arrays.fill(dp, 1);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            for (int i1 = 1; i1 < i; i1++) {
                dp[i] += dp[i1];
            }
        }
        return dp[target];
    }

    // 2、数学推导
    public int JumpFloorII(int target) {
        // 数学推导
        // 跳上 n-1 级台阶，可以从 n-2 级跳 1 级上去，也可以从 n-3 级跳 2 级上去...，那么
        // f(n-1) = f(n-2) + f(n-3) + ... + f(0)
        // 同样，跳上 n 级台阶，可以从 n-1 级跳 1 级上去，也可以从 n-2 级跳 2 级上去... ，那么
        // f(n) = f(n-1) + f(n-2) + ... + f(0)
        // fn = 2^1 * fn-1
        int n = target + 1;
        int[] dp = new int[n];
        int ans = 0;
        Arrays.fill(dp, 0);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = 2 * dp[i - 1];
        }
        return dp[target];

        // or 直接
        return (int) Math.pow(2, target - 1);
    }

    // 11. 旋转数组的最小数字
    // 题目描述
    // 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    // 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
    // 解题思路
    // 将旋转数组对半分可以得到一个包含最小元素的新旋转数组，以及一个非递减排序的数组。
    // 新的旋转数组的数组元素是原数组的一半，从而将问题规模减少了一半，
    // 这种折半性质的算法的时间复杂度为 O(logN)（为了方便，这里将 log2N 写为 logN）。
    // 此时问题的关键在于确定对半分得到的两个数组哪一个是旋转数组，哪一个是非递减数组。
    // 我们很容易知道非递减数组的第一个元素一定小于等于最后一个元素。
    // 通过修改二分查找算法进行求解（l 代表 low，m 代表 mid，h 代表 high）：
    // 当 nums[m] <= nums[h] 时，表示 [m, h] 区间内的数组是非递减数组，[l, m] 区间内的数组是旋转数组，此时令 h = m；
    // 否则 [m + 1, h] 区间内的数组是旋转数组，令 l = m + 1。
    // public int minNumberInRotateArray(int[] nums) {
    //     if (nums.length == 0)
    //         return 0;
    //     int l = 0, h = nums.length - 1;
    //     while (l < h) {
    //         int m = l + (h - l) / 2;
    //         if (nums[m] <= nums[h])
    //             h = m;
    //         else
    //             l = m + 1;
    //     }
    //     return nums[l];
    // }
    // 如果数组元素允许重复，会出现一个特殊的情况：nums[l] == nums[m] == nums[h]，
    // 此时无法确定解在哪个区间，需要切换到顺序查找。
    // 例如对于数组 {1,1,1,0,1}，l、m 和 h 指向的数都为 1，此时无法知道最小数字 0 在哪个区间。
    // 1、解法一，判断array[0]与后续第一个小于它的数
    public int minNumberInRotateArray(int[] array) {
        // array是一个非递减数组的旋转
        int n = array.length;
        int rotateIndex = 0;
        while (++rotateIndex < n) {
            if (array[rotateIndex] < array[0]) {
                break;
            }
        }
        return array[rotateIndex];
    }

    // 2、解法二，二分
    public int minNumberInRotateArray(int[] array) {
        // array是一个非递减数组的旋转
        int n = array.length;
        int low = 0;
        int high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid] <= array[high]) {
                high = mid;
            } else if (array[mid] > array[high]) {
                low = mid + 1;
            } else {

            }
        }
        return array[low];
    }

    // 12. 矩阵中的路径
    // 题目描述
    // 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
    // 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向上下左右移动一个格子。
    // 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
    // 解题思路
    // 使用回溯法（backtracking）进行求解，它是一种暴力搜索方法，通过搜索所有可能的结果来求解问题。
    // 回溯法在一次搜索结束时需要进行回溯（回退），将这一次搜索过程中设置的状态进行清除，
    // 从而开始一次新的搜索过程。例如下图示例中，从 f 开始，下一步有 4 种搜索可能，
    // 如果先搜索 b，需要将 b 标记为已经使用，防止重复使用。在这一次搜索结束之后，
    // 需要将 b 的已经使用状态清除，并搜索 c。
    //
    //
    // 本题的输入是数组而不是矩阵（二维数组），因此需要先将数组转换成矩阵。
    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    // private int rows;
    // private int cols;

    public boolean hasPath(char[] array, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0) return false;
        boolean[][] marked = new boolean[rows][cols];
        char[][] matrix = buildMatrix(array, rows, cols);
        // 两个for暴力搜索/递归
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtracking(matrix, str, marked, 0, i, j, rows, cols)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtracking(char[][] matrix, char[] str,
                                 boolean[][] marked, int pathLen, int rows, int cols, int maxRows, int maxCols) {
        // 1、递归退出条件
        if (pathLen == str.length) {
            return true;
        }
        // 2、、判断matrix[rows][cols] != str[pathLen]，是否在搜索的字符串中
        //      判断边界条件、标记不可重入
        if (rows < 0 || rows >= maxRows || cols < 0 || cols >= maxCols
                || matrix[rows][cols] != str[pathLen] || marked[rows][cols]) {
            return false;
        }
        // 3、标记
        marked[rows][cols] = true;
        // 4、从该结点往4个方向进行搜索/递归
        for (int[] n : next) {
            if (backtracking(matrix, str, marked, pathLen + 1, rows + n[0], cols + n[1], maxRows, maxCols)) {
                return true;
            }
        }
        // 5、回溯，取消标记，每次递归的起始点不同，暴力遍历所有点作为起始点
        marked[rows][cols] = false;
        return false;
    }

    private char[][] buildMatrix(char[] array, int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        for (int r = 0, idx = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = array[idx++];
            }
        }
        return matrix;
    }

    // 链接：https://www.nowcoder.com/questionTerminal/c61c6999eecb4b8f88a98f66b273a3cc?answerType=1&f=discussion
    // 来源：牛客网
    // 描述
    // 这是一篇针对初学者的题解，给出一个比较好的DFS模板。
    // 知识点:DFS
    // 难度：二星
    // 题解
    // 题目描述：给定一个二维字符串矩阵mat,和一个字符串str,判断str是否可以在mat中匹配。
    // 可以选择的方向是上下左右。
    // 方法：DFS
    // 这道题大家都知道是DFS的题，关键是怎么可以快速并且正确的写出，是本题解讨论的重点。
    // 首先解释一下递归函数。
    // 递归函数：就是当前处理的问题是什么，并且下一次在规模减小的情况下处理相同的问题。
    // 比如此题：当前处理的问题是：判断字符串str[0 ... len]是否在mat中匹配，显然下一次递归处理的问题是:如果str[0]已经匹配，则判断字符串str[1 ... len]是否在mat中匹配。
    //
    // 这里先给出一个我认为比较清晰的DFS模板：
    // dfs(){
    //     // 第一步，检查下标是否满足条件
    //
    //     // 第二步：检查是否被访问过，或者是否满足当前匹配条件
    //
    //     // 第三步：检查是否满足返回结果条件
    //
    //     // 第四步：都没有返回，说明应该进行下一步递归
    //     // 标记
    //     dfs(下一次)
    //     // 回溯
    // }
    //
    //
    // main() {
    //     for (对所有可能情况) {
    //         dfs()
    //     }
    // }
    // 上面每步的顺序都不能颠倒。


    // 13. 机器人的运动范围
    // NowCoder
    // 题目描述
    // 地上有一个 m 行和 n 列的方格。一个机器人从坐标 (0, 0) 的格子开始移动，
    // 每一次只能向左右上下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于 k 的格子。
    // 例如，当 k 为 18 时，机器人能够进入方格 (35,37)，因为 3+5+3+7=18。
    // 但是，它不能进入方格 (35,38)，因为 3+5+3+8=19。请问该机器人能够达到多少个格子？
    // 解题思路
    // 使用深度优先搜索（Depth First Search，DFS）方法进行求解。
    // 回溯是深度优先搜索的一种特例，它在一次搜索过程中需要设置一些本次搜索过程的局部状态，
    // 并在本次搜索结束之后清除状态。而普通的深度优先搜索并不需要使用这些局部状态，虽然还是有可能设置一些全局状态。
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] marked = new boolean[rows][cols];
        // for (int[] n : next) {
        //     searchRoute(threshold, marked, 0 + n[0], 0 + n[1], rows, cols);
        // }
        searchRoute(threshold, marked, 0, 0, rows, cols);
        int ans = 0;
        for (boolean[] booleans : marked) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private void searchRoute(int threshold, boolean[][] marked, int r, int c, int rows, int cols) {
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || marked[r][c] || biggerThanThreshold(r, c, threshold)) {
            // do nothing
        } else {
            marked[r][c] = true;
            for (int[] n : next) {
                searchRoute(threshold, marked, r + n[0], c + n[1], rows, cols);
            }
        }
    }

    private boolean biggerThanThreshold(int r, int c, int threshold) {
        int rAdd = 0;
        int cAdd = 0;
        while (r % 10 > 0) {
            rAdd += r % 10;
            r = r / 10;
        }
        while (c % 10 > 0) {
            cAdd += c % 10;
            c = c / 10;
        }
        System.out.println(rAdd + cAdd);
        if (rAdd + cAdd > threshold) {
            return true;
        } else {
            return false;
        }
    }

    // 14. 剪绳子
    // Leetcode
    // 题目描述
    // 把一根绳子剪成多段，并且使得每段的长度乘积最大。
    // n = 2
    // return 1 (2 = 1 + 1)
    // n = 10
    // return 36 (10 = 3 + 3 + 4)
    // 解题思路
    //
    // 贪心
    // 尽可能多剪长度为 3 的绳子，并且不允许有长度为 1 的绳子出现。如果出现了，就从已经切好长度为 3 的绳子中拿出一段与长度为 1 的绳子重新组合，把它们切成两段长度为 2 的绳子。
    // 证明：当 n >= 5 时，3(n - 3) - n = 2n - 9 > 0，且 2(n - 2) - n = n - 4 > 0。因此在 n >= 5 的情况下，将绳子剪成一段为 2 或者 3，得到的乘积会更大。又因为 3(n - 3) - 2(n - 2) = n - 5 >= 0，所以剪成一段长度为 3 比长度为 2 得到的乘积更大。
    // public int integerBreak(int n) {
    //     if (n < 2)
    //         return 0;
    //     if (n == 2)
    //         return 1;
    //     if (n == 3)
    //         return 2;
    //     int timesOf3 = n / 3;
    //     if (n - timesOf3 * 3 == 1)
    //         timesOf3--;
    //     int timesOf2 = (n - timesOf3 * 3) / 2;
    //     return (int) (Math.pow(3, timesOf3)) * (int) (Math.pow(2, timesOf2));
    // }
    // 动态规划
    // public int integerBreak(int n) {
    //     int[] dp = new int[n + 1];
    //     dp[1] = 1;
    //     for (int i = 2; i <= n; i++)
    //         for (int j = 1; j < i; j++)
    //             dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * (i - j)));
    //     return dp[n];
    // }
    //
    // e 2.7 为极值点，带入可得x=3时 > x=2时的解，因此尽量拆分为3+3+3，若有1则改为2+2。
    //
    public int integerBreak(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int mul2times = 0;
        int mul3times = 0;
        while (n / 3 >= 1) {
            mul3times++;
            n = n - 3;
        }
        switch (n) {
            case 0:
                // do nothing
                break;
            case 1:
                mul3times--;
                mul2times++;
                mul2times++;
                break;
            case 2:
                mul2times++;
                break;
            default:
                break;
        }
        return (int) (Math.pow(2, mul2times) * Math.pow(3, mul3times));
    }

    // 15. 二进制中 1 的个数
    //      参考隔壁leetcode 191
    public int NumberOf1(int n) {
        int ans = 0;
        while (n != 0) {
            n &= (n - 1);
            ans++;
        }
        return ans;
    }

    // 16. 数值的整数次方
    // NowCoder
    // 题目描述
    // 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。
    // 解题思路
    // 下面的讨论中 x 代表 base，n 代表 exponent。
    // 因为 (x*x)n/2 可以通过递归求解，并且每次递归 n 都减小一半，因此整个算法的时间复杂度为 O(logN)。
    public double Power(double base, int exponent) {
        // 1、解法一，brutal
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        double ans = 1.0;
        for (int i = 0; i < exponent; i++) {
            ans *= base;
        }
        return ans;
        // 2、解法二，快速幂
        boolean oddFlag = false;
        double ans = 1;
        // 二分快速幂
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        if ((exponent % 2) == 1) {
            oddFlag = true;
        }
        exponent = exponent / 2;
        for (int i = 0; i < exponent; i++) {
            ans *= base;
        }
        if (oddFlag) {
            return ans * ans * base;
        } else {
            return ans * ans;
        }
        // 3、。。。
        return Math.pow(base, exponent);
    }

    // 18.2 删除链表中重复的结点
    // NowCoder 类似 leetcode83题
    // 题目描述
    // 题目描述
    // 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
    // 重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
    // 解题描述 -1 1 1 1 1 1 2
    public ListNode deleteDuplication(ListNode pHead) {
        if (next == null) {
            return pHead;
        }
        ListNode head = new ListNode(-1); // 新建一个头结点，防止链表中头结点是重复节点被删除。
        ListNode trail = head;
        while (pHead != null) {
            ListNode nextNode = pHead.next;
            boolean flag = false;
            while (nextNode != null && pHead.val == nextNode.val) {
                nextNode = nextNode.next;
                flag = true;
            }
            if (!flag) {
                trail.next = pHead;
                trail = trail.next;
            }
            pHead = nextNode;
        }
        trail.next = null; // 1->2->3->3->3
        return head.next;
    }

    // 83. 删除排序链表中的重复元素
    // 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
    // 示例 1:
    // 输入: 1->1->2
    // 输出: 1->2
    // 示例 2:
    // 输入: 1->1->2->3->3
    // 输出: 1->2->3
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode head = new ListNode(-1);
        head.next = pHead;
        ListNode cur = head, pre = head;
        boolean deleteFlag = false;
        while (cur.next != null) {
            if (cur.next != null && cur.next.val == cur.val) {
                deleteFlag = true;
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
            }
            if (deleteFlag) {
                cur = cur.next;
                pre.next = cur;
                deleteFlag = false;

            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }


}
