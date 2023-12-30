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

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText email, password;
    Button submit;
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        submit = findViewById(R.id.login_submit_btn);
        signUp = findViewById(R.id.sign_up_link);
        auth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailStr = String.valueOf(email.getText());
                String passwordStr = String.valueOf(password.getText());

                auth.signInWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_LONG).show();

                                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {

                                    Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}