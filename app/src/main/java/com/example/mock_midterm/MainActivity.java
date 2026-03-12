package com.example.mock_midterm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentInput = "";
    private Double firstNumber = null;
    private String operator = null;
    private boolean isNewInput = false;
    private static final String KEY_CURRENT_INPUT = "currentInput";
    private static final String KEY_FIRST_NUMBER = "firstNumber";
    private static final String KEY_OPERATOR = "operator";
    private static final String KEY_IS_NEW_INPUT = "isNewInput";
    private static final String KEY_DISPLAY_TEXT = "displayText";
    private static final String KEY_HAS_FIRST_NUMBER = "hasFirstNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDisplay = findViewById(R.id.tvDisplay);

        if (savedInstanceState != null) {
            currentInput = savedInstanceState.getString(KEY_CURRENT_INPUT, "");
            operator = savedInstanceState.getString(KEY_OPERATOR, null);
            isNewInput = savedInstanceState.getBoolean(KEY_IS_NEW_INPUT, false);

            boolean hasFirstNumber = savedInstanceState.getBoolean(KEY_HAS_FIRST_NUMBER, false);
            if (hasFirstNumber) {
                firstNumber = savedInstanceState.getDouble(KEY_FIRST_NUMBER);
            } else {
                firstNumber = null;
            }

            String displayText = savedInstanceState.getString(KEY_DISPLAY_TEXT, "0");
            tvDisplay.setText(displayText);
        }

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);

        Button btnDot = findViewById(R.id.btnDot);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnBack = findViewById(R.id.btnBack);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnEquals = findViewById(R.id.btnEquals);

        Button btnCustom = findViewById(R.id.btnCustom);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;

                if (isNewInput) {
                    currentInput = "";
                    isNewInput = false;
                }

                currentInput += clickedButton.getText().toString();
                tvDisplay.setText(currentInput);
            }
        };

        btn0.setOnClickListener(numberListener);
        btn1.setOnClickListener(numberListener);
        btn2.setOnClickListener(numberListener);
        btn3.setOnClickListener(numberListener);
        btn4.setOnClickListener(numberListener);
        btn5.setOnClickListener(numberListener);
        btn6.setOnClickListener(numberListener);
        btn7.setOnClickListener(numberListener);
        btn8.setOnClickListener(numberListener);
        btn9.setOnClickListener(numberListener);

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewInput) {
                    currentInput = "";
                    isNewInput = false;
                }

                if (!currentInput.contains(".")) {
                    if (currentInput.isEmpty()) {
                        currentInput = "0.";
                    } else {
                        currentInput += ".";
                    }
                    tvDisplay.setText(currentInput);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                firstNumber = null;
                operator = null;
                isNewInput = false;
                tvDisplay.setText("0");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty() && !isNewInput) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);

                    if (currentInput.isEmpty()) {
                        tvDisplay.setText("0");
                    } else {
                        tvDisplay.setText(currentInput);
                    }
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("+");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("*");
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperator("/");
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyCustomOperator();
            }
        });
    }

    private void setOperator(String selectedOperator) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operator = selectedOperator;
            isNewInput = true;
        }
    }

    private void calculateResult() {
        if (firstNumber != null && operator != null && !currentInput.isEmpty() && !isNewInput) {
            double secondNumber = Double.parseDouble(currentInput);
            double result;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        tvDisplay.setText("Cannot divide by zero");
                        currentInput = "";
                        firstNumber = null;
                        operator = null;
                        isNewInput = true;
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
                default:
                    return;
            }

            String formattedResult;
            if (result == (long) result) {
                formattedResult = String.valueOf((long) result);
            } else {
                formattedResult = String.valueOf(result);
            }

            tvDisplay.setText(formattedResult);
            currentInput = formattedResult;
            firstNumber = null;
            operator = null;
            isNewInput = true;
        }
    }

    private void applyCustomOperator() {
        if (currentInput.isEmpty()) {
            return;
        }

        double value = Double.parseDouble(currentInput);

        int lastThreeDigits = 775;
        double factor = lastThreeDigits / 100.0;

        double result = value * factor;

        String formattedResult;
        if (result == (long) result) {
            formattedResult = String.valueOf((long) result);
        } else {
            formattedResult = String.valueOf(result);
        }

        tvDisplay.setText(formattedResult);
        currentInput = formattedResult;
        firstNumber = null;
        operator = null;
        isNewInput = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_CURRENT_INPUT, currentInput);
        outState.putString(KEY_OPERATOR, operator);
        outState.putBoolean(KEY_IS_NEW_INPUT, isNewInput);
        outState.putString(KEY_DISPLAY_TEXT, tvDisplay.getText().toString());

        if (firstNumber != null) {
            outState.putBoolean(KEY_HAS_FIRST_NUMBER, true);
            outState.putDouble(KEY_FIRST_NUMBER, firstNumber);
        } else {
            outState.putBoolean(KEY_HAS_FIRST_NUMBER, false);
        }
    }
}