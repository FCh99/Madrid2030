package com.example.faustocheca.madrid2030;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    ImageView imageViewFoto1;

    TextView textViewName;
    TextView textViewActivity;
    TextView textViewZone;
    TextView textViewAddress;
    TextView textViewComments;

    Local local;
    String stringUrl;
    Toolbar toolbar;
    Context context;
    String thePhone = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setupToolbar();


        // Binding

        imageViewFoto1 = (ImageView) findViewById(R.id.image_view);

        textViewName = (TextView) findViewById(R.id.tv_detail_name);
        textViewActivity = (TextView) findViewById(R.id.tv_detail_activity);
        textViewZone = (TextView) findViewById(R.id.tv_detail_zone);
        textViewAddress = (TextView) findViewById(R.id.tv_detail_address);
        textViewComments = (TextView) findViewById(R.id.tv_detail_comments);


        //  Recoger el Local que se va a presentar /////////////


        Intent intent = getIntent();
        Local local = (Local) intent.getParcelableExtra("local");

        stringUrl = local.getFoto1();
        Log.i(">>Foto>>", stringUrl);

        Picasso.with(this).load(stringUrl).into(imageViewFoto1);


    // no influye en la posición que ocupa cada uno

        textViewName.setText(local.getName());
        textViewActivity.setText(local.getActivity());
        textViewZone.setText(local.getZone());
        textViewAddress.setText(local.getDireccion());
        textViewComments.setText(local.getComentarios());



    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                super.onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    public void llamar(View fab) {


        /*
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "660109730"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
        */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + thePhone));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(callIntent);
        }






    }
}
