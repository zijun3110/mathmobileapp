package com.example.mathmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderNumbersActivity extends AppCompatActivity {

    private List<Integer> originalNumbers = new ArrayList<>();
    private List<Integer> orderedNumbers = new ArrayList<>();
    private LinearLayout originalContainer, orderedContainer;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_numbers);

        originalContainer = findViewById(R.id.originalNumbersContainer);
        orderedContainer = findViewById(R.id.orderedNumbersContainer);
        tvResult = findViewById(R.id.tvResult);

        Button btn1 = findViewById(R.id.btnSelect1);
        Button btn2 = findViewById(R.id.btnSelect2);
        Button btn3 = findViewById(R.id.btnSelect3);
        Button btn4 = findViewById(R.id.btnSelect4);

        Button btnAscending = findViewById(R.id.btnAscending);
        Button btnDescending = findViewById(R.id.btnDescending);
        Button btnNewNumbers = findViewById(R.id.btnNewNumbers);
        Button btnBackHome = findViewById(R.id.btnBackHome);

        generateNewNumbers();

        // Number selection buttons
        btn1.setOnClickListener(v -> selectNumber(0));
        btn2.setOnClickListener(v -> selectNumber(1));
        btn3.setOnClickListener(v -> selectNumber(2));
        btn4.setOnClickListener(v -> selectNumber(3));

        // Check order buttons
        btnAscending.setOnClickListener(v -> checkOrder(true));
        btnDescending.setOnClickListener(v -> checkOrder(false));

        btnNewNumbers.setOnClickListener(v -> {
            orderedNumbers.clear();
            generateNewNumbers();
        });

        btnBackHome.setOnClickListener(v -> {
            startActivity(new Intent(OrderNumbersActivity.this, MainActivity.class));
            finish();
        });
    }

    private void generateNewNumbers() {
        originalNumbers.clear();
        orderedNumbers.clear();
        originalContainer.removeAllViews();
        orderedContainer.removeAllViews();
        tvResult.setText("");

        Random random = new Random();
        while (originalNumbers.size() < 4) {
            int num = 10 + random.nextInt(90); // Numbers 10-99
            if (!originalNumbers.contains(num)) {
                originalNumbers.add(num);
            }
        }

        // Display original numbers
        for (int num : originalNumbers) {
            Button numButton = new Button(this);
            numButton.setText(String.valueOf(num));
            numButton.setWidth(dpToPx(80));
            numButton.setHeight(dpToPx(80));
            originalContainer.addView(numButton);
        }
    }

    private void selectNumber(int position) {
        if (orderedNumbers.size() >= 4) return;

        int selectedNum = originalNumbers.get(position);
        orderedNumbers.add(selectedNum);

        // Display in ordered container
        Button numButton = new Button(this);
        numButton.setText(String.valueOf(selectedNum));
        numButton.setWidth(dpToPx(80));
        numButton.setHeight(dpToPx(80));
        orderedContainer.addView(numButton);
    }

    private void checkOrder(boolean ascending) {
        if (orderedNumbers.size() != 4) {
            tvResult.setText("Please select all 4 numbers first!");
            return;
        }

        boolean isCorrect = true;
        for (int i = 0; i < 3; i++) {
            if (ascending && orderedNumbers.get(i) > orderedNumbers.get(i + 1)) {
                isCorrect = false;
                break;
            } else if (!ascending && orderedNumbers.get(i) < orderedNumbers.get(i + 1)) {
                isCorrect = false;
                break;
            }
        }

        if (isCorrect) {
            tvResult.setText("Correct! " + (ascending ? "Ascending" : "Descending") + " order!");
        } else {
            tvResult.setText("Not correct. Try again!");
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
