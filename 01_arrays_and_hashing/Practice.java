import java.util.*;

/*
 * ============================================================================
 * ARRAYS AND HASHING -- Practice Problems
 * ============================================================================
 *
 * Instructions:
 *   1. Read the problem description below each method.
 *   2. Open the LeetCode link and read the full problem.
 *   3. Use the UMPIRE method (see PROBLEM_SOLVING_FRAMEWORK.md).
 *   4. Write your solution in the method body.
 *   5. Run this file to test against the provided test cases.
 *   6. Write the time and space complexity in the comment.
 *
 * Do NOT look at solutions until you have spent at least 20 minutes trying.
 * If you are stuck, re-read the Concepts.md and Patterns.java files for hints.
 *
 * ============================================================================
 */


public class Practice {

    /*
     * Problem 1: Contains Duplicate
     * LeetCode #217
     * https://leetcode.com/problems/contains-duplicate/
     *
     * Given an integer array, return true if any value appears at least twice.
     *
     * Example: [1, 2, 3, 1] -> true
     * Example: [1, 2, 3, 4] -> false
     *
     * Hint: What data structure gives you O(1) duplicate detection?
     *
     * Time: O(?)  Space: O(?)
     */
    public static boolean containsDuplicate(int[] nums) {
        // YOUR CODE HERE

        return false;
    }


    /*
     * Problem 2: Move Zeroes
     * LeetCode #283
     * https://leetcode.com/problems/move-zeroes/
     *
     * Move all zeros to the end of the array while maintaining the relative
     * order of the non-zero elements. Do this in-place.
     *
     * Example: [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
     *
     * Hint: Use a pointer to track where the next non-zero should go.
     *
     * Time: O(?)  Space: O(?)
     */
    public static void moveZeroes(int[] nums) {
        // YOUR CODE HERE

    }


    /*
     * Problem 3: Merge Sorted Array
     * LeetCode #88
     * https://leetcode.com/problems/merge-sorted-array/
     *
     * You are given two sorted arrays nums1 and nums2. Merge nums2 into nums1
     * as one sorted array. nums1 has enough space at the end (filled with 0s).
     *
     * Example: nums1 = [1,2,3,0,0,0], m=3, nums2 = [2,5,6], n=3
     *          Result: [1,2,2,3,5,6]
     *
     * Hint: Start filling from the END of nums1. That way you do not
     *       overwrite elements you still need.
     *
     * Time: O(?)  Space: O(?)
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // YOUR CODE HERE

    }


    /*
     * Problem 4: Top K Frequent Elements
     * LeetCode #347
     * https://leetcode.com/problems/top-k-frequent-elements/
     *
     * Given an integer array and an integer k, return the k most frequent
     * elements. You may return the answer in any order.
     *
     * Example: nums = [1,1,1,2,2,3], k = 2 -> [1, 2]
     *
     * Hint: Count frequencies with a HashMap, then use a Heap or Bucket Sort.
     *
     * Time: O(?)  Space: O(?)
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // YOUR CODE HERE

        return new int[]{};
    }


    /*
     * Problem 5: Set Matrix Zeroes
     * LeetCode #73
     * https://leetcode.com/problems/set-matrix-zeroes/
     *
     * Given an m x n matrix, if an element is 0, set its entire row and
     * column to 0. Do it in place.
     *
     * Example: [[1,1,1],[1,0,1],[1,1,1]] -> [[1,0,1],[0,0,0],[1,0,1]]
     *
     * Hint: Use the first row and first column as markers to avoid O(m*n)
     * extra space. Be careful about the order of operations.
     *
     * Time: O(?)  Space: O(?)
     */
    public static void setZeroes(int[][] matrix) {
        // YOUR CODE HERE

    }


    /*
     * Problem 6: Rotate Array
     * LeetCode #189
     * https://leetcode.com/problems/rotate-array/
     *
     * Given an array, rotate it to the right by k steps.
     *
     * Example: [1,2,3,4,5,6,7], k=3 -> [5,6,7,1,2,3,4]
     *
     * Hint: There is a clever trick using three reverses.
     *   1. Reverse the entire array.
     *   2. Reverse the first k elements.
     *   3. Reverse the remaining elements.
     *
     * Time: O(?)  Space: O(?)
     */
    public static void rotate(int[] nums, int k) {
        // YOUR CODE HERE

    }


    /*
     * Problem 7: Spiral Matrix
     * LeetCode #54
     * https://leetcode.com/problems/spiral-matrix/
     *
     * Given an m x n matrix, return all elements in spiral order.
     *
     * Example: [[1,2,3],[4,5,6],[7,8,9]] -> [1,2,3,6,9,8,7,4,5]
     *
     * Hint: Use four boundaries (top, bottom, left, right) and shrink
     * them after processing each edge.
     *
     * Time: O(?)  Space: O(?)
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        // YOUR CODE HERE

        return new ArrayList<>();
    }


    // -----------------------------------------------------------------------
    // TEST CASES -- Do not modify below this line
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        // Test Contains Duplicate
        total++;
        if (containsDuplicate(new int[]{1, 2, 3, 1}) == true) {
            System.out.println("PASS: Contains Duplicate test 1");
            passed++;
        } else {
            System.out.println("FAIL: Contains Duplicate test 1");
        }

        total++;
        if (containsDuplicate(new int[]{1, 2, 3, 4}) == false) {
            System.out.println("PASS: Contains Duplicate test 2");
            passed++;
        } else {
            System.out.println("FAIL: Contains Duplicate test 2");
        }

        // Test Move Zeroes
        total++;
        int[] mz = {0, 1, 0, 3, 12};
        moveZeroes(mz);
        if (Arrays.equals(mz, new int[]{1, 3, 12, 0, 0})) {
            System.out.println("PASS: Move Zeroes");
            passed++;
        } else {
            System.out.println("FAIL: Move Zeroes - got " + Arrays.toString(mz));
        }

        // Test Merge Sorted Array
        total++;
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        merge(nums1, 3, new int[]{2, 5, 6}, 3);
        if (Arrays.equals(nums1, new int[]{1, 2, 2, 3, 5, 6})) {
            System.out.println("PASS: Merge Sorted Array");
            passed++;
        } else {
            System.out.println("FAIL: Merge Sorted Array - got " + Arrays.toString(nums1));
        }

        // Test Top K Frequent
        total++;
        int[] topk = topKFrequent(new int[]{1,1,1,2,2,3}, 2);
        Arrays.sort(topk);
        if (Arrays.equals(topk, new int[]{1, 2})) {
            System.out.println("PASS: Top K Frequent");
            passed++;
        } else {
            System.out.println("FAIL: Top K Frequent - got " + Arrays.toString(topk));
        }

        // Test Rotate Array
        total++;
        int[] rot = {1, 2, 3, 4, 5, 6, 7};
        rotate(rot, 3);
        if (Arrays.equals(rot, new int[]{5, 6, 7, 1, 2, 3, 4})) {
            System.out.println("PASS: Rotate Array");
            passed++;
        } else {
            System.out.println("FAIL: Rotate Array - got " + Arrays.toString(rot));
        }

        System.out.println("\n" + passed + " / " + total + " tests passed.");
    }
}
