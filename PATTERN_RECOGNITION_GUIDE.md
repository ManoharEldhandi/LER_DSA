# Pattern Recognition Guide

When you read a problem, look for these clues. They tell you which
technique to use. This is the most important skill in interviews.


---


## Quick Lookup Table

| If You See This in the Problem... | Think About This Approach |
|---|---|
| "Sorted array" | Binary Search, Two Pointers |
| "Find a pair that sums to target" | HashMap (Two Sum), Two Pointers if sorted |
| "Subarray" or "contiguous" | Sliding Window, Prefix Sum |
| "Substring" | Sliding Window, HashMap |
| "Subsequence" | DP, Two Pointers (if checking existence) |
| "Top K" or "Kth largest/smallest" | Heap (PriorityQueue), QuickSelect |
| "Frequency" or "count occurrences" | HashMap |
| "Unique" or "distinct" | HashSet |
| "Anagram" or "permutation of" | Frequency count (int[26] or HashMap) |
| "Palindrome" | Two Pointers (from edges), DP (substring) |
| "Parentheses" or "brackets" | Stack |
| "Next greater/smaller element" | Monotonic Stack |
| "Tree traversal" | DFS (recursion) or BFS (queue) |
| "Level by level" in a tree | BFS with queue |
| "Lowest common ancestor" | DFS on tree |
| "Validate BST" | Inorder traversal or min/max bounds |
| "Graph" or "connected" | BFS, DFS, Union-Find |
| "Shortest path" (unweighted) | BFS |
| "Shortest path" (weighted) | Dijkstra |
| "Detect cycle" (directed graph) | DFS with coloring or topological sort |
| "Detect cycle" (undirected graph) | Union-Find or DFS |
| "Topological order" or "prerequisites" | Topological Sort (Kahn's BFS) |
| "Number of islands" or "connected components" | DFS/BFS on grid, or Union-Find |
| "All combinations" or "all subsets" | Backtracking |
| "All permutations" | Backtracking |
| "Can you partition into..." | Backtracking or DP |
| "Minimum cost" or "minimum operations" | DP or BFS |
| "Maximum profit/value" with choices | DP (take or skip) |
| "How many ways" | DP (counting) |
| "Overlapping subproblems" | DP (memoization or tabulation) |
| "0/1 choices" (include or exclude) | 0/1 Knapsack DP |
| "Unlimited choices" | Unbounded Knapsack DP |
| "Two strings, compare/transform" | 2D DP (LCS, Edit Distance) |
| "Intervals" or "ranges" | Sort + Greedy or Sweep Line |
| "Merge intervals" | Sort by start, merge overlapping |
| "Schedule meetings" or "rooms" | Sort + Heap |
| "Word prefix" or "autocomplete" | Trie |
| "Word in grid" | Trie + Backtracking |
| "Bit counting" or "XOR" | Bit Manipulation |
| "Power of 2" | n & (n-1) == 0 |
| "Single number among duplicates" | XOR |
| "Matrix rotation" or "spiral" | Index manipulation |
| "In-place" with O(1) space | Two Pointers, Swap, Cyclic Sort |
| "Design a class with get/put/insert/remove in O(1)" | HashMap + another structure |
| "Least recently used" | HashMap + Doubly Linked List |
| "Random pick in O(1)" | HashMap + ArrayList |
| "Value at or before timestamp" | HashMap + Binary Search or TreeMap |


---


## Decision Trees for Common Scenarios


### "Given an Array, Find..."

```
Is it sorted?
  Yes -> Binary Search or Two Pointers
  No  -> Do you need pairs/triplets?
           Yes -> HashMap or Sort + Two Pointers
           No  -> Is it about subarrays?
                    Yes -> Sliding Window or Prefix Sum
                    No  -> Is it about subsequences?
                             Yes -> DP
                             No  -> HashMap, Sorting, or Greedy
```


### "Given a String, Find..."

```
Is it about substrings?
  Yes -> Sliding Window or DP
  No  -> Is it about anagrams/permutations?
           Yes -> Frequency count (int[26])
           No  -> Is it about palindromes?
                    Yes -> Two Pointers or DP
                    No  -> Is it about words/prefixes?
                             Yes -> Trie
                             No  -> HashMap or Stack
```


### "Given a Graph/Grid, Find..."

```
Is it a tree?
  Yes -> DFS/BFS, recursion
  No  -> Is it about connectivity?
           Yes -> Union-Find or DFS
           No  -> Is it about shortest path?
                    Yes -> BFS (unweighted) or Dijkstra (weighted)
                    No  -> Is it about ordering?
                             Yes -> Topological Sort
                             No  -> DFS/BFS exploration
```


### "Given Intervals, Find..."

```
Merge overlapping?
  Yes -> Sort by start + merge
Remove minimum to avoid overlap?
  Yes -> Sort by end + greedy (activity selection)
Find how many overlap at once?
  Yes -> Sort + sweep line or min-heap
```


### "Design a Data Structure..."

```
Need O(1) lookup by key?
  Yes -> HashMap
Need O(1) delete from middle?
  Yes -> Custom doubly linked list node references
Need O(1) random pick?
  Yes -> ArrayList + HashMap(value -> index)
Need latest value before timestamp?
  Yes -> Store sorted timestamps + binary search
Need prefix search?
  Yes -> Trie
Need min/max by priority?
  Yes -> PriorityQueue or TreeMap
```


---


## The "I'm Stuck" Protocol

If you cannot figure out the approach within 5 minutes:

1. Re-read the problem. Did you miss a constraint?
2. What is the brute force? Even O(n^3) is a starting point.
3. Can you sort the input? Sorting often reveals structure.
4. Can you use a HashMap? It solves a surprising number of problems.
5. Can you draw it out? Trees, graphs, and intervals become clearer when drawn.
6. Have you seen a similar problem? Think about which pattern matches.
7. Work through a small example by hand. The algorithm often emerges from
   doing the work manually.

In an interview, say: "Let me think about this for a moment" and then
try one of the above. Never sit in silence for more than 30 seconds.


---


## Complexity Quick Reference

| Approach | Typical Time | Typical Space |
|---|---|---|
| Brute force nested loops | O(n^2) or O(n^3) | O(1) |
| HashMap single pass | O(n) | O(n) |
| Sorting + scan | O(n log n) | O(1) to O(n) |
| Two Pointers | O(n) | O(1) |
| Sliding Window | O(n) | O(k) or O(1) |
| Binary Search | O(log n) | O(1) |
| BFS/DFS on graph | O(V + E) | O(V) |
| Backtracking | O(2^n) or O(n!) | O(n) |
| DP 1D | O(n) to O(n^2) | O(n) |
| DP 2D | O(m * n) | O(m * n) |
| Heap operations | O(n log k) | O(k) |
| Union-Find | O(n * alpha(n)) | O(n) |
| Trie | O(L) per operation | O(total chars) |
| HashMap + Doubly Linked List | O(1) get/put | O(n) |
| HashMap + ArrayList | O(1) insert/remove/random | O(n) |
