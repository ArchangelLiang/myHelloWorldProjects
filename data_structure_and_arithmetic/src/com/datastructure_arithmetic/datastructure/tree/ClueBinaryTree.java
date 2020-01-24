package com.datastructure_arithmetic.datastructure.tree;

public class ClueBinaryTree {

    private int no;
    private String name;
    private ClueBinaryTree left;
    private ClueBinaryTree right;
    private int leftType;
    private int rightType;
    private ClueBinaryTree preNode;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public ClueBinaryTree getPreNode() {
        return preNode;
    }

    public void setPreNode(ClueBinaryTree preNode) {
        this.preNode = preNode;
    }

    public ClueBinaryTree(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClueBinaryTree getLeft() {
        return left;
    }

    public void setLeft(ClueBinaryTree left) {
        this.left = left;
    }

    public ClueBinaryTree getRight() {
        return right;
    }

    public void setRight(ClueBinaryTree right) {
        this.right = right;
    }

    /**
     * 前序线索化节点
     * @param node 要线索化的节点
     */
    public void preClueNode(ClueBinaryTree node) {
        if (node == null){
            return;
        }
        currentNodeClue(node);
        if (node.leftType == 0){
            preClueNode(node.left);
        }
        if (node.rightType == 0){
            preClueNode(node.right);
        }
    }

    /**
     * 中序线索化节点
     * @param node 要线索化的节点
     */
    public void infixClueNode(ClueBinaryTree node) {
        if (node == null) {
            return;
        }
        infixClueNode(node.left);
        currentNodeClue(node);
        infixClueNode(node.right);
    }

    /**
     * 后序线索化节点
     * @param node 要线索化的节点
     */
    public void postClueNode(ClueBinaryTree node){
        if (node == null){
            return;
        }
        postClueNode(node.left);
        postClueNode(node.right);
        currentNodeClue(node);
    }

    private void currentNodeClue(ClueBinaryTree node) {
        if (node.left == null) {
            node.left = preNode;
            node.leftType = 1;
        }
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rightType = 1;
        }
        preNode = node;
    }

    public boolean del(int no) {
        if (this.no == no) {
            System.out.println("当前节点即是要删除的节点");
            return false;
        }
        if (this.left != null) {
            if (this.left.getNo() == no) {
                this.left = null;
                return true;
            }
            if (this.left.del(no)) {
                return true;
            }
        }
        if (this.right != null) {
            if (this.right.getNo() == no) {
                this.right = null;
                return true;
            }
            return this.right.del(no);
        }
        return false;
    }

    /**
     * 循环法前序遍历线索化二叉树
     */
    public void preOrderListOfClueBinaryTree(){
        ClueBinaryTree tempNode = this;
        while (tempNode != null){
            System.out.println(tempNode.no);
            while (tempNode.leftType != 1){
                tempNode = tempNode.left;
                System.out.println(tempNode.no);
            }
            while (tempNode.rightType == 1){
                tempNode = tempNode.right;
                System.out.println(tempNode.no);
            }
            tempNode = tempNode.right;
        }
    }

    /**
     * 递归方式中序遍历线索化二叉树
     */
    public void infixOrderListOfClueBinaryTree() {
        if (this.leftType == 0) {
            this.left.infixOrderListOfClueBinaryTree();
        }
        System.out.println(this.no);
        if (this.right != null && this.rightType == 0) {
            this.right.infixOrderListOfClueBinaryTree();
        }
    }

    /**
     * 循环法中序遍历线索化二叉树
     */
    public void infixOrderListOfClueBinaryTree2() {
        ClueBinaryTree tempNode = this;
        while (tempNode != null){
            while (tempNode.leftType == 0){
                tempNode = tempNode.left;
            }
            System.out.println(tempNode.no);
            while (tempNode.rightType == 1){
                tempNode = tempNode.right;
                System.out.println(tempNode.no);
            }
            tempNode = tempNode.right;
        }
    }

    /**
     * 中序遍历[在线索化二叉树下不可用]
     */
    public void infixOrderList() {
        if (this.left != null) {
            this.left.infixOrderList();
        }
        System.out.println(this.no + "号节点的名字为：" + this.name);
        if (this.right != null) {
            this.right.infixOrderList();
        }
    }

    public static void main(String[] args) {
        ClueBinaryTree root = new ClueBinaryTree(1, "tom");
        ClueBinaryTree node2 = new ClueBinaryTree(3, "jack");
        ClueBinaryTree node3 = new ClueBinaryTree(6, "smith");
        ClueBinaryTree node4 = new ClueBinaryTree(8, "mary");
        ClueBinaryTree node5 = new ClueBinaryTree(10, "king");
        ClueBinaryTree node6 = new ClueBinaryTree(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
//        root.infixClueNode(root);
//        root.preClueNode(root);
        root.postClueNode(root);
        System.out.println(node5.getLeft().no+"==="+node5.getRight().no);
//        root.infixOrderListOfClueBinaryTree();
//        root.infixOrderListOfClueBinaryTree2();
//        root.preOrderListOfClueBinaryTree();
    }
}
