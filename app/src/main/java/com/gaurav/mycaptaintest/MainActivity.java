package com.gaurav.mycaptaintest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstNumber, secondNumber;
    Button add, subtract, multiply, division;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumber = findViewById(R.id.first_number);
        secondNumber = findViewById(R.id.second_number);
        add = findViewById(R.id.add);
        subtract = findViewById(R.id.subtract);
        multiply = findViewById(R.id.multiply);
        division = findViewById(R.id.division);
        answer = findViewById(R.id.answer);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTheCalculation("+");
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTheCalculation("-");
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTheCalculation("*");
            }
        });
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTheCalculation("/");
            }
        });

    }

    private void doTheCalculation(String operation) {

        String first = firstNumber.getText().toString().trim();
        String second = secondNumber.getText().toString().trim();

        if(first.isEmpty() || second.isEmpty()) {
            showToast("Invalid Inputs");
        } else {

            try {

                int firstNumber = Integer.parseInt(first);
                int secondNumber = Integer.parseInt(second);

                int ans = 0;
                switch (operation) {
                    case "+" : {
                        ans = firstNumber + secondNumber;
                        break;
                    }
                    case "-" : {
                        ans = firstNumber - secondNumber;
                        break;
                    }
                    case "*" : {
                        ans = firstNumber * secondNumber;
                        break;
                    }
                    default: {
                        ans = firstNumber / secondNumber;
                        break;
                    }
                }

                String Answer = String.valueOf(ans);

                answer.setText(Answer);

            } catch (Exception e) {
                showToast("Invalid Inputs");
                Log.e("Conversion error", e.getMessage());
            }

        }

    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

}
