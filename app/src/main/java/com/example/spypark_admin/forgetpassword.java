package com.example.spypark_admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class forgetpassword extends AppCompatActivity {

    TextInputEditText editTextEmail;
    Button submit;
    private FirebaseAuth mAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.forgot_email);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(view -> validateData());
    }

    private void validateData() {
        email = Objects.requireNonNull(editTextEmail.getText()).toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(forgetpassword.this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else{
            forgetpassword();
            }
    }

    private void forgetpassword() {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(forgetpassword.this, "Please Check your Email", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(forgetpassword.this, "Unable to proceed with your request", Toast.LENGTH_SHORT).show();
            }
        });
    }
}