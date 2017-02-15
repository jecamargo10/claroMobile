package com.example.javie.claro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by javie on 15/02/2017.
 */

public class Terminacion extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminacion_activity);

         TextView spinn = (TextView) findViewById(R.id.textView4);
        spinn.setText("Se ha creado la solicitud de visita con el radicado: \n  "+Math.floor(Math.random() * 1000000000) );

    }

}