package com.example.javie.claro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by javie on 12/02/2017.
 */

public class MenuSeleccionAcitivity extends AppCompatActivity {

 private   ListView listView ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.selecion_activity);
        listView = (ListView) findViewById(R.id.leadsNames);


        String[] values = {
                "Instalar nuevo punto",
                "Falla recurrente",
                "Corte del servicio",
                "Fallas en el dispositivo",
                "Otro",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);


                //HACER IF PARA DEPURAR
              if("Instalar nuevo punto".equals(itemValue))
              {
                 // Intent intent = new Intent(MenuSeleccionAcitivity.this, Calendario.class);
                  //startActivity(intent);

                  RestService papas = new RestService(MenuSeleccionAcitivity.this);
                  papas.execute();

              }else{
                  Intent intent = new Intent(MenuSeleccionAcitivity.this, CheckBoxes.class);
                  startActivity(intent);
              }


                // Show Alert


            }
        });


    }
}
