package com.route.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView result;
    String rhs = "";
    String lhs = "";
    String operator = "";
    Boolean isThereAPoint = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.resultTV);
    }

    public void onDigitClick(View view) {
        Button button = (Button) view;
        button.setOnClickListener(v -> {
            if (!rhs.isEmpty()) {
                clear();
            }
            result.append(button.getTag().toString());

        });
    }

    public void onPointClick(View view) {
        Button button = (Button) view;
        button.setOnClickListener(v -> {

            for (int i = 0; i < result.getText().length(); i++) {
                isThereAPoint = result.getText().charAt(i) == '.';
            }

            if (!isThereAPoint) {
                if (!rhs.isEmpty()) {
                    clear();
                }
                isThereAPoint = true;
                result.append(button.getTag().toString());
            }

        });
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        button.setOnClickListener(v -> {
            if (result.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Number first", Toast.LENGTH_SHORT).show();
            } else if (lhs.isEmpty()) {
                lhs = result.getText().toString();
                result.setText("");
                operator = button.getTag().toString();
            } else if (rhs.isEmpty()) {
                rhs = result.getText().toString();
                operator = button.getTag().toString();
                result.setText(calculate(lhs, rhs, operator));
            } else {
                rhs = "";
                lhs = result.getText().toString();
                operator = button.getTag().toString();
                result.setText("");
            }
        });
    }

    //on equal bottom click
    public void onEqualClick(View view) {
        Button button = (Button) view;
        button.setOnClickListener(v -> {
            if (!operator.isEmpty() && !result.getText().toString().isEmpty()) {
                rhs = result.getText().toString();
                result.setText(calculate(lhs, rhs, operator));
                lhs = "";
                rhs = "";
                operator = "";
            }
        });
    }

    //on square bottom click
    public void onExponentClick(View view) {
        Button button = (Button) view;
        button.setOnClickListener(v -> {
            lhs = result.getText().toString();
            result.setText(calculate(lhs, lhs, "*"));
            lhs = "";
        });
    }

    //on remove bottom click
    public void onRemoveClick(View view) {
        Button button = (Button) view;
        button.setOnClickListener(v -> {
            if (result.getText().length() != 0)
                result.setText(result.getText().subSequence(0, result.getText().length() - 1));
        });
        button.setOnLongClickListener(v -> {
            clear();
            return false;
        });
    }

    //clear all variables
    public void onClearClick(View view) {
        Button button = (Button) view;
        button.setOnClickListener(v -> {
            clear();
        });
    }

    // Calculate the numbers
    public String calculate(String lhs, String rhs, String operator) {

        if (operator != "") {

            Double firstNumber = Double.valueOf(lhs);
            Double secondNumber = Double.valueOf(rhs);
            isThereAPoint = true;
            switch (operator) {
                case "+":
                    return String.valueOf((firstNumber + secondNumber));

                case "-":
                    return String.valueOf((firstNumber - secondNumber));


                case "*":
                    return String.valueOf((firstNumber * secondNumber));

                case "/":
                    return String.valueOf((firstNumber / secondNumber));

                default:
                    return "";
            }
        }
        return "";
    }

    public void clear() {
        result.setText("");
        lhs = "";
        rhs = "";
        operator = "";
    }

}