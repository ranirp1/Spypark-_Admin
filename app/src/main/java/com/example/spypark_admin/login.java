package com.example.spypark_admin;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class login extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button buttonLogin;
    TextView forgotPassword,register;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail= findViewById(R.id.login_email);
        editTextPassword = findViewById(R.id.login_password);
        buttonLogin = findViewById(R.id.login_button);
        register= findViewById(R.id.navigate_to_signUp);
        forgotPassword= findViewById(R.id.forgotButton);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.login_progress);

        forgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), forgetpassword.class);
            startActivity(intent);
        });

        buttonLogin.setOnClickListener(view -> {

        });

        register.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), register.class);
            startActivity(intent);
            finish();
        });
        buttonLogin.setOnClickListener(view -> {
            String email, password;

            email= Objects.requireNonNull(editTextEmail.getText()).toString();
            password= Objects.requireNonNull(editTextPassword.getText()).toString();
            progressBar.setVisibility(View.VISIBLE);

            if(TextUtils.isEmpty(email)){
                Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(login.this, "Authentication Success.",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    });
        });



    }
}