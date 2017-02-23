package com.example.javie.claro;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by javie on 22/02/2017.
 */

public class ProgramaFinal extends AppCompatActivity {
    private NotificationManager notificationManager;

    private static final int NOTIFICATION_ID = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

      String  horas= extras.getString("HORA");


        AlertDialog.Builder builder = new AlertDialog.Builder(ProgramaFinal.this);
        builder.setCancelable(false)
                //         .setView(x)
                .setMessage("El programa: " + horas  + " esta a punto de comenzar")
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
        Vibrator vibrator = (Vibrator) ProgramaFinal.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);



        pushNotification(this.getApplicationContext(),"El programa: " + horas  + "esta a punto de comenzar");




    }
    private void pushNotification (Context ctx, String msg) {




        Intent notifyIntent = new Intent(ctx, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(
                ctx,
                0,
                new Intent[]{notifyIntent},
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(ctx)

                .setSmallIcon(R.drawable.icon)


                .setContentTitle("Claro te informa")
                .setContentText(msg)
                // .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        notificationManager.notify(NOTIFICATION_ID, notification);




    }


}















