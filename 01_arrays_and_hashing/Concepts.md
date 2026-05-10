# Arrays and Hashing -- Concepts

## What is an Array?

An array is the most basic data structure. It is a contiguous block of memory
that stores elements of the same type. Because the elements are stored next to
each other in memory, you can access any element instantly if you know its index.

Think of it like a row of numbered lockers. If you want locker number 5, you go
straight to it. You do not need to check lockers 1 through 4 first.

Key properties:
  - Fixed size (in Java, once you create an array, its size cannot change)
  - O(1) access by index (this is the main advantage)
  - O(n) to search for a value (you might have to check every element)
  - O(n) to insert or delete in the middle (elements need to shift)
  - Elements are stored in contiguous memory


## What is a HashMap?

A HashMap stores key-value pairs. You give it a key, and it gives you back the
value associated with that key. The magic is that it does this in O(1) average
time.

How does it work internally? It uses a hash function to convert the key into an
array index. When you say map.put("apple", 5), Java computes a hash of "apple"
which gives a number, say 42. It then stores the value 5 at index 42 of an
internal array. When you later say map.get("apple"), it hashes "apple" again,
gets 42, and looks up index 42.

What about collisions? Sometimes two different keys hash to the same index.
Java handles this by storing a linked list (or a balanced tree for large lists)
at each index. In the worst case, everything hashes to the same index and
lookups become O(n). But with a good hash function, this almost never happens.

Key properties:
  - O(1) average for put, get, containsKey, remove
  - O(n) space to store n entries
  - Keys must be unique (putting a duplicate key overwrites the old value)
  - Keys can be null (only one null key allowed)
  - Not ordered (if you need order, use LinkedHashMap or TreeMap)


## What is a HashSet?

A HashSet is like a HashMap where you only care about the keys, not the values.
It stores unique elements with O(1) average lookup.

Internally, Java's HashSet is actually backed by a HashMap. When you add an
element, it stores it as a key in the HashMap with a dummy value.

Key properties:
  - O(1) average for add, contains, remove
  - No duplicates allowed
  - Not ordered


## When to Use What

  Need fast access by index?                --> Array or ArrayList
  Need fast lookup by key?                  --> HashMap
  Need to check if something exists?        --> HashSet
  Need to count frequency of elements?      --> HashMap (element -> count)
  Need to find pairs that satisfy a condition? --> HashMap (store complement)
  Need to detect duplicates?                --> HashSet
  Need to group elements by some property?  --> HashMap (property -> list)


---


## Problem Set

The problems are listed in the order you should solve them. Start from the top.

### Easy -- Build Your Foundation

  1. Two Sum                          LeetCode #1
     Pattern: HashMap for complement lookup
     This is the most important easy problem. Master it completely.

  2. Contains Duplicate               LeetCode #217
     Pattern: HashSet for duplicate detection

  3. Valid Anagram                    LeetCode #242
     Pattern: HashMap for frequency counting

  4. Best Time to Buy and Sell Stock  LeetCode #121
     Pattern: Track running minimum

  5. Majority Element                 LeetCode #169
     Pattern: Boyer-Moore Voting Algorithm

  6. Move Zeroes                      LeetCode #283
     Pattern: Two-pointer partitioning

  7. Plus One                         LeetCode #66
     Pattern: Array manipulation with carry

  8. Single Number                    LeetCode #136
     Pattern: XOR bit manipulation


### Medium -- Core Interview Problems

  1. Product of Array Except Self     LeetCode #238
     Pattern: Prefix and suffix products
     Asked very frequently. Must know.

  2. Maximum Subarray                 LeetCode #53
     Pattern: Kadane's Algorithm
     Asked very frequently. Must know.

  3. Group Anagrams                   LeetCode #49
     Pattern: HashMap with sorted string as key

  4. Top K Frequent Elements          LeetCode #347
     Pattern: HashMap + Heap or Bucket Sort

  5. Longest Consecutive Sequence     LeetCode #128
     Pattern: HashSet for O(1) lookups

  6. Subarray Sum Equals K            LeetCode #560
     Pattern: Prefix sum + HashMap
     This pattern is extremely important.

  7. Encode and Decode Strings        LeetCode #271
     Pattern: String encoding

  8. Rotate Array                     LeetCode #189
     Pattern: Reverse trick

  9. Set Matrix Zeroes                LeetCode #73
     Pattern: Using first row/column as markers

  10. Spiral Matrix                   LeetCode #54
      Pattern: Layer-by-layer simulation


### Hard -- Stretch Goals

  1. First Missing Positive           LeetCode #41
     Pattern: Using array indices as hash

  2. Trapping Rain Water              LeetCode #42
     Pattern: Two pointers or Stack
     Asked frequently in coding interviews.

  3. Minimum Window Substring         LeetCode #76
     Pattern: Sliding window with frequency map
