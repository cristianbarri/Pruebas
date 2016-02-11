package com.example.barri.pruebas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import DataBase.CustomDB;
import InternetConexion.ImageLoadTask;

public class Login extends AppCompatActivity {

    private TextView tv_user, tv_password, tv_password2;
    private EditText et_user, et_password, et_password2;
    private Button b_login, b_signup;
    private boolean login = true;
    private CustomDB cdb;

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv = (ImageView) findViewById(R.id.imageView3);

        String url = "https://pbs.twimg.com/profile_images/3086835833/892110c8209229ef9bfa107da9af03d9_400x400.png";
        new ImageLoadTask(url, iv).execute();

        tv_user = (TextView) findViewById(R.id.tv_user);
        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_password2 = (TextView) findViewById(R.id.tv_password2);

        et_user = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password2 = (EditText) findViewById(R.id.et_password2);

        et_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !et_password.hasFocus() && !et_password2.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !et_user.hasFocus() && !et_password2.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });

        et_password2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !et_password.hasFocus() && !et_user.hasFocus()) {
                    hideKeyboard(v);
                }
            }
        });

        b_login = (Button) findViewById(R.id.b_login);
        b_signup = (Button) findViewById(R.id.b_signup);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login) {
                    cdb = new CustomDB(getApplicationContext());
                    if (cdb.checkUser(et_user.getText().toString(), et_password.getText().toString(), false)) {
                        Intent i = new Intent(getApplicationContext(), Perfil.class);
                        i.putExtra("user", et_user.getText().toString());
                        startActivity(i);
                        cdb.close();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    tv_password2.setVisibility(View.GONE);
                    et_password2.setVisibility(View.GONE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) b_login.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.et_password);

                    b_login.setLayoutParams(params); //causes layout update
                    login = true;
                }
            }
        });

        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!login) {
                    if (et_password.getText().toString().equals(et_password2.getText().toString()) && !(et_password.getText().toString().equals("") || et_user.getText().toString().equals(""))) {
                        cdb = new CustomDB(getApplicationContext());
                        if (!cdb.checkUser(et_user.getText().toString(), "", true)) {

                            ContentValues cv = new ContentValues();
                            cv.put("user", et_user.getText().toString());
                            cv.put("password", et_password.getText().toString());
                            cv.put("puntuacion", 999);
                            cv.put("notificacion", "Usuario creado correctamente");
                            cv.put("uri_string", "a");

                            cdb.createUser("Usuarios", cv);
                            cdb.close();

                            tv_password2.setVisibility(View.GONE);
                            et_password2.setVisibility(View.GONE);

                            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) b_login.getLayoutParams();
                            params.addRule(RelativeLayout.BELOW, R.id.et_password);

                            b_login.setLayoutParams(params); //causes layout update
                            login = true;

                            Toast.makeText(Login.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Login.this, "El ususario ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        if (et_password.getText().toString().equals(""))
                            Toast.makeText(Login.this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Login.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    tv_password2.setVisibility(View.VISIBLE);
                    et_password2.setVisibility(View.VISIBLE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) b_login.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.et_password2);

                    b_login.setLayoutParams(params); //causes layout update
                    login = false;
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
