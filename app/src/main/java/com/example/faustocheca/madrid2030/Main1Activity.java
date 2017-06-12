package com.example.faustocheca.madrid2030;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Main1Activity extends AppCompatActivity {

    ArrayList<Local> locales;
    Toolbar toolbar;
    String baseURL = "http://coachingempresas.com/";
    Call<String>localesResponse;
    JSONArray jsonArray;
    Spinner spinnerActividad;
    Spinner spinnerZona;
    String actividadSeleccionada;
    String zonaSeleccionada;
    Context context;
    Button button_start;
    String stringActividad;


    // ONCREATE    //////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);
        button_start = (Button) findViewById(R.id.button_start);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);






        spinnerActividad = (Spinner) findViewById(R.id.spinner_actividad);
        ArrayAdapter adapter = ArrayAdapter.createFromResource
                (this,R.array.activity_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActividad.setAdapter(adapter);


        spinnerZona = (Spinner) findViewById(R.id.spinner_zona);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource
                (this,R.array.zone_array,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZona.setAdapter(adapter2);








        spinnerActividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actividadSeleccionada = parent.getItemAtPosition(position).toString();
                Log.i(">>S ACT_SELEC:>>", actividadSeleccionada);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spinnerZona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zonaSeleccionada = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        if (button_start != null) {
            button_start.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    traerStringParaInterface ();     // <<<<<<<<<<<<<<<<<<<
                    hacerRetrofit();               // <<<<<<<<<<<<<<<<<<<




                }
            });
        }


    }    // FIN DEL ON CREATE  ////////////////////





    private String traerStringParaInterface() {

        //string = "DBPub.json";

        switch (actividadSeleccionada){

            case "Bares de Tapas":
                stringActividad = "Bar";
                break;
            case "Pubs":
                stringActividad = "Pub";
                break;
            case "Discotecas":
                stringActividad = "Discoteca";
                break;
            default:
                stringActividad = "Restaurante";
                break;

        }


        return stringActividad;


    }

    private void hacerRetrofit() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);


        localesResponse = service.traeLocales(stringActividad,zonaSeleccionada);

        localesResponse.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //response.body();
                try {
                    jsonArray = new JSONArray(response.body());

                    // Log.i("TAG1 >>", jsonArray.toString());


                    //meter el arraydelocales en un Intent y enviar a Main2

                    Intent intent = new Intent(Main1Activity.this, Main2Activity.class);
                    intent.putExtra("jsonArray", jsonArray.toString());
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }



}
