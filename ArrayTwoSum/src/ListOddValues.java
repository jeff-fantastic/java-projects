/*
    LIST ODD VALUES ALGO
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListOddValues {
    public static int[] list = { 0, 0, 0, 2, 2, 1, 1, 1 };

    public static void main(String[] args) {
        List<Integer> result = getOddValues(list);
        System.out.println("The odd values are " + result.toString());
    }

    // Iterates through an array and returns odd values.
    private static List<Integer> getOddValues(int[] arr) {
        // Create hash map
        HashMap<Integer,Boolean> map = new HashMap<Integer,Boolean>();

        // Iterate over array and mark odd
        for (int j : arr) {
            // Flip value if already in hash map
            if (map.containsKey(j)) {
                map.put(j, !map.get(j));
                // Otherwise put number into map starting off as odd (true)
            } else {
                map.put(j, true);
            }
        }

        // Iterate over hashmap and return numbers marked as odd
        Integer[] keys = map.keySet().toArray(new Integer[0]);
        List<Integer> odds = new ArrayList<>();
        for (Integer key : keys) {
            if (map.get(key)) {
                odds.add(key);
            }
        }

        // Return odd keys
        return odds;
    }
}
