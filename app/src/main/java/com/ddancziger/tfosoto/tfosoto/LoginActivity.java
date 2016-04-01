package com.ddancziger.tfosoto.tfosoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton btnLogin;
    private EditText txtUsername;
    private EditText txtPassword;
    private TextView btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        txtPassword = (EditText) findViewById(R.id.input_password);
        txtUsername = (EditText) findViewById(R.id.input_email);

        btnLogin = (AppCompatButton) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseInterface.logIn(getApplicationContext(), txtUsername.getText().toString(), txtPassword.getText().toString(), new CallbackResponse() {
                    @Override
                    public void returnResult(boolean response) {
                        Log.d("ParseInterface", "T: " + response);
                        if (response) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }

                    }
                });
            }
        });

        btnSignUp = (TextView) findViewById(R.id.link_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}
