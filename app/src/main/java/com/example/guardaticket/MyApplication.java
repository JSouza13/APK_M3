package com.example.guardaticket;


import android.app.Application;

import com.example.guardaticket.model.GerenciadorMigracoesRealm;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.deleteRealm(Realm.getDefaultConfiguration());

        RealmConfiguration configuracaoRealm = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration( new GerenciadorMigracoesRealm() )
                .build();

        Realm.setDefaultConfiguration( configuracaoRealm );
        Realm.getInstance( configuracaoRealm );
    }
}
