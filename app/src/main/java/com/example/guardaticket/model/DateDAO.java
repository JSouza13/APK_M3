package com.example.guardaticket.model;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class DateDAO {
    private ArrayList<DateModel> databaseDate;

    private DateDAO(){
        databaseDate = new ArrayList<DateModel>();
    }

    public ArrayList<DateModel> getDateList(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults list = realm.where(DateModel.class).findAll();
        databaseDate.clear();
        databaseDate.addAll(realm.copyFromRealm(list));
        return databaseDate;
    }


    public void addOnList(DateModel date){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(date);
        realm.commitTransaction();
    }

    public DateModel getDate(Date date){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        DateModel data = realm.copyFromRealm(realm.where(DateModel.class).equalTo("date", date).findFirst());
        realm.commitTransaction();
        return data;
    }

    public boolean search(Date date){
        DateModel data = null;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        DateModel data1 = realm.where(DateModel.class).equalTo("date", date).findFirst();
        if(data1 != null){
            data = realm.copyFromRealm(data1);
        }
        realm.commitTransaction();

        if(data != null){
            return true;
        }else{
            return false;
        }
    }

    public int updateOnList(DateModel date){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(date);
        realm.commitTransaction();

        for (int i = 0; i < databaseDate.size(); i++) {
            if (databaseDate.get(i).getId().equals(date.getId())) {
                databaseDate.set(i, date);
                return i;
            }
        }
        return -1;
    }

    public int deleteOnList(DateModel date){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(DateModel.class).equalTo("id", date.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();

        for (int i = 0; i < databaseDate.size(); i++) {
            if (databaseDate.get(i).getId().equals(date.getId())) {
                databaseDate.remove(i);
                return i;
            }
        }
        return -1;
    }

    private static DateDAO INSTANCIA;

    public static DateDAO obtemInstancia(){
        if(INSTANCIA == null){
            INSTANCIA = new DateDAO();
        }
        return INSTANCIA;
    }


}
