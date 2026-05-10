# Heaps and Priority Queues -- Concepts

## What is a Heap?

A heap is a complete binary tree that satisfies the heap property:
  - Min-Heap: Every parent is smaller than or equal to its children.
    The root is the smallest element.
  - Max-Heap: Every parent is greater than or equal to its children.
    The root is the largest element.

Heaps are used to efficiently find the minimum or maximum element.

Key operations:
  Insert (offer):  O(log n) -- add element, then "bubble up"
  Remove min/max (poll): O(log n) -- remove root, then "bubble down"
  Peek min/max:    O(1) -- just look at the root

In Java, PriorityQueue is a min-heap by default:
  PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());


## When to Use a Heap

  - "Find the kth largest/smallest element" -- Use a heap of size k.
  - "Find the top k frequent elements" -- Count with HashMap, then heap.
  - "Merge k sorted lists" -- Use a min-heap to always get the smallest.
  - "Find the median in a stream" -- Use two heaps (max-heap + min-heap).
  - "Schedule tasks by priority" -- Natural use of a priority queue.


---


## Problem Set

### Medium

  1. Kth Largest Element in Array     LeetCode #215
     Pattern: Min-heap of size k, or QuickSelect

  2. Top K Frequent Elements          LeetCode #347
     Pattern: HashMap + min-heap

  3. K Closest Points to Origin       LeetCode #973
     Pattern: Max-heap of size k

  4. Task Scheduler                   LeetCode #621
     Pattern: Max-heap + greedy

  5. Design Twitter                   LeetCode #355
     Pattern: OOP design + merge sorted feeds with heap

  6. Kth Largest in a Stream          LeetCode #703
     Pattern: Min-heap of size k

### Hard

  1. Find Median from Data Stream     LeetCode #295
     Pattern: Two heaps (max-heap for lower half, min-heap for upper half)
     Very frequently asked.

  2. Merge K Sorted Lists             LeetCode #23
     Pattern: Min-heap
     Very frequently asked.
