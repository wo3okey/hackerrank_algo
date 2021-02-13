package trees;

import java.util.*;
import java.util.stream.Collectors;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data, int depth) {
        this.data = data;
        left = null;
        right = null;
    }
}

/**
[input]
6
4 2 3 1 7 6
1 7
**/
public class Example2 {
    public static Node lca(Node root, int v1, int v2) {
        // dfs로 타겟별 부모리스트 만들기
        List<Node> v1ParentList = new ArrayList<>();
        List<Node> v2ParentList = new ArrayList<>();
        Map<Integer, Node> parentMap = new HashMap<>();
        Map<Integer, Integer> depthMap = new HashMap<>();
        int depth = 0;


        dfs(root, depthMap, depth, parentMap, root, v1ParentList, v1);
        dfs(root, depthMap, depth, parentMap, root, v2ParentList, v2);

        int idx = 0;
        Node result = null;
        while (true) {
            if (v1ParentList.isEmpty() || v2ParentList.isEmpty()) {
                break;
            }

            if (v1ParentList.size() < v2ParentList.size()) {
                for (int i = 0; i < v1ParentList.size(); i++) {
                    if (v2ParentList.contains(v1ParentList.get(i))) {
                        result = v1ParentList.get(i);
                        return result;
                    }
                }
            } else {
                for (int i = 0; i < v2ParentList.size(); i++) {
                    if (v1ParentList.contains(v2ParentList.get(i))) {
                        result = v2ParentList.get(i);
                        return result;
                    }
                }
            }
        }
        return null;
    }

    public static List<Node> dfs(Node cur, Map<Integer, Integer> depthMap, int depth, Map<Integer, Node> parentMap, Node parent, List<Node> parentList, int target) {
        if (cur == null) {
            return parentList;
        }

        if (parent.data == target) {
            parentList.add(parent);
            return parentList;
        }

        parentMap.put(cur.data, parent);
        depthMap.put(cur.data,  depth);

        Queue<Node> queue = new LinkedList<>();
        if (cur.data == target) {
            queue.add(cur);
            parentList.add(cur);

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                Node parentNode = parentMap.get(node.data);

                if (parentNode != null && parentNode.data != node.data) {
                    parentList.add(parentNode);
                    queue.add(parentNode);
                }
            }
            return parentList;
        }

        dfs(cur.left, depthMap, depth + 1, parentMap, cur, parentList, target);
        dfs(cur.right, depthMap, depth + 1, parentMap, cur, parentList, target);

        return parentList;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data, 0);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root, v1, v2);
        System.out.println(ans.data);
    }
}

