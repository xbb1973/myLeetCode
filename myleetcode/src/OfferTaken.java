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
    public static void main(String[] args) {

    }

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

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
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
            // ⚠️ 卡了有点久，解决当1->1->1->1->1->1时候的问题
            //      因为while是判断cur.next，因此当cur在链表尾时会报异常，要提前手动判断一下。
            if (cur == null) {
                break;
            }
        }
        return head.next;
    }

    // 19. 正则表达式匹配
    // NowCoder  同 leetcode 10.
    // 题目描述
    // 请实现一个函数用来匹配包括 '.' 和 '*' 的正则表达式。
    // 模式中的字符 '.' 表示任意一个字符，
    // 而 '*' 表示它前面的字符可以出现任意次（包含 0 次）。
    // 在本题中，匹配是指字符串的所有字符匹配整个模式。
    // 例如，字符串 "aaa" 与模式 "a.a" 和 "ab*ac*a" 匹配，但是与 "aa.a" 和 "ab*a" 均不匹配。
    // 解题思路
    // 应该注意到，'.' 是用来当做一个任意字符，而 '*' 是用来重复前面的字符。
    // 这两个的作用不同，不能把 '.' 的作用和 '*' 进行类比，从而把它当成重复前面字符一次。
    public boolean match(char[] str, char[] pattern) {

        // 递归1
        String s = String.copyValueOf(str);
        String p = String.copyValueOf(pattern);
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        boolean FirMatch = (!s.isEmpty()) && ((s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.'));
        // boolean FirMatch = (true && ( (s.charAt(0)==p.charAt(0))||(p.charAt(0)=='.') );

        if (p.length() >= 2 && p.charAt(1) == '*') {

            return (FirMatch && match(s.substring(1).toCharArray(), p.toCharArray())) || (match(s.toCharArray(), p.substring(2).toCharArray()));
        } else {

            return FirMatch && match(s.substring(1).toCharArray(), p.substring(1).toCharArray());
        }


        if (str.length == pattern.length && pattern.length == 0) {
            return true;
        }
        int N = str.length;
        int M = pattern.length;

        String s = String.copyValueOf(str);
        String p = String.copyValueOf(pattern);

        // 递归
        boolean firstMatch = (!);
        if (M >= 2 && pattern[1] == '*') {
            return ((match(Arrays.copyOfRange(str, 0, N), Arrays.copyOfRange(pattern, 2, M)))
                    || (firstMatch && match(Arrays.copyOfRange(str, 1, N), Arrays.copyOfRange(pattern, 0, M))));
        } else {
            return (firstMatch && match(Arrays.copyOfRange(str, 1, N), Arrays.copyOfRange(pattern, 1, M)));
        }
        // KMP字符匹配=DP动态规划/ DFA有限状态机，原理一样
        // 计算这个 dp 数组，只和 pat 串有关，只需要计算一次就可以匹配任意str
        // pat的下表i不是索引而是状态，因此会有区别
        // N = str.len  M = pat.len
        // dp[j][c] = next
        // 0 <= j < M，代表当前的状态
        // 0 <= c < 256，代表遇到的字符（ASCII 码）
        // 0 <= next <= M，代表下⼀个状态
        int[][] dp = dpConstruction(pattern);
        int N = str.length;
        int M = pattern.length;
        int j = 0;
        for (int i = 0; i < N; i++) {
            j = dp[j][pattern[i]];
            if (j == M) {
                return true;
            }
        }
        return false;
    }

    private int[][] dpConstruction(char[] pattern) {
        int[][] dp = new int[pattern.length][256];
        // dp[j][c]边界 当前状态j=0 遇到字符c=patt[0]时的 dp[0][pattern[0]]=next状态
        dp[0][pattern[0]] = 1;
        // 影子状态，初始为0，就是和当前状态具有相同的前缀
        int X = 0;
        for (int i = 1; i < pattern.length; i++) {
            for (int c = 0; c < 256; c++) {
                // 1、命中
                if (pattern[i] == c || pattern[i] == '.') {
                    dp[i][c] = i + 1;
                } else if (pattern[i] == '*') {
                    // 而 '*' 表示它前面的字符可以出现任意次（包含 0 次）。
                    if (pattern[i - 1] == c) {
                        dp[i][c] = dp[i - 1][c] + 1;
                        i--;
                    } else {

                    }
                } else {
                    // 2、⚠️没有命中，此时i无法推进，
                    //      将状态重启交给X处理
                    dp[i][c] = dp[X][c];
                }
            }
            // ⚠️更新影子状态
            X = dp[X][pattern[i]];
        }
        return dp;
    }

    // KMP匹配 dp构造框架（伪码）
    public DpConstruct(String pat) {
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 影子状态 X 初始为 0
        int X = 0;
        // 构建状态转移图（稍改的更紧凑了）
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++)
                dp[j][c] = dp[X][c];
            dp[j][pat.charAt(j)] = j + 1;
            // 更新影子状态
            X = dp[X][pat.charAt(j)];
        }
        // for 0 <= j < M: #状态
        // for 0 <= c < 256: #字符
        // dp[j][c] = next
    }

    // KMP匹配（伪码）
    public int KMPSearch(String txt) {
        int M = pat.length();
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 当前是状态 j，遇到字符 txt[i]，
            // pat 应该转移到哪个状态？
            j = dp[j][txt.charAt(i)];
            // 如果达到终止态，返回匹配开头的索引
            if (j == M) {
                return i - M + 1;
            }
        }
        // 没到达终止态，匹配失败
        return -1;
    }

    // 暴⼒匹配（伪码）
    int BrutalSearch(String pat, String txt) {
        int M = pat.length;
        int N = txt.length;
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (pat[j] != txt[i + j]) {
                    break;
                }
            }
            // pat 全都匹配了
            if (j == M) return i;
        }
        // txt 中不存在 pat ⼦串
        return -1;
    }

    /**
     * @Description: 10. 正则表达式匹配
     * 难度
     * 困难
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * <p>
     * 说明:
     * <p>
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 示例 1:
     * <p>
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     * <p>
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * 示例 3:
     * <p>
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * 示例 4:
     * <p>
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5:
     * <p>
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     * @Param:
     * @return:
     * @Date: 2020/6/26
     * @Author: xbb1973
     */
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();

        // 递归思路，不考虑text空、.和*的情况
        boolean firstMatch = text.charAt(0) == pattern.charAt(0);
        return firstMatch & isMatch(text.substring(1), pattern.substring(1));

        // 递归思路，考虑.和*的情况
        // 1、判断 text 是否为空，防止越界，如果 text 为空， 表达式直接判为 false, text.charAt(0)就不会执行了
        boolean firstMatch = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        // 2、判断*
        // ⚠️charAt下标从0开始，charAt1实际上就是第二个字符为*，因此假设*代表0个时，需要比较patt.sub2
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            // 两种情况
            // pattern 直接跳过两个字符。表示 * 前边的字符出现 0 次
            // pattern 不变，例如 text = aa ，pattern = a*，第一个 a 匹配，然后 text 的第二个 a 接着和 pattern 的第一个 a 进行匹配。表示 * 用前一个字符替代。
            // 其实就是*代表0个的情况，和*代表1个以上为情况
            // 1、*代表0个，match text, ptt.sub2
            // 2、*代表1个，match text.sub1, ptt
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch & isMatch(text.substring(1), pattern.substring(1));
        }
    }


    // 20. 表示数值的字符串
    // NowCoder 同 leetcode 65
    // 题目描述
    // 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
    // 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
    // [+-]? \d* (\.\d+)? ([eE][+-]?\.\d+)?
    // 解题思路
    // 使用正则表达式进行匹配。
    // []  ： 字符集合
    // ()  ： 分组
    // ?   ： 重复 0 ~ 1 次
    // +   ： 重复 1 ~ n 次
    // *   ： 重复 0 ~ n 次
    // .   ： 任意字符
    // \\. ： 转义后的 .
    // \\d ： 数字
    public boolean isNumeric(char[] str) {
        if (str.length == 0) {
            return false;
        }

        // 用例:
        // "1+23"
        // 对应输出应该为:
        // false
        // 你的输出为:
        // true
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");

        // 正则表达式， [+-]? \d* (\.\d+)? ([eE][+-]?)\d+)?
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
        // 正则表达式， [+-]? \d* (\.\d+)? ([eE][+-]?)\d+)?
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }


    // 65. 有效数字
    // 验证给定的字符串是否可以解释为十进制数字。
    // 例如:
    // "0" => true
    // " 0.1 " => true
    // "abc" => false
    // "1 a" => false
    // "2e10" => true
    // " -90e3   " => true
    // " 1e" => false
    // "e3" => false
    // " 6e-1" => true
    // " 99e2.5 " => false
    // "53.5e93" => true
    // " --6 " => false
    // "-+3" => false
    // "95a54e53" => false
    // 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
    // 数字 0-9
    // 指数 - "e"
    // 正/负号 - "+"/"-"
    // 小数点 - "."
    // 当然，在输入中，这些字符的上下文也很重要。
    // 使用正则表达式进行匹配。
    // []  ： 字符集合
    // ()  ： 分组
    // ?   ： 重复 0 ~ 1 次
    // +   ： 重复 1 ~ n 次
    // *   ： 重复 0 ~ n 次
    // .   ： 任意字符
    // \\. ： 转义后的 .
    // \\d ： 数字
    public boolean isNumber(String s) {
        // "1 " => true
        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }
        // 正则表达式， base [+-]? \d* (\.\d+)? ([eE][+-]?\d+)?
        // 1368 / 1481 个通过测试用例
        // 1480 / 1481 个通过测试用例
        // PASS
        if (s.charAt(0) == 'e' || s.charAt(0) == 'E' || ((s.charAt(0) == '-' || s.charAt(0) == '+') && (s.charAt(1) == 'e' || s.charAt(1) == 'E'))) {
            return false;
        }
        // 1428 / 1481 个通过测试用例
        // ".1" => true
        // "3." => true
        // [+-]? (\d+(\.\d*)?)|(\d*(\.\d+)?)? ([eE][+-]?\d+)?
        // 正则表达式，
        // 1480 / 1481 个通过测试用例
        return s.matches("[+-]?((\\d+(\\.\\d*)?)|(\\d*(\\.\\d+)?))?([eE][+-]?\\d+)?");
    }


    // 解法二 自动机
    // 自己最开始想到的就是这个，编译原理时候在学到的自动机，就是一些状态转移。
    // 这一块内容很多，自己也很多东西都忘了，但不影响我们写算法，主要参考这里。
    // 如上图，从 0 开始总共有 9 个状态，橙色代表可接受状态，也就是表示此时是合法数字。
    // 总共有四大类输入，数字，小数点，e 和 正负号。我们只需要将这个图实现就够了。
    // 最后看返回的状态是否正确。
    public boolean isNumber(String s) {
        // 1、初始状态0，遇到 +/-、数字、 "." 变为状态 1 2 7
        int state = 0;
        s = s.trim();//去除头尾的空格
        //遍历所有字符，当做输入
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                //输入正负号
                case '+':
                case '-':
                    if (state == 0) {
                        state = 1;
                    } else if (state == 4) {
                        state = 6;
                    } else {
                        return false;
                    }
                    break;
                //输入数字
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    //根据当前状态去跳转
                    switch (state) {
                        case 0:
                        case 1:
                        case 2:
                            state = 2;
                            break;
                        case 3:
                            state = 3;
                            break;
                        case 4:
                        case 5:
                        case 6:
                            state = 5;
                            break;
                        case 7:
                            state = 8;
                            break;
                        case 8:
                            state = 8;
                            break;
                        default:
                            return false;
                    }
                    break;
                //小数点
                case '.':
                    switch (state) {
                        case 0:
                        case 1:
                            state = 7;
                            break;
                        case 2:
                            state = 3;
                            break;
                        default:
                            return false;
                    }
                    break;
                //e
                case 'e':
                    switch (state) {
                        case 2:
                        case 3:
                        case 8:
                            state = 4;
                            break;
                        default:
                            return false;
                    }
                    break;
                default:
                    return false;

            }
        }
        //橙色部分的状态代表合法数字
        return state == 2 || state == 3 || state == 5 || state == 8;
    }

    // 21. 调整数组顺序使奇数位于偶数前面
    // NowCoder
    // 题目描述
    // 需要保证奇数和奇数，偶数和偶数之间的相对位置不变，这和书本不太一样。
    // 解题思路
    // 方法一：创建一个新数组，时间复杂度 O(N)，空间复杂度 O(N)。
    // 方法二：使用冒泡思想，每次都将当前偶数上浮到当前最右边。时间复杂度 O(N2)，空间复杂度 O(1)，时间换空间。
    public void reOrderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {

            // 下标优化前：
            // 运行时间：18ms
            // 占用内存：9320k
            // for (int j = 0; j < array.length -1; j++) {

            // 下标优化后：
            // 运行时间：11ms
            // 占用内存：9436k
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] % 2 == 0) {
                    if (array[j + 1] % 2 != 0) {
                        swapInt(array, j, j + 1);
                    }
                }
            }
        }
    }

    // 22. 链表中倒数第 K 个结点
    // NowCoder
    // 解题思路
    // 设链表的长度为 N。设置两个指针 P1 和 P2，先让 P1 移动 K 个节点，则还有 N - K 个节点可以移动。此时让 P1 和 P2 同时移动，可以知道当 P1 移动到链表结尾时，P2 移动到第 N - K 个节点处，该位置就是倒数第 K 个节点。
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode kPre = new ListNode(-1);
        kPre.next = head;
        ListNode kAft = new ListNode(-1);
        kAft.next = head;
        int index = 0;
        int length = 0;
        while (kPre.next != null) {
            kPre = kPre.next;
            if (index < k) {
                index++;
                // do nothing
            } else {
                kAft = kAft.next;
            }
            length++;
        }
        if (length < k) {
            return null;
        }
        return kAft.next;
    }

    // 23. 链表中环的入口结点
    // NowCoder
    // 题目描述
    // 一个链表中包含环，请找出该链表的环的入口结点。要求不能使用额外的空间。
    public ListNode EntryNodeOfLoop(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode kPre = new ListNode(-1);
        kPre.next = head;
        ListNode kAft = new ListNode(-1);
        kAft.next = head;
        while (true) {
            kPre = kPre.next.next;
            kAft = kAft.next;
            if (kPre == kAft) {
                break;
            }
        }
        kPre = new ListNode(-1);
        kPre.next = head;
        while (true) {
            kPre = kPre.next;
            kAft = kAft.next;
            if (kPre == kAft) {
                break;
            }
        }
        return kAft;


        ListNode fast = head;
        ListNode slow = head;
        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    // 24. 反转链表
    // NowCoder
    // 解题思路
    // 递归
    // 迭代 使用头插法。
    public ListNode ReverseList(ListNode head) {
        ListNode pHead = new ListNode(-1);
        pHead.next = null;
        while (head != null) {
            ListNode tmp = head;
            head = head.next;
            tmp.next = pHead.next;
            pHead.next = tmp;
        }
        return pHead.next;
    }

    // 25. 合并两个排序的链表
    // NowCoder
    // 题目描述
    // 解题思路
    // 递归
    // 迭代
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode pHead = new ListNode(-1);
        // 尾插，保证顺序
        ListNode r = new ListNode(-1);
        r = pHead;
        // pHead.next = r;
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                r.next = list2;
                list2 = list2.next;
            } else {
                r.next = list1;
                list1 = list1.next;
            }
            r = r.next;
        }
        if (list1 == null) {
            r.next = list2;
        }
        if (list2 == null) {
            r.next = list1;
        }
        return pHead.next;
    }

    // 26. 树的子结构
    // NowCoder
    // 题目描述
    // 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }

        return recursionHasSubtree(root1, root2) || recursionHasSubtree(root1.left, root2) || recursionHasSubtree(root1.right, root2);
    }

    private boolean recursionHasSubtree(TreeNode root1, TreeNode root2) {
        // ⚠️特殊点，当root2为null则返回true，是因为前面判断都是non false
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return recursionHasSubtree(root1.right, root2.right) && recursionHasSubtree(root1.left, root2.left);
    }

    // 27. 二叉树的镜像
    // NowCoder
    // 题目描述
    // 操作给定的二叉树，将其变换为源二叉树的镜像。
    public void Mirror(TreeNode root) {
        if (root != null) {
            // pass
            swapTreeNode(root);
            // fail
            // swapTreeNode(root.left, root.right);
            Mirror(root.left);
            Mirror(root.right);
        } else {
            return;
        }
    }

    // 为什么？？？
    private void swapTreeNode(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    // 这个swap不对，root无法知晓left和right的变化。
    private void swapTreeNode(TreeNode left, TreeNode right) {
        TreeNode temp = left;
        left = right;
        right = temp;
    }

    // 28. 对称的二叉树
    // NowCoder
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return recursionIsSymmetrical(pRoot.left, pRoot.right);
    }

    private boolean recursionIsSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return recursionIsSymmetrical(left.left, right.right) && recursionIsSymmetrical(left.right, right.left);
    }

    // 29. 顺时针打印矩阵
    // NowCoder
    // 纯计算数组边缘条件

    // 30. 包含 min 函数的栈
    // NowCoder
    // 题目描述
    // 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的 min 函数。
    // 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
    // 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
    // ⚠️push、pop、top等都是时间复杂度为O(1)的操作，但是min需要遍历O(n)。
    // 使用辅助栈来标识当前栈的min值
    private Stack<Integer> masterStack = new Stack<>();
    private Stack<Integer> slaveStack = new Stack<>();

    public void push(int node) {
        masterStack.push(node);
        // ⚠️有时候很多if else 可以通过三元代替！！
        slaveStack.push((slaveStack.isEmpty()) ? slaveStack.push(node) : Math.min(node, slaveStack.peek()));
    }

    public void pop() {
        masterStack.pop();
        slaveStack.pop();
    }

    public int top() {
        // peek : Looks at the object at the top of this stack without removing it from the stack.
        return masterStack.peek();
    }

    public int min() {
        return slaveStack.peek();
    }


    // 31. 栈的压入、弹出序列
    // NowCoder
    // 题目描述
    // 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
    // 例如序列 1,2,3,4,5 是某栈的压入顺序，
    // 序列 4,5,3,2,1 是该压栈序列对应的一个弹出序列，
    // 但 4,3,5,1,2 就不可能是该压栈序列的弹出序列。
    // 解题思路
    // 使用一个栈来模拟压入弹出操作。
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int n = pushA.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, popIndex = 0; i < n; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && popA[popIndex] == stack.peek()) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    // 32.1 从上往下打印二叉树
    // NowCoder
    // 题目描述
    // 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    // 例如，以下二叉树层次遍历的结果为：1,2,3,4,5,6,7
    // 解题思路
    // 使用队列来进行层次遍历。
    // 不需要使用两个队列分别存储当前层的节点和下一层的节点，因为在开始遍历一层的节点时，当前队列中的节点数就是当前层的节点数，只要控制遍历这么多节点数，就能保证这次遍历的都是当前层的节点。
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        // ⚠️使用LinkedList来作为queue
        Queue<TreeNode> queue = new LinkedList<>();
        // Queue<TreeNode> queue = new ArrayBlockingQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // ⚠️用continue优化null
            if (node == null) {
                continue;
            }
            ans.add(node.val);
            // if (node.left != null)
            queue.offer(node.left);
            // if (node.right != null)
            queue.offer(node.right);
        }
        return ans;
    }

    // 32.2 把二叉树打印成多行
    // NowCoder
    // 题目描述
    // 和上题几乎一样。
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ansss = new ArrayList<>();
        // ⚠️使用LinkedList来作为queue
        Queue<TreeNode> queue = new LinkedList<>();
        // Queue<TreeNode> queue = new ArrayBlockingQueue<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> ans = new ArrayList<>();
            // ⚠️这个地方queue.size() > 0不能直接作为条件！,不然一个循环就跑完了
            int currentSize = queue.size();
            // while (queue.size() > 0) {
            while (currentSize-- > 0) {
                TreeNode node = queue.poll();
                // ⚠️用continue优化null
                if (node == null) {
                    continue;
                }
                ans.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (!ans.isEmpty()) {
                ansss.add(ans);
            }
        }
        return ansss;
    }

    // 32.3 按之字形顺序打印二叉树
    // NowCoder
    // 题目描述
    // 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
    // 解题思路
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {

        boolean reverseFlag = false;
        ArrayList<ArrayList<Integer>> ansss = new ArrayList<>();
        // ⚠️使用LinkedList来作为queue
        Queue<TreeNode> queue = new LinkedList<>();
        // Queue<TreeNode> queue = new ArrayBlockingQueue<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> ans = new ArrayList<>();
            // ⚠️这个地方queue.size() > 0不能直接作为条件！,不然一个循环就跑完了
            int currentSize = queue.size();
            // while (queue.size() > 0) {
            while (currentSize-- > 0) {
                TreeNode node = queue.poll();
                // ⚠️用continue优化null
                if (node == null) {
                    continue;
                }
                ans.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);

            }
            if (reverseFlag) {
                Collections.reverse(ans);
            }
            reverseFlag = !reverseFlag;
            if (!ans.isEmpty()) {
                ansss.add(ans);
            }
        }
        return ansss;
    }

    // 33. 二叉搜索树的后序遍历序列
    // NowCoder
    // 题目描述
    // 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。假设输入的数组的任意两个数字都互不相同。
    // 例如，下图是后序遍历序列 1,3,2 所对应的二叉搜索树。
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0 || sequence == null) {
            return false;
        }
        return recursionVerifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean recursionVerifySequenceOfBST(int[] sequence, int leftIndex, int lastIndex) {
        // ⚠️退出条件，以后如果实在不知道，就整个简答的例子先写着
        if (lastIndex - leftIndex < 1) {
            return true;
        }
        int rootValue = sequence[lastIndex];
        int rightIndex = leftIndex;
        while (rightIndex < lastIndex && sequence[rightIndex] < rootValue) {
            rightIndex++;
        }
        for (int i = rightIndex; i < lastIndex; i++) {
            if (sequence[i] < rootValue) {
                return false;
            }
        }
        return recursionVerifySequenceOfBST(sequence, leftIndex, rightIndex - 1) && recursionVerifySequenceOfBST(sequence, rightIndex, lastIndex - 1);
    }

    // 34. 二叉树中和为某一值的路径
    // NowCoder
    // 题目描述
    // 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    // 下图的二叉树有两条和为 22 的路径：10, 5, 7 和 10, 12
    ArrayList<ArrayList<Integer>> ansFindPath = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return ansFindPath;
        }
        preOrder(root, target, new ArrayList<>());
        return ansFindPath;
    }


    private void preOrder(TreeNode root, int target, ArrayList<Integer> path) {
        path.add(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            // ???????
            // 为什么直接add path不行，需要new一个！！，不然最后会改变的。。因为是引用
            // ansFindPath.add(path);
            ansFindPath.addAll(new ArrayList<>(path));
        } else {
            if (root.left != null) {
                preOrder(root.left, target, path);
            }
            if (root.right != null) {
                preOrder(root.right, target, path);
            }
        }
        // ⚠️核心代码，没想到的地方，代码走到这里
        // 表示要回溯了，需要pop，不需要当前的root结点
        path.remove(path.size() - 1);
    }

    // 35. 复杂链表的复制
    // NowCoder
    // 题目描述
    // 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
    // 另一个特殊指针random指向一个随机节点），请对此链表进行深拷贝，并返回拷贝后的头结点。
    // （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
    // 解题思路
    // ⚠️关键点在于random其实就是映射关系，如果不处理好random映射的深拷贝会出现多余的Node，
    // 所以要遍历已经深拷贝的链表，这里使用Map减少时间复杂度
    public RandomListNode Clone(RandomListNode pHead) {
        RandomListNode newHead = new RandomListNode(-1);
        RandomListNode ans = new RandomListNode(-1);
        RandomListNode tail = ans;
        Map<Integer, RandomListNode> map = new HashMap<>();
        newHead.next = pHead;
        while (newHead.next != null) {
            newHead = newHead.next;
            RandomListNode temp = new RandomListNode(newHead.label);
            if (newHead.random != null) {
                if (map.containsKey(newHead.random.label)) {
                    temp.random = map.get(newHead.random.label);
                } else {
                    RandomListNode random = new RandomListNode(newHead.random.label);
                    random.next = newHead.random.next;
                    map.put(random.label, random);
                    temp.random = random;
                }
            }
            tail.next = temp;
            tail = tail.next;
        }
        tail.next = null;
        return ans;
    }

    // 36. 二叉搜索树与双向链表
    // NowCoder
    // 题目描述
    // 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
    List<TreeNode> list = new ArrayList<>();

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return pRootOfTree;
        }
        inOrder(pRootOfTree);
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                list.get(i).left = list.get(i - 1);
            }
            if (i != list.size() - 1) {
                list.get(i).right = list.get(i + 1);
            }
        }
        return list.get(0);
    }

    private void inOrder(TreeNode pRootOfTree) {
        if (pRootOfTree.left != null) {
            inOrder(pRootOfTree.left);
        }
        list.add(pRootOfTree);
        if (pRootOfTree.right != null) {
            inOrder(pRootOfTree.right);
        }
    }

    // 37. 序列化二叉树
    // 题目描述
    // 请实现两个函数，分别用来序列化和反序列化二叉树
    // 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
    // 从而使得内存中建立起来的二叉树可以持久保存。
    // 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，
    // 序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
    // 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
    // 例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
    String Serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + "!" + Serialize(root.left) + "!" + Serialize(root.right) + "!";
    }

    List<TreeNode> list = new ArrayList<>();

    TreeNode Deserialize(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        TreeNode ans = null;
        int nodeIndex = 1;
        for (int i = 0, numerStartIndex = 0; i < str.length(); i++) {
            if ('!' == str.charAt(i) && nodeIndex == 1) {
                ans = new TreeNode(Integer.parseInt(str.substring(numerStartIndex, i)));
                list.add(ans);
                nodeIndex++;
                numerStartIndex = i + 1;
            } else if (numerStartIndex < i) {
                if ('#' == str.charAt(i)) {
                    numerStartIndex = i + 1;
                    list.add(null);
                } else if ('!' == str.charAt(i) || (numerStartIndex < i && i == str.length() - 1)) {
                    TreeNode treeNode = new TreeNode(Integer.parseInt(str.substring(numerStartIndex, i)));
                    list.add(treeNode);
                    // 左，父nodeIndex / 2
                    if (nodeIndex % 2 == 0) {
                        list.get((nodeIndex / 2) - 1).left = treeNode;
                    }
                    // 右
                    if (nodeIndex % 2 == 1) {
                        list.get((nodeIndex / 2) - 1).right = treeNode;
                    }
                    numerStartIndex = i + 1;
                }
            } else {

            }
        }
        return ans;
    }

    // 38. 字符串的排列，此题有扩展题，去找找。
    // NowCoder
    // 题目描述
    // 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
    // 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
    // 输入描述:
    // 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
    // 解题思路：回溯！回溯其实用遍历树结点，先序遍历、后序遍历。
    // 代码⽅⾯，回溯算法的框架：
    // result = []
    // def backtrack(路径, 选择列表):
    // if 满⾜结束条件:
    // result.add(路径)
    // return
    // for 选择 in 选择列表:
    // 做选择
    // backtrack(路径, 选择列表)
    // 撤销选择
    Set<String> ans = new HashSet<>();
    ArrayList<String> finnalAns = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        int length = str.length();
        if (length == 0) {
            return finnalAns;
        }
        char[] characterSet = str.toCharArray();
        Arrays.sort(characterSet);
        backtrackingPermutation(new StringBuilder(), characterSet, new boolean[length]);
        finnalAns.addAll(ans);
        return finnalAns;
    }

    // 使用boolean[] hasUsed来记录使用情况，进行剪枝。
    private void backtrackingPermutation(StringBuilder stringBuilder, char[] characterSet, boolean[] hasUsed) {
        if (stringBuilder.length() == characterSet.length) {
            ans.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < characterSet.length; i++) {
            // 做选择
            if (hasUsed[i]) {
                continue;
            }
            // 使用Set或者判断去重，因为排序过，所以比较 i-i i 和 used关系。
            // 判断去重
            // if (i > 0 && characterSet[i] == characterSet[i - 1] && hasUsed[i - 1]) {
            //     continue;
            // }
            stringBuilder.append(characterSet[i]);
            hasUsed[i] = true;
            // backtrack
            backtrackingPermutation(stringBuilder, characterSet, hasUsed);
            // 撤销选择
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            hasUsed[i] = false;
        }
    }


    // 39. 数组中出现次数超过一半的数字
    // NowCoder
    // 解题思路
    // 多数投票问题，可以利用 Boyer-Moore Majority Vote Algorithm 来解决这个问题，使得时间复杂度为 O(N)。
    // 使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素相等时，令 cnt++，否则令 cnt--。
    // 如果前面查找了 i 个元素，且 cnt == 0，说明前 i 个元素没有 majority，或者有 majority，但是出现的次数少于 i / 2 ，
    // 因为如果多于 i / 2 的话 cnt 就一定不会为 0 。此时剩下的 n - i 个元素中，majority 的数目依然多于 (n - i) / 2，
    // 因此继续查找就能找出 majority。
    public int MoreThanHalfNum_Solution(int[] array) {
        int cnt = 1;
        int major = array[0];
        for (int i = 1; i < array.length; i++) {
            cnt = (major == array[i]) ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                major = array[i];
                // ⚠️记得这里补cnt=1
                cnt = 1;
            }
        }
        // ⚠️上面的只是找出了疑似主元素，这里进行判定。
        cnt = 0;
        for (int i : array) {
            if (i == major) {
                cnt++;
            }
        }
        return (cnt > array.length / 2) ? major : 0;
    }


    // 40. 最小的 K 个数
    // NowCoder
    // 解题思路
    // 快速选择
    // 复杂度：O(N) + O(1)
    // 只有当允许修改数组元素时才可以使用
    // 快速排序的 partition() 方法，会返回一个整数 j 使得 a[l..j-1] 小于等于 a[j]，且 a[j+1..h] 大于等于 a[j]，
    // 此时 a[j] 就是数组的第 j 大元素。可以利用这个特性找出数组的第 K 个元素，这种找第 K 个元素的算法称为快速选择算法。
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input.length < k) {
            return new ArrayList<>();
        }
        int n = input.length;
        ArrayList<Integer> ans = new ArrayList<>();
        int low = 0;
        int high = n - 1;
        int[] ints = quickSearch(input, low, high, k);
        for (int anInt : ints) {
            ans.add(anInt);
        }
        return ans;
    }

    private int[] quickSearch(int[] arr, int low, int high, int k) {
        int j = partition(arr, low, high);
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k-1就返回j以及j左边所有的数；
        if (j == k - 1) {
            return Arrays.copyOf(arr, k);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段;
        // 这个地方出问题，调试了十分钟。。。
        // 重新寻找的范围内需要包含k-1 ！
        return j > k - 1 ? quickSearch(arr, low, j - 1, k) : quickSearch(arr, j + 1, high, k);
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            // 判断条件时需要包含等于号
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    // 41.1 数据流中的中位数
    // NowCoder
    // 题目描述
    // 如何得到一个数据流中的中位数？
    // 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
    // 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
    // 解题思路
    // /* 大顶堆，存储左半边元素 */
    // private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    // /* 小顶堆，存储右半边元素，并且右半边元素都大于左半边 */
    // private PriorityQueue<Integer> right = new PriorityQueue<>();
    // /* 当前数据流读入的元素个数 */
    // private int N = 0;
    // public void Insert(Integer val) {
    //     /* 插入要保证两个堆存于平衡状态 */
    //     if (N % 2 == 0) {
    //         /* N 为偶数的情况下插入到右半边。
    //          * 因为右半边元素都要大于左半边，但是新插入的元素不一定比左半边元素来的大，
    //          * 因此需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边 */
    // 大根堆
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    // 小根堆
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    private List<Integer> list = new ArrayList<>();

    public void Insert(Integer num) {
        list.add(num);
    }

    public Double GetMedian() {
        list.sort(((o1, o2) -> o1 - o2));
        int mid = list.size() / 2;
        if (list.size() % 2 == 0) {
            return Double.valueOf((list.get(mid) + list.get(mid - 1)) / 2.0);
        } else {
            return Double.valueOf(list.get(mid));
        }
    }


    // 41.2 字符流中第一个不重复的字符
    // NowCoder
    // 题目描述
    // 请实现一个函数用来找出字符流中第一个只出现一次的字符。
    // 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
    // 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
    // 输出描述:
    // 如果当前字符流没有存在出现一次的字符，返回#字符。
    int[] cnt = new int[256];
    Queue<Character> queue = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        cnt[ch]++;
        if (cnt[ch] < 1) {
            queue.add(ch);
        }
        // 清除队列中出现次数大于1的元素。
        while (!queue.isEmpty() && cnt[queue.peek()] > 1) {
            queue.poll();
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }


    // 42. 连续子数组的最大和
    // NowCoder
    // 题目描述
    // HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
    // 今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,
    // 当向量全为正数的时候,问题很好解决。
    // 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
    // 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
    // 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
    // {6, -3, -2, 7, -15, 1, 2, 2}，连续子数组的最大和为 8（从第 0 个开始，到第 3 个为止）。
    // 解题思路
    public int FindGreatestSumOfSubArray(int[] array) {
        int n = array.length;
        int sum, greatestSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // ⚠️这里是核心逻辑，
            // 小于0的累加sum则抛弃，从当前点开始继续累加，
            // 大于0的累加sum则继续累加。
            sum = sum <= 0 ? array[i] : sum + val[i];
            greatestSum = sum > greatestSum ? sum : greatestSum;
        }
        return greatestSum;
    }

    // 43. 从 1 到 n 整数中 1 出现的次数
    // 题目描述
    // 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
    // num1,num2分别为长度为1的数组。传出参数
    // 将num1[0],num2[0]设置为返回结果

    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        // 解法1、hashmap
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1);
            }
        }
        int flag = 0;
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == 1 && flag == 0) {
                num1[0] = integer;
                flag++;
            } else if (map.get(integer) == 1 && flag == 1) {
                num2[0] = integer;
            } else {

            }
        }

        // 解法2、异或
        // ⚠️异或运算：如果a、b两个值不相同，则异或结果为1。如果a、b两个值相同，异或结果为0。
        int k = 0;
        for (int i : array) {
            // ⚠️最后的k结果实际上是要求的两个值异或的结果。
            k ^= i;
        }
        // ⚠️通过找到k的第一个1位，1是由于两个数不同的位异或而得，可以把数组分为两组
        int index = 1;
        if ((index & k) == 0) {
            index <<= 1;
        }
        int k1, k2;
        for (int i : array) {
            if ((i & index) == 0) {
                k1 ^= i;
            } else {

                k2 ^= i;
            }
        }
        num1[0] = k1;
        num2[0] = k2;
    }


    // 45. 把数组排成最小的数
    // NowCoder
    // 题目描述
    // 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
    // 例如输入数组 {3，32，321}，则打印出这三个数字能排成的最小数字为 321323。
    // 解题思路
    // 可以看成是一个排序问题，在比较两个字符串 S1 和 S2 的大小时，应该比较的是 S1+S2 和 S2+S1 的大小，
    // 如果 S1+S2 < S2+S1，那么应该把 S1 排在前面，否则应该把 S2 排在前面。
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int n = numbers.length;
        String[] numStrings = new String[n];
        for (int i = 0; i < n; i++) {
            numStrings[i] = numbers[i] + "";
        }
        Arrays.sort(numStrings, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        for (String numString : numStrings) {
            stringBuffer.append(numString);
        }
        return stringBuffer.toString();
    }

    // 46. 把数字翻译成字符串
    // Leetcode     此题有扩展，动态规划方面，到时候再深入了解把。
    // 题目描述
    // 给定一个数字，按照如下规则翻译成字符串：1 翻译成“a”，2 翻译成“b”... 26 翻译成“z”。
    // 一个数字有多种翻译可能，例如 12258 一共有 5 种，分别是 abbeh，lbeh，aveh，abyh，lyh。
    // 实现一个函数，用来计算一个数字有多少种不同的翻译方法。
    // 解题思路
    // 91. Decode Ways
    // A message containing letters from A-Z is being encoded to numbers using the following mapping:
    // 'A' -> 1
    // 'B' -> 2
    // ...
    // 'Z' -> 26
    // Given a non-empty string containing only digits, determine the total number of ways to decode it.
    // Example 1:
    // Input: "12"
    // Output: 2
    // Explanation: It could be decoded as "AB" (1 2) or "L" (12).
    // Example 2:
    // Input: "226"
    // Output: 3
    // Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
    int ans = 0;

    public int numDecodings(String s) {
        int begin = 0;
        int length = s.length();
        backtrackingNumDecodings(s, begin);
        return ans;
    }

    // 使用boolean[] hasUsed来记录使用情况，进行剪枝。
    private void backtrackingNumDecodings(String s, int begin) {
        // 递归/回溯出口
        if (begin == s.length()) {
            ans++;
            return;
        }

        // 171 / 258 个通过测试用例 状态：解答错误 输入： "0" 输出： 1 预期： 0
        // Integer.valueOf(s.substring(begin, begin + 1)) >= 1
        // Integer.valueOf(s.substring(begin, begin + 1)) <= 26

        // 递归/回溯进入条件
        if (begin <= (s.length() - 1)
                && Integer.valueOf(s.substring(begin, begin + 1)) <= 26
                && Integer.valueOf(s.substring(begin, begin + 1)) >= 1) {
            // backtrack
            // 先序/标记
            begin = begin + 1;
            backtrackingNumDecodings(s, begin);
            // 后序/取消标记
            begin = begin - 1;
        }
        // 递归/回溯进入条件
        if (begin <= (s.length() - 2)
                && Integer.valueOf(s.substring(begin, begin + 2)) <= 26
                && Integer.valueOf(s.substring(begin, begin + 1)) >= 1) {
            // backtrack
            begin = begin + 2;
            backtrackingNumDecodings(s, begin);
            begin = begin - 2;
        }
    }

    // 234 / 258 个通过测试用例
    // 状态：超出时间限制
    // 最后执行的输入：
    // "9272971672512277354953939427689518239714228293463398742522722274929422229859968434281231132695842184"
    // ⚠️要使用动态规划，空间换时间
    public int numDecodings(String s) {
        int length = s.length();

        // 动态规划递归式，0.1.2.3.4.5....n
        // ⚠️这样的动态规划需要额外判断0的情况。
        // ⚠️可以考虑从n往前开始动态规划，遇到0则前进一位，合法的话则把10or20当成一个整数处理。否则直接return 0。

        // if (n-1,n) > 26
        // dp[n] = dp[n-1]
        // if if (n-1,n) <= 26
        //  dp[n] = dp[n-1] + dp[n-2]
        // ⚠️特殊值0，进行特殊处理
        int[] dp = new int[length];
        if (Integer.valueOf(s.substring(0, 1)) == 0) {
            return 0;
        }
        dp[0] = 1;
        int noneZeroIndex = 0;
        for (int i = 1; i < length; i++) {
            if (Integer.valueOf(s.substring(i, i + 1)) == 0) {
                if (Integer.valueOf(s.substring(noneZeroIndex, i + 1)) <= 26) {
                    if (i >= 2) {
                        dp[i] = dp[i - 2];
                    } else {
                        dp[i] = dp[i - 1];
                    }
                } else {
                    for (int j = noneZeroIndex; j <= i; j++) {
                        dp[j] = 0;
                    }
                }
            } else {
                noneZeroIndex = i;
                if (Integer.valueOf(s.substring(i - 1, i)) != 0) {
                    if (Integer.valueOf(s.substring(i - 1, i + 1)) > 26) {
                        dp[i] = dp[i - 1];
                    } else if (Integer.valueOf(s.substring(i - 1, i + 1)) <= 26) {
                        if (i == 1) {
                            dp[i] = dp[i - 1] + 1;
                        } else {
                            dp[i] = dp[i - 1] + dp[i - 2];
                        }
                    }
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[length - 1];
    }


    // 47. 礼物的最大价值
    // NowCoder
    // 题目描述
    // 在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值。例如，对于如下棋盘
    // 1    10   3    8
    // 12   2    9    6
    // 5    7    4    11
    // 3    7    16   5
    // 礼物的最大价值为 1+12+5+7+7+16+5=53。
    // 解题思路
    // 应该用动态规划求解，而不是深度优先搜索，深度优先搜索过于复杂，不是最优解。
    public int getMost(int[][] board) {
        // write code here
        int m = board.length;
        int n = board[0].length;
        int[] dp = new int[m + n];
        int[][] dp = new int[m][n];
        dp[0][0] = board[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + board[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + board[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max((dp[i - 1][j] + board[i][j]), (dp[i][j - 1] + board[i][j]));
            }
        }
        return dp[m - 1][n - 1];
    }


    // 48. 最长不含重复字符的子字符串
    // 题目描述
    // 输入一个字符串（只包含 a~z 的字符），求其最长不含重复字符的子字符串的长度。
    // 例如对于 arabcacfr，最长不含重复字符的子字符串为 acfr，长度为 4。
    // 解题思路
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length < 1) {
            return 0;
        }
        int[] dp = new int[length];
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        dp[0] = 1;
        map.put(s.charAt(0), 0);
        max = 1;
        for (int i = 1; i < length; i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);
                dp[i] = dp[i - 1] + 1;
            } else {
                int duplicatedIndex = map.get(c);
                dp[i] = i - duplicatedIndex;
                map.put(s.charAt(i), i);
            }
            System.out.println(dp[i]);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length < 1) {
            return 0;
        }
        int[] dp = new int[length];
        Arrays.fill(dp, 1);
        int max = 0;
        max = 1;
        for (int i = 1; i < length; i++) {
            char c = s.charAt(i);
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(j) != c) {
                    dp[i]++;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }


    // 49. 丑数
    // NowCoder
    // 题目描述
    // 把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。例如 6、8 都是丑数，但 14 不是，因为它包含因子 7。习惯上我们把 1 当做是第一个丑数。求按从小到大的顺序的第 N 个丑数。
    // 解题思路
    public int GetUglyNumber_Solution(int index) {
        int[] dp = new int[index + 1];
        dp[0] = 0;
        dp[1] = 1;
        return 0;
    }


    // 50. 第一个只出现一次的字符位置
    // NowCoder
    // 题目描述
    // 在一个字符串中找到第一个只出现一次的字符，并返回它的位置。
    // Input: abacc
    // Output: b
    // 解题思路
    // 最直观的解法是使用 HashMap 对出现次数进行统计，但是考虑到要统计的字符范围有限，因此可以使用整型数组代替 HashMap，从而将空间复杂度由 O(N) 降低为 O(1)。
    public int FirstNotRepeatingChar(String str) {
        int[] cnt = new int[256];
        int length = str.length();
        if (length < 1) {
            return 0;
        }
        for (int i = 0; i < length; i++) {
            cnt[str.charAt(i)]++;
        }
        for (int i = 0; i < length; i++) {
            if ((cnt[str.charAt(i)] == 1)) {
                return i;
            }
        }
        return 0;
    }


    // 51. 数组中的逆序对
    // NowCoder
    // 题目描述
    // 数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
    // 输入一个数组,求出这个数组中的逆序对的总数P。
    // 并将P对1000000007取模的结果输出。 即输出P%1000000007
    // 输入描述:
    // 题目保证输入的数组中没有的相同的数字
    // 数据范围：
    // 	对于%50的数据,size<=10^4
    // 	对于%75的数据,size<=10^5
    // 	对于%100的数据,size<=2*10^5
    // 解题思路
    public int InversePairs(int[] array) {
        int length = array.length;
        int dp[] = new int[length];

        // 解法一：暴力解，会超时

        // 执行结果： 运行超时:您的程序未能在规定时间内运行结束，
        // 请检查是否循环有错或算法复杂度过大。
        // 用例通过率: 50.00% 运行时间: 4001ms 占用内存: 12004KB
        // case通过率为50.00%
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    dp[i]++;
                }
            }
            if (i > 0) {
                dp[i] += dp[i - 1];
            }
        }
        return dp[length - 1];

        // 解法二：采用分治思想，使用merge sort ？？ 没写过不太会

    }

    // 52. 两个链表的第一个公共结点
    // NowCoder
    // 题目描述
    // 解题思路
    // 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
    // 当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B；
    // 同样地，当访问链表 B 的指针访问到链表尾部时，令它从链表 A 的头部重新开始访问链表 A。
    // 这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 解法一：数学，a+c = b+c = n
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead1 : l1.next;
            l2 = (l2 == null) ? pHead2 : l2.next;
        }
        return l1;
    }


    // 53. 数字在排序数组中出现的次数
    // NowCoder
    // 题目描述
    // Input:
    // nums = 1, 2, 3, 3, 3, 3, 4, 6
    // K = 3
    // Output:
    // 4
    // 解题思路
    public int GetNumberOfK(int[] array, int k) {

    }


}