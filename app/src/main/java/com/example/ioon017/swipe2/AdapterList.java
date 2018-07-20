package com.example.ioon017.swipe2;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolderDatos> {
    ArrayList<Notificacion> arrayListNotifications;
    Context context;
    Cursor cursor;



    public AdapterList(ArrayList<Notificacion> arrayListNotifications, Context context) {
        this.arrayListNotifications = arrayListNotifications;
        this.context = context;
        this.cursor=cursor;
    }



    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(arrayListNotifications.get(position));
        holder.itemView.setTag( arrayListNotifications.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return arrayListNotifications.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView myTitle;
        TextView myDescription;
        TextView myHoras;
        String id = "";


        public ViewHolderDatos(View itemView) {
            super(itemView);
            myTitle = itemView.findViewById(R.id.text_title);
            myDescription = itemView.findViewById(R.id.text_desc);
            myHoras = itemView.findViewById(R.id.text_hora);


        }

        public void asignarDatos(Notificacion notificacion) {

            myTitle.setText(notificacion.titulo);
            myDescription.setText(notificacion.descripcion);
            myHoras.setText(notificacion.fecha);
            id = notificacion.getId();


        }


    }

}