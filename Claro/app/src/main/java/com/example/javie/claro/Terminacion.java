package com.example.javie.claro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by javie on 15/02/2017.
 */

public class Terminacion extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminacion_activity);






        LayoutInflater inflater = (LayoutInflater) Terminacion.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View x = inflater.inflate(R.layout.dialogterminacion_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(Terminacion.this);
        TextView spinn = (TextView) x.findViewById(R.id.textView8);
        spinn.setText("Se ha creado la solicitud de visita con el radicado: \n  "+String.valueOf(Math.floor(Math.random() * 100000) ));


        builder.setTitle("Finalizar")

                .setCancelable(false)
                .setView(x)
                .setPositiveButton("Terminar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("LOGOUT", true);
                                startActivity(intent);


                            }
                        });
        final AlertDialog alert = builder.create();

        alert.show();

    }

}