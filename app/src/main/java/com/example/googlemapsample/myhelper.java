package com.example.googlemapsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.googlemapsample.myhelper.storedData.TABLE_NAME;

public class myhelper {
    storedData storedData;

    public myhelper(Context context) {
        storedData = new storedData( context ) {
        };
    }

    static class storedData extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "location.db";
        private static final int DATABASE_Version = 1;
        public static final String TABLE_NAME = "LatLongDetails";
        private static final String LocName = "Name";

        private static final String selectedLatlong = "LATLONG";

        public static final String UID = "_id";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LocName + " VARCHAR(255)," + selectedLatlong + " VARCHAR(255));";

        public Context context;

        public storedData(Context context) {
            super( context, DATABASE_NAME, null, DATABASE_Version );
            this.context = context;

        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL( CREATE_TABLE );
            } catch (Exception e) {

                Toast.makeText( context, "" + e.getMessage(), Toast.LENGTH_SHORT ).show();

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
            onCreate( sqLiteDatabase );
        }
    }


    public long insertion(String address, String Latlong) {

        SQLiteDatabase dbb = storedData.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( storedData.LocName, address );
        contentValues.put( storedData.selectedLatlong, Latlong );


        long result = dbb.insert( TABLE_NAME, null, contentValues );
        return result;
    }

    public String getData() {
        SQLiteDatabase sqLiteDatabase = storedData.getWritableDatabase();
        String details[] = {storedData.UID, storedData.LocName};
        Cursor cursor = sqLiteDatabase.query( TABLE_NAME, details, null, null, null, null, null );
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int id = cursor.getColumnIndex( storedData.UID );
            String eventname = cursor.getString( cursor.getColumnIndex( storedData.LocName ) );
            String selected_latlong = cursor.getString( cursor.getColumnIndex( storedData.selectedLatlong ) );
            buffer.append( eventname + selected_latlong + "\n" );
        }
        return buffer.toString();

    }


    public List<address> addressList() {
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase dbb = storedData.getReadableDatabase();

        List<address> storedlocations = new ArrayList<>();

        Cursor cursor = dbb.rawQuery( sql, null );
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt( cursor.getString( 0 ) );
                    String locanme = cursor.getString( 1 );
                    String locLatlong = cursor.getString( 2 );

                    storedlocations.add( new address( id, locanme, locLatlong ) );
                }
                while (cursor.moveToNext());

            }
        }
        cursor.close();
        return storedlocations;
    }


}




