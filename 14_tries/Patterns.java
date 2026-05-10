import java.util.*;

/*
 * ============================================================================
 * TRIES (PREFIX TREES) -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * TRIE NODE -- Used by all patterns below
     * -----------------------------------------------------------------------
     *
     * Each node has an array of 26 children (for lowercase English letters)
     * and a flag to mark end of a word. Some problems also store the word
     * itself at the end node for convenience.
     * -----------------------------------------------------------------------
     */
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
        String word = null; // Useful for Word Search II
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Implement Trie
     * -----------------------------------------------------------------------
     *
     * LeetCode #208 -- Implement Trie (Prefix Tree)
     *
     * Implement a trie with insert, search, and startsWith.
     *
     * - insert("apple"): Walk down the trie creating nodes as needed, mark
     *   the last node as end of word.
     * - search("apple"): Walk down the trie following each character. If any
     *   character is missing, return false. If we reach the end, check isEnd.
     * - startsWith("app"): Same as search but we don't need isEnd to be true.
     *
     * Time: O(L) for each operation, Space: O(total characters inserted)
     * -----------------------------------------------------------------------
     */
    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode node = findNode(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return findNode(prefix) != null;
        }

        // Helper: traverse the trie following the given string
        private TrieNode findNode(String s) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) return null;
                node = node.children[index];
            }
            return node;
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Wildcard Search -- Design Add and Search Words
     * -----------------------------------------------------------------------
     *
     * LeetCode #211 -- Design Add and Search Words Data Structure
     *
     * Like a regular trie, but search supports '.' which matches any
     * single character.
     *
     * Example:
     *   addWord("bad")
     *   search("b.d") -> true
     *   search("b..") -> true
     *   search(".a.") -> true
     *
     * For '.', we try ALL children using DFS.
     *
     * Time: Insert O(L), Search O(26^L) worst case with all dots
     * -----------------------------------------------------------------------
     */
    static class WordDictionary {
        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int index, TrieNode node) {
            if (index == word.length()) return node.isEnd;

            char c = word.charAt(index);

            if (c == '.') {
                // Try all possible children
                for (TrieNode child : node.children) {
                    if (child != null && dfs(word, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                // Normal character
                TrieNode child = node.children[c - 'a'];
                if (child == null) return false;
                return dfs(word, index + 1, child);
            }
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Trie + Backtracking -- Word Search II
     * -----------------------------------------------------------------------
     *
     * LeetCode #212 -- Word Search II
     *
     * Given a 2D board of characters and a list of words, find all words
     * that can be formed by sequentially adjacent cells (no cell reused).
     *
     * WHY TRIE:
     * Brute force would do a separate DFS for each word: O(words * m * n * 4^L).
     * With a trie, we do ONE DFS and check all words simultaneously. If the
     * current path doesn't match any prefix in the trie, we prune immediately.
     *
     * APPROACH:
     * 1. Build a trie from all target words
     * 2. DFS from every cell, guided by the trie
     * 3. When we hit an end-of-word node, add the word to results
     * 4. Mark cells as visited during DFS (restore after)
     *
     * OPTIMIZATION: Remove found words from the trie to avoid duplicates
     * and speed up future searches.
     *
     * Time: O(m * n * 4^L), Space: O(total chars in words)
     * -----------------------------------------------------------------------
     */
    public static List<String> findWords(char[][] board, String[] words) {
        // Build trie from all words
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
            node.word = word; // Store the word at the end node
        }

        List<String> result = new ArrayList<>();
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int index = board[r][c] - 'a';
                if (root.children[index] != null) {
                    dfsBoard(board, r, c, root, result);
                }
            }
        }

        return result;
    }

    private static void dfsBoard(char[][] board, int r, int c,
                                  TrieNode node, List<String> result) {
        // Boundary checks
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;
        if (board[r][c] == '#') return; // Already visited

        char ch = board[r][c];
        int index = ch - 'a';
        if (node.children[index] == null) return; // No matching prefix

        node = node.children[index];

        // Found a word
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Avoid duplicates
        }

        // Mark as visited
        board[r][c] = '#';

        // Explore 4 directions
        dfsBoard(board, r + 1, c, node, result);
        dfsBoard(board, r - 1, c, node, result);
        dfsBoard(board, r, c + 1, node, result);
        dfsBoard(board, r, c - 1, node, result);

        // Restore
        board[r][c] = ch;
    }


    public static void main(String[] args) {

        System.out.println("--- Implement Trie ---");
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));     // true
        System.out.println(trie.search("app"));        // false
        System.out.println(trie.startsWith("app"));    // true
        trie.insert("app");
        System.out.println(trie.search("app"));        // true

        System.out.println("\n--- Word Dictionary (Wildcard) ---");
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad"));   // false
        System.out.println(dict.search("bad"));   // true
        System.out.println(dict.search(".ad"));   // true
        System.out.println(dict.search("b.."));   // true

        System.out.println("\n--- Word Search II ---");
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));  // [oath, eat]
    }
}
