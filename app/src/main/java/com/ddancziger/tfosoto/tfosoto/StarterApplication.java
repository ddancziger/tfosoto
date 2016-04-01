package com.ddancziger.tfosoto.tfosoto;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by DanielDancziger on 3/27/2016.
 */
public class StarterApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("x6an8Q5avC2s8Dg7U3BBBeRepJJlySxUdZpBuddT")
                .server("http://parseserver-gewm5-env.us-east-1.elasticbeanstalk.com/parse/")
                .build() );

        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
