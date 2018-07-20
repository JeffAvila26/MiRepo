package com.example.ioon017.swipe2;

public class Notificacion {

    public String titulo;
    public String descripcion;
    public String fecha;
    public String id;

    public Notificacion(String titulo, String descripcion, String fecha, String id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.id = id;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
    }

    public String getFecha() {

        return fecha;
    }

    public void setFecha(String fecha) {

        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
