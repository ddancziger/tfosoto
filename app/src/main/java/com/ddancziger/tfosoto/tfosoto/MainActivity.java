package com.ddancziger.tfosoto.tfosoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private AppCompatButton mSmsViewButton;
    private TextView mLogIn;
    private NavigationView mNavegationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateToolbar();

        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuOptionChoose(menuItem);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        mSmsViewButton = (AppCompatButton) findViewById(R.id.smsThreadsView);
        mSmsViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_LONG).show();
//                ParseObject testObject = new ParseObject("Claims");
//                testObject.put("name", "jose");
////                try {
////                    testObject.save();
////                } catch (ParseException e) {
////                    e.printStackTrace();
////                }
//                testObject.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        Toast.makeText(getApplicationContext(), "ENTER", Toast.LENGTH_LONG).show();
//                        if (e == null) {
//                            Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//                Toast.makeText(getApplicationContext(), "FINAL", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

//        ParseObject testObject = new ParseObject("Claims");
//        testObject.put("name", "jose");
//        try {
//            testObject.save();
//        } catch (ParseException e) {
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//        testObject.saveEventually(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                Toast.makeText(getApplicationContext(), "ENTER", Toast.LENGTH_LONG).show();
//                if (e == null) {
//                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        mLogIn = (TextView) findViewById(R.id.txtLogIn);

        if (ParseInterface.isLoggedIn()) {
            mLogIn.setText("Im Logged In");
        } else {
            mLogIn.setText("Im NOT Logged In");
        }

        mNavegationMenu = (NavigationView) findViewById(R.id.nav_view);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        showOrHideUserMenu(mNavegationMenu.getMenu());
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
