package com.datastructure_arithmetic.datastructure.tree;

public class AVLBinaryTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static class Node {
        private int no;
        private Node left;
        private Node right;

        public Node(int no) {
            this.no = no;
        }

        public int getHeight() {
            return Math.max(this.left == null ? 0 : this.left.getHeight(), this.right == null ? 0 : this.right.getHeight()) + 1;
        }

        public void del(int no) {
            Node temp;
            if (this.left != null && no < this.no) {
                if (this.left.no == no) {
                    temp = this.left;
                    this.left = null;
                    this.add(temp.left);
                    this.add(temp.right);
                    return;
                }
                this.left.del(no);
            } else if (this.right != null) {
                if (this.right.no == no) {
                    temp = this.right;
                    this.right = null;
                    this.add(temp.left);
                    this.add(temp.right);
                    return;
                }
                this.right.del(no);
            }
        }

        public void add(Node node) {
            if (node == null) {
                return;
            }
            if (node.no < this.no) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
            //加入平衡二叉树的处理
            if ((this.right == null ? 0 : this.right.getHeight()) - (this.left == null ? 0 : this.left.getHeight()) > 1) {
                //满足以下条件需要进行双旋转
                if (this.right != null && (this.right.left == null ? 0 : this.right.left.getHeight()) > (this.right.right == null ? 0 : this.right.right.getHeight())) {
                    //右旋转
                    this.right.rightRotate();
                }
                //左旋转
                leftRotate();
                return;
            }
            if ((this.left == null ? 0 : this.left.getHeight()) - (this.right == null ? 0 : this.right.getHeight()) > 1) {
                //满足以下条件需要进行双旋转
                if (this.left != null && (this.left.right == null ? 0 : this.left.right.getHeight()) > (this.left.left == null ? 0 : this.left.left.getHeight())) {
                    //左旋转
                    this.left.leftRotate();
                }
                //右旋转
                rightRotate();
            }
        }

        private void rightRotate() {
            Node node = new Node(this.no);
            node.right = this.right;
            node.left = this.left.right;
            this.no = this.left.no;
            this.left = this.left.left;
            this.right = node;
        }

        private void leftRotate() {
            Node node = new Node(this.no);
            node.left = this.left;
            node.right = this.right.left;
            this.no = this.right.no;
            this.right = this.right.right;
            this.left = node;
        }

        public void infixOrderList() {
            if (this.left != null) {
                this.left.infixOrderList();
            }
            System.out.println(this.no);
            if (this.right != null) {
                this.right.infixOrderList();
            }
        }
    }

    public void delete(int no) {
        if (root == null) {
            System.out.println("tree is empty!");
        }
        if (root.no != no) {
            root.del(no);
        } else {
            delRootNode();
        }
    }

    private void delRootNode() {
        Node temp;
        if (root.right != null) {
            temp = root.right;
        } else if (root.left != null) {
            root = root.left;
            return;
        } else {
            root = null;
            return;
        }
        int value;
        if (temp.left != null) {
            while (temp.left.left != null) {
                temp = temp.left;
            }
            value = temp.left.no;
        } else {
            value = temp.no;
        }
        root.del(value);
        root.no = value;
    }

    public void add(int no) {
        if (this.root == null) {
            this.root = new Node(no);
        } else {
            root.add(new Node(no));
        }
    }

    public void infixOrderList() {
        if (this.root != null) {
            this.root.infixOrderList();
        } else {
            System.out.println("this tree is empty");
        }
    }

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLBinaryTree tree = new AVLBinaryTree();
        for (int i : arr) {
            tree.add(i);
        }
        tree.infixOrderList();
        System.out.println("左子树" + tree.getRoot().left.getHeight());
        System.out.println("右子树" + tree.getRoot().right.getHeight());
    }
}
