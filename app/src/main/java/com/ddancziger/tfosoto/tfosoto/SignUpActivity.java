package com.ddancziger.tfosoto.tfosoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mName;
    private EditText mLastName;
    private EditText mPassword;
    private EditText mPhone;
    private EditText mAddress;
    private AppCompatButton mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initialize();

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    private void initialize(){

        mEmail = (EditText) findViewById(R.id.input_email);
        mName = (EditText) findViewById(R.id.input_name);
        mLastName = (EditText) findViewById(R.id.input_lastName);
        mPassword = (EditText) findViewById(R.id.input_password);
        mPhone = (EditText) findViewById(R.id.input_phone);
        mAddress = (EditText) findViewById(R.id.input_address);
        mSignUp = (AppCompatButton) findViewById(R.id.btn_signup);

    }

    private void signUp(){

        String email = mEmail.getText().toString();
        String password  = mPassword.getText().toString();
        String name = !mName.getText().toString().isEmpty() ? mName.getText().toString() : "";
        String lastName = !mLastName.getText().toString().isEmpty() ? mLastName.getText().toString() : "";
        String phone = !mPhone.getText().toString().isEmpty() ? mPhone.getText().toString() : "";
        String address = !mAddress.getText().toString().isEmpty() ? mAddress.getText().toString() : "";

        ParseInterface.signUp(getApplicationContext(), email, password, name, lastName, phone, address, new CallbackResponse() {
            @Override
            public void returnResult(boolean response) {
                if(response){
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

}
