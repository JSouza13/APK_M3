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
    private String horas;
    private String minutos;
    private String foto;

    @Ignore
    private Calendar date;


    public PontoModel(){
        id = UUID.randomUUID().toString();

    }

    public String getId() {
        return id;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
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
