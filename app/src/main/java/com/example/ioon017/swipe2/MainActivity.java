package com.example.ioon017.swipe2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.google.firebase.messaging.RemoteMessage;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Notificacion> arrayListNotifications;
    AdapterList adap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this);
        arrayListNotifications = conexion.llenar_lv();

        adap = new AdapterList(arrayListNotifications,this);
        rv.setAdapter(adap);
        loadSwipe();

    }

    private void loadSwipe() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                arrayListNotifications.remove(viewHolder.getAdapterPosition());
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext());
                int idRemove = Integer.valueOf(viewHolder.itemView.getTag().toString());
                conn.removeItem(idRemove);
                adap.notifyDataSetChanged();
            }

            @Override
            public void onChildDraw(Canvas c,RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Paint color = new Paint();

                if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){
                    View itemview = viewHolder.itemView;

                    if(dX>0){

                        color.setColor(Color.parseColor("#FF0066"));
                        RectF fondo = new RectF((float)itemview.getLeft(),(float)itemview.getTop(),dX,(float)itemview.getBottom());
                        c.drawRect(fondo,color);


                    } else {
                        color.setColor(Color.parseColor("#FF0066"));
                        RectF fondo = new RectF((float)itemview.getRight()+dX,(float)itemview.getTop(),itemview.getRight(),(float)itemview.getBottom());
                        c.drawRect(fondo,color);

                    }
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };



        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv);

    }
}
