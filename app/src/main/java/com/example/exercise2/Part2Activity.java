package com.example.exercise2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.exercise2.databinding.ActivityPart2Binding;

public class Part2Activity extends AppCompatActivity {

    private ActivityPart2Binding binding;
    private final String wallColor = "blue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPart2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(v -> validateLogin());
    }

    private void validateLogin() {
        String studentId = binding.etStudentId.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (studentId.isEmpty() || password.isEmpty()) {
            binding.tvResult.setText("Please enter both Student ID and Password.");
            return;
        }

        if (studentId.length() < 2) {
            binding.tvResult.setText("Student ID must contain at least 2 digits.");
            return;
        }

        String lastTwoDigits = studentId.substring(studentId.length() - 2);
        String expectedPassword = wallColor + lastTwoDigits;

        if (password.equals(expectedPassword)) {
            binding.tvResult.setText("Access Granted");
        } else {
            binding.tvResult.setText("Access Denied");
        }
    }
}