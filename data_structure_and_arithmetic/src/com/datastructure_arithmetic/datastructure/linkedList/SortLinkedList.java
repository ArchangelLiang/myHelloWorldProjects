package com.datastructure_arithmetic.datastructure.linkedList;

public class SortLinkedList {

    private Node first;
    private Node last;

    private static class Node {
        private int id;
        private String data;
        private Node pre;
        private Node next;

        public Node(int id, String data) {
            this.id = id;
            this.data = data;
        }

        @Override
        public String toString() {
            return "\n"+"Node{" +
                    "id=" + id +
                    ", data='" + data + '\'' +
                    ", next=" + next +
                    '}';
        }
    }

    public void add(int id, String data) {
        Node node = new Node(id, data);
        if (first == null) {
            first = node;
            last = node;
            return;
        }
        if (first.id > node.id){
            node.next = first;
            first.pre = node;
            first = node;
            return;
        } else if (first.id == node.id){
            node.next = first.next;
            node.pre = first;
            first.next = node;
            return;
        }
        Node temp = first;
        while (true) {
            if (temp.next == null) {
                if (temp.id > node.id) {
                    first = node;
                    node.next = temp;
                    temp.pre = node;
                    last = temp;
                } else {
                    temp.next = node;
                    node.pre = temp;
                    last = node;
                }
                break;
            } else if (temp.next.id > node.id) {
                node.next = temp.next;
                temp.next.pre = node;
                temp.next = node;
                node.pre = temp;
                break;
            } else if (temp.next.id == node.id) {
                node.next = temp.next.next;
                temp.next.next.pre = node;
                temp.next.next = node;
                node.pre = temp.next;
                break;
            }
            temp = temp.next;
        }
    }

    public void reverse(){
        if (first == null || first.next == null){
            System.out.println("当前无法反转");
            return;
        }
        Node temp = first;
        Node next = null;
        Node reverseNode = new Node(1,"");
        while (temp != null){
            next = temp.next;
            temp.next = reverseNode.next;
            reverseNode.next = temp;
            temp = next;
        }
        first = reverseNode.next;
    }

    public static SortLinkedList integration(SortLinkedList source,SortLinkedList target){
        Node temp = source.first;
        while (temp != null){
            target.add(temp.id,temp.data);
            temp = temp.next;
        }
        return target;
    }

    public void del(String data){
        Node temp = first;
        while (temp != null){
            if (temp.data.equals(data)){
                if (temp.pre!=null){
                    temp.pre.next = temp.next;
                } else {
                    first = null;
                }
                if (temp.next != null){
                    temp.next.pre = temp.pre;
                }
            }
            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        return "SortLinkedList{" +
                "first=" + first +
                '}';
    }

    public static void main(String[] args) {

        SortLinkedList list = new SortLinkedList();
        list.add(3,"zio");
        list.add(1,"decade");
        list.add(4,"kabuto");
        System.out.println(list);
        list.del("kabuto");
        System.out.println(list);
      /*  SortLinkedList list2 = new SortLinkedList();
        for (int i = 0; i < 10; i++) {
            list2.add(i*3,"xxx");
        }
        SortLinkedList integration = integration(list, list2);
        System.out.println(integration);*/
    }
}
