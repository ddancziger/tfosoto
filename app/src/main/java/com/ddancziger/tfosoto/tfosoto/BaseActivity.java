package com.ddancziger.tfosoto.tfosoto;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

/**
 * Created by DanielDancziger on 3/26/2016.
 */
public class BaseActivity extends AppCompatActivity{

    private android.support.v7.widget.Toolbar mToolbar;
    protected static final int MY_PERMISSIONS_REQUEST_READ_SMS = 100;

    protected Toolbar activateToolbar(){
        if(mToolbar == null){
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if(mToolbar != null){
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    protected Toolbar activateToolbarWithHomeEnabled(){
        activateToolbar();
        if(mToolbar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return mToolbar;
    }

    protected void showOrHideUserMenu(Menu menu){

        if(ParseInterface.isLoggedIn()){
            menu.findItem(R.id.menu_navegation_log_out).setVisible(true);
            menu.findItem(R.id.menu_navegation_my_claims).setVisible(true);
        }else{
            menu.findItem(R.id.menu_navegation_log_out).setVisible(false);
            menu.findItem(R.id.menu_navegation_my_claims).setVisible(false);
        }


    }

    protected void menuOptionChoose(MenuItem item){

        switch (item.getItemId()){
            case R.id.menu_navegation_log_out:
                ParseUser.getCurrentUser().logOut();
                Log.d("ParseInterface", "Current User: "+ParseUser.getCurrentUser());
                break;

        }
        invalidateOptionsMenu();
    }

    protected void checkSMSPermission(AppCompatActivity thisActivity){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Manifest.permission.READ_SMS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(thisActivity,
                        new String[]{Manifest.permission.READ_SMS},
                        MY_PERMISSIONS_REQUEST_READ_SMS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}
