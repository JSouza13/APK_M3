package com.example.guardaticket.model;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class PontoDAO {
    private ArrayList<PontoModel> database;

    private PontoDAO(){
        database = new ArrayList<PontoModel>();
    }

    public ArrayList<PontoModel> getList() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults list = realm.where(PontoModel.class).findAll();
        database.clear();
        database.addAll(realm.copyFromRealm(list));
        return database;
    }

    public ArrayList<PontoModel> getDateList(Date date){
        Realm realm = Realm.getDefaultInstance();
        RealmResults list = realm.where(PontoModel.class).equalTo("data", date).findAll();
        database.clear();
        database.addAll(realm.copyFromRealm(list));
        return database;
    }

    public void addOnList(PontoModel ponto) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(ponto);
        realm.commitTransaction();
    }

    public int updateOnList(PontoModel ponto) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(ponto);
        realm.commitTransaction();

        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getId().equals(ponto.getId())) {
                database.set(i, ponto);
                return i;
            }
        }
        return -1;
    }

    public int deleteOnList(PontoModel ponto) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(PontoModel.class).equalTo("id", ponto.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();

        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getId().equals(ponto.getId())) {
                database.remove(i);
                return i;
            }
        }
        return -1;
    }

    public PontoModel getObjectId(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        PontoModel ponto = realm.copyFromRealm(realm.where(PontoModel.class).equalTo("id", id).findFirst());
        realm.commitTransaction();
        return ponto;
    }

    private static PontoDAO INSTANCIA;

    public static PontoDAO obterInstancia(){
        if(INSTANCIA == null){
            INSTANCIA = new PontoDAO();
        }

        return INSTANCIA;
    }

}
