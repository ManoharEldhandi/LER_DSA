# Intervals

## What Are Interval Problems?

An interval is simply a pair [start, end] representing a range. Interval problems ask you to manipulate, merge, insert, or analyze these ranges. These come up constantly in interviews and in real-world systems (calendar scheduling, IP range merging, resource allocation).


## Core Technique: Sort by Start Time (Usually)

Almost every interval problem starts with sorting. The question is: sort by start time or end time?

- **Sort by start time:** When you want to merge overlapping intervals or find conflicts
- **Sort by end time:** When you want to select the maximum number of non-overlapping intervals (activity selection / greedy)


## How to Detect Overlap

Two intervals [a, b] and [c, d] overlap if and only if:
```
a <= d AND c <= b
```

They do NOT overlap if:
```
b < c OR d < a
```

When sorted by start time, checking overlap is simpler. Given intervals sorted by start, the current interval [c, d] overlaps with previous [a, b] if:
```
c <= b
```
That's it. Because they are sorted, we already know c >= a.


## Key Patterns

### Pattern 1: Merge Overlapping Intervals
Sort by start. Walk through. If current overlaps previous, merge them (extend the end). Otherwise, add current as a new interval.

### Pattern 2: Insert an Interval
Walk through sorted intervals. Add all that come before the new interval. Merge all that overlap with it. Add all that come after.

### Pattern 3: Find Conflicts / Non-Overlapping Count
Sort by end time. Greedily pick intervals that end earliest. Count conflicts.

### Pattern 4: Meeting Rooms (Min Resources)
Use a min-heap or sort start/end times separately to track how many overlapping intervals exist at any point.


## Interval Manipulation in Java

Intervals are usually represented as int[][] where each row is [start, end].

Sorting by start time:
```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
```

Sorting by end time:
```java
Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
```

Using a min-heap on end times:
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```


## Common Mistakes

1. Off-by-one errors: Is [1,2] and [2,3] overlapping? Read the problem carefully. Sometimes touching edges count as overlap, sometimes they don't.
2. Forgetting to sort. Almost every interval problem needs sorting first.
3. Modifying the result list while iterating. Use the last element in your result list as the "current" interval.


## Problems to Practice

| Problem | Difficulty | Core Idea |
|---|---|---|
| Merge Intervals (LC #56) | Medium | Sort + merge overlapping |
| Insert Interval (LC #57) | Medium | Three-phase insert |
| Non-Overlapping Intervals (LC #435) | Medium | Sort by end + greedy |
| Meeting Rooms (LC #252) | Easy | Sort + check any overlap |
| Meeting Rooms II (LC #253) | Medium | Min-heap for concurrent meetings |
| Minimum Interval to Include Query (LC #1851) | Hard | Sort + priority queue |
