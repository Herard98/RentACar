package com.nelson.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnlogin = findViewById(R.id.btnlogin);
        user=findViewById(R.id.inputEmail);
        password=findViewById(R.id.inputPassword);
        TextView textsign = findViewById(R.id.textViewSignUp);
        TextView textcount = findViewById(R.id.textcount);
        textsign.setOnClickListener(v -> {
            startActivity(new Intent(login.this, Register.class));
            finish();
        });

        textcount.setOnClickListener(v -> {
            startActivity(new Intent(login.this, Media.class));
            finish();
        });
        btnlogin.setOnClickListener(view -> Login());
    }
    public void Login(){
        if(user.getText().toString().equalsIgnoreCase("admin")&&password.getText().toString().equals("pass")){
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }

        else {
            Toast.makeText(login.this,"Echec de connexion, user ou password est incorrect",Toast.LENGTH_LONG).show();
            user.setText("");
            password.setText("");
        }
    }
    }
