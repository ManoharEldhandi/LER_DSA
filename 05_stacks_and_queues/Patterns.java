import java.util.*;

/*
 * ============================================================================
 * STACKS AND QUEUES -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Stack for Matching
     * -----------------------------------------------------------------------
     *
     * LeetCode #20 -- Valid Parentheses
     *
     * Given a string containing just '(', ')', '{', '}', '[', ']', determine
     * if the input string is valid. Brackets must close in the correct order.
     *
     * Example: "()[]{}" -> true
     * Example: "(]"     -> false
     * Example: "([)]"   -> false
     *
     * THOUGHT PROCESS:
     * When we see an opening bracket, we push it. When we see a closing
     * bracket, we check if it matches the most recent opening bracket (top
     * of stack). If it does not match, or the stack is empty, it is invalid.
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        return stack.isEmpty();
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Stack with Auxiliary Tracking -- Min Stack
     * -----------------------------------------------------------------------
     *
     * LeetCode #155 -- Min Stack
     *
     * Design a stack that supports push, pop, top, and retrieving the
     * minimum element, all in O(1) time.
     *
     * THOUGHT PROCESS:
     * The tricky part is getMin() in O(1). We cannot just keep a single
     * variable because when we pop the minimum, we need to know the next
     * minimum.
     *
     * Solution: Use a second stack that tracks the minimum at each level.
     * When we push a value, we also push the current minimum onto the
     * min stack. When we pop, we pop from both stacks.
     *
     * Time: O(1) for all operations, Space: O(n)
     * -----------------------------------------------------------------------
     */
    static class MinStack {
        private ArrayDeque<Integer> stack;
        private ArrayDeque<Integer> minStack;

        public MinStack() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);
            // Push the new minimum onto minStack
            int currentMin = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
            minStack.push(currentMin);
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Stack for Expression Evaluation
     * -----------------------------------------------------------------------
     *
     * LeetCode #150 -- Evaluate Reverse Polish Notation
     *
     * Evaluate an expression in Reverse Polish Notation (postfix notation).
     * Valid operators are +, -, *, /.
     *
     * Example: ["2","1","+","3","*"] -> 9
     *          ((2 + 1) * 3) = 9
     *
     * THOUGHT PROCESS:
     * When we see a number, push it. When we see an operator, pop two
     * numbers, apply the operator, and push the result back.
     *
     * Important: The first popped number is the right operand, the second
     * is the left operand (order matters for subtraction and division).
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/")) {

                int b = stack.pop(); // Right operand
                int a = stack.pop(); // Left operand

                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.peek();
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Monotonic Stack -- Next Greater Element
     * -----------------------------------------------------------------------
     *
     * LeetCode #739 -- Daily Temperatures
     *
     * Given daily temperatures, return an array where each element is the
     * number of days you have to wait for a warmer temperature. If there
     * is no future warmer day, put 0.
     *
     * Example: [73,74,75,71,69,72,76,73] -> [1,1,4,2,1,1,0,0]
     *
     * THOUGHT PROCESS:
     * For each day, we need the next day with a higher temperature. This is
     * the "next greater element" pattern.
     *
     * Use a stack that stores indices (not values). We maintain a decreasing
     * stack -- each element in the stack is colder than or equal to the one
     * below it.
     *
     * When we find a temperature warmer than the top of the stack, we pop
     * the stack and calculate the distance. We keep popping until the stack
     * is empty or the top is warmer.
     *
     * Why does this work? The stack always holds indices of days that have
     * not yet found their "next warmer day." When we find a warmer day,
     * we resolve all the colder days waiting in the stack.
     *
     * Time: O(n) -- each index is pushed and popped at most once
     * Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // Store indices

        for (int i = 0; i < n; i++) {
            // Pop all days that are colder than today
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                result[prevDay] = i - prevDay;
            }

            stack.push(i);
        }

        // Days remaining in the stack have no warmer future day (result stays 0)
        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Monotonic Stack -- Largest Rectangle in Histogram
     * -----------------------------------------------------------------------
     *
     * LeetCode #84 -- Largest Rectangle in Histogram
     *
     * Given an array of bar heights, find the area of the largest rectangle
     * that can be formed in the histogram.
     *
     * Example: [2,1,5,6,2,3] -> 10
     *          The rectangle with height 5 spanning indices 2 and 3 (width 2)
     *          has area 5 * 2 = 10.
     *
     * THOUGHT PROCESS:
     * For each bar, we want to know: how far left and how far right can this
     * bar extend? It can extend in both directions as long as the neighboring
     * bars are at least as tall.
     *
     * We use a stack that stores indices in increasing order of height. When
     * we encounter a bar shorter than the top of the stack, the top bar
     * cannot extend further to the right. We pop it and calculate its area.
     *
     * The width for the popped bar is determined by the current index and
     * the new stack top.
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int largestRectangleArea(int[] heights) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // Use 0 as a sentinel value at the end to flush the stack
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Monotonic Deque -- Sliding Window Maximum
     * -----------------------------------------------------------------------
     *
     * LeetCode #239 -- Sliding Window Maximum
     *
     * Given an array and window size k, return the maximum value in each
     * window as the window slides from left to right.
     *
     * Example: nums = [1,3,-1,-3,5,3,6,7], k = 3 -> [3,3,5,5,6,7]
     *
     * THOUGHT PROCESS:
     * We need the maximum in each window. A brute force approach scans
     * the entire window each time: O(n * k).
     *
     * Better: Use a deque (double-ended queue) that stores indices.
     * We maintain the deque so that:
     *   - Elements are in decreasing order of their values.
     *   - The front of the deque is always the maximum of the current window.
     *
     * When adding a new element:
     *   1. Remove indices from the back that have smaller values (they can
     *      never be the maximum while the new element is in the window).
     *   2. Add the new index to the back.
     *   3. Remove the front if it is outside the window.
     *   4. The front is the maximum for the current window.
     *
     * Time: O(n), Space: O(k)
     * -----------------------------------------------------------------------
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];

        int[] result = new int[nums.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>(); // Stores indices

        for (int i = 0; i < nums.length; i++) {
            // Remove indices outside the window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove smaller elements from the back (they are useless)
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Start recording results once we have a full window
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }


    public static void main(String[] args) {

        System.out.println("--- Valid Parentheses ---");
        System.out.println(isValid("()[]{}"));   // true
        System.out.println(isValid("(]"));        // false

        System.out.println("\n--- Min Stack ---");
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        System.out.println("Min: " + ms.getMin());  // -3
        ms.pop();
        System.out.println("Top: " + ms.top());      // 0
        System.out.println("Min: " + ms.getMin());  // -2

        System.out.println("\n--- Evaluate RPN ---");
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));  // 9

        System.out.println("\n--- Daily Temperatures ---");
        System.out.println(Arrays.toString(
            dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));  // [1,1,4,2,1,1,0,0]

        System.out.println("\n--- Largest Rectangle in Histogram ---");
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));  // 10

        System.out.println("\n--- Sliding Window Maximum ---");
        System.out.println(Arrays.toString(
            maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));  // [3,3,5,5,6,7]
    }
}
