# Design-Style Coding Problems

## What This Means

Design-style coding problems are not full system design. They are data-structure design questions.

The interviewer asks you to build a small class with methods like:

- `get`
- `put`
- `insert`
- `remove`
- `search`
- `addWord`

The trick is usually combining two data structures so every operation is fast.

---

## The Main Skill

When a method must be O(1), ask:

1. What operation needs fast lookup? Usually HashMap.
2. What operation needs fast order update? Usually linked list or array list.
3. What operation needs min/max? Usually heap or TreeMap.
4. What operation needs prefix lookup? Usually Trie.

Most design problems are combinations:

| Problem Need | Common Structure |
|---|---|
| Fast lookup by key | HashMap |
| Fast insert/delete by position | Doubly linked list |
| Fast random access | ArrayList |
| Fast delete by value and random pick | HashMap + ArrayList |
| Fast least-recent update | HashMap + Doubly Linked List |
| Fast timestamp floor lookup | HashMap + binary search list or TreeMap |
| Prefix search | Trie |
| Min/max by priority | PriorityQueue |

---

## Pattern 1: LRU Cache

Need:

- `get(key)` in O(1)
- `put(key, value)` in O(1)
- evict least recently used item

Use:

- HashMap from key to node
- Doubly linked list for recency order

The head is most recent. The tail is least recent.

When a key is accessed, move its node to the front.

---

## Pattern 2: RandomizedSet

Need:

- `insert(val)` in O(1)
- `remove(val)` in O(1)
- `getRandom()` in O(1)

Use:

- ArrayList for random index access
- HashMap from value to index

For removal, swap the removed value with the last value, update its index, then remove the last element.

---

## Pattern 3: Time-Based Key-Value Store

Need:

- set key/value at timestamp
- get value for latest timestamp <= query timestamp

Use:

- HashMap from key to list of timestamped values
- Binary search inside the list

---

## Pattern 4: Min Stack

Need:

- push, pop, top, getMin all O(1)

Use:

- one normal stack
- one min stack

The min stack stores the minimum value seen so far.

---

## Common Mistakes

- Forgetting to update both data structures.
- Removing from the list but leaving stale map entries.
- Not handling existing keys in `put`.
- Not moving accessed LRU nodes to the front.
- Using `LinkedList` when you actually need direct node removal.
- Forgetting edge cases when capacity is 1.

---

## Problems to Practice

### Must Know

1. Min Stack (LC 155)
2. LRU Cache (LC 146)
3. Insert Delete GetRandom O(1) (LC 380)
4. Time Based Key-Value Store (LC 981)
5. Implement Trie (LC 208)
6. Design Add and Search Words Data Structure (LC 211)

### Good Stretch

1. LFU Cache (LC 460)
2. Design Twitter (LC 355)
3. Design Hit Counter (LC 362)
4. Design Browser History (LC 1472)
5. Snapshot Array (LC 1146)

---

## Interview Shortcut

For any design coding question, say:

"Let me list the required operations and their target complexity. Then I will choose data structures around those operations."

That one sentence makes your thinking clear.
