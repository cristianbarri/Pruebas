package com.example.barri.pruebas.NoEsDelProyectoNoMirar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.barri.pruebas.R;

public class PasoVariables extends AppCompatActivity {

    private EditText et, et1;
    private Button b, b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasovariables);

        et = (EditText) findViewById(R.id.editText);
        b = (Button) findViewById(R.id.button4);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ReciboVariables.class);
                i.putExtra("EditText", et.getText().toString());
                startActivity(i);
            }
        });

        et1 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("EditText1", et1.getText().toString());

                Intent i = new Intent(getApplicationContext(), ReciboVariables.class);
                i.putExtras(b);
                startActivity(i);

            }
        });
    }
}
