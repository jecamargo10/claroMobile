package com.example.javie.claro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by javie on 13/02/2017.
 */

public class RestService  extends AsyncTask <String, String, String>
{


    Activity myActivity;
    String nombre;
    String id;
    public RestService(Activity act)
    {
        super();
        myActivity = act;

    }
    ProgressDialog pd;

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        pd = new ProgressDialog(myActivity);
        pd.setMessage("Cargando");

        pd.show();

    }


    @Override
    protected String doInBackground(String... params) {

        String output = "";

        try {

            Date d = new Date();
            CharSequence z  = DateFormat.format("yyyy", d.getTime());
            String year =  z.toString();
            CharSequence x  = DateFormat.format("MM", d.getTime());
            String month =x.toString();
            CharSequence y  = DateFormat.format("dd", d.getTime());
            String  day= y.toString();
        //    String pag = "http://129.144.18.14/ClaroDemo/webresources/claro/availableDates?day=20&month="+month+"&year="+year;

             String pag = "http://129.144.18.14/ClaroDemo/webresources/claro/availableDates?day="+day+"&month="+month+"&year="+year;
                URL url = new URL(pag);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(false);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");

            conn.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String content = "", line;
            while ((line = rd.readLine()) != null) {
                content += line + "\n";
            }
            rd.close();
            conn.disconnect();

            output+= content.toString();






        } catch (Exception e) {
            e.printStackTrace();
            Log.e("falla", e.getMessage());


        }
        Log.e("retorno", output);

        return output ;
    }


    @Override
    protected void onPostExecute(String result)
    {

        super.onPostExecute(result);

        Log.e("RESULTADO", result);

        Intent jesus = new Intent(myActivity, Calendario.class);
            jesus.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            jesus.putExtra("Fechas", result);
            myActivity.getApplicationContext().startActivity(jesus);
        pd.dismiss();


    }



}