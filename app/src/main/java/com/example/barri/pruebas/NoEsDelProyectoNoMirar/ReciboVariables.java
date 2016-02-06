package com.example.barri.pruebas.NoEsDelProyectoNoMirar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.barri.pruebas.R;

public class ReciboVariables extends AppCompatActivity {

    private TextView tv, tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibovariables);

        tv = (TextView) findViewById(R.id.textView11);
        tv.setText(getIntent().getStringExtra("EditText"));
        //tv.setText(getIntent().getExtras().getString("EditText")); ESTO FUNCIONA Y NO LE PASO UN BUNDLE. WTF???

        tv1 = (TextView) findViewById(R.id.textView12);
        tv1.setText(getIntent().getExtras().getString("EditText1"));

    }
}
