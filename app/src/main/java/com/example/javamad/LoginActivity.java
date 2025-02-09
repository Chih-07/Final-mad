package com.example.javamad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // Declare the views
    private EditText etEmail, etPassword;
    private MaterialButton btnLogin, btnRegister;
    private FirebaseAuth mAuth;  // Firebase Auth instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);  // Ensure this corresponds to the correct login layout

        // Initialize the views from the layout
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Set an onClickListener for the Login button
        btnLogin.setOnClickListener(v -> handleLogin());

        // Set an onClickListener for the Register button
        btnRegister.setOnClickListener(v -> {
            // Navigate to RegisterActivity when clicked
            Intent intent = new Intent(LoginActivity.this, Register.class);
            startActivity(intent);
        });
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate email and password
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login successful, get the current user
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        // Navigate to the HomeActivity after successful login
                        Intent intent = new Intent(LoginActivity.this, home.class);  // Replace HomeActivity.class with your home activity
                        startActivity(intent);
                        finish();  // Optionally finish the LoginActivity so it doesn't remain in the back stack
                    } else {
                        // Authentication failed
                        Toast.makeText(LoginActivity.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
}
}