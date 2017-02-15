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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by javie on 12/02/2017.
 */

public class CheckBoxes extends AppCompatActivity {

private RadioButton rad3;
    private RadioButton rad6;
    private RadioButton rad4;
    private RadioButton rad2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.checkboxes_activity);
         rad3 = (RadioButton) findViewById(R.id.radioButton3);

         rad6 = (RadioButton) findViewById(R.id.radioButton6);
         rad4 = (RadioButton) findViewById(R.id.radioButton4);
         rad2 = (RadioButton) findViewById(R.id.radioButton2);
        rad2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                Log.e("ENTRO","METODO 2");
                ejecuteRadioButton();
            }

            });
        rad4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                Log.e("ENTRO","METODO 2");
                ejecuteRadioButton();
            }

        });
        rad6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                Log.e("ENTRO","METODO 2");
                ejecuteRadioButton();
            }

        });
        rad3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                Log.e("ENTRO","METODO 2");
                ejecuteRadioButton();
            }

        });


        Button boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciar salgo
                ejecuteRadioButton();
                System.exit(0);

            }
        });


    }



    public void ejecuteRadioButton()
    {

                if (rad2.isChecked()&&rad3.isChecked()&&rad4.isChecked()&&rad6.isChecked())
                {
Log.e("ENTRO","ENTRANDO");

                    //PASAR A CALENDARIO LayoutInflater inflater = (LayoutInflater) LoginActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    LayoutInflater inflater = (LayoutInflater) CheckBoxes.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    Log.e("ENTRO","A");

                    final View x = inflater.inflate(R.layout.dialog, null);
                    Log.e("ENTRO","B");

                    AlertDialog.Builder builder = new AlertDialog.Builder(CheckBoxes.this);
                    Log.e("ENTRO","C");
                    final TextView input = new TextView (this);
                    builder.setView(input);

                    builder.setTitle("Finalizar Validaciones")

                            .setCancelable(false)
                            .setView(x)
                            .setPositiveButton("Siguiente",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            RestService papas = new RestService(CheckBoxes.this);
                                            papas.execute();

                                        }
                                    })
                            .setNeutralButton("Problema Solucionado",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.exit(0);
                                        }
                                    });
                    final AlertDialog alert = builder.create();

                    alert.show();
                }
                    // Pirates are the best

        }
    }

