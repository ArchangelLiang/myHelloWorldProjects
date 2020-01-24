package com.datastructure_arithmetic.datastructure.linkedList;

public class JosephuCircleLinkedList {

    private Child first;

    public void constructChildLinkedList(int nums){
        Child temp = null;
        for (int i = 1; i <= nums; i++) {
            Child child = new Child(i);
            if (i == 1){
                first = child;
                first.next = first;
                temp = first;
            } else {
                temp.next = child;
                child.next = first;
                temp = child;
            }
        }
    }

    public void showChildList(){
        Child temp = first;
        while (true){
            System.out.println("当前小孩的编号为："+temp.num);
            if (temp.next == first){
                break;
            } else {
                temp = temp.next;
            }
        }
    }

    public void toMuckOut(int startNum,int val,int childCount){
        constructChildLinkedList(childCount);
        if (first == null || first.next == null || startNum > childCount){
            System.out.println("输入参数有误");
            return;
        }
        Child start;
        Child before;
        Child temp = first;
        while (true){
            if (temp.next.num == startNum){
                before = temp;
                start = temp.next;
                break;
            }
            temp = temp.next;
        }
        int num = 0;
        while (true) {
            num++;
            if (before == start){
                break;
            }
            if (num == val){
                System.out.println(start.num+"号小孩出圈---");
                start = start.next;
                before.next = start;
                num = 1;
            }
            start = start.next;
            before = before.next;
        }
        System.out.println("最后一位编号为"+start.num+"的小孩出圈---");

    }

   private static class Child {
        private int num;
        private Child next;

        public Child(int num){
            this.num = num;
        }
    }

    public static void main(String[] args) {
        JosephuCircleLinkedList list = new JosephuCircleLinkedList();
        list.toMuckOut(1,2,5);
    }

}
