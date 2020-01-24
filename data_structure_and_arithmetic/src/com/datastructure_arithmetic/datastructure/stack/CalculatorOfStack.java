package com.datastructure_arithmetic.datastructure.stack;

import java.util.Stack;

public class CalculatorOfStack {

    public static boolean isMark(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
    }

    public static int getPriority(char mark) {
        if (mark == '*' || mark == '/') {
            return 1;
        }
        return 0;
    }

    public static int calculate(int num1, int num2, char mark) {
        int result = 0;
        switch (mark) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        return result;
    }

    public static void main(String[] args) {

        String expression = "(20+3*4)*6-8";
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        char ch;
        StringBuilder nu = new StringBuilder();
        LinkedListStack numStack = new LinkedListStack(expression.length());
        LinkedListStack markStack = new LinkedListStack(expression.length());
        while (index < expression.length()) {
            ch = expression.charAt(index);
            if (isMark(ch)) {
                if (!markStack.isEmpty()) {
                    if (markStack.peekStackTop() == ')') {
                        char op;
                        while ((op = (char) markStack.pop()) != '(') {
                            if (op != ')') {
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                numStack.push(calculate(num2,num1,op));
                            }
                        }
                    }
                    if (!markStack.isEmpty() && markStack.peekStackTop() != '(' && ch != ')' && getPriority(ch) <= getPriority((char) markStack.peekStackTop())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        int result = calculate(num2, num1, (char) markStack.pop());
                        numStack.push(result);
                    }
                }
                markStack.push(ch);
            } else {
                nu.append(ch - '0');
                if (index == expression.length() - 1 || isMark(expression.charAt(index + 1))) {
                    numStack.push(Integer.parseInt(nu.toString()));
                    nu.delete(0, nu.length());
                }
            }
            index++;
        }
        while (!markStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            int result = calculate(num2, num1, (char) markStack.pop());
            numStack.push(result);
        }
        System.out.println(expression + "=" + numStack.pop());
    }
}
