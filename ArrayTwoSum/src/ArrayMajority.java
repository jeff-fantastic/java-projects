/*
    ARRAY MAJORITY VALUE ALGO
 */

import java.util.HashMap;

public class ArrayMajority {
    public static int[] list = { 9, 8, 8, 3, 3, 3, 3, 3, 3, 2, 3, 1, 3, 3, 3, 3 };

    public static void main(String[] args) {
        int best = getMajority(list);
        System.out.println("The best value in list is " + best);
    }

    // Returns the majority of an array, or -1 if there is no majority.
    public static int getMajority(int[] arr) {
        // Create hash table
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

        // Iterate and determine the best count
        int count = 0;
        for (int i : arr) {
            // If the map already contains this value, increment its count
            if (map.containsKey(arr[i])) {
                count = map.get(arr[i]) + 1;
                map.put(arr[i], count);
            // Otherwise, create a new entry with a count of 1
            } else {
                map.put(arr[i], 1);
            }
            // Check to see if the current number is the majority
            if (map.get(arr[i]) > arr.length / 2) {
                return i;
            }
        }
        // If no majority element, return -1
        return -1;
    }
}
