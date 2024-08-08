package com.example.spypark_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class d1 extends AppCompatActivity {
    Button buttonSubmit;
    TextInputEditText textName, textBikeCapacity, textCarCapacity, textAddress;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d1);
        buttonSubmit = findViewById(R.id.submit);
        textName = findViewById(R.id.Name);
        textCarCapacity = findViewById(R.id.car_capacity);
        textBikeCapacity = findViewById(R.id.bike_capacity);
        textAddress = findViewById(R.id.address);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = textName.getText().toString();
                String bike = textBikeCapacity.getText().toString();
                String car = textCarCapacity.getText().toString();
                String address = textAddress.getText().toString();


                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",name);
                hashMap.put("bike",bike);
                hashMap.put("car",car);
                databaseReference.child("Parkings")
                        .child(name)
                        .setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>(){
                            @Override
                            public void onSuccess(Void aVoid){
                            }
                        });
                Intent intent = new Intent(d1.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}