package com.example.guardaticket.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;


public class PontoModel extends RealmObject {

    @PrimaryKey
    private String id;
    private Date data;
    private int horas;
    private int minutos;
    private String foto;

    @Ignore
    private Calendar date;

    public PontoModel(){
        id = UUID.randomUUID().toString();

    }

    public String getId() {
        return id;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Calendar getDate() {
        if(data != null){
            date = Calendar.getInstance();
            date.setTime(data);

        }
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
        this.data = date.getTime();
    }
}
