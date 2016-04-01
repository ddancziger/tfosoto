package com.ddancziger.tfosoto.tfosoto;

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
}
