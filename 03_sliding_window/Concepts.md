# Sliding Window -- Concepts

## What is the Sliding Window Technique?

Imagine you have a long array and you need to examine subarrays (contiguous
chunks) of it. The brute force approach recomputes everything for each
subarray from scratch. The sliding window technique avoids this by maintaining
a "window" that slides across the array. As the window moves, you add the new
element entering the window and remove the element leaving the window.

This turns O(n * k) or O(n^2) problems into O(n) problems.

Think of it like this: you are looking through a window on a moving train.
As the train moves, you do not re-examine the entire landscape. You just
notice what comes into view and what leaves.


## Two Types of Sliding Windows

### 1. Fixed-Size Window

The window size is given in the problem (for example, "find the maximum sum
of a subarray of size k"). You just slide a window of exactly that size.

Template:
  1. Initialize the window with the first k elements.
  2. Slide: add the new element on the right, remove the element on the left.
  3. Update your answer at each step.

### 2. Variable-Size Window

The window grows and shrinks based on a condition (for example, "find the
longest substring without repeating characters"). This is the more common
and more interesting type.

Template:
  1. Start with left = 0.
  2. Expand: move right pointer forward, add the new element to your window state.
  3. Shrink: while the window violates the condition, remove the left element
     and move left forward.
  4. Update your answer after ensuring the window is valid.

The key insight is that both left and right only move forward. Together,
they visit each element at most twice, giving O(n) total.


---


## Problem Set

### Easy

  1. Maximum Average Subarray I       LeetCode #643
     Pattern: Fixed-size window

### Medium -- Must Know

  1. Longest Substring Without Repeating Characters   LeetCode #3
     Pattern: Variable window with HashSet
     One of the most frequently asked interview problems.

  2. Longest Repeating Character Replacement           LeetCode #424
     Pattern: Variable window with frequency count

  3. Permutation in String            LeetCode #567
     Pattern: Fixed window with frequency matching

  4. Minimum Size Subarray Sum        LeetCode #209
     Pattern: Variable window

  5. Fruit Into Baskets               LeetCode #904
     Pattern: Variable window with at most 2 distinct elements

  6. Max Consecutive Ones III         LeetCode #1004
     Pattern: Variable window with at most k zeros

### Hard

  1. Minimum Window Substring         LeetCode #76
     Pattern: Variable window with frequency matching
     Extremely frequently asked. This is the "ultimate" sliding window problem.

  2. Sliding Window Maximum           LeetCode #239
     Pattern: Fixed window with monotonic deque
