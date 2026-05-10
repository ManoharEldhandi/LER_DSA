# Dynamic Programming -- Concepts

## What is Dynamic Programming?

Dynamic Programming (DP) is a method for solving problems by breaking them
into smaller overlapping subproblems, solving each subproblem once, and
storing the result to avoid redundant computation.

Think of it this way: if you have already computed fibonacci(5), why would
you compute it again? Store it and reuse it.


## When to Use DP

Look for these clues:
  - "Count the number of ways..."
  - "Find the minimum/maximum cost/path/sum..."
  - "Can you reach...?" or "Is it possible...?"
  - The problem has OVERLAPPING SUBPROBLEMS (same subproblem appears multiple times)
  - The problem has OPTIMAL SUBSTRUCTURE (optimal solution uses optimal sub-solutions)


## How to Solve a DP Problem (5-Step Process)

  1. DEFINE THE STATE
     What does dp[i] or dp[i][j] represent? This is the hardest step.
     Usually it is: "the answer to the problem considering the first i elements"
     or "the answer to the subproblem with parameters i and j."

  2. FIND THE RECURRENCE (Transition)
     How does dp[i] relate to previous states?
     What choices do I have at each step? What are their costs?

  3. BASE CASES
     What are the starting values? dp[0], dp[1], etc.

  4. ITERATION ORDER
     Fill smaller states before larger states.
     For 1D: usually left to right.
     For 2D: usually top-left to bottom-right.

  5. FIND THE ANSWER
     Where is the final answer? dp[n]? dp[n][m]? max(dp[i])?


## Two Approaches

  Top-Down (Memoization): Write the recursive solution, then add a cache.
  Bottom-Up (Tabulation): Build a table from base cases up to the answer.

  Both approaches give the same result. Bottom-up is usually faster in practice
  (no recursion overhead) and is what most interviewers prefer.


---


## Problem Set

### 1D DP -- Start Here

  1. Climbing Stairs                  LeetCode #70
  2. House Robber                     LeetCode #198
  3. House Robber II                  LeetCode #213
  4. Coin Change                      LeetCode #322
  5. Longest Increasing Subsequence   LeetCode #300
  6. Word Break                       LeetCode #139
  7. Decode Ways                      LeetCode #91
  8. Maximum Product Subarray         LeetCode #152
  9. Partition Equal Subset Sum       LeetCode #416

### 2D DP

  1. Unique Paths                     LeetCode #62
  2. Longest Common Subsequence       LeetCode #1143
  3. Edit Distance                    LeetCode #72
  4. Target Sum                       LeetCode #494
  5. Interleaving String              LeetCode #97

### Interval / String DP

  1. Longest Palindromic Substring    LeetCode #5
  2. Palindromic Substrings           LeetCode #647

### Hard DP

  1. Burst Balloons                   LeetCode #312
  2. Regular Expression Matching      LeetCode #10
