package com.example.firstapplication;

import java.util.ArrayList;
import java.util.Stack;

public class Calc {

    public static int setOpPriority(char op) {
        switch(op) {
            case '×':
            case '÷':
                return 3;
            case '+':
            case '-':
                return 2;
            case '(':
                return 1;
            default:
                return -1;
        }
    }

    public static double calc(double a, double b, String op) {
        switch(op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "×":
                return a * b;
            case "÷":
                return a / b;
        }

        System.out.println("Error!!");
        return -1;
    }

    public static ArrayList<String> infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<Character>();
        ArrayList<String> postfix = new ArrayList<String>();
        String operand = "";
        System.out.println(infix);

        // infix --> postfix 변환
        for (int i = 0; i < infix.length(); i++) {
            char element = infix.charAt(i);
            if (Character.isDigit(element) || element == '.' || element == '−')
                operand += element;
            else {
                if (operand.length() > 0) {
                    postfix.add(operand);
                    operand = "";
                }
                switch (element) {
                    case '(':
                        stack.push(element);
                        break;
                    case ')':
                        while (true) {
                            String pop = stack.pop().toString();
                            if (pop.equals("("))
                                break;
                            postfix.add(pop);
                        }
                        break;
                    case '+':
                    case '-':
                    case '×':
                    case '÷':
                        while (!stack.isEmpty() && setOpPriority(stack.peek()) > setOpPriority(element))
                            postfix.add(stack.pop().toString());
                        stack.push(element);
                        break;
                }
            }
        }

        if (operand.length() > 0)
            postfix.add(operand);
        while (!stack.isEmpty()) {
            char pop = stack.pop();
            if (pop == '(')
                continue;
            postfix.add(Character.toString(pop));
        }

        return postfix;
    }

    public static String calcInfix(String exp) {
        ArrayList<String> postfix = infixToPostfix(exp);
        Stack<String> stack = new Stack<String>();

        System.out.println(postfix);

        for (int i = 0; i < postfix.size(); i++) {
            String element = postfix.get(i);
            if (element.contains("−")) {
                element = element.replace("−", "-");
            }
            if (!element.equals("+") && !element.equals("-") && !element.equals("×") && !element.equals("÷"))
                stack.push(element);
            else {
                double a = Double.parseDouble(stack.pop());
                double b = Double.parseDouble(stack.pop());
                double result = calc(b, a, element);
                if (result == (int)result) {
                    stack.push(Integer.toString((int)result));
                }
                else {
                    stack.push(Double.toString(result));
                }
            }
        }

        return stack.pop();
    }
}
