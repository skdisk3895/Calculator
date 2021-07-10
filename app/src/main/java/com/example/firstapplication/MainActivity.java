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
    private Button btn_zero, btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine, btn_add, btn_sub, btn_mult, btn_div, btn_res, btn_clear, btn_delete;
    private String display = "";

    public int setOpPriority(char op) {
        switch(op) {
            case '*':
            case '/':
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

    public int calc(int a, int b, String op) {
        switch(op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }

        System.out.println("Error!!");
        return -1;
    }

    public ArrayList<String> infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<Character>();
        ArrayList<String> postfix = new ArrayList<String>();
        String tmp = "";
        System.out.println(infix);

        // infix --> postfix
        for (int i = 0; i < infix.length(); i++) {
            if (infix.charAt(i) != '+' && infix.charAt(i) != '-' && infix.charAt(i) != '*' && infix.charAt(i) != '/') {
                tmp += infix.charAt(i);
            }
            else {
                postfix.add(tmp);
                tmp = "";
                if (!stack.isEmpty() && setOpPriority(stack.peek()) > setOpPriority(infix.charAt(i))) {
                    postfix.add(stack.pop().toString());
                    stack.push(infix.charAt(i));
                }
                else {
                    stack.push(infix.charAt(i));
                }
            }
        }

        postfix.add(tmp);
        while (!stack.isEmpty())
            postfix.add(stack.pop().toString());

        return postfix;
    }

    public String calcInfix(String exp) {
        ArrayList<String> postfix = infixToPostfix(exp);
        Stack<String> stack = new Stack<String>();
        System.out.println(postfix);

        for (int i = 0; i < postfix.size(); i++) {
            String element = postfix.get(i);
            if (!element.equals("+") && !element.equals("-") && !element.equals("*") && !element.equals("/")) {
                System.out.println("1." + element);
                stack.push(element);
            }
            else {
                System.out.println("2." + element);
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                stack.push(Integer.toString(calc(a, b, element)));
            }
        }
        System.out.println(stack.peek());

        return stack.pop();
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
                display += "*";
                tv_display.setText(display);
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "/";
                tv_display.setText(display);
            }
        });

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exp = tv_display.getText().toString();
                String result = "";
                char lastCharacter = exp.charAt(exp.length()-1);
                if (Character.isDigit(lastCharacter)) {
                    result = calcInfix(exp);
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
                tv_display.setText(display);
            }
        });

        btn_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "0";
                tv_display.setText(display);
            }
        });

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "1";
                tv_display.setText(display);
            }
        });

        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "2";
                tv_display.setText(display);
            }
        });

        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "3";
                tv_display.setText(display);
            }
        });

        btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "4";
                tv_display.setText(display);
            }
        });

        btn_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "5";
                tv_display.setText(display);
            }
        });

        btn_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "6";
                tv_display.setText(display);
            }
        });

        btn_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "7";
                tv_display.setText(display);
            }
        });

        btn_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "8";
                tv_display.setText(display);
            }
        });

        btn_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display += "9";
                tv_display.setText(display);
            }
        });
    }
}