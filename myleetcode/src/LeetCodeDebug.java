import java.io.IOException;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/20 10:39 上午
 * @description：debug
 * @modifiedBy：
 * @version:
 */
public class LeetCodeDebug {
    // public static int[] stringToIntegerArray(String input) {
    //     input = input.trim();
    //     input = input.substring(1, input.length() - 1);
    //     if (input.length() == 0) {
    //         return new int[0];
    //     }
    //
    //     String[] parts = input.split(",");
    //     int[] output = new int[parts.length];
    //     for (int index = 0; index < parts.length; index++) {
    //         String part = parts[index].trim();
    //         output[index] = Integer.parseInt(part);
    //     }
    //     return output;
    // }
    //
    // public static String integerArrayToString(int[] nums, int length) {
    //     if (length == 0) {
    //
    //     }
    //
    //     String result = "";
    //     for (int index = 0; index < length; index++) {
    //         int number = nums[index];
    //         result += Integer.toString(number) + ", ";
    //     }
    //     return "[" + result.substring(0, result.length() - 2) + "]";
    // }
    //
    // public static String integerArrayToString(int[] nums) {
    //     return integerArrayToString(nums, nums.length);
    // }
    //
    //
    // public static void main(String[] args) throws IOException {
    //     // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    //     // String line;
    //     // while ((line = in.readLine()) != null) {
    //     //     int[] arr = stringToIntegerArray(line);
    //     //     line = in.readLine();
    //     //     int k = Integer.parseInt(line);
    //     //
    //     //     int[] ret = new Solution().getLeastNumbers_quickSort(arr, k);
    //     //
    //     //     String out = integerArrayToString(ret);
    //     //
    //     //     System.out.print(out);
    //     // }
    //     int[] arr = {0, 0, 0, 2, 0, 5};
    //     // int line = 2;
    //     // int k = Integer.parseInt(line);
    //     int k = 2;
    //     int[] ret = new Solution().getLeastNumbers_quickSort(arr, k);
    //     String out = integerArrayToString(ret);
    //     System.out.print(out);
    //
    // }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        // int[] nums  = new int[]{5,2,4,1,6};
        // int ret = new Solution().findNumberOfLIS(nums);
        // String out = String.valueOf(ret);
        // System.out.print(out);

        int ret = new Solution().myAtoi("4193 with words");
        String out = String.valueOf(ret);
        System.out.print(out);
    }
}
