package com.example.firstapplication;

import com.example.firstapplication.Calc.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    private TextView tv_display, tv_result;
    private Button btn_zero, btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine, btn_add, btn_sub, btn_mult, btn_div, btn_res, btn_clear, btn_delete, btn_bracket, btn_sign, btn_dot;
    private String display = "";
    private String currentNumber = new String();
    private boolean isDot = false;

    public void insertNumberInDisplay(String number) {
        if (display.length() > 0) {
            char lastChr = display.charAt(display.length() - 1);
            if (lastChr == ')')
                display += "×";
        }
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
        btn_dot = findViewById(R.id.btn_dot);
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
                if (display.length() == 0) return;
                char lastChr = display.charAt(display.length()-1);
                if (!Character.isDigit(lastChr) && lastChr != ')') return;
                display += "+";
                currentNumber = "";
                tv_display.setText(display);
                isDot = false;
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.length() == 0) return;
                char lastChr = display.charAt(display.length()-1);
                if (!Character.isDigit(lastChr) && lastChr != ')') return;
                display += "-";
                currentNumber = "";
                tv_display.setText(display);
                isDot = false;
            }
        });

        btn_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.length() == 0) return;
                char lastChr = display.charAt(display.length()-1);
                if (!Character.isDigit(lastChr) && lastChr != ')') return;
                display += "×";
                currentNumber = "";
                tv_display.setText(display);
                isDot = false;
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.length() == 0) return;
                char lastChr = display.charAt(display.length()-1);
                if (!Character.isDigit(lastChr) && lastChr != ')') return;
                display += "÷";
                currentNumber = "";
                tv_display.setText(display);
                isDot = false;
            }
        });

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.length() == 0) return;
                String exp = tv_display.getText().toString();
                String result = "";
                char lastCharacter = exp.charAt(exp.length()-1);
                if (Character.isDigit(lastCharacter) || lastCharacter == ')') {
                    try {
                        result = Calc.calcInfix(exp);
                        System.out.println("result : " + result);
                        tv_result.setText(result);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "잘못된 수식입니다.", Toast.LENGTH_LONG).show();
                    }
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
                isDot = false;
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
                if (Character.isDigit(lastStr) || lastStr == ')') {
                    Stack<Character> stack = new Stack<Character>();
                    for (int i = 0; i < display.length(); i++) {
                        char element = display.charAt(i);
                        if (element != '(' && element != ')')
                            continue;
                        if (element == '(')
                            stack.push('(');
                        else
                            stack.pop();
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
                currentNumber = "";
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDot) return;
                if (display.length() == 0) {
                    display += "0.";
                    currentNumber += "0.";
                }
                else {
                    char lastStr = display.charAt(display.length()-1);
                    if (!Character.isDigit(lastStr)) {
                        display += "0.";
                        currentNumber += "0.";
                    }
                    else {
                        display += ".";
                        currentNumber += ".";
                    }
                }
                isDot = true;
                tv_display.setText(display);
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean findSign = false;
                for (int i = display.length() - 1; i >= 0; i--) {
                    if (display.charAt(i) == '+' || display.charAt(i) == '-' || display.charAt(i) == '×' || display.charAt(i) == '÷' || display.charAt(i) == '(')
                        break;
                    if (display.charAt(i) == '−') {
                        findSign = true;
                        break;
                    }
                }

                if (findSign) {
                    int signIndex = display.lastIndexOf("(−");
                    display = display.substring(0, signIndex) + "" + display.substring(signIndex + 2, display.length());
                }
                else {
                    if (display.length() == 0 || display.length() == 1) {
                        display = "(−" + display;
                        tv_display.setText(display);
                        return;
                    }

                    char lastChr = display.charAt(display.length()-1);
                    if (!Character.isDigit(lastChr)) {
                        display += "(−";
                    }
                    else {
                        int idx = 0;
                        for (int i = display.length() - 1; i >= 0; i--) {
                            if (!Character.isDigit(display.charAt(i))) {
                                idx = i + 1;
                                break;
                            }
                        }
                        display = display.substring(0, idx) + "(−" + display.substring(idx, display.length());
                    }
                }
                tv_display.setText(display);
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