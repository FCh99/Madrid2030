package com.example.faustocheca.madrid2030;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by faustocheca on 2/5/16.
 */
public class Fragment1 extends Fragment {



    ArrayList <Local> locals;



    public static Fragment1 newInstance (ArrayList<Local>locals) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("array",locals);

        Fragment1 fragment1 = new Fragment1();
        fragment1.setArguments(args);
        return fragment1;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locals = getArguments().getParcelableArrayList("array");






    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_1,container,false);





        //Binding con las vistas del layout que tiene este fragmento
        RecyclerView recyclerView = (RecyclerView) layout. findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Creamos el adapter que maneja el recycler
        BasicRecyclerViewAdapter adapter = new BasicRecyclerViewAdapter(locals);
        recyclerView.setAdapter(adapter);

        return layout;



    }
}
