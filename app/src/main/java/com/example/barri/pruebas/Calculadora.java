package com.example.barri.pruebas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import DataBase.CustomDB;

public class Calculadora extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bPunto, bIgual, bMas, bMenos, bMultiplicar, bDividir, bClear;
    private double result = 0;
    private int operacion = 6;
    private boolean toast_estado = true;
    private String error = "No he recibido notificaciones";

    private CustomDB cdb; //Actualizar notificaciones
    private String user;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout_calc;
    private Toolbar toolbar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        toolbar = (Toolbar) findViewById(R.id.toolbar_calc);
        setSupportActionBar(toolbar);

        user = getIntent().getStringExtra("user");

        tv = (TextView) findViewById(R.id.textView10);
        tv.setText("0");

        b1 = (Button) findViewById(R.id.boton1);
        b2 = (Button) findViewById(R.id.boton2);
        b3 = (Button) findViewById(R.id.boton3);
        b4 = (Button) findViewById(R.id.boton4);
        b5 = (Button) findViewById(R.id.boton5);
        b6 = (Button) findViewById(R.id.boton6);
        b7 = (Button) findViewById(R.id.boton7);
        b8 = (Button) findViewById(R.id.boton8);
        b9 = (Button) findViewById(R.id.boton9);
        b0 = (Button) findViewById(R.id.boton0);
        bPunto = (Button) findViewById(R.id.botonPunto);
        bIgual = (Button) findViewById(R.id.botonIgual);
        bMas = (Button) findViewById(R.id.botonMas);
        bMenos = (Button) findViewById(R.id.botonMenos);
        bMultiplicar = (Button) findViewById(R.id.botonMultiplica);
        bDividir = (Button) findViewById(R.id.botonDividir);
        bClear = (Button) findViewById(R.id.botonClear);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        bPunto.setOnClickListener(this);
        bIgual.setOnClickListener(this);
        bMas.setOnClickListener(this);
        bMenos.setOnClickListener(this);
        bMultiplicar.setOnClickListener(this);
        bDividir.setOnClickListener(this);
        bClear.setOnClickListener(this);

        //NAVIGATION DRAWER

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navview);

        //Initializing DrawerLayout
        drawerLayout_calc = (DrawerLayout) findViewById(R.id.drawer_layout_calc);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout_calc.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.memory:
                        Intent i2 = new Intent(getApplicationContext(), MemorySwipe.class);
                        i2.putExtra("user", user);
                        i2.putExtra("fragment", 0);
                        startActivity(i2);
                        finish();
                        break;

                    case R.id.ranking:
                        Intent i3 = new Intent(getApplicationContext(), MemorySwipe.class);
                        i3.putExtra("user", user);
                        i3.putExtra("fragment", 1);
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
                        Intent i = new Intent(getApplicationContext(), Perfil.class);
                        i.putExtra("user", user);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.calculadora:
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, "Ya estas en la calculadora");
                        cdb.close();
                        Toast.makeText(Calculadora.this, "Ya estas en la calculadora", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.log_out:
                        Toast.makeText(Calculadora.this, "Log Out Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout_calc, toolbar, R.string.openDrawer, R.string.closeDrawer){

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
        drawerLayout_calc.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton0:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("0");
                    else tv.setText(tv.getText() + "0");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton1:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("1");
                    else tv.setText(tv.getText() + "1");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton2:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("2");
                    else tv.setText(tv.getText() + "2");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton3:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("3");
                    else tv.setText(tv.getText() + "3");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton4:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("4");
                    else tv.setText(tv.getText() + "4");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton5:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("5");
                    else tv.setText(tv.getText() + "5");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton6:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("6");
                    else tv.setText(tv.getText() + "6");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton7:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("7");
                    else tv.setText(tv.getText() + "7");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton8:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("8");
                    else tv.setText(tv.getText() + "8");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.boton9:
                if (operacion != 0) {
                    if (tv.getText() == "0") tv.setText("9");
                    else tv.setText(tv.getText() + "9");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.botonPunto:
                if (operacion != 0) {
                    if (!tv.getText().toString().contains(".")) tv.setText(tv.getText() + ".");
                } else {
                    cdb = new CustomDB(getApplicationContext());
                    cdb.setNotificacion(user, "Porfavor escoge un operador!");
                    cdb.close();
                    Snackbar.make(v, "Porfavor escoge un operador!", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.botonIgual:
                if (operacion == 1) {
                    result += Double.parseDouble(tv.getText().toString());
                    if (!Double.isInfinite(result) && !Double.isNaN(result))
                        tv.setText(String.valueOf(result));
                    else if (Double.isInfinite(result)) {
                        error = "El resultado es infinito!";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();

                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error, Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 1;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                    else if (Double.isNaN(result)) {
                        error = "El resultado produce overflow aritmetico";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error, Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 12;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                } else if (operacion == 2) {
                    result -= Double.parseDouble(tv.getText().toString());
                    if (!Double.isInfinite(result) && !Double.isNaN(result)) tv.setText(String.valueOf(result));
                    else if (Double.isInfinite(result)) {
                        error = "El resultado es infinito!";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error , Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 2;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                    else if (Double.isNaN(result)) {
                        error = "El resultado produce overflow aritmetico";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error, Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 22;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                } else if (operacion == 3) {
                    result *= Double.parseDouble(tv.getText().toString());
                    if (!Double.isInfinite(result) && !Double.isNaN(result)) tv.setText(String.valueOf(result));
                    else if (Double.isInfinite(result)) {
                        error = "El resultado es infinito!";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error , Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 3;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                    else if (Double.isNaN(result)) {
                        error = "El resultado produce overflow aritmetico";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error, Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 33;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                } else if (operacion == 4) {
                    result /= Double.parseDouble(tv.getText().toString());
                    if (!Double.isInfinite(result) && !Double.isNaN(result)) tv.setText(String.valueOf(result));
                    else if (Double.isInfinite(result)) {
                        error = "El resultado es infinito!";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error , Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 4;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                    else if (Double.isNaN(result)) {
                        error = "El resultado produce overflow aritmetico";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error, Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 44;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                } else {
                    if (!Double.isInfinite(result) && !Double.isNaN(result)) tv.setText(String.valueOf(result));
                    else if (Double.isInfinite(result)) {
                        error = "El resultado es infinito!";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error , Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 5;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                    else if (Double.isNaN(result)) {
                        error = "El resultado produce overflow aritmetico";
                        cdb = new CustomDB(getApplicationContext());
                        cdb.setNotificacion(user, error);
                        cdb.close();
                        if (toast_estado)
                            Toast.makeText(Calculadora.this, error, Toast.LENGTH_SHORT).show();
                        else {
                            int mId = 55;

                            NotificationManager mNotificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.ic_launcher)
                                            .setContentTitle("Error!")
                                            .setContentText(error);

                            Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

                            stackBuilder.addParentStack(Calculadora.class);

                            stackBuilder.addNextIntent(resultIntent);

                            PendingIntent resultPendingIntent =
                                    stackBuilder.getPendingIntent(
                                            0,
                                            PendingIntent.FLAG_UPDATE_CURRENT
                                    );
                            mBuilder.setContentIntent(resultPendingIntent);

                            mNotificationManager.notify(mId, mBuilder.build());
                        }
                        tv.setText("0");
                        result = 0;
                        operacion = 6;
                    }
                }
                operacion = 0;

                break;

            case R.id.botonMas:
                if (operacion == 0 || operacion == 6) {
                    result = Double.parseDouble(tv.getText().toString());
                    tv.setText("0");
                    operacion = 1;
                } else operacion = 1;

                break;

            case R.id.botonMenos:
                if (operacion == 0 || operacion == 6) {
                    result = Double.parseDouble(tv.getText().toString());
                    tv.setText("0");
                    operacion = 2;
                } else operacion = 2;


                break;

            case R.id.botonMultiplica:
                if (operacion == 0 || operacion == 6) {
                    result = Double.parseDouble(tv.getText().toString());
                    tv.setText("0");
                    operacion = 3;
                } else operacion = 3;


                break;

            case R.id.botonDividir:
                if (operacion == 0 || operacion == 6) {
                    result = Double.parseDouble(tv.getText().toString());
                    tv.setText("0");
                    operacion = 4;
                } else operacion = 4;


                break;

            case R.id.botonClear:
                tv.setText("0");
                result = 0;
                operacion = 6;

                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sett:
                return true;

            case R.id.llamar:

                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:696969696"));
                startActivity(i);
                return true;

            case R.id.abrir_navegador:
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(i1);
                return true;

            case R.id.toast:
                toast_estado = true;
                cdb = new CustomDB(getApplicationContext());
                cdb.setNotificacion(user, "I'm a toast!");
                cdb.close();
                Toast.makeText(getApplicationContext(), "I'm a toast!", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.estado:
                toast_estado = false;
                cdb = new CustomDB(getApplicationContext());
                cdb.setNotificacion(user, "Tocame para ir a la aplicacion");
                cdb.close();
                //Instanciamos Notification Manager
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
                // un Builder/constructor.
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Soy una notificacion de estado")
                        .setContentText("Tocame para ir a la aplicacion");

                // Creamos un intent explicito, para abrir la app desde nuestra notificación
                Intent resultIntent = new Intent(getApplicationContext(), Calculadora.class);

                //El objeto stack builder contiene una pila artificial para la Acitivty empezada.
                //De esta manera, aseguramos que al navegar hacia atr�s
                //desde la Activity nos lleve a la home screen.

                //Desde donde la creamos
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                // Añade la pila para el Intent, pero no el intent en si
                stackBuilder.addParentStack(Calculadora.class);
                // Añadimos el intent que empieza la activity que esta en el top de la pila
                stackBuilder.addNextIntent(resultIntent);

                //Obtenemos el pending intent
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                mBuilder.setContentIntent(resultPendingIntent);

                // Primer parametro es el id de la notificacion
                mNotificationManager.notify(1, mBuilder.build());

                return true;

            default:
                cdb = new CustomDB(getApplicationContext());
                cdb.setNotificacion(user, "DEFAULT!");
                cdb.close();
                Snackbar.make(findViewById(R.id.linearLayout), "DEFAULT!", Snackbar.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView t = (TextView) findViewById(R.id.textView10);
        outState.putString("textView", t.getText().toString());
        outState.putDouble("result", result);
        outState.putInt("operacion", operacion);
        outState.putBoolean("toast_estado", toast_estado);
        outState.putString("error", error);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView t = (TextView) findViewById(R.id.textView10);
        t.setText(savedInstanceState.getString("textView"));
        result = savedInstanceState.getDouble("result");
        operacion = savedInstanceState.getInt("operacion");
        toast_estado = savedInstanceState.getBoolean("toast_estado");
        error = savedInstanceState.getString("error");

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main5 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.barri.pruebas/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main5 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.barri.pruebas/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
