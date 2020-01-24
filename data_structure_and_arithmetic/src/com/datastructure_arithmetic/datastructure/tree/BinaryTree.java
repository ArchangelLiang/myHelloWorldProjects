package com.datastructure_arithmetic.datastructure.tree;

public class BinaryTree {

    private int no;
    private String name;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int no, String name) {
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

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public boolean del(int no) {
        if (this.no == no) {
            System.out.println("当前节点即是要删除的节点");
            return false;
        }
        if (this.left != null) {
            if (this.left.no == no) {
                this.left = null;
                return true;
            }
            if (this.left.del(no)) {
                return true;
            }
        }
        if (this.right != null) {
            if (this.right.no == no) {
                this.right = null;
                return true;
            }
            return this.right.del(no);
        }
        return false;
    }

    /**
     * 前序查找
     *
     * @param no 要查找的节点编号
     * @return 查找目标编号对应的name属性
     */
    public String preSearch(int no) {
        if (this.no == no) {
            return this.name;
        }
        if (this.left != null) {
            String result = this.left.preSearch(no);
            if (result != null) {
                return result;
            }
        }
        if (this.right != null) {
            return this.right.preSearch(no);
        }
        return null;
    }

    /**
     * 前序遍历
     */
    public void preOrderList() {
        System.out.println(this.no + "号节点的名字为：" + this.name);
        if (this.left != null) {
            this.left.preOrderList();
        }
        if (this.right != null) {
            this.right.preOrderList();
        }
    }

    /**
     * 中序遍历
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

    /**
     * 后序遍历
     */
    public void postOrderList() {
        if (this.left != null) {
            this.left.postOrderList();
        }
        if (this.right != null) {
            this.right.postOrderList();
        }
        System.out.println(this.no + "号节点的名字为：" + this.name);
    }

    public static void main(String[] args) {
        BinaryTree rootTree = new BinaryTree(1, "a");
        BinaryTree b = new BinaryTree(2, "b");
        BinaryTree c = new BinaryTree(3, "c");
        BinaryTree d = new BinaryTree(4, "d");
        BinaryTree e = new BinaryTree(5, "e");
        rootTree.setLeft(b);
        b.setLeft(c);
        b.setRight(d);
        rootTree.setRight(e);
//        rootTree.preOrderList();
//        rootTree.infixOrderList();
//        rootTree.postOrderList();
//        String result = rootTree.preSearch(5);
//        System.out.println(result == null ? "未找到匹配的结果！" : "找到目标，姓名=" + result);
        System.out.println("删除前打印");
        rootTree.preOrderList();
        boolean bool = rootTree.del(2);
        System.out.println(bool ? "删除成功" : "删除失败");
        System.out.println("删除后打印");
        rootTree.preOrderList();
    }

}
