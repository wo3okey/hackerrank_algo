import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        Queue<Node> queue = new LinkedList<>();
        Tree tree = new Tree();

        // set root
        int depth = 0;
        Node root = new Node(1, depth);
        queue.add(root);
        depth++;

        // set binary tree
        for (int i = 0; i < indexes.length; i++) {
            Node node = queue.poll();
            Node leftNode = indexes[i][0] == - 1 ? null : new Node(indexes[i][0], node.depth + 1);
            Node rightNode = indexes[i][1] == - 1 ? null : new Node(indexes[i][1], node.depth + 1);

            if (node.left == null && leftNode != null) {
                node.left = leftNode;
                queue.add(leftNode);
            }

            if (node.right == null && rightNode != null) {
                node.right = rightNode;
                queue.add(rightNode);
            }
        }

        // swap and print
        int[][] result = new int[queries.length][indexes.length];
        for (int i = 0; i < queries.length; i++) {
            // swap
            tree.swapTree(root, queries[i]);

            // print
            List<Integer> ref = new ArrayList<>();
            tree.inOrderPrintTree(root, ref);
            result[i] = ref.stream().mapToInt(r->r).toArray();
        }
        return result;
    }

    static class Node {
        int data;
        int depth;
        Node left;
        Node right;

        Node(int data, int depth) {
            this.data = data;
            this.depth = depth;
            this.left = null;
            this.right = null;
        }
    }

    static class Tree {
        public void swapTree(Node root, int k) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }

                if (cur.right != null) {
                    queue.add(cur.right);
                }

                int nextDepth = cur.depth + 1;
                if (nextDepth >= k && nextDepth % k == 0 ) {
                    Node tmp = cur.left;
                    cur.left = cur.right;
                    cur.right = tmp;
                }
            }
//
//            if (cur == null) {
//                return ;
//            }
//
//            swapTree(cur.left, depth + 1, k);
//            swapTree(cur.right, depth + 1, k);
//
//            if (depth >= k && depth % k == 0 ) {
//                Node tmp = cur.left;
//                cur.left = cur.right;
//                cur.right = tmp;
//            }
        }

        public void inOrderPrintTree(Node node, List<Integer> ref) {
            if (node == null) {
                return;
            }

            inOrderPrintTree(node.left, ref);
            ref.add(node.data);
            inOrderPrintTree(node.right, ref);
        }
    }

    public static void main(String[] args) {
        int[][] indexes = {{2,3}
                ,{4,-1}
                ,{5,-1}
                ,{6,-1}
                ,{7,8}
                ,{-1,9}
                ,{-1,-1}
                ,{10,11}
                ,{-1,-1}
                ,{-1,-1}
                ,{-1,-1}};
        int[] queries = {2, 2};
        int[][] result = swapNodes(indexes, queries);

        System.out.println(result);
    }
}

