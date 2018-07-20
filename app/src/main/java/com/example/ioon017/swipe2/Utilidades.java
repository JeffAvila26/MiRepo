package com.example.ioon017.swipe2;

public class Utilidades {

    public static String TABLA_NOTIFICACION = "notificacion3";
    public static String CAMPO_TITULO = "titulo";
    public static String CAMPO_DESCRIPCION = "descripcion";
    public static String CAMPO_FECHA = "fecha";
    public static String CAMPO_ID = "id";

    public static String CREAR_TABLA_NOTIFICACION = "CREATE TABLE " + TABLA_NOTIFICACION +" ("+CAMPO_TITULO+" TEXT,"
            +CAMPO_DESCRIPCION+" TEXT, "+CAMPO_FECHA+" TEXT," + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT) ";

}

