# Stacks and Queues -- Concepts

## What is a Stack?

A stack is a data structure that follows LIFO -- Last In, First Out. Think of
a stack of plates. You can only add a plate on top, and you can only remove
the plate from the top. The last plate you put on is the first one you take off.

Operations:
  push(x)  -- Add element x on top. O(1).
  pop()    -- Remove and return the top element. O(1).
  peek()   -- Look at the top element without removing it. O(1).
  isEmpty() -- Check if the stack is empty. O(1).

In Java, use ArrayDeque as your stack (not the Stack class, which is legacy
and synchronized):

  ArrayDeque<Integer> stack = new ArrayDeque<>();
  stack.push(1);
  stack.peek();
  stack.pop();


## What is a Queue?

A queue is a data structure that follows FIFO -- First In, First Out. Think of
a line at a store. The first person in line is the first person served.

Operations:
  offer(x) -- Add element to the back. O(1).
  poll()   -- Remove and return the front element. O(1).
  peek()   -- Look at the front element without removing it. O(1).
  isEmpty() -- Check if the queue is empty. O(1).

In Java, use LinkedList or ArrayDeque:

  Queue<Integer> queue = new LinkedList<>();
  queue.offer(1);
  queue.peek();
  queue.poll();


## What is a Monotonic Stack?

A monotonic stack is a stack that maintains its elements in either increasing
or decreasing order. When you push a new element, you first pop all elements
that violate the monotonic property.

This is not a special data structure -- it is a technique using a regular stack.

Why is it useful? It efficiently answers questions like:
  - For each element, what is the next greater element?
  - For each element, what is the previous smaller element?
  - What is the largest rectangle in a histogram?

These questions come up surprisingly often in interviews.


---


## Problem Set

### Easy

  1. Valid Parentheses                LeetCode #20
     Pattern: Stack for matching brackets

  2. Implement Queue using Stacks     LeetCode #232
     Pattern: Two stacks

### Medium

  1. Min Stack                        LeetCode #155
     Pattern: Auxiliary stack to track minimum

  2. Evaluate Reverse Polish Notation LeetCode #150
     Pattern: Stack for expression evaluation

  3. Daily Temperatures               LeetCode #739
     Pattern: Monotonic decreasing stack
     Very frequently asked.

  4. Next Greater Element I           LeetCode #496
     Pattern: Monotonic stack + HashMap

  5. Asteroid Collision               LeetCode #735
     Pattern: Stack simulation

  6. Car Fleet                        LeetCode #853
     Pattern: Stack (sort + monotonic)

  7. Generate Parentheses             LeetCode #22
     Pattern: Backtracking (not really a stack problem, but related)

### Hard

  1. Largest Rectangle in Histogram   LeetCode #84
     Pattern: Monotonic stack
     Classic and very frequently asked.

  2. Sliding Window Maximum           LeetCode #239
     Pattern: Monotonic deque
