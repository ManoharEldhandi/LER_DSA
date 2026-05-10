import java.util.*;

/*
 * ============================================================================
 * INTERVALS -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Merge Overlapping -- Merge Intervals
     * -----------------------------------------------------------------------
     *
     * LeetCode #56 -- Merge Intervals
     *
     * Given a collection of intervals, merge all overlapping intervals.
     *
     * Example: [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
     *
     * APPROACH:
     * 1. Sort by start time
     * 2. Walk through. If current interval overlaps with the last one in
     *    our result, extend the end of the last one. Otherwise add current
     *    as a new interval.
     *
     * Two intervals overlap when sorted: current.start <= previous.end
     *
     * Time: O(n log n) for sorting, Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] last = result.get(result.size() - 1);
            int[] current = intervals[i];

            if (current[0] <= last[1]) {
                // Overlapping -- extend the end
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap -- add as new interval
                result.add(current);
            }
        }

        return result.toArray(new int[result.size()][]);
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Three-Phase Insert -- Insert Interval
     * -----------------------------------------------------------------------
     *
     * LeetCode #57 -- Insert Interval
     *
     * Given sorted non-overlapping intervals and a new interval, insert
     * the new interval and merge if necessary. Return sorted result.
     *
     * Example: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * -> [[1,5],[6,9]]
     *
     * APPROACH:
     * Three phases:
     * Phase 1: Add all intervals that end before the new one starts
     * Phase 2: Merge all intervals that overlap with the new one
     * Phase 3: Add all intervals that start after the new one ends
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Phase 1: Add all intervals that come before (end < newStart)
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Phase 3: Add all intervals that come after
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Greedy Removal -- Non-Overlapping Intervals
     * -----------------------------------------------------------------------
     *
     * LeetCode #435 -- Non-Overlapping Intervals
     *
     * Given intervals, find the minimum number of intervals to REMOVE so
     * that the rest don't overlap.
     *
     * APPROACH:
     * Sort by end time. Greedily keep intervals that end earliest (they
     * leave the most room for future intervals). Count removals.
     *
     * This is the classic activity selection problem.
     *
     * Time: O(n log n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // Sort by end time

        int removals = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prevEnd) {
                // Overlap -- remove the current one (it ends later)
                removals++;
            } else {
                // No overlap -- update the boundary
                prevEnd = intervals[i][1];
            }
        }

        return removals;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Overlap Detection -- Meeting Rooms
     * -----------------------------------------------------------------------
     *
     * LeetCode #252 -- Meeting Rooms
     *
     * Given meeting time intervals, determine if a person can attend all
     * meetings (no overlaps).
     *
     * APPROACH:
     * Sort by start time. Check if any meeting starts before the previous
     * one ends.
     *
     * Time: O(n log n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false; // This meeting starts before previous ends
            }
        }

        return true;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Min-Heap for Concurrent Count -- Meeting Rooms II
     * -----------------------------------------------------------------------
     *
     * LeetCode #253 -- Meeting Rooms II
     *
     * Given meeting times, find the minimum number of conference rooms needed.
     *
     * Example: [[0,30],[5,10],[15,20]] -> 2
     * (Meeting [0,30] overlaps with [5,10] at time 5, so we need 2 rooms)
     *
     * APPROACH:
     * Sort by start time. Use a min-heap that stores end times of ongoing
     * meetings. For each meeting:
     * - If earliest ending meeting is done (end <= current start), reuse
     *   that room (poll from heap)
     * - Add current meeting's end time to heap
     * The heap size at any point = rooms in use.
     *
     * Think of it this way: the heap tracks "when does each room become free?"
     * If the soonest-to-free room is still busy, you need a new room.
     *
     * Time: O(n log n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : intervals) {
            // If earliest ending room is free, reuse it
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll();
            }
            // Assign a room (add end time)
            minHeap.add(meeting[1]);
        }

        return minHeap.size();
    }


    // Helper to print 2D array
    private static void print2D(int[][] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("[").append(arr[i][0]).append(",").append(arr[i][1]).append("]");
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {

        System.out.println("--- Merge Intervals ---");
        print2D(merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));
        // [[1,6], [8,10], [15,18]]

        System.out.println("\n--- Insert Interval ---");
        print2D(insert(new int[][]{{1,3},{6,9}}, new int[]{2,5}));
        // [[1,5], [6,9]]

        System.out.println("\n--- Non-Overlapping Intervals ---");
        System.out.println(eraseOverlapIntervals(
            new int[][]{{1,2},{2,3},{3,4},{1,3}}));  // 1

        System.out.println("\n--- Meeting Rooms ---");
        System.out.println(canAttendMeetings(
            new int[][]{{0,30},{5,10},{15,20}}));  // false

        System.out.println("\n--- Meeting Rooms II ---");
        System.out.println(minMeetingRooms(
            new int[][]{{0,30},{5,10},{15,20}}));  // 2
    }
}
