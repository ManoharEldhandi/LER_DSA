import java.util.*;

/*
 * ============================================================================
 * JAVA FOR DSA -- Everything You Need to Know
 * ============================================================================
 *
 * This file covers all the Java collections, methods, and syntax tricks you
 * will use constantly in DSA problems. Think of this as your quick reference.
 *
 * Java has a rich Collections Framework. The key data structures you will
 * use in interviews are:
 *
 *   ArrayList    -- Dynamic array (like Python's list)
 *   HashMap      -- Key-value pairs with O(1) average lookup
 *   HashSet      -- Unique elements with O(1) average lookup
 *   LinkedList   -- Doubly linked list (also works as Queue/Deque)
 *   PriorityQueue-- Min-heap by default
 *   Stack        -- LIFO structure (though Deque is preferred)
 *   ArrayDeque   -- Double-ended queue (better than Stack and LinkedList)
 *   TreeMap      -- Sorted key-value pairs (O(log n) operations)
 *   TreeSet      -- Sorted unique elements (O(log n) operations)
 *
 * ============================================================================
 */


public class JavaForDSA {

    public static void main(String[] args) {

        // ==================================================================
        // ARRAYS
        // ==================================================================
        // Arrays in Java have a fixed size. Once created, you cannot add or
        // remove elements. But they give you O(1) access by index.

        // Declaration and initialization
        int[] nums = new int[5];            // Array of 5 zeros
        int[] values = {1, 2, 3, 4, 5};    // Array with initial values
        int[][] grid = new int[3][4];       // 2D array: 3 rows, 4 columns

        // Common operations
        int length = values.length;         // Length (it is a field, not a method)
        Arrays.sort(values);                // Sort in-place: O(n log n)
        Arrays.fill(nums, -1);             // Fill entire array with -1

        // Copying arrays
        int[] copy = Arrays.copyOf(values, values.length);
        int[] partial = Arrays.copyOfRange(values, 1, 4); // index 1 to 3

        // Converting array to string for printing
        System.out.println("Array: " + Arrays.toString(values));

        // Important: Arrays.sort() on primitives uses Dual-Pivot QuickSort.
        // Arrays.sort() on objects uses TimSort (stable, O(n log n)).


        // ==================================================================
        // ARRAYLIST -- Dynamic Array
        // ==================================================================
        // Use this when you need a resizable array. It grows automatically.
        // Access by index is O(1). Adding at the end is O(1) amortized.
        // Adding/removing in the middle is O(n) because elements shift.

        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);                   // Add to end: O(1)
        list.add(20);
        list.add(30);
        list.add(1, 15);               // Insert at index 1: O(n)
        list.get(0);                    // Get by index: O(1)
        list.set(0, 99);               // Update by index: O(1)
        list.remove(Integer.valueOf(15)); // Remove by value: O(n)
        list.remove(0);                // Remove by index: O(n)
        list.size();                    // Size
        list.isEmpty();                 // Is it empty?
        list.contains(20);             // Contains? O(n)

        // Sorting an ArrayList
        Collections.sort(list);                      // Ascending
        Collections.sort(list, Collections.reverseOrder()); // Descending

        // Custom sort with lambda (very useful in interviews)
        // Sort by absolute value:
        // Collections.sort(list, (a, b) -> Math.abs(a) - Math.abs(b));

        // Convert between array and ArrayList
        Integer[] arr = list.toArray(new Integer[0]);
        ArrayList<Integer> fromArray = new ArrayList<>(Arrays.asList(1, 2, 3));

        System.out.println("ArrayList: " + list);


        // ==================================================================
        // HASHMAP -- Key-Value Store
        // ==================================================================
        // This is your single most useful data structure in interviews.
        // O(1) average for put, get, containsKey, remove.
        // Use it for: counting frequency, storing complements, caching results.

        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 3);           // Insert/update: O(1)
        map.put("banana", 5);
        map.get("apple");              // Get value: O(1), returns null if absent
        map.getOrDefault("cherry", 0); // Get with default: O(1)
        map.containsKey("apple");      // Check key exists: O(1)
        map.containsValue(3);          // Check value exists: O(n) -- avoid this
        map.remove("banana");          // Remove by key: O(1)
        map.size();

        // Iterating over a HashMap
        map.put("banana", 5);
        map.put("cherry", 2);

        // Iterate over keys
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }

        // Iterate over entries (more efficient)
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Common pattern: counting frequency
        String word = "hello";
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : word.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        System.out.println("Frequency: " + freq);


        // ==================================================================
        // HASHSET -- Unique Elements
        // ==================================================================
        // Like a HashMap but only stores keys (no values).
        // O(1) average for add, contains, remove.
        // Use it for: checking duplicates, checking membership.

        HashSet<Integer> set = new HashSet<>();
        set.add(1);                    // Add: O(1)
        set.add(2);
        set.add(1);                    // Duplicate ignored
        set.contains(1);              // Check: O(1)
        set.remove(2);                // Remove: O(1)
        set.size();                    // Size

        System.out.println("Set: " + set);


        // ==================================================================
        // LINKEDLIST -- Doubly Linked List
        // ==================================================================
        // O(1) add/remove at both ends.
        // O(n) access by index (must traverse from head or tail).
        // Also implements Queue and Deque interfaces.

        LinkedList<Integer> linked = new LinkedList<>();
        linked.addFirst(1);            // Add at head: O(1)
        linked.addLast(3);             // Add at tail: O(1)
        linked.add(2);                 // Add at tail: O(1)
        linked.getFirst();             // Peek at head: O(1)
        linked.getLast();              // Peek at tail: O(1)
        linked.removeFirst();          // Remove from head: O(1)
        linked.removeLast();           // Remove from tail: O(1)

        System.out.println("LinkedList: " + linked);


        // ==================================================================
        // STACK -- Last In, First Out (LIFO)
        // ==================================================================
        // Java has a Stack class, but in interviews ArrayDeque is preferred
        // because Stack is synchronized (slower) and extends Vector.
        // Use ArrayDeque as your stack.

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);                 // Push: O(1)
        stack.push(2);
        stack.push(3);
        stack.peek();                  // Look at top without removing: O(1)
        stack.pop();                   // Remove from top: O(1)
        stack.isEmpty();               // Check if empty

        System.out.println("Stack top: " + stack.peek());


        // ==================================================================
        // QUEUE -- First In, First Out (FIFO)
        // ==================================================================
        // Used heavily in BFS. Use ArrayDeque or LinkedList.

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);                // Enqueue: O(1)
        queue.offer(2);
        queue.offer(3);
        queue.peek();                  // Look at front: O(1)
        queue.poll();                  // Dequeue: O(1)
        queue.isEmpty();

        System.out.println("Queue front: " + queue.peek());


        // ==================================================================
        // PRIORITYQUEUE -- Min-Heap
        // ==================================================================
        // By default, Java's PriorityQueue is a min-heap (smallest on top).
        // O(log n) for add and poll. O(1) for peek.
        // For a max-heap, use Collections.reverseOrder().

        // Min-heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(5);
        minHeap.offer(1);
        minHeap.offer(3);
        System.out.println("Min-heap top: " + minHeap.peek()); // 1

        // Max-heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(5);
        maxHeap.offer(1);
        maxHeap.offer(3);
        System.out.println("Max-heap top: " + maxHeap.peek()); // 5

        // Custom comparator (sort by second element of array)
        PriorityQueue<int[]> customHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);


        // ==================================================================
        // TREEMAP -- Sorted Key-Value Store
        // ==================================================================
        // Like HashMap but keys are always sorted. O(log n) for all operations.
        // Useful when you need ordered keys or range queries.

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "three");
        treeMap.put(1, "one");
        treeMap.put(2, "two");
        treeMap.firstKey();            // Smallest key: O(log n)
        treeMap.lastKey();             // Largest key: O(log n)
        treeMap.floorKey(2);           // Largest key <= 2
        treeMap.ceilingKey(2);         // Smallest key >= 2

        System.out.println("TreeMap: " + treeMap); // Prints in sorted order


        // ==================================================================
        // STRING OPERATIONS
        // ==================================================================
        // Strings in Java are IMMUTABLE. Every modification creates a new
        // string. For building strings in a loop, use StringBuilder.

        String s = "Hello World";
        s.length();                    // Length
        s.charAt(0);                   // Character at index: 'H'
        s.substring(0, 5);            // Substring [0, 5): "Hello"
        s.indexOf("World");           // First occurrence: 6
        s.contains("World");          // true
        s.toLowerCase();              // "hello world"
        s.toUpperCase();              // "HELLO WORLD"
        s.trim();                      // Remove leading/trailing whitespace
        s.split(" ");                 // Split into array: ["Hello", "World"]
        s.toCharArray();              // Convert to char array

        // IMPORTANT: String comparison
        // Use .equals(), NEVER use == for strings.
        String a1 = "hello";
        String a2 = new String("hello");
        // a1 == a2 might be false (compares references)
        // a1.equals(a2) is true (compares content)

        // StringBuilder -- for efficient string building in loops
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        sb.reverse();                   // Reverse in place
        String result = sb.toString();  // Convert to String

        System.out.println("StringBuilder: " + result);


        // ==================================================================
        // USEFUL TRICKS FOR INTERVIEWS
        // ==================================================================

        // Integer min and max values
        int maxInt = Integer.MAX_VALUE;   // 2,147,483,647
        int minInt = Integer.MIN_VALUE;   // -2,147,483,648

        // Converting between types
        int num = Integer.parseInt("123");       // String to int
        String str = String.valueOf(123);         // int to String
        char ch = '5';
        int digit = ch - '0';                     // char digit to int: 5

        // Character checks
        Character.isLetter('a');       // true
        Character.isDigit('5');        // true
        Character.isLetterOrDigit('a');// true
        Character.toLowerCase('A');    // 'a'

        // Math utilities
        Math.max(3, 5);                // 5
        Math.min(3, 5);                // 3
        Math.abs(-5);                  // 5
        Math.pow(2, 10);              // 1024.0
        Math.sqrt(16);                // 4.0

        // Swap trick (no temp variable needed for primitives)
        int x = 1, y = 2;
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        // Now x = 2, y = 1
        // But honestly, just use a temp variable. It is clearer.

        System.out.println("\nAll Java DSA basics covered.");
    }
}
