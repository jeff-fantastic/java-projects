/*
    ARRAY TWO SUM ALGO
 */

import java.util.Arrays;

public class ArrayTwoSum {
    public static int[] list = { 0, 1, 1, 7, 3, -2, -1, 4 };

    public static void main(String[] args) {
        boolean[] result = {
                list_has_sum(list, 100),
                list_has_sum(list, 2),
                list_has_sum(list, 5)
        };
        System.out.println(Arrays.toString(result));
    }

    public static boolean list_has_sum(int[] list, int tar) {
        // Declare and set variables
        int i, j;
        i = 0;
        j = (int)Arrays.stream(list).count() - 1;

        // Sort array
        Arrays.sort(list);

        // Iterate and find sum
        while (i < j) {
            if (list[i] + list[j] == tar)
                return true;
            else if (list[i] + list[j] < tar)
                i++;
            else
                j--;
        }

        return false;
    }
}