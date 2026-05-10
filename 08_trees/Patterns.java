import java.util.*;

/*
 * ============================================================================
 * TREES -- Core Patterns
 * ============================================================================
 */


public class Patterns {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }


    /*
     * -----------------------------------------------------------------------
     * TREE TRAVERSALS -- The Foundation
     * -----------------------------------------------------------------------
     * You must be able to write all four traversals from memory.
     * -----------------------------------------------------------------------
     */

    // In-order: Left, Root, Right (gives sorted order for BST)
    public static void inorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    // Pre-order: Root, Left, Right
    public static void preorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    // Post-order: Left, Right, Root
    public static void postorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    // Level-order (BFS): Level by level using a queue
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(level);
        }

        return result;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 1: Simple DFS -- Maximum Depth
     * -----------------------------------------------------------------------
     *
     * LeetCode #104 -- Maximum Depth of Binary Tree
     *
     * The depth of a tree is the number of nodes along the longest path from
     * root to the farthest leaf.
     *
     * THOUGHT PROCESS:
     * The depth of a tree is 1 + max(depth of left subtree, depth of right subtree).
     * Base case: if node is null, depth is 0.
     *
     * Trust the recursion: assume maxDepth correctly computes the depth of
     * any subtree, and use that to compute the depth of the current tree.
     *
     * Time: O(n), Space: O(h) where h = height of tree
     * -----------------------------------------------------------------------
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 2: Modify the Tree -- Invert Binary Tree
     * -----------------------------------------------------------------------
     *
     * LeetCode #226 -- Invert Binary Tree
     *
     * Swap every left and right child in the tree.
     *
     * THOUGHT PROCESS:
     * For each node, swap its left and right children. Then recursively
     * invert both subtrees. That is it.
     *
     * Time: O(n), Space: O(h)
     * -----------------------------------------------------------------------
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // Swap children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively invert subtrees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 3: DFS with Global State -- Diameter of Binary Tree
     * -----------------------------------------------------------------------
     *
     * LeetCode #543 -- Diameter of Binary Tree
     *
     * The diameter is the length of the longest path between any two nodes.
     * This path may or may not pass through the root.
     *
     * THOUGHT PROCESS:
     * At each node, the longest path THROUGH that node is:
     *   leftHeight + rightHeight
     *
     * We compute the height of each subtree (like maxDepth), but at each
     * node we also check if leftHeight + rightHeight is a new maximum.
     *
     * We use a class-level variable (or an array of size 1) to track the
     * maximum diameter found so far.
     *
     * Time: O(n), Space: O(h)
     * -----------------------------------------------------------------------
     */
    private static int maxDiameter;

    public static int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        heightForDiameter(root);
        return maxDiameter;
    }

    private static int heightForDiameter(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = heightForDiameter(node.left);
        int rightHeight = heightForDiameter(node.right);

        // Update diameter: the path through this node
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return the height of this subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 4: BST Property -- Validate BST
     * -----------------------------------------------------------------------
     *
     * LeetCode #98 -- Validate Binary Search Tree
     *
     * Check if a binary tree is a valid BST.
     *
     * THOUGHT PROCESS:
     * A common mistake: just checking that left < root < right for each node.
     * That is not enough. Every node in the left subtree must be less than
     * the root, not just the direct left child.
     *
     * Correct approach: pass a valid range (min, max) down. Each node must
     * be within its range. The left child's range is (min, currentVal).
     * The right child's range is (currentVal, max).
     *
     * Time: O(n), Space: O(h)
     * -----------------------------------------------------------------------
     */
    public static boolean isValidBST(TreeNode root) {
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validateBST(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return validateBST(node.left, min, node.val)
            && validateBST(node.right, node.val, max);
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 5: Lowest Common Ancestor (LCA)
     * -----------------------------------------------------------------------
     *
     * LeetCode #236 -- Lowest Common Ancestor of a Binary Tree
     *
     * Given two nodes p and q, find their lowest common ancestor.
     *
     * THOUGHT PROCESS:
     * Recursively search the tree:
     *   - If the current node is null, return null.
     *   - If the current node is p or q, return it.
     *   - Recurse on both subtrees.
     *   - If both subtrees return non-null, this node is the LCA.
     *   - If only one subtree returns non-null, return that result.
     *
     * Time: O(n), Space: O(h)
     * -----------------------------------------------------------------------
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root; // Both sides found something
        return left != null ? left : right;             // Return whichever side found something
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 6: BST Property -- Kth Smallest Element
     * -----------------------------------------------------------------------
     *
     * LeetCode #230 -- Kth Smallest Element in a BST
     *
     * THOUGHT PROCESS:
     * In-order traversal of a BST gives nodes in sorted order. So the kth
     * element in an in-order traversal is the kth smallest.
     *
     * We can do this iteratively with a stack to avoid generating the full
     * traversal.
     *
     * Time: O(h + k), Space: O(h)
     * -----------------------------------------------------------------------
     */
    public static int kthSmallest(TreeNode root, int k) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go as far left as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            k--;
            if (k == 0) return current.val;

            current = current.right;
        }

        return -1; // Should not reach here if k is valid
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 7: Build Tree from Traversals
     * -----------------------------------------------------------------------
     *
     * LeetCode #105 -- Construct Binary Tree from Preorder and Inorder
     *
     * Given preorder and inorder traversal arrays, construct the tree.
     *
     * THOUGHT PROCESS:
     * In preorder, the first element is always the root. In inorder, everything
     * to the left of the root is the left subtree, everything to the right
     * is the right subtree.
     *
     * 1. The first element of preorder is the root.
     * 2. Find this root in inorder. Elements to the left form the left subtree.
     *    Elements to the right form the right subtree.
     * 3. Recurse with the appropriate subarrays.
     *
     * Use a HashMap to find the root's position in inorder in O(1).
     *
     * Time: O(n), Space: O(n)
     * -----------------------------------------------------------------------
     */
    private static int preorderIndex;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorderMap, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeHelper(int[] preorder,
                                             HashMap<Integer, Integer> inorderMap,
                                             int inLeft, int inRight) {
        if (inLeft > inRight) return null;

        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inorderIndex = inorderMap.get(rootVal);

        root.left = buildTreeHelper(preorder, inorderMap, inLeft, inorderIndex - 1);
        root.right = buildTreeHelper(preorder, inorderMap, inorderIndex + 1, inRight);

        return root;
    }


    /*
     * -----------------------------------------------------------------------
     * PATTERN 8: Binary Tree Maximum Path Sum (HARD)
     * -----------------------------------------------------------------------
     *
     * LeetCode #124 -- Binary Tree Maximum Path Sum
     *
     * A path is any sequence of nodes connected by edges. Find the maximum
     * sum of any path.
     *
     * THOUGHT PROCESS:
     * At each node, compute the maximum "gain" from its left and right subtrees.
     * Gain means: the maximum sum of a path starting from this node and going
     * down through one subtree.
     *
     * At each node, the maximum path THROUGH this node is:
     *   node.val + leftGain + rightGain
     *
     * But the gain we RETURN to the parent is:
     *   node.val + max(leftGain, rightGain)
     * because a path cannot fork (it must go through at most one child).
     *
     * If a gain is negative, we treat it as 0 (do not include that subtree).
     *
     * Time: O(n), Space: O(h)
     * -----------------------------------------------------------------------
     */
    private static int maxPathResult;

    public static int maxPathSum(TreeNode root) {
        maxPathResult = Integer.MIN_VALUE;
        maxPathGain(root);
        return maxPathResult;
    }

    private static int maxPathGain(TreeNode node) {
        if (node == null) return 0;

        int leftGain = Math.max(0, maxPathGain(node.left));
        int rightGain = Math.max(0, maxPathGain(node.right));

        // Path through this node
        maxPathResult = Math.max(maxPathResult, node.val + leftGain + rightGain);

        // Return the max gain going through one side
        return node.val + Math.max(leftGain, rightGain);
    }


    // Helper: Build tree from array (level order, -1 means null)
    public static TreeNode buildFromArray(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode node = queue.poll();
            if (i < arr.length && arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }


    public static void main(String[] args) {
        //       3
        //      / \
        //     9   20
        //        /  \
        //       15   7
        TreeNode root = buildFromArray(new Integer[]{3, 9, 20, null, null, 15, 7});

        System.out.println("--- Level Order Traversal ---");
        System.out.println(levelOrder(root));  // [[3], [9, 20], [15, 7]]

        System.out.println("\n--- Max Depth ---");
        System.out.println(maxDepth(root));  // 3

        System.out.println("\n--- In-order ---");
        List<Integer> inorderResult = new ArrayList<>();
        inorder(root, inorderResult);
        System.out.println(inorderResult);

        //     2
        //    / \
        //   1   3
        TreeNode bst = buildFromArray(new Integer[]{2, 1, 3});
        System.out.println("\n--- Validate BST ---");
        System.out.println(isValidBST(bst));  // true

        //       3
        //      / \
        //     5   1
        //    / \ / \
        //   6  2 0  8
        TreeNode tree2 = buildFromArray(new Integer[]{3, 5, 1, 6, 2, 0, 8});
        System.out.println("\n--- Diameter ---");
        System.out.println(diameterOfBinaryTree(tree2));

        System.out.println("\n--- Max Path Sum ---");
        TreeNode tree3 = buildFromArray(new Integer[]{-10, 9, 20, null, null, 15, 7});
        System.out.println(maxPathSum(tree3));  // 42 (15 + 20 + 7)
    }
}
