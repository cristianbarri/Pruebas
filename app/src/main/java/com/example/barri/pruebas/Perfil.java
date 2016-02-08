package com.example.barri.pruebas;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.List;

import DataBase.CustomDB;

public class Perfil extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout_perfil;
    private Toolbar toolbar;
    private ImageView iv;
    private Uri selectedImage = Uri.parse("a");

    private TextView tv_nombre, tv_notificacion, tv_puntuacion, tv_ubicacion;

    private CustomDB cdb;
    private String user;

    List<Address> l;
    LocationManager lm;
    LocationListener lis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        toolbar = (Toolbar) findViewById(R.id.toolbar_perfil);
        setSupportActionBar(toolbar);

        user = getIntent().getStringExtra("user");

        tv_nombre = (TextView) findViewById(R.id.tv_nombre);
        tv_nombre.setText(user);

        cdb = new CustomDB(getApplicationContext());
        tv_notificacion = (TextView) findViewById(R.id.tv_notificacion);
        tv_notificacion.setText(cdb.getNotificacion(user));

        tv_puntuacion = (TextView) findViewById(R.id.tv_puntuacion);
        if (cdb.getPuntuacion(user) == 999) tv_puntuacion.setText("No has acabado ninguna partida todavia");
        else tv_puntuacion.setText(String.valueOf(cdb.getPuntuacion(user)));
        cdb.close();

        tv_ubicacion = (TextView) findViewById(R.id.tv_ubicacion);
        tv_ubicacion.setText("Buscando ubicacion...");


        iv = (ImageView) findViewById(R.id.imageView);

        if (selectedImage == Uri.parse("a")) iv.setBackgroundResource(R.drawable.ic_launcher);
        //else iv.setImageBitmap(); CAMBIAR IMAGEN CON LA URI

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getImageAsContent = new Intent(Intent.ACTION_GET_CONTENT, null);
                getImageAsContent.setType("image/*");
                startActivityForResult(getImageAsContent, 1);
            }
        });


//@@@@@@ NAVIGATION DRAWER

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navview);

        //Initializing DrawerLayout
        drawerLayout_perfil = (DrawerLayout) findViewById(R.id.drawer_layout_perfil);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout_perfil.closeDrawers();

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
                        Intent i1 = new Intent(getApplicationContext(), Reproductor.class);
                        i1.putExtra("user", user);
                        startActivity(i1);
                        finish();
                        break;

                    case R.id.perfil:
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, "Ya estas en tu perfil");
                        cdb.close();
                        tv_notificacion.setText("Ya estas en tu perfil");
                        Toast.makeText(Perfil.this, "Ya estas en tu perfil", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.calculadora:
                        Intent i = new Intent(getApplicationContext(), Calculadora.class);
                        i.putExtra("user", user);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.log_out:
                        Toast.makeText(Perfil.this, "Log Out Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout_perfil, toolbar, R.string.openDrawer, R.string.closeDrawer) {

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
        drawerLayout_perfil.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();


        //Localizacion
        l = null;
        lm = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        lis = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                Geocoder gc = new Geocoder(getApplicationContext());
                try {
                    l = gc.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //
                tv_ubicacion.setText(l.get(0).getAddressLine(0));
                /*
                for (int i = 0; i < l.size(); ++i) {
                    if (i == 0) tv_ubicacion.setText("");
                    tv_ubicacion.setText(l.get(i).getAddressLine(0).toString());
                }
                */
            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, lis);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lis);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                data.getData();
                selectedImage = data.getData();
                try {
                    iv.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else{
            cdb = new CustomDB(getApplicationContext());
            cdb.setNotificacion(user, "Problemas con el 'Request Code'");
            cdb.close();
            Toast.makeText(Perfil.this, "Problemas con el 'Request Code'", Toast.LENGTH_SHORT).show();
        }
    }
}
