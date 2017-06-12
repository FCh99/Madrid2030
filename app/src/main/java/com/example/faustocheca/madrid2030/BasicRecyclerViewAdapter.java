package com.example.faustocheca.madrid2030;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by cice on 1/12/15.
 */
public class BasicRecyclerViewAdapter extends RecyclerView.Adapter<BasicRecyclerViewAdapter.BasicViewHolder> {

    ArrayList<Local> locales;



    public BasicRecyclerViewAdapter(ArrayList<Local> locales) {
        this.locales = locales;
    }




    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_basic, parent, false);

        return new BasicViewHolder(row);
    }


    @Override
    public void onBindViewHolder(BasicViewHolder holder, final int position) {

        holder.textViewName.setText(locales.get(position).getName());
        holder.textViewActivity.setText(locales.get(position).getActivity());
        holder.textViewZone.setText(locales.get(position).getZone());


    }



    @Override
    public int getItemCount() {
        return locales.size();
    }




    public class BasicViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName, textViewActivity, textViewZone;
        public View view;

        public BasicViewHolder(View itemView) {
            super(itemView);


            view = itemView;
            textViewName = (TextView) itemView.findViewById(R.id.text_view_name);
            textViewActivity = (TextView) itemView.findViewById(R.id.text_view_activity);
            textViewZone = (TextView) itemView.findViewById(R.id.text_view_zone);


            itemView.setOnClickListener(
                    new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition ();

                    //Toast.makeText(v.getContext(), "Click" + position, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), DetailActivity.class);

                    Local local = locales.get(position);

                    intent.putExtra("local", local);

                    v.getContext().startActivity(intent);

                }
            });
        }
    }

}
