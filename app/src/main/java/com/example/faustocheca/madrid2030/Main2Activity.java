package com.example.faustocheca.madrid2030;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {



    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    JSONArray jsonArray;
    ArrayList<Local>locals = new ArrayList<>();  // OJO incluye url fotos para el detail ¡¡¡
    ArrayList<LatLng> latLngs = new ArrayList<>();
    //LatLng location;
    String nombre, actividad, zona, comentarios, urlFoto1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        //Binding
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent = getIntent();
        String jsonArrayString = intent.getStringExtra("jsonArray");


        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            //Log.i(">>MESSAGE2>>", jsonArray.toString());


            // http://www.coachingempresas.com/pruebas/BDLocales4.json
            //CAMBIADO ¡¡¡

            // json Array
            // [{"Nombre":"Casa Labra","Actividad":"Restaurante","Zona":"Sol","Comentarios":"Taberna centenaria de cocina castellana: tapas, carnes y recetas clásicas de bacalao en un local con solera.",
            // "Coordenadas":"40.41718184,-3.7046198",
            // "Foto1":"http:\/\/coachingempresas.com\/pruebas\/labra_portada.png"},


            if (jsonArray != null) {

                for (int i=0; i<jsonArray.length(); i++) {

                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String nombre = jsonObject.getString("nombre");
                        String actividad = jsonObject.getString("actividad");
                        String zona = jsonObject.getString("zona");

                        String direccion = jsonObject.getString("direccion");
                        String telefono = jsonObject.getString("telefono");
                        String comentarios = jsonObject. getString("comentario1");
                        String coordenadas = jsonObject.getString("coordenadas");
                        String urlFoto1 = jsonObject.getString("foto1");

                        Local local = new Local(nombre,actividad,zona,comentarios,
                                coordenadas,direccion,telefono,urlFoto1);


                        //Añado al ArrayList<Local>

                        locals.add(local);
                        Log.i(">>localsConFotos>>", String.valueOf(local));



                        String[] coordPartidas = coordenadas.split(",");
                        double latitude = Double.parseDouble(coordPartidas[0]);
                        double longitude = Double.parseDouble(coordPartidas[1]);

                        LatLng latLng = new LatLng(latitude,longitude);


                        // añado al ArrayList<LatLng>

                        latLngs.add(latLng);
                        Log.i(">>>latLngs>>", latLngs.toString());



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }



            setupViewPager();
            setupTabs();
            setupToolbar();



    }




    private void setupViewPager() {



        //Creamos el Array de fragments y le añado las instancias
        // de cada fragment con sus Arrays locals y latLngs


        ArrayList<Fragment> fragments = new ArrayList<>();

        Fragment1 fragment1 = new Fragment1().newInstance(locals);
        fragments.add(fragment1);

        Fragment2GoogleMap fragment2GoogleMap = Fragment2GoogleMap.newInstance(latLngs);
        fragments.add(fragment2GoogleMap);




        //a mostrar por el view pager

        ArrayList<String> titles = new ArrayList<>();
        titles.add("LISTA");
        titles.add("MAPA");

        TabFragmentViewpagerAdapter adapter= new TabFragmentViewpagerAdapter
                (getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);


    }


    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                super.onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }




    private void setupTabs() {
        tabLayout.setupWithViewPager(viewPager);



        //Si queremos cambiar el tamaño del indicador


        int px = Helper.dpToPx(this, 16);
        tabLayout.setSelectedTabIndicatorHeight(px);
 /*

        //si queremos cambiar el color del indicador de tab seleccionada
        int color = ContextCompat.getColor(this, R.color.colorTabIndicator);
        tabLayout.setSelectedTabIndicatorColor(color);

        int colorSelected = ContextCompat.getColor(this, R.color.colorTabIndicatorS);
        int colorUnselected = ContextCompat.getColor(this, R.color.colorTabIndicatorU);
        tabLayout.setTabTextColors(colorSelected, colorUnselected);

         */

    }



    public ArrayList<Local> getMyArrayLocals () {
        return locals;
    }




}
