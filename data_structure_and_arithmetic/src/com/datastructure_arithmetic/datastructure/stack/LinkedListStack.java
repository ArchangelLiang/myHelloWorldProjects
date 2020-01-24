package com.datastructure_arithmetic.datastructure.stack;

public class LinkedListStack {

    private int maxSize;
    private Node stackBottom;
    private Node stackTop;
    private int size;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public int peekStackTop(){
        return stackTop.data;
    }

    public boolean isEmpty() {
        return stackBottom == null;
    }

    public void push(int data) {
        if (size == maxSize) {
            System.out.println("当前栈已满");
            return;
        }
        Node node = new Node(data);
        if (stackBottom == null) {
            stackBottom = node;
        } else {
            stackTop.next = node;
        }
        stackTop = node;
        size++;
    }

    public int pop() {
        if (stackBottom == null) {
            return -1;
        }
        size--;
        if (stackBottom == stackTop) {
            stackTop = null;
            int data = stackBottom.data;
            stackBottom = null;
            return data;
        }
        Node temp = stackBottom;
        while (true) {
            if (temp.next == stackTop) {
                stackTop = temp;
                break;
            }
            temp = temp.next;
        }
        return stackTop.next.data;
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
/*        LinkedListStack stack = new LinkedListStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());*/
    }

}
