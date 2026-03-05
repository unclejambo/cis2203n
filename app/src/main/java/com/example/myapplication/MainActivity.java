package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    TextView myText;
    Button myButton;

    private static final String COUNT_KEY = "COUNT_KEY";
    TextView counterText;
    Button btnPlus;
    int mCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("ID: 21102775");
        }

        myText = findViewById(R.id.tvDepartment);
        myButton = findViewById(R.id.btnChangeName);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myText.setText("Dr. Angie");
            }
        });

        counterText = findViewById(R.id.counterText);
        btnPlus = findViewById(R.id.btnPlus);

        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt(COUNT_KEY, 0);
        }
        counterText.setText(String.valueOf(mCounter));

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;
                counterText.setText(String.valueOf(mCounter));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT_KEY, mCounter);
    }
}