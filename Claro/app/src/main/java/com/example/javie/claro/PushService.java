package com.example.javie.claro;

import android.app.LauncherActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class PushService extends FirebaseMessagingService {
    private NotificationManager notificationManager;

    private static final int NOTIFICATION_ID = 123;
    private Handler handler;

    public PushService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        notificationManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
//        Log.d(TAG, "TITTLE: " + remoteMessage.getNotification().getTitle());
//        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().toString());

        Log.d(TAG, "Message: " + remoteMessage.getData());
String data = remoteMessage.getData().toString().substring(5,74);
        Log.d(TAG, "Message: " + data);
  //      data =data.split(Pattern.quote("\\}\\},"))[0];
      //  data = data.substring(5);
        data += "}}";
        Log.e(TAG, "Super MENSAJE: " + data);

 String hora = "";

        try {

            JSONObject jsonObj = new JSONObject(data);
            hora =jsonObj.getJSONObject("alert").getString("body");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        final String horas = hora.split(",")[1];
        Log.e(TAG, "Super MENSAJE: " + data);

        Intent i = new Intent();
        i.setClass(this, BroadCastDialog.class);
        i.putExtra("HORA",horas);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);





pushNotification(this.getApplicationContext(),"Visita programada a las : " + horas);



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
