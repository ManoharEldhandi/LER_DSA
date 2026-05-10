# Recursion and Backtracking -- Concepts

## What is Recursion?

Recursion is when a function calls itself to solve a smaller version of the
same problem. Every recursive function needs two things:

1. A BASE CASE -- when to stop. Without this, you get infinite recursion
   (a stack overflow error).

2. A RECURSIVE CASE -- how to break the problem into a smaller subproblem
   and call yourself with that smaller problem.

The mental model: Imagine you are standing on step 10 of a staircase and
someone asks "how many steps did you climb?" You turn to the person on step 9
and ask "how many did YOU climb?" They turn to step 8 and ask the same. This
continues until the person on step 0 says "zero." Then each person adds 1
and passes the answer back up.


## What is Backtracking?

Backtracking is a specific pattern of recursion used to explore ALL possible
solutions by building them step by step. At each step, you make a choice.
If a choice leads to a dead end, you undo it (backtrack) and try the next
option.

Think of it like exploring a maze. You walk forward, making choices at each
fork. If you hit a dead end, you go back to the last fork and try a different
path. You keep doing this until you find the exit (or explore all paths).

The three key steps in backtracking:
  1. CHOOSE   -- Pick an option from the available choices.
  2. EXPLORE  -- Recurse with that choice.
  3. UNCHOOSE -- Undo the choice (backtrack) and try the next option.


## Backtracking Template

    void backtrack(input, path, result) {
        if (path is a complete solution) {
            result.add(copy of path);
            return;
        }

        for (each choice in available choices) {
            if (choice is valid) {
                path.add(choice);                    // Choose
                backtrack(remaining input, path, result);  // Explore
                path.remove(last element);           // Unchoose
            }
        }
    }


## Common Backtracking Problems and How They Differ

  Subsets:       At each element, choose to include it or not. No duplicates.
  Subsets II:    Same as subsets but input has duplicates. Skip duplicates.
  Permutations:  Arrange ALL elements in every possible order.
  Combinations:  Choose k elements from n. Order does not matter.
  Combination Sum: Choose elements that sum to target. Can reuse elements.


---


## Problem Set

### Medium

  1. Subsets                          LeetCode #78
     Pattern: Include/exclude at each step. Foundation for all backtracking.

  2. Subsets II                       LeetCode #90
     Pattern: Subsets with duplicate handling

  3. Permutations                     LeetCode #46
     Pattern: Build all orderings

  4. Combination Sum                  LeetCode #39
     Pattern: Reuse elements, target sum

  5. Combination Sum II               LeetCode #40
     Pattern: No reuse, skip duplicates

  6. Letter Combinations of Phone     LeetCode #17
     Pattern: Map digits to letters, explore all

  7. Palindrome Partitioning          LeetCode #131
     Pattern: Partition + palindrome check

  8. Word Search                      LeetCode #79
     Pattern: DFS + backtracking on a grid

  9. Generate Parentheses             LeetCode #22
     Pattern: Build valid parentheses strings

### Hard

  1. N-Queens                         LeetCode #51
     Pattern: Place queens row by row, validate columns and diagonals

  2. Sudoku Solver                    LeetCode #37
     Pattern: Try each digit, validate, backtrack
