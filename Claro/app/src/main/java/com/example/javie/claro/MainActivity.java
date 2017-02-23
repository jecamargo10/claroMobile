package com.example.javie.claro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import oracle.cloud.mobile.exception.ServiceProxyException;
import oracle.cloud.mobile.mobilebackend.MobileBackendManager;
import oracle.cloud.mobile.notifications.Notifications;

public class MainActivity extends AppCompatActivity


{

    private oracle.cloud.mobile.notifications.Notifications mNotification;
    private final String PROJECT_ID = "602794928434";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushNotification papitas = new PushNotification();



        Button soporteButton = (Button) findViewById(R.id.button2);
        soporteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciar salgo
                Intent intent = new Intent(MainActivity.this, QrAcitivity.class);
                startActivity(intent);
            }
        });


        Button programacion = (Button) findViewById(R.id.button8);
        programacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciar salgo
                RestProgramation papas = new RestProgramation(MainActivity.this);
                papas.execute();
            }
        });



        this.registerNotificationClient();


    }
    private void registerNotificationClient(){
        try {
            mNotification = MobileBackendManager.getManager().getDefaultMobileBackend(this).getServiceProxy(oracle.cloud.mobile.notifications.Notifications.class);
            mNotification.initialize(this, PROJECT_ID);
        } catch (ServiceProxyException e) {
            e.printStackTrace();
        }
    }


}
