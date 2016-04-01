package com.ddancziger.tfosoto.tfosoto;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.concurrent.Future;

interface CallbackResponse{
    public void returnResult(boolean response);
}

/**
 * Created by DanielDancziger on 3/30/2016.
 */
public class ParseInterface {


    public static void logIn(final Context context, String email, String password, final CallbackResponse callback){

        ParseUser.logInInBackground(email, password, new LogInCallback() {

            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    callback.returnResult(true);
                } else {
                    callback.returnResult(false);
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void signUp(final Context context, String email, String password, String name,
                              String lastName, String phone, String address, final CallbackResponse callback){

        ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);
        user.put("name", name);
        user.put("lastname", lastName);
        user.put("address", address);
        user.put("phone", phone);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    callback.returnResult(true);
                } else {
                    callback.returnResult(false);
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public static boolean isLoggedIn(){

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null){
            return true;
        }
        return false;
    }

    public static String getEmailCurrentUser(){

        if(isLoggedIn()){
            return ParseUser.getCurrentUser().getEmail();
        }
        return null;
    }




}
