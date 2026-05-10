import java.util.*;

/*
 * ============================================================================
 * UNION-FIND (Disjoint Set Union) -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * UNION-FIND DATA STRUCTURE -- Used by all patterns below
     * -----------------------------------------------------------------------
     *
     * This is the template you should memorize. Every Union-Find problem
     * uses this exact structure with minor tweaks.
     *
     * Two optimizations that make it nearly O(1) per operation:
     * 1. Path compression in find()
     * 2. Union by rank in union()
     * -----------------------------------------------------------------------
     */
    static class UnionFind {
        int[] parent;
        int[] rank;
        int components;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i; // Everyone is their own parent initially
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

            if (rootX == rootY) return false; // Already in same set

            // Union by rank: attach shorter tree under taller tree
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


    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Count Connected Components
     * -----------------------------------------------------------------------
     *
     * LeetCode #323 -- Number of Connected Components in an Undirected Graph
     *
     * Given n nodes and a list of edges, find the number of connected
     * components.
     *
     * APPROACH:
     * Start with n components (each node is its own component).
     * For each edge, union the two nodes. Each successful union reduces
     * the component count by 1.
     *
     * Time: O(E * alpha(n)), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        return uf.components;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Cycle Detection -- Redundant Connection
     * -----------------------------------------------------------------------
     *
     * LeetCode #684 -- Redundant Connection
     *
     * Given a graph that started as a tree with n nodes, one extra edge was
     * added. Find and return that edge.
     *
     * APPROACH:
     * Process edges one by one. For each edge, try to union the two nodes.
     * If they are already connected (find returns same root), this edge
     * creates a cycle -- that is the redundant edge.
     *
     * Time: O(n * alpha(n)), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1); // Nodes are 1-indexed

        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge; // This edge creates a cycle
            }
        }

        return new int[0]; // Should never reach here
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Validate Tree -- Graph Valid Tree
     * -----------------------------------------------------------------------
     *
     * LeetCode #261 -- Graph Valid Tree
     *
     * Given n nodes and edges, determine if the graph is a valid tree.
     *
     * A valid tree has exactly n-1 edges and is fully connected.
     *
     * APPROACH:
     * Check edge count first: must be exactly n-1.
     * Then union all edges. If any union fails (nodes already connected),
     * there is a cycle, so it is not a tree.
     *
     * Time: O(n * alpha(n)), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;

        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return false; // Cycle detected
            }
        }

        return true; // n-1 edges, no cycle = tree
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Group by Shared Property -- Accounts Merge
     * -----------------------------------------------------------------------
     *
     * LeetCode #721 -- Accounts Merge
     *
     * Given a list of accounts where each account has a name and emails,
     * merge accounts that share at least one email.
     *
     * APPROACH:
     * 1. Map each email to an account index
     * 2. If an email appears in multiple accounts, union those accounts
     * 3. Group all emails by their root account
     * 4. Sort and format the result
     *
     * Time: O(n * k * alpha(n) + n * k * log(n*k)) where k = avg emails
     * Space: O(n * k)
     * -----------------------------------------------------------------------
     */
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);

        // Map email -> first account index that owns it
        HashMap<String, Integer> emailToAccount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);

                if (emailToAccount.containsKey(email)) {
                    // This email appeared before, union the two accounts
                    uf.union(i, emailToAccount.get(email));
                } else {
                    emailToAccount.put(email, i);
                }
            }
        }

        // Group emails by root account
        HashMap<Integer, TreeSet<String>> rootToEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToAccount.entrySet()) {
            int root = uf.find(entry.getValue());
            rootToEmails.computeIfAbsent(root, k -> new TreeSet<>())
                        .add(entry.getKey());
        }

        // Build result
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<String>> entry : rootToEmails.entrySet()) {
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(entry.getKey()).get(0)); // Name
            merged.addAll(entry.getValue()); // Sorted emails
            result.add(merged);
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Number of Provinces (Adjacency Matrix)
     * -----------------------------------------------------------------------
     *
     * LeetCode #547 -- Number of Provinces
     *
     * Given an n x n adjacency matrix where isConnected[i][j] = 1 means
     * city i and city j are directly connected, find the number of provinces
     * (connected components).
     *
     * APPROACH:
     * For each pair (i, j) where isConnected[i][j] == 1, union them.
     * Return the component count.
     *
     * Time: O(n^2 * alpha(n)), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.components;
    }


    public static void main(String[] args) {

        System.out.println("--- Connected Components ---");
        System.out.println(countComponents(5, new int[][]{
            {0,1},{1,2},{3,4}}));  // 2

        System.out.println("\n--- Redundant Connection ---");
        System.out.println(Arrays.toString(findRedundantConnection(new int[][]{
            {1,2},{1,3},{2,3}})));  // [2, 3]

        System.out.println("\n--- Graph Valid Tree ---");
        System.out.println(validTree(5, new int[][]{
            {0,1},{0,2},{0,3},{1,4}}));  // true
        System.out.println(validTree(5, new int[][]{
            {0,1},{1,2},{2,3},{1,3},{1,4}}));  // false

        System.out.println("\n--- Accounts Merge ---");
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","john00@mail.com","john_neo@mail.com"));
        accounts.add(Arrays.asList("John","john_neo@mail.com","john01@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));
        System.out.println(accountsMerge(accounts));

        System.out.println("\n--- Number of Provinces ---");
        System.out.println(findCircleNum(new int[][]{
            {1,1,0},{1,1,0},{0,0,1}}));  // 2
    }
}
