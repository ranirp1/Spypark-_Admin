package com.example.spypark_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class d2 extends AppCompatActivity {
    Button buttonSubmit;
    TextInputEditText textACCNO, textgst, textexperience;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d2);
        buttonSubmit = findViewById(R.id.submit);
        textACCNO = findViewById(R.id.Bank);
        textgst = findViewById(R.id.GST);
        textexperience = findViewById(R.id.experience);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accno = textACCNO.getText().toString();
                String gst = textgst.getText().toString();
                String exp = textexperience.getText().toString();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("accountno", accno);
                hashMap.put("exp", exp);
                hashMap.put("gst", gst);
                databaseReference.child("Bank")
                        .child(accno)
                        .setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(d2.this, "Account details submitted",Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent = new Intent(d2.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }
}