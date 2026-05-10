import java.util.*;

/*
 * ============================================================================
 * BIT MANIPULATION -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: XOR Everything -- Single Number
     * -----------------------------------------------------------------------
     *
     * LeetCode #136 -- Single Number
     *
     * Every element appears twice except one. Find the unique element.
     *
     * Example: [2, 2, 1] -> 1
     *
     * WHY XOR WORKS:
     * a ^ a = 0 (pairs cancel out)
     * a ^ 0 = a (identity)
     * So XOR of all elements leaves only the unpaired one.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Clear Lowest Set Bit -- Number of 1 Bits
     * -----------------------------------------------------------------------
     *
     * LeetCode #191 -- Number of 1 Bits (Hamming Weight)
     *
     * Count the number of 1 bits in a number's binary representation.
     *
     * Example: n = 11 (1011) -> 3
     *
     * TRICK: n & (n - 1) removes the lowest set bit.
     *   11 = 1011
     *   10 = 1010
     *   11 & 10 = 1010 (removed the last 1)
     *
     * Keep doing this until n is 0. Count iterations.
     *
     * Time: O(k) where k = number of set bits, Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // Remove lowest set bit
            count++;
        }
        return count;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: DP with Bit Trick -- Counting Bits
     * -----------------------------------------------------------------------
     *
     * LeetCode #338 -- Counting Bits
     *
     * Given n, return an array where ans[i] = number of 1's in binary of i,
     * for 0 <= i <= n.
     *
     * Example: n = 5 -> [0, 1, 1, 2, 1, 2]
     *
     * DP RELATION: ans[i] = ans[i & (i - 1)] + 1
     * Why: i & (i-1) removes one set bit, so it has exactly one fewer 1.
     *
     * Alternative: ans[i] = ans[i >> 1] + (i & 1)
     * Why: i >> 1 is i without the last bit. (i & 1) adds 1 if last bit is set.
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Bit-by-Bit Construction -- Reverse Bits
     * -----------------------------------------------------------------------
     *
     * LeetCode #190 -- Reverse Bits
     *
     * Reverse the bits of a 32-bit unsigned integer.
     *
     * Example: 00000010100101000001111010011100
     *       -> 00111001011110000010100101000000
     *
     * APPROACH:
     * Extract bits from n one at a time (right to left) and build the
     * result from left to right by shifting result left and ORing with
     * the extracted bit.
     *
     * Time: O(32) = O(1), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result <<= 1;        // Make room for next bit
            result |= (n & 1);   // Add the last bit of n
            n >>= 1;             // Move to next bit of n
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: XOR with Indices -- Missing Number
     * -----------------------------------------------------------------------
     *
     * LeetCode #268 -- Missing Number
     *
     * Array contains n distinct numbers from [0, n]. Find the missing one.
     *
     * Example: [3, 0, 1] -> 2
     *
     * APPROACH 1 (XOR):
     * XOR all indices [0..n] with all array elements. Everything pairs up
     * and cancels except the missing number.
     *
     * APPROACH 2 (Math):
     * Expected sum = n * (n + 1) / 2. Actual sum = sum of array.
     * Missing = expected - actual.
     *
     * We'll show the XOR approach.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int missingNumber(int[] nums) {
        int xor = nums.length; // Start with n (since indices go 0 to n-1)

        for (int i = 0; i < nums.length; i++) {
            xor ^= i ^ nums[i];
        }

        return xor;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Bit-Level Arithmetic -- Sum of Two Integers
     * -----------------------------------------------------------------------
     *
     * LeetCode #371 -- Sum of Two Integers
     *
     * Add two integers WITHOUT using + or -.
     *
     * HOW ADDITION WORKS IN BINARY:
     * a ^ b gives the sum without carries
     * (a & b) << 1 gives the carries
     *
     * Example: a = 5 (101), b = 3 (011)
     *   sum without carry: 101 ^ 011 = 110
     *   carry:            (101 & 011) << 1 = (001) << 1 = 010
     *   Now add 110 and 010:
     *   sum: 110 ^ 010 = 100
     *   carry: (110 & 010) << 1 = (010) << 1 = 100
     *   Continue until carry is 0...
     *   Result: 1000 = 8 ... wait that's wrong.
     *
     * Let me redo: 5 + 3 = 8. 1000 in binary = 8. That IS correct!
     *
     * Keep going until carry is 0.
     *
     * Time: O(32) worst case, Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;     // Sum without carry
            b = carry;     // Carry becomes the new b
        }
        return a;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: Reverse Integer (Math + Overflow)
     * -----------------------------------------------------------------------
     *
     * LeetCode #7 -- Reverse Integer
     *
     * Given a 32-bit signed integer, reverse its digits. Return 0 if
     * the result overflows.
     *
     * Example: 123 -> 321, -123 -> -321, 120 -> 21
     *
     * This is more of a math problem than pure bit manipulation, but it
     * often appears alongside bit problems in interviews.
     *
     * APPROACH:
     * Build the reversed number digit by digit. Check for overflow BEFORE
     * multiplying by 10.
     *
     * Time: O(log n) digits, Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int digit = x % 10;

            // Check overflow BEFORE it happens
            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (result < Integer.MIN_VALUE / 10 ||
                (result == Integer.MIN_VALUE / 10 && digit < -8)) return 0;

            result = result * 10 + digit;
            x /= 10;
        }

        return result;
    }


    public static void main(String[] args) {

        System.out.println("--- Single Number ---");
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));  // 4

        System.out.println("\n--- Hamming Weight ---");
        System.out.println(hammingWeight(11));  // 3 (1011)

        System.out.println("\n--- Counting Bits ---");
        System.out.println(Arrays.toString(countBits(5)));
        // [0, 1, 1, 2, 1, 2]

        System.out.println("\n--- Reverse Bits ---");
        System.out.println(reverseBits(43261596));
        // 964176192

        System.out.println("\n--- Missing Number ---");
        System.out.println(missingNumber(new int[]{3, 0, 1}));  // 2

        System.out.println("\n--- Sum of Two Integers ---");
        System.out.println(getSum(5, 3));  // 8

        System.out.println("\n--- Reverse Integer ---");
        System.out.println(reverse(123));   // 321
        System.out.println(reverse(-123));  // -321
    }
}
