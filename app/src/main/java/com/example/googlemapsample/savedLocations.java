package com.example.googlemapsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class savedLocations extends AppCompatActivity {

    RecyclerView rcv_savedLoc;


    ArrayList<address> arrayList = new ArrayList<>();

    savedLocationAdapter locationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_saved_locations );

        rcv_savedLoc = findViewById( R.id.rcv_savedLoc );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext(), RecyclerView.VERTICAL, false );
        rcv_savedLoc.setLayoutManager( linearLayoutManager );

        LoadData();

        setAdapter();

    }

    private void LoadData() {
        arrayList.clear();
        myhelper myhelper = new myhelper( getApplicationContext() );
        arrayList = (ArrayList<address>) myhelper.addressList();

        if (arrayList.size() > 0) {
            setAdapter();
        }
    }

    private void setAdapter() {
        locationAdapter = new savedLocationAdapter( this, arrayList );
        rcv_savedLoc.setAdapter( locationAdapter );
    }
}
