package com.mobappdev.pricewatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    EditText email, password;
    Button submit;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        submit = findViewById(R.id.reg_submit_btn);
        signIn = findViewById(R.id.sign_in_link);
        auth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailStr = String.valueOf(email.getText());
                String passwordStr = String.valueOf(password.getText());

                auth.createUserWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    Toast.makeText(MainActivity.this, "Account created successfully!", Toast.LENGTH_LONG).show();

                                    user = auth.getCurrentUser();
                                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {

                                    Toast.makeText(MainActivity.this, "Account creation failed.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}