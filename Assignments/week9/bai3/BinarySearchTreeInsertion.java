public class BinarySearchTreeInsertion {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        Node current = root;
        while (true) {
            if (data < current.data) {
                if (current.left == null) {
                    current.left = new Node(data);
                    break;
                }
                current = current.left;
            } else if (data > current.data) {
                if (current.right == null) {
                    current.right = new Node(data);
                    break;
                }
                current = current.right;
            } else {
                break;
            }
        }
        return root;
    }
}
