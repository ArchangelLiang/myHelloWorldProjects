package com.datastructure_arithmetic.datastructure.tree;

public class BinarySortTree {

    private Node root;

    public static class Node {
        private int no;
        private Node left;
        private Node right;

        public Node(int no) {
            this.no = no;
        }

        public void del(int no) {
            Node temp;
            if (this.left != null && no < this.no){
                if (this.left.no == no){
                    temp = this.left;
                    this.left = null;
                    this.add(temp.left);
                    this.add(temp.right);
                    return;
                }
               this.left.del(no);
            } else if (this.right != null){
                if (this.right.no == no){
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

    public void delete(int no){
        if (root == null){
            System.out.println("tree is empty!");
        }
        if (root.no != no){
            root.del(no);
        } else {
            delRootNode();
        }
    }

    private void delRootNode() {
        Node temp;
        if (root.right != null){
            temp = root.right;
        } else if (root.left != null){
            root = root.left;
            return;
        }  else {
            root = null;
            return;
        }
        int value;
        if (temp.left != null){
            while (temp.left.left != null){
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
        int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int i : arr) {
            tree.add(i);
        }
        tree.delete(2);
        tree.delete(5);
        tree.delete(9);
        tree.delete(12);
        tree.delete(7);
        tree.delete(3);
        tree.delete(10);
        tree.delete(1);
        tree.infixOrderList();
    }

}
