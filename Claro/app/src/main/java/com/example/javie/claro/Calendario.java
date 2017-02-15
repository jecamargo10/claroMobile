package com.example.javie.claro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by javie on 12/02/2017.
 */

public class Calendario extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    private String[] arraySpinner = new String []{"8-12","1-5"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        Intent intent = getIntent();

        String data = intent.getExtras().getString("Fechas");
        String mayor = "";
        String menor = "";
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final ArrayList<CalendarDay>lasFechasNormales = new ArrayList<CalendarDay>();

        final ArrayList<CalendarDay>lasFechasComplejas = new ArrayList<CalendarDay>();


        Log.e("JSON:", data);
        try {
       //     JSONParser parser_obj = new JSONParser();
         //   JSONArray array_obj = (JSONArray) parser_obj.parse("String from web service");
// in your case it will be "result"
            JSONObject jsonObj = new JSONObject(data);

            if(!jsonObj.isNull("fechasComplejas")) {
                String inicia = jsonObj.getString("fechasNormales");
                String ultimo = jsonObj.getString("fechasComplejas");
                String [] x= inicia.split(",");
                String [] z= ultimo.split(",");

                String inicial = x[0];
                String ultimisimo = x[x.length - 1];
                String otroInicial = z[0];
                String otroUltimisimo = z[z.length - 1];


                Date date = format.parse(inicial);

                Date date2 = format.parse(ultimisimo);
                Date date3 = format.parse(otroInicial);
                Date date4 = format.parse(otroUltimisimo);


                if (date.before(date3)) {
                    menor = format.format(date);
                } else {
                    menor = format.format(date3);

                }

                if (date2.after(date4)) {
                    mayor = format.format(date2);
                } else {
                    mayor = format.format(date4);

                }


                //Añado las fechas
                for (int i = 0; i < x.length;i++)
                {
                    String mcdonals = x[i];

                    Calendar tudate = Calendar.getInstance();

                    tudate.setTime(format.parse(mcdonals));
                    CalendarDay s = CalendarDay.from(tudate);


                    lasFechasNormales.add(s);

                }
                for (int i = 0; i < z.length;i++)
                {
                    String mcdonals = x[i];

                    Calendar tudate = Calendar.getInstance();

                    tudate.setTime(format.parse(mcdonals));
                    CalendarDay s = CalendarDay.from(tudate);


                    lasFechasComplejas.add( s);

                }

            }
            else
            {
                String inicia = jsonObj.getString("fechasNormales");
                String [] x= inicia.split(",");

                String inicial = x[0];
                Date date = format.parse(inicial);

                menor = format.format(date);

                String ultimisimo = x[x.length-1];
                Date date2 = format.parse(ultimisimo);

                mayor = format.format(date2);
                for (int i = 0; i < x.length;i++)
                {
                    String mcdonals = x[i];

                    Calendar tudate = Calendar.getInstance();

                    tudate.setTime(format.parse(mcdonals));
                    CalendarDay s = CalendarDay.from(tudate);


                    lasFechasNormales.add(s);

                }


            }


            } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("Fechas tamaño:", lasFechasComplejas.size() +"");
        Log.e("Fechas dos tamaño :", lasFechasNormales.size() +"");



        String dt = "2017-02-22";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
        }

        final MaterialCalendarView calendario = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendario.state().edit()
                .setMinimumDate(CalendarDay.from(Integer.parseInt(menor.split("-")[0]), Integer.parseInt(menor.split("-")[1])-1, Integer.parseInt(menor.split("-")[2])))
                .setMaximumDate(CalendarDay.from(Integer.parseInt(mayor.split("-")[0]), Integer.parseInt(mayor.split("-")[1])-1, Integer.parseInt(mayor.split("-")[2])))

                .setCalendarDisplayMode(CalendarMode.WEEKS)

                .commit();




        EventDecorator asd = new EventDecorator(Color.parseColor("#2980b9"), lasFechasComplejas,lasFechasNormales);
        calendario.addDecorator(asd);





        Button xasd = (Button) findViewById(R.id.button3);
        xasd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciar salgo



                CalendarDay daticoo = null;
                boolean normal = false;
                for (int i = 0; i < lasFechasNormales.size();i++)
                {
                    CalendarDay papitas = lasFechasNormales.get(i);
                    if(papitas.equals(calendario.getSelectedDate()))
                    {
                        daticoo = papitas;
                        normal=true;

                        break;
                    }
                }
                for (int i = 0; i < lasFechasComplejas.size();i++)
                {
                    CalendarDay papitas = lasFechasComplejas.get(i);
                    if(papitas.equals(calendario.getSelectedDate()))
                    {
                        daticoo = papitas;
                        break;
                    }
                }

                if(daticoo != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.set(daticoo.getYear(), daticoo.getMonth() , daticoo.getDay());
                    final String fechaJson= format.format(cal.getTime() );

                    LayoutInflater inflater = (LayoutInflater) Calendario.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    Log.e("ENTRO","A");

                    final View x = inflater.inflate(R.layout.dialog_hora_calendar, null);
                    Log.e("ENTRO","B");

                    AlertDialog.Builder builder = new AlertDialog.Builder(Calendario.this);
                    Log.e("ENTRO","C");
                    final Spinner spinn = (Spinner) x.findViewById(R.id.spinner);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(x.getContext(), android.R.layout.simple_spinner_item, arraySpinner);
                    spinn.setAdapter(adapter);

                    builder.setTitle("Agendar Visita")

                            .setCancelable(false)
                            .setView(x)
                            .setPositiveButton("Agendar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {


                                            PostService papas = new PostService(Calendario.this);
                                            String asd = (String)spinn.getSelectedItem();
                                            if (asd.equals("8-12"))
                                            {

                                                papas.execute(fechaJson,"DIA");

                                            }
                                            else
                                            {
                                                papas.execute(fechaJson,"TARDE");

                                            }

                                        }
                                    })
                            .setNeutralButton("Ver mas fechas",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.exit(0);
                                        }
                                    });
                    final AlertDialog alert = builder.create();

                    alert.show();

                }
                else
                {
                    Toast.makeText(Calendario.this, "No has seleccionado ninguna fecha", Toast.LENGTH_LONG).show();
                }






            }
        });







        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


Log.e("ENTRO","ENTRO");
                Toast.makeText(Calendario.this,  calendario.getSelectedDate().toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
    {
        Log.e("ENTRO","ENTRO");
        Toast.makeText(Calendario.this,  dayOfMonth  + "", Toast.LENGTH_LONG).show();

    }


    public class  EventDecorator implements DayViewDecorator {

        private final int color;
        private final ArrayList<CalendarDay> datesComplejas;
      private final ArrayList<CalendarDay> datesNormales;

        public EventDecorator(int color, ArrayList<CalendarDay>pDatesComplejas,ArrayList<CalendarDay>pDatesNormales) {
            this.color = color;
            this.datesComplejas = pDatesComplejas;
            this.datesNormales = pDatesNormales;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day)
        {
for (int i = 0; i < datesNormales.size();i++)
{
    CalendarDay papitas = datesNormales.get(i);
    if(papitas.equals(day))
    {
        return false;
    }
}
            for (int i = 0; i < datesComplejas.size();i++)
            {
                CalendarDay papitas = datesComplejas.get(i);
                if(papitas.equals(day))
                {
                    return false;
                }
            }
            return true;


        }

        @Override
        public void decorate(DayViewFacade view) {
                    view.setDaysDisabled(true);}
    }


}
