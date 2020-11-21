package com.example.googlemapsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class savedLocationAdapter extends RecyclerView.Adapter<savedLocationAdapter.MyViewHolder> {

    ArrayList<address> arrayList;
    Context context;


    public savedLocationAdapter(Context savedLocations, ArrayList<address> arrayList) {
        this.context = savedLocations;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view1 = inflater.inflate( R.layout.item_savedlocadapter, parent, false );
        MyViewHolder holder = new MyViewHolder( view1 );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txt_savedloc.setText( arrayList.get( position ).getLatlong() );

        holder.txt_savedloc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( context, "position" + arrayList.get( position ).getLocname(), Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_savedloc;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            txt_savedloc = itemView.findViewById( R.id.txt_savedloc );
        }
    }
}
