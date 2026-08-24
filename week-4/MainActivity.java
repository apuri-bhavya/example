package com.example.week2;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
public class MainActivity extends AppCompatActivity {
    EditText editTextNumber1, editTextNumber2;
    Button Add, Subtract, Multiply, Divide;
    TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        Add = findViewById(R.id.btnAdd);
        Subtract = findViewById(R.id.btnSubtract);
        Multiply = findViewById(R.id.btnMultiply);
        Divide = findViewById(R.id.btnDivide);
        textViewResult = findViewById(R.id.textViewResult);
        Add.setOnClickListener(v -> calculate('+'));
        Subtract.setOnClickListener(v -> calculate('-'));
        Multiply.setOnClickListener(v -> calculate('*'));
        Divide.setOnClickListener(v -> calculate('/'));
    }      @SuppressLint("SetTextI18n")
    private void calculate(char operator) {
        String num1Str = editTextNumber1.getText().toString();
        String num2Str = editTextNumber2.getText().toString();


        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            textViewResult.setText("Please enter both numbers");
            return;
        }
        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double result = 0;
        switch (operator) {
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
                if (num2 == 0) {
                    textViewResult.setText("Cannot divide by zero");
                    return;
                }
                result = num1 / num2;
                break;
        }
        textViewResult.setText("Result: " + result);
    }
}