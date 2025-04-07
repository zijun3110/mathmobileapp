package com.example.mathmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class ComposeNumbersActivity extends AppCompatActivity {

    private int targetNumber;
    private TextView tvTargetNumber, tvComposeResult;
    private EditText etNum1, etNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_numbers);

        tvTargetNumber = findViewById(R.id.tvTargetNumber);
        tvComposeResult = findViewById(R.id.tvComposeResult);
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        Button btnCheckCompose = findViewById(R.id.btnCheckCompose);
        Button btnNewCompose = findViewById(R.id.btnNewCompose);
        Button btnBackHome = findViewById(R.id.btnBackHome);

        generateNewTarget();

        btnCheckCompose.setOnClickListener(v -> checkComposition());
        btnNewCompose.setOnClickListener(v -> generateNewTarget());

        btnBackHome.setOnClickListener(v -> {
            startActivity(new Intent(ComposeNumbersActivity.this, MainActivity.class));
            finish();
        });
    }

    private void generateNewTarget() {
        Random random = new Random();
        targetNumber = 5 + random.nextInt(46); // Numbers between 5 and 50
        tvTargetNumber.setText(String.valueOf(targetNumber));
        etNum1.setText("");
        etNum2.setText("");
        tvComposeResult.setText("");
    }

    private void checkComposition() {
        try {
            int num1 = Integer.parseInt(etNum1.getText().toString());
            int num2 = Integer.parseInt(etNum2.getText().toString());

            if (num1 + num2 == targetNumber) {
                tvComposeResult.setText("Great job! " + num1 + " + " + num2 + " = " + targetNumber);
            } else {
                tvComposeResult.setText("Oops! " + num1 + " + " + num2 + " = " + (num1 + num2) + ", not " + targetNumber);
            }
        } catch (NumberFormatException e) {
            tvComposeResult.setText("Please enter two numbers");
        }
    }
}
