import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/27 8:58 上午
 * @description：牛客网刷题
 * @modifiedBy：
 * @version:
 */
public class Main {

    /**
     * for nowcoder 牛客网
     */
    public static void main(String[] args) {
        // CityBuilding();
        // Enclosure();
        // CountFlow();
        // LongestCommonSubsequence();
        // OverDuePay();
        // MaxSt();

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }

        Test1(); // 给定n，计算1～n的bitCount个数，累加的
        Test2(); // 给定数组，给出正负间隔的形式，一直有数组越界错误？
        Test3(); // 给定计算公式 3+5/2-2*5,求出结果。
    }

    private static void Test2() {
        // read all lines
        List<String> lines = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // write codes here
        int n = Integer.valueOf(lines.get(0));
        if (n < 1){
            return;
        }
        int[] input = new int[n];
        int flag = -1;
        for (int i = 0; i < n; i++) {
            String s = lines.get(i + 1);
            Integer value = Integer.valueOf(s);
            if (flag == -1 && value < 0) {
                flag = i;
            }
            input[i] = value;
        }
        if (flag == -1 || flag == 0){
            for (int i = 0; i < input.length; i++) {
                System.out.print(input[i] + " ");
            }
            return;
        }
        int[] positives = Arrays.copyOf(input, flag);
        int[] negatives = Arrays.copyOfRange(input, flag, n);

        int pi = 0;
        int ni = 0;
        for (int i = 0; i < n; i++) {
            if (ni < negatives.length && pi < positives.length) {
                if (i % 2 == 0) {
                    input[i] = positives[pi++];
                } else {
                    input[i] = negatives[ni++];
                }
            } else {
                if (negatives.length > positives.length) {
                    input[i] = negatives[ni++];
                } else {
                    input[i] = positives[pi++];
                }
            }

        }

        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + " ");
        }
    }

    private static void Test1() {
        // read all lines
        List<String> lines = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // write codes here
        int testCaseNum = Integer.valueOf(lines.get(0));
        int max = 0;
        for (int i = 1; i <= testCaseNum; i++) {
            String s = lines.get(i);
            Integer value = Integer.valueOf(s);
            if (value > max) {
                max = value;
            }
        }

        int[] dp = new int[max + 1];
        CountNumOne(max, dp);
        for (int i = 1; i <= testCaseNum; i++) {
            String s = lines.get(i);
            Integer value = Integer.valueOf(s);
            System.out.println(dp[value]);
        }
    }

    private static void CountNumOne(int n, int[] dp) {
        if (n < 1) {
            return;
        }
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int ans = 0;
            int tempi = i;
            while (i > 0) {
                if ((i & 1) == 1) {
                    ans++;
                }
                i = i >> 1;
            }
            dp[tempi] = dp[tempi - 1] + ans;
        }
    }

    /**
     * 城市修建
     * 有一个城市需要修建，给你N个民居的坐标X,Y，
     * 问把这么多民居全都包进城市的话，城市所需最小面积是多少（注意，城市为平行于坐标轴的正方形）
     */
    private static void CityBuilding() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Long xMax = Long.MIN_VALUE;
        Long yMax = Long.MIN_VALUE;
        Long xMin = Long.MAX_VALUE;
        Long yMin = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            Long x = in.nextLong();
            xMax = xMax > x ? xMax : x;
            xMin = xMin < x ? xMin : x;
            Long y = in.nextLong();
            yMax = yMax > y ? yMax : y;
            yMin = yMin < y ? yMin : y;
        }

        Long x = xMax - xMin;
        Long y = yMax - yMin;

        if (x.compareTo(y) == 1) {
            System.out.println(x * x);
        } else {
            System.out.println(y * y);
        }
    }

    /**
     * 圈地运动链接：
     * 圈地运动，就是用很多木棍摆在地上组成一个面积大于0的多边形～
     * 小明喜欢圈地运动，于是他需要去小红店里面买一些木棍，期望圈出一块地来。
     * 小红想挑战一下小明，所以给小明设置了一些障碍。障碍分别是：
     * 1.如果小明要买第i块木棍的话，他就必须把前i-1块木棍都买下来。
     * 2.买了的木棍都必须用在圈地运动中。
     * 那么请问小明最少买多少根木棍，才能使得木棍围成的图形是个面积大于0多边形呢？
     * 输入描述:
     * 第一行一个数n，表示木棍个数。
     * 第二行n个数，第i个数表示第i个木棍的长度ai
     * 1<=n<=10000
     * 1<=ai<=10000
     * 输出描述:
     * 输出一个数，表示最少需要的木棍个数，如果无解输出-1
     * 示例1
     * 输入
     * 3
     * 6 8 10
     */
    private static void Enclosure() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n < 3) {
            System.out.println(-1);
        }
        Double max = 0D;
        Double sum = 0D;

        for (int i = 0; i < n; i++) {
            Double x = in.nextDouble();
            max = max > x ? max : x;
            sum += x;
            if (sum - max > max) {
                System.out.println(i + 1);
                break;
            }
        }
        if (sum - max <= max) {
            System.out.println(-1);
        }
    }

    /**
     * 现在有q个询问，每次询问想问你在[l,r]区间内，k进制表示中，k-1的数量最多的数是哪个数。比如当k=2时，9的二进制就是1001，那么他就有2个1.
     * 输入描述:
     * 第一行一个q，表示有q组询问。
     * 接下来q行，每行三个整数k,l,r,分别表示进制数,查询的数字,以及查询的范围。
     * 满足1<=q<=100000,2<=k<=16,1<=l<=r<=10^16
     * 输出描述:
     * 对于每组询问，输出答案。如果有多个答案，请输出最小的。
     * 示例1
     * 输入
     * 1
     * 8 1 100
     * 输出
     * 63
     */
    private static void Bittttttts() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {

        }

    }

    /**
     * 小明有一个花园，花园里面一共有m朵花，对于每一朵花，都是不一样的，小明用1～m中的一个整数表示每一朵花。
     * 他很喜欢去看这些花，有一天他看了n次，并将n次他看花的种类是什么按照时间顺序记录下来。
     * 记录用a[i]表示，表示第i次他看了a[i]这朵花。
     * 小红很好奇，她有Q个问题,问[l,r]的时间内，小明一共看了多少朵不同的花儿，小明因为在忙着欣赏他的花儿，所以想请你帮他回答这些问题。
     * 输入描述:
     * 输入两个数n,m;(1<=n<=2000,1<=m<=100);分别表示n次看花，m表示一共有m朵花儿。
     * 接下来输入n个数a[1]~a[n]，a[i]表示第i次，小明看的花的种类;
     * 输入一个数Q(1<=Q<=1000000);表示小红的问题数量。
     * 输入Q行 每行两个数l,r(1<=l<=r<=n);表示小红想知道在第l次到第r次，小明一共看了多少不同的花儿。
     * 输出描述:
     * 一共Q行
     * 每一行输出一个数 表示小明在[l,r]的时间内看了多少种花。
     * 示例1
     * 输入
     * 5 3
     * 1 2 3 2 2
     * 3
     * 1 4
     * 2 4
     * 1 5
     * 输出
     * 3
     * 2
     * 3
     */
    private static void CountFlow() {
        Scanner in = new Scanner(System.in);
        // 1~n看花的次数
        int n = in.nextInt();
        // 1~m 花的种类
        int m = in.nextInt();
        int[] aN = new int[n];
        for (int i = 0; i < n; i++) {
            aN[i] = in.nextInt();
        }
        // dp[i][j]记录从i到j次观看的花种类
        int[][] dp = new int[n][n];
        // 边界
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 状态转移方程
        // dp[i][j] = dp[i][j-1]+ 0/1
        for (int i = 0; i < n; i++) {
            Set<Integer> count = new TreeSet<>();
            count.add(aN[i]);
            for (int j = i + 1; j < n; j++) {
                count.add(aN[j]);
                dp[i][j] = count.size();
            }
            count.clear();
        }

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            System.out.println(dp[in.nextInt() - 1][in.nextInt() - 1]);
        }
    }


    /**
     * Array
     * 小红有两个长度为n的排列A和B。每个排列由[1,n]数组成，且里面的数字都是不同的。
     * 现在要找到一个新的序列C，要求这个新序列中任意两个位置(i,j)满足:
     * 如果在A数组中C[i]这个数在C[j]的后面，那么在B数组中需要C[i]这个数在C[j]的前面。
     * 请问C序列的长度最长为多少呢？
     * 输入描述:
     * 第一行一个整数，表示N。
     * 第二行N个整数，表示A序列。
     * 第三行N个整数，表示B序列。
     * 满足:N<=50000
     * 输出描述:
     * 输出最大的长度
     * 示例1
     * 输入
     * 5
     * 1 2 4 3 5
     * 5 2 3 4 1
     * 输出
     * 4
     * 说明
     * 最长的C为1,3,4,5
     */
    private static void LongestCommonSubsequence() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a_n = new int[n];
        for (int i = 0; i < n; i++) {
            a_n[i] = in.nextInt();
        }
        // dp[i][j]记录从i到j次观看的花种类
        int[][] dp = new int[n][n];
        // 边界
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 状态转移方程
        // dp[i][j] = dp[i][j-1]+ 0/1
        for (int i = 0; i < n; i++) {
            Set<Integer> count = new TreeSet<>();
            count.add(a_n[i]);
            for (int j = i + 1; j < n; j++) {
                count.add(a_n[j]);
                dp[i][j] = count.size();
            }
            count.clear();
        }

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            System.out.println(dp[in.nextInt() - 1][in.nextInt() - 1]);
        }
    }

    /**
     * 加班奖励，连续加班奖励，求N天加班期望。
     * 输入N天，加班情况为1 2 1 0 1 2，0不加班 1加班 2 50%的概率加班，连续加n天班则当天的奖励为n
     * 求这N天的加班奖励期望。
     * <p>
     * 例子
     * 输入
     * 3
     * 1 2 1
     * 输出
     * 4 =（1+2+3 + 1+0+1 ）/2
     */
    private static void OverDuePay() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] an = new int[n];
        for (int i = 0; i < n; i++) {
            an[i] = in.nextInt();
        }
        // dp[i][j]记录从i到j次观看的花种类
        int[][] dp = new int[n][n];
        int accumulate = 0;
        // 边界
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 0;
            }
        }
        // 状态转移方程
        // dp[i][j] = dp[i-1][j]+ 0/ accumulate / accumulate*0.5
        // 动态规划的话应该就是遇到2累加结果翻倍，遇到1累加连续天数，遇到0初始加班天数把吧

    }


    private static void MaxSt() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 输入为一个数n,表示题面中的n=(R-2)*(C-1)*(L-2)
        // 输出为一个数，表示最坏情况下被偷了多少的货物
        // n=4,样例解释：R=3,C=5,L=3,3*5*3-(3-2)*(5-1)*(3-2)=41

        int R = 3;
        int C = 2;
        int L = 3;

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int target = (R - 2) * (C - 1) * (L - 2);
            if (target == n) {
                System.out.println(R * C * L - target);
                break;
            }
            if (target > n) {
                C++;
            }
            if (target < n) {
                C--;
                L++;
                R = L;
            }
            if (L >= C) {
                break;
            }
        }
    }

    /**
     * for nowcoder 牛客网
     */

    public static int findDuplicate(int[] array) {
        // 1
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], 1);
            if (map.containsKey(array[i])) {
                return array[i];
            }
        }
        return -1;
    }
}
//
// // 爬楼梯
// // 题目描述
// // 在你面前有一个n阶的楼梯，你一步只能上1阶或2阶。
// // 请问计算出你可以采用多少种不同的方式爬完这个楼梯。
// // 输入描述:
// // 一个正整数n(n<=100)，表示这个楼梯一共有多少阶
// // 输出描述:
// // 一个正整数，表示有多少种不同的方式爬完这个楼梯
// import java.util.*;
// import java.math.BigInteger;
//
// public class Main {
//     public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         int n = in.nextInt();
//
//         BigInteger bigInteger = JumpFloor(n);
//         System.out.println(bigInteger.toString());
//     }
//
//     /**
//      * 如果不使用java.math.BigInteger无法处理溢出问题。
//      * @param n
//      * @return
//      */
//     public static BigInteger JumpFloor(int n) {
//         if (n < 1) {
//             return BigInteger.valueOf(0);
//         }
//         if (n == 1 || n == 2) {
//             return BigInteger.valueOf(n);
//         }
//         BigInteger f0 = BigInteger.valueOf(1);
//         BigInteger f1 = BigInteger.valueOf(1);
//         BigInteger f2 = BigInteger.valueOf(2);
//         for (int i = 2; i <= n; i++) {
//             f2 = f0.add(f1);
//             // f2 = f0 + f1;
//             f0 = f1;
//             f1 = f2;
//         }
//         return f2;
//     }
//
// }
