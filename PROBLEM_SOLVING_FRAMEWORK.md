# Problem-Solving Framework -- The UMPIRE Method

Every single problem you solve, whether on LeetCode or in an interview, should
follow this framework. Practice it until it becomes automatic. The biggest
difference between someone who can solve problems and someone who cannot is not
intelligence -- it is having a structured approach.


---


## U -- Understand the Problem

Before you write a single line of code, make sure you truly understand what is
being asked. Read the problem statement twice. Then do the following:

1. Identify the inputs. What are their types? What are the constraints?
   - Can the array be empty?
   - Can there be negative numbers?
   - Can there be duplicates?
   - What is the maximum size of the input?

2. Identify the output. What exactly should you return?

3. Walk through the given examples by hand. Trace through them step by step.

4. Create your own test cases, especially edge cases:
   - Empty input
   - Single element
   - All elements the same
   - Already sorted
   - Maximum possible input size

In an interview, say something like:
  "Let me make sure I understand the problem correctly. We are given [restate
  the input], and we need to return [restate the output]. Can I assume the input
  is always valid?"


---


## M -- Match to Known Patterns

Once you understand the problem, ask yourself: have I seen something like this
before? Here is a quick lookup guide:

  Problem says "sorted array"               -->  Binary Search or Two Pointers
  Problem says "subarray" or "substring"     -->  Sliding Window or Prefix Sum
  Problem says "top K" or "kth largest"      -->  Heap or QuickSelect
  Problem involves a tree                    -->  DFS or BFS (recursion)
  Problem involves a graph                   -->  BFS, DFS, Union-Find
  Problem says "all combinations"            -->  Backtracking
  Problem says "minimum cost" or "optimal"   -->  Dynamic Programming or Greedy
  Problem says "shortest path"               -->  BFS (unweighted) or Dijkstra
  Problem involves parentheses or nesting    -->  Stack
  Problem involves frequency counting        -->  HashMap
  Problem involves intervals                 -->  Sort + Greedy
  Problem involves connectivity              -->  Union-Find or DFS
  Problem involves prefix/word matching      -->  Trie

Do not memorize this table blindly. As you solve more problems, you will start
to feel which pattern fits. That intuition is what you are building.


---


## P -- Plan Before You Code

This is the step most beginners skip, and it costs them dearly.

Step 1: Think of the brute force solution first. What is the simplest, most
        straightforward way to solve this, even if it is slow?

Step 2: Analyze the brute force. What is the time complexity? Why is it slow?
        What work is being repeated?

Step 3: Optimize. Ask yourself:
        - Can I use a HashMap to avoid repeated lookups?
        - Can I sort the input first to make things easier?
        - Can I use two pointers instead of nested loops?
        - Can I break this into smaller subproblems (DP)?
        - Can I precompute something (prefix sum, frequency count)?

Step 4: Write pseudocode. Not Java code -- plain English steps.

In an interview, say something like:
  "The brute force approach would be to [explain], which gives us O(n squared)
  time. I think we can do better by using a HashMap to [explain optimization].
  That would bring it down to O(n). Should I go ahead and code this?"


---


## I -- Implement

Now translate your plan into clean code. Some guidelines:

- Handle edge cases first (empty input, null, single element)
- Use meaningful variable names (left, right, count -- not i, j, k everywhere)
- Write helper methods if a block of logic is complex
- Keep your code clean and readable -- the interviewer is reading it too

In an interview, talk as you code:
  "I am creating a HashMap to store the frequency of each element..."
  "This loop goes through each element and checks if its complement exists..."
  "Here I am handling the edge case where the array is empty..."


---


## R -- Review and Test

After writing your code, do not say "I think this is right." Prove it.

1. Trace through your code with the given examples. Literally walk through
   each line and say what happens.

2. Check common bugs:
   - Off-by-one errors in loop boundaries
   - Forgetting to handle null or empty input
   - Integer overflow (use long if needed in Java)
   - Modifying a collection while iterating over it
   - Wrong comparison (== vs .equals() for strings in Java)

3. Test with your own edge cases.


---


## E -- Evaluate Complexity

Always state the time and space complexity of your solution.

Time Complexity: How many operations does your algorithm perform as the input
                 grows? Express it in Big-O notation.

Space Complexity: How much extra memory does your algorithm use? Do not count
                  the input itself, only the additional space.

In an interview, say:
  "The time complexity is O(n) because we iterate through the array once.
  The space complexity is O(n) because in the worst case, the HashMap stores
  all n elements."


---


## Time Management in a 45-Minute Interview

  Minutes 0 to 5:    Read the problem, ask clarifying questions, discuss examples
  Minutes 5 to 10:   Think about approach, discuss brute force and optimization
  Minutes 10 to 30:  Write clean, working code
  Minutes 30 to 35:  Trace through examples, test edge cases
  Minutes 35 to 40:  Discuss complexity, possible improvements
  Minutes 40 to 45:  Ask the interviewer questions about the team or role


---


## What to Do When You Are Stuck

It happens to everyone. Here is how to handle it:

1. Go back to the examples. Walk through them more carefully. Sometimes the
   answer reveals itself when you trace through the data.

2. Think about what data structure would help. If you are doing repeated
   lookups, you need a HashMap. If you need ordering, you need a Heap or
   TreeMap. If you need to track a range, you need a sliding window.

3. Think about simpler versions of the problem. What if the input was sorted?
   What if there were only 2 elements? Solve the simple case first, then
   generalize.

4. In an interview, communicate that you are thinking:
   "I am considering a few approaches. Let me think about whether a sliding
   window would work here..."

   Never sit in silence. The interviewer wants to see your thought process.
