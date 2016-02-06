package com.example.barri.pruebas.NoEsDelProyectoNoMirar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.barri.pruebas.R;

public class Pantalla2 extends AppCompatActivity {

    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("OnCreate", "OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        b = (Button) findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Pantalla3.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("OnResume", "OnResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("OnStart", "OnStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("OnPause", "OnPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("OnDestroy", "OnDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("OnStop", "OnStop");
    }
}
