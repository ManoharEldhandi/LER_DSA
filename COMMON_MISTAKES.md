# Common Mistakes and Debugging Checklist

These are the bugs that waste the most time in interviews and on LeetCode.
Read this file once a week until these checks become automatic.


---


## Java-Specific Bugs


### 1. String Comparison with ==

WRONG:
```java
if (s1 == s2)  // Compares references, not content
```

RIGHT:
```java
if (s1.equals(s2))  // Compares actual string content
```

This is the #1 Java bug in interviews. String == checks if two variables
point to the same object in memory, NOT if they have the same characters.


### 2. Integer Overflow

WRONG:
```java
int mid = (left + right) / 2;  // Can overflow if left + right > Integer.MAX_VALUE
```

RIGHT:
```java
int mid = left + (right - left) / 2;
```

Also watch out for:
```java
int result = a * b;  // If a and b are large, this overflows silently
long result = (long) a * b;  // Cast BEFORE multiplication
```


### 3. Modifying Collection While Iterating

WRONG:
```java
for (int key : map.keySet()) {
    if (someCondition) map.remove(key);  // ConcurrentModificationException
}
```

RIGHT:
```java
Iterator<Integer> it = map.keySet().iterator();
while (it.hasNext()) {
    int key = it.next();
    if (someCondition) it.remove();
}
```

Or just build a list of keys to remove first, then remove them after the loop.


### 4. Null Pointer Exceptions

Always check for null before accessing:
```java
if (node != null && node.left != null)  // Safe
if (node.left != null && node != null)  // WRONG order, crashes if node is null
```

Common places NPE hides:
- Tree problems: forgot to check `if (root == null)`
- Linked list: forgot to check `if (head == null)` or `if (current.next == null)`
- HashMap: `map.get(key)` returns null if key doesn't exist
  Use `map.getOrDefault(key, defaultValue)` instead


### 5. Array Index Out of Bounds

Common causes:
- Loop goes to `i <= arr.length` instead of `i < arr.length`
- Accessing `arr[i + 1]` without checking `i + 1 < arr.length`
- Negative index from `i - 1` when `i = 0`

Prevention: Before accessing any index, mentally check the bounds.


### 6. Comparing Objects vs Primitives

```java
Integer a = 128;
Integer b = 128;
System.out.println(a == b);       // false! (Integer caching only for -128 to 127)
System.out.println(a.equals(b));  // true
```

For Integer, Long, etc., always use `.equals()` for comparison.
For int, long, etc. (primitives), use `==`.


### 7. Sorting with Subtraction Overflow

WRONG:
```java
Arrays.sort(arr, (a, b) -> a - b);  // Overflows if a is very negative and b is very positive
```

RIGHT:
```java
Arrays.sort(arr, (a, b) -> Integer.compare(a, b));
```

Or for simple cases, the subtraction is fine. But know about this edge case.


---


## Algorithm-Specific Bugs


### Binary Search Off-by-One

The most common binary search bugs:
1. Should it be `left <= right` or `left < right`?
2. Should it be `mid + 1` or `mid`?
3. Am I returning `left` or `right`?

Rule of thumb:
- If searching for exact match: `while (left <= right)`, return when found
- If searching for a boundary (first >= target): `while (left < right)`,
  return `left` at the end


### Forgetting to Clone/Copy

WRONG:
```java
result.add(currentList);  // Adds a REFERENCE, not a copy
currentList.add(5);       // This modifies what's already in result!
```

RIGHT:
```java
result.add(new ArrayList<>(currentList));  // Add a copy
```

This happens ALL the time in backtracking problems.


### Graph: Not Marking as Visited Before Adding to Queue

WRONG:
```java
queue.offer(neighbor);
// ... later when processing ...
visited.add(neighbor);  // By now, neighbor might be added multiple times
```

RIGHT:
```java
visited.add(neighbor);  // Mark BEFORE adding to queue
queue.offer(neighbor);
```


### DP: Wrong Base Case

If your DP solution gives wrong answers, check the base case first.
Common issues:
- dp[0] should be 0 or 1? Depends on the problem.
- dp[0][j] and dp[i][0] -- did you initialize them correctly?
- Off-by-one: is dp[n] the answer or dp[n-1]?


### Backtracking: Forgetting to Undo the Choice

The template is: choose -> explore -> un-choose.

WRONG:
```java
path.add(nums[i]);
backtrack(path, i + 1);
// Forgot to remove -- path keeps growing
```

RIGHT:
```java
path.add(nums[i]);
backtrack(path, i + 1);
path.remove(path.size() - 1);  // Undo the choice
```


---


## Interview-Specific Mistakes


### 1. Jumping to Code Without a Plan

This is the #1 reason people fail interviews. Always spend 5-10 minutes
discussing the approach BEFORE writing code. The interviewer wants to see
your thinking, not just your code.


### 2. Not Handling Edge Cases

Before coding, list edge cases out loud:
- What if the input is empty?
- What if there's only one element?
- What if all elements are the same?

Handle them explicitly at the top of your function.


### 3. Writing Unreadable Code

Use meaningful variable names:
- `left`, `right` instead of `i`, `j` for two pointers
- `count`, `freq` instead of `c`
- `result` instead of `r`

The interviewer needs to read your code. Make it easy for them.


### 4. Not Testing Your Solution

After coding, trace through at least one example step by step.
Say: "Let me trace through this with the example..."

This shows thoroughness and often catches bugs.


### 5. Panicking When Stuck

Getting stuck is NORMAL. Even experienced engineers get stuck.
What matters is how you handle it:

- "I'm not sure about the optimal approach yet. Let me think about
  what data structure would help here..."
- "The brute force would be O(n^2). Can I reduce the work by
  precomputing something?"
- "This reminds me of [pattern]. Let me try that approach."


---


## Pre-Submit Checklist (Use This Every Time)

Before you submit on LeetCode or say "I'm done" in an interview:

1. [ ] Did I handle null/empty input?
2. [ ] Are my loop bounds correct? (< vs <=, start vs end)
3. [ ] Am I using .equals() for String and Object comparison?
4. [ ] Could any operation overflow? (multiplication, addition)
5. [ ] Did I test with the given examples?
6. [ ] Did I test with an edge case?
7. [ ] What is the time complexity?
8. [ ] What is the space complexity?
