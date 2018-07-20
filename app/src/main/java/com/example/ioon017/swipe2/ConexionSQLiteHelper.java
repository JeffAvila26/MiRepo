package com.example.ioon017.swipe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


import static com.example.ioon017.swipe2.Utilidades.CAMPO_DESCRIPCION;
import static com.example.ioon017.swipe2.Utilidades.CAMPO_TITULO;
import static com.example.ioon017.swipe2.Utilidades.TABLA_NOTIFICACION;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    ArrayList<Notificacion> notificaciones = new ArrayList<>();
    AdapterList adapterList;





    public ConexionSQLiteHelper(Context context) {

        super(context, "notificacion3", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREAR_TABLA_NOTIFICACION);

    }

    public void guardarNoti(String titulo, String descripcion){
        db = super.getReadableDatabase();
        ContentValues values = new ContentValues();


        try {
            values.put(CAMPO_TITULO, titulo);
            values.put(CAMPO_DESCRIPCION, descripcion);
        }catch (Exception error){
            error.printStackTrace();
        }
        db.insert(TABLA_NOTIFICACION, null, values);
        db.close();


    }

    public void removeItem(int id){
        db = this.getWritableDatabase();
        db.execSQL("delete from " + Utilidades.TABLA_NOTIFICACION + " where " + Utilidades.CAMPO_ID +" = " + id);

        db.close();
    }



     public ArrayList<Notificacion> llamarTodo(){
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLA_NOTIFICACION, null);
        if(cursor.moveToFirst()){

            for(int i = 0; i < cursor.getString(0).length();i++) {

                Notificacion contenedor = new Notificacion(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
                notificaciones.add(contenedor);

            }
        }

        db.close();

        return notificaciones;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int viejaVersion, int nuevaVersion) {

        db.execSQL("DROP TABLE IF EXISTS notificacion3");

        db.execSQL(Utilidades.CREAR_TABLA_NOTIFICACION);

    }

    public ArrayList llenar_lv(){
        ArrayList<Notificacion> list = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * from notificacion3";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                list.add(new Notificacion(registros.getString(0), registros.getString(1), registros.getString(2), registros.getString(3)));

            }while(registros.moveToNext());
        }

        return list;
    }


    }



