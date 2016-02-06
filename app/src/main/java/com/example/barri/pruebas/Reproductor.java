package com.example.barri.pruebas;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;

import DataBase.CustomDB;

public class Reproductor extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    private MediaPlayer mp;
    private Button b_play_pause, b_stop;

    private String user;

    private CustomDB cdb;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout_reproductor;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        toolbar = (Toolbar) findViewById(R.id.toolbar_reproductor);
        setSupportActionBar(toolbar);

        user = getIntent().getStringExtra("user");

        b_play_pause = (Button) findViewById(R.id.button7);
        b_play_pause.setBackgroundResource(R.drawable.play);
        b_stop = (Button) findViewById(R.id.button9);
        b_stop.setBackgroundResource(R.drawable.stop);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.fiesta_pagana);

        b_play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.pause();
                    b_play_pause.setBackgroundResource(R.drawable.play);
                }
                else {
                    mp.start();
                    b_play_pause.setBackgroundResource(R.drawable.pausa);
                }
            }
        });

        b_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(getApplicationContext(), R.raw.fiesta_pagana);
                b_play_pause.setBackgroundResource(R.drawable.play);
            }
        });


        //NAVIGATION DRAWER
        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navview);

        //Initializing DrawerLayout
        drawerLayout_reproductor = (DrawerLayout) findViewById(R.id.drawer_layout_reproductor);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout_reproductor.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.memory:
                        Intent i2 = new Intent(getApplicationContext(), MemorySwipe.class);
                        i2.putExtra("user", user);
                        startActivity(i2);
                        finish();
                        break;

                    case R.id.ranking:
                        Intent i3 = new Intent(getApplicationContext(), MemorySwipe.class);
                        i3.putExtra("user", user);
                        startActivity(i3);
                        finish();
                        break;

                    case R.id.reproductor:
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, "Ya estas en el reproductor");
                        cdb.close();
                        Toast.makeText(Reproductor.this, "Ya estas en el reproductor", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.perfil:
                        Intent i = new Intent(getApplicationContext(), Perfil.class);
                        i.putExtra("user", user);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.calculadora:
                        Intent i1 = new Intent(getApplicationContext(), Calculadora.class);
                        i1.putExtra("user", user);
                        startActivity(i1);
                        finish();
                        break;

                    case R.id.log_out:
                        Toast.makeText(Reproductor.this, "Log Out Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout_reproductor, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout_reproductor.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mp.stop();
        mp.release();
        finish();
    }
}