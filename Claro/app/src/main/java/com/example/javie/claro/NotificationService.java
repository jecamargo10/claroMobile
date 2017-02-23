package com.example.javie.claro;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public  class  NotificationService extends IntentService {
   NotificationService() {
        super("asd");
    }
    private boolean isTerminationConditionMet = false;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("GUARDI", "FUCK");



        final String data = intent.getExtras().getString("HORA");
        Log.e("GUARDI",data);

        //TODO do something useful
        int time=18000;

        Handler h=new Handler();

        h.postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent();
                i.putExtra("HORA",data);
                i.setClass(NotificationService.this, ProgramaFinal.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setAction(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }

        },time);




        return Service.START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();

    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }




    }






