# Union-Find (Disjoint Set Union)

## What Is Union-Find?

Union-Find is a data structure that tracks a collection of elements partitioned into disjoint (non-overlapping) sets. It supports two main operations:

- **Find:** Which set does this element belong to?
- **Union:** Merge two sets into one.

Think of it like groups of friends. Initially everyone is their own group. When two people become friends, their entire friend groups merge. Union-Find lets you quickly check "are these two people in the same friend group?" and "merge these two friend groups."


## When to Use Union-Find

Use Union-Find when you see these keywords or patterns:

- "Connected components" in an undirected graph
- "Are two nodes connected?"
- "Group elements together"
- "Merge sets"
- "Minimum spanning tree" (Kruskal's algorithm uses Union-Find)
- "Redundant connection" or "detect a cycle in undirected graph"
- "Accounts merge" or "similar groups"

If BFS/DFS can solve the connectivity problem, Union-Find usually can too, and sometimes more efficiently -- especially when connections are added dynamically.


## How It Works

### The Basic Idea

Each element has a "parent" pointer. The root of each tree is the representative (or "leader") of that set.

```
Initial state (everyone is their own parent):
parent = [0, 1, 2, 3, 4]

After union(0, 1) and union(2, 3):
parent = [0, 0, 2, 2, 4]

Sets: {0, 1}, {2, 3}, {4}
```

### Find with Path Compression

When you call find(x), you walk up the parent chain to the root. Path compression makes every node along the path point directly to the root. This flattens the tree and makes future operations nearly O(1).

```
Before path compression:    After find(4):
0                           0
|                          /|\
1                         1  2  4
|
2
|
4
```

### Union by Rank

When merging two sets, attach the smaller tree under the root of the larger tree. This keeps trees shallow. "Rank" is an upper bound on the height of the tree.

Without rank: trees can become long chains (O(n) find).
With rank: trees stay flat (nearly O(1) find).


## Time Complexity

With both path compression and union by rank:

| Operation | Amortized Time |
|---|---|
| Find | O(alpha(n)) -- nearly O(1) |
| Union | O(alpha(n)) -- nearly O(1) |
| Space | O(n) |

alpha(n) is the inverse Ackermann function. For all practical purposes, it is less than 5 for any realistic input size. So treat it as O(1).


## Union-Find Template in Java

```java
class UnionFind {
    int[] parent;
    int[] rank;
    int components;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        components = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false; // Already connected

        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        components--;
        return true;
    }

    boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```


## Union-Find vs DFS/BFS

| Scenario | Union-Find | DFS/BFS |
|---|---|---|
| Static graph (all edges given upfront) | Works | Works (often simpler) |
| Dynamic graph (edges added over time) | Better | Must rebuild each time |
| Count connected components | Easy (track count) | Need full traversal |
| Detect cycle in undirected graph | Natural fit | Also works |
| MST (Kruskal's algorithm) | Required | Not applicable |


## Problems to Practice

| Problem | Difficulty | Core Idea |
|---|---|---|
| Number of Connected Components (LC #323) | Medium | Basic union-find |
| Redundant Connection (LC #684) | Medium | Edge that creates a cycle |
| Graph Valid Tree (LC #261) | Medium | n-1 edges + all connected |
| Accounts Merge (LC #721) | Medium | Union by shared emails |
| Longest Consecutive Sequence (LC #128) | Medium | Alternative to hashset approach |
| Number of Provinces (LC #547) | Medium | Adjacency matrix + union |
| Smallest String With Swaps (LC #1202) | Medium | Group indices, sort within groups |
