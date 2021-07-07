package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tv_display, tv_result;
    private Button btn_zero, btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine, btn_add, btn_sub, btn_mult, btn_div, btn_res, btn_clear;
    private String display = "";

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

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = tv_display.getText().toString();
                char lastCharacter = result.charAt(result.length()-1);
                // System.out.println(lastCharacter);
                if (Character.isDigit(lastCharacter)) {
                    System.out.println(result);
                }
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