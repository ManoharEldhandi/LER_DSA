# Two Pointers -- Concepts

## What is the Two Pointers Technique?

The two pointers technique uses two variables (usually called left and right,
or slow and fast) that move through a data structure -- typically an array or
string -- to solve a problem more efficiently than brute force.

The key idea is simple: instead of using nested loops (which give you O(n^2)),
you use two pointers that together visit each element at most once, giving you
O(n).


## Three Main Variations

### 1. Opposite Direction (left and right moving toward each other)

You start with one pointer at the beginning and one at the end. They move
toward each other based on some condition.

When to use: sorted arrays, palindrome checking, finding pairs.

Example: In a sorted array, to find two numbers that add up to a target:
  - If the sum is too small, move the left pointer right (to increase the sum).
  - If the sum is too big, move the right pointer left (to decrease the sum).

### 2. Same Direction (slow and fast)

Both pointers start at the beginning. The fast pointer scans ahead while the
slow pointer marks a position where you want to write or process.

When to use: removing duplicates, partitioning arrays, the "reader-writer"
pattern.

Example: Removing duplicates from a sorted array:
  - Fast pointer reads each element.
  - Slow pointer marks where to write the next unique element.

### 3. Fast and Slow (Floyd's Tortoise and Hare)

One pointer moves one step at a time, the other moves two steps. If there is
a cycle, they will eventually meet. If there is no cycle, the fast pointer
reaches the end.

When to use: cycle detection in linked lists, finding the middle of a linked
list.


---


## Problem Set

### Easy

  1. Valid Palindrome                 LeetCode #125
     Pattern: Opposite direction, skip non-alphanumeric

  2. Merge Sorted Array               LeetCode #88
     Pattern: Two pointers from the end

  3. Move Zeroes                      LeetCode #283
     Pattern: Same direction (reader-writer)

  4. Squares of a Sorted Array        LeetCode #977
     Pattern: Opposite direction

  5. Remove Duplicates from Sorted    LeetCode #26
     Pattern: Same direction


### Medium -- Must Know for Interviews

  1. Two Sum II (Sorted Input)        LeetCode #167
     Pattern: Opposite direction

  2. 3Sum                             LeetCode #15
     Pattern: Fix one, use two pointers for the rest
     This is one of the most frequently asked medium problems.

  3. Container With Most Water        LeetCode #11
     Pattern: Opposite direction, greedy choice
     Very frequently asked.

  4. Sort Colors                      LeetCode #75
     Pattern: Dutch National Flag (three-way partition)

  5. Boats to Save People             LeetCode #881
     Pattern: Sort + opposite direction


### Hard

  1. Trapping Rain Water              LeetCode #42
     Pattern: Opposite direction
     One of the most famous interview problems.
