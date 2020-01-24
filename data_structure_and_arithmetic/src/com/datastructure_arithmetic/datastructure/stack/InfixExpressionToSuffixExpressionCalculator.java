package com.datastructure_arithmetic.datastructure.stack;

import jdk.nashorn.internal.ir.IfNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class InfixExpressionToSuffixExpressionCalculator {

    //  1+3+(2*3-5+7)-5  ==>  1 3 + 2 3 * 5 - 7 + + 5 -
    private static String toSuffixExpression(String infixExpression) {
        List<String> infixExps = strToList(infixExpression);
        Stack<String> operators = new Stack<>();
        StringBuilder suffixExpression = new StringBuilder();
        for (String str : infixExps) {
            if (isOperator(str.charAt(0))) {
                if (str.equals(")")) {
                    while (operators.size() > 0 && !operators.peek().equals("(")) {
                        suffixExpression.append(operators.pop());
                        suffixExpression.append(" ");
                    }
                    operators.pop();
                } else {
                    if (!operators.isEmpty()) {
                        if (!str.equals("(") && !operators.peek().equals("(") && getPriority(str) <= getPriority(operators.peek())) {
                            suffixExpression.append(operators.pop());
                            suffixExpression.append(" ");
                        }
                    }
                    operators.add(str);
                }
            } else {
                suffixExpression.append(str);
                suffixExpression.append(" ");
            }
        }
        suffixExpression.append(operators.pop());
        return suffixExpression.toString().trim();
    }

    private static int getPriority(String str) {
        return str.equals("*") || str.equals("/") ? 1 : 0;
    }

    private static List<String> strToList(String infixExpression) {
        if (infixExpression.contains(" ")) {
            return Arrays.asList(infixExpression.split(" "));
        }
        ArrayList<String> list = new ArrayList<>();
        int index = 0;
        char ch;
        StringBuilder nums = new StringBuilder();
        while (index < infixExpression.length()) {
            ch = infixExpression.charAt(index);
            if (isOperator(ch)) {
                list.add(String.valueOf(ch));
            } else {
                nums.append(ch - '0');
                if (index == infixExpression.length() - 1 || isOperator(infixExpression.charAt(index + 1))) {
                    list.add(nums.toString());
                    nums.delete(0, nums.length());
                }
            }
            index++;
        }
        return list;
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
    }

    private static int suffixCalculator(String suffixExpression) {
        List<String> list = strToList(suffixExpression);
        Stack<Integer> numStack = new Stack<>();
        int num1;
        int num2;
        for (String s : list) {
            if (isOperator(s.charAt(0))) {
                num2 = numStack.pop();
                num1 = numStack.pop();
                switch (s) {
                    case "+":
                        numStack.add(num1 + num2);
                        break;
                    case "-":
                        numStack.add(num1 - num2);
                        break;
                    case "*":
                        numStack.add(num1 * num2);
                        break;
                    case "/":
                        numStack.add(num1 / num2);
                        break;
                }
            } else {
                numStack.add(Integer.parseInt(s));
            }
        }
        return numStack.pop();
    }

    public static void main(String[] args) {
        String infixExpression = "11+3+(2*3-5+7)-5";
        String suffixExpression = toSuffixExpression(infixExpression);
        System.out.println(suffixExpression);
        int result = suffixCalculator(suffixExpression);
        System.out.println(result);
    }

    @Test
    public void toList() {
        String expression = "11 3 + 2 3 * 5 - 7 + + 5 -";
        List<String> list = Arrays.asList(expression.split(" "));
        System.out.println(list);
    }

}
