# Binary Search -- Concepts

## What is Binary Search?

Binary search is an algorithm for finding a target value in a SORTED array.
Instead of checking every element (O(n)), it repeatedly cuts the search space
in half, achieving O(log n) time.

Here is the intuition: Imagine you are looking for a word in a dictionary.
You would not start from page 1 and check every page. You would open the
dictionary roughly in the middle. If the word you want comes after the middle
page, you ignore the first half. If it comes before, you ignore the second
half. You keep halving until you find the word.

That is exactly what binary search does.


## How it Works

1. Start with left = 0 and right = array.length - 1.
2. Find the middle: mid = left + (right - left) / 2.
   (We use this formula instead of (left + right) / 2 to avoid integer overflow.)
3. Compare array[mid] with target:
   - If equal: found it, return mid.
   - If array[mid] < target: the target must be in the right half.
     Set left = mid + 1.
   - If array[mid] > target: the target must be in the left half.
     Set right = mid - 1.
4. If left > right, the target does not exist.


## When to Use Binary Search

Binary search applies whenever you have a monotonic (sorted or ordered)
property. This is not limited to sorted arrays:

  - Sorted array: find a target value
  - Find the boundary: first element >= target, last element <= target
  - Search on answer: "what is the minimum/maximum value X such that
    some condition is satisfied?" If the condition is monotonic (once it
    becomes true, it stays true), binary search works.
  - Rotated sorted array: a special case with a twist


## Common Mistakes

1. Off-by-one errors: Use "left <= right" (inclusive) or "left < right"
   (exclusive) consistently. Do not mix them up.

2. Infinite loops: Make sure left and right converge. If you use
   mid = left + (right - left) / 2, left always moves to mid + 1 and
   right moves to mid - 1 (or mid, depending on the variant).

3. Integer overflow: Always use left + (right - left) / 2 instead of
   (left + right) / 2.


---


## Problem Set

### Easy

  1. Binary Search                    LeetCode #704
     Pattern: Basic binary search

  2. Search Insert Position           LeetCode #35
     Pattern: Find the left boundary

  3. First Bad Version                LeetCode #278
     Pattern: Binary search on condition

  4. Valid Perfect Square             LeetCode #367
     Pattern: Search on answer

### Medium

  1. Search in Rotated Sorted Array   LeetCode #33
     Pattern: Modified binary search. Extremely frequently asked.

  2. Find Minimum in Rotated Array    LeetCode #153
     Pattern: Binary search on rotated array

  3. Search a 2D Matrix               LeetCode #74
     Pattern: Treat 2D matrix as 1D sorted array

  4. Koko Eating Bananas              LeetCode #875
     Pattern: Binary search on answer. Classic pattern.

  5. Find Peak Element               LeetCode #162
     Pattern: Binary search on unsorted array

  6. Time Based Key-Value Store       LeetCode #981
     Pattern: Binary search on timestamps

### Hard

  1. Median of Two Sorted Arrays      LeetCode #4
     Pattern: Binary search on partition. Very tricky but frequently asked.

  2. Split Array Largest Sum          LeetCode #410
     Pattern: Binary search on answer
