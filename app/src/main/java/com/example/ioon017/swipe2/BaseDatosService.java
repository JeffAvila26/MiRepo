package com.example.ioon017.swipe2;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class BaseDatosService extends Service {

    ConexionSQLiteHelper conexionSQLiteHelper;
    String titulo;
    String descripcion;
    Context context;

    public BaseDatosService() {
    }

    public BaseDatosService(Context context, String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("APP", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Servicio creado...");
        conexionSQLiteHelper = new ConexionSQLiteHelper(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        String titulo = intent.getStringExtra("titulo");
        String descripcion = intent.getStringExtra("descripcion");


        Log.d("APP", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TITULO : " + titulo);


        if(MainActivity.class == null) {
            conexionSQLiteHelper = new ConexionSQLiteHelper(this);
            conexionSQLiteHelper.guardarNoti(titulo, descripcion);
         }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {



        return null;
    }
}
