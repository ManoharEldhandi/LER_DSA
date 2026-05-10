import java.util.*;

/*
 * ============================================================================
 * ARRAYS AND HASHING -- Core Patterns
 * ============================================================================
 *
 * This file contains the most important patterns for array and hashing
 * problems. Each solution includes:
 *   - A clear explanation of the problem
 *   - The thought process (how would you arrive at this in an interview?)
 *   - The brute force approach and why it is not good enough
 *   - The optimized approach with full explanation
 *   - Time and space complexity analysis
 *
 * Study each pattern carefully. Understand WHY each approach works, not
 * just the code. In an interview, you need to explain your reasoning.
 *
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: HashMap for Complement Lookup
     * -----------------------------------------------------------------------
     *
     * LeetCode #1 -- Two Sum
     *
     * Given an array of integers and a target, return the indices of the
     * two numbers that add up to the target.
     *
     * Example:
     *   Input:  nums = [2, 7, 11, 15], target = 9
     *   Output: [0, 1]
     *   Why:    nums[0] + nums[1] = 2 + 7 = 9
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * Brute force: Check every pair of numbers. Two nested loops.
     * Time: O(n^2), Space: O(1).
     * This is too slow for large inputs.
     *
     * Better approach: For each number, I need to know if (target - number)
     * exists somewhere in the array. Searching the array each time is O(n),
     * making it O(n^2) total. But if I store numbers in a HashMap, I can
     * check in O(1).
     *
     * So the idea is: iterate through the array. For each number, calculate
     * the complement (target - current number). Check if the complement is
     * already in the HashMap. If yes, we found our pair. If no, store the
     * current number in the HashMap and move on.
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); // value -> index

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        return new int[] {}; // No solution found
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: HashMap for Frequency Counting
     * -----------------------------------------------------------------------
     *
     * LeetCode #242 -- Valid Anagram
     *
     * Given two strings s and t, return true if t is an anagram of s.
     * An anagram uses the exact same characters with the exact same frequency.
     *
     * Example:
     *   Input:  s = "anagram", t = "nagaram"
     *   Output: true
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * If two strings are anagrams, they have the same character frequencies.
     * So I can count the frequency of each character in s, then subtract the
     * frequencies from t. If everything ends up at zero, they are anagrams.
     *
     * Alternative: Sort both strings and compare. O(n log n) time.
     * Better: Use a frequency array (since we only have 26 lowercase letters).
     *
     * Time: O(n), Space: O(1) -- at most 26 entries
     * -----------------------------------------------------------------------
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26]; // 26 lowercase letters

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) return false;
        }

        return true;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Kadane's Algorithm (Maximum Subarray)
     * -----------------------------------------------------------------------
     *
     * LeetCode #53 -- Maximum Subarray
     *
     * Given an integer array, find the contiguous subarray with the largest
     * sum and return that sum.
     *
     * Example:
     *   Input:  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     *   Output: 6
     *   Why:    The subarray [4, -1, 2, 1] has the largest sum = 6.
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * Brute force: Check every possible subarray, compute its sum. O(n^2).
     *
     * Kadane's insight: As I scan from left to right, at each position I
     * make a simple decision: should I extend the current subarray by adding
     * this element, or should I start a new subarray from this element?
     *
     * I extend if (currentSum + nums[i]) is greater than starting fresh
     * with just nums[i]. In other words, I only carry forward the previous
     * sum if it is positive (it helps). If the previous sum is negative,
     * it is better to start over.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int maxSubarray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either extend the current subarray or start a new one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Track Running Minimum/Maximum
     * -----------------------------------------------------------------------
     *
     * LeetCode #121 -- Best Time to Buy and Sell Stock
     *
     * Given an array where prices[i] is the price on day i, find the maximum
     * profit from one buy and one sell. You must buy before you sell.
     *
     * Example:
     *   Input:  [7, 1, 5, 3, 6, 4]
     *   Output: 5
     *   Why:    Buy on day 1 (price=1), sell on day 4 (price=6). Profit=5.
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * I need to find the maximum difference prices[j] - prices[i] where j > i.
     *
     * Brute force: Check all pairs. O(n^2).
     *
     * Better: As I scan left to right, I keep track of the minimum price I
     * have seen so far. At each day, I compute the profit if I sold today
     * (today's price minus the minimum so far) and keep track of the best.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Prefix and Suffix Products
     * -----------------------------------------------------------------------
     *
     * LeetCode #238 -- Product of Array Except Self
     *
     * Given an array nums, return an array where each element is the product
     * of all elements EXCEPT the one at that index. You cannot use division.
     *
     * Example:
     *   Input:  [1, 2, 3, 4]
     *   Output: [24, 12, 8, 6]
     *   Why:    result[0] = 2*3*4 = 24, result[1] = 1*3*4 = 12, etc.
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * For each index i, the answer is:
     *   (product of everything to the LEFT of i) * (product of everything to the RIGHT of i)
     *
     * Pass 1: Build an array of left products.
     *   leftProduct[i] = nums[0] * nums[1] * ... * nums[i-1]
     *
     * Pass 2: Build right products and multiply.
     *   rightProduct[i] = nums[i+1] * nums[i+2] * ... * nums[n-1]
     *
     * We can do this in O(1) extra space by using the output array itself
     * for left products, then multiplying in the right products.
     *
     * Time: O(n), Space: O(1) (output array does not count)
     * -----------------------------------------------------------------------
     */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Left pass: result[i] = product of all elements to the left of i
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Right pass: multiply by product of all elements to the right of i
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Prefix Sum + HashMap
     * -----------------------------------------------------------------------
     *
     * LeetCode #560 -- Subarray Sum Equals K
     *
     * Given an array of integers and a target k, return the total number of
     * contiguous subarrays whose sum equals k.
     *
     * Example:
     *   Input:  nums = [1, 1, 1], k = 2
     *   Output: 2
     *   Why:    Subarrays [1,1] starting at index 0 and [1,1] starting at index 1.
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * Key insight: If the prefix sum at index j minus the prefix sum at index i
     * equals k, then the subarray from i+1 to j has sum k.
     *
     * In other words: prefixSum[j] - prefixSum[i] = k
     * Which means:    prefixSum[i] = prefixSum[j] - k
     *
     * So for each position j, I need to know how many previous positions i
     * had a prefix sum equal to (current prefix sum - k). I can store
     * prefix sum frequencies in a HashMap.
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1); // Empty prefix has sum 0, occurs once

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            // How many previous prefix sums equal (prefixSum - k)?
            if (prefixMap.containsKey(prefixSum - k)) {
                count += prefixMap.get(prefixSum - k);
            }

            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: HashMap for Grouping
     * -----------------------------------------------------------------------
     *
     * LeetCode #49 -- Group Anagrams
     *
     * Given an array of strings, group the anagrams together.
     *
     * Example:
     *   Input:  ["eat", "tea", "tan", "ate", "nat", "bat"]
     *   Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * Anagrams, when sorted, produce the same string. "eat" sorted is "aet".
     * "tea" sorted is also "aet". So the sorted version is a natural key
     * for grouping.
     *
     * Use a HashMap where the key is the sorted string and the value is
     * a list of all original strings that sort to that key.
     *
     * Time: O(n * k log k) where n = number of strings, k = max string length
     * Space: O(n * k)
     * -----------------------------------------------------------------------
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 8: HashSet for Sequence Detection
     * -----------------------------------------------------------------------
     *
     * LeetCode #128 -- Longest Consecutive Sequence
     *
     * Given an unsorted array, find the length of the longest sequence of
     * consecutive integers. Must run in O(n) time (so sorting is not allowed).
     *
     * Example:
     *   Input:  [100, 4, 200, 1, 3, 2]
     *   Output: 4
     *   Why:    The sequence [1, 2, 3, 4] has length 4.
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * Put all numbers in a HashSet for O(1) lookups.
     *
     * Key insight: A number is the START of a consecutive sequence if
     * (number - 1) is NOT in the set. This way, we only start counting
     * from the beginning of each sequence, not from every number.
     *
     * For each sequence start, count how far it goes.
     *
     * Even though there are nested loops, each number is visited at most
     * twice (once in the outer loop, once in the while loop), so it is O(n).
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {
            // Only start counting if this is the beginning of a sequence
            if (!set.contains(num - 1)) {
                int current = num;
                int streak = 1;

                while (set.contains(current + 1)) {
                    current++;
                    streak++;
                }

                longest = Math.max(longest, streak);
            }
        }

        return longest;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 9: Boyer-Moore Voting Algorithm
     * -----------------------------------------------------------------------
     *
     * LeetCode #169 -- Majority Element
     *
     * Given an array, find the element that appears more than n/2 times.
     * You may assume the majority element always exists.
     *
     * Example:
     *   Input:  [2, 2, 1, 1, 1, 2, 2]
     *   Output: 2
     *
     * -----------------------------------------------------------------------
     * THOUGHT PROCESS:
     *
     * Brute force: Count frequency of each element with a HashMap. O(n) time
     * and O(n) space.
     *
     * Better: Boyer-Moore Voting. Imagine a crowd where more than half the
     * people support one candidate. If each supporter "cancels out" one
     * non-supporter, the majority candidate always survives.
     *
     * Keep a candidate and a count. For each element:
     *   - If count is 0, set this element as the new candidate.
     *   - If current element equals candidate, increment count.
     *   - Otherwise, decrement count.
     *
     * The survivor at the end is the majority element.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }


    // -----------------------------------------------------------------------
    // MAIN -- Run all examples
    // -----------------------------------------------------------------------
    public static void main(String[] args) {

        System.out.println("--- Two Sum ---");
        int[] result1 = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(result1));  // [0, 1]

        System.out.println("\n--- Valid Anagram ---");
        System.out.println(isAnagram("anagram", "nagaram"));  // true
        System.out.println(isAnagram("rat", "car"));           // false

        System.out.println("\n--- Maximum Subarray ---");
        System.out.println(maxSubarray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));  // 6

        System.out.println("\n--- Best Time to Buy and Sell ---");
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));  // 5

        System.out.println("\n--- Product Except Self ---");
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));  // [24,12,8,6]

        System.out.println("\n--- Subarray Sum Equals K ---");
        System.out.println(subarraySum(new int[]{1,1,1}, 2));  // 2

        System.out.println("\n--- Group Anagrams ---");
        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));

        System.out.println("\n--- Longest Consecutive Sequence ---");
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));  // 4

        System.out.println("\n--- Majority Element ---");
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}));  // 2
    }
}
