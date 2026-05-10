# Linked Lists -- Concepts

## What is a Linked List?

A linked list is a data structure where elements are stored in nodes, and each
node contains a value and a pointer (reference) to the next node. Unlike arrays,
linked list elements are NOT stored in contiguous memory. Each node can be
anywhere in memory; the pointers connect them.

Think of it like a treasure hunt. Each clue (node) tells you where the next
clue is. To find the 5th clue, you must follow clues 1 through 4 first.

### Singly Linked List

Each node has a value and a pointer to the next node. The last node points to
null.

    [1] -> [2] -> [3] -> [4] -> null

In Java:
    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

### Doubly Linked List

Each node has a value, a pointer to the next node, and a pointer to the
previous node. This allows traversal in both directions.


## Array vs Linked List

  Operation              Array      Linked List
  ------------------------------------------------
  Access by index        O(1)       O(n)
  Search                 O(n)       O(n)
  Insert at beginning    O(n)       O(1)
  Insert at end          O(1)*      O(1) if you have tail pointer, O(n) otherwise
  Insert in middle       O(n)       O(1) if you have the node reference
  Delete                 O(n)       O(1) if you have the node reference
  Memory                 Contiguous Scattered, extra pointer overhead

*O(1) amortized for ArrayList.


## Key Techniques for Linked List Problems

### 1. Dummy Head Node
When the head of the list might change (e.g., removing the first node), create
a dummy node that points to the head. This simplifies edge case handling.

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    // ... do your operations ...
    return dummy.next; // The new head

### 2. Fast and Slow Pointers
Use two pointers: slow moves 1 step, fast moves 2 steps.
  - Finding the middle: when fast reaches the end, slow is at the middle.
  - Detecting a cycle: if there is a cycle, fast and slow will meet.

### 3. Reversing a Linked List
This is a fundamental operation. You need to know how to reverse a linked list
both iteratively and recursively. It appears as a subroutine in many problems.


---


## Problem Set

### Easy

  1. Reverse Linked List              LeetCode #206
     Pattern: Iterative and recursive reversal. Must know perfectly.

  2. Merge Two Sorted Lists           LeetCode #21
     Pattern: Two pointers + dummy head

  3. Linked List Cycle                LeetCode #141
     Pattern: Fast and slow pointers

  4. Middle of the Linked List        LeetCode #876
     Pattern: Fast and slow pointers

  5. Palindrome Linked List           LeetCode #234
     Pattern: Fast/slow + reverse second half

### Medium

  1. Remove Nth Node From End         LeetCode #19
     Pattern: Two pointers with gap of n

  2. Reorder List                     LeetCode #143
     Pattern: Find middle + reverse + merge

  3. Add Two Numbers                  LeetCode #2
     Pattern: Simulate addition with carry

  4. Copy List with Random Pointer    LeetCode #138
     Pattern: HashMap for node mapping

  5. LRU Cache                        LeetCode #146
     Pattern: HashMap + Doubly Linked List
     Very frequently asked. Must know.

### Hard

  1. Merge K Sorted Lists             LeetCode #23
     Pattern: Min-heap
     Very frequently asked.

  2. Reverse Nodes in K-Group         LeetCode #25
     Pattern: Iterative reversal in groups
