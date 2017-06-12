package com.example.faustocheca.madrid2030;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by cice on 4/3/16.
 */
public class Fragment2GoogleMap extends Fragment implements OnMapReadyCallback {

    private static final String ARG_LATLNGS = "latlngs";

    ArrayList<LatLng> latLngs;
    ArrayList<Local>locals;
    SupportMapFragment mapFragment;
    GoogleMap googleMap;
    Marker marker;
    int index;
    Context context;

    public static Fragment2GoogleMap newInstance(ArrayList<LatLng> latLngs) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LATLNGS, latLngs);

        Fragment2GoogleMap fragment2GoogleMap = new Fragment2GoogleMap();
        fragment2GoogleMap.setArguments(args);



        return fragment2GoogleMap;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        latLngs = getArguments().getParcelableArrayList(ARG_LATLNGS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = LayoutInflater.from(getContext()).inflate(R.layout.fragment2_google_map, container, false);
        return layout;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapFragment = SupportMapFragment.newInstance();

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.map_container, mapFragment)
                .commit();



        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;



        // OJO con este sistema se convierte en no reutilizable

        Main2Activity activity = (Main2Activity) getActivity();
        locals = activity.getMyArrayLocals();



        //  for para recorrer todas las coordenadas

        for (LatLng latLng : latLngs) {


            int i = latLngs.indexOf(latLng);
            String nombre = locals.get(i).getName().toString();
            String actividad = locals.get(i).getActivity().toString();




            MarkerOptions marker = new MarkerOptions()
                    .position(latLng)
                    .title(nombre) // cuando se hace onClick se muestra
                    .snippet(actividad); // cuando se hace onClick se muestra


            googleMap.addMarker(marker);



            CameraPosition cameraPosition = CameraPosition.builder()
                    .target(latLng)
                    .zoom(13)
                    .build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }


    }


}
