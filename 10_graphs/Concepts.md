# Graphs -- Concepts

## What is a Graph?

A graph is a collection of nodes (vertices) connected by edges. Unlike trees,
graphs can have cycles, multiple paths between nodes, and nodes with no
connections at all.

Graphs are everywhere: social networks (people are nodes, friendships are edges),
maps (cities are nodes, roads are edges), web pages (pages are nodes, links are
edges).

Types of graphs:
  - Directed: edges have a direction (A -> B does not mean B -> A)
  - Undirected: edges go both ways (A -- B means A -> B and B -> A)
  - Weighted: edges have a cost/distance
  - Unweighted: all edges have equal cost


## How to Represent a Graph in Code

### Adjacency List (Most Common in Interviews)

Store a list of neighbors for each node. Use a HashMap or array of lists.

    // Using HashMap
    HashMap<Integer, List<Integer>> graph = new HashMap<>();

    // Using array of lists (when nodes are 0 to n-1)
    List<List<Integer>> graph = new ArrayList<>();

### Adjacency Matrix

A 2D boolean or int array. matrix[i][j] = true means there is an edge from
i to j. Uses O(n^2) space, so only practical for small, dense graphs.

### Edge List

A list of [from, to] pairs. Simple but inefficient for neighbor lookups.
Convert to adjacency list before processing.


## Graph Traversal

### BFS (Breadth-First Search)

Explores nodes level by level using a queue. Finds the shortest path in
unweighted graphs.

    Use BFS when:
    - Finding shortest path in unweighted graph
    - Level-by-level processing
    - Finding connected components

### DFS (Depth-First Search)

Explores as deep as possible before backtracking. Uses a stack (or recursion).

    Use DFS when:
    - Detecting cycles
    - Topological sorting
    - Finding connected components
    - Path finding (all paths)

### Topological Sort

Ordering of nodes in a directed acyclic graph (DAG) such that for every edge
A -> B, A comes before B. Used for task scheduling, build systems, course
prerequisites.


---


## Problem Set

### Medium

  1. Number of Islands               LeetCode #200
     Pattern: BFS or DFS on grid. Must know.

  2. Clone Graph                     LeetCode #133
     Pattern: BFS/DFS + HashMap

  3. Pacific Atlantic Water Flow     LeetCode #417
     Pattern: Multi-source BFS/DFS from borders

  4. Course Schedule                 LeetCode #207
     Pattern: Topological sort / cycle detection. Must know.

  5. Course Schedule II              LeetCode #210
     Pattern: Topological sort (return the ordering)

  6. Rotting Oranges                 LeetCode #994
     Pattern: Multi-source BFS. Must know.

  7. Surrounded Regions              LeetCode #130
     Pattern: DFS from borders

  8. Number of Connected Components  LeetCode #323
     Pattern: Union-Find or DFS

  9. Graph Valid Tree                LeetCode #261
     Pattern: Union-Find or DFS

  10. Network Delay Time             LeetCode #743
      Pattern: Dijkstra's algorithm

### Hard

  1. Word Ladder                     LeetCode #127
     Pattern: BFS on word transformations

  2. Alien Dictionary                LeetCode #269
     Pattern: Topological sort from character ordering

  3. Cheapest Flights Within K Stops LeetCode #787
     Pattern: Modified Dijkstra or Bellman-Ford
