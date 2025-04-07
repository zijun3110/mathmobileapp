package com.example.mathmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class CompareNumbersActivity extends AppCompatActivity {

    private int num1, num2;
    private Button btnNum1, btnNum2;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_compare_numbers);

        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        tvResult = findViewById(R.id.tvResult);
        Button btnNewCompare = findViewById(R.id.btnNewCompare);
        Button btnBackHome = findViewById(R.id.btnBackHome);

        generateNewNumbers();

        btnNum1.setOnClickListener(v -> checkAnswer(num1, num2));
        btnNum2.setOnClickListener(v -> checkAnswer(num2, num1));

        btnNewCompare.setOnClickListener(v -> generateNewNumbers());

        btnBackHome.setOnClickListener(v -> {
            startActivity(new Intent(CompareNumbersActivity.this, MainActivity.class));
            finish();
        });
    }

    private void generateNewNumbers() {
        Random random = new Random();
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);

        // Ensure numbers are different
        while (num1 == num2) {
            num2 = random.nextInt(100);
        }

        btnNum1.setText(String.valueOf(num1));
        btnNum2.setText(String.valueOf(num2));
        tvResult.setText("");
    }

    private void checkAnswer(int selectedNum, int otherNum) {
        if (selectedNum > otherNum) {
            tvResult.setText("Correct! " + selectedNum + " is greater than " + otherNum);
        } else {
            tvResult.setText("Try again! " + selectedNum + " is less than " + otherNum);
        }
    }
}