# Detailed 30-Day Study Plan

This is the main sprint plan for `LER_DSA`.

Start date: **Monday, May 11, 2026**  
End date: **Tuesday, June 9, 2026**  
Time available: **2 hours on weekdays, 4 hours on weekends**  
Main target: **80 to 95 problems solved properly**  
Stretch target: **100+ problems if revision is still clean**

If you start later, ignore the dates and follow Day 1 through Day 30 in order.

---

## How to Execute Each Problem

Use this same process every time:

1. Read the problem and constraints.
2. Say the brute force idea.
3. Identify the pattern.
4. Explain the optimized approach.
5. Code in Java.
6. Test edge cases.
7. Log the result in `progress_tracker.md`.

Use these ratings:

- `[S]` solved independently within interview time
- `[H]` needed a hint but coded mostly yourself
- `[L]` looked at solution/editorial
- `[R]` redone successfully during revision

Any `[H]` or `[L]` problem must be redone after 3 days, 1 week, and 2 weeks.

---

## Daily Problem Load

Weekday target:

- 1 main problem carefully, plus 1 easy if time allows
- stop after 2 hours and log what happened

Weekend target:

- 4 to 5 problems
- 1 revision block
- 1 timed set or mock-style run

Quality beats count. A clean 80-problem sprint is better than a messy 130-problem sprint.

---

## Week 1 -- Foundation, Arrays, Hashing, Pointers, Search

### Day 1 -- Mon May 11 -- Java + Big-O + Array Basics

Time: 2 hours

Study:
- `00_fundamentals/BigOAnalysis.java`
- `00_fundamentals/JavaForDSA.java`
- `01_arrays_and_hashing/Concepts.md`

Solve:
1. Two Sum (LC 1) -- Easy -- HashMap complement
2. Contains Duplicate (LC 217) -- Easy -- HashSet
3. Valid Anagram (LC 242) -- Easy -- Frequency count

Checkpoint:
- You can explain why HashMap lookup is average O(1).
- You can write frequency counting with `int[26]` and `HashMap`.

### Day 2 -- Tue May 12 -- Array Running State

Time: 2 hours

Study:
- `01_arrays_and_hashing/Patterns.java`

Solve:
1. Best Time to Buy and Sell Stock (LC 121) -- Easy -- Running minimum
2. Majority Element (LC 169) -- Easy -- Boyer-Moore
3. Maximum Subarray (LC 53) -- Medium -- Kadane

Checkpoint:
- You can explain what each tracking variable means.

### Day 3 -- Wed May 13 -- Prefix and Grouping

Time: 2 hours

Solve:
1. Product of Array Except Self (LC 238) -- Medium -- Prefix/suffix
2. Group Anagrams (LC 49) -- Medium -- HashMap grouping

Checkpoint:
- You can group items by a computed key.

### Day 4 -- Thu May 14 -- Prefix Sum + HashMap

Time: 2 hours

Solve:
1. Subarray Sum Equals K (LC 560) -- Medium -- Prefix sum + HashMap
2. Longest Consecutive Sequence (LC 128) -- Medium -- HashSet sequence start

Checkpoint:
- You know why prefix sum stores `sum - k`.

### Day 5 -- Fri May 15 -- Two Pointers

Time: 2 hours

Study:
- `02_two_pointers/Concepts.md`
- `02_two_pointers/Patterns.java`

Solve:
1. Valid Palindrome (LC 125) -- Easy -- Opposite pointers
2. Container With Most Water (LC 11) -- Medium -- Move limiting side
3. 3Sum (LC 15) -- Medium -- Sort + two pointers

Checkpoint:
- You can skip duplicates correctly in 3Sum.

### Day 6 -- Sat May 16 -- Sliding Window + Binary Search

Time: 4 hours

Study:
- `03_sliding_window/Concepts.md`
- `03_sliding_window/Patterns.java`
- `04_binary_search/Concepts.md`

Solve:
1. Longest Substring Without Repeating Characters (LC 3) -- Medium
2. Longest Repeating Character Replacement (LC 424) -- Medium
3. Binary Search (LC 704) -- Easy
4. Search Insert Position (LC 35) -- Easy
5. Search in Rotated Sorted Array (LC 33) -- Medium

Checkpoint:
- You know fixed window vs variable window.
- You can update binary search boundaries without guessing.

### Day 7 -- Sun May 17 -- Binary Search on Answer + Review

Time: 4 hours

Study:
- `04_binary_search/Patterns.java`
- `19_sorting_and_matrix/Concepts.md`

Solve:
1. Find Minimum in Rotated Sorted Array (LC 153) -- Medium
2. Koko Eating Bananas (LC 875) -- Medium
3. Split Array Largest Sum (LC 410) -- Hard-ish Medium
4. Allocate Minimum Number of Pages -- Medium -- Binary search on answer
5. Move Zeroes (LC 283) -- Easy -- In-place partition

Review:
- Redo any Week 1 problem marked `[H]` or `[L]`.

Week 1 checkpoint:
- 20 to 23 problems attempted.
- Arrays, maps, pointers, windows, and binary search are usable.

---

## Week 2 -- Stacks, Linked Lists, Backtracking, Strings, Matrix

### Day 8 -- Mon May 18 -- Stack Basics

Time: 2 hours

Study:
- `05_stacks_and_queues/Concepts.md`
- `05_stacks_and_queues/Patterns.java`

Solve:
1. Valid Parentheses (LC 20) -- Easy
2. Min Stack (LC 155) -- Medium
3. Daily Temperatures (LC 739) -- Medium

Checkpoint:
- You can explain why stack fits matching and next-greater problems.

### Day 9 -- Tue May 19 -- Monotonic Stack

Time: 2 hours

Solve:
1. Next Greater Element I (LC 496) -- Easy
2. Asteroid Collision (LC 735) -- Medium
3. Largest Rectangle in Histogram (LC 84) -- Hard

Tip:
- LC 84 is supposed to feel hard. Focus on width calculation.

### Day 10 -- Wed May 20 -- Linked List Basics

Time: 2 hours

Study:
- `06_linked_lists/Concepts.md`
- `06_linked_lists/Patterns.java`

Solve:
1. Reverse Linked List (LC 206) -- Easy
2. Merge Two Sorted Lists (LC 21) -- Easy
3. Linked List Cycle (LC 141) -- Easy

Checkpoint:
- You can reverse a list without losing `next`.

### Day 11 -- Thu May 21 -- Linked List Mediums

Time: 2 hours

Solve:
1. Remove Nth Node From End of List (LC 19) -- Medium
2. Reorder List (LC 143) -- Medium
3. Copy List with Random Pointer (LC 138) -- Medium

Checkpoint:
- You know when to use dummy node, fast/slow pointers, and HashMap.

### Day 12 -- Fri May 22 -- Backtracking Basics

Time: 2 hours

Study:
- `07_recursion_and_backtracking/Concepts.md`
- `07_recursion_and_backtracking/Patterns.java`

Solve:
1. Subsets (LC 78) -- Medium
2. Permutations (LC 46) -- Medium

Checkpoint:
- Your template is choose, explore, un-choose.

### Day 13 -- Sat May 23 -- Backtracking + Strings

Time: 4 hours

Study:
- `18_strings/Concepts.md`

Solve:
1. Combination Sum (LC 39) -- Medium
2. Word Search (LC 79) -- Medium
3. Generate Parentheses (LC 22) -- Medium
4. Valid Palindrome II (LC 680) -- Easy
5. Minimum Window Substring (LC 76) -- Hard

Review:
- Redo 2 Week 1 misses.

Checkpoint:
- You can explain when a problem is asking for all possibilities.

### Day 14 -- Sun May 24 -- Sorting + Matrix

Time: 4 hours

Study:
- `19_sorting_and_matrix/Concepts.md`
- `19_sorting_and_matrix/Patterns.java`

Solve:
1. Set Matrix Zeroes (LC 73) -- Medium
2. Spiral Matrix (LC 54) -- Medium
3. Rotate Image (LC 48) -- Medium
4. Sort Colors (LC 75) -- Medium
5. Search a 2D Matrix (LC 74) -- Medium

Review:
- Redo 2 stack, linked list, or backtracking misses.

Week 2 checkpoint:
- 40 to 47 problems attempted.
- You can handle pointers, stack structure, recursive search, strings, and matrix indexing.

---

## Week 3 -- Trees, Heaps, Greedy, Intervals

### Day 15 -- Mon May 25 -- Binary Tree Traversal

Time: 2 hours

Study:
- `08_trees/Concepts.md`
- `08_trees/Patterns.java`

Solve:
1. Invert Binary Tree (LC 226) -- Easy
2. Maximum Depth of Binary Tree (LC 104) -- Easy
3. Binary Tree Level Order Traversal (LC 102) -- Medium

Checkpoint:
- You can write recursive DFS and queue-based BFS.

### Day 16 -- Tue May 26 -- Tree Recursion

Time: 2 hours

Solve:
1. Same Tree (LC 100) -- Easy
2. Diameter of Binary Tree (LC 543) -- Easy
3. Balanced Binary Tree (LC 110) -- Easy

Checkpoint:
- You can return information from children to parent nodes.

### Day 17 -- Wed May 27 -- BST

Time: 2 hours

Solve:
1. Validate Binary Search Tree (LC 98) -- Medium
2. Kth Smallest Element in a BST (LC 230) -- Medium
3. Lowest Common Ancestor of BST (LC 235) -- Medium

Checkpoint:
- You know why inorder traversal of a BST is sorted.

### Day 18 -- Thu May 28 -- Tree Mediums

Time: 2 hours

Solve:
1. Lowest Common Ancestor of Binary Tree (LC 236) -- Medium
2. Construct Binary Tree from Preorder and Inorder (LC 105) -- Medium
3. Binary Tree Right Side View (LC 199) -- Medium

Checkpoint:
- You can choose between DFS and BFS based on what the problem asks.

### Day 19 -- Fri May 29 -- Heap Basics

Time: 2 hours

Study:
- `09_heaps/Concepts.md`
- `09_heaps/Patterns.java`

Solve:
1. Kth Largest Element in an Array (LC 215) -- Medium
2. Top K Frequent Elements (LC 347) -- Medium
3. K Closest Points to Origin (LC 973) -- Medium

Checkpoint:
- You can decide min-heap vs max-heap.

### Day 20 -- Sat May 30 -- Greedy + Intervals

Time: 4 hours

Study:
- `12_greedy/Concepts.md`
- `13_intervals/Concepts.md`

Solve:
1. Jump Game (LC 55) -- Medium
2. Jump Game II (LC 45) -- Medium
3. Gas Station (LC 134) -- Medium
4. Merge Intervals (LC 56) -- Medium
5. Insert Interval (LC 57) -- Medium
6. Non-overlapping Intervals (LC 435) -- Medium

Checkpoint:
- You can explain the greedy choice in plain English.

### Day 21 -- Sun May 31 -- Heap Hard + Interval Review

Time: 4 hours

Study:
- `13_intervals/Patterns.java`

Solve:
1. Meeting Rooms II (LC 253) -- Medium
2. Merge K Sorted Lists (LC 23) -- Hard
3. Find Median from Data Stream (LC 295) -- Hard
4. Binary Tree Maximum Path Sum (LC 124) -- Hard
5. Last Stone Weight (LC 1046) -- Easy

Timed set:
- 45 minutes: one tree or interval medium.

Week 3 checkpoint:
- 62 to 73 problems attempted.
- Trees, heaps, greedy, and intervals are interview-usable.

---

## Week 4 -- Graphs, Union-Find, DP, Tries, Bits, Design, Review

### Day 22 -- Mon Jun 1 -- Graph BFS/DFS

Time: 2 hours

Study:
- `10_graphs/Concepts.md`
- `10_graphs/Patterns.java`

Solve:
1. Number of Islands (LC 200) -- Medium
2. Max Area of Island (LC 695) -- Medium

Checkpoint:
- You can turn grid movement into four directions.

### Day 23 -- Tue Jun 2 -- Graph Multi-Source BFS

Time: 2 hours

Solve:
1. Clone Graph (LC 133) -- Medium
2. Rotting Oranges (LC 994) -- Medium
3. Pacific Atlantic Water Flow (LC 417) -- Medium

Checkpoint:
- You know when to mark a node visited.

### Day 24 -- Wed Jun 3 -- Topological Sort + Union-Find

Time: 2 hours

Study:
- `17_union_find/Concepts.md`
- `17_union_find/Patterns.java`

Solve:
1. Course Schedule (LC 207) -- Medium
2. Course Schedule II (LC 210) -- Medium
3. Redundant Connection (LC 684) -- Medium

Checkpoint:
- You can explain indegree and path compression.

### Day 25 -- Thu Jun 4 -- Components + Shortest Path

Time: 2 hours

Solve:
1. Number of Connected Components (LC 323) -- Medium
2. Network Delay Time (LC 743) -- Medium

Checkpoint:
- You can choose DFS, Union-Find, or Dijkstra based on the question.

### Day 26 -- Fri Jun 5 -- Dynamic Programming 1D

Time: 2 hours

Study:
- `11_dynamic_programming/Concepts.md`
- `11_dynamic_programming/Patterns.java`

Solve:
1. Climbing Stairs (LC 70) -- Easy
2. House Robber (LC 198) -- Medium
3. Coin Change (LC 322) -- Medium

Checkpoint:
- You can define `dp[i]` in a full sentence.

### Day 27 -- Sat Jun 6 -- DP Patterns + 2D DP

Time: 4 hours

Solve:
1. Longest Increasing Subsequence (LC 300) -- Medium
2. Word Break (LC 139) -- Medium
3. Decode Ways (LC 91) -- Medium
4. Unique Paths (LC 62) -- Medium
5. Longest Common Subsequence (LC 1143) -- Medium
6. Edit Distance (LC 72) -- Hard-ish Medium

Timed set:
- 45 minutes: one DP medium without looking at notes.

Checkpoint:
- You understand state, recurrence, base case, and iteration order.

### Day 28 -- Sun Jun 7 -- Tries, Bits, Math

Time: 4 hours

Study:
- `14_tries/Concepts.md`
- `15_bit_manipulation/Concepts.md`
- `16_math_and_stats/Concepts.md`

Solve:
1. Implement Trie (LC 208) -- Medium
2. Design Add and Search Words (LC 211) -- Medium
3. Word Search II (LC 212) -- Hard
4. Single Number (LC 136) -- Easy
5. Counting Bits (LC 338) -- Easy
6. Missing Number (LC 268) -- Easy
7. Power of Two (LC 231) -- Easy

Review:
- Redo 2 graph or DP misses.

### Day 29 -- Mon Jun 8 -- Design-Style Coding

Time: 2 hours

Study:
- `20_design/Concepts.md`
- `20_design/Patterns.java`

Solve:
1. LRU Cache (LC 146) -- Medium
2. Insert Delete GetRandom O(1) (LC 380) -- Medium

Optional if time remains:
- Time Based Key-Value Store (LC 981) -- Medium

Checkpoint:
- You can combine HashMap with another data structure.

### Day 30 -- Tue Jun 9 -- Final Mixed Review

Time: 2 hours

Do:
1. Pick 2 previous `[H]` or `[L]` problems and redo them cold.
2. Pick 1 random medium from a topic you dislike.
3. Review `PATTERN_RECOGNITION_GUIDE.md`.
4. Review `COMMON_MISTAKES.md`.
5. Update final status in `progress_tracker.md`.

Final checkpoint:
- 80 to 95 problems attempted.
- At least 55 solved independently or on clean revisit.
- At least 3 timed sessions completed.
- Weak topics are known, not hidden.

---

## Core 90 Problem Bank

Prioritize these if time gets tight.

### Arrays, Hashing, Prefix Sum

1. Two Sum (LC 1)
2. Contains Duplicate (LC 217)
3. Valid Anagram (LC 242)
4. Best Time to Buy and Sell Stock (LC 121)
5. Majority Element (LC 169)
6. Maximum Subarray (LC 53)
7. Product of Array Except Self (LC 238)
8. Group Anagrams (LC 49)
9. Subarray Sum Equals K (LC 560)
10. Longest Consecutive Sequence (LC 128)

### Two Pointers, Sliding Window, Binary Search

11. Valid Palindrome (LC 125)
12. Container With Most Water (LC 11)
13. 3Sum (LC 15)
14. Longest Substring Without Repeating Characters (LC 3)
15. Longest Repeating Character Replacement (LC 424)
16. Binary Search (LC 704)
17. Search Insert Position (LC 35)
18. Search in Rotated Sorted Array (LC 33)
19. Find Minimum in Rotated Sorted Array (LC 153)
20. Koko Eating Bananas (LC 875)
21. Split Array Largest Sum (LC 410)

### Stacks, Linked Lists, Backtracking

22. Valid Parentheses (LC 20)
23. Min Stack (LC 155)
24. Daily Temperatures (LC 739)
25. Next Greater Element I (LC 496)
26. Asteroid Collision (LC 735)
27. Largest Rectangle in Histogram (LC 84)
28. Reverse Linked List (LC 206)
29. Merge Two Sorted Lists (LC 21)
30. Linked List Cycle (LC 141)
31. Remove Nth Node From End of List (LC 19)
32. Reorder List (LC 143)
33. Copy List with Random Pointer (LC 138)
34. Subsets (LC 78)
35. Permutations (LC 46)
36. Combination Sum (LC 39)
37. Word Search (LC 79)

### Strings, Sorting, Matrix

38. Generate Parentheses (LC 22)
39. Valid Palindrome II (LC 680)
40. Minimum Window Substring (LC 76)
41. Set Matrix Zeroes (LC 73)
42. Spiral Matrix (LC 54)
43. Rotate Image (LC 48)
44. Sort Colors (LC 75)
45. Search a 2D Matrix (LC 74)

### Trees and BST

46. Invert Binary Tree (LC 226)
47. Maximum Depth of Binary Tree (LC 104)
48. Binary Tree Level Order Traversal (LC 102)
49. Same Tree (LC 100)
50. Diameter of Binary Tree (LC 543)
51. Balanced Binary Tree (LC 110)
52. Validate Binary Search Tree (LC 98)
53. Kth Smallest Element in a BST (LC 230)
54. Lowest Common Ancestor of BST (LC 235)
55. Lowest Common Ancestor of Binary Tree (LC 236)
56. Construct Binary Tree from Preorder and Inorder (LC 105)
57. Binary Tree Right Side View (LC 199)
58. Binary Tree Maximum Path Sum (LC 124)

### Heaps, Greedy, Intervals

59. Kth Largest Element in an Array (LC 215)
60. Top K Frequent Elements (LC 347)
61. K Closest Points to Origin (LC 973)
62. Merge K Sorted Lists (LC 23)
63. Find Median from Data Stream (LC 295)
64. Jump Game (LC 55)
65. Jump Game II (LC 45)
66. Gas Station (LC 134)
67. Merge Intervals (LC 56)
68. Insert Interval (LC 57)
69. Non-overlapping Intervals (LC 435)
70. Meeting Rooms II (LC 253)

### Graphs and Union-Find

71. Number of Islands (LC 200)
72. Max Area of Island (LC 695)
73. Clone Graph (LC 133)
74. Rotting Oranges (LC 994)
75. Pacific Atlantic Water Flow (LC 417)
76. Course Schedule (LC 207)
77. Course Schedule II (LC 210)
78. Redundant Connection (LC 684)
79. Number of Connected Components (LC 323)
80. Network Delay Time (LC 743)

### Dynamic Programming, Trie, Bits, Design

81. Climbing Stairs (LC 70)
82. House Robber (LC 198)
83. Coin Change (LC 322)
84. Longest Increasing Subsequence (LC 300)
85. Word Break (LC 139)
86. Decode Ways (LC 91)
87. Unique Paths (LC 62)
88. Longest Common Subsequence (LC 1143)
89. Edit Distance (LC 72)
90. LRU Cache (LC 146)

Priority add-ons:

- Implement Trie (LC 208)
- Design Add and Search Words (LC 211)
- Word Search II (LC 212)
- Single Number (LC 136)
- Counting Bits (LC 338)
- Insert Delete GetRandom O(1) (LC 380)
- Time Based Key-Value Store (LC 981)

---

## Stretch Problems

Do these only after the core list is moving well.

1. N-Queens (LC 51)
2. Sudoku Solver (LC 37)
3. Serialize and Deserialize Binary Tree (LC 297)
4. Word Ladder (LC 127)
5. Alien Dictionary (LC 269)
6. Accounts Merge (LC 721)
7. Partition Equal Subset Sum (LC 416)
8. Target Sum (LC 494)
9. Burst Balloons (LC 312)
10. Regular Expression Matching (LC 10)
11. Trapping Rain Water (LC 42)
12. Sliding Window Maximum (LC 239)
13. Basic Calculator (LC 224)
14. Maximal Rectangle (LC 85)
15. LFU Cache (LC 460)

---

## Timed Practice Plan

Do at least 3 timed sessions:

1. Day 21: one tree or interval medium in 45 minutes.
2. Day 27: one DP medium in 45 minutes.
3. After Day 30: two mixed mediums in 45 to 60 minutes.

Timed format:

```
00:00 - 05:00   Clarify and give brute force
05:00 - 10:00   Explain optimized approach
10:00 - 30:00   Code
30:00 - 40:00   Test and fix
40:00 - 45:00   Complexity and follow-ups
```

---

## If You Fall Behind

Cut in this order:

1. hard stretch problems
2. optional add-ons
3. duplicate easy problems
4. advanced graph algorithms beyond BFS/DFS/topo/Dijkstra/DSU
5. rare math problems

Never cut:

- Arrays and HashMap
- Two pointers and sliding window
- Binary search
- Trees
- Graph BFS/DFS
- DP basics
- Java collection fluency
- Revision of missed problems
