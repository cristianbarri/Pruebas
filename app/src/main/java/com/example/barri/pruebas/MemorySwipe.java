package com.example.barri.pruebas;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import DataBase.CustomDB;
import TabSwipe.TabsPagerAdapter;

public class MemorySwipe extends FragmentActivity {

    TabLayout mTabLayout;
    ViewPager pager;

    private String user;
    private int fragment;

    private CustomDB cdb;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout_mem;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_swipe);


        toolbar = (Toolbar) findViewById(R.id.toolbar_mem);
        toolbar.setTitle("Memory");
        //setSupportActionBar(toolbar);

        user = getIntent().getStringExtra("user");
        fragment = getIntent().getIntExtra("fragment", 0);

        pager = (ViewPager) findViewById(R.id.pagerMemorySwipe);
        pager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager(), user));
        pager.setCurrentItem(fragment);

        mTabLayout = (TabLayout) findViewById(R.id.tabsMemorySwipe);
        mTabLayout.setupWithViewPager(pager);


        //NAVIGATION DRAWER

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navview);

        //Initializing DrawerLayout
        drawerLayout_mem = (DrawerLayout) findViewById(R.id.drawer_layout_mem);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout_mem.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.memory:
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, "Ya estas en memory o ranking");
                        cdb.close();

                        Toast.makeText(MemorySwipe.this, "Ya estas en memory o ranking", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.ranking:
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, "Ya estas en memory o ranking");
                        cdb.close();
                        Toast.makeText(MemorySwipe.this, "Ya estas en memory o ranking", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.reproductor:
                        Intent i1 = new Intent(getApplicationContext(), Reproductor.class);
                        i1.putExtra("user", user);
                        startActivity(i1);
                        finish();
                        break;

                    case R.id.perfil:
                        Intent i = new Intent(getApplicationContext(), Perfil.class);
                        i.putExtra("user", user);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.calculadora:
                        Intent i2 = new Intent(getApplicationContext(), Calculadora.class);
                        i2.putExtra("user", user);
                        startActivity(i2);
                        finish();
                        break;

                    case R.id.log_out:
                        finish();
                        break;
                }
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout_mem, toolbar, R.string.openDrawer, R.string.closeDrawer){

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
        drawerLayout_mem.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();


    }

}
