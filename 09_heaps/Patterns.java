import java.util.*;

/*
 * ============================================================================
 * HEAPS AND PRIORITY QUEUES -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Heap of Size K -- Kth Largest Element
     * -----------------------------------------------------------------------
     *
     * LeetCode #215 -- Kth Largest Element in an Array
     *
     * Example: [3,2,1,5,6,4], k = 2 -> 5
     *
     * THOUGHT PROCESS:
     * Approach 1 (Sort): Sort the array and return nums[n-k]. O(n log n).
     *
     * Approach 2 (Min-heap of size k): Keep a min-heap of size k. The top
     * of this heap is the kth largest. For each element:
     *   - If the heap has fewer than k elements, add it.
     *   - If the element is larger than the heap top, remove the top and add it.
     * After processing all elements, the top is the kth largest.
     *
     * Why min-heap? Because we want to discard the smallest elements. The
     * min-heap lets us efficiently find and remove the smallest of our k
     * candidates.
     *
     * Time: O(n log k), Space: O(k)
     * -----------------------------------------------------------------------
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the smallest
            }
        }

        return minHeap.peek();
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: HashMap + Heap -- Top K Frequent Elements
     * -----------------------------------------------------------------------
     *
     * LeetCode #347 -- Top K Frequent Elements
     *
     * Example: nums = [1,1,1,2,2,3], k = 2 -> [1, 2]
     *
     * THOUGHT PROCESS:
     * Step 1: Count frequencies with a HashMap.
     * Step 2: Use a min-heap of size k, ordered by frequency. After processing
     * all elements, the heap contains the k most frequent elements.
     *
     * Alternative: Bucket sort. Create an array of lists where index = frequency.
     * Then iterate from the end. This gives O(n) time.
     *
     * Time: O(n log k), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Min-heap ordered by frequency
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> freq.get(a) - freq.get(b)
        );

        for (int num : freq.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Two Heaps -- Find Median from Data Stream
     * -----------------------------------------------------------------------
     *
     * LeetCode #295 -- Find Median from Data Stream
     *
     * Design a data structure that supports:
     *   addNum(int num) -- Add a number.
     *   findMedian()    -- Return the median of all numbers added so far.
     *
     * THOUGHT PROCESS:
     * Split the numbers into two halves:
     *   - Lower half: stored in a max-heap (we want the largest of the lower half)
     *   - Upper half: stored in a min-heap (we want the smallest of the upper half)
     *
     * The median is either the top of the max-heap (odd count) or the average
     * of both tops (even count).
     *
     * To maintain balance:
     *   - Always add to maxHeap first, then move the top to minHeap.
     *   - If minHeap becomes larger, move its top back to maxHeap.
     *
     * This ensures maxHeap always has equal or one more element than minHeap.
     *
     * Time: O(log n) for addNum, O(1) for findMedian
     * Space: O(n)
     * -----------------------------------------------------------------------
     */
    static class MedianFinder {
        private PriorityQueue<Integer> maxHeap; // Lower half (max on top)
        private PriorityQueue<Integer> minHeap; // Upper half (min on top)

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll()); // Move max of lower half to upper half

            // Balance: maxHeap should have >= elements than minHeap
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Merge K Sorted Lists using Heap
     * -----------------------------------------------------------------------
     *
     * LeetCode #23 -- Merge K Sorted Lists
     *
     * Given k sorted linked lists, merge them into one sorted list.
     *
     * THOUGHT PROCESS:
     * Put the head of each list into a min-heap. The heap gives us the
     * smallest element across all lists. Remove it, add it to the result,
     * and if that node has a next, add next to the heap.
     *
     * Time: O(N log k) where N = total nodes, k = number of lists
     * Space: O(k) for the heap
     * -----------------------------------------------------------------------
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Add the head of each non-null list
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Task Scheduler
     * -----------------------------------------------------------------------
     *
     * LeetCode #621 -- Task Scheduler
     *
     * Given tasks and a cooldown period n, find the minimum time to finish
     * all tasks. The same task must have at least n intervals between repeats.
     *
     * Example: tasks = ["A","A","A","B","B","B"], n = 2 -> 8
     *          A B _ A B _ A B
     *
     * THOUGHT PROCESS:
     * Always execute the most frequent task first (greedy). Use a max-heap
     * to get the task with the highest remaining count.
     *
     * After executing a task, it enters a cooldown. We track cooldowns with
     * a queue of (count, availableTime) pairs. When a task's cooldown expires,
     * we add it back to the heap.
     *
     * Time: O(n * m) where m = number of distinct tasks, Space: O(m)
     * -----------------------------------------------------------------------
     */
    public static int leastInterval(char[] tasks, int n) {
        // Count frequencies
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Max-heap of frequencies
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) {
            if (f > 0) maxHeap.offer(f);
        }

        // Queue of (remaining count, time when available)
        Queue<int[]> cooldown = new LinkedList<>();
        int time = 0;

        while (!maxHeap.isEmpty() || !cooldown.isEmpty()) {
            time++;

            if (!maxHeap.isEmpty()) {
                int count = maxHeap.poll() - 1; // Execute one instance
                if (count > 0) {
                    cooldown.offer(new int[]{count, time + n});
                }
            }

            // Check if any task is ready to come back
            if (!cooldown.isEmpty() && cooldown.peek()[1] == time) {
                maxHeap.offer(cooldown.poll()[0]);
            }
        }

        return time;
    }


    public static void main(String[] args) {

        System.out.println("--- Kth Largest Element ---");
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));  // 5

        System.out.println("\n--- Top K Frequent ---");
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3}, 2)));

        System.out.println("\n--- Median Finder ---");
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Median: " + mf.findMedian());  // 1.5
        mf.addNum(3);
        System.out.println("Median: " + mf.findMedian());  // 2.0

        System.out.println("\n--- Task Scheduler ---");
        System.out.println(leastInterval(new char[]{'A','A','A','B','B','B'}, 2));  // 8
    }
}
