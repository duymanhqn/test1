package com.example.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private EditText edEmailSignup, edPasswordSignup, edRePassword;
    private Button btnSignup;
    private TextView txtToLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edEmailSignup = findViewById(R.id.edESignup);
        edPasswordSignup = findViewById(R.id.edPSignup);
        edRePassword = findViewById(R.id.edRePass);
        btnSignup = findViewById(R.id.btnSignup);
        txtToLogin = findViewById(R.id.ToLogin);
        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(view -> {
            String email = edEmailSignup.getText().toString();
            String pass = edPasswordSignup.getText().toString();
            String repass = edRePassword.getText().toString();

            if (email.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(repass)) {
                Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                        } else {
                            Toast.makeText(this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        txtToLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}
