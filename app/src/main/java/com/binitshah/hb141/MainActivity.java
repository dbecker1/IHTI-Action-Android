package com.binitshah.hb141;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open_string, R.string.navigation_drawer_close_string);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_id);
        navigationView.setNavigationItemSelectedListener(this);


        boolean loggedIn = true; //todo modify by checking the SharedPreference for whether the user has been logged in or maybe Firebase will provide it.
        String returningFrom = "nothing"; //todo modify by checking the Intent for an extraString value
        if(!loggedIn){ //check if the person is logged in
            //todo send the user through the onboarding/login process
        }
        else if(returningFrom.equals("prevreports")){
            //set the fragment to Previous Reports
            toolbar.setTitle(getResources().getString(R.string.nav_prevreports_string));
            navigationView.setCheckedItem(R.id.nav_prevreports_id);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame_id, new PreviousReportsFragment());
            ft.commit();
        }
        else if(returningFrom.equals("settings")){
            //set the fragment to Settings
            toolbar.setTitle(getResources().getString(R.string.nav_settings_string));
            navigationView.setCheckedItem(R.id.nav_settings_id);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame_id, new SettingsFragment());
            ft.commit();
        }
        else {
            //default is to set the fragment to Maps Fragment
            toolbar.setTitle(getResources().getString(R.string.nav_chooselocation_string));
            navigationView.setCheckedItem(R.id.nav_chooselocation_id);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame_id, new MapsFragment());
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_id) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_chooselocation_id) {
            toolbar.setTitle(getResources().getString(R.string.nav_chooselocation_string));
            fragment = new MapsFragment();
        } else if (id == R.id.nav_prevreports_id) {
            toolbar.setTitle(getResources().getString(R.string.nav_prevreports_string));
            fragment = new PreviousReportsFragment();
        } else if (id == R.id.nav_moreinfo_id) {
            toolbar.setTitle(getResources().getString(R.string.nav_moreinfo_string));
            fragment = new InfoFragment();
        } else if (id == R.id.nav_settings_id) {
            toolbar.setTitle(getResources().getString(R.string.nav_settings_string));
            fragment = new SettingsFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame_id, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
