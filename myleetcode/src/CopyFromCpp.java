import javax.swing.tree.TreeNode;

/**
 * @author ：xbb
 * @date ：Created in 2020/6/20 3:14 下午
 * @description：从C++迁移过来的代码
 * @modifiedBy：
 * @version:
 */
public class CopyFromCpp {

    /**
     * Definition for singly-linked list.
     * struct ListNode {
     *     int val;
     *     ListNode *next;
     *     ListNode(int x) : val(x), next(NULL) {}
     * };
     */
    struct ListNode {
        int val;
        ListNode *next;

        ListNode(int x) : val(x), next(NULL) {}
    };


    /**
     * Definition for a binary tree node.
     * struct TreeNode {
     *     int val;
     *     TreeNode *left;
     *     TreeNode *right;
     *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
     * };
     */

    struct TreeNode {
        int val;
        TreeNode *left;
        TreeNode *right;

        TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    };


    void combination();

    void recursion(vector<int> &nums, int k, int pos);

    vector<vector<int> > combine(int n, int k);

    vector<vector<int> > ans;

    vector<int> twoSum(vector<int> &nums, int target);  // leetcode 1. 两数之和
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2); // leetcode 2. 两数相加
    int lengthOfLongestSubstring(string s); // leetcode 3. 无重复字符最长子串
    double findMedianSortedArrays(vector<int> &nums1, vector<int> &nums2); // leetcode 4. 寻找两个有序数组的中位数
    vector<int> distributeCandies(int candies, int num_people); // leetcode 1103. 分糖果
    int coinChange(vector<int> &coins, int amount); // leetcode 322. 零钱兑换


    //回文
    void palindrome(); // leetcode 5. 最长回文子串
    int longestPalindrome(string s); // leetcode 409. 最长回文串
    int lengthOfLIS(vector<int> &nums); // leetcode 300. 最长上升子序列
    bool increasingTriplet(vector<int> &nums); // leetcode 334. 递增的三元子序列
    // 股票问题-动态规划
    // 这个问题的「状态」有三个，
    // 第⼀个是天数，
    // 第⼆个是允许交易的最⼤次数，
    // 第三个是当前的持有状态（即之前说的 rest 的状态，我们不妨⽤ 1 表⽰持有，0 表⽰没有持有）。
    // 然后我们⽤⼀个 三维数组就可以装下这⼏种状态的全部组合：

    int maxProfit1(vector<int> &pfindNumberOfLISrices); // leetcode 121. 买卖股票的最佳时机 I
    int maxProfit2(vector<int> &prices); // leetcode 122. 买卖股票的最佳时机 II
    int maxProfit3(vector<int> &prices); // leetcode 123. 买卖股票的最佳时机 III
    int maxProfit4(int k, vector<int> &prices); // leetcode 188. 买卖股票的最佳时机 IV           k次买，k>n/2就无限
    int maxProfit5(vector<int> &prices); // leetcode 309. 最佳买卖股票时机含冷冻期               无限买，冻结期
    int maxProfit6(vector<int> &prices, int fee); // leetcode 714. 买卖股票的最佳时机含手续费    无限买，有fee

    int wiggleMaxLength(vector<int> &nums); // leetcode 376. 摆动序列   动态规划+改造问题
    vector<int> movesToStamp(string stamp, string target); // leetcode 936. 戳印序列
    int lengthOfLIS(vector<int> &nums); // leetcode 300. 最长上升子序列
    int numDistinct(string s, string t); // leetcode 115. 不同的子序列
    int distinctSubseqII(string S); // leetcode 940. 不同的子序列 II
    int threeSumMulti(vector<int>& A, int target); // leetcode 923. 三数之和的多种可能

    bool isSubsequence(string s, string t); // leetcode 392. 判断子序列


    //DSF BSF遍历
    vector<vector<int>> findSubsequences(vector<int> &nums); // leetcode 491. 递增子序列

    // 二叉树
    int diameterOfBinaryTree(javax.swing.tree.TreeNode *root); // leetcode 543. 二叉树的直径

    vector<int> dp_fibonacci(255, -1);

    int fibonacci(int n);

    int fibonacci2(int n);

    int dp_tower_path[256][256] = {0}; //d[i][j]记录第i行第j个塔牌到底端的最大/最小值
    int f_tower_path[256][256] = {0}; //f[i][j]记录第i行第j个塔牌的数值
    void tower_path(int n); //P428 迭代动态规划 塔牌路径


    // 数学
    bool canThreePartsEqualSum1(vector<int> &A); // leetcode 1013. 将数组分成和相等的三个部分
    bool canThreePartsEqualSum2(vector<int> &A); // leetcode 1013. 将数组分成和相等的三个部分
    string gcdOfStrings(string str1, string str2); // leetcode 1071. 字符串的最大公因子
    int majorityElement(vector<int> &nums); // leetcode 169. 多数元素

    //algorithm book startup
    void chapter2_part1();  //before 2.6
    void chapter2_part2();  //after 2.5
    void chapter6();

    void setbuff_test();

    int reverse(int x); // leetcode


    int main() {
//    palindrome();
//    combination();
//    chapter2_part1();
//    chapter2_part2();
//    setbuff_test();

//    chapter6();
//    cout << reverse(-1534236469) << endl;


//    for (int i = 0; i < 32; ++i) {
//        cout << fibonacci2(i) << " ";
//    }

//    memset(dp_tower_path, 0, sizeof(dp_tower_path));
//    memset(f_tower_path, 0, sizeof(f_tower_path));
//    tower_path(5);

        return 0;
    }

    void setbuff_test() {
        setvbuf(stdout, NULL, _IOLBF, 1024);  //设置控制台输出为行缓存模式，把缓冲区与流相关
        cout << "hello world1\n";
        cout << "hello world2";
//    Sleep(5000);
        std::this_thread::sleep_for(std::chrono::milliseconds(1000));
        cout << "leeboy1";
        cout << "leeboy2" << endl;
//    system("pause");
        printf("pause");
    }

    //数组作为函数参数，第一维不需要写长度，第二维要填写长度
    void change_array(int a[], int b[][2]) {
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;
        b[0][1] = 3;
        b[1][0] = 33;
        b[1][1] = 333;
    }

    void swap_value_point(int *a, int *b) {
    *a += *b;
    *b = *a - *b;
    *a = *a - *b;
    }

    void swap_value_quote(int &a, int &b) {
        a += b;
        b = a - b;
        a = a - b;
    }

    void swap_value_quote_point(int *&a, int *&b) {
//    交换地址-只有使用引用+指针才能真正交换指针的地址
        int *temp = a;
        a = b;
        b = temp;
    }

    void chapter6() {
//    6.1 vector-向量/变长数组，常见用途：1.存储数据 2.用邻接表存储图
        cout << "VECTOR============" << endl;
//    1定义
//    一维vector
//    vector<typename> name;
        vector<int> _1D_v_int_array;
        vector<double> _1D_v_double_array;
        struct node {
            int id;
            string name;
            node *next;
        };
        vector<node> _1D_v_node_array;
//    二维vector
        vector<vector<int> > _2D_v_int_array1; //2维vector
        int arraySize = 4;
        vector<int> _2D_v_int_array2[arraySize]; //vector数组，相比前者，它的第一维已经固定了

//    2访问 3操作-push_back pop_back size clear insert erase
        for (int i = 0; i < arraySize; ++i) {
            _1D_v_int_array.push_back(i); //赋值
            node node1 = {i, " hlx", NULL};
            _1D_v_node_array.push_back(node1); //赋值
        }
        _1D_v_int_array[arraySize - 1] = 512; //下标访问
//    for (vector<int>::iterator it = _1D_v_int_array.begin(); //迭代器访问
//         it !=  _1D_v_int_array.end(); ++it) {
//        cout << *it << endl;
//        printf("%p\n", it); //迭代器it实质是指针，表示地址，通过加*取值
//    }

        vector<node>::iterator it_vector = _1D_v_node_array.begin();
        _1D_v_node_array[arraySize - 1] = (node) {512, " hyl", NULL}; //下标访问
        printf("%d\n", _1D_v_node_array.size());
        _1D_v_node_array.pop_back();
//    _1D_v_node_array.pop_back();
//    _1D_v_node_array.pop_back();
        printf("%d\n", _1D_v_node_array.size());
        _1D_v_node_array.insert(it_vector + 1, (node) {256, " hyl", NULL});
        printf("%d\n", _1D_v_node_array.size());
        _1D_v_node_array.erase(it_vector);
        printf("%d\n", _1D_v_node_array.size());
        for (vector<node>::iterator it = _1D_v_node_array.begin(); //迭代器访问
        it != _1D_v_node_array.end(); ++it) {
            cout << (*it).id << it->name << endl;
            printf("%p\n", it); //迭代器it实质是指针，表示地址，通过加*取值
        }
        for (int j = 0; j < arraySize; ++j) { //由于上面pop back这里数组越界了，但是原来的数据仍在，因此不报错
            cout << (*(it_vector + j)).id << (it_vector + j)->name << endl;
            printf("%p\n", it_vector + j); //v[i]==*(v + i)和*(v.begin + i)是一致的，
        }

//    6.2 set-集合，常见用途：1.自动排序 2.不含重复元素
        cout << "SET============" << endl;
        set<int> _1D_s_int_array;
        for (int i = 0; i < arraySize; ++i) {
            _1D_s_int_array.insert(i); //赋值
        }
        for (set<int>::iterator it = _1D_s_int_array.begin(); //迭代器访问，不可以使用*it+i访问，因此用find
        it != _1D_s_int_array.end(); ++it) {
            cout << *it << endl;
        }
//    操作-size clear insert erase find
        int value = 2;
        set<int>::iterator it_set = _1D_s_int_array.find(value);
//    _1D_s_int_array.erase(it_set, _1D_s_int_array.end()); //删除某区间
        _1D_s_int_array.erase(it_set); //删除单个
        _1D_s_int_array.erase(value - 1);
        for (set<int>::iterator it = _1D_s_int_array.begin(); //迭代器访问，不可以使用*it+i访问，因此用find
        it != _1D_s_int_array.end(); ++it) {
            cout << *it << endl;
        }

//    6.3 string-字符串
        cout << "STRING============" << endl;
        string _1D_string = "ABCDEFG"; //可下标访问，也可以迭代器访问
        for (string::iterator it = _1D_string.begin(); //迭代器访问，不可以使用*it+i访问，因此用find
             it != _1D_string.end(); ++it) {
            cout << *it << endl;
        }
//    常用操作，+= 比较 length==size insert erase clear substr find replace
//    insert
        string string1 = "WWW";
        int pos = 2;
        _1D_string.insert(pos, string1);
        cout << _1D_string << endl;
        string::iterator it_string = _1D_string.begin();
        _1D_string.insert(it_string, it_string + 1, it_string + 2); //it为插入位置，it1 it2为待插入字符串首位位置
        cout << _1D_string << endl;
//    erase
        int length = 2;
        _1D_string.erase(it_string); //erase单个元素
        cout << _1D_string << endl;
        _1D_string.erase(it_string, it_string + 2); //erase区间内元素
        cout << _1D_string << endl;
        _1D_string.erase(pos, length); //erase区间元素
        cout << _1D_string << endl;
//    substr
        cout << _1D_string.substr(pos, length) << endl; //截取从pos开始长为lenght的字符串，pos从0开始
//    find & npos
        string string2 = "EFG";
        if (_1D_string.find(string2) != string::npos) { //find查找成功返回pos,npos为查找失败时的返回值-1
            cout << _1D_string.find(string2) << endl;
        }
        if (_1D_string.find(string2, pos) != string::npos) { //find从pos位开始查找，成功返回pos,npos为查找失败时的返回值-1
            cout << _1D_string.find(string2) << endl;
        }


    }

    void chapter2_part2() {
//    2.6.3 数组作为函数参数，第一维不需要写长度，第二维要填写长度，数组作为参数时在函数中对数组元素对修改就是修改其本身
        int a[3]{0};
        int b[2][2]{1};
        for (int i = 0; i < 3; ++i) {
            printf("a[%d]=%d ", i, a[i]);
        }
        cout << endl;
        for (int j = 0; j < 2; ++j) {
            for (int i = 0; i < 2; ++i) {
                printf("b[%d][%d]=%d ", j, i, b[j][i]);
            }
        }
        cout << endl;
        change_array(a, b); //调用时，只需要填写数组名即可
        for (int i = 0; i < 3; ++i) {
            printf("a[%d]=%d ", i, a[i]);
        }
        cout << endl;
        for (int j = 0; j < 2; ++j) {
            for (int i = 0; i < 2; ++i) {
                printf("b[%d][%d]=%d ", j, i, b[j][i]);
            }
        }
        cout << endl;

//    2.7 指针-指针是unsigned类型的整数，可以将int*看作unsigned int可以理解多符号时的效果
        int value_a = 5;
        int *point_a = &value_a;    //指针point_a存放value_a的地址
        printf("指针赋值后的值：%d\n", *point_a);   //*号类似钥匙，可以打开房间，获得值
        value_a = 6;
        printf("改变指向地址的值：%d\n", *point_a);


        int array_a[3]{0};
        cout << "为数组输入3个值" << endl;
        for (int k = 0; k < 3; ++k) {
//        scanf("%d", &array_a[k]);   //scanf的参数需要指针类型 type */地址
            scanf("%d", array_a + k);   //scanf的参数需要指针类型 type */地址
        }
        for (int k = 0; k < 3; ++k) {
            printf("%d ", array_a[k]);
        }
        cout << endl;
//    指针和引用-指针和引用要区分，引用是别名而不是取地址的意思
//    指针作为函数参数
        int value_point_a = 5;
        int value_point_b = 6;
        int *point_a1 = &value_point_a;
        int *point_b1 = &value_point_b;
        cout << "使用指针作为函数参数" << endl;
        printf("初始值: a=%d, b=%d;", *point_a1, *point_b1);
        printf("初始值: a=%d, b=%d;", value_point_a, value_point_b);
        cout << endl;
        swap_value_point(point_a1, point_b1);   //指针指向地址内的值改变，a和b的值也改变-实质：改变了a和b的值
        printf("指针函数交换: a=%d, b=%d;", *point_a1, *point_b1);
        printf("指针函数交换: a=%d, b=%d;", value_point_a, value_point_b);
        cout << endl;
//    引用作为函数参数-指针和引用要区分，引用是别名而不是取地址的意思
        cout << "使用引用作为函数参数" << endl;
        swap_value_quote(value_point_a, value_point_b);   //指针指向地址内的值改变，a和b的值也改变-实质：改变了a和b的值
        printf("引用函数交换: a=%d, b=%d;", *point_a1, *point_b1);
        printf("引用函数交换: a=%d, b=%d;", value_point_a, value_point_b);
        cout << endl;
//    指针的引用使用，可以将int*看作unsigned int可以理解多符号时的效果，效果是改变指针的值
        swap_value_quote_point(point_a1, point_b1);   //这里指针a b的值相互交换了，但是值a b是不变的-实质：改变了指针的值
        printf("指针的引用函数交换: a=%d, b=%d;", *point_a1, *point_b1);
        printf("指针的引用函数交换: a=%d, b=%d;", value_point_a, value_point_b);
        cout << endl;
        printf("指针的引用函数交换: a=%p, b=%p;", point_a1, point_b1);
        printf("指针的引用函数交换: a=%p, b=%p;", &value_point_a, &value_point_b);
        cout << endl;

//    2.8 结构体
        struct studentInfo {
            int id;
            char gender;
            char name[20];
            char major[20];
            studentInfo *next;

            studentInfo() {
                id = -1;
                gender = 'm';
            }//默认构造函数
            studentInfo(int _id, char _gender) : id(_id), gender(_gender) {}
        } Alice, Bob, stu[20], *pStudentInfo = &Bob;
        studentInfo HLX = studentInfo(1, 'f');
//    访问方法-访问Alice、*p、p
        cout << Alice.gender << endl;
        cout << (*pStudentInfo).gender << endl;
        cout << pStudentInfo->gender << endl;   //使用*.和->是等价的
        cout << stu->gender << endl;
        cout << (stu + 1)->gender << endl;
        cout << (*(stu + 1)).gender << endl;

        cout << HLX.id << " " << HLX.gender << endl;

    }

    void chapter2_part1() {
//    2.1.1 - 2.2.1 基本数据类型等
//    int a, b;
//    scanf("%d %d", &a, &b);
//    printf("%d %d\n", a, b);
//    printf("a+b=%d\n", a+b);

        long long bignum_error = 123456789012345;
        LL bignum_correct = 0000000000000001LL;   //如果值大于2^31-1则可能需要加上LL，保证编译不报错
//    long long bignum_correct = 123456789012345LL;   //如果值大于2^31-1则可能需要加上LL，保证编译不报错
//    强制类型转换
//    double bignum = (double)bignum_correct; //有精度损失，导致数值异常
        int bignum = (int) bignum_correct;

//    2.2.2 使用scanf和printf输入/输出字符
//    常见的数据类型的printf的格式符号
//    %d 十进制有符号整数   int
//    %lld 十进制有符号整数   long long
//    %u 十进制无符号整数
//    %f 浮点数            double float
//    %s 字符串            string
//    %c 单个字符           char
//    %p 指针的值
//    %e 指数形式的浮点数
//    %x, %X 无符号以十六进制表示的整数
//    %0 无符号以八进制表示的整数
//    %g 自动选择合适的表示法
//    %md 不足m位进行右对齐输出
//    %0md 不足m位右对齐左补0输出
//    %.mf 保留m位小数输出
        printf("%lld %lld %d %f\n", bignum_error, bignum_correct, bignum, pi);
        printf("%5d %05d %.2f\n", bignum, bignum, pi);
        char c = '\0';  //代表空字符NULL，其ASCII码为0，String最后一位就是这个
        char endl = '\n';  //代表空字符NULL，其ASCII码为0，String最后一位就是这个
        char str[25] = "hello\tword!\n";
//    printf("%s", str1 + '\n');  //ERROR adding char to str pointer doesn't happen to the string
//    str1[13] = endl ;
        printf("%s", str);  //ERROR adding char to str pointer doesn't happen to the string
        printf("请输入分别一个char和string\n");
        scanf("%c%s", &c, str);
        printf("%c\n%s\n", c, str);

//    缓冲区中没有被取走的字符串会直接赋值过来，导致出现回车或者其他现象！！
//    ?????缓冲区哪来的回车呀
        getchar();  //清空缓存中的回车，这里缓存中的回车哪里来的？？

        //    2.2.3 使用getchar和putchar输入/输出字符
        printf("请输入分别四个个char，第二个不显示\n");
        char c1, c2, c3;
        c1 = getchar();
        getchar();
        c2 = getchar();
        c3 = getchar();
        putchar(c1);
        putchar(c2);
        putchar(c3);

//    2.5.4 memset-对数组每一个元素赋相同的值-先将值转化为补码形式，再按字节赋值，0补码全为0，-1的补码全为1，不容易错。
        cout << "2.5.4 memset-对数组每一个元素赋相同的值-先将值转化为补码形式，再按字节赋值，0补码全为0，-1的补码全为1，不容易错。" << endl;
        int a_array_int[5] = {1, 2, 3, 4, 5};
        int b_array_int[5]{5};   //和预想的不一样，不是所有的都复制5，而是只有第一个赋值为5，类推按顺序赋值，没有值为0
        int c_array_int[5]{11, 22, 33, 44, 55};    //可以省略"="号
        printf("循环输出数组a b的值\n");
        for (int i = 0; i < 5; ++i) {
            printf("%d %d %d\n", *(a_array_int + i), *(b_array_int + i), *(c_array_int + i));
            printf("%d %d %d\n", a_array_int[i], b_array_int[i], c_array_int[i]);
        }
        //
        memset(a_array_int, 0, sizeof(a_array_int));
        memset(b_array_int, -1, sizeof(b_array_int));   //按字节赋值，补码形式为0xffffffff，原码为0x80000001
        memset(c_array_int, 1, sizeof(c_array_int));    //按字节赋值，补码和原码都为0x01010101=16843009
        printf("memset后，循环输出数组a b的值\n");
        for (int i = 0; i < 5; ++i) {
            printf("%d %d %d\n", *(a_array_int + i), *(b_array_int + i), *(c_array_int + i));
            printf("%d %d %d\n", a_array_int[i], b_array_int[i], c_array_int[i]);
        }

//    2.5.5 字符数组
        cout << "2.5.5 字符数组" << endl;
        char str1[20];
        char str2_array[5][10];
        printf("输入字符串str1\n");
//    getline()  //需要有清空缓冲区的操作
        getchar();  //需要有清空缓冲区的操作
        gets(str1); //缓冲区中没有被取走的字符串会直接赋值过来，导致出现回车或者其他现象！！
        puts(str1);
        for (int j = 0; j < 5; ++j) {
            printf("输入字符串str2 %d\n", j);
            gets(str2_array[j]);
        }
        for (int j = 0; j < 5; ++j) {
            puts(str2_array[j]);
        }
//    字符数组的存放方式
//    空字符\0在使用gets或scanf输入字符串时会自动添加，占用一个字符位-一个char
//    puts和printf通过识别\0作为字符串结束的标识
//    char字符数组需要在末尾加\0
        char test_char_array[5] = "hell";
        puts(test_char_array);
        printf("%c", test_char_array[0]);
        printf("%c", test_char_array[1]);
        printf("%c", test_char_array[2]);
        printf("%c", test_char_array[3]);
        printf("%c", test_char_array[4]);
        printf("%c", '\0');

        printf("输入字符串str1，使用getchar循环读入，故意不在末尾添加\\0 \n");
        for (int k = 0; k < 5; ++k) {
            str1[k] = getchar();
        }
        printf("str1 = %s", str1);
        printf("%c\n", str1[5]);  //好像ide或者编译器自己补了\0，若无\0则会因为字符串末尾无法识别而输出乱码

//    2.5.6 string.h头文件的使用
        cout << "2.5.6 string.h头文件的使用" << endl;
//    char *str2 = "hello world";
        char str2[] = "hello world";
        printf("str1 = %s\n", str1);
        printf("str2 = %s\n", str2);
        strlen(str1);    //不包括\0
        printf("str1 length = %zu\n", strlen(str1));
        strcmp(str1, str2);    //按字典顺序比较大小，str1<str2==-1 str1==str2==0 str1>str2==1，例如：aaaa < aab
        printf("strcmp(str1, str2) = %d\n", strcmp(str1, str2));
        strcpy(str1, str2);    //把str2复制给str1
        printf("cpy str2 to str1 = %s\n", str1);
        strcat(str1, str2);   //把str2拼接到数组str1
        printf("cat str2 to str1 = %s\n", str1);

//    2.5.7 sscanf和sprintf的使用
//    了解sscanf、sprintf和scanf、printf的差异
        cout << "2.5.7 sscanf和sprintf的使用" << endl;
        printf("sscanf和sprintf的使用，输入整数\n");
        int n_int_ss_test = 0;
        char str_ss_test[100] = "123";
        scanf("%d", &n_int_ss_test);   //scanf(screen, "%d", &n);从screen取值，以%d的格式赋值给n
        printf("%d\n", n_int_ss_test);   //printf(screen, "%d", n);将n的值以%d的格式赋值给screen
        sscanf(str_ss_test, "%d", &n_int_ss_test);  //sscanf(str, "%d", &n);从str取值，以%d的格式赋值给n
        printf("%d \n", n_int_ss_test);
        n_int_ss_test = 999;
        sprintf(str_ss_test, "%d", n_int_ss_test);//sprintf(str, "%d", n);将n的值以%d的格式赋值给str
        printf("%s \n", str_ss_test);
//    复杂情况下使用s-
        int year_ss_test;
        double pi_ss_test;
        char str1_ss_test[100] = "2020:3.14;helloworld!", *str2_ss_test;
        sscanf(str1_ss_test, "%d:%lf;%s", &year_ss_test, &pi_ss_test, str2_ss_test);
        printf("year_ss_test=%d; pi_ss_test=%.2f; str2_ss_test=%s \n", year_ss_test, pi_ss_test, str2_ss_test);
        year_ss_test = 2021;
        pi_ss_test = 3.1415926;
//    str2_ss_test = "fuck the world";
//    *(str2_ss_test+5) = '\0';
        sprintf(str1_ss_test, "%d; %.5f; %s", year_ss_test, pi_ss_test, str2_ss_test);
        printf("str1_ss_test=%s \n", str1_ss_test);

    }

    void palindrome() {
        int maxn = 1010;
        char S[maxn];
//    string S;
//dp=1代表回文，dp=0代表不是回文
        int dp[maxn][maxn];

        cout << "Hello, World!\n" << endl;
        gets(S);
        int len = strlen(S), ans = 1, ans_start = 0;
        memset(dp, 0, sizeof(dp));

        for (int k = 0; k < len; ++k) {
            for (int i = 0; i < len; ++i) {
                printf("%d", dp[k][i]);
            }
            printf("\n");
        }


        //边界
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
            if (i < len - 1) {
                if (S[i] == S[i + 1]) {
                    dp[i][i + 1] = 1;
                    ans = 2;
                }
            }
        }

        //动态转移方程
//枚举子串长度
        for (int L = 3; L <= len; L++) {
            //枚举子串起始位置
            for (int i = 0; i < len - 3; i++) {
                //子串终点位置
                int j = i + L - 1;
                if (S[i] == S[j] && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                    ans_start = i;
                    ans = L;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        printf("longest string length is %d \n", ans);
        //截取S中的最长子串，这个很重要，不可以直接S[start, end]
        char x[ans];
        strncpy(x, S + ans_start, ans);
        printf("longest string is %s \n", x);
    }

    void combination() {
        printf("enter n and k\n");
        int n = 0;
        int k = 0;
        scanf("%d%d", &n, &k);
        printf("%d %d\n", n, k);
//    ans = combine(n, k);

        combine(4, 2);
    }

    vector<vector<int> > combine(int n, int k) {
//    第一个n表示数组长度，{(1, n)} {}内为赋值数值，没有显性赋值会默认0补齐
//    实际结果默认所有数为最后一个值
        vector<int> nums(n, {0});
        for (int i = 0; i < nums.size(); i++) {
            printf("%d\n", nums[i]);
        }
//    这里重新赋值，因此无所谓
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        for (int num : nums) {
            printf("%d\n", num);
        }
        recursion(nums, k, 0);

        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans[0].size(); j++)
                printf("%d ", ans[i][j]);
            printf("\n");
        }

    }

    void recursion(vector<int> &nums, int k, int pos) {
        if (nums.size() == k) {
            ans.push_back(nums);
            return;
        }
        for (int i = pos; i < nums.size(); i++) {
            vector<int> temp(nums);
            nums.erase(nums.begin() + i);
            recursion(nums, k, i);
            nums = temp;
        }
    }

    // leetcode 1. 两数之和
    vector<int> twoSum(vector<int> &nums, int target) {
        vector<int> result;
        for (int i = 0; i < nums.size(); ++i) {
            for (int j = i + 1; j < nums.size(); ++j) {
                if (target == nums[i] + nums[j]) {
                    result.push_back(i);
                    result.push_back(j);
                    return result;
                }
            }
        }
        return result;

//    用时较快的方法，后续了解unordered_map的用法
//    unordered_map<int,int> m;
//
//    for(int i=0;i<nums.size();i++)
//    {
//        if(m.find(target-nums[i])!=m.end())//m中存在对应的键值
//            return {m[target-nums[i]],i};//因为i为较大的元素，此时添加进去的键值都还小于i，所以i在后面
//
//        m[nums[i]]=i;  //添加
//    }
//    return {};
    }

    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
//执行用时 :
//72.70%
//内存消耗 :
//5.21%
        ListNode *result = new ListNode(-1), *pre = result;
        int CF_flag = 0;
        while (l1 || l2) {
            int sum = 0;

            if (l1) {
                sum += l1->val;
                l1 = l1->next;
            }

            if (l2) {
                sum += l2->val;
                l2 = l2->next;
            }
            sum += CF_flag;

            if (sum > 9) {
                CF_flag = 1;
                sum -= 10;
            } else {
                CF_flag = 0;
            }
            pre->next = new ListNode(sum);
            pre = pre->next;
//         if(!pre){
//             cout << "sxxxx" << endl;
//             result.val = sum;
//             pre = &result;
//             cout << sum << CF_flag << endl;
//         }else{
//             ListNode tmp = ListNode(sum);
//             pre->next = &tmp; //为什么这样不可以？？？？？？
//             pre = pre->next;
//             cout << sum << CF_flag << endl;
//         }
            printf("%p\n", pre);
            printf("%p\n", result);
        }
        if (CF_flag) pre->next = new ListNode(1);
        return result->next;
//
//
//    // 定义一个pre表示前一个操作节点
//    // 定义n永远指向头部，这样最后返回n即可
//    //用时99.74，内存99.25
//    ListNode *n = l1, *pre;
//    int flag = 0;
//    while (l1 || l2) {
//        int value = 0;
//        if (l1) {
//            pre = l1;
//            value = l1->val + value;
//            l1 = l1->next;
//        } else {
//            // l1为空时将链l1接在链l2上
//            pre->next = l2;
//            pre = l2;
//        }
//        if (l2) {
//            value = l2->val + value;
//            l2 = l2->next;
//        } else {
//            // 这个没必要
//            // pre->next=l1;
//        }
//        value = value + flag;
//        if (value > 9) {
//            value %= 10;
//            pre->val = value;
//            flag = 1;
//        } else {
//            pre->val = value;
//            flag = 0;
//        }
//    }
//    // 位数增加
//    if (l1 == NULL && l2 == NULL && flag == 1)
//        pre->next = new ListNode(1);
//    return n;
    }


    int lengthOfLongestSubstring(string s) {
//    滑动窗口
        //s[start,end) 前面包含 后面不包含
        int start(0), end(0), length(0), result(0);
        int sSize = int(s.size());

//    桶优化
        vector<int> vec(128, -1);

        while (end < sSize) {
            char tmpChar = s[end];

//        滑动窗口
//        这个for循环可以利用hashmap或者桶进行优化，减少时间复杂度
            for (int index = start; index < end; index++) {
                if (tmpChar == s[index]) {
                    start = index + 1;
                    length = end - start;
                    break;
                }
            }

//        桶优化
            if (vec[tmpChar] >= start) {    //这里必须>=因为出现==start的情况也是字符串重复的情况
                start = vec[tmpChar] + 1;
                length = end - start;
            }
            vec[tmpChar] = end;   //key关键逻辑

//        hashmap优化
//        待续

            end++;
            length++;
            result = max(result, length);   //这个放在上面if里面无法处理输入为" "的问题
        }
        return result;
    }

    double findMedianSortedArrays(vector<int> &nums1, vector<int> &nums2) {
        int n = nums1.size();
        int m = nums2.size();

        if (n > m)  //保证数组1一定最短
        {
            return findMedianSortedArrays(nums2, nums1);
        }

        // Ci 为第i个数组的割,比如C1为2时表示第1个数组只有2个元素。LMaxi为第i个数组割后的左元素。RMini为第i个数组割后的右元素。
        int LMax1, LMax2, RMin1, RMin2, c1, c2, lo = 0, hi = 2 * n;  //我们目前是虚拟加了'#'所以数组1是2*n长度

        while (lo <= hi)   //二分
        {
            c1 = (lo + hi) / 2;  //c1是二分的结果
            c2 = m + n - c1;

            LMax1 = (c1 == 0) ? INT_MIN : nums1[(c1 - 1) / 2];
            RMin1 = (c1 == 2 * n) ? INT_MAX : nums1[c1 / 2];
            LMax2 = (c2 == 0) ? INT_MIN : nums2[(c2 - 1) / 2];
            RMin2 = (c2 == 2 * m) ? INT_MAX : nums2[c2 / 2];

            if (LMax1 > RMin2)
                hi = c1 - 1;
            else if (LMax2 > RMin1)
                lo = c1 + 1;
            else
                break;
        }
        return (max(LMax1, LMax2) + min(RMin1, RMin2)) / 2.0;

//    const int n1 = nums1.size();
//    const int n2 = nums2.size();
//    if (n1 > n2) return findMedianSortedArrays(nums2, nums1);
//    const int k = (n1 + n2 + 1) / 2;
//    int left = 0;
//    int right = n1;
//    while (left < right) {
//        const int m1 = left + (right - left) / 2;
//        const int m2 = k - m1;
//        if (nums1[m1] < nums2[m2 - 1])
//            left = m1 + 1;
//        else
//            right = m1;
//    }
//    const int m1 = left;
//    const int m2 = k - left;
//    const int c1 = max(m1 <= 0 ? INT_MIN : nums1[m1 - 1],
//                       m2 <= 0 ? INT_MIN : nums2[m2 - 1]);
//    if ((n1 + n2) % 2 == 1)
//        return c1;
//    const int c2 = min(m1 >= n1 ? INT_MAX : nums1[m1],
//                       m2 >= n2 ? INT_MAX : nums2[m2]);
//    return (c1 + c2) * 0.5;




//此方案没有考虑到中位数由单个数组中的两个元素组成
//    输入:
//    [1,2]
//    [-1,3]
//    输出
//    2.00000
//    预期结果
//    1.5

//    int step = 0;
//    const int n1 = nums1.size();
//    const int n2 = nums2.size();
//    const int k = (n1 + n2 + 1)/2;  //向上取整，如果总数是基数个不加1就错了。
//    int high1(n1-1), high2(n2-1), low1(0), low2(0), mid1(0), mid2(0);
//    if(n1 == 0) return (n2%2==0?(nums2[(high2 + low2) / 2]+nums2[(high2 + low2 + 1) / 2])/2.0:nums2[(high2 + low2) / 2]);
//    if(n2 == 0) return (n1%2==0?(nums1[(high1 + low1) / 2]+nums1[(high1 + low1 + 1) / 2])/2.0:nums1[(high1 + low1) / 2]);
//    while (high1 >= low1 || high2 >= low2) {
//
////        if (high1 > low1 && high2 > low2) {
////            mid1 = (high1 - low1) / 2;
////            mid2 = (high2 - low2) / 2;
////        }
//        if (high1 <= low1){
//            mid2 = (high2 + low2) / 2;
//        }
//
//        if (high2 <= low2){
//            mid1 = (high1 + low1) / 2;
//        }
//
//        if (nums1[mid1] > nums2[mid2]) {
//            high1 = mid1-1;
//            low2 = mid2+1;
//        } else {
//            low1 = mid1+1;
//            high2 = mid2-1;
//        }
//        step++;
//        if (step == k)
//            break;
//    }
//    if ((n1+n2) % 2 == 0){
//        return (nums1[mid1]+nums2[mid2]) / 2.0;
//    } else {
//        return min(nums1[mid1], nums2[mid2]);
//    }

    }

    vector<int> distributeCandies(int candies, int num_people) {
        vector<int> candies_distribution;
        for (int i = 0; i < num_people; i++) {
            candies_distribution.push_back(0);
        }
        int k = 0;
        // while (1){
        //     // 循环用 i%num_people代替
        //     for (int i = 0; i < num_people; i++){
        //         k++;
        //         if(candies < k) {
        //             candies_distribution[i] += candies;
        //             candies = 0;
        //             break;
        //         }
        //         candies_distribution[i] += k;
        //         candies -= k;
        //     }
        //     if(candies == 0){
        //         break;
        //     }
        // }
        while (candies > 0) {
            // 循环用 k%num_people代替循环，是个不错的思路。
            candies_distribution[k % num_people] += min(k + 1, candies);
            k++;
            candies -= k;
        }
        return candies_distribution;
    }

    int coinPathSearch(vector<int> &coins, int amount, int &path) {
        path++;
        for (int i = coins.size() - 1; i >= 0; --i) {
            if (amount - coins[i] == 0) return path;
            if (amount - coins[i] < 0) coinPathSearch(coins, amount - coins[i], path);
            if (amount - coins[i] > 0) continue;
        }
    }


    void searchCoins(vector<int> &coins, int amount, int &times, set<int> &all_result) {
        for (int i = coins.size() - 1; i >= 0; --i) {
            if (amount == 0) {
                all_result.insert(0);
            } else if (amount - coins[i] == 0) {
                cout << "i==" << i << endl;
                cout << "OK times==" << times << endl;
                all_result.insert(times);
                break;
//            return times;
            } else if (amount - coins[i] > 0) {
                cout << "i==" << i << endl;
                times = times + 1;
                searchCoins(coins, amount - coins[i], times, all_result);
            } else if (amount - coins[i] < 0) {
                cout << "i==" << i << endl;
//            times = -1;
                cout << "NOT OK times==" << times << endl;
                continue;
            }
        }
        // return times;
    }

    int coinChange(vector<int> &coins, int amount) {

        int times = 1;
        set<int> all_result;
        searchCoins(coins, amount, times, all_result);
        if (all_result.size() > 0) {
            return *(all_result.begin());
        } else {
            return -1;
        }
    }

// BEST ！！主要研究它的for循环
//void coinChange(vector<int>& coins, int amount, int c_index, int count, int& ans)
//{
//    if (amount == 0)
//    {
//        ans = min(ans, count);
//        return;
//    }
//    if (c_index == coins.size()) return;
//
//    for (int k = amount / coins[c_index]; k >= 0 && k + count < ans; k--)
//    {
//        coinChange(coins, amount - k * coins[c_index], c_index + 1, count + k, ans);
//    }
//}
//
//int coinChange(vector<int>& coins, int amount)
//{
//    if (amount == 0) return 0;
//    sort(coins.rbegin(), coins.rend());
//    int ans = INT_MAX;
//    coinChange(coins, amount, 0, 0, ans);
//    return ans == INT_MAX ? -1 : ans;
//}
//

    int maxProfit1(vector<int> &prices) {
        int buyin = -1;
        int profit = 0;
        for (int i = 0; i < prices.size(); i++) {
            if (buyin == -1 || prices[i] <= buyin) {
                buyin = prices[i];
            } else if (prices[i] > buyin) {
                profit = max(profit, prices[i] - buyin);
            } else {
                continue;
            }
        }
        return profit;
//
//    stack<int> st;
//    int ans = 0; // 若无利润则返回0
////    6.7 Stack常用操作, push top pop empty size
//    for (int i = 0; i < prices.size(); ++i) {
//        if (st.empty() || st.top() > prices[i]) { // 核心思想，如果top比prices大，则后续的prices[i+n]-top肯定小于prices[i+n]-prices[i]
//            if (!st.empty()) {
//                st.pop(); // 核心思想，弹出无用的top，腾出栈位
//            }
//            st.push(prices[i]);
//        } else {
//            ans = max(ans, prices[i] - st.top());
//        }
//    }
//    return ans;
    }

    int maxProfit2(vector<int> &prices) {

        stack<int> st;
        int ans = 0; // 若无利润则返回0
//    6.7 Stack常用操作, push top pop empty size
        for (int i = 0; i < prices.size(); ++i) {
            if (!st.empty() && prices[i] > st.top()) {
                ans = ans + prices[i] - st.top();
            } else {
                st.push(prices[i]);
            }
        }
        return ans;

//    用buyin作为买入最佳时机，代替st栈
//    int buyin = -1;// 核心思想，遍历prices过程中找到最小的prices，使用最小prices后的prices减去即可。
//    int ans = 0;
//    for (int i = 0; i < prices.size(); ++i) {
//        if(buyin != -1 && prices[i]>buyin){
//            ans = ans + prices[i] - buyin;
//        }
//        else{
//            buyin = prices[i];
//        }
//    }
//    return ans;

    }

    int maxProfit3(vector<int> &prices) {
        int k = 2;
        int s = 2;
        int dp_maxProfit[prices.size() + 1][k + 1][s];
        memset(dp_maxProfit, 0, sizeof(dp_maxProfit));

        // 边界
        // 用0表示当前利润为0。
        // ⽤负⽆穷表⽰这种不可能。
        for (int i = 0; i <= prices.size(); ++i) {
            dp_maxProfit[i][0][0] = 0;
            dp_maxProfit[i][0][1] = -99999;
        }
        for (int j = 0; j <= k; ++j) {
            dp_maxProfit[0][j][0] = 0;
            dp_maxProfit[0][j][1] = -99999;
        }

        // 状态转移方程
        // 这个问题的「状态」有三个，
        // 第⼀个是天数，
        // 第⼆个是允许交易的最⼤次数，
        // 第三个是当前的持有状态（即之前说的 rest 的状态，我们不妨⽤ 1 表⽰持有，0 表⽰没有持有）。
        // 然后我们⽤⼀个 三维数组就可以装下这⼏种状态的全部组合：
        for (int i = 1; i <= prices.size(); ++i) {
            for (int j = 1; j <= k; ++j) {
                //解释：今天我没有持有股票，有两种可能：
                //要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
                //要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
                dp_maxProfit[i][j][0] = max(dp_maxProfit[i - 1][j][0], dp_maxProfit[i - 1][j][1] + prices[i - 1]);

                //解释：今天我持有着股票，有两种可能：
                //要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
                //要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
                dp_maxProfit[i][j][1] = max(dp_maxProfit[i - 1][j][1], dp_maxProfit[i - 1][j - 1][0] - prices[i - 1]);
            }
        }
        return dp_maxProfit[prices.size()][k][0];
    }

    int maxProfit_infinite_K(vector<int> &prices) {
        int s = 2;
        int dp_maxProfit[prices.size() + 1][s];
        memset(dp_maxProfit, 0, sizeof(dp_maxProfit));

        dp_maxProfit[0][0] = 0;
        dp_maxProfit[0][1] = INT_MIN;

        for (int i = 1; i <= prices.size(); ++i) {
            dp_maxProfit[i][0] = max(dp_maxProfit[i - 1][0], dp_maxProfit[i - 1][1] + prices[i - 1]);
            dp_maxProfit[i][1] = max(dp_maxProfit[i - 1][1], dp_maxProfit[i - 1][0] - prices[i - 1]);

        }
        return dp_maxProfit[prices.size()][0];
    }

    int maxProfit4(int k, vector<int> &prices) {
        // 有了上⼀题 k = 2 的铺垫，这题应该和上⼀题的第⼀个解法没啥区别。
        // 但是 出现了⼀个超内存的错误，原来是传⼊的 k 值会⾮常⼤，dp 数组太⼤了。
        // 现在想想，交易次数 k 最多有多⼤呢？
        // ⼀次交易由买⼊和卖出构成，⾄少需要两天。所以说有效的限制 k 应该不超 过 n/2，如果超过，就没有约束作⽤了，相当于 k = +infinity。这种情况是之 前解决过的。
        if (k > prices.size() / 2) {
            return maxProfit_infinite_K(prices);
        }
        int s = 2;
        int dp_maxProfit[prices.size() + 1][k + 1][s];
        memset(dp_maxProfit, 0, sizeof(dp_maxProfit));

        // 边界
        for (int i = 0; i <= prices.size(); ++i) {
            dp_maxProfit[i][0][0] = 0;
            dp_maxProfit[i][0][1] = INT_MIN;
        }
        for (int j = 0; j <= k; ++j) {
            dp_maxProfit[0][j][0] = 0;
            dp_maxProfit[0][j][1] = INT_MIN;
        }

        // 状态转移方程
        for (int i = 1; i <= prices.size(); ++i) {
            for (int j = 1; j <= k; ++j) {
                dp_maxProfit[i][j][0] = max(dp_maxProfit[i - 1][j][0], dp_maxProfit[i - 1][j][1] + prices[i - 1]);
                dp_maxProfit[i][j][1] = max(dp_maxProfit[i - 1][j][1], dp_maxProfit[i - 1][j - 1][0] - prices[i - 1]);
            }
        }
        return dp_maxProfit[prices.size()][k][0];
    }

    int maxProfit5(vector<int> &prices) {
        int s = 2;
        int dp_maxProfit[prices.size() + 1][s];
        memset(dp_maxProfit, 0, sizeof(dp_maxProfit));

        dp_maxProfit[0][0] = 0;
        dp_maxProfit[0][1] = INT_MIN;

        // dp_maxProfit[i-2][0]
        int dp_maxProfit_id2_0 = 0;

        for (int i = 1; i <= prices.size(); ++i) {
            int temp = dp_maxProfit[i - 1][0];
            dp_maxProfit[i][0] = max(dp_maxProfit[i - 1][0], dp_maxProfit[i - 1][1] + prices[i - 1]);
            dp_maxProfit[i][1] = max(dp_maxProfit[i - 1][1], dp_maxProfit_id2_0 - prices[i - 1]);
            dp_maxProfit_id2_0 = temp; //下次循环就是dp [i-2][0]
        }
        return dp_maxProfit[prices.size()][0];
    }

    int maxProfit6(vector<int> &prices, int fee) {
        int s = 2;
        int dp_maxProfit[prices.size() + 1][s];
        memset(dp_maxProfit, 0, sizeof(dp_maxProfit));

        dp_maxProfit[0][0] = 0;
        dp_maxProfit[0][1] = -9999999;

        // dp_maxProfit[i-2][0]
        int dp_maxProfit_id2_0 = 0;

        for (int i = 1; i <= prices.size(); ++i) {
            dp_maxProfit[i][0] = max(dp_maxProfit[i - 1][0], dp_maxProfit[i - 1][1] + prices[i - 1] - fee);
            dp_maxProfit[i][1] = max(dp_maxProfit[i - 1][1], dp_maxProfit[i - 1][0] - prices[i - 1]);
        }
        return dp_maxProfit[prices.size()][0];
    }

    int ans_diameterOfBinaryTree = 0;

    int trail_tree(TreeNode *root) { //深度从低向上递增，一开始先算最左边的叶子->左右为空L=0；继续推导～～～～
        if (!root) {
            return 0;
        }
        int l = trail_tree(root->left);
        int r = trail_tree(root->right);
        ans_diameterOfBinaryTree = max(l + r, ans_diameterOfBinaryTree);
        return max(l, r) + 1;
    }

    int diameterOfBinaryTree(TreeNode *root) {
        trail_tree(root);
        return ans_diameterOfBinaryTree;
    }

    int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (dp_fibonacci[n] != -1) {
            return dp_fibonacci[n];
        } else {
            dp_fibonacci[n] = fibonacci(n - 2) + fibonacci(n - 1);
            return dp_fibonacci[n];
        }
    }

    int fibonacci2(int n) {
//    vector<int> dp_fibonacci2(n + 1, 0);
//    dp_fibonacci2[0] = 1;
//    dp_fibonacci2[1] = 1;
//    if (n > 1) {
//
//        for (int i = 2; i <= n; ++i) {
//            dp_fibonacci2[i] = dp_fibonacci2[i - 2] + dp_fibonacci2[i - 1];
////            cout << dp_fibonacci2[i] << endl;
//        }
//    }
//    return dp_fibonacci2[n];
        // 优化，仅用两个临时变量
        int pre1 = 1, pre2 = 1, cur = 0;
        if (n == 1 || n == 0) {
            return 1;
        } else {
            for (int i = 2; i <= n; ++i) {
                cur = pre1 + pre2;
                pre2 = pre1;
                pre1 = cur;
            }
        }
        return cur;
    }


    void tower_path(int n) {
        scanf("输入塔牌层数%d", &n);
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) { //塔牌从1行开始
                scanf("%d", &f_tower_path[i][j]); //f[i][j]记录第i行第j个塔牌的数值
            }
        }

//    边界
        for (int k = 1; k <= n; ++k) {
            dp_tower_path[n][k] = f_tower_path[n][k];
        }

//    从第n-1层计算出dp[i][j]
        for (int l = n - 1; l >= 1; --l) {
            for (int i = 1; i <= l; ++i) {
                dp_tower_path[l][i] = max(dp_tower_path[l + 1][i], dp_tower_path[l + 1][i + 1]) + f_tower_path[l][i];
            }
        }

        printf("最大路径值%d", dp_tower_path[1][1]);
    }

    int reverse(int x) {
//思路
//
//标签：数学
//本题如果不考虑溢出问题，是非常简单的。解决溢出问题有两个思路，第一个思路是通过字符串转换加try catch的方式来解决，第二个思路就是通过数学计算来解决。
//由于字符串转换的效率较低且使用较多库函数，所以解题方案不考虑该方法，而是通过数学计算来解决。
//通过循环将数字x的每一位拆开，在计算新值时每一步都判断是否溢出。
//溢出条件有两个，一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值MIN_VALUE，设当前计算结果为ans，下一位为pop。
//从ans * 10 + pop > MAX_VALUE这个溢出条件来看
//当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
//当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
//从ans * 10 + pop < MIN_VALUE这个溢出条件来看
//当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
//当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > INT_MAX / 10 || (ans == INT_MAX / 10 && pop > 7))
                return 0;
            if (ans < INT_MIN / 10 || (ans == INT_MIN / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    bool canThreePartsEqualSum1(vector<int> &A) {
        int sum = accumulate(A.begin(), A.end(), 0);
        if (sum % 3 != 0) {
            return false;
        }
        int part_sum = 0;
        int flag = 0;
        for (int i = 0; i < A.size(); ++i) {
            part_sum += A[i];
            if (part_sum == sum / 3) {
                flag++;
                part_sum = 0;
            }
            if (flag == 3) {
                return true;
            }
        }
        return false;
    }

    bool canThreePartsEqualSum2(vector<int> &A) {
        int sum = accumulate(A.begin(), A.end(), 0);
        if (sum % 3 != 0) {
            return false;
        }
        int count = 0, subSum = 0;
        for (int i = 0; i < A.size(); i++) {
            subSum += A[i];
            if (subSum == sum / 3) {
                count++;
                subSum = 0;
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    string gcdOfStrings(string str1, string str2) {
        if (str1 + str2 != str2 + str1) return "";
        return str1.substr(0, gcd((int) str1.length(), (int) str2.length())); // __gcd() 为c++自带的求最大公约数的函数
    }

    int majorityElement(vector<int> &nums) {
        int times = 0;
        int pos = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] == nums[pos]) {
                times++;
            } else {
                times--;
            }
            if (times < 0) {
                pos = i;
                times = 1;
            }
        }
        if (times >= 0) {
            return nums[pos];
        }
        return -1;
    }

    vector<vector<int>> findSubsequences(vector<int> &nums) {
        vector<vector<int> > ans;

    }

    int longestPalindrome(string s) {
        unordered_map<char, int> map;
        for (int i = 0; i < s.length(); i++) {
            if (map.find(s[i]) != map.end()) {
                map[s[i]] += 1;
            } else {
                map[s[i]] = 1;
            }
        }
        unordered_map<char, int>::iterator it;
        int odd = 0, even = 0;
        int even_flag = 0;
        int odd_flag = 0;
        for (it = map.begin(); it != map.end(); it++) {
            if (it->second % 2 == 0) {
                even_flag += 1;
                even += it->second;
            } else {
                odd_flag += 1;
                odd += it->second;
            }
        }
        if (odd_flag > 0 && even_flag == 0) {
            return odd - odd_flag + 1;
        } else if (odd_flag > 0 && even_flag > 0) {
            return even + odd - odd_flag + 1;
        } else {
            return even;
        }
    }

    int wiggleMaxLength(vector<int> &nums) {
        int n = nums.size();
        if (n <= 1) return n;
        int length = 0;
        int diff[n];
        memset(diff, 0, n);

        for (int i = 1; i < n; ++i) {
            int d = nums[i] - nums[i - 1];
            if (d != 0) {
                diff[length++] = nums[i] - nums[i - 1]; // 消除重复带来的影响, diff from 0~length-1
            }
        }
        if (length == 0) return 1;    //如果len==0，N >= 2证明相邻的N个数重复是重复的
        int dp[length];
        memset(dp, 0, length);
        //边界
        dp[0] = 1;
        // 状态转移方程
        for (int j = 1; j < length; ++j) {
            if (diff[j] * diff[j - 1] < 0) {
                dp[j] = dp[j - 1] + 1;
            } else {
                dp[j] = dp[j - 1];
            }
        }
        return dp[length - 1] + 1;
    }

    vector<int> movesToStamp(string stamp, string target) {
        int step = 0;
        char start = stamp[0];
        int target_start = 0;
        vector<int> ans;
        while (step < 10 && target_start != -1) {
            for (int i = target_start; i < target.length(); ++i) {
                if (start == target[i]) {
                    ans.push_back(i);
                    if (stamp == target.substr(i, stamp.length())) {
                        target_start = -1;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    int lengthOfLIS(vector<int> &nums) {
        int n = nums.size();
        if (n <= 1) return n;
        vector<int> dp(n, 1);
        // 边界 初始dp都为1
        // dp[i] 表⽰以 nums[i] 这个数结尾的最⻓递增⼦序列的⻓度。
        // 动态转移方程
        for (int j = 0; j < n; ++j) {
            for (int i = j + 1; i < n; ++i) {
                if (nums[i] > nums[j]) {
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            }
        }
        // 找出最长递增序列长度
        int res = 0;
        for (auto x: dp) {
            res = max(res, x);
        }
        return res;
    }

    bool increasingTriplet(vector<int> &nums) {
        // O(n^2) 解法
        int n = nums.size();
        if (n <= 2) return false;
        vector<int> dp(n, 1);
        // 边界 初始dp都为1
        // dp[i] 表⽰以 nums[i] 这个数结尾的最⻓递增⼦序列的⻓度。
        // 动态转移方程
        for (int j = 0; j < n; ++j) {
            for (int i = j + 1; i < n; ++i) {
                if (nums[i] > nums[j]) {
                    dp[i] = max(dp[i], dp[j] + 1);
                }
                if (dp[i] >= 3) {
                    return true;
                }
            }
        }
        return false;

        // O(n) 解法
        int small = INT_MAX, mid = INT_MAX;
        for (auto x : nums) {
            if (small >= x) {
                small = x;
            } else if (mid >= x) {
                mid = x;
            } else {
                return true;
            }
        }
        return false;

        // 思路三：前后遍历，使用两个数组
        // small[i]保存前i个最小的值
        // big[i]保存后i个最小的值

    }

    int numDistinct(string s, string t) {

    }

    int distinctSubseqII(string S) {
        int ans;
        int model = 10^9 + 7;


    }
    int threeSumMulti(vector<int>& A, int target) {
        int ans;
        int model = 10^9 + 7;

    }

    bool isSubsequence(string s, string t) {
        int end = s.size();
        if (end < 1) {
            return true;
        }

        int start = 0;
        for (int j = 0; j < t.size(); ++j) {
            if(s[start] == t[j]){
                start++;
                if (end == start){
                    return true;
                }
            }
        }
        return false;
    }

}
