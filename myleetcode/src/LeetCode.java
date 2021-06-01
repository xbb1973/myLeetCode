import com.sun.xml.internal.ws.util.StringUtils;
import javafx.util.Pair;

import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/19 8:13 下午
 * @description：leetcode代码从C/C++迁移至java
 * @modifiedBy：
 * @version:
 */
public class LeetCode {
    public static void main(String[] args) {
        System.out.println("leetcode saigao");
        System.out.println(function());
        System.out.println(functionreturnX(123));

        Object object = new Object() {
            @Override
            public boolean equals(Object obj) {
                return true;
            }
        };
        System.out.println(object.equals("dsad"));
    }

    public static int function() {
        return 123;
    }

    public static int functionreturnX(int x) {
        return x;
    }
}


class Solution {
    /**
     * 面试题40. 最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。
     * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * 对于经典TopK问题，本文给出3种通用解决方案。
     * 一、用快排最最最高效解决TopK问题：O(N)
     * 二、大根堆(前K小) / 小根堆（前K大),Java中有现成的PriorityQueue，实现起来最简单：O(NlogK)
     * 三、二叉搜索树也可以O(NlogK)解决TopK问题哦
     * <p>
     * 快排切分时间复杂度分析：
     * 因为我们是要找下标为k的元素，
     * 第一次切分的时候需要遍历整个数组(0 ~ n)找到了下标是j的元素，
     * 假如k比j小的话，那么我们下次切分只要遍历数组(0~k-1)的元素就行啦，
     * 反之如果k比j大的话，那下次切分只要遍历数组(k+1～n)的元素就行啦，
     * 总之可以看作每次调用partition遍历的元素数目都是上一次遍历的1/2，
     * 因此时间复杂度是N + N/2 + N/4 + ... + N/N = 2N, 因此时间复杂度是O(N)。
     * <p>
     * 快排解法实际问题记录：
     * 1 递归时low high需要包含k-1
     * 2 patition时while判断数值大小包含等于
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers_apiSolution(int[] arr, int k) {
        int n = arr.length;
        int[] ans = new int[k];
        if (k > n || n < 1 || k < 1) {
            return ans;
        }
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int x : arr) {
            arrayList.add(x);
        }
        // 自带api使用mergeSort binarySory
        arrayList.sort(null);
        for (int i = 0; i < k; i++) {
            ans[i] = arrayList.get(i);
        }
        // return Arrays.copyOf(arrayList, k);
        return ans;
    }

    public int[] getLeastNumbers_quickSort(int[] arr, int k) {
        int n = arr.length;
        if (k < 1 || n < 1) {
            return new int[0];
        }
        return quickSearch(arr, 0, n - 1, k);
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
            while (low < high && arr[high] >= pivot) high--;
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) low++;
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    /**
     * 面试题 17.09. 第 k 个数
     *
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        // long[] dp = new long[k];
        // Arrays.fill(dp, 1);
        // while (1) {
        //     if (dp[j - 1] * 3 == k) {
        //         return j;
        //     }
        // }
        return 0;
    }

    /**
     * 673. 最长递增子序列的个数
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 1) {
            return 0;
        }
        int[] dp = new int[len];
        int[] combination = new int[len];
        // 边界
        Arrays.fill(dp, 1);
        Arrays.fill(combination, 1);
        combination[0] = 1;
        // 动态转移方程
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[j] > nums[i]) {
                    if (dp[j] < dp[i] + 1) {
                        //如果+1长于当前LIS 则组合数不变
                        dp[j] = dp[i] + 1;
                        combination[j] = combination[i];
                    } else if (dp[j] == dp[i] + 1) {
                        //如果+1等于当前LIS 则说明找到了新组合
                        combination[j] += combination[i];
                    }
                }
            }
        }
        int maxLen = 0, ans = 0;
        for (int x : dp) {
            maxLen = Math.max(maxLen, x);
        }
        for (int i = 0; i < len; i++) {
            if (maxLen == dp[i]) {
                ans += combination[i];
            }
        }
        return ans;
    }

    /**
     * 365. 水壶问题
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater1(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == 0 || y == 0) return z == 0 || x + y == z;
        return z % gcd(x, y) == 0;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    public boolean canMeasureWater2(int x, int y, int z) {
        if (z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }
        Queue<Map.Entry<Integer, Integer>> queue = new ArrayDeque<>();
        AbstractMap.SimpleEntry<Integer, Integer> start = new AbstractMap.SimpleEntry<>(0, 0);
        queue.add(start);
        Set<Map.Entry<Integer, Integer>> visited = new HashSet<>();
        visited.add(start);
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            int curX = entry.getKey();
            int curY = entry.getValue();
            if (curX == z || curY == z || curX + curY == z) {
                return true;
            }
            if (curX == 0) {
                // 把第一个桶填满
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(x, curY));
            }
            if (curY == 0) {
                // 把第二个桶填满
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX, y));
            }
            if (curY < y) {
                // 把第一个桶倒空
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(0, curY));
            }
            if (curX < x) {
                // 把第二个桶倒空
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX, 0));
            }

            // y - curY是第二个桶还可以再加的水的升数，但是最多只能加curX升水。
            int moveSize = Math.min(curX, y - curY);
            // 把第一个桶里的curX升水倒到第二个桶里去。
            addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX - moveSize, curY + moveSize));
            // 反过来同理，x - curX是第一个桶还可以再加的升数，但是最多只能加curY升水。
            moveSize = Math.min(curY, x - curX);
            // 把第一个桶里的curX升水倒到第二个桶里去。
            addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX + moveSize, curY - moveSize));
        }
        return false;
    }

    private void addIntoQueue(Queue<Map.Entry<Integer, Integer>> queue,
                              Set<Map.Entry<Integer, Integer>> visited,
                              Map.Entry<Integer, Integer> newEntry) {
        if (!visited.contains(newEntry)) {
            visited.add(newEntry);
            queue.add(newEntry);
        }
    }

    /**
     * 876. 链表的中间结点
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        int target = 0;
        while (true) {
            if (target == len / 2) {
                return p;
            }
            target++;
            p = p.next;
        }
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //860. 柠檬水找零
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5)
                five++;
            else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 动态规划设计：最⻓递增⼦序列
     */
    public int lengthOfLis(int[] nums) {
        int[] dp = new int[nums.length];
        // dp 数组全都初始化为 1
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int longestPalindrome(String s) {
        return 0;
    }


//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================
//1、数论=================================================

    /**
     * 8. 字符串转换整数 (atoi)
     * 解法1
     * 这题的做法大概是这样：
     * 1、去掉前导空格
     * 2、再是处理正负号
     * 3、识别数字，注意越界情况。
     * 关联题目
     * 7. 整数反转
     * 65. 有效数字
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        str = str.trim();
        Pattern pattern = Pattern.compile("^(\\+|-)?\\d+");
        Matcher matcher = pattern.matcher(str);
        int res = 0;
        while (matcher.find()) {
            str = matcher.group();
            if (str.startsWith("+")) {
                str = str.replaceFirst("\\+", "");
            }
            try {
                res = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                if (str.startsWith("-")) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
            return res;
        }
        return 0;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     * 解法2
     *
     * @param str
     * @return
     */
    public int myAtoi2(String str) {
        long ans = 0;
        boolean isNegative = false;
        boolean isDot = false;
        int n = str.length();
        int strIndex = 0;
        int dotIndex = 1;

        if (n < 1) {
            return 0;
        }
        char[] charArr = str.toCharArray();
        while (strIndex < n && charArr[strIndex] == ' ') {
            strIndex++;
        }
        if (strIndex < n && charArr[strIndex] == '+') {
            strIndex++;
        } else if (strIndex < n && charArr[strIndex] == '-') {
            strIndex++;
            isNegative = true;
        }
        if (strIndex == n || charArr[strIndex] < '0' || charArr[strIndex] > '9') {
            return 0;
        }
        for (int i = strIndex; i < n; i++) {
            char x = charArr[i];
            if (x >= '0' && x <= '9') {
                if (isDot) {
                    ans = (long) (ans + (x - '0') * Math.pow(0.1, dotIndex));
                    dotIndex++;
                } else {
                    ans = ans * 10 + (x - '0');
                }

                if (isNegative) {
                    if (-ans < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    if (ans > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }

            } else if (x == '.') {
                isDot = true;
            } else {
                break;
            }
        }

        if (isNegative) {
            ans = -ans;
        }

        return (int) ans;
    }

    /**
     * 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        // long ans = 0;
        int ans = 0;
        final int max_value = 0x7fffffff;
        final int min_value = 0x80000000;

        while (x != 0) {
            // // 第二种解法
            // // 上溢出临界 下溢出临界
            // int pop = x % 10;
            // if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7))
            //     return 0;
            // if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8))
            //     return 0;
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        // 第一种解法
        if (ans < min_value || ans > max_value) {
            return 0;
        }
        return (int) ans;
    }

    /**
     * 231. 2的幂
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     * <p>
     * 关联题目：
     * 326. 3的幂
     * 342. 4的幂
     * 191. 位1的个数
     * 869. 重新排序得到 2 的幂 没做
     * 面试题 08.04. 幂集 没做
     * 982. 按位与为零的三元组 没做
     * 172. 阶乘后的零
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        // 解法一
        if (n == 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        // 342. 4的幂
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;

        // 解法二
        if (n == 0) {
            return false;
        }
        long x = (long) n;
        return (x & (x - 1)) == 0;
        // 342. 4的幂
        return ((x & (x - 1)) == 0) && ((x & 0x55555555) == x);
        return ((x & (x - 1)) == 0) && ((x & 0xaaaaaaaa) == 0);


        // 解法三
        if (n == 0) {
            return false;
        }
        long x = (long) n;
        return (x & (-x)) == x;


        // 326. 3的幂
        // 解法一
        while (n % 3 == 0) {
            n /= 3;
        }
        // 解法二
        // Integer.toString(n, radix)将整数n（十进制）转化为radix进制的整数。
        // ^10*$ ^开始标志 $结束标志 10*以1开头[0,∞]个0
        return Integer.toString(n, 3).matches("^10*$");

        // 解法三
        // 我们现在可以推断出 n 的最大值，也就是 3 的幂，是 1162261467。我们计算如下：
        return n > 0 && 1162261467 % n == 0;


    }

    /**
     * 191. 位1的个数
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        // 解法一
        int ans = 0;
        while (n != 0) {
            n = n & (n - 1);
            ans++;
        }
        return ans;

        // 解法二
        // 解法二
        int mask = 1;
        int step = 0;
        while (step < 32) {
            if ((mask & n) != 0) {
                ans++;
            }
            mask = mask << 1;
            step++;
        }
        return ans;
    }

    /**
     * 190. 颠倒二进制位
     * 颠倒给定的 32 位无符号整数的二进制位。
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        // 解法一 会超时
        int ret = 0, power = 31;
        while (n != 0) {
            ret += (n & 1) << power;
            n = n >> 1;
            power -= 1;
        }
        return ret;

        // 解法一 变种
        int ret = 0;
        int count = 0;
        while (count < 32) {
            ret <<= 1;  //res 左移一位空出位置
            ret |= (n & 1); //得到的最低位加过来
            n >>= 1;//原数字右移一位去掉已经处理过的最低位
            count++;
        }
        return ret;

        // 参考解法一 变种 修改 解法一 可pass
        int ret = 0, power = 32;
        while (power > 0) {
            ret <<= 1;
            ret |= (n & 1);
            n = n >> 1;
            power -= 1;
        }
        return ret;

        // 解法二
        // return ( byte * 0x0202020202b & 0x010884422010b) %1023;

        // 解法三
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    /**
     * 172. 阶乘后的零 a*10^k
     * 给定一个整数 n，返回 n! 结果尾数中零的数量。
     * 关联题目：最优解都需要找规律。。。
     * 258. 各位相加
     * 400. 第N个数字
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        // 解法一 会超时，看规律
        // 我们把每个乘数再稍微分解下，看一个例子。
        // 11! = 11 * 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 = 11 * (2 * 5) * 9 * (4 * 2) * 7 * (3 * 2) * (1 * 5) * (2 * 2) * 3 * (1 * 2) * 1
        // 对于含有 2 的因子的话是 1 * 2, 2 * 2, 3 * 2, 4 * 2 ...
        // 对于含有 5 的因子的话是 1 * 5, 2 * 5...
        // 含有 2 的因子每两个出现一次，含有 5 的因子每 5 个出现一次，所有 2 出现的个数远远多于 5，换言之找到一个 5，一定能找到一个 2 与之配对。所以我们只需要找有多少个 5。
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int x = i;
            while (x % 5 == 0) {
                x /= 5;
                ans++;
            }
        }

        // 解法二
        // 对于一个数的阶乘，就如之前分析的，5 的因子一定是每隔 5 个数出现一次，也就是下边的样子。
        // n! = 1 * 2 * 3 * 4 * (1 * 5) * ... * (2 * 5) * ... * (3 * 5) *... * n
        // 因为每隔 5 个数出现一个 5，所以计算出现了多少个 5，我们只需要用 n/5 就可以算出来。
        // 但还没有结束，继续分析。
        // ... * (1 * 5) * ... * (1 * 5 * 5) * ... * (2 * 5 * 5) * ... * (3 * 5 * 5) * ... * n
        // 每隔 25 个数字，出现的是两个 5，所以除了每隔 5 个数算作一个 5，每隔 25 个数，还需要多算一个 5。
        // 也就是我们需要再加上 n / 25 个 5。
        // 同理我们还会发现每隔 5 * 5 * 5 = 125 个数字，会出现 3 个 5，所以我们还需要再加上 n / 125 。
        // 综上，规律就是每隔 5 个数，出现一个 5，每隔 25 个数，出现 2 个 5，每隔 125 个数，出现 3 个 5... 以此类推。
        // 最终 5 的个数就是 n / 5 + n / 25 + n / 125 ...
        // 写程序的话，如果直接按照上边的式子计算，分母可能会造成溢出。所以算 n / 25 的时候，我们先把 n 更新，n = n / 5，然后再计算 n / 5 即可。后边的同理。
        while (n > 0) {
            ans += n / 5;
            n /= 5;
        }
        return ans;
    }

    /**
     * 268. 缺失数字
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        // 常规解法
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;

        // 此解法很有意思，使用异或运算
        // 可以将结果的初始值设为n，因为下标和元素数差一。
        // 再对数组中的每一个数以及它的下标进行一个异或运算，即：
        // missing
        // =4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
        // =(4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
        // =0∧0∧0∧0∧2
        // =2
        // 就得到了缺失的数字为 2。
        //
        int missing = nums.length; // 因为下标和元素数差一。
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    /**
     * 258. 各位相加 ab=a*10+b ab%9=(a*9+a+b)%9=(a+b)%9 abc%9=(a+b+c)%9，展开举例即可
     * 看了下 Discuss ，原来要求的数叫做数字根，看下 维基百科 的定义。
     * 在数学中，数根(又称位数根或数字根Digital root)是自然数的一种性质，换句话说，每个自然数都有一个数根。
     * 然后是它的用途:
     * 数根可以计算模运算的同余，对于非常大的数字的情况下可以节省很多时间。
     * 数字根可作为一种检验计算正确性的方法。例如，两数字的和的数根等于两数字分别的数根的和。
     * 另外，数根也可以用来判断数字的整除性，如果数根能被3或9整除，则原来的数也能被3或9整除。
     * 接下来讨论我们怎么求出树根。
     * 我们把 1 到 30 的树根列出来。
     * 原数: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
     * 数根: 1 2 3 4 5 6 7 8 9  1  2  3  4  5  6  7  8  9  1  2  3  4  5  6  7  8  9  1  2  3
     * Copy
     * 可以发现数根 9 个为一组， 1 - 9 循环出现。我们需要做就是把原数映射到树根就可以，循环出现的话，想到的就是取余了。
     * 结合上边的规律，对于给定的 n 有三种情况。
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        // 解法一 常规解法
        if (num < 10) {
            return num;
        }
        int ans = 0;
        while (num > 0) {
            ans += num % 10;
            num = num / 10;
        }
        return addDigits(ans);

        // 解法二 分析后的解法
        return (num - 1) % 9 + 1;
    }

    /**
     * 204. 计数质数
     * 统计所有小于非负整数 n 的质数的数量。
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        // 解法一，暴力解法
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimes(i)) {
                ans++;
            }
        }
        return ans;

        // 解法二，通过标记2、3、5、7、8等<n等多倍体也标记
        // 空间换时间
        int ans = 0;
        boolean[] isNotPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!isNotPrimes[i]) {
                ans++;
                for (int j = 2; j * i < n; j++) {
                    isNotPrimes[i * j] = true;
                }
            }
        }
        return ans;

        // 解法二三统称厄拉多塞筛法
        // 解法三，优化解法二
        // 我们可以稍微优化一下，让 j 从 i 的平方开始遍历，而不是从 2 * i 开始

        int ans = 0;
        boolean[] isNotPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!isNotPrimes[i]) {
                ans++;
                for (int j = i; j * i < n; j++) {
                    isNotPrimes[i * j] = true;
                }
            }
        }
        return ans;
    }

    public boolean isPrimes(int n) {
        int sqrtN = (int) Math.sqrt(n);
        // ⚠️注意边界条件
        for (int i = 2; i <= sqrtN; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 292. Nim 游戏
     * 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。
     * 拿掉最后一块石头的人就是获胜者。你作为先手。
     * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
     * <p>
     * 关联题目：
     * 1025. 除数博弈
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        // 解法一 博弈公式
        // 巴什博奕，n%(m+1)!=0时，先手总是会赢的
        // m = 3 每次取多少个，n为总数
        int m = 3;
        if (n % (m + 1) == 0) {
            return true;
        }
        return false;

        // 解法二 dp动态规划
        boolean[] dp = new boolean[n + 6];
        // 先手<=3时取胜，。
        // 胜 0 1 2 3 5 6 7 9 10 11
        // 负 4 8 12
        // 边界
        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        dp[3] = true;
        dp[4] = false;
        dp[5] = true;
        dp[6] = true;
        // 状态转移
        // 然后我们考虑所有的情况。
        // 你拿走 1 个石子，然后不论对方从剩下的石子中拿走 1 个，2 个，还是 3 个，判断一下剩下的石子你是不是有稳赢的策略。
        // 如果上边不行的话，你就拿走 2 个石子，然后再判断不论对方从剩下的石子拿走 1 个，2 个，还是3 个，剩下的石子你是不是都有稳赢的策略。
        // 如果上边还不行的话，你就拿走 3 个石子，然后再判断不论对方从剩下的石子拿走 1 个，2 个，还是3 个，剩下的石子你是不是都有稳赢的策略。
        // 如果上边通通不行，那就是你输了。
        for (int num = 6; num <= n; num++) {
            for (int i = 1; i <= 3; i++) {
                if (dp[num - i - 1] && dp[num - i - 2] && dp[num - i - 3]) {
                    dp[num] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    /**
     * 1025. 除数博弈
     * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {

    }

    /**
     * 877. 石子游戏
     * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
     * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。
     * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
     * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {

    }

    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * <p>
     * 分治法
     * 相关问题：
     * 4. 寻找两个有序数组的中位数
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        // 解法一：动态规划
        // 用一个二维数组 dp[ i ] [ len ] 表示从下标 i 开始，长度为 len 的子数组的元素和。
        // 这样长度是 len + 1 的子数组就可以通过长度是 len 的子数组去求，也就是下边的递推式，
        // dp [ i ] [ len + 1 ] = dp[ i ] [ len ] + nums [ i + len - 1 ]。
        // 当然，和第 5 题一样，考虑到求 i + 1 的情况的时候，
        // 我们只需要 i 时候的情况，所有我们其实没必要用一个二维数组，直接用一维数组就可以了。

        // dp[i][j] 表示从index i～j的累加元素和
        // 二维数组会超出内存限制
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + nums[j];
                ans = Integer.max(ans, dp[i][j]);
            }
            ans = Integer.max(ans, dp[i][i]);
        }
        return ans;

        // 优化dp为一维数组
        // 代码中添加了临时变量sum，可以再进行优化，不使用dp数组。
        int n = nums.length;
        int ans = Integer.MIN_VALUE;
        int[] dp = new int[n];
        // 边界
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        // 状态转移
        for (int i = 0; i < n; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < n; j++) {
                sum += nums[j];
                dp[i] = Integer.max(sum, dp[i]);
            }
            ans = Integer.max(ans, dp[i]);
        }
        return ans;
        // 优化dp一维数组再优化
        // 用一个一维数组 dp [ i ] 表示以下标 i 结尾的子数组的元素的最大的和，
        // 也就是这个子数组最后一个元素是下边为 i 的元素，并且这个子数组是所有以 i 结尾的子数组中，和最大的。
        // 这样的话就有两种情况，
        // 如果 dp [ i - 1 ] < 0，那么 dp [ i ] = nums [ i ]。
        // 如果 dp [ i - 1 ] >= 0，那么 dp [ i ] = dp [ i - 1 ] + nums [ i ]。

        // 解法二：正数增益
        // 动态规划，再进行优化，不使用dp数组
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(sum, ans);
        }
        return ans;

        // 解法三：分治法
        // 分治法其他题解里将的很清楚了。其实就是它的最大子序和要么在左半边，要么在右半边，要么是穿过中间，对于左右边的序列，情况也是一样，因此可以用递归处理。中间部分的则可以直接计算出来，时间复杂度应该是
        // 步骤：
        // 1、定义子问题递归函数getSubMax(begin, end, nums);
        // 	2、找到最小子问题（递归出口）：当begin=end时，返回nums[begin]；
        // 3、分解最大子序和问题：
        // 	当最大的子序和的序列不穿透mid时，最大子序和为左右子序列最大序列和leftMax、rightMax的最大值。
        // 	当最大子序和的序列穿透mid时，最大子序和为crossMidMax。
        // 	4、解决子问题，
        // 	递归求解左右子序列最大序列和：
        // leftMax = getSubMax(begin, mid, nums);
        // rightMax = getSubMax(mid + 1, end, nums);
        // 	直接求解穿透mid的最大序列和：
        // 分别出以mid-1为终结的左子序列最大序列和、以mid+1为起始的右子序列最大序列和，最后加上mid元素的值。
        // crossMidMax = crossMidLeftMax + nums[mid] + crossMidRightMax;
        // 5、合并， leftMax、rightMax和crossMidMax三者的最大值就是最大子序列和。
        int n = nums.length;
        return getSubMax(0, n - 1, nums);
    }

    public int getSubMax(int begin, int end, int[] nums) {
        if (begin == end) { // 递归出口
            return nums[begin];
        }
        int mid = (begin + end) / 2;
        int leftMax = getSubMax(begin, mid, nums); // 递归求解左子序列最大序列和
        int rightMax = getSubMax(mid + 1, end, nums); // 递归求解右子序列最大序列和
        int crossMidMax = getCrossMidMax(begin, end, mid, nums); // 求解子序列穿透mid时的最大序列和
        return Math.max(Math.max(leftMax, rightMax), crossMidMax); // 合并子问题的解，返回最终解
    }

    public int getCrossMidMax(int begin, int end, int mid, int[] nums) {
        int sum = 0;
        int crossLeftMax = 0;
        int crossRightMax = 0;
        if (mid > begin) {
            for (int i = mid - 1; i <= begin; i--) {
                sum += nums[i];
                crossLeftMax = Math.max(sum, crossLeftMax); // 找出左边以mid-1结束的最大子序和
            }
        }
        if (mid < end) {
            sum = 0;
            for (int i = mid + 1; i >= end; i++) {
                sum += nums[i];
                crossRightMax = Math.max(sum, crossRightMax); // 同理，找出右边边以mid+1起始的最大子序和
            }
        }
        return crossLeftMax + nums[mid] + crossRightMax;
    }

    /**
     * 4. 寻找两个有序数组的中位数
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * 示例 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * 则中位数是 2.0
     * 示例 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 则中位数是 (2 + 3)/2 = 2.5
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 解法一，暴力解法，合并再求中位数
        int n = nums1.length;
        int m = nums2.length;
        int[] nums = new int[(n + m)];
        int x = 0, y = 0;
        for (int i = 0; i < (n + m); i++) {
            if (x == n) {
                nums[i] = nums2[y++];
                continue;
            }
            if (y == m) {
                nums[i] = nums1[x++];
                continue;
            }
            if (nums1[x] < nums2[y]) {
                nums[i] = nums1[x++];
                System.out.println(x);
            } else {
                nums[i] = nums2[y++];
            }
        }
        if (((n + m) & 1) == 0) {
            return (nums[(n + m) / 2] + nums[(n + m) / 2 - 1]) / 2.0;
        } else {
            return nums[(n + m) / 2];
        }

        // 解法二，快排Patition的思想，我们不需要排序所有的元素，只要找到第mid个元素就可以了
        // 也是解法一的一个改进版本
        int n = nums1.length;
        int m = nums2.length;
        int x = 0, y = 0;
        int mid = 0;
        int mid_2 = 0;
        for (int i = 0; i <= (n + m) / 2; i++) {
            mid_2 = mid; // 这步挺巧妙的。。存储前一次的结果。
            if (x == n) {
                mid = nums2[y++];
                continue;
            }
            if (y == m) {
                mid = nums1[x++];
                continue;
            }
            if (nums1[x] < nums2[y]) {
                mid = nums1[x++];
            } else {
                mid = nums2[y++];
            }
        }
        if (((m + n) & 1) == 0) {
            return (mid + mid_2) / 2.0;
        } else {
            return mid;
        }

        // 解法三，递归分治
        // 步骤：
        // 1、定义子问题递归函数getK(int[] nums1, int[] nums2, int begin1, int begin2, int end1, int end2, int k)
        // 	2、找到最小子问题（递归出口）：
        // 	当k=1时且数组长度都不为0时，找出最小的值即为第k个数的值，返回min(nums1[begin1], nums2[begin2])。
        // 3、分解问题：
        // 	通过数组有序的条件，判断两个数组第k/2个数的大小，筛选排除较小的前k/2个数。(哪个小，就表明该数组的前 k / 2 个数字都不是第 k 小数字，所以可以排除)
        // 	4、解决子问题，
        // 	当某一个数组长度为0时，返回另外一个数组的第k个数；
        // 	尾递归筛选前k/2个数，最后返回第k个值。
        // 	若数组长度小于k/2，则取最后一个元素进行比较。
        // 5、合并，算法使用尾递归，将每次递归的结果传递至下一次递归，直至筛选出第k个数。
        int n = nums1.length;
        int m = nums2.length;
        // int k = (m+n+1)/2;
        // m+n为奇数时，k为偶数，中位数为第k个数的值。k_left == k_right
        // m+n为偶数时，k为奇数，中位数为第k个和第k+1个数的平均值。k_left+1 == k_right
        int k_left = (m + n + 1) / 2;
        int k_right = (m + n + 2) / 2;
        // k为偶数，中位数为第k个数的值。k_left == k_right，取两次getK的平均值，仍是自身。
        // k为奇数，中位数为第k个和第k+1个数的平均值。k_left+1 == k_right，取两次getK的平均值，即为所求。
        return (getK(nums1, nums2, 0, 0, n - 1, m - 1, k_left)
                + getK(nums1, nums2, 0, 0, n - 1, m - 1, k_right)) / 2.0;
    }

    public int getK(int[] nums1, int[] nums2, int begin1, int begin2, int end1, int end2, int k) {
        // 求出当前的数组长度，若某一个数组长度为0时，返回另外一个数组的第k个数，即为所求。
        int len1 = end1 - begin1 + 1;
        int len2 = end2 - begin2 + 1;
        if (len1 == 0) {
            return nums2[begin2 + k - 1];
        } else if (len2 == 0) {
            return nums1[begin1 + k - 1];
        }
        // 递归出口，当数组长度都不为0时，找出最小的值即为第k个数的值
        if (k == 1) {
            return Math.min(nums1[begin1], nums2[begin2]);
        }
        // 筛选最多前k/2个数，若长度小于k/2则取最后一个元素进行比较，下一次递归中其数组长度为0
        int x = begin1 + Math.min(len1, k / 2) - 1;
        int y = begin2 + Math.min(len2, k / 2) - 1;
        if (nums1[x] < nums2[y]) {
            // 比较结束，更新数组起始索引和剩余需要筛选的数量k
            return getK(nums1, nums2, x + 1, begin2, end1, end2, k - (x - begin1 + 1));
        } else {
            return getK(nums1, nums2, begin1, y + 1, end1, end2, k - (y - begin2 + 1));
        }
    }

    /**
     * 98. 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        /*
        // 解法一、递归，常规思路有问题，需要每次遍历得到左右子树的最大最小值
        // 无法处理如下情况
        // [10,5,15,null,null,6,20]
        //      10
        //     /  \
        //    5   15
        //       /  \
        //      6   20
        if (root == null || root.left == null && root.right == null) {
            return true;
        }
        if (isValidBST(root.left)) {
            if (root.left != null) {
                if (root.val <= getMaxOfBST(root.left)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        if (isValidBST(root.right)) {
            if (root.right != null) {
                if (root.val >= getMinOfBST(root.right)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;

        // 解法二、递推，自顶向下，通过[min, max]进行限制
        // 可以观察到，左孩子的范围是 （父结点左边界，父节点的值），右孩子的范围是（父节点的值，父节点的右边界）。
        return getAns(root, null, null);

        // 解法三、中序遍历，形成一个递增序列，如果后遍历的数小于先遍历的数则返回false
        Integer pre = null; // 一开始使用Integer.MIN，不符合测试用例边界需求，使用null代替无穷小
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (pre == null) {
                    pre = pop.val;
                } else if (pre >= pop.val) {
                    return false;
                } else {
                    pre = pop.val;
                }
                root = pop.right;
            }
        }
        return true;
        */
        // 解法三、中序遍历-改进
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }

    private boolean getAns(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.val <= min) {
            return false;
        }
        if (max != null && node.val >= max) {
            return false;
        }
        return getAns(node.left, min, node.val) && getAns(node.right, node.val, max);
    }

    private int getMinOfBST(TreeNode root) {
        int min = root.val;
        while (root != null) {
            if (root.val <= min) {
                min = root.val;
            }
            root = root.left;
        }
        return min;
    }

    private int getMaxOfBST(TreeNode root) {
        int max = root.val;
        while (root != null) {
            if (root.val >= max) {
                max = root.val;
            }
            root = root.right;
        }
        return max;
    }

    /**
     * @Description: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 相关标签：位运算、哈希表
     * 相似题目：
     * 只出现一次的数字 II
     * 只出现一次的数字 III
     * 缺失数字
     * 寻找重复数
     * 找不同
     * @Param:
     * @return:
     * @Date: 2020/5/14
     * @Author: xbb1973
     */
    public int singleNumber(int[] nums) {
        // 解法一，暴力枚举，使用map容器，适用同类问题
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                countMap.put(num, countMap.get(num) + 1);
            } else {
                countMap.put(num, 1);
            }
        }
        for (int num : nums) {
            if (countMap.get(num).intValue() == 1) {
                return num;
            }
        }
        return -1;

        // 解法一，暴力枚举，使用set容器
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                set.remove(nums[i]);
            }
        }
        return set.iterator().next();

        // 解法二，暴力枚举，数学推导
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;//之前的数字和
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            sum += nums[i];
        }
        int sumMul = 0;//出现过的数字和
        for (int n : set) {
            sumMul += n;
        }
        sumMul = sumMul * 2;
        return sumMul - sum;

        // 解法二，位操作
        // int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (ans != 0) {
                ans ^= nums[i];
            } else {
                ans = nums[i];
            }
        }
        return ans;

        // 解法二，位操作，小优化，初始值为0就可以解决战斗了
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;

    }

    /**
     * @Description: 137. 只出现一次的数字 II
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * @Param:
     * @return:
     * @Date: 2020/5/14
     * @Author: xbb1973
     */
    public int singleNumber(int[] nums) {
        // 解法一，暴力枚举，使用map容器，适用同类问题
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                countMap.put(num, countMap.get(num) + 1);
            } else {
                countMap.put(num, 1);
            }
        }
        for (int num : nums) {
            if (countMap.get(num).intValue() == 1) {
                return num;
            }
        }
        return -1;

        // 解法二，数学法，使用set容器，适用同类问题，有溢出问题
        AtomicLong ans = new AtomicLong();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            ans.addAndGet(num);
            if (!set.contains(num)) {
                set.add(num);
            }
        }
        set.forEach(i -> ans.addAndGet(-3 * i));
        return (int) (ans.longValue() / -2);

        // 解法三，位操作，首位是符号位！
        int ans = 0;
        //考虑每一位
        for (int i = 0; i < 32; i++) {
            int count = 0;
            //考虑每一个数
            for (int j = 0; j < nums.length; j++) {
                //当前位是否是 1
                if ((nums[j] >>> i & 1) == 1) {
                    count++;
                }
            }
            //1 的个数是否是 3 的倍数
            if (count % 3 != 0) {
                ans = ans | 1 << i;
            }
        }
        return ans;

    }

    /**
     * @Description: 260. 只出现一次的数字 III
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，
     * 其余所有元素均出现两次。 找出只出现一次的那两个元素。
     * @Param:
     * @return:
     * @Date: 2020/5/14
     * @Author: xbb1973
     */
    public int[] singleNumber(int[] nums) {
        // 解法一，暴力枚举，使用map容器，适用同类问题
        // List<Integer> ans = new ArrayList<>();
        int[] ans = {0, 0};
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (countMap.containsKey(num)) {
                countMap.put(num, countMap.get(num) + 1);
            } else {
                countMap.put(num, 1);
            }
        }
        int index = 0;
        for (int num : nums) {
            if (countMap.get(num).intValue() == 1) {
                ans[index] = num;
                index++;
            }
        }
        return ans;

        // 解法二，位操作，异或出diff，根据diff再找其他的
        int[] ans = {0, 0};
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        System.out.println(diff);
        // 使用diff2将数组分组，不同组内分别有一个目标数字
        int diff2 = Integer.highestOneBit(diff);
        for (int num : nums) {
            // 直接使用&=不行，因为一组里不仅有目标数字
            // 需要使用^异或
            // if ((diff2 & num) != 0) {
            //     ans[0] = num;
            // }
            if ((diff2 & num) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        // for (int num : nums) {
        //     if ((ans[0] ^ num) == diff) {
        //         ans[1] = num;
        //     }
        // }
        return ans;
    }

    /**
     * @Description: 389. 找不同
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母。
     * @Param:
     * @return:
     * @Date: 2020/5/14
     * @Author: xbb1973
     */
    public char findTheDifference(String s, String t) {
        // 解法一，暴力枚举，使用map容器
        Map<Character, Integer> countMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            if (countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1);
            } else {
                countMap.put(c, 1);
            }
        }
        for (char c : t.toCharArray()) {
            if (countMap.containsKey(c) && countMap.get(c) > 0) {
                countMap.put(c, countMap.get(c) - 1);
            } else {
                return c;
            }
        }
        return '0';

        // 解法二，数学法
        int ans = 0;
        for (char c : t.toCharArray()) {
            ans += c;
        }
        for (char c : s.toCharArray()) {
            ans -= c;
        }
        return (char) ans;

        // 解法二，数学法，小优化，提速
        int ans = 0;
        for (char c : t.toCharArray()) {
            ans ^= c;
        }
        for (char c : s.toCharArray()) {
            ans ^= c;
        }
        return (char) ans;

        // 解法三，维护26位字母表，
        int[] record = new int[26];
        int lenS = s.length();
        int lenT = t.length();
        for (int i = 0; i < lenS; i++) {
            record[s.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < lenT; i++) {
            record[t.charAt(i) - 'a'] -= 1;
            if (record[t.charAt(i) - 'a'] < 0) return t.charAt(i);

        }
        return ' ';

    }

    /**
     * @Description: 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * 示例 1:输入: [1,3,4,2,2] 输出: 2
     * 示例 2:输入: [3,1,3,4,2] 输出: 3
     * 说明：
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     * @Param:
     * @return:
     * @Date: 2020/5/15
     * @Author: xbb1973
     */
    public int findDuplicate(int[] nums) {
        // 解法一，二分查找，鸽巢原理，找中值比较
        int low = 1;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) >>> 1;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;

        // 解法二，找环，把数组当next链表
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        // (k2 - 2k1 - 1) * n + (n - y) = x
        // n = 0, 1, 2, 3 ...，令n取0即可。
        // 左边右边的含义结合起来就是，从相遇点走到入口点，
        // 然后转 (k2 - 2k1 - 1) 圈后再次回到入口点的这段时间，
        // 刚好就等于从 head 走向入口点的时间。
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    private int findDuplicate(int[] array) {
        // 1
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                return array[i];
            } else {
                map.put(array[i], array[i]);
            }
        }
        return -1;
    }

    private int findDuplicate(List<Integer> array) {
        // 1
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            if (map.containsKey(array.get(i))) {
                return map.get(array.get(i));
            } else {
                map.put(array.get(i), array.get(i));
            }
        }
        return -1;

        // 2
        // int[] dp = new int[array.size()];
        // int ans;
        // for (int i = 0; i < array.size(); i++) {
        //     dp[array.get(i)] += 1;
        //     if (dp[array.get(i)] > 1) {
        //         return array.get(i);
        //     }
        // }
        // return -1;
    }

//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================
//2、二分法=================================================

    /**
     * @Description: 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * 示例 1: 输入: 4 输出: 2
     * 示例 2: 输入: 8 输出: 2
     * 说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     * <p>
     * 标签：数学、二分查找
     * 相似题目：Pow(x, n)、有效的完全平方数
     * @Param:
     * @return:
     * @Date: 2020/5/17
     * @Author: xbb1973
     */
    public int mySqrt(int x) {
        // 解法一、暴力解，超时
        int index = 1;
        long ans = 1;
        while (ans < x) {
            index++;
            ans = index * index;
        }
        if (ans == x) {
            return index;
        } else {
            return index - 1;
        }

        // 解法二、二分，利用数学变形解决溢出问题
        int L = 1, R = x;
        int ans = 0;
        while (L <= R) {
            // 1、数学变形解决溢出问题
            int mid = L + (R - L) / 2;
            // 2、数学变形解决溢出问题，本来要mid*mid和x比较的
            //      mid*mid ? x
            //      mid*mid > x
            //      mid > x/mid==div
            int div = x / mid;
            if (div == mid) {
                return mid;
            } else if (mid < div) {
                // 存储ans用于返回，因为不是精确的，只需要返回整数
                // 所以取左边
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;

        // orginal 方案二，自行优化
        {
            public int mySqrt ( int x){
            int L = 1, R = x;
            while (L <= R) {
                int mid = （L + R) /2;
                int square = mid * mid;
                if (square == x) {
                    return mid;
                } else if (square < x) {
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return ?;
        }

        }

        // 解法三，求x^2 sqrt转化为求x^2-n的零点
        // 解法四，牛顿法

    }

    /**
     * @Description: 410. 分割数组的最大值
     * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
     * 注意:
     * 数组长度 n 满足以下条件:
     * 1 ≤ n ≤ 1000
     * 1 ≤ m ≤ min(50, n)
     * 示例:
     * 输入:
     * nums = [7,2,5,10,8]
     * m = 2
     * 输出:
     * 18
     * 解释:
     * 一共有四种方法将nums分割为2个子数组。
     * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
     * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     * @Param:
     * @return:
     * @Date: 2020/5/17
     * @Author: xbb1973
     */
    public int splitArray(int[] nums, int m) {
        // 如何写guess函数，
    }


    /**
     * @Description: 79. 单词搜索,DFS、回溯
     * @Param:
     * @return:
     * @Date: 2020/6/23
     * @Author: xbb1973
     */
    public boolean exist(char[][] board, String word) {
        // System.out.println(word.length());
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] marked = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                marked[i][j] = false;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (findWord(board, word, marked, 0, i, j, rows, cols)) {
                    return true;
                }
            }
        }
        return false;
    }

    int[][] next = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private boolean findWord(char[][] board, String word, boolean[][] marked, int length, int r, int c, int rows, int cols) {
        // 1、递归退出条件/回溯达成条件
        if (length == word.length()) {
            return true;
        }
        // 2、判断matrix[rows][cols] != str[pathLen]，是否在搜索的字符串中
        //      判断边界条件、判断
        //      标记不可重入
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || word.charAt(length) != board[r][c]
                || marked[r][c]) {
            return false;
        }
        // 3、标记/先序操作
        marked[r][c] = true;
        // 4、从该结点往4个方向进行搜索/递归
        for (int[] n : next) {
            if (findWord(board, word, marked, length + 1, r + n[0], c + n[1], rows, cols)) {
                return true;
            }
        }
        // 5、回溯，取消标记，每次递归的起始点不同，暴力遍历所有点作为起始点//后序操作
        marked[r][c] = false;
        return false;
    }


    // 算法导论，理论与实践结合！
    // 912. 排序数组
    // 给你一个整数数组 nums，请你将该数组升序排列。
    // 示例 1：
    // 输入：nums = [5,2,3,1]
    // 输出：[1,2,3,5]
    // 示例 2：
    // 输入：nums = [5,1,1,2,0,0]
    // 输出：[0,0,1,1,2,5]
    // 提示：
    // 1 <= nums.length <= 50000
    // -50000 <= nums[i] <= 50000

    // 暴力求解，显然会超时
    public int[] sortArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[j - 1] > nums[j]) {
                    int tmp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        return nums;
    }
    // 使用分治，归并排序or快速排序
    // 1、归并排序，把具有n个元素的待排序数组划分为2个具有n/2个元素的待排序数组，分解至方便求解，合并解。
    // 伪代码
    // void merge_sort(A, low, high) {
    //     if (low < high) {
    //         mid = (low+high)/2;
    //         merge_sort(A, low, mid);
    //         merge_sort(A, mid+1, high);
    //         merge(A, low, mid, high);
    //     }
    // }
    // void merge(A, low, mid, high) {
    //     // 计算分解数组的长度
    //     int n1 = mid - low + 1;
    //     int n2 = high - mid;
    //     // 使用辅助空间L、R存储
    //     for (int i = 0; i < n1; i++) {
    //         L[i] = A[p+i];
    //     }
    //     for (int i = 0; i < n2; i++) {
    //         R[i] = A[mid+1+i];
    //     }
    //     // 哨兵？
    //     L[n1] = Integer.MAX_VALUE;
    //     R[n2] = Integer.MAX_VALUE;
    //     // 归并
    //     int Lindex = 0;
    //     int Rindex = 0;
    //     for (int i = low; i < high; i++) {
    //         if (L[Lindex]<=R[Rindex]){
    //             A[i] = L[Lindex];
    //             Lindex++;
    //         } else {
    //             A[i] = R[Rindex];
    //             Rindex++;
    //         }
    //     }
    // }

    public int[] sortArray(int[] nums) {
        merge_sort(nums, 0, nums.length - 1);
        return nums;
    }

    void merge_sort(int[] A, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            merge_sort(A, low, mid);
            merge_sort(A, mid + 1, high);
            merge(A, low, mid, high);
        }
    }

    void merge(int[] A, int low, int mid, int high) {
        // 计算分解数组的长度
        int n1 = mid - low + 1;
        int n2 = high - mid;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 2];
        // 使用辅助空间L、R存储
        for (int i = 0; i < n1; i++) {
            L[i] = A[low + i];
        }
        for (int i = 0; i < n2; i++) {
            R[i] = A[mid + 1 + i];
        }
        // 哨兵？
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        // 归并
        int Lindex = 0;
        int Rindex = 0;
        for (int i = low; i <= high; i++) {
            // 哨兵在此处可以简化算法，可避免越界，因此不需要判断Lindex和Rindex越界的情况。
            // 否则需要增加对每个子数组判断是否到达末尾的判断。
            if (L[Lindex] <= R[Rindex]) {
                A[i] = L[Lindex];
                Lindex++;
            } else {
                A[i] = R[Rindex];
                Rindex++;
            }
        }
    }


    // 2、快速排序，把具有n个元素的待排序数组划分为2个[low,q-1] [q,high]的待排序数组，分解至方便求解，合并解。
    // 伪代码和实现
    void quick_sort(int[] A, int low, int high) {
        if (low < high) {
            int partition = partition(A, low, high);
            quick_sort(A, low, partition);
            quick_sort(A, partition + 1, high);
        }
    }

    int partition(int[] A, int low, int high) {
        int pivot = A[low];
        while (low < high) {
            while (low < high && A[high] > pivot) high--;
            A[low] = A[high];
            while (low < high && A[low] < pivot) low++;
            A[high] = A[low];
        }
        A[low] = pivot;
        return low;
    }

    public int[] sortArray(int[] nums) {
        quick_sort(nums, 0, nums.length - 1);
        return nums;
    }

    // 46. 全排列
    // 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    // 示例:
    // 输入: [1,2,3]
    // 输出:
    // [
    //   [1,2,3],
    //   [1,3,2],
    //   [2,1,3],
    //   [2,3,1],
    //   [3,1,2],
    //   [3,2,1]
    // ]

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backTrackPermute(nums, new boolean[nums.length], new ArrayList<Integer>());
        return ans;
    }

    private void backTrackPermute(int[] nums, boolean[] hasUsed, List<Integer> arrayList) {
        if (arrayList.size() == nums.length) {
            // ⚠️这个地方错了两次了！！！！！
            // 直接传递arrarlist，到最后回溯的时候会remove掉掉。
            ans.add(new ArrayList(arrayList));
        }
        for (int i = 0; i < nums.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            arrayList.add(nums[i]);
            hasUsed[i] = true;
            backTrackPermute(nums, hasUsed, arrayList);
            arrayList.remove(arrayList.size() - 1);
            hasUsed[i] = false;
        }
    }

    // 给定一个可包含重复数字的序列，返回所有不重复的全排列。
    // 示例:
    // 输入: [1,1,2]
    // 输出:
    // [
    //   [1,1,2],
    //   [1,2,1],
    //   [2,1,1]
    // ]

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        // ⚠️若重复需要剪枝则数组必须先排序
        Arrays.sort(nums);
        backTrackPermute(nums, new boolean[nums.length], new ArrayList<Integer>());
        return ans;
    }

    private void backTrackPermute(int[] nums, boolean[] hasUsed, List<Integer> arrayList) {
        if (arrayList.size() == nums.length) {
            // ⚠️这个地方错了两次了！！！！！
            // 直接传递arrarlist，到最后回溯的时候会remove掉掉。
            ans.add(new ArrayList(arrayList));
        }
        for (int i = 0; i < nums.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            // ⚠️!hasUsed[i - 1]和hasUsed[i - 1]都是剪枝
            // 但是!hasUsed[i - 1]剪枝更加彻底，特点是：1、1'、1'' 出现的顺序只能是 1、1'、1''。
            // 反之则逆转出现顺序。
            if (i > 0 && !hasUsed[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            arrayList.add(nums[i]);
            hasUsed[i] = true;
            backTrackPermute(nums, hasUsed, arrayList);
            arrayList.remove(arrayList.size() - 1);
            hasUsed[i] = false;
        }
    }

    // 31. 下一个排列
    // 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
    // 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
    // 必须原地修改，只允许使用额外常数空间。
    // 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
    // 1,2,3 → 1,3,2
    // 3,2,1 → 1,2,3
    // 1,1,5 → 1,5,1
    public void nextPermutation(int[] nums) {
        int max = nums[0];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
                break;
            }
        }
        for (int i = index - 1; i > 0; i--) {
            if (nums[i] < max) {
                nums[index] = nums[i];
                nums[i] = max;
            }
        }
    }

    // LCS问题-动态规划
    // 	基本步骤：
    // 	（1）描述最优解的结构。
    // 	（2）递归的定义最优解的值。
    // 	（3）按自底向上方式计算出最优值
    //  （4）由计算出的结果，构造一个最优解
    // 1、最优子结构，X Y Z，1、2、3、
    // 2、最优解的递归式：
    // c[i,j]表示存放X和Y的LCS长度
    // c[i,j] = {      0,  i=0 or j=0;
    //          { c[i-1,j-1]+1,    ij>0且xi=yj;
    //          { Max(c[i,j-1],c[i-1,j]),    ij>0且xi≠yj;
    // 3、自底向上计算最优解的值。
    // 4、根据最优解的值，构造最优解。
    //
    // 1143. 最长公共子序列
    // 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
    // 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    // 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    // 两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
    // 若这两个字符串没有公共子序列，则返回 0。
    public int longestCommonSubsequence(String text1, String text2) {
        // 1、找到最优子结构。
        // 2、定义最优解值的递归式。
        // 3、自顶向下求解最优解值。
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] c = new int[l1 + 1][l2 + 1];
        int[][] b = new int[l1 + 1][l2 + 1];
        int max = 0;
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    c[i + 1][j + 1] = c[i][j] + 1;
                    // "↖️"
                    b[i + 1][j + 1] = 3;
                } else {
                    c[i + 1][j + 1] = Math.max(c[i + 1][j], c[i][j + 1]);
                    if (c[i + 1][j] > c[i][j + 1]) {
                        // "⬅️"
                        b[i + 1][j + 1] = 2;
                    } else {
                        // "⬆️ "
                        b[i + 1][j + 1] = 1;
                    }
                }
                // if (c[i + 1][j + 1] > max) {
                //     max = c[i + 1][j + 1];
                // }
            }
        }

        // 4、根据结果，构造最优解。
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
        return c[l1][l2];
    }

    // =====================================================================================================================
    // New beginning from here, 12/06 of 2020, shouldn't take this long to starting coding.
    // Leetcode 题解 - 双指针
    // 1. 有序数组的 Two Sum
    // 2. 两数平方和
    // 3. 反转字符串中的元音字符
    // 4. 回文字符串
    // 5. 归并两个有序数组
    // 6. 判断链表是否存在环
    // 7. 最长子序列

    // 1. 有序数组的 Two Sum
    // 167. Two Sum II - Input array is sorted (Easy)
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], numbers[i]);
        }
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                // return new int[]{numbers[i], target - numbers[i]};
                for (int j = i + 1; j < numbers.length; j++) {
                    if (map.get(target - numbers[i]) == numbers[j]) {
                        return new int[]{i + 1, j + 1};
                    }
                }
            }
        }
        return new int[2];
    }

    public int[] twoSum(int[] numbers, int target) { // Best Performace
        int i = 0;
        int j = numbers.length - 1; // key set
        while (i < j) {
            int total = numbers[i] + numbers[j];
            if (total == target) {
                return new int[]{i + 1, j + 1};
            } else if (total < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    // 2. 两数平方和
    // 633. Sum of Square Numbers (Easy)
    public boolean judgeSquareSum(int c) { // Best P
        double sqrt = Math.sqrt(c);
        int i = 0;
        int j = (int) sqrt + 1;
        while (i < j) {
            int total = i * i + j * j;
            if (total == c) {
                return true;
            } else if (total < c) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    // 3. 反转字符串中的元音字符
    // 345. Reverse Vowels of a String (Easy)
    public String reverseVowels(String s) {
        int i = 0;
        int j = s.length() - 1;
        StringBuffer ans = new StringBuffer(s);
        while (i < j) {
            char charAtI = s.charAt(i);
            char charAtJ = s.charAt(j);
            if (isAEIOU(charAtI) && isAEIOU(charAtJ)) {
                char tmpCharAtI = charAtI;
                ans.deleteCharAt(i);
                ans.insert(i, charAtJ);
                ans.deleteCharAt(j);
                ans.insert(j, tmpCharAtI);
                i++;
                j--;
            } else if (isAEIOU(charAtI) && !isAEIOU(charAtJ)) {
                j--;
            } else if (!isAEIOU(charAtI) && isAEIOU(charAtJ)) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return ans.toString();
    }

    HashSet<Character> vowenls = new HashSet<>(Arrays.asList('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'));

    public String reverseVowels(String s) { // BEST P
        int i = 0;
        int j = s.length() - 1;
        // StringBuffer ans = new StringBuffer(s);
        char[] ans = s.toCharArray(); // key set
        while (i < j) {
            char charAtI = s.charAt(i);
            char charAtJ = s.charAt(j);
            if (isAEIOU(charAtI) && isAEIOU(charAtJ)) {
                ans[i] = charAtJ;
                ans[j] = charAtI;
                i++;
                j--;
            } else if (isAEIOU(charAtI) && !isAEIOU(charAtJ)) {
                j--;
            } else if (!isAEIOU(charAtI) && isAEIOU(charAtJ)) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return new String(ans);
    }

    public boolean isAEIOU(char c) {
        return vowenls.contains(c);
        switch (c) {
            case 'a':
            case 'A':
            case 'e':
            case 'E':
            case 'i':
            case 'I':
            case 'o':
            case 'O':
            case 'u':
            case 'U':
                return true;
            default:
                return false;
        }
    }

    // 4. 回文字符串
    // 680. Valid Palindrome II (Easy)
    // 本题的关键是处理删除一个字符。
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        int k = 0;
        char[] charArray = s.toCharArray(); // key set
        while (i < j) {
            if (charArray[i] != charArray[j]) {
                return validPalindromeRecur(charArray, i + 1, j) || validPalindromeRecur(charArray, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean validPalindromeRecur(char[] charArray, int i, int j) {
        while (i < j) {
            if (charArray[i] != charArray[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean validPalindrome(String s) { // BEST P
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return validPalindromeRecur(s, i + 1, j) || validPalindromeRecur(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean validPalindromeRecur(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // 5. 归并两个有序数组
    // 88. Merge Sorted Array (Easy)
    public void merge(int[] nums1, int m, int[] nums2, int n) { // BEST P
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 || j >= 0) {
            if (i < 0) {
                nums1[k--] = nums2[j--];
            } else if (j < 0) {
                nums1[k--] = nums1[i--];
            } else if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

    // 6. 判断链表是否存在环
    // 141. Linked List Cycle (Easy)
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p1 != null && p2 != null && p2.next != null) { // key set, if not recycler will meet null
            if (p1 == p2) {
                return true;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return false;
    }

    // 7. 最长子序列
    // 524. Longest Word in Dictionary through Deleting (Medium)
    public String findLongestWord(String s, List<String> d) {
        int max = 0;
        String maxWord = "";
        for (String s1 : d) {
            if (isSubString(s, s1)) {
                if (s1.length() > max) {
                    maxWord = s1;
                    max = s1.length();
                } else if (s1.length() == max) { // return dict seq samll
                    int i = s1.compareTo(maxWord);
                    if (i < 0) {
                        maxWord = s1;
                    }
                }
            }
        }
        return maxWord;
    }

    public boolean isSubString(String s1, String s2) {
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) { // two point
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == s2.length();
    }

    // =====================================================================================================================
    // New beginning from here, 12/06 of 2020, shouldn't take this long to starting coding.

    // =====================================================================================================================
    // Another day for coding 12/07 of 2020, shouldn't take this long to starting coding.
    // Leetcode 题解 - 排序
    // 快速选择
    // 快速排序
    // 堆
    // 1. Kth Element
    // 桶排序
    // 1. 出现频率最多的 k 个元素
    // 2. 按照字符出现次数对字符串排序
    // 荷兰国旗问题
    // 1. 按颜色进行排序

    // 快速选择
    // 快速排序
    // 堆
    // 1. Kth Element
    // 215. Kth Largest Element in an Array (Medium)
    public int findKthLargest(int[] nums, int k) { // QuickSort O(N) O(1)
        int low = 0;
        int high = nums.length - 1;
        k = nums.length - k; // k == 2, target pIndex = N-2
        int partitionIndex = partition(low, high, nums);
        while (partitionIndex != k) { // SLOW in QuickSort
            if (partitionIndex > k) {
                partitionIndex = partition(low, partitionIndex - 1, nums);
            } else {
                partitionIndex = partition(partitionIndex + 1, high, nums);
            }
        }
        while (low < high) { // FAST in QuickSort, O(N) why slow then Arrays.sort(x)....
            if (partitionIndex == k) {
                break;
            } else if (partitionIndex > k) {
                high = partitionIndex - 1;
            } else {
                low = partitionIndex + 1;
            }
            partitionIndex = partition(low, high, nums);
        }
        return nums[partitionIndex];
    }

    /**
     * Get Kth Smaller Num, return Index from low~high index(0~nums.leth-1)
     *
     * @param low
     * @param high
     * @param nums
     * @return
     */
    public int partition(int low, int high, int[] nums) {
        int pivot = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= pivot) high--;
            nums[low] = nums[high];
            while (low < high && nums[low] < pivot) low++;
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    public int findKthLargest(int[] nums, int k) { // BEST P ????????   O(NlogN) O(1)
        Arrays.sort(nums); // QuickSort O(NlogN) ....
        return nums[nums.length - k];
    }

    public int findKthLargest(int[] nums, int k) { // 2nd BEST P, make sense   O(NlogK) O(1)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(); // small top heap
        for (int num : nums) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) { // 维护堆的大小为 K
                priorityQueue.poll(); // poll del top element
            }
        }
        return priorityQueue.peek(); // peek top element
    }

    // 桶排序
    // 1. 出现频率最多的 k 个元素
    // 2. 按照字符出现次数对字符串排序

    // 1. 出现频率最多的 k 个元素
    // 347. Top K Frequent Elements (Medium)
    public int[] topKFrequent(int[] nums, int k) { // BEST P
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1); // get☑️
        }

        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (Integer num : map.keySet()) {
            Integer freq = map.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] == null) continue;
            for (Integer n : buckets[i]) {
                topK.add(n);
                if (topK.size() == k) break; // bug !
            }
            if (topK.size() == k) break; // add more for fix bug

            // if (buckets[i].size() <= (k - topK.size())) {
            //     topK.addAll(buckets[i]);
            // } else {
            //     topK.addAll(buckets[i].subList(0, k - topK.size())); // get ☑️
            // }
        }
        int[] ans = new int[k];
        System.out.println(k);
        System.out.println(topK.size());
        // for (int i = 0; i < k; i++) { // worked
        for (int i = 0; i < topK.size(); i++) { // not, why? cause topK more than k, upper code issue.
            ans[i] = topK.get(i);
        }
        return ans;
    }

    // 2. 按照字符出现次数对字符串排序
    // 451. Sort Characters By Frequency (Medium)
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1); // get☑️
        }

        List<Character>[] buckets = new ArrayList[s.length() + 1];
        for (Character character : map.keySet()) {
            Integer freq = map.get(character);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(character);
        }

        // StringBuffer ans = new StringBuffer(); // sync SLOWER
        StringBuilder ans = new StringBuilder(); // FAST
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] == null) continue;
            for (Character character : buckets[i]) {
                for (int times = 0; times < i; times++) {
                    ans.append(character);
                }
            }
        }
        return ans.toString();
    }


    // 荷兰国旗问题
    // 1. 按颜色进行排序
    public void sortColors(int[] nums) {
        int[] frequencies = new int[3];
        for (int i = 0; i < nums.length; i++) {
            switch (nums[i]) {
                case 0:
                    frequencies[0]++;
                    break;
                case 1:
                    frequencies[1]++;
                    break;
                case 2:
                    frequencies[2]++;
                    break;
                default:
                    break;
            }
        }
        // 0-2 1-2 2-3
        int k = 0;
        for (int i = 0; i < frequencies[0]; i++) {
            nums[k + i] = 0;
        }
        k += frequencies[0];
        for (int i = 0; i < frequencies[1]; i++) {
            nums[k + i] = 1;
        }
        k += frequencies[1];
        for (int i = 0; i < frequencies[2]; i++) {
            nums[k + i] = 2;
        }
        // return nums;
    }

    // =====================================================================================================================
    // Another days for coding 12/07 of 2020, shouldn't take 3 days to complete.... faster faster....


    // =====================================================================================================================
    // Another days for coding 12/13 of 2020..
    // Leetcode 题解 - 贪心思想
    // 1. 分配饼干
    // 2. 不重叠的区间个数
    // 3. 投飞镖刺破气球
    // 4. 根据身高和序号重组队列
    // 5. 买卖股票最大的收益
    // 6. 买卖股票的最大收益 II
    // 7. 种植花朵
    // 8. 判断是否为子序列
    // 9. 修改一个数成为非递减数组
    // 10. 子数组最大的和
    // 11. 分隔字符串使同种字符出现在一起

    // Key Set
    // 如何证明不存在比贪心策略更优的策略，即贪心策略就是最优策略。 ???

    // 1. 分配饼干
    // 455. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        // g - requirement
        // s - resource
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        int k = 0;
        for (int requirement : g) {
            for (int j = k; j < s.length; j++) {
                k++;
                if (requirement <= s[j]) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    // 2. 不重叠的区间个数
    // 435. 无重叠区间
    // 算法思想为什么成立？？ 反证法，若B成立，则A也成立，则存在。。。。
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        // Arrays.sort(intervals, Comparator.comparing(ints -> ints[1])); // wocao ? niubi .
        Arrays.sort(intervals, new Comparator<int[]>() { // BEST P, 使用 lambda 表示式创建 Comparator 会导致算法运行时间过长，如果注重运行时间，可以修改为普通创建 Comparator 语句：
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] < o2[1]) ? -1 : ((o1[1] == o2[1]) ? 0 : 1); // 从小到大排序 o1-o2
                // 实现 compare() 函数时避免使用 return o1[1] - o2[1]; 这种减法操作，防止溢出。
            }
        });
        int lastEnd = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (start >= lastEnd) {
                ans++;
                lastEnd = end;
            }
        }
        return intervals.length - ans;
    }

    // 3. 投飞镖刺破气球
    // 452. 用最少数量的箭引爆气球
    // same as 2. just reverse the result.
    public int findMinArrowShots(int[][] intervals) {
        if (intervals.length == 0) return 0;
        // Arrays.sort(intervals, Comparator.comparing(ints -> ints[1])); // wocao ? niubi .
        Arrays.sort(intervals, new Comparator<int[]>() { // BEST P, 使用 lambda 表示式创建 Comparator 会导致算法运行时间过长，如果注重运行时间，可以修改为普通创建 Comparator 语句：
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] < o2[1]) ? -1 : ((o1[1] == o2[1]) ? 0 : 1); // 从小到大排序 o1-o2
                // 实现 compare() 函数时避免使用 return o1[1] - o2[1]; 这种减法操作，防止溢出。
            }
        });
        int lastEnd = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (start > lastEnd) {
                ans++;
                lastEnd = end;
            }
        }
        return ans;
    }

    // 4. 根据身高和序号重组队列
    // 406. 根据身高重建队列 Queue Reconstruction by Height
    public int[][] reconstructQueue(int[][] people) {

        // 前面两处排序错误，总结经验

        // Arrays.sort(people, new Comparator<>() { // 只按照序号排序，不对，应该按照序号升序+身高降序
        //     @Override
        //     public int compare(int[] o1, int[] o2) {
        //         return Integer.compare(o1[1], o2[1]); // k升序
        //     }
        // });

        // Arrays.sort(people, new Comparator<>() {
        //     @Override
        //     public int compare(int[] o1, int[] o2) {
        //         // return (o1[1] == o2[1]) ? Integer.compare(o2[0], o1[0]) : Integer.compare(o1[1], o2[1]) ; // k升序, h降序
        //         return (o1[1] == o2[1]) ? (o2[0] - o1[0]) : (o1[1] - o2[1]) ; // k升序, h降序， 也不对
        //         // 分辨方法 -1 0 1 升  1 0 -1 降
        //     }
        // });

        // 身高 h 降序、个数 k 值升序，然后将某个学生插入队列的第 k 个位置中。
        Arrays.sort(people, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // return (o1[1] == o2[1]) ? Integer.compare(o2[0], o1[0]) : Integer.compare(o1[1], o2[1]) ; // k升序, h降序
                return (o1[0] == o2[0]) ? (o1[1] - o2[1]) : (o2[0] - o1[0]); // h降序，k升序,  才对
                // 分辨方法 -1 0 1 升  1 0 -1 降
            }
        });

        // 按照身高排序之后，优先按身高高的people的k来插入，后序插入节点也不会影响前面已经插入的节点，最终按照k的规则完成了队列。
        //
        // 所以在按照身高从大到小排序后：
        //
        // 局部最优：优先按身高高的people的k来插入。插入操作过后的people满足队列属性
        //
        // 全局最优：最后都做完插入操作，整个队列满足题目队列属性
        //
        // 局部最优可推出全局最优，找不出反例，那就试试贪心。
        //
        // 一些同学可能也会疑惑，你怎么知道局部最优就可以推出全局最优呢？ 有数学证明么？
        //
        // 作者：carlsun-2
        // 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/406du-shuo-shi-tan-xin-na-yao-wei-shi-yao-yong-tan/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        for (int i = 0; i < people.length; i++) {
            int height = people[i][0];
            int frontN = people[i][1];
            System.out.println(height);
            System.out.println(frontN);
        }
        List<int[]> queue = new ArrayList<>();
        // for (int[] person : people) {
        //     queue.add(person[1], person);
        // }
        for (int i = 0; i < people.length; i++) {
            queue.add(people[i][1], people[i]);
        }
        return queue.toArray(new int[queue.size()][]);
    }

    // 5. 买卖股票最大的收益
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        // 只要记录前面的最小价格，将这个最小价格作为买入价格，然后将当前的价格作为售出价格，查看当前收益是不是最大收益。
        int min = prices[0];
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > ans) {
                ans = prices[i] - min;
            }
        }
        return ans;
    }

    // 6. 买卖股票的最大收益 II
    // 122. 买卖股票的最佳时机 II
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        // 对于 [a, b, c, d]，如果有 a <= b <= c <= d ，那么最大收益为 d - a。而 d - a = (d - c) + (c - b) + (b - a) ，
        // 因此当访问到一个 prices[i] 且 prices[i] - prices[i-1] > 0，那么就把 prices[i] - prices[i-1] 添加到收益中。
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += prices[i] - prices[i - 1];
            }
        }
        return ans;
    }

    // 7. 种植花朵
    // 605. 种花问题
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int max = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int pre = (i == 0) ? 0 : flowerbed[i - 1]; // 学习如何优雅获取前后结点而不越界。
            int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                max++;
                flowerbed[i] = 1;
            }
        }
        return max >= n;
    }

    // 8. 判断是否为子序列
    // 对后续挑战的一些思考--如何快速判断大量字符串//
    // 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10 亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
    // 这种类似对同一个长字符串做很多次匹配的 ，可以像 KMP 算法一样，先用一些时间将长字符串中的数据 提取出来，磨刀不误砍柴功。有了提取好的数据，就可以快速的进行匹配。
    // 作者：dongzengjie
    // 链接：https://leetcode-cn.com/problems/is-subsequence/solution/dui-hou-xu-tiao-zhan-de-yi-xie-si-kao-ru-he-kuai-s/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (t.length() == 0) {
            return false;
        }
        char[] sub = s.toCharArray();
        char[] total = t.toCharArray();

        int j = 0;
        for (int i = 0; i < total.length; i++) {
            if (j == sub.length) { //
                break;
            }
            if (sub[j] == total[i]) {
                j++;
            }
        }
        return j == sub.length;
    }

    // 9. 修改一个数成为非递减数组
    public boolean checkPossibility(int[] nums) {
        // 在出现 nums[i] < nums[i - 1] 时，需要考虑的是应该修改数组的哪个数，
        // 使得本次修改能使 i 之前的数组成为非递减数组，并且 不影响后续的操作 。
        // 优先考虑令 nums[i - 1] = nums[i]，因为如果修改 nums[i] = nums[i - 1] 的话，
        // 那么 nums[i] 这个数会变大，就有可能比 nums[i + 1] 大，从而影响了后续操作。
        // 还有一个比较特别的情况就是 nums[i] < nums[i - 2]，修改 nums[i - 1] = nums[i] 不能使数组成为非递减数组，
        // 只能修改 nums[i] = nums[i - 1]。

        // 给老子改！！！
        int n = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] < 0) {
                n++;
            }
        }
        return n >= 2;
    }

    // 10. 子数组最大的和
    // 11. 分隔字符串使同种字符出现在一起

    // =====================================================================================================================
    // Another days for coding 12/13 of 2020..
    // Not Complete but push in 1/4 of 2021 . Get boring in coding greedy algorithm ...


    // =====================================================================================================================
    // Another days for coding 1/5 of 2021..
    // Leetcode 题解 - 二分查找
    // 1. 求开方
    // 2. 大于给定元素的最小元素
    // 3. 有序数组的 Single Element
    // 4. 第一个错误的版本
    // 5. 旋转数组的最小数字
    // 6. 查找区间

    // 二分查找实现
    public int binarySearch(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] == key) {
                return m;
            } else if (nums[m] < key) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return -1;
    }

    // 例如在一个有重复元素的数组中查找 key 的最左位置的实现如下：
    public int binarySearch(int[] nums, int key) {
        int l = 0, h = nums.length; // ****
        while (l < h) { // *
            int m = l + (h - l) / 2;
            if (nums[m] >= key) { // *
                h = m;
            } else { // *
                l = m + 1;
            }
        }
        return l;
    }

    // 1. 求开方
    // 69. x 的平方根
    public int mySqrt(int x) {
        if (x == 1) return 1;
        int h = x / 2;
        int l = 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            int div = x / m; // * 防止溢出
            if (m == div) {
                return m;
            } else if (m < div) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return h;
    }

    // 2. 大于给定元素的最小元素
    // 744. Find Smallest Letter Greater Than Target (Easy)
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0;
        int h = letters.length - 1;
        while (l <= h) { // *
            int m = l + (h - l) / 2;
            if (letters[m] <= target) { // *
                l = m + 1;
            } else {
                h = m - 1; // *
            }
        }
        return (l == letters.length) ? letters[0] : letters[l];
    }

    // 3. 有序数组的 Single Element
    // 540. Single Element in a Sorted Array (Medium)
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            // 保证 l/h/m 都在偶数位，使得查找区间大小一直都是奇数
            if (m % 2 == 1) {
                m--;
            }
            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                h = m;
            }
        }
        return nums[l];
    }

    // 4. 第一个错误的版本
    // 278. First Bad Version (Easy)
    // 如果第 m 个版本出错，则表示第一个错误的版本在 [l, m] 之间，令 h = m；否则第一个错误的版本在 [m + 1, h] 之间，令 l = m + 1。
    // 因为 h 的赋值表达式为 h = m，因此循环条件为 l < h。
    public int firstBadVersion(int n) {
        int l = 1;
        int h = n;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (isBadVersion(m)) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    // 5. 旋转数组的最小数字
    // 153. Find Minimum in Rotated Sorted Array (Medium)
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] < nums[h]) { // key point
                h = m;
            } else {
                l = m + 1;
            }
        }
        return nums[l];
    }

    // 6. 查找区间
    // 34. Find First and Last Position of Element in Sorted Array

    public int[] searchRange(int[] nums, int target) {
        // 我们将寻找 target 最后一个位置，转换成寻找 target+1 第一个位置，再往前移动一个位置。这样我们只需要实现一个二分查找代码即可。
        int first = binarySearchL(nums, target);
        int last = binarySearchL(nums, target + 1) - 1;
        if (i == -1) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last)};
        }

        // stupid
        int i = binarySearchL(nums, target);
        if (i == -1) {
            return new int[]{-1, -1};
        } else {
            int j = i - 1;
            int l = i;
            int k = i + 1;
            int h = i;
            while (j >= 0) {
                if (nums[j] == target) {
                    l--;
                    j--;
                } else {
                    break;
                }
            }
            while (k < nums.length) {
                if (nums[k] == target) {
                    h++;
                    k++;
                } else {
                    break;
                }
            }
            return new int[]{l, h};
        }
    }

    public int binarySearch(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] == key) {
                return m;
            } else if (nums[m] > key) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        // 我们将寻找 target 最后一个位置，转换成寻找 target+1 第一个位置，再往前移动一个位置。这样我们只需要实现一个二分查找代码即可。
        int first = binarySearchL(nums, target);
        int last = binarySearchL(nums, target + 1) - 1;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last)};
        }
    }

    public int binarySearchL(int[] nums, int key) {
        int l = 0, h = nums.length; // 为什么这里要为h
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= key) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    // =====================================================================================================================
    // Another days for coding 1/5 ~ 1/28 of 2021..

    // =====================================================================================================================
    // Another days for coding 1/28 of 2021..

    // Leetcode 题解 - 分治
    //
    // Leetcode 题解 - 分治
    // 1. 给表达式加括号
    // 2. 不同的二叉搜索树
    //
    // 1. 给表达式加括号
    // 241. Different Ways to Add Parentheses (Medium)
    // 解题思路
    // 整体思路:分治+回溯；遍历整个字符串，一旦遇见符号，则根据符号位置将字符串分成左右两部分，左边进行，右边进行；
    // 递归函数的三要素：
    // 递归函数终止条件（即最后不再调用递归函数而结束本次函数）---本层要干什么---返回值是什么
    // 具体看注释：
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> waysToCompute = new ArrayList<>();
        for (int i = 0; i < input.toCharArray().length; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c =='*') {
                List<Integer> l = diffWaysToCompute(input.substring(0, i));
                List<Integer> r = diffWaysToCompute(input.substring(i+1));  // 省略i
                for (Integer integer : l) {
                    for (Integer integer1 : r) {
                        switch (c) {
                            case '+':
                                waysToCompute.add(integer+integer1);
                                break;
                            case '-':
                                waysToCompute.add(integer-integer1);
                                break;
                            case '*':
                                waysToCompute.add(integer*integer1);
                                break;
                        }
                    }
                }
            }
        }
        if (waysToCompute.size() == 0) {
            waysToCompute.add(Integer.valueOf(input));
        }
        return waysToCompute;
    }
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();//这里要注意，每一个调用递归函数都会有一个【属于】本次
        // 调用的res列表存放数据
        // 递归函数传参传进来的是上一层左半部分或者右半部分
        for(int i=0;i<input.length();i++){  //一层（一次函数调用中）需要将传入数字符串遍历完
            char temp = input.charAt(i);
            if(temp == '+' || temp == '-' || temp == '*'){
                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));
                for(int l:left){
                    for(int r:right){
                        if(temp == '-'){
                            res.add(l-r);//而一层中只有一个res用于存放数据
                        }else if(temp == '+'){
                            res.add(l+r);
                        }else if(temp == '*'){
                            res.add(l*r);
                        }
                    }
                }
            }
        }
        //这种情况是只有一个数字字符传入了递归函数，那么该次调用直接返回该数值的list集合
        if(res.size() == 0){
            res.add(Integer.valueOf(input));
            return res;
        }
        return res;
    }

    // 2. 不同的二叉搜索树
    // 95. Unique Binary Search Trees II (Medium)
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n == 0){
            return ans;
        }
        return getAns(1, n);
    }

    public List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (start > end) { // >
            ans.add(null);
            return ans;
        }
        if (start == end){ // ==
            ans.add(new TreeNode(start));
            return ans;
        }
        for (int i = start; i <= end; i++) { // every point as root // <
            List<TreeNode> left = getAns(start, i - 1); // start ~ i-1
            List<TreeNode> right = getAns(i + 1, end); // i+1 ~ end
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ans.add(root);
                }
            }
        }
        return ans;
    }


    // 解法二 递归
    // 解法一完全没有用到查找二叉树的性质，暴力尝试了所有可能从而造成了重复。我们可以利用一下查找二叉树的性质。左子树的所有值小于根节点，右子树的所有值大于根节点。
    // 所以如果求 1...n 的所有可能。
    // 我们只需要把 1 作为根节点，[ ] 空作为左子树，[ 2 ... n ] 的所有可能作为右子树。
    // 2 作为根节点，[ 1 ] 作为左子树，[ 3...n ] 的所有可能作为右子树。
    // 3 作为根节点，[ 1 2 ] 的所有可能作为左子树，[ 4 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
    // 4 作为根节点，[ 1 2 3 ] 的所有可能作为左子树，[ 5 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
    // n 作为根节点，[ 1... n ] 的所有可能作为左子树，[ ] 作为右子树。
    // 至于，[ 2 ... n ] 的所有可能以及 [ 4 ... n ] 以及其他情况的所有可能，可以利用上边的方法，把每个数字作为根节点，然后把所有可能的左子树和右子树组合起来即可。
    // 如果只有一个数字，那么所有可能就是一种情况，把该数字作为一棵树。而如果是 [ ]，那就返回 null。
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (n == 0) {
            return ans;
        }
        return getAns(1, n);
    }

    private List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        //此时没有数字，将 null 加入结果中
        if (start > end) {
            ans.add(null);
            return ans;
        }
        //只有一个数字，当前数字作为一棵树加入结果中
        if (start == end) {
            TreeNode tree = new TreeNode(start);
            ans.add(tree);
            return ans;
        }
        //尝试每个数字作为根节点
        for (int i = start; i <= end; i++) {
            //得到所有可能的左子树
            List<TreeNode> leftTrees = getAns(start, i - 1);
            //得到所有可能的右子树
            List<TreeNode> rightTrees = getAns(i + 1, end);
            //左子树右子树两两组合
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    //加入到最终结果中
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    // =====================================================================================================================
    // Another days for coding 1/28 of 2021..


    // =====================================================================================================================
    // Another days for coding 2/22 of 2021..
    // Leetcode 题解 - 搜索
    // BFS
    // 1. 计算在网格中从原点到特定点的最短路径长度
    // 2. 组成整数的最小平方数数量
    // 3. 最短单词路径
    // DFS
    // 1. 查找最大的连通面积
    // 2. 矩阵中的连通分量数目
    // 3. 好友关系的连通分量数目
    // 4. 填充封闭区域
    // 5. 能到达的太平洋和大西洋的区域
    // Backtracking
    // 1. 数字键盘组合
    // 2. IP 地址划分
    // 3. 在矩阵中寻找字符串
    // 4. 输出二叉树中所有从根到叶子的路径
    // 5. 排列
    // 6. 含有相同元素求排列
    // 7. 组合
    // 8. 组合求和
    // 9. 含有相同元素的组合求和
    // 10. 1-9 数字的组合求和
    // 11. 子集
    // 12. 含有相同元素求子集
    // 13. 分割字符串使得每个部分都是回文数
    // 14. 数独
    // 15. N 皇后
    // 深度优先搜索和广度优先搜索广泛运用于树和图中，但是它们的应用远远不止如此。

    // BFS
    // 在程序实现 BFS 时需要考虑以下问题：
    // 队列：用来存储每一轮遍历得到的节点；
    // 标记：对于遍历过的节点，应该将它标记，防止重复遍历。
    // 1. 计算在网格中从原点到特定点的最短路径长度
    // 1091. Shortest Path in Binary Matrix(Medium)
    public int shortestPathBinaryMatrix(int[][] grids) {
        if (grids == null || grids.length == 0 || grids[0].length == 0) {
            return -1;
        }
        int[][] direction = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int m = grids.length, n = grids[0].length;
        // key code data structure Queue<Pair<Integer, Integer>>
        // Use Queue FIFO, first find path is short path.
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        // Map can't getKey directly, just use Pair instead.
        // Queue<HashMap<Integer, Integer>> queue2 = new LinkedList<>();
        // 1.push into stack
        queue.add(new Pair<>(0, 0));
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;
            while (size-- > 0) {
                // 2.get from stack to check value
                Pair<Integer, Integer> cur = queue.poll();
                int cr = cur.getKey(), cc = cur.getValue();
                // check status 1 visit status
                if (grids[cr][cc] == 1) {
                    continue;
                }
                // check status 2 end status
                if (cr == m - 1 && cc == n - 1) {
                    return pathLength;
                }
                // 3.mark as visited
                grids[cr][cc] = 1;
                // 4.key code visit all 8 direction and put into stack
                for (int[] d : direction) {
                    int nr = cr + d[0], nc = cc + d[1];
                    // check status 3 out of grids
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    queue.add(new Pair<>(nr, nc));
                }
            }
        }
        return -1;
    }

    public int shortestPathBinaryMatrix(int[][] grids) {
        if (grids == null || grids.length == 0 || grids[0].length == 0) {
            return -1;
        }
        int[][] direction = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int m = grids.length, n = grids[0].length;
        // key code data structure Queue<Pair<Integer, Integer>>
        // Use Queue FIFO, first find path is short path.
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<Integer, Integer>(0,0));
        int pathLength = 0;
        while(!queue.isEmpty()) {
            pathLength++;
            int size = queue.size();
            while(size-- > 0) {
                Pair<Integer, Integer> cur = queue.poll();
                int cm = cur.getKey();
                int cn = cur.getValue();

                // this can be settle into direction for loop
                if (cm < 0 || cn < 0 || cm > m-1 || cn > n-1) {
                    continue;
                }
                if (grids[cm][cn] == 1) {
                    continue;
                }
                if (cm == m-1 && cn == n-1) {
                    return pathLength;
                }
                grids[cm][cn] = 1;
                int curPoint = grids[cm][cn];
                for (int[] d : direction) {
                    int nm = cm + d[0];
                    int nn = cn + d[1];
                    queue.add(new Pair<>(nm, nn));
                }
            }
        }
        return -1;
    }

    // 2. 组成整数的最小平方数数量
    // 279. Perfect Squares (Medium)
    //  key logic
    // 可以将每个整数看成图中的一个节点，如果两个整数之差为一个平方数，那么这两个整数所在的节点就有一条边。
    // 要求解最小的平方数数量，就是求解从节点 n 到节点 0 的最短路径。
    // 本题也可以用动态规划求解，在之后动态规划部分中会再次出现。
    public int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n + 1];
        queue.add(n);
        marked[n] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int s : squares) {
                    int next = cur - s;
                    if (next < 0) {
                        break;
                    }
                    if (next == 0) {
                        return level;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }
        return n;
    }

    /**
     * 生成小于 n 的平方数序列
     * @return 1,4,9,...
     */
    private List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            // square 1 4 9 16 25  36
            // diff    3 5 7  9  11
            // just math magic ...
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }


    public int numSquares(int n) {
        List<Integer> squares = generateSquares2(n);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int ans = 0;
        boolean[] marked = new boolean[n+1];
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (Integer square : squares) {
                    int nextN = cur - square;
                    if (nextN == 0) {
                        return ans;
                    }

                    if (nextN < 0) { // n - square This node has no edge to go..
                        break;
                    }
                    // N -> 0
                    // if N = 15 nextN is 6 2 1 ... and other edge or path is forbidden...
                    // 1 4 9 16 25 36
                    // nextN must not same ?? why ??
                    // if nextN = 1 1 1 1 1 1
                    // performance code here ..
                    if (marked[nextN]) { // has same edge, why continue ? solution for duplicated computation. reduce queue add times.
                        continue;
                    }
                    marked[nextN] = true;
                    queue.add(nextN);
                }
            }
        }
        return  -1;
    }

    private List<Integer> generateSquares2(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squares.add(square);
            square  += diff;
            diff += 2;
        }
        return squares;
    }

    // 3. 最短单词路径
    // 127. Word Ladder (Medium)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int N = wordList.size();
        int start = N - 1;
        int end = 0;
        while (end < N && !wordList.get(end).equals(endWord)) {
            end++;
        }
        if (end == N) {
            return 0;
        }
        List<Integer>[] graphic = buildGraphic(wordList);
        return getShortestPath(graphic, start, end);
    }

    private List<Integer>[] buildGraphic(List<String> wordList) {
        int N = wordList.size();
        List<Integer>[] graphic = new List[N];
        for (int i = 0; i < N; i++) {
            graphic[i] = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (isConnect(wordList.get(i), wordList.get(j))) {
                    graphic[i].add(j);
                }
            }
        }
        return graphic;
    }

    private boolean isConnect(String s1, String s2) {
        int diffCnt = 0;
        for (int i = 0; i < s1.length() && diffCnt <= 1; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }

    private int getShortestPath(List<Integer>[] graphic, int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[graphic.length];
        queue.add(start);
        marked[start] = true;
        int path = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            path++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int next : graphic[cur]) {
                    if (next == end) {
                        return path;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }
        return 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

    }


    // DFS
    // 1. 查找最大的连通面积
    // 695. Max Area of Island (Medium)
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int width;
    int height;
    public int maxAreaOfIsland(int[][] grid) {
        N = grid.length;
        height = grid[0].length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < height; j++) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int w, int h) {
        if (w < 0 || w >= N || h < 0 || h >= height || grid[w][h] == 0) {
            return 0;
        }
        grid[w][h] = 0;
        int area = 1;
        for (int[] d : directions) {
            area += dfs(grid, w + d[0], h + d[1]);
        }
        return area;
    }

    // 2. 矩阵中的连通分量数目
    // 200. Number of Islands (Medium)
    int width;
    int height;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int numIslands(char[][] grid) {
        // m == grid.length
        // n == grid[i].length
        // 1 <= m, n <= 300
        // grid[i][j] 的值为 '0' 或 '1'
        width = grid.length;
        height = grid[0].length;
        int ans = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ans += dfs(grid, i, j)!=0?1:0;
            }
        }
        return ans;
    }


    private int dfs(char[][] grid, int w, int h) {
        // This if to block the un-connected area. let others run by next for-for..
        if (w < 0 || w >= width || h < 0 || h >= height || grid[w][h] == '0') {
            return 0;
        }

        grid[w][h] = '0';
        int area = 1;
        for (int[] d : directions) {
            area += dfs(grid, w + d[0], h + d[1]);
        }
        return area;
    }

    // 3. 好友关系的连通分量数目
    // 547. Friend Circles (Medium)
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int N;
    boolean[] visited;
    public int findCircleNum(int[][] grid) {
        N = grid.length;
        visited = new boolean[N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            // Key Logic, to make visited array filled..
            dfs(grid, i);
            ans++;
        }
        return ans;
    }

    private void dfs(int[][] grid, int w) {
        visited[w] = true;
        for (int i = 0; i < N; i++) {
            if (grid[w][i]==1 && !visited[i]){
                dfs(grid, i);
            }
        }
    }

    // 4. 填充封闭区域
    // 130. Surrounded Regions (Medium)

    int width;
    int height;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // KEY Solution 先填充外围，再填充内部。
    public void solve(char[][] grid) {
        width = grid.length;
        height = grid[0].length;

        for (int i = 0; i < width; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, height-1);
        }
        for (int i = 0; i < height; i++) {
            dfs(grid, 0, i);
            dfs(grid, width-1, i);
        }
        // int ans = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] == 'O') {
                    grid[i][j] = 'X';
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] == 'T') {
                    grid[i][j] = 'O';
                }
            }
        }
        // return ans;
    }

    private void dfs(char[][] grid, int w, int h) {
        // This if to block the un-connected area. let others run by next for-for..
        if (w < 0 || w >= width || h < 0 || h >= height || grid[w][h] == 'X' || grid[w][h] == 'T') {
            return;
        }
        grid[w][h] = 'T';
        // int area = 1;
        for (int[] d : directions) {
            // area += dfs(grid, w + d[0], h + d[1]);
            dfs(grid, w + d[0], h + d[1]);
        }
        // return area;
    }



    // 5. 能到达的太平洋和大西洋的区域
    // 417. Pacific Atlantic Water Flow (Medium)
    int m, n;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();

        m = matrix.length;
        n = matrix[0].length;
        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachA = new boolean[m][n];


        for (int i = 0; i < m; i++) {
            dfs(matrix, i, 0, canReachA);
            dfs(matrix, i, n-1, canReachP);
        }
        for (int i = 0; i < n; i++) {
            dfs(matrix, 0, i, canReachA);
            dfs(matrix, m-1, i, canReachP);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachA[i][j] && canReachP[i][j]) {
                    List<Integer> s = new ArrayList<>();
                    s.add(i);
                    s.add(j);
                    ans.add(s);
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int a, int b, boolean[][] canReach) {
        if (canReach[a][b])  return;
        canReach[a][b] = true;
        // grid[a][b] = 0;
        // int area = 1;
        for (int[] d : directions) {
            // area += dfs(grid, a + d[0], b + d[1]);
            int a1 = a + d[0];
            int b1 = b + d[1];
            if (!(a1 < 0 || a1 >= m || b1 < 0 || b1 >= n || grid[a1][b1] < grid[a][b])) {
                dfs(grid, a1, b1, canReach);
            }
        }
        // return area;
    }


    // Backtracking
    // Backtracking（回溯）属于 DFS。
    // Backtracking（回溯）属于 DFS。
    // 普通 DFS 主要用在 可达性问题 ，这种问题只需要执行到特点的位置然后返回即可。
    // 而 Backtracking 主要用于求解 排列组合 问题，例如有 { 'a','b','c' } 三个字符，
    // 求解所有由这三个字符排列得到的字符串，这种问题在执行到特定的位置返回之后还会继续执行求解过程。

    // 1. 数字键盘组合
    // 17. Letter Combinations of a Phone Number (Medium)
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> ans = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        char[] charArray = digits.toCharArray();
        // for (int i = 0; i < charArray.length; i++) {
        //     StringBuilder sb = new StringBuilder();
        //     ans.add(backTracking(charArray, i, sb));
        // }
        backTracking(charArray, new StringBuilder());
        return ans;
    }

    public void backTracking(char[] c, StringBuilder sb) {
        if (sb.length() == c.length) {
            ans.add(sb.toString());
            return;
        }
        int intValue = c[sb.length()] - '0';
        String letters = KEYS[intValue];
        char[] charArray = letters.toCharArray();
        for (char ca : charArray) {
            sb.append(ca);
            backTracking(c, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    // add new restart ~~~~
    // try to loving coding.
    String[] KEY_LIST = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        ArrayList<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return ans;
        }

        backTracking(digits.toCharArray(), ans, new StringBuilder());

        return ans;
    }

    public void backTracking(char[] chars, List<String> ans, StringBuilder stringBuilder) {
        if (stringBuilder.length() == chars.length) {
            ans.add(stringBuilder.toString());
            return;
        }

        String s = KEY_LIST[chars[stringBuilder.length()] - '0'];
        char[] chars1 = s.toCharArray();
        for (int j = 0; j < chars1.length; j++) {
            // in
            stringBuilder.append(chars1[j]);
            backTracking(chars, ans, stringBuilder);
            // out
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }


    // 2. IP 地址划分
    // 93. Restore IP Addresses(Medium)
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> ans = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return ans;
        }
        backTracking(s, ans, new StringBuilder(), 0);
        return ans;
    }

    // FIXME Use Substirng as parameter.
    public void backTracking(String s, List<String> ans, StringBuilder stringBuilder, int k) {
        if (k == 4 || s.isEmpty()) {
            if (k == 4 && s.isEmpty()) {
                ans.add(stringBuilder.toString());
            }
            return;
        }

        int length = s.length();
        for (int i = 1; i <= 3 || i <= length; i++) {
            if (s.charAt(0) == '0' && i != 1) {
                break;
            }
            StringBuilder subAddr = new StringBuilder(s.substring(0, i));
            if (Integer.parseInt(subAddr.toString()) > 255) {
                break;
            }
            if (k != 3) {
                subAddr.append(".");
            }

            stringBuilder.append(subAddr);
            backTracking(s.substring(i), ans, stringBuilder, k+1);
            stringBuilder.delete(stringBuilder.length() - subAddr.length(), stringBuilder.length());
        }
    }

    // 3. 在矩阵中寻找字符串
    // 79. Word Search (Medium)
    int[][] Direactionn = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int height;
    int width;
    boolean[][] visited;
    // Code with over time ...
    public boolean exist(char[][] board, String word) {
        height = board.length;
        width = board[0].length;
        visited = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (backTracking(board, i, j, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    // key point int[][] first parameter is height.
    private boolean backTracking(char[][] board, int i, int j, String word) {
        if (word.length() == 0) {
            return true;
        }
        if (i < 0 || j < 0 || i >= height || j >= width
                || board[i][j] != word.charAt(0)
                || visited[i][j]) {
            return false;
        }
        char c = board[i][j];
//        System.out.println(c); // cause over time
        visited[i][j] = true;
        for (int[] direction : Direactionn) {
            if (backTracking(board, i + direction[0], j + direction[1], word.substring(1))) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    // AC Code
    private final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int m;
    private int n;
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        m = board.length;
        n = board[0].length;
        boolean[][] hasVisited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (backtracking(0, r, c, hasVisited, board, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtracking(int curLen, int r, int c, boolean[][] visited, final char[][] board, final String word) {
        if (curLen == word.length()) {
            return true;
        }
        if (r < 0 || r >= m || c < 0 || c >= n
                || board[r][c] != word.charAt(curLen) || visited[r][c]) {

            return false;
        }

        visited[r][c] = true;

        for (int[] d : direction) {
            if (backtracking(curLen + 1, r + d[0], c + d[1], visited, board, word)) {
                return true;
            }
        }

        visited[r][c] = false;

        return false;
    }


    // 4. 输出二叉树中所有从根到叶子的路径
    // 257. Binary Tree Paths (Easy)

    // DFS Logic
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        DFS(ans, root, new StringBuilder());
        return ans;
    }
    private void DFS(List<String> ans, TreeNode pop, StringBuilder stringBuilder) {

        StringBuilder tmpPath = new StringBuilder();
        if (pop.left == null && pop.right == null) {
            tmpPath.append(pop.val);
            stringBuilder.append(tmpPath.toString());
            ans.add(stringBuilder.toString());
        } else {
            tmpPath.append(pop.val);
            tmpPath.append("->");
            stringBuilder.append(tmpPath.toString());
        }

        if (pop.left != null) {
            DFS(ans, pop.left, new StringBuilder(stringBuilder.toString()));
        }
        if (pop.right != null) {
            DFS(ans, pop.right, new StringBuilder(stringBuilder.toString()));
        }
    }

    // Backtracking Logic
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        DFS(ans, root, new StringBuilder());
        return ans;
    }
    private void DFS(List<String> ans, TreeNode pop, StringBuilder stringBuilder) {

        StringBuilder tmpPath = new StringBuilder();
        // in
        if (pop.left == null && pop.right == null) {
            tmpPath.append(pop.val);
            stringBuilder.append(tmpPath.toString());
            ans.add(stringBuilder.toString());
        } else {
            tmpPath.append(pop.val);
            tmpPath.append("->");
            stringBuilder.append(tmpPath.toString());
        }

        if (pop.left != null) {
            DFS(ans, pop.left, stringBuilder);
        }
        if (pop.right != null) {
            DFS(ans, pop.right, stringBuilder);
        }
        // out
        // key point, can use List<Integer> node to replace StringBuilder..
        stringBuilder.delete(stringBuilder.length()-tmpPath.length(), stringBuilder.length());
    }

    // 5. 排列
    // 46. Permutations (Medium)
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] booleans = new boolean[nums.length];
        backTracking(nums, new ArrayList<Integer>(), booleans);
        return ans;
    }

    private void backTracking(int[] nums, ArrayList<Integer> integers, boolean[] booleans) {
        if (integers.size() == nums.length) {
            // ans.add(integers); this code will cause return null, because integers will remove to nothing
            ans.add(new ArrayList<>(integers)); // key point, return new ...
            return;
        }
        for (int k = 0; k < nums.length; k++) { // key point , backtracking in loop
            if (booleans[k]) {
                continue;
            }

            // in
            integers.add(nums[k]);
            booleans[k] = true;

            backTracking(nums, integers, booleans);

            // out
            integers.remove(integers.size()-1);
            booleans[k] = false;
        }
    }

    // 6. 含有相同元素求排列
    // 47. Permutations II (Medium)

    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); // key point, sort 1st
        backTracking(nums, new ArrayList<>(), new boolean[nums.length]);
        return ans;
    }

    private void backTracking(int[] nums, ArrayList<Integer> integers, boolean[] booleans) {
        if (integers.size() == nums.length) {
            // ans.add(integers); this code will cause return null
            ans.add(new ArrayList<>(integers)); // key point, return new ...
            return;
        }
        for (int k = 0; k < nums.length; k++) { // key point , backtracking in loop
            if (k != 0 && (nums[k-1] == nums[k]) && booleans[k-1]) { // key point, check unique case.
                continue;
            }
            if (booleans[k]) {
                continue;
            }

            // in
            integers.add(nums[k]);
            booleans[k] = true;

            backTracking(nums, integers, booleans);

            // out
            integers.remove(integers.size()-1);
            booleans[k] = false;
        }
    }

    // 7. 组合
    // 77. Combinations (Medium)
    // 全排列
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k, new boolean[n+1], new ArrayList<Integer>());
        return ans;
    }

    private void backTracking(int n, int k, boolean[] booleans, List<Integer> list) {
        if (k==0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        // 未剪枝 全排列
        for (int i = 1; i <= n; i++) {
            if (booleans[i]) {
                continue;
            }
            list.add(i);
            booleans[i] = true;
            k--;
            backTracking(n, k, booleans, list);
            list.remove(list.size()-1);
            booleans[i] = false;
            k++;
        }
    }

    // AC
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k, new boolean[n+1], new ArrayList<Integer>(), 1);
        return ans;
    }

    private void backTracking(int n, int k, boolean[] booleans, List<Integer> list, int start) {
        // 优化 1，不用k--，这样还要额外对k进行回溯
        if (k==0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        // 剪枝 此方法需要原来的是有序的
        // 优化 2，不用visited，按顺序走，因为不需要全排列
        for (int i = start; i <= n; i++) {
            if (booleans[i]) {
                continue;
            }
            list.add(i);
            booleans[i] = true;
            k--;
            backTracking(n, k, booleans, list, i);
            list.remove(list.size()-1);
            booleans[i] = false;
            k++;
        }
    }

    // Best AC
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combineList = new ArrayList<>();
        backtracking(combineList, combinations, 1, k, n);
        return combinations;
    }

    private void backtracking(List<Integer> combineList, List<List<Integer>> combinations, int start, int k, final int n) {
        if (k == 0) {
            combinations.add(new ArrayList<>(combineList));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {  // 剪枝 // key point why?
            combineList.add(i);
            backtracking(combineList, combinations, i + 1, k - 1, n);
            combineList.remove(combineList.size() - 1);
        }
    }

    // 优化 版本 1 本质就是DFS
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k, new boolean[n+1], new ArrayList<Integer>(), 1);
        return ans;
    }

    private void backTracking(int n, int k, boolean[] booleans, List<Integer> list, int start) {
        // if (k==0) {
        //     ans.add(new ArrayList<>(list));
        //     return;
        // }
        if (k==list.size()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            // if (booleans[i]) {
            //     continue;
            // }
            list.add(i);
            // booleans[i] = true;
            // k--;
            // backTracking(n, k, booleans, list, i);
            backTracking(n, k, booleans, list, i+1);
            list.remove(list.size()-1);
            // booleans[i] = false;
            // k++;
        }
    }
    // 优化 版本 2 在DFS基础上 进行剪枝 容易理解
    // 剪枝就是因为从start 到 n 有些区间实际上不行到
    // k 也需要同时改变 若不加入回溯 则不可以改变原值 仅修改入参数
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTracking(n, k, new boolean[n+1], new ArrayList<Integer>(), 1);
        return ans;
    }

    private void backTracking(int n, int k, boolean[] booleans, List<Integer> list, int start) {
        if (k==0) { // k need change
            ans.add(new ArrayList<>(list));
            return;
        }
        // if (k==list.size()) {
        //     ans.add(new ArrayList<>(list));
        //     return;
        // }
        // 剪枝就是因为从start 到 n 有些区间实际上不行到
        for (int i = start; i <= n - k + 1; i++) {
            // if (booleans[i]) {
            //     continue;
            // }
            list.add(i);
            // booleans[i] = true;
            k--;
            // backTracking(n, k-1, booleans, list, i); use k-1 instead also ac.
            backTracking(n, k, booleans, list, i+1);
            list.remove(list.size()-1);
            // booleans[i] = false;
            k++;
        }
    }

    // 8. 组合求和
    // 39. Combination Sum (Medium)
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTracking(new ArrayList<Integer>(), candidates, target);
        return ans;
    }

    private void backTracking(List<Integer> list, int[] candidates, int target) {
        if (target == 0){
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < candidates.length; i++) {
            int chose = candidates[i];
            if (chose > target) {
                continue;
            }
            // 优化点 这里增加start 不允许 重复分叉
            if (list.size() > 0 && chose < list.get(list.size()-1)) {
                continue;
            }
            list.add(chose);
            backTracking(list, candidates, target-chose);
            list.remove(list.size() - 1);
        }
    }


    // 优化 不允许重复分叉 进行剪枝
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTracking(new ArrayList<Integer>(), candidates, target, 0);
        return ans;
    }

    private void backTracking(List<Integer> list, int[] candidates, int target, int start) {
        if (target == 0){
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int chose = candidates[i];
            if (chose > target) {
                continue;
            }
            list.add(chose);
            backTracking(list, candidates, target-chose, i);
            list.remove(list.size() - 1);
        }
    }

    // 9. 含有相同元素的组合求和
    // 40. Combination Sum II (Medium)
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracking(new ArrayList<Integer>(), candidates, target, 0, new boolean[candidates.length]);
        return ans;
    }

    private void backTracking(List<Integer> list, int[] candidates, int target, int start, boolean[] visited) {
        if (target == 0){
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (visited[i]) { // 可以省略 因为有 start
                continue;
            }
            int chose = candidates[i];
            if (i != 0 && (chose == candidates[i-1]) && !visited[i-1]) { // key point 这里需要的还真是 !visited 首先排序 其次前者肯定dfs过 所以要屏蔽
                continue;
            }
            if (chose > target) {
                continue;
            }
            list.add(chose);
            visited[i] = true;
            backTracking(list, candidates, target-chose, i+1, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    // 10. 1-9 数字的组合求和
    // 216. Combination Sum III (Medium)
    //找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        backTracking(new ArrayList<Integer>(), candidates, n, 0, k);
        return ans;
    }
    private void backTracking(List<Integer> list, int[] candidates, int target, int start, int k) {
        if (k == 0) {
            if (target == 0){
                ans.add(new ArrayList<>(list));
                return;
            }
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int chose = candidates[i];
            if (chose > target) {
                continue;
            }
            list.add(chose);
            backTracking(list, candidates, target-chose, i+1, k-1);
            list.remove(list.size() - 1);
        }
    }

    // 11. 子集
    // 78. Subsets (Medium)
    //输入：nums = [1,2,3]
    //输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    public List<List<Integer>> subsets(int[] nums) {

    }

    // 12. 含有相同元素求子集
    // 90. Subsets II (Medium)

    // 13. 分割字符串使得每个部分都是回文数
    // 131. Palindrome Partitioning (Medium)

    // 14. 数独
    // 37. Sudoku Solver (Hard)

    // 15. N 皇后
    // 51. N-Queens (Hard)



}