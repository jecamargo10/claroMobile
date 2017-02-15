package com.example.javie.claro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import static android.R.attr.data;

/**
 * Created by javie on 13/02/2017.
 */

public class PostService extends AsyncTask <String, String, String>
{


    Activity myActivity;
    String nombre;
    String id;
    public PostService(Activity act)
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
        String json = " { \"fecha\":\""+params[0]+"\", \"jornada\": \""+params[1]+"\"}";

        try {


            String pag = "http://129.144.18.14/ClaroDemo/webresources/claro/createActivity";

          //      String pag = "http://129.144.18.14/ClaroDemo/webresources/claro/availableDates?day="+day+"&month="+month+"&year="+year;
            URL url = new URL(pag);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            Log.e("Envio", json);

            os.write( json.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            Log.e("ENVIO", "asd");
            output = br.readLine();
            Log.e("ENVIO", output + "x");

            Log.e("ENVIO", params[0]);

            os.close();
            br.close();




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

        Intent jesus = new Intent(myActivity, Terminacion.class);
        jesus.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myActivity.getApplicationContext().startActivity(jesus);

pd.dismiss();
    }



}