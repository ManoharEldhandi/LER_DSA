import java.util.*;

/*
 * ============================================================================
 * DYNAMIC PROGRAMMING -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Fibonacci-Style DP -- Climbing Stairs
     * -----------------------------------------------------------------------
     *
     * LeetCode #70 -- Climbing Stairs
     *
     * You can climb 1 or 2 steps at a time. How many distinct ways can you
     * climb to step n?
     *
     * State: dp[i] = number of ways to reach step i
     * Recurrence: dp[i] = dp[i-1] + dp[i-2]
     *   (You can reach step i from step i-1 with 1 step, or from i-2 with 2 steps)
     * Base: dp[0] = 1, dp[1] = 1
     *
     * This is literally the Fibonacci sequence.
     *
     * Time: O(n), Space: O(1) with optimization
     * -----------------------------------------------------------------------
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n;

        int prev2 = 1; // dp[i-2]
        int prev1 = 2; // dp[i-1]

        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Take or Skip -- House Robber
     * -----------------------------------------------------------------------
     *
     * LeetCode #198 -- House Robber
     *
     * You are a robber planning to rob houses along a street. Each house has
     * money. You cannot rob two adjacent houses. Find the maximum money.
     *
     * Example: [1, 2, 3, 1] -> 4 (rob house 0 and house 2: 1 + 3 = 4)
     *
     * State: dp[i] = maximum money robbing from house 0 to house i
     * Recurrence: dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     *   Choice 1: Skip house i -> dp[i-1]
     *   Choice 2: Rob house i -> dp[i-2] + nums[i] (cannot rob i-1)
     * Base: dp[0] = nums[0], dp[1] = max(nums[0], nums[1])
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Unbounded Knapsack -- Coin Change
     * -----------------------------------------------------------------------
     *
     * LeetCode #322 -- Coin Change
     *
     * Given coin denominations and a target amount, find the fewest number
     * of coins needed. Return -1 if impossible.
     *
     * Example: coins = [1, 2, 5], amount = 11 -> 3 (5 + 5 + 1)
     *
     * State: dp[a] = minimum coins needed to make amount a
     * Recurrence: dp[a] = min(dp[a - coin] + 1) for each coin
     *   (For each coin, if I use it, I need 1 more coin plus whatever it
     *   takes to make the remaining amount)
     * Base: dp[0] = 0 (zero coins for zero amount)
     *
     * This is called "unbounded" because we can use each coin unlimited times.
     *
     * Time: O(amount * coins), Space: O(amount)
     * -----------------------------------------------------------------------
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Use amount+1 as "infinity"
        dp[0] = 0;

        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a) {
                    dp[a] = Math.min(dp[a], dp[a - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Longest Increasing Subsequence (LIS)
     * -----------------------------------------------------------------------
     *
     * LeetCode #300 -- Longest Increasing Subsequence
     *
     * Given an array, find the length of the longest strictly increasing
     * subsequence.
     *
     * Example: [10, 9, 2, 5, 3, 7, 101, 18] -> 4 ([2, 3, 7, 101])
     *
     * State: dp[i] = length of LIS ending at index i
     * Recurrence: dp[i] = max(dp[j] + 1) for all j < i where nums[j] < nums[i]
     * Base: dp[i] = 1 (each element is a subsequence of length 1)
     *
     * There is also an O(n log n) solution using binary search + patience sort,
     * but the O(n^2) DP version is what you should know first.
     *
     * Time: O(n^2), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLen = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: String DP -- Word Break
     * -----------------------------------------------------------------------
     *
     * LeetCode #139 -- Word Break
     *
     * Given a string and a dictionary, can the string be segmented into
     * dictionary words?
     *
     * Example: s = "leetcode", dict = ["leet", "code"] -> true
     *
     * State: dp[i] = true if s[0..i-1] can be segmented
     * Recurrence: dp[i] = true if there exists j < i such that
     *   dp[j] is true AND s[j..i-1] is in the dictionary
     * Base: dp[0] = true (empty string is valid)
     *
     * Time: O(n^2 * m) where m = max word length, Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: 2D Grid DP -- Unique Paths
     * -----------------------------------------------------------------------
     *
     * LeetCode #62 -- Unique Paths
     *
     * Robot starts at top-left of an m x n grid. Can only move right or down.
     * How many unique paths to the bottom-right?
     *
     * State: dp[i][j] = number of paths to reach cell (i, j)
     * Recurrence: dp[i][j] = dp[i-1][j] + dp[i][j-1]
     *   (Can arrive from above or from the left)
     * Base: dp[0][j] = 1 (only one way to reach any cell in first row)
     *       dp[i][0] = 1 (only one way to reach any cell in first column)
     *
     * Time: O(m * n), Space: O(m * n), can optimize to O(n)
     * -----------------------------------------------------------------------
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Base cases
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: Two String DP -- Longest Common Subsequence
     * -----------------------------------------------------------------------
     *
     * LeetCode #1143 -- Longest Common Subsequence
     *
     * Given two strings, find the length of their longest common subsequence.
     *
     * Example: text1 = "abcde", text2 = "ace" -> 3 ("ace")
     *
     * State: dp[i][j] = LCS of text1[0..i-1] and text2[0..j-1]
     * Recurrence:
     *   If text1[i-1] == text2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
     *   Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * Base: dp[0][j] = 0, dp[i][0] = 0
     *
     * Time: O(m * n), Space: O(m * n)
     * -----------------------------------------------------------------------
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 8: Edit Distance (Classic 2D String DP)
     * -----------------------------------------------------------------------
     *
     * LeetCode #72 -- Edit Distance
     *
     * Given two strings, find the minimum number of operations (insert,
     * delete, replace) to convert word1 to word2.
     *
     * State: dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
     * Recurrence:
     *   If chars match: dp[i][j] = dp[i-1][j-1] (no operation needed)
     *   Else: dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
     *     dp[i-1][j-1] + 1 = replace
     *     dp[i-1][j] + 1   = delete from word1
     *     dp[i][j-1] + 1   = insert into word1
     * Base: dp[i][0] = i (delete all), dp[0][j] = j (insert all)
     *
     * Time: O(m * n), Space: O(m * n)
     * -----------------------------------------------------------------------
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                   Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 9: 0/1 Knapsack -- Partition Equal Subset Sum
     * -----------------------------------------------------------------------
     *
     * LeetCode #416 -- Partition Equal Subset Sum
     *
     * Can you partition the array into two subsets with equal sum?
     *
     * THOUGHT PROCESS:
     * Total sum must be even. Then the problem becomes: can you find a subset
     * that sums to totalSum / 2? This is the 0/1 Knapsack problem.
     *
     * State: dp[j] = true if we can form sum j using some subset
     * Recurrence: dp[j] = dp[j] || dp[j - nums[i]]
     * Base: dp[0] = true
     *
     * Important: iterate j from target DOWN to nums[i] to avoid using
     * the same element twice.
     *
     * Time: O(n * target), Space: O(target)
     * -----------------------------------------------------------------------
     */
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            // Iterate backwards to avoid using the same element twice
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }


    public static void main(String[] args) {

        System.out.println("--- Climbing Stairs ---");
        System.out.println(climbStairs(5));  // 8

        System.out.println("\n--- House Robber ---");
        System.out.println(rob(new int[]{1,2,3,1}));  // 4

        System.out.println("\n--- Coin Change ---");
        System.out.println(coinChange(new int[]{1,2,5}, 11));  // 3

        System.out.println("\n--- Longest Increasing Subsequence ---");
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));  // 4

        System.out.println("\n--- Word Break ---");
        System.out.println(wordBreak("leetcode", Arrays.asList("leet","code")));  // true

        System.out.println("\n--- Unique Paths ---");
        System.out.println(uniquePaths(3, 7));  // 28

        System.out.println("\n--- LCS ---");
        System.out.println(longestCommonSubsequence("abcde", "ace"));  // 3

        System.out.println("\n--- Edit Distance ---");
        System.out.println(minDistance("horse", "ros"));  // 3

        System.out.println("\n--- Partition Equal Subset Sum ---");
        System.out.println(canPartition(new int[]{1,5,11,5}));  // true
    }
}
