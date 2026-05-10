import java.util.*;

public class Patterns {

    // Pattern 1: LRU Cache = HashMap + custom doubly linked list.
    static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node> map;
        private final Node head;
        private final Node tail;

        private static class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            Node node = map.get(key);
            remove(node);
            addAfterHead(node);
            return node.value;
        }

        void put(int key, int value) {
            if (map.containsKey(key)) {
                Node existing = map.get(key);
                existing.value = value;
                remove(existing);
                addAfterHead(existing);
                return;
            }

            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }

            Node node = new Node(key, value);
            map.put(key, node);
            addAfterHead(node);
        }

        private void addAfterHead(Node node) {
            Node oldFirst = head.next;
            head.next = node;
            node.prev = head;
            node.next = oldFirst;
            oldFirst.prev = node;
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    // Pattern 2: RandomizedSet = ArrayList + value-to-index map.
    static class RandomizedSet {
        private final List<Integer> values = new ArrayList<>();
        private final Map<Integer, Integer> index = new HashMap<>();
        private final Random random = new Random();

        boolean insert(int val) {
            if (index.containsKey(val)) {
                return false;
            }

            index.put(val, values.size());
            values.add(val);
            return true;
        }

        boolean remove(int val) {
            if (!index.containsKey(val)) {
                return false;
            }

            int removeIndex = index.get(val);
            int lastValue = values.get(values.size() - 1);

            values.set(removeIndex, lastValue);
            index.put(lastValue, removeIndex);

            values.remove(values.size() - 1);
            index.remove(val);
            return true;
        }

        int getRandom() {
            return values.get(random.nextInt(values.size()));
        }
    }

    // Pattern 3: TimeMap = HashMap + sorted timestamp list + binary search.
    static class TimeMap {
        private static class Entry {
            int timestamp;
            String value;

            Entry(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }
        }

        private final Map<String, List<Entry>> map = new HashMap<>();

        void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Entry(timestamp, value));
        }

        String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }

            List<Entry> entries = map.get(key);
            int left = 0;
            int right = entries.size() - 1;
            String answer = "";

            while (left <= right) {
                int mid = left + (right - left) / 2;
                Entry entry = entries.get(mid);

                if (entry.timestamp <= timestamp) {
                    answer = entry.value;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return answer;
        }
    }

    // Pattern 4: MinStack = normal stack + stack of minimums.
    static class MinStack {
        private final Deque<Integer> stack = new ArrayDeque<>();
        private final Deque<Integer> minStack = new ArrayDeque<>();

        void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        void pop() {
            int removed = stack.pop();
            if (removed == minStack.peek()) {
                minStack.pop();
            }
        }

        int top() {
            return stack.peek();
        }

        int getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println(cache.get(1));
        cache.put(3, 30);
        System.out.println(cache.get(2));

        RandomizedSet set = new RandomizedSet();
        System.out.println(set.insert(5));
        System.out.println(set.remove(5));

        TimeMap timeMap = new TimeMap();
        timeMap.set("course", "dsa", 1);
        System.out.println(timeMap.get("course", 2));
    }
}
