public class IsBinarySearchTree {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    boolean checkBST(Node root) {
        return isWithinBounds(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isWithinBounds(Node node, long lowerBound, long upperBound) {
        if (node == null) {
            return true;
        }
        if (node.data <= lowerBound || node.data >= upperBound) {
            return false;
        }
        return isWithinBounds(node.left, lowerBound, node.data)
                && isWithinBounds(node.right, node.data, upperBound);
    }
}
