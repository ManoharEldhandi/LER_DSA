# Java Cheat Sheet for DSA Interviews

Quick reference for everything you need during practice and interviews.
Bookmark this file and come back to it often.


---


## 1. Primitive Types and Limits

```java
int     -> 32 bits, range: -2^31 to 2^31 - 1  (~2.1 billion)
long    -> 64 bits, range: -2^63 to 2^63 - 1
double  -> 64-bit floating point
char    -> 16-bit Unicode character
boolean -> true or false

// Constants
Integer.MAX_VALUE    // 2147483647
Integer.MIN_VALUE    // -2147483648
Long.MAX_VALUE
Long.MIN_VALUE
Double.MAX_VALUE
```

WATCH OUT: Integer overflow is the #1 sneaky bug in interviews.
When doing `mid = (left + right) / 2`, use `mid = left + (right - left) / 2`.


---


## 2. String Operations

```java
String s = "hello";

s.length()                    // 5
s.charAt(0)                   // 'h'
s.substring(1, 3)             // "el" (start inclusive, end exclusive)
s.indexOf('l')                // 2 (first occurrence)
s.contains("ell")             // true
s.equals("hello")             // true (USE THIS, not ==)
s.compareTo("world")          // negative (lexicographic comparison)
s.toCharArray()               // char[] {'h','e','l','l','o'}
s.split(",")                  // split by delimiter
s.trim()                      // remove leading/trailing whitespace
s.toLowerCase()
s.toUpperCase()
s.replace('l', 'r')           // "herro"

// Building strings efficiently (use StringBuilder, not + in loops)
StringBuilder sb = new StringBuilder();
sb.append("hello");
sb.append(" world");
sb.insert(0, "say ");         // "say hello world"
sb.deleteCharAt(0);
sb.reverse();
sb.toString();                // convert back to String

// Convert between types
String.valueOf(123)           // "123"
Integer.parseInt("123")       // 123
Character.isLetterOrDigit(c)  // true if c is letter or digit
Character.isLetter(c)
Character.isDigit(c)
Character.toLowerCase(c)
```


---


## 3. Arrays

```java
// Declaration and initialization
int[] arr = new int[10];              // all zeros
int[] arr = {1, 2, 3, 4, 5};
int[][] grid = new int[3][4];        // 3 rows, 4 cols

// Common operations
arr.length                            // size (NO parentheses)
Arrays.sort(arr);                     // sort ascending O(n log n)
Arrays.sort(arr, 0, 5);              // sort a range
Arrays.fill(arr, -1);                // fill all with -1
Arrays.copyOf(arr, newLength);       // copy with new size
Arrays.toString(arr);                // "[1, 2, 3, 4, 5]" for printing
Arrays.equals(arr1, arr2);           // compare two arrays

// Sort in descending order (requires Integer[], not int[])
Integer[] boxed = {3, 1, 2};
Arrays.sort(boxed, Collections.reverseOrder());

// Sort 2D array by first element
int[][] intervals = {{3,4},{1,2},{2,3}};
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

// Binary search (array must be sorted)
int index = Arrays.binarySearch(arr, target);
// Returns index if found, negative value if not found
```


---


## 4. ArrayList

```java
List<Integer> list = new ArrayList<>();

list.add(10);                 // append
list.add(0, 5);               // insert at index 0
list.get(0);                   // access by index
list.set(0, 99);               // update at index
list.remove(0);                // remove by index (returns removed element)
list.remove(Integer.valueOf(10)); // remove by value
list.size();
list.isEmpty();
list.contains(10);
list.indexOf(10);              // first index, -1 if not found
list.sort(Comparator.naturalOrder());
list.sort(Comparator.reverseOrder());
Collections.reverse(list);
Collections.swap(list, i, j);

// Convert array to list
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

// Convert list to array
int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
```


---


## 5. HashMap and HashSet

```java
// HashMap
Map<String, Integer> map = new HashMap<>();
map.put("key", 1);
map.get("key");                        // 1
map.getOrDefault("missing", 0);        // 0
map.containsKey("key");                // true
map.containsValue(1);                  // true
map.remove("key");
map.size();
map.isEmpty();

// Iterate
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    int val = entry.getValue();
}
for (String key : map.keySet()) { }
for (int val : map.values()) { }

// Frequency counting pattern
map.put(key, map.getOrDefault(key, 0) + 1);
// Or cleaner:
map.merge(key, 1, Integer::sum);

// computeIfAbsent (great for adjacency lists)
map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);


// HashSet
Set<Integer> set = new HashSet<>();
set.add(10);
set.contains(10);                      // true
set.remove(10);
set.size();

// Set from array
Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
```


---


## 6. Stack and Queue

```java
// Stack (use ArrayDeque, not Stack class)
Deque<Integer> stack = new ArrayDeque<>();
stack.push(10);                // push to top
stack.pop();                   // remove and return top
stack.peek();                  // look at top without removing
stack.isEmpty();
stack.size();

// Queue
Queue<Integer> queue = new LinkedList<>();
queue.offer(10);               // add to back
queue.poll();                  // remove and return front
queue.peek();                  // look at front
queue.isEmpty();
queue.size();

// Deque (double-ended queue)
Deque<Integer> deque = new ArrayDeque<>();
deque.offerFirst(1);           // add to front
deque.offerLast(2);            // add to back
deque.pollFirst();             // remove from front
deque.pollLast();              // remove from back
deque.peekFirst();
deque.peekLast();
```


---


## 7. PriorityQueue (Heap)

```java
// Min-heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Custom comparator
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

pq.offer(element);            // add
pq.poll();                    // remove and return min/max
pq.peek();                    // look at min/max
pq.size();
pq.isEmpty();
```


---


## 8. LinkedList Node (Define Your Own)

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

// Tree node
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}
```


---


## 9. TreeMap and TreeSet (Sorted Collections)

```java
// TreeMap: sorted by key
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.firstKey();            // smallest key
treeMap.lastKey();             // largest key
treeMap.floorKey(5);           // largest key <= 5
treeMap.ceilingKey(5);         // smallest key >= 5

// TreeSet: sorted set
TreeSet<Integer> treeSet = new TreeSet<>();
treeSet.first();               // smallest
treeSet.last();                // largest
treeSet.floor(5);              // largest element <= 5
treeSet.ceiling(5);            // smallest element >= 5
treeSet.lower(5);              // largest element < 5 (strict)
treeSet.higher(5);             // smallest element > 5 (strict)
```


---


## 10. Common Type Conversions

```java
// char to int
int digit = c - '0';          // char '5' -> int 5
int index = c - 'a';          // char 'c' -> int 2

// int to char
char c = (char) ('a' + index);

// String to int
int n = Integer.parseInt("123");

// int to String
String s = String.valueOf(123);
String s = Integer.toString(123);

// char[] to String
String s = new String(charArray);
String s = String.valueOf(charArray);

// String to char[]
char[] arr = s.toCharArray();

// List<Integer> to int[]
int[] arr = list.stream().mapToInt(i -> i).toArray();

// int[] to List<Integer>
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
```


---


## 11. Useful Math

```java
Math.max(a, b)
Math.min(a, b)
Math.abs(a)
Math.pow(base, exp)            // returns double
Math.sqrt(n)                   // returns double
Math.log(n)                    // natural log
Math.ceil(3.2)                 // 4.0
Math.floor(3.8)                // 3.0

// Integer division rounds toward zero in Java
7 / 2 = 3       (not 3.5)
-7 / 2 = -3     (not -4)

// Modulo with negatives
-7 % 3 = -1     (Java keeps the sign of the dividend)
// To get positive modulo: ((n % m) + m) % m
```


---


## 12. Bit Operations

```java
a & b          // AND
a | b          // OR
a ^ b          // XOR
~a             // NOT (flips all bits)
a << k         // left shift by k
a >> k         // right shift by k (arithmetic, preserves sign)
a >>> k        // unsigned right shift

// Common tricks
n & 1          // check if odd
n & (n - 1)    // remove lowest set bit
n & (-n)       // isolate lowest set bit
1 << k         // 2^k
```


---


## 13. Edge Cases to Always Check

Before submitting any solution, mentally check these:

1. Empty input (null, empty array, empty string)
2. Single element
3. Two elements
4. All elements the same
5. Already sorted / reverse sorted
6. Negative numbers
7. Zero
8. Integer overflow (especially with multiplication or addition)
9. Duplicate elements
10. Very large input (does your solution handle 10^5 elements?)
