import java.util.*;

/*
 * ============================================================================
 * LINKED LISTS -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    // Definition of a singly linked list node
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // Helper: Build a linked list from an array
    public static ListNode buildList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    // Helper: Print a linked list
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb.toString());
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Reverse a Linked List (Iterative)
     * -----------------------------------------------------------------------
     *
     * LeetCode #206 -- Reverse Linked List
     *
     * This is the most fundamental linked list operation. You MUST be able
     * to write this from memory in under 2 minutes.
     *
     * THOUGHT PROCESS:
     * We need to reverse all the arrows. For each node, instead of pointing
     * to the next node, it should point to the previous node.
     *
     * We use three pointers: prev, current, and next.
     *   1. Save current.next (so we do not lose the rest of the list).
     *   2. Point current.next to prev (reverse the arrow).
     *   3. Move prev to current.
     *   4. Move current to the saved next.
     *
     * When current reaches null, prev is the new head.
     *
     *   Before: 1 -> 2 -> 3 -> 4 -> null
     *   After:  null <- 1 <- 2 <- 3 <- 4
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;  // Save next
            current.next = prev;           // Reverse arrow
            prev = current;                // Move prev forward
            current = next;                // Move current forward
        }

        return prev; // prev is the new head
    }

    // Recursive version (also important to know)
    public static ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Reverse the rest of the list
        ListNode newHead = reverseListRecursive(head.next);

        // head.next is now the last node of the reversed rest.
        // Make it point back to head.
        head.next.next = head;
        head.next = null;

        return newHead;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Merge Two Sorted Lists
     * -----------------------------------------------------------------------
     *
     * LeetCode #21 -- Merge Two Sorted Lists
     *
     * Merge two sorted linked lists into one sorted list.
     *
     * THOUGHT PROCESS:
     * Create a dummy node. Compare the heads of both lists. Attach the
     * smaller one to the result and advance that list's pointer. When one
     * list is exhausted, attach the rest of the other.
     *
     * Time: O(n + m), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach the remaining nodes
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Fast and Slow Pointers -- Cycle Detection
     * -----------------------------------------------------------------------
     *
     * LeetCode #141 -- Linked List Cycle
     *
     * Given a linked list, determine if it has a cycle.
     *
     * THOUGHT PROCESS:
     * If there is no cycle, the fast pointer will reach null.
     * If there is a cycle, the fast pointer will eventually catch up to
     * the slow pointer (they will meet inside the cycle).
     *
     * Think of two runners on a circular track. The faster runner will
     * always lap the slower runner.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; // They met -- cycle exists
            }
        }

        return false; // Fast reached the end -- no cycle
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Fast and Slow Pointers -- Find Middle
     * -----------------------------------------------------------------------
     *
     * LeetCode #876 -- Middle of the Linked List
     *
     * Find the middle node. If there are two middle nodes, return the second.
     *
     * THOUGHT PROCESS:
     * When fast reaches the end, slow is at the middle. This is because
     * fast moves at 2x speed, so when fast has traveled the full length,
     * slow has traveled half.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Two Pointers with Gap
     * -----------------------------------------------------------------------
     *
     * LeetCode #19 -- Remove Nth Node From End of List
     *
     * Remove the nth node from the end. Do it in one pass.
     *
     * Example: [1,2,3,4,5], n=2 -> [1,2,3,5] (remove 4)
     *
     * THOUGHT PROCESS:
     * Use two pointers with a gap of n between them. Advance the first
     * pointer n steps ahead. Then move both pointers together. When the
     * first pointer reaches the end, the second pointer is right before
     * the node to remove.
     *
     * Use a dummy node to handle the case where we remove the head.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = dummy;

        // Move first n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Move both until first reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // second.next is the node to remove
        second.next = second.next.next;

        return dummy.next;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Reorder List (Combination of Techniques)
     * -----------------------------------------------------------------------
     *
     * LeetCode #143 -- Reorder List
     *
     * Reorder: L0 -> L1 -> ... -> Ln  becomes  L0 -> Ln -> L1 -> Ln-1 -> ...
     *
     * Example: [1,2,3,4] -> [1,4,2,3]
     *
     * THOUGHT PROCESS:
     * This combines three techniques:
     *   1. Find the middle of the list (fast/slow pointers).
     *   2. Reverse the second half.
     *   3. Merge the two halves by alternating nodes.
     *
     * Time: O(n), Space: O(1)
     * -----------------------------------------------------------------------
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find the middle
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null; // Cut the list in half

        // Step 3: Merge alternately
        ListNode firstHalf = head;
        while (secondHalf != null) {
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = temp1;

            firstHalf = temp1;
            secondHalf = temp2;
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: LRU Cache (HashMap + Doubly Linked List)
     * -----------------------------------------------------------------------
     *
     * LeetCode #146 -- LRU Cache
     *
     * Design a data structure that follows the Least Recently Used (LRU)
     * policy. It should support:
     *   get(key)       -- Return value if key exists, otherwise -1.
     *   put(key, value) -- Insert or update. If capacity is exceeded,
     *                      evict the least recently used item.
     *
     * Both operations must be O(1).
     *
     * THOUGHT PROCESS:
     * We need O(1) access by key --> HashMap.
     * We need to track the order of usage and quickly remove/add --> Doubly Linked List.
     *
     * The HashMap maps key -> node in the doubly linked list.
     * The doubly linked list maintains the usage order:
     *   - Most recently used at the head.
     *   - Least recently used at the tail.
     * When an item is accessed or added, move it to the head.
     * When we need to evict, remove from the tail.
     *
     * Time: O(1) for both get and put
     * Space: O(capacity)
     * -----------------------------------------------------------------------
     */
    static class LRUCache {
        private int capacity;
        private HashMap<Integer, DNode> map;
        private DNode head, tail; // Dummy head and tail

        private static class DNode {
            int key, value;
            DNode prev, next;
            DNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.head = new DNode(0, 0); // Dummy head
            this.tail = new DNode(0, 0); // Dummy tail
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            DNode node = map.get(key);
            moveToFront(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                DNode node = map.get(key);
                node.value = value;
                moveToFront(node);
            } else {
                if (map.size() == capacity) {
                    // Evict from the tail
                    DNode lru = tail.prev;
                    removeNode(lru);
                    map.remove(lru.key);
                }
                DNode newNode = new DNode(key, value);
                addToFront(newNode);
                map.put(key, newNode);
            }
        }

        private void removeNode(DNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToFront(DNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private void moveToFront(DNode node) {
            removeNode(node);
            addToFront(node);
        }
    }


    public static void main(String[] args) {

        System.out.println("--- Reverse Linked List ---");
        ListNode list1 = buildList(new int[]{1, 2, 3, 4, 5});
        printList(reverseList(list1));  // 5 -> 4 -> 3 -> 2 -> 1

        System.out.println("\n--- Merge Two Sorted Lists ---");
        ListNode l1 = buildList(new int[]{1, 2, 4});
        ListNode l2 = buildList(new int[]{1, 3, 4});
        printList(mergeTwoLists(l1, l2));  // 1 -> 1 -> 2 -> 3 -> 4 -> 4

        System.out.println("\n--- Middle of Linked List ---");
        ListNode list3 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Middle: " + middleNode(list3).val);  // 3

        System.out.println("\n--- Remove Nth From End ---");
        ListNode list4 = buildList(new int[]{1, 2, 3, 4, 5});
        printList(removeNthFromEnd(list4, 2));  // 1 -> 2 -> 3 -> 5

        System.out.println("\n--- Reorder List ---");
        ListNode list5 = buildList(new int[]{1, 2, 3, 4});
        reorderList(list5);
        printList(list5);  // 1 -> 4 -> 2 -> 3

        System.out.println("\n--- LRU Cache ---");
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1): " + cache.get(1));   // 1
        cache.put(3, 3);  // Evicts key 2
        System.out.println("get(2): " + cache.get(2));   // -1
        cache.put(4, 4);  // Evicts key 1
        System.out.println("get(1): " + cache.get(1));   // -1
        System.out.println("get(3): " + cache.get(3));   // 3
        System.out.println("get(4): " + cache.get(4));   // 4
    }
}
