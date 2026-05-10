import java.util.*;

/*
 * ============================================================================
 * RECURSION AND BACKTRACKING -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Subsets (The Foundation)
     * -----------------------------------------------------------------------
     *
     * LeetCode #78 -- Subsets
     *
     * Given an array of distinct integers, return all possible subsets.
     *
     * Example: [1, 2, 3] -> [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]
     *
     * THOUGHT PROCESS:
     * At each index, we have two choices: include this element or skip it.
     * We explore both paths. When we reach the end of the array, whatever
     * we have collected is a valid subset.
     *
     * Alternatively, iterate from the current index forward. For each element,
     * include it and recurse with the next index. This naturally generates
     * all subsets.
     *
     * Time: O(n * 2^n) -- 2^n subsets, each takes O(n) to copy
     * Space: O(n) for the recursion depth
     * -----------------------------------------------------------------------
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackSubsets(int[] nums, int start,
                                          List<Integer> path,
                                          List<List<Integer>> result) {
        result.add(new ArrayList<>(path)); // Add a copy of the current subset

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);                            // Choose
            backtrackSubsets(nums, i + 1, path, result);  // Explore
            path.remove(path.size() - 1);                 // Unchoose
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Subsets with Duplicates
     * -----------------------------------------------------------------------
     *
     * LeetCode #90 -- Subsets II
     *
     * Given an array that MAY contain duplicates, return all unique subsets.
     *
     * Example: [1, 2, 2] -> [[], [1], [2], [1,2], [2,2], [1,2,2]]
     *
     * THOUGHT PROCESS:
     * Same as subsets, but we need to skip duplicates. Sort the array first.
     * Then, if the current element is the same as the previous element at
     * the same level of recursion, skip it.
     *
     * The key condition: if (i > start && nums[i] == nums[i-1]) skip.
     * This ensures we do not create duplicate subsets.
     *
     * Time: O(n * 2^n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort to group duplicates together
        backtrackSubsetsDup(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackSubsetsDup(int[] nums, int start,
                                              List<Integer> path,
                                              List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            // Skip duplicates at the same level
            if (i > start && nums[i] == nums[i - 1]) continue;

            path.add(nums[i]);
            backtrackSubsetsDup(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: Permutations
     * -----------------------------------------------------------------------
     *
     * LeetCode #46 -- Permutations
     *
     * Given an array of distinct integers, return all possible permutations.
     *
     * Example: [1, 2, 3] -> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * THOUGHT PROCESS:
     * Unlike subsets where order does not matter, in permutations every
     * arrangement counts. So we need to try every element at every position.
     *
     * We use a "used" boolean array to track which elements are already in
     * the current permutation. For each position, we try every unused element.
     *
     * Time: O(n * n!) -- n! permutations, each takes O(n) to copy
     * Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackPermute(nums, used, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackPermute(int[] nums, boolean[] used,
                                          List<Integer> path,
                                          List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // Skip elements already in the permutation

            used[i] = true;
            path.add(nums[i]);
            backtrackPermute(nums, used, path, result);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: Combination Sum (Reuse Allowed)
     * -----------------------------------------------------------------------
     *
     * LeetCode #39 -- Combination Sum
     *
     * Given an array of distinct integers and a target, return all unique
     * combinations that sum to the target. The same number can be used
     * multiple times.
     *
     * Example: candidates = [2, 3, 6, 7], target = 7
     *          -> [[2,2,3], [7]]
     *
     * THOUGHT PROCESS:
     * Similar to subsets, but:
     *   - We stop when the sum reaches (or exceeds) the target.
     *   - We allow reusing elements (pass i instead of i+1 in recursion).
     *
     * Time: varies depending on target, Space: O(target / min(candidates))
     * -----------------------------------------------------------------------
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombSum(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackCombSum(int[] candidates, int remaining,
                                          int start, List<Integer> path,
                                          List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (remaining < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            // Pass i (not i+1) because we can reuse the same element
            backtrackCombSum(candidates, remaining - candidates[i], i, path, result);
            path.remove(path.size() - 1);
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Generate Parentheses
     * -----------------------------------------------------------------------
     *
     * LeetCode #22 -- Generate Parentheses
     *
     * Given n pairs of parentheses, generate all combinations of
     * well-formed parentheses.
     *
     * Example: n = 3 -> ["((()))","(()())","(())()","()(())","()()()"]
     *
     * THOUGHT PROCESS:
     * At each step, we can add '(' if we have not used all of them.
     * We can add ')' if the number of ')' used is less than '(' used
     * (to keep it valid).
     *
     * The two choices at each step are: add '(' or add ')'.
     * The constraint: close count must never exceed open count.
     *
     * Time: O(4^n / sqrt(n)) -- Catalan number, Space: O(n)
     * -----------------------------------------------------------------------
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrackParens(n, 0, 0, new StringBuilder(), result);
        return result;
    }

    private static void backtrackParens(int n, int open, int close,
                                         StringBuilder path, List<String> result) {
        if (path.length() == 2 * n) {
            result.add(path.toString());
            return;
        }

        if (open < n) {
            path.append('(');
            backtrackParens(n, open + 1, close, path, result);
            path.deleteCharAt(path.length() - 1);
        }

        if (close < open) {
            path.append(')');
            backtrackParens(n, open, close + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: Word Search (Backtracking on Grid)
     * -----------------------------------------------------------------------
     *
     * LeetCode #79 -- Word Search
     *
     * Given a 2D grid of characters and a word, determine if the word exists
     * in the grid by moving horizontally or vertically to adjacent cells.
     * Each cell can be used at most once.
     *
     * THOUGHT PROCESS:
     * For each cell, if it matches the first character of the word, start a
     * DFS. At each step, mark the cell as visited, explore all four neighbors,
     * and unmark when backtracking.
     *
     * Time: O(m * n * 4^L) where L = word length, Space: O(L)
     * -----------------------------------------------------------------------
     */
    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (dfsWordSearch(board, word, r, c, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfsWordSearch(char[][] board, String word,
                                          int r, int c, int index) {
        if (index == word.length()) return true; // Found all characters

        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return false;
        if (board[r][c] != word.charAt(index)) return false;

        // Mark as visited by changing the character
        char original = board[r][c];
        board[r][c] = '#';

        // Explore all four directions
        boolean found = dfsWordSearch(board, word, r + 1, c, index + 1)
                      || dfsWordSearch(board, word, r - 1, c, index + 1)
                      || dfsWordSearch(board, word, r, c + 1, index + 1)
                      || dfsWordSearch(board, word, r, c - 1, index + 1);

        // Restore the character (backtrack)
        board[r][c] = original;

        return found;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: N-Queens (Classic Backtracking)
     * -----------------------------------------------------------------------
     *
     * LeetCode #51 -- N-Queens
     *
     * Place n queens on an n x n chessboard so that no two queens attack
     * each other. Return all distinct solutions.
     *
     * THOUGHT PROCESS:
     * Place queens row by row. For each row, try placing a queen in each
     * column. Check if it conflicts with any previously placed queen
     * (same column, same diagonal, same anti-diagonal).
     *
     * We track occupied columns, diagonals (row - col), and anti-diagonals
     * (row + col) using sets.
     *
     * Time: O(n!), Space: O(n^2)
     * -----------------------------------------------------------------------
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');

        Set<Integer> cols = new HashSet<>();
        Set<Integer> diagonals = new HashSet<>();     // row - col
        Set<Integer> antiDiagonals = new HashSet<>();  // row + col

        backtrackQueens(board, 0, cols, diagonals, antiDiagonals, result);
        return result;
    }

    private static void backtrackQueens(char[][] board, int row,
                                         Set<Integer> cols,
                                         Set<Integer> diags,
                                         Set<Integer> antiDiags,
                                         List<List<String>> result) {
        if (row == board.length) {
            // Convert board to list of strings
            List<String> solution = new ArrayList<>();
            for (char[] r : board) solution.add(new String(r));
            result.add(solution);
            return;
        }

        for (int col = 0; col < board.length; col++) {
            int diag = row - col;
            int antiDiag = row + col;

            // Check if this position is under attack
            if (cols.contains(col) || diags.contains(diag) || antiDiags.contains(antiDiag)) {
                continue;
            }

            // Place the queen
            board[row][col] = 'Q';
            cols.add(col);
            diags.add(diag);
            antiDiags.add(antiDiag);

            // Recurse to the next row
            backtrackQueens(board, row + 1, cols, diags, antiDiags, result);

            // Remove the queen (backtrack)
            board[row][col] = '.';
            cols.remove(col);
            diags.remove(diag);
            antiDiags.remove(antiDiag);
        }
    }


    public static void main(String[] args) {

        System.out.println("--- Subsets ---");
        System.out.println(subsets(new int[]{1, 2, 3}));

        System.out.println("\n--- Subsets II ---");
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));

        System.out.println("\n--- Permutations ---");
        System.out.println(permute(new int[]{1, 2, 3}));

        System.out.println("\n--- Combination Sum ---");
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));

        System.out.println("\n--- Generate Parentheses ---");
        System.out.println(generateParenthesis(3));

        System.out.println("\n--- Word Search ---");
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        System.out.println(exist(board, "ABCCED"));  // true
        System.out.println(exist(board, "SEE"));      // true
        System.out.println(exist(board, "ABCB"));     // false

        System.out.println("\n--- N-Queens (n=4) ---");
        List<List<String>> queens = solveNQueens(4);
        for (List<String> solution : queens) {
            for (String row : solution) System.out.println(row);
            System.out.println();
        }
    }
}
