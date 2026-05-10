# Sorting and Matrix -- Concepts

## Why This Module Exists

Sorting and matrix problems appear inside many topics. They are not always standalone, but interviewers love them because they test clean thinking.

Sorting often turns a messy problem into a structured one.

Matrix problems test whether you can control indexes without getting lost.

---

## Sorting in Java

For primitive arrays:

```java
int[] nums = {3, 1, 2};
Arrays.sort(nums);
```

For object arrays or intervals:

```java
int[][] intervals = {{1, 3}, {2, 6}};
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
```

For lists:

```java
list.sort((a, b) -> a.value - b.value); // avoid this if overflow is possible
list.sort((a, b) -> Integer.compare(a.value, b.value));
```

Prefer `Integer.compare(a, b)` over `a - b` because subtraction can overflow.

---

## Core Sorting Patterns

### Pattern 1: Sort Then Two Pointers

Use for pairs, triplets, and closest sums.

Examples:

- 3Sum
- 4Sum
- Two Sum II

### Pattern 2: Sort Then Greedy

Use when the order lets you make a local best choice.

Examples:

- Non-overlapping Intervals
- Meeting Rooms
- Minimum arrows to burst balloons

### Pattern 3: Custom Comparator

Use when normal numeric order is not enough.

Examples:

- Largest Number
- Reorder logs
- Sort by frequency

### Pattern 4: Cyclic Sort / Index Placement

Use when values are in the range `1..n` or `0..n`.

Examples:

- Missing Number
- Find All Duplicates in an Array
- First Missing Positive

### Pattern 5: Binary Search on Answer

Use when:

- You need the minimum possible maximum.
- You need the maximum possible minimum.
- You can check whether an answer is feasible.

Examples:

- Koko Eating Bananas
- Split Array Largest Sum
- Allocate Minimum Number of Pages
- Aggressive Cows

---

## Matrix Basics

A matrix is a 2D array:

```java
int rows = matrix.length;
int cols = matrix[0].length;
```

Four-direction movement:

```java
int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
```

Boundary check:

```java
if (r < 0 || c < 0 || r >= rows || c >= cols) {
    continue;
}
```

---

## Core Matrix Patterns

### Pattern 1: Layer-by-Layer Traversal

Use for spiral traversal and rotation.

### Pattern 2: Use First Row/Column as Markers

Use when the problem asks for O(1) extra space.

Example:

- Set Matrix Zeroes

### Pattern 3: Treat Matrix as Sorted Space

Use binary search when rows or the whole matrix are sorted.

Examples:

- Search a 2D Matrix
- Kth Smallest Element in a Sorted Matrix

### Pattern 4: Grid DFS/BFS

Use graph traversal when cells connect to neighbors.

Examples:

- Number of Islands
- Rotten Oranges
- Pacific Atlantic Water Flow

---

## Common Mistakes

- Sorting with `a[0] - b[0]` and causing overflow.
- Forgetting to skip duplicates in 3Sum.
- Mixing row and column limits.
- Using `matrix.length` for both rows and columns.
- Off-by-one errors in spiral boundaries.
- Marking matrix cells too early or too late in BFS/DFS.

---

## Problems to Practice

### Sorting and Searching

1. Sort Colors (LC 75)
2. Merge Sorted Array (LC 88)
3. 3Sum (LC 15)
4. 4Sum (LC 18)
5. Search in Rotated Sorted Array (LC 33)
6. Find Minimum in Rotated Sorted Array (LC 153)
7. Koko Eating Bananas (LC 875)
8. Split Array Largest Sum (LC 410)
9. Find the Duplicate Number (LC 287)
10. First Missing Positive (LC 41)

### Matrix

1. Set Matrix Zeroes (LC 73)
2. Spiral Matrix (LC 54)
3. Rotate Image (LC 48)
4. Search a 2D Matrix (LC 74)
5. Game of Life (LC 289)
6. Maximal Square (LC 221)
7. Maximal Rectangle (LC 85)

---

## Interview Shortcut

For sorting problems, ask:

1. Will sorting preserve enough information?
2. After sorting, can I use two pointers?
3. Is a greedy choice obvious after sorting?
4. Is the answer searchable with a feasibility check?

For matrix problems, ask:

1. Is this traversal, transformation, search, or graph?
2. What are my boundaries?
3. Do I need extra space or in-place mutation?
