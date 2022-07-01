package com.nelson.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {
Button btnregiste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView btn=findViewById(R.id.alreadyHaveAccount);
        TextView bt=findViewById(R.id.alreadyHaveAccount);
        btnregiste=findViewById(R.id.btnRegister);
        btnregiste.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Register.this,login.class));
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,login.class));
                finish();
            }
        });
    }
}