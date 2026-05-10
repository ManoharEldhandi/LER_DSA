/*
 * ============================================================================
 * BIG-O ANALYSIS -- Understanding Time and Space Complexity
 * ============================================================================
 *
 * WHAT IS BIG-O?
 *
 * Big-O notation describes how the runtime or memory usage of an algorithm
 * grows as the input size increases. It does not tell you the exact time in
 * seconds -- it tells you the growth rate.
 *
 * When someone says "this algorithm is O(n)", they mean: if the input doubles
 * in size, the runtime roughly doubles too. If someone says "this is O(n^2)",
 * it means: if the input doubles, the runtime roughly quadruples.
 *
 * Why does this matter? Because in interviews, the first thing you should
 * think about after understanding a problem is: what time complexity can I
 * afford? The constraints in the problem tell you this.
 *
 *
 * ============================================================================
 * COMMON COMPLEXITIES (from fastest to slowest)
 * ============================================================================
 *
 * O(1)        Constant      - HashMap lookup, array access by index
 * O(log n)    Logarithmic   - Binary search, balanced BST operations
 * O(n)        Linear        - Single loop through the array
 * O(n log n)  Linearithmic  - Sorting (merge sort, Arrays.sort for objects)
 * O(n^2)      Quadratic     - Nested loops (comparing all pairs)
 * O(2^n)      Exponential   - Recursive subsets, brute force combinations
 * O(n!)       Factorial     - Generating all permutations
 *
 *
 * To put this in perspective with real numbers:
 *
 *   n = 1,000
 *   O(n)       = 1,000 operations          (instant)
 *   O(n log n) = 10,000 operations          (instant)
 *   O(n^2)     = 1,000,000 operations       (about 1 second)
 *   O(2^n)     = 2^1000 operations          (heat death of the universe)
 *
 *
 * ============================================================================
 * HOW TO CALCULATE BIG-O
 * ============================================================================
 *
 * Rule 1: Drop constants.
 *   O(2n) becomes O(n). O(100n) becomes O(n). Constants do not matter at
 *   scale because we care about growth rate, not exact count.
 *
 * Rule 2: Drop lower-order terms.
 *   O(n^2 + n) becomes O(n^2). O(n + log n) becomes O(n). The biggest
 *   term dominates as n gets large.
 *
 * Rule 3: Different inputs get different variables.
 *   If you have two arrays of different sizes, do not call them both "n".
 *   If array1 has size "a" and array2 has size "b":
 *     - Iterating both sequentially: O(a + b)
 *     - Nested loops over both: O(a * b)
 *
 * Rule 4: Identify the pattern in your code.
 *   Single loop over n elements:           O(n)
 *   Nested loop (loop inside a loop):      O(n^2)
 *   Loop where index doubles/halves:       O(log n)
 *   Loop inside a halving loop:            O(n log n)
 *   Recursive function with 2 branches:    O(2^n)
 *
 *
 * ============================================================================
 * THE CONSTRAINT TRICK -- What Complexity Can I Use?
 * ============================================================================
 *
 * LeetCode problems always give you constraints like "1 <= n <= 10^5". This
 * tells you what time complexity your solution needs to be.
 *
 * A modern computer does roughly 10^8 simple operations per second.
 * If the time limit is 1-2 seconds, your total operations should be
 * under about 10^8.
 *
 *   n <= 10           -->  O(n!) or O(2^n) is fine. Brute force everything.
 *   n <= 20           -->  O(2^n) is fine. Bitmask, backtracking.
 *   n <= 100          -->  O(n^3) is fine. Triple nested loops.
 *   n <= 1,000        -->  O(n^2) is fine. Double nested loops.
 *   n <= 10,000       -->  O(n^2) is borderline. Be careful.
 *   n <= 100,000      -->  O(n log n) needed. Sort-based, divide and conquer.
 *   n <= 1,000,000    -->  O(n) or O(n log n). HashMap, two pointers, sorting.
 *   n <= 10^8         -->  O(n) only. Single pass.
 *   n > 10^8          -->  O(log n) or O(1). Binary search, math formula.
 *
 * THIS TABLE IS GOLD. Use it on every single problem.
 *
 *
 * ============================================================================
 * SPACE COMPLEXITY
 * ============================================================================
 *
 * Space complexity measures the extra memory your algorithm uses, not
 * counting the input itself.
 *
 *   A few variables:           O(1)
 *   An array of size n:        O(n)
 *   A 2D array of size n*m:    O(n * m)
 *   A HashMap with n entries:  O(n)
 *   A recursive call stack:    O(depth of recursion)
 *
 * ============================================================================
 */


public class BigOAnalysis {

    // ---------------------------------------------------------------
    // EXAMPLE 1: O(1) -- Constant time
    // No matter how big the array is, this takes the same time.
    // ---------------------------------------------------------------
    public static int getFirst(int[] arr) {
        return arr[0];
    }


    // ---------------------------------------------------------------
    // EXAMPLE 2: O(n) -- Linear time
    // We visit each element exactly once. If the array has n elements,
    // we do n operations.
    // ---------------------------------------------------------------
    public static int sum(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }


    // ---------------------------------------------------------------
    // EXAMPLE 3: O(n^2) -- Quadratic time
    // For each element, we look at every other element. That is
    // n * n = n^2 operations.
    // ---------------------------------------------------------------
    public static boolean hasDuplicateBrute(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }


    // ---------------------------------------------------------------
    // EXAMPLE 4: O(log n) -- Logarithmic time
    // Each step cuts the search space in half. For an array of 1024
    // elements, binary search takes at most 10 steps (log2(1024) = 10).
    // ---------------------------------------------------------------
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    // ---------------------------------------------------------------
    // EXAMPLE 5: O(n log n) -- Linearithmic time
    // Sorting algorithms like merge sort achieve this. Java's
    // Arrays.sort() for object arrays uses TimSort which is O(n log n).
    // For primitive arrays, it uses Dual-Pivot QuickSort, also O(n log n).
    // ---------------------------------------------------------------
    public static void sortExample(int[] arr) {
        java.util.Arrays.sort(arr);
    }


    // ---------------------------------------------------------------
    // EXAMPLE 6: O(2^n) -- Exponential time
    // Classic naive Fibonacci. Each call branches into two more calls.
    // For n = 40, this makes about 2^40 = 1 trillion calls.
    // This is why we need Dynamic Programming.
    // ---------------------------------------------------------------
    public static int fibNaive(int n) {
        if (n <= 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }


    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};

        System.out.println("First element: " + getFirst(arr));
        System.out.println("Sum: " + sum(arr));
        System.out.println("Has duplicate: " + hasDuplicateBrute(arr));

        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Binary search for 5: index " + binarySearch(sorted, 5));

        System.out.println("Fibonacci(10): " + fibNaive(10));
    }
}
