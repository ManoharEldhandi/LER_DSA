import java.util.*;

/*
 * ============================================================================
 * SLIDING WINDOW -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Fixed-Size Window
     * -----------------------------------------------------------------------
     *
     * LeetCode #643 -- Maximum Average Subarray I
     *
     * Given an array and an integer k, find the contiguous subarray of
     * length k that has the maximum average value.
     *
     * Example: nums = [1,12,-5,-6,50,3], k = 4 -> 12.75
     *          The subarray [12,-5,-6,50] has average 51/4 = 12.75.
     *
     * THOUGHT PROCESS:
     * Compute the sum of the first k elements. Then slide the window:
     * add the new element on the right, subtract the element that fell off
     * the left. Track the maximum sum.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static double findMaxAverage(int[] nums, int k) {
        // Compute sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        int maxSum = windowSum;

        // Slide the window
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k]; // Add new, remove old
            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Variable Window with HashSet
     * -----------------------------------------------------------------------
     *
     * LeetCode #3 -- Longest Substring Without Repeating Characters
     *
     * Given a string, find the length of the longest substring that contains
     * no duplicate characters.
     *
     * Example: "abcabcbb" -> 3 (the substring "abc")
     *
     * THOUGHT PROCESS:
     * We want a window where every character is unique. Expand the window
     * by moving right. If we encounter a character that is already in the
     * window, shrink from the left until the duplicate is removed.
     *
     * We use a HashSet to track which characters are in the current window.
     *
     * Time: O(n) -- each character is added and removed at most once
     * Space: O(min(n, 26)) -- at most 26 lowercase letters in the set
     * -----------------------------------------------------------------------
     */
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> window = new HashSet<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            // Shrink window until the duplicate is removed
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }

            window.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Variable Window with Frequency Count
     * -----------------------------------------------------------------------
     *
     * LeetCode #424 -- Longest Repeating Character Replacement
     *
     * Given a string and an integer k, you can change at most k characters
     * to any other character. Find the length of the longest substring
     * containing the same letter after performing at most k replacements.
     *
     * Example: s = "AABABBA", k = 1 -> 4
     *          Change one B to A to get "AAAAABA" -> "AAAA" has length 4.
     *
     * THOUGHT PROCESS:
     * In any valid window, the number of characters we need to replace is:
     *   (window size) - (count of the most frequent character in the window)
     *
     * This must be <= k. If it exceeds k, the window is invalid and we
     * shrink it from the left.
     *
     * We do not need to update maxFreq when shrinking. Even if it becomes
     * slightly stale, it will never produce a wrong answer -- it just means
     * we might not shrink as aggressively, but the maximum valid window
     * length is still tracked correctly.
     *
     * Time: O(n), Space: O(26) = O(1)
     * -----------------------------------------------------------------------
     */
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int result = 0;

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);

            // Window is invalid if we need to replace more than k characters
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Fixed Window with Frequency Matching
     * -----------------------------------------------------------------------
     *
     * LeetCode #567 -- Permutation in String
     *
     * Given two strings s1 and s2, return true if s2 contains a permutation
     * of s1 as a substring.
     *
     * Example: s1 = "ab", s2 = "eidbaooo" -> true ("ba" is a permutation of "ab")
     *
     * THOUGHT PROCESS:
     * A permutation has the same character frequencies. So we need to find a
     * window in s2 of size s1.length() that has the same frequency as s1.
     *
     * Count the frequencies of s1. Slide a window of that size over s2.
     * At each position, check if the window frequencies match.
     *
     * Optimization: Instead of comparing all 26 frequencies each time,
     * track how many characters have matching frequencies.
     *
     * Time: O(n), Space: O(1) -- frequency arrays of size 26
     * -----------------------------------------------------------------------
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26];
        int[] windowCount = new int[26];

        // Count frequencies of s1 and the initial window
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            windowCount[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(s1Count, windowCount)) return true;

        // Slide the window
        for (int i = s1.length(); i < s2.length(); i++) {
            windowCount[s2.charAt(i) - 'a']++;                   // Add new character
            windowCount[s2.charAt(i - s1.length()) - 'a']--;      // Remove old character

            if (Arrays.equals(s1Count, windowCount)) return true;
        }

        return false;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Variable Window -- Minimum Size Subarray Sum
     * -----------------------------------------------------------------------
     *
     * LeetCode #209 -- Minimum Size Subarray Sum
     *
     * Given an array of positive integers and a target, find the minimal
     * length of a contiguous subarray whose sum is greater than or equal
     * to the target. Return 0 if no such subarray exists.
     *
     * Example: target = 7, nums = [2,3,1,2,4,3] -> 2
     *          The subarray [4,3] has sum >= 7 and length 2.
     *
     * THOUGHT PROCESS:
     * Expand the window by adding elements until the sum >= target. Then
     * shrink from the left to find the minimum length that still satisfies
     * the condition.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Variable Window with Frequency Matching (HARD)
     * -----------------------------------------------------------------------
     *
     * LeetCode #76 -- Minimum Window Substring
     *
     * Given strings s and t, find the minimum window substring of s that
     * contains all characters of t (including duplicates).
     *
     * Example: s = "ADOBECODEBANC", t = "ABC" -> "BANC"
     *
     * THOUGHT PROCESS:
     * 1. Count the frequency of each character in t. This is what we "need".
     * 2. Expand the window by moving right. Track what we "have" in the window.
     * 3. When we have all required characters, try to shrink from the left
     *    to find the smallest valid window.
     * 4. Track the minimum window found.
     *
     * We use a variable "formed" to count how many unique characters in t
     * have their required frequency met in the current window.
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        // Count what we need
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> have = new HashMap<>();
        int formed = 0;
        int required = need.size(); // Number of unique chars in t that must be satisfied

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {
            // Add the right character to our window
            char c = s.charAt(right);
            have.put(c, have.getOrDefault(c, 0) + 1);

            // Check if this character's frequency requirement is met
            if (need.containsKey(c) && have.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }

            // Try to shrink the window
            while (formed == required) {
                // Update the answer
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                // Remove the left character
                char leftChar = s.charAt(left);
                have.put(leftChar, have.get(leftChar) - 1);
                if (need.containsKey(leftChar) && have.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }


    public static void main(String[] args) {

        System.out.println("--- Max Average Subarray ---");
        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));  // 12.75

        System.out.println("\n--- Longest Substring Without Repeating ---");
        System.out.println(lengthOfLongestSubstring("abcabcbb"));  // 3
        System.out.println(lengthOfLongestSubstring("bbbbb"));     // 1

        System.out.println("\n--- Character Replacement ---");
        System.out.println(characterReplacement("AABABBA", 1));  // 4

        System.out.println("\n--- Permutation in String ---");
        System.out.println(checkInclusion("ab", "eidbaooo"));  // true
        System.out.println(checkInclusion("ab", "eidboaooo")); // false

        System.out.println("\n--- Min Size Subarray Sum ---");
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));  // 2

        System.out.println("\n--- Minimum Window Substring ---");
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));  // "BANC"
    }
}
