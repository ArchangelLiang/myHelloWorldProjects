package com.datastructure_arithmetic.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {

    public static class Node implements Comparable<Node>{
        private int value;
        private Node left;
        private Node right;

        public Node(int value){
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

        public void preOrderList(){
            System.out.println(this.value);
            if (this.left != null){
                this.left.preOrderList();
            }
            if (this.right != null){
                this.right.preOrderList();
            }
        }
    }

    public Node createHuffmanTree(int[] arr){
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        Collections.sort(nodes);
       while (nodes.size() > 1){
           Node left = nodes.get(0);
           Node right = nodes.get(1);

           Node parent = new Node(left.value + right.value);
           parent.left = left;
           parent.right = right;
           nodes.remove(left);
           nodes.remove(right);
           nodes.add(parent);
           Collections.sort(nodes);
       }
        return nodes.get(0);
    }

    public void preOrderList(Node root){
        if (root != null){
            root.preOrderList();
        }
    }

    public static void main(String[] args) {

        int[] arr = {13,7,8,3,29,6,1};
        HuffmanTree tree = new HuffmanTree();
        Node huffmanTree = tree.createHuffmanTree(arr);
        tree.preOrderList(huffmanTree);
    }

}
