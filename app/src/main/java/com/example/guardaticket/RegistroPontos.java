package com.example.guardaticket;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.guardaticket.adapter.PontoAdapter;
import com.example.guardaticket.model.PontoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistroPontos extends AppCompatActivity {

    private PontoAdapter adapter;
    private RecyclerView rvPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pontos);

        rvPontos = findViewById(R.id.rvRegistros);
        adapter = new PontoAdapter();
        rvPontos.setLayoutManager(new LinearLayoutManager(this));
        rvPontos.setAdapter(adapter);


        FloatingActionButton floatingActionButton = findViewById(R.id.btnAdicionar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencao = new Intent(RegistroPontos.this, CadastroPonto.class);
                startActivityForResult(intencao, 1);
            }
        });
    }

    public void editPonto(View v, String pontoId){
        Intent intencao = new Intent(this, CadastroPonto.class);
        intencao.putExtra("pontoId", pontoId);
        startActivityForResult(intencao, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == 200){
                int posicao = data.getIntExtra("posicaoDoObjetoEditado", -1);
                Toast.makeText(this,"Ponto Alterado com Sucesso", Toast.LENGTH_LONG).show();
                adapter.notifyItemChanged(posicao);
                rvPontos.smoothScrollToPosition(posicao);
            }else if(resultCode == 201){
                Toast.makeText(this,"Ponto Inserido com Sucesso", Toast.LENGTH_LONG).show();
                int posicao = PontoDAO.obterInstancia().getList().size()-1;
                adapter.notifyItemInserted(posicao);
                rvPontos.smoothScrollToPosition(posicao);
            }else if(resultCode == 202){
                Toast.makeText(this, "Ponto Excluído com Sucesso", Toast.LENGTH_LONG).show();
                int posicao = data.getIntExtra("posicaoDoObjetoExcluido", -1);
                adapter.notifyItemRemoved(posicao);

            }
        }
    }
}
