package com.example.guardaticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListaPontosCadastrados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pontos_cadastrados);
    }

    public void viewData(View v, String idData){
        Intent intencao = new Intent(this, RegistroPontos.class);
        intencao.putExtra("idData", idData);
        startActivityForResult(intencao, 1);
    }
}
