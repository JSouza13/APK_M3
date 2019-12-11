package com.example.guardaticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guardaticket.model.PontoDAO;
import com.example.guardaticket.model.PontoModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textViewQtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.btnRegistros);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencao = new Intent(MainActivity.this, RegistroPontos.class);
                startActivity(intencao);


            }
        });

        Calendar dataSelecionada = Calendar.getInstance();

        dataSelecionada.set(dataSelecionada.get(Calendar.YEAR), dataSelecionada.get(Calendar.MONTH), dataSelecionada.get(Calendar.DAY_OF_MONTH));





        int size = PontoDAO.obterInstancia().getList().size();
        Log.d("DATATEST", dataSelecionada.getTime().toString());

        int qtdPontoDiario = PontoDAO.obterInstancia().getDateList(dataSelecionada.getTime()).size();
        textView = findViewById(R.id.textView2);
        textViewQtd = findViewById(R.id.textQtd);

        if(size > 0){
            textView.setText("Você possui " + size + " registros cadastrados");
        }

        if(qtdPontoDiario == 0){
            textViewQtd.setText("Hoje você ainda não cadastrou seu ponto!");
        }else{
            textViewQtd.setText("Você tem " + qtdPontoDiario + " pontos cadastrados de hoje!");
        }




    }
}
