import java.util.*;

/*
 * ============================================================================
 * GRAPHS -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: DFS/BFS on Grid -- Number of Islands
     * -----------------------------------------------------------------------
     *
     * LeetCode #200 -- Number of Islands
     *
     * Given a 2D grid of '1' (land) and '0' (water), count the number of
     * islands. An island is surrounded by water and formed by connecting
     * adjacent land cells horizontally or vertically.
     *
     * THOUGHT PROCESS:
     * Iterate through every cell. When we find a '1' that has not been visited,
     * it is a new island. Perform DFS or BFS from that cell to mark all
     * connected land cells as visited. Increment the island count.
     *
     * We can mark cells as visited by changing '1' to '0' (modifying the grid
     * in place) or by using a separate visited array.
     *
     * Time: O(m * n), Space: O(m * n) in worst case for DFS call stack
     * -----------------------------------------------------------------------
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfsIsland(grid, r, c);
                }
            }
        }

        return count;
    }

    private static void dfsIsland(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;
        if (grid[r][c] != '1') return;

        grid[r][c] = '0'; // Mark as visited

        dfsIsland(grid, r + 1, c);
        dfsIsland(grid, r - 1, c);
        dfsIsland(grid, r, c + 1);
        dfsIsland(grid, r, c - 1);
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Multi-Source BFS -- Rotting Oranges
     * -----------------------------------------------------------------------
     *
     * LeetCode #994 -- Rotting Oranges
     *
     * Grid has: 0 = empty, 1 = fresh orange, 2 = rotten orange.
     * Each minute, rotten oranges rot their 4-directional neighbors.
     * Return the minimum minutes until no fresh oranges remain, or -1.
     *
     * THOUGHT PROCESS:
     * This is a BFS problem where we start from MULTIPLE sources (all rotten
     * oranges) simultaneously. We add all initially rotten oranges to the
     * queue and BFS from all of them at once. Each "level" of BFS is one
     * minute.
     *
     * Time: O(m * n), Space: O(m * n)
     * -----------------------------------------------------------------------
     */
    public static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        // Find all rotten oranges and count fresh ones
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) return 0;

        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        int minutes = 0;

        while (!queue.isEmpty() && fresh > 0) {
            minutes++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();

                for (int[] dir : directions) {
                    int nr = cell[0] + dir[0];
                    int nc = cell[1] + dir[1];

                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return fresh == 0 ? minutes : -1;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Topological Sort -- Course Schedule
     * -----------------------------------------------------------------------
     *
     * LeetCode #207 -- Course Schedule
     *
     * There are numCourses courses and prerequisites. Can you finish all?
     * (i.e., is there a valid ordering, meaning no cycles in the dependency graph?)
     *
     * Example: numCourses = 2, prerequisites = [[1,0]] -> true
     *          (Take course 0 first, then course 1.)
     *
     * THOUGHT PROCESS:
     * Build a directed graph from prerequisites. If there is a cycle, you
     * cannot finish all courses.
     *
     * Kahn's Algorithm (BFS-based topological sort):
     * 1. Compute the in-degree of each node (how many prerequisites it has).
     * 2. Add all nodes with in-degree 0 to a queue (no prerequisites).
     * 3. Process the queue: for each node, reduce the in-degree of its
     *    neighbors by 1. If a neighbor's in-degree becomes 0, add it to queue.
     * 4. If we processed all nodes, there is no cycle.
     *
     * Time: O(V + E), Space: O(V + E)
     * -----------------------------------------------------------------------
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]); // pre[1] -> pre[0]
            inDegree[pre[0]]++;
        }

        // Add all nodes with in-degree 0 to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processed = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            processed++;

            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return processed == numCourses;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Dijkstra's Algorithm -- Shortest Path in Weighted Graph
     * -----------------------------------------------------------------------
     *
     * LeetCode #743 -- Network Delay Time
     *
     * Given a weighted directed graph, find the shortest time for a signal
     * to reach all nodes from a given source. Return -1 if not all reachable.
     *
     * THOUGHT PROCESS:
     * This is Dijkstra's algorithm. It finds the shortest path from a source
     * to all other nodes in a graph with non-negative weights.
     *
     * 1. Initialize distances to infinity, source distance to 0.
     * 2. Use a min-heap (priority queue) ordered by distance.
     * 3. Process the node with the smallest distance. For each neighbor,
     *    if we can reach it with a shorter distance, update and add to heap.
     *
     * Time: O(E log V), Space: O(V + E)
     * -----------------------------------------------------------------------
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list: node -> [(neighbor, weight), ...]
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }

        // Distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Min-heap: (distance, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int node = curr[1];

            // Skip if we already found a shorter path
            if (d > dist[node]) continue;

            for (int[] edge : graph.get(node)) {
                int neighbor = edge[0];
                int weight = edge[1];

                if (dist[node] + weight < dist[neighbor]) {
                    dist[neighbor] = dist[node] + weight;
                    pq.offer(new int[]{dist[neighbor], neighbor});
                }
            }
        }

        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            maxDist = Math.max(maxDist, dist[i]);
        }

        return maxDist;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Clone Graph using BFS + HashMap
     * -----------------------------------------------------------------------
     *
     * LeetCode #133 -- Clone Graph
     *
     * Given a reference to a node in a connected undirected graph, return a
     * deep copy (clone) of the graph.
     *
     * THOUGHT PROCESS:
     * Use a HashMap to map original nodes to their clones. BFS through the
     * original graph. For each unvisited neighbor, create a clone and add
     * the edge.
     *
     * Time: O(V + E), Space: O(V)
     * -----------------------------------------------------------------------
     */
    static class Node {
        int val;
        List<Node> neighbors;
        Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public static Node cloneGraph(Node node) {
        if (node == null) return null;

        HashMap<Node, Node> map = new HashMap<>(); // original -> clone
        Queue<Node> queue = new LinkedList<>();

        map.put(node, new Node(node.val));
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node neighbor : current.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                map.get(current).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }


    public static void main(String[] args) {

        System.out.println("--- Number of Islands ---");
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println(numIslands(grid));  // 3

        System.out.println("\n--- Rotting Oranges ---");
        int[][] oranges = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(oranges));  // 4

        System.out.println("\n--- Course Schedule ---");
        System.out.println(canFinish(2, new int[][]{{1,0}}));          // true
        System.out.println(canFinish(2, new int[][]{{1,0},{0,1}}));    // false (cycle)

        System.out.println("\n--- Network Delay Time ---");
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(networkDelayTime(times, 4, 2));  // 2
    }
}
