package com.example.guardaticket.model;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class GerenciadorMigracoesRealm implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        for(long versao = oldVersion; versao < newVersion; versao++){
            passoDeMigracao(realm, versao, versao + 1);
        }
    }

    private void passoDeMigracao(DynamicRealm realm, long versaoVelha, long versaoNova) {
        if(versaoVelha == 0 && versaoNova == 1){
            RealmSchema schema = realm.getSchema();
            RealmObjectSchema pontoSchema = schema.get("Ponto");
            pontoSchema.addField("foto", String.class);
        }
    }
}
