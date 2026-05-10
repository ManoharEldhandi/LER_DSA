
/*
 * ============================================================================
 * BINARY SEARCH -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Standard Binary Search
     * -----------------------------------------------------------------------
     *
     * LeetCode #704 -- Binary Search
     *
     * Given a sorted array and a target, return the index of the target
     * or -1 if it does not exist.
     *
     * Time: O(log n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Left Boundary (Find First Position >= Target)
     * -----------------------------------------------------------------------
     *
     * LeetCode #35 -- Search Insert Position
     *
     * Given a sorted array and a target, return the index where the target
     * should be inserted to keep the array sorted.
     *
     * This is equivalent to finding the leftmost position where the element
     * is >= target. This pattern is incredibly useful.
     *
     * Example: [1, 3, 5, 6], target = 5 -> 2
     * Example: [1, 3, 5, 6], target = 2 -> 1 (insert between 1 and 3)
     *
     * THOUGHT PROCESS:
     * We want the first index where nums[index] >= target.
     * If nums[mid] < target, the answer is to the right.
     * If nums[mid] >= target, mid could be the answer, but there might be
     * an earlier one, so we continue searching left.
     *
     * Time: O(log n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // Note: right is one past the end

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid; // mid could be the answer
            }
        }

        return left;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Search in Rotated Sorted Array
     * -----------------------------------------------------------------------
     *
     * LeetCode #33 -- Search in Rotated Sorted Array
     *
     * A sorted array has been rotated at some pivot (e.g., [4,5,6,7,0,1,2]).
     * Given a target, return its index or -1.
     *
     * Example: nums = [4,5,6,7,0,1,2], target = 0 -> 4
     *
     * THOUGHT PROCESS:
     * Even though the array is rotated, at least one half (left or right)
     * is always sorted. We can determine which half is sorted by comparing
     * nums[left] with nums[mid].
     *
     * If the left half is sorted (nums[left] <= nums[mid]):
     *   - If target is in the left half range, search there.
     *   - Otherwise, search the right half.
     *
     * If the right half is sorted (nums[mid] <= nums[right]):
     *   - If target is in the right half range, search there.
     *   - Otherwise, search the left half.
     *
     * Time: O(log n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int searchRotated(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // Target is in the sorted left half
                } else {
                    left = mid + 1;  // Target is in the right half
                }
            }
            // Right half is sorted
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;  // Target is in the sorted right half
                } else {
                    right = mid - 1; // Target is in the left half
                }
            }
        }

        return -1;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Find Minimum in Rotated Sorted Array
     * -----------------------------------------------------------------------
     *
     * LeetCode #153 -- Find Minimum in Rotated Sorted Array
     *
     * Example: [3,4,5,1,2] -> 1
     *
     * THOUGHT PROCESS:
     * The minimum element is the point where the rotation happened.
     * Compare nums[mid] with nums[right]:
     *   - If nums[mid] > nums[right], the minimum is in the right half.
     *   - If nums[mid] <= nums[right], the minimum is in the left half
     *     (including mid).
     *
     * Time: O(log n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;   // Minimum is to the right of mid
            } else {
                right = mid;      // Minimum could be mid itself
            }
        }

        return nums[left];
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Binary Search on Answer
     * -----------------------------------------------------------------------
     *
     * LeetCode #875 -- Koko Eating Bananas
     *
     * Koko has piles of bananas. She can eat at most k bananas per hour from
     * one pile. If the pile has fewer than k bananas, she finishes it and
     * waits. She has h hours. Find the minimum eating speed k to finish all
     * bananas within h hours.
     *
     * Example: piles = [3,6,7,11], h = 8 -> 4
     *
     * THOUGHT PROCESS:
     * This is not about searching in an array. It is about searching for the
     * right "answer" in a range.
     *
     * The minimum speed is 1. The maximum speed is max(piles) (finish the
     * biggest pile in 1 hour). We binary search over this range.
     *
     * For a given speed k, we can calculate how many hours it takes to eat
     * all bananas. If hours <= h, the speed is fast enough (try slower).
     * If hours > h, the speed is too slow (try faster).
     *
     * This "binary search on answer" pattern appears in many problems.
     *
     * Time: O(n * log(max(piles))), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canFinish(piles, mid, h)) {
                right = mid;       // This speed works, try slower
            } else {
                left = mid + 1;    // Too slow, need to eat faster
            }
        }

        return left;
    }

    private static boolean canFinish(int[] piles, int speed, int hours) {
        int totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + speed - 1) / speed; // Ceiling division
        }
        return totalHours <= hours;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Search a 2D Matrix
     * -----------------------------------------------------------------------
     *
     * LeetCode #74 -- Search a 2D Matrix
     *
     * Each row is sorted. The first integer of each row is greater than the
     * last integer of the previous row. Search for a target.
     *
     * THOUGHT PROCESS:
     * The entire matrix, read row by row, is one sorted array. We can treat
     * it as a 1D sorted array of size m*n and do standard binary search.
     *
     * To convert a 1D index to 2D:
     *   row = index / numCols
     *   col = index % numCols
     *
     * Time: O(log(m * n)), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }


    public static void main(String[] args) {

        System.out.println("--- Standard Binary Search ---");
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 9));  // 4
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2));  // -1

        System.out.println("\n--- Search Insert Position ---");
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));  // 2
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));  // 1

        System.out.println("\n--- Search in Rotated Sorted Array ---");
        System.out.println(searchRotated(new int[]{4,5,6,7,0,1,2}, 0));  // 4
        System.out.println(searchRotated(new int[]{4,5,6,7,0,1,2}, 3));  // -1

        System.out.println("\n--- Find Min in Rotated Array ---");
        System.out.println(findMin(new int[]{3,4,5,1,2}));  // 1

        System.out.println("\n--- Koko Eating Bananas ---");
        System.out.println(minEatingSpeed(new int[]{3,6,7,11}, 8));  // 4

        System.out.println("\n--- Search 2D Matrix ---");
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(matrix, 3));   // true
        System.out.println(searchMatrix(matrix, 13));  // false
    }
}
