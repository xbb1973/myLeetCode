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

}
