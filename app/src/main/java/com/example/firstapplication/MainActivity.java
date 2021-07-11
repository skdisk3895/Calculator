package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView tv_display, tv_result;
    private Button btn_zero, btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine, btn_add, btn_sub, btn_mult, btn_div, btn_res, btn_clear, btn_delete, btn_bracket, btn_sign;
    private String display = "";
    private String currentNumber = new String();
    private Stack<String> bracket = new Stack<String>();

    public int setOpPriority(char op) {
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

    public double calc(double a, double b, String op) {
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

    public ArrayList<String> infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<Character>();
        ArrayList<String> postfix = new ArrayList<String>();
        String operand = "";
        System.out.println(infix);

        // infix --> postfix
        for (int i = 0; i < infix.length(); i++) {
            char element = infix.charAt(i);
            if (Character.isDigit(element))
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
        while (!stack.isEmpty())
            postfix.add(stack.pop().toString());

        System.out.println("postfix : " + postfix);
        return postfix;
    }

    public String calcInfix(String exp) {
        ArrayList<String> postfix = infixToPostfix(exp);
        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < postfix.size(); i++) {
            String element = postfix.get(i);
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

    public void insertNumberInDisplay(String number) {
        display += number;
        currentNumber += number;
    }

    public void checkZero() {
        if (currentNumber.equals("0")) {
            currentNumber = "";
            display = "";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_display = findViewById(R.id.tv_display);
        tv_result = findViewById(R.id.tv_result);
        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mult = findViewById(R.id.btn_mult);
        btn_div = findViewById(R.id.btn_div);
        btn_res = findViewById(R.id.btn_res);
        btn_delete = findViewById(R.id.btn_delete);
        btn_clear = findViewById(R.id.btn_clear);
        btn_bracket = findViewById(R.id.btn_bracket);
        btn_sign = findViewById(R.id.btn_sign);
        btn_zero = findViewById(R.id.btn_zero);
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "+";
                tv_display.setText(display);
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "-";
                tv_display.setText(display);
            }
        });

        btn_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "×";
                tv_display.setText(display);
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "÷";
                tv_display.setText(display);
            }
        });

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exp = tv_display.getText().toString();
                String result = "";
                char lastCharacter = exp.charAt(exp.length()-1);
                if (Character.isDigit(lastCharacter) || lastCharacter == ')') {
                    result = calcInfix(exp);
                    System.out.println(result);
                    tv_result.setText(result);
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentString = tv_display.getText().toString();
                if (currentString.length() == 0) return;
                String deletedString = currentString.substring(0, currentString.length()-1);
                display = display.substring(0, display.length()-1);
                tv_display.setText(deletedString);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display = "";
                currentNumber = "";
                tv_display.setText(display);
                tv_result.setText("");
            }
        });

        btn_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.length() == 0) {
                    display += "(";
                    tv_display.setText(display);
                    return;
                }
                char lastStr = display.charAt(display.length()-1);
                if (Character.isDigit(lastStr)) {
                    display += ")";
                    tv_display.setText(display);
                }
                else if (lastStr == '+' || lastStr == '-' || lastStr == '×' || lastStr == '÷') {
                    display += "(";
                    tv_display.setText(display);
                }
                else if (lastStr == '(') {
                    display += "(";
                    tv_display.setText(display);
                }
                else if (lastStr == ')'){
                    Stack<Character> stack = new Stack<Character>();
                    for (int i = 0; i < display.length(); i++) {
                        char element = display.charAt(i);
                        if (element != '(' && element != ')') {
                            continue;
                        }
//                        System.out.println("test" + element);
                        if (element == '(') {
                            stack.push('(');
                        }
                        else {
                            stack.pop();
                        }
                    }
                    if (stack.isEmpty())
                        display += "×(";
                    else
                        display += ")";
                    tv_display.setText(display);

                    while (!stack.isEmpty())
                        stack.pop();
                }
                else {
                    display += "(";
                    tv_display.setText(display);
                }
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentNumber.equals("0")) return;
                insertNumberInDisplay("0");
                tv_display.setText(display);
            }
        });

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("1");
                tv_display.setText(display);
            }
        });

        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("2");
                tv_display.setText(display);
            }
        });

        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("3");
                tv_display.setText(display);
            }
        });

        btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("4");
                tv_display.setText(display);
            }
        });

        btn_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("5");
                tv_display.setText(display);
            }
        });

        btn_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("6");
                tv_display.setText(display);
            }
        });

        btn_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("7");
                tv_display.setText(display);
            }
        });

        btn_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("8");
                tv_display.setText(display);
            }
        });

        btn_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkZero();
                insertNumberInDisplay("9");
                tv_display.setText(display);
            }
        });
    }
}