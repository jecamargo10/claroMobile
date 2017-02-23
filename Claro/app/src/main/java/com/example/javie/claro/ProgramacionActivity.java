package com.example.javie.claro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by javie on 22/02/2017.
 */

public class ProgramacionActivity extends AppCompatActivity {


    private String[] arraySpinner = new String []{"5 minutos antes","10 minutos antes","20 minutos antes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programacion);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String  horas= extras.getString("DATOS");

        final String [] canal =horas.split(";");


        TableLayout prices = (TableLayout)findViewById(R.id.programa);
        prices.setStretchAllColumns(true);
        prices.bringToFront();

        for(int i = 0; i < canal.length; i++){
           if (i ==0)
           {

               TableRow tr =  new TableRow(this);
               tr.setClickable(false);

               TextView c1 = new TextView(this);
               c1.setText("CANAL");
               c1.setTextSize(22);
               c1.setBackground(getResources().getDrawable(R.drawable.borderix));

               TextView c2 = new TextView(this);
               c2.setTextSize(22);
               c2.setText("PROGRAMA");
               c2.setBackground(getResources().getDrawable(R.drawable.borderix));

         //      TextView c3 = new TextView(this);
               //       c3.setText("ROGRAMA 2");
               //       c3.setTextSize(22);
               //        c3.setBackground(getResources().getDrawable(R.drawable.borderix));

               tr.addView(c1);

               tr.addView(c2);
               //     tr.addView(c3);

               prices.addView(tr);
           }



            TableRow tr =  new TableRow(this);
            tr.setClickable(true);
            final int finalI1 = i;
            tr.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
Log.e("ALGO","espicho"  +  canal[finalI1].split(":")[0].split(",")[0]);


                    LayoutInflater inflater = (LayoutInflater) ProgramacionActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View x = inflater.inflate(R.layout.dialog_notificarprograma, null);
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ProgramacionActivity.this);

                    final Spinner spinn = (Spinner) x.findViewById(R.id.spinner2);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(x.getContext(), android.R.layout.simple_spinner_item, arraySpinner);
                    spinn.setAdapter(adapter);

                    builder.setTitle("Notificarme")

                            .setCancelable(false)
                            .setView(x)
                            .setPositiveButton("Guardar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            Intent intent = new Intent(x.getContext(), NotificationService.class);
                                            intent.putExtra("HORA", canal[finalI1].split(":")[0].split(",")[1]);
                                            startService(intent);

                                            Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                                            intento.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intento.putExtra("LOGOUT", true);
                                            startActivity(intento);


                                        }
                                    })
                            .setNeutralButton("Cancelar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.exit(0);
                                        }
                                    });
                    final android.support.v7.app.AlertDialog alert = builder.create();

                    alert.show();




                }
            });

            TextView c1 = new TextView(this);
            c1.setText(canal[i].split(":")[0].split(",")[0]);
            c1.setTextSize(20);
            c1.setBackground(getResources().getDrawable(R.drawable.border));

            TextView c2 = new TextView(this);
            //c2.setBackground(getResources().getDrawable(R.drawable.border));
            c2.setTextSize(18);

            c2.setText(canal[i].split(":")[0].split(",")[1]);
            c2.setBackground(getResources().getDrawable(R.drawable.border));

//            TextView c3 = new TextView(this);
  //          c3.setText(canal[i].split(":")[0].split(",")[2]);
    //        c3.setTextSize(18);
      //      c3.setBackground(getResources().getDrawable(R.drawable.border));


            tr.addView(c1);

            tr.addView(c2);
        //    tr.addView(c3);

            prices.addView(tr);
        }
    }



}















