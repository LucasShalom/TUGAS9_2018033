package com.example.pertemuan4_actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mengganti actionbar dengan toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //memanggil drawer_layout dari activity_main.xml
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //membuat hamburger icon pada toolbar dan animasinya
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //membuat default navigation menu select
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PaymentFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_view);}
    }

    //drawer menu fragment handler
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_API) {
            Intent a = new Intent(MainActivity.this,RESTAPI .class);
            startActivity(a);
        }
        else if  (id == R.id.nav_database) {
        Intent a = new Intent(MainActivity.this,HotelMain.class);
        startActivity(a);
        return true;
    }
        switch (item.getItemId()) {
            case R.id.nav_notif:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotifFragment()).commit();
                break;
            case R.id.nav_payment:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PaymentFragment()).commit();
                break;
            case R.id.nav_collections:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CollectionsFragments()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingFragment()).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HelpFragment()).commit();
                break;
            case R.id.nav_TOC:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TOCFragment()).commit();
                break;
            case R.id.nav_database:

            case R.id.nav_API:

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //on back press behavior
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}