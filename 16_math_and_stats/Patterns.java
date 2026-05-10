import java.util.*;

/*
 * ============================================================================
 * MATH AND STATS -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Cycle Detection on Digits -- Happy Number
     * -----------------------------------------------------------------------
     *
     * LeetCode #202 -- Happy Number
     *
     * A number is "happy" if repeatedly replacing it with the sum of
     * squares of its digits eventually reaches 1. If it loops endlessly
     * without reaching 1, it is not happy.
     *
     * Example: 19 -> 1^2 + 9^2 = 82 -> 64+4 = 68 -> 36+64 = 100
     *       -> 1 -> happy!
     *
     * APPROACH:
     * This is a cycle detection problem in disguise. Use Floyd's slow/fast
     * pointer approach. If slow and fast meet and it is not 1, there is a
     * cycle and the number is not happy.
     *
     * Time: O(log n) per step, Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = digitSquareSum(n);

        while (fast != 1 && slow != fast) {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(digitSquareSum(fast));
        }

        return fast == 1;
    }

    private static int digitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Binary Exponentiation -- Pow(x, n)
     * -----------------------------------------------------------------------
     *
     * LeetCode #50 -- Pow(x, n)
     *
     * Compute x raised to the power n.
     *
     * NAIVE: Multiply x by itself n times -> O(n). Too slow for large n.
     *
     * FAST POWER (Binary Exponentiation):
     * x^n = (x^(n/2))^2        if n is even
     * x^n = x * (x^(n/2))^2    if n is odd
     *
     * This cuts n in half each time -> O(log n).
     *
     * Edge cases:
     * - n < 0: compute 1 / x^(-n)
     * - n = Integer.MIN_VALUE: be careful with -n overflow
     *
     * Time: O(log n), Space: O(1) iterative
     * -----------------------------------------------------------------------
     */
    public static double myPow(double x, int n) {
        long power = n; // Use long to handle Integer.MIN_VALUE

        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double result = 1.0;

        while (power > 0) {
            if (power % 2 == 1) {
                result *= x; // Odd power: multiply once
            }
            x *= x;       // Square x
            power /= 2;   // Halve the power
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: In-Place Matrix -- Rotate Image
     * -----------------------------------------------------------------------
     *
     * LeetCode #48 -- Rotate Image
     *
     * Rotate an n x n matrix 90 degrees clockwise, in place.
     *
     * TRICK: Transpose the matrix (swap rows and columns), then reverse
     * each row. This gives a 90-degree clockwise rotation.
     *
     * Why it works:
     *   Original:    Transpose:    Reverse rows:
     *   1 2 3        1 4 7         7 4 1
     *   4 5 6   ->   2 5 8    ->   8 5 2
     *   7 8 9        3 6 9         9 6 3
     *
     * Time: O(n^2), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Boundary Shrinking -- Spiral Matrix
     * -----------------------------------------------------------------------
     *
     * LeetCode #54 -- Spiral Matrix
     *
     * Given an m x n matrix, return all elements in spiral order.
     *
     * APPROACH:
     * Maintain four boundaries: top, bottom, left, right.
     * Traverse right, then down, then left, then up.
     * After each direction, shrink the corresponding boundary.
     *
     * Time: O(m * n), Space: O(1) extra (excluding result)
     * -----------------------------------------------------------------------
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse right
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            // Traverse down
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            // Traverse left (if rows remain)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // Traverse up (if columns remain)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Use First Row/Col as Markers -- Set Matrix Zeroes
     * -----------------------------------------------------------------------
     *
     * LeetCode #73 -- Set Matrix Zeroes
     *
     * If an element is 0, set its entire row and column to 0. Do it in place.
     *
     * NAIVE: Use O(m + n) space for marking rows and columns.
     *
     * OPTIMAL: Use the first row and first column of the matrix itself as
     * markers. Need a separate variable for whether the first row/col
     * should be zeroed.
     *
     * APPROACH:
     * 1. Check if first row or first column has any zeros (save this info)
     * 2. Use first row/col to mark which rows/cols need zeroing
     * 3. Zero cells based on markers
     * 4. Handle first row and first column last
     *
     * Time: O(m * n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check if first row has zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) { firstRowZero = true; break; }
        }

        // Check if first column has zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) { firstColZero = true; break; }
        }

        // Mark zeros in first row/col
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Zero cells based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Handle first row
        if (firstRowZero) {
            for (int j = 0; j < n; j++) matrix[0][j] = 0;
        }

        // Handle first column
        if (firstColZero) {
            for (int i = 0; i < m; i++) matrix[i][0] = 0;
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Sieve of Eratosthenes -- Count Primes
     * -----------------------------------------------------------------------
     *
     * LeetCode #204 -- Count Primes
     *
     * Count the number of primes less than n.
     *
     * APPROACH:
     * Create a boolean array of size n. Start from 2. For each prime p,
     * mark all multiples of p as not prime. Count the unmarked ones.
     *
     * Optimization: start marking from p*p (smaller multiples already marked).
     *
     * Time: O(n log log n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int countPrimes(int n) {
        if (n <= 2) return 0;

        boolean[] notPrime = new boolean[n];

        for (int i = 2; (long) i * i < n; i++) {
            if (!notPrime[i]) {
                // Mark all multiples of i starting from i*i
                for (int j = i * i; j < n; j += i) {
                    notPrime[j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) count++;
        }

        return count;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: Grade School Multiplication -- Multiply Strings
     * -----------------------------------------------------------------------
     *
     * LeetCode #43 -- Multiply Strings
     *
     * Multiply two numbers represented as strings. Cannot use BigInteger.
     *
     * APPROACH:
     * Simulate grade-school multiplication. If num1 has m digits and num2
     * has n digits, the result has at most m + n digits.
     *
     * Key insight: digit at position i in num1 and position j in num2
     * contributes to positions i+j and i+j+1 in the result.
     *
     * Time: O(m * n), Space: O(m + n)
     * -----------------------------------------------------------------------
     */
    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] result = new int[m + n];

        // Multiply digit by digit (right to left)
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int pos1 = i + j;     // Tens position
                int pos2 = i + j + 1; // Ones position

                int sum = product + result[pos2];
                result[pos2] = sum % 10;
                result[pos1] += sum / 10;
            }
        }

        // Build result string, skip leading zeros
        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            if (sb.length() == 0 && digit == 0) continue;
            sb.append(digit);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }


    // Helper to print a matrix
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }


    public static void main(String[] args) {

        System.out.println("--- Happy Number ---");
        System.out.println(isHappy(19));   // true
        System.out.println(isHappy(2));    // false

        System.out.println("\n--- Pow(x, n) ---");
        System.out.println(myPow(2.0, 10));   // 1024.0
        System.out.println(myPow(2.0, -2));   // 0.25

        System.out.println("\n--- Rotate Image ---");
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);
        printMatrix(matrix);  // [[7,4,1],[8,5,2],[9,6,3]]

        System.out.println("\n--- Spiral Matrix ---");
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(spiralOrder(grid));
        // [1, 2, 3, 6, 9, 8, 7, 4, 5]

        System.out.println("\n--- Set Matrix Zeroes ---");
        int[][] m2 = {{1,1,1},{1,0,1},{1,1,1}};
        setZeroes(m2);
        printMatrix(m2);  // [[1,0,1],[0,0,0],[1,0,1]]

        System.out.println("\n--- Count Primes ---");
        System.out.println(countPrimes(10));   // 4 (primes: 2, 3, 5, 7)

        System.out.println("\n--- Multiply Strings ---");
        System.out.println(multiply("123", "456"));  // 56088
    }
}
