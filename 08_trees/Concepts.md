# Trees -- Concepts

## What is a Tree?

A tree is a hierarchical data structure made of nodes. Each node has a value
and pointers to its children. The topmost node is called the root. Nodes with
no children are called leaves.

A Binary Tree is a tree where each node has at most two children, called left
and right.

In Java:
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }


## Binary Search Tree (BST)

A BST is a binary tree with an ordering property:
  - All nodes in the left subtree have values LESS than the current node.
  - All nodes in the right subtree have values GREATER than the current node.

This property allows O(log n) search, insert, and delete (on average, when
the tree is balanced). In the worst case (a skewed tree), it degrades to O(n).


## Tree Traversals

There are four main ways to visit every node in a tree:

### Depth-First Search (DFS) -- Three Variants

  In-order (Left, Root, Right):
    Visit left subtree, then root, then right subtree.
    For a BST, this gives nodes in sorted order.

  Pre-order (Root, Left, Right):
    Visit root first, then left, then right.
    Useful for creating a copy of the tree.

  Post-order (Left, Right, Root):
    Visit left and right subtrees first, then root.
    Useful for deleting a tree or evaluating expressions.

### Breadth-First Search (BFS) -- Level Order

  Visit all nodes at depth 0, then depth 1, then depth 2, etc.
  Uses a queue. This is Level Order Traversal.


## How to Think About Tree Problems

Most tree problems fall into one of these categories:

1. Compute something about the tree (height, diameter, whether balanced).
   -> Usually DFS with return values.

2. Search for a value or validate a property.
   -> DFS with constraints passed down (like valid range for BST).

3. Process level by level.
   -> BFS with a queue.

4. Build a tree from given data.
   -> Recursion, using indices to split the data.


---


## Problem Set

### Easy

  1. Invert Binary Tree               LeetCode #226
  2. Maximum Depth of Binary Tree     LeetCode #104
  3. Same Tree                        LeetCode #100
  4. Subtree of Another Tree          LeetCode #572
  5. Diameter of Binary Tree          LeetCode #543
  6. Balanced Binary Tree             LeetCode #110

### Medium

  1. Level Order Traversal            LeetCode #102
  2. Binary Tree Right Side View      LeetCode #199
  3. Lowest Common Ancestor (BST)     LeetCode #235
  4. Lowest Common Ancestor (BT)      LeetCode #236
  5. Validate Binary Search Tree      LeetCode #98
  6. Kth Smallest Element in BST      LeetCode #230
  7. Construct from Pre + In order    LeetCode #105
  8. Count Good Nodes                 LeetCode #1448

### Hard

  1. Binary Tree Maximum Path Sum     LeetCode #124
  2. Serialize and Deserialize BT     LeetCode #297
