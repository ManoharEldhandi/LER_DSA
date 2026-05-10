import java.util.*;

/*
 * ============================================================================
 * GREEDY ALGORITHMS -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Track the Farthest -- Jump Game
     * -----------------------------------------------------------------------
     *
     * LeetCode #55 -- Jump Game
     *
     * Given an array where each element is the max jump length from that
     * position, determine if you can reach the last index.
     *
     * Example: [2,3,1,1,4] -> true
     *          [3,2,1,0,4] -> false (stuck at index 3)
     *
     * GREEDY IDEA:
     * Track the farthest index you can reach. Walk through the array. If your
     * current index is beyond what you can reach, you are stuck. Otherwise
     * update the farthest reachable.
     *
     * Why greedy works: if you can reach index i, you can reach everything
     * before i too. So just track the maximum reach.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static boolean canJump(int[] nums) {
        int farthest = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) return false; // Can't reach this index
            farthest = Math.max(farthest, i + nums[i]);
        }

        return true;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: BFS-Style Greedy -- Jump Game II
     * -----------------------------------------------------------------------
     *
     * LeetCode #45 -- Jump Game II
     *
     * Same setup as Jump Game, but find the MINIMUM number of jumps to reach
     * the last index. Guaranteed you can reach it.
     *
     * GREEDY IDEA:
     * Think of it like BFS levels. From your current "window" of reachable
     * positions, find the farthest you can go. That defines the next window.
     * Each window transition is one jump.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;  // End of current BFS level
        int farthest = 0;    // Farthest reachable from current level

        // We don't need to check the last index
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                // We've explored everything in the current level, jump
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Circular Greedy -- Gas Station
     * -----------------------------------------------------------------------
     *
     * LeetCode #134 -- Gas Station
     *
     * There are n gas stations in a circle. Station i has gas[i] fuel and it
     * costs cost[i] to drive to station i+1. Find the starting station index
     * to complete the circuit. Return -1 if impossible.
     *
     * GREEDY IDEA:
     * Key insight 1: If total gas >= total cost, a solution ALWAYS exists.
     * Key insight 2: If running sum goes negative at station i, then none of
     * the stations from our start to i can be the answer. Start fresh from i+1.
     *
     * Why: If you can't reach station i from some start, you also can't reach
     * it from any station between start and i (because you'd have even less
     * gas starting later in that segment).
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0;
        int currentSurplus = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            totalSurplus += gas[i] - cost[i];
            currentSurplus += gas[i] - cost[i];

            if (currentSurplus < 0) {
                // Can't reach here from current start, try next station
                start = i + 1;
                currentSurplus = 0;
            }
        }

        return totalSurplus >= 0 ? start : -1;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Last Occurrence Tracking -- Partition Labels
     * -----------------------------------------------------------------------
     *
     * LeetCode #763 -- Partition Labels
     *
     * Given a string, partition it into as many parts as possible so that
     * each letter appears in at most one part. Return the sizes of parts.
     *
     * Example: "ababcbacadefegdehijhklij"
     * -> [9, 7, 8] ("ababcbaca", "defegde", "hijhklij")
     *
     * GREEDY IDEA:
     * First pass: record the last index of each character.
     * Second pass: expand the current partition's end to include the last
     * occurrence of every character you see. When your current index equals
     * the partition end, cut.
     *
     * Time: O(n), Space: O(1) (26 letters)
     * -----------------------------------------------------------------------
     */
    public static List<Integer> partitionLabels(String s) {
        // Record last index of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int partitionEnd = 0;
        int partitionStart = 0;

        for (int i = 0; i < s.length(); i++) {
            // Expand partition to include last occurrence of current char
            partitionEnd = Math.max(partitionEnd, lastIndex[s.charAt(i) - 'a']);

            if (i == partitionEnd) {
                // Current index is the end of this partition
                result.add(partitionEnd - partitionStart + 1);
                partitionStart = i + 1;
            }
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Range Tracking -- Valid Parenthesis String
     * -----------------------------------------------------------------------
     *
     * LeetCode #678 -- Valid Parenthesis String
     *
     * Given a string with '(', ')', and '*', where '*' can be '(', ')', or
     * empty, determine if the string can be valid.
     *
     * GREEDY IDEA:
     * Track the range of possible open parenthesis counts.
     * - '(' increases both min and max by 1
     * - ')' decreases both min and max by 1
     * - '*' decreases min by 1 (treat as ')') and increases max by 1 (treat as '(')
     * If max goes below 0, too many ')' -- impossible.
     * Keep min >= 0 (can't have negative open count).
     * At the end, if min == 0, it's valid.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static boolean checkValidString(String s) {
        int minOpen = 0; // Minimum possible open '(' count
        int maxOpen = 0; // Maximum possible open '(' count

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // '*'
                minOpen--; // Treat as ')'
                maxOpen++; // Treat as '('
            }

            if (maxOpen < 0) return false; // Too many ')'
            minOpen = Math.max(minOpen, 0); // Can't go below 0
        }

        return minOpen == 0;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Sort and Group -- Hand of Straights
     * -----------------------------------------------------------------------
     *
     * LeetCode #846 -- Hand of Straights
     *
     * Given an array and a group size W, can you rearrange the array into
     * groups of W consecutive numbers?
     *
     * Example: hand = [1,2,3,6,2,3,4,7,8], W = 3
     * -> true ([1,2,3], [2,3,4], [6,7,8])
     *
     * GREEDY IDEA:
     * Use a TreeMap (sorted map) to count frequencies. Always start a group
     * from the smallest available number. For each group, take W consecutive
     * numbers starting from the smallest.
     *
     * Time: O(n log n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (int card : hand) {
            countMap.put(card, countMap.getOrDefault(card, 0) + 1);
        }

        while (!countMap.isEmpty()) {
            int first = countMap.firstKey(); // Smallest available card

            for (int i = first; i < first + groupSize; i++) {
                if (!countMap.containsKey(i)) return false;

                int count = countMap.get(i);
                if (count == 1) {
                    countMap.remove(i);
                } else {
                    countMap.put(i, count - 1);
                }
            }
        }

        return true;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: Target Matching -- Merge Triplets to Form Target
     * -----------------------------------------------------------------------
     *
     * LeetCode #1899 -- Merge Triplets to Form Target Triplet
     *
     * Given triplets [a, b, c] and a target [x, y, z], you can merge two
     * triplets by taking max of each position. Can you form the target?
     *
     * GREEDY IDEA:
     * A triplet is usable if none of its values exceed the target values.
     * From all usable triplets, check if we can find target[0], target[1],
     * and target[2] each appearing in at least one usable triplet.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean foundFirst = false;
        boolean foundSecond = false;
        boolean foundThird = false;

        for (int[] t : triplets) {
            // Skip triplets that exceed target in any position
            if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) {
                continue;
            }

            if (t[0] == target[0]) foundFirst = true;
            if (t[1] == target[1]) foundSecond = true;
            if (t[2] == target[2]) foundThird = true;
        }

        return foundFirst && foundSecond && foundThird;
    }


    public static void main(String[] args) {

        System.out.println("--- Jump Game ---");
        System.out.println(canJump(new int[]{2,3,1,1,4}));  // true
        System.out.println(canJump(new int[]{3,2,1,0,4}));  // false

        System.out.println("\n--- Jump Game II ---");
        System.out.println(jump(new int[]{2,3,1,1,4}));  // 2

        System.out.println("\n--- Gas Station ---");
        System.out.println(canCompleteCircuit(
            new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));  // 3

        System.out.println("\n--- Partition Labels ---");
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
        // [9, 7, 8]

        System.out.println("\n--- Valid Parenthesis String ---");
        System.out.println(checkValidString("(*))"));  // true

        System.out.println("\n--- Hand of Straights ---");
        System.out.println(isNStraightHand(
            new int[]{1,2,3,6,2,3,4,7,8}, 3));  // true

        System.out.println("\n--- Merge Triplets ---");
        System.out.println(mergeTriplets(
            new int[][]{{2,5,3},{1,8,4},{1,7,5}}, new int[]{2,7,5}));  // true
    }
}
