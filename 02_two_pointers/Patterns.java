import java.util.*;

/*
 * ============================================================================
 * TWO POINTERS -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Opposite Direction -- Palindrome Check
     * -----------------------------------------------------------------------
     *
     * LeetCode #125 -- Valid Palindrome
     *
     * A phrase is a palindrome if, after converting to lowercase and removing
     * non-alphanumeric characters, it reads the same forward and backward.
     *
     * Example: "A man, a plan, a canal: Panama" -> true
     *
     * THOUGHT PROCESS:
     * Put one pointer at the start, one at the end. Compare characters.
     * Skip anything that is not a letter or digit. If all pairs match,
     * it is a palindrome.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Skip non-alphanumeric from the left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric from the right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Opposite Direction -- Two Sum on Sorted Array
     * -----------------------------------------------------------------------
     *
     * LeetCode #167 -- Two Sum II (Input Array is Sorted)
     *
     * Given a sorted array and a target, find two numbers that add up to
     * the target. Return their 1-indexed positions.
     *
     * Example: numbers = [2, 7, 11, 15], target = 9 -> [1, 2]
     *
     * THOUGHT PROCESS:
     * Because the array is sorted, we can use two pointers:
     *   - If the sum is too small, move left pointer right (increase sum)
     *   - If the sum is too big, move right pointer left (decrease sum)
     *   - If the sum matches, we found it
     *
     * Why does this work? When we move left right, we are guaranteed to
     * increase the sum (because the array is sorted). When we move right
     * left, we are guaranteed to decrease it.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int[] twoSumSorted(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[] { left + 1, right + 1 }; // 1-indexed
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[] {};
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Fix One + Two Pointers -- 3Sum
     * -----------------------------------------------------------------------
     *
     * LeetCode #15 -- 3Sum
     *
     * Given an integer array, return all unique triplets that sum to zero.
     *
     * Example: [-1, 0, 1, 2, -1, -4] -> [[-1,-1,2], [-1,0,1]]
     *
     * THOUGHT PROCESS:
     * Finding three numbers that sum to zero is hard with brute force (O(n^3)).
     *
     * Better approach:
     * 1. Sort the array.
     * 2. Fix the first number (iterate with i).
     * 3. Use two pointers (left and right) to find two numbers that sum to
     *    the negative of the first number.
     * 4. Skip duplicates at every level to avoid duplicate triplets.
     *
     * Sorting takes O(n log n). The outer loop is O(n). The inner two-pointer
     * scan is O(n). Total: O(n^2).
     *
     * Time: O(n^2), Space: O(1) excluding the output
     * -----------------------------------------------------------------------
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // If the smallest number is positive, no triplet can sum to zero
            if (nums[i] > 0) {
                break;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for the second element
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for the third element
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Opposite Direction -- Container With Most Water
     * -----------------------------------------------------------------------
     *
     * LeetCode #11 -- Container With Most Water
     *
     * Given an array of heights, find two lines that together with the x-axis
     * form a container that holds the most water.
     *
     * Example: [1,8,6,2,5,4,8,3,7] -> 49
     *
     * THOUGHT PROCESS:
     * The area between two lines is:
     *   area = min(height[left], height[right]) * (right - left)
     *
     * Start with the widest container (left = 0, right = n-1). The only way
     * to potentially find a larger area is to find taller lines, so we move
     * the shorter pointer inward.
     *
     * Why move the shorter one? Because moving the taller one can only
     * decrease or maintain the area (the width decreases, and the height is
     * still limited by the shorter line). Moving the shorter one at least
     * gives us a chance of finding a taller line.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Same Direction -- Remove Duplicates
     * -----------------------------------------------------------------------
     *
     * LeetCode #26 -- Remove Duplicates from Sorted Array
     *
     * Given a sorted array, remove duplicates in-place. Return the number
     * of unique elements.
     *
     * Example: [1,1,2] -> 2, and the array becomes [1,2,...]
     *
     * THOUGHT PROCESS:
     * Use a slow pointer to mark where the next unique element should go.
     * Use a fast pointer to scan through the array.
     * When fast finds a new unique element (different from what slow points to),
     * advance slow and copy the element there.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }

        return slow + 1; // Length of unique portion
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Dutch National Flag -- Three-Way Partition
     * -----------------------------------------------------------------------
     *
     * LeetCode #75 -- Sort Colors
     *
     * Given an array with elements 0, 1, and 2 (representing red, white,
     * and blue), sort them in-place in one pass.
     *
     * Example: [2, 0, 2, 1, 1, 0] -> [0, 0, 1, 1, 2, 2]
     *
     * THOUGHT PROCESS:
     * Use three pointers: low, mid, high.
     *   - Everything before low is 0.
     *   - Everything after high is 2.
     *   - mid scans from low to high.
     *
     * When mid sees 0: swap with low, advance both low and mid.
     * When mid sees 1: just advance mid (it is in the right section).
     * When mid sees 2: swap with high, decrease high. Do NOT advance mid
     *   because the swapped element needs to be checked.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
                // Do not advance mid -- need to check the swapped element
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: Opposite Direction -- Trapping Rain Water
     * -----------------------------------------------------------------------
     *
     * LeetCode #42 -- Trapping Rain Water
     *
     * Given an elevation map (array of non-negative integers representing
     * bar heights), compute how much water can be trapped after raining.
     *
     * Example: [0,1,0,2,1,0,1,3,2,1,2,1] -> 6
     *
     * THOUGHT PROCESS:
     * At any position, the water level is determined by the minimum of:
     *   - The tallest bar to its left
     *   - The tallest bar to its right
     * Water at position i = min(maxLeft, maxRight) - height[i]
     *
     * Brute force: For each position, scan left and right to find max. O(n^2).
     *
     * Two pointer approach:
     *   - Keep leftMax and rightMax.
     *   - Process from the side with the smaller max, because that side
     *     determines the water level.
     *   - If leftMax < rightMax, process left side (water is limited by leftMax).
     *   - Otherwise, process right side.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int trap(int[] height) {
        if (height == null || height.length < 3) return 0;

        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int water = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
            }
        }

        return water;
    }


    public static void main(String[] args) {

        System.out.println("--- Valid Palindrome ---");
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));  // true
        System.out.println(isPalindrome("race a car"));  // false

        System.out.println("\n--- Two Sum II ---");
        System.out.println(Arrays.toString(twoSumSorted(new int[]{2,7,11,15}, 9)));  // [1,2]

        System.out.println("\n--- 3Sum ---");
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));

        System.out.println("\n--- Container With Most Water ---");
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));  // 49

        System.out.println("\n--- Remove Duplicates ---");
        int[] dupes = {1, 1, 2};
        System.out.println(removeDuplicates(dupes));  // 2

        System.out.println("\n--- Sort Colors ---");
        int[] colors = {2, 0, 2, 1, 1, 0};
        sortColors(colors);
        System.out.println(Arrays.toString(colors));  // [0,0,1,1,2,2]

        System.out.println("\n--- Trapping Rain Water ---");
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));  // 6
    }
}
