package com.example.faustocheca.madrid2030;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by faustocheca on 11/6/16.
 */
public interface ApiService {

    @GET ("pruebas/programFiles/locales.php")
    Call<String> traeLocales (@Query("actividad") String actividad,
                              @Query("zona") String zona
    );

}

/*

http://coachingempresas.com/pruebas/programFiles/locales.php?actividad=Discoteca&zona=Sol

pruebas/programFiles/locales.php


 */

