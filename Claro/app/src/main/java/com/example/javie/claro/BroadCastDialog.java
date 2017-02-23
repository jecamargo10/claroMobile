package com.example.javie.claro;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import static android.content.ContentValues.TAG;

/**
 * Created by javie on 22/02/2017.
 */

public class BroadCastDialog extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        Log.e(TAG, "Super MENSAJE: " + "ENTROOO");

      String  horas= extras.getString("HORA");


        AlertDialog.Builder builder = new AlertDialog.Builder(BroadCastDialog.this);
        builder.setCancelable(false)
                //         .setView(x)
                .setMessage("El tenico Rafael Meneses lo visitara aproximadamente a las : " + horas)
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("LOGOUT", true);
                                startActivity(intent);
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
        Vibrator vibrator = (Vibrator) BroadCastDialog.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);







    }



}















