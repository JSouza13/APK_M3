package com.example.guardaticket.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guardaticket.R;
import com.example.guardaticket.model.PontoDAO;
import com.example.guardaticket.model.PontoModel;

public class PontoAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout elemento = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.ponto_item, parent, false
        );

        PontoViewHolder gaveta = new PontoViewHolder(elemento);
        return gaveta;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PontoModel ponto = PontoDAO.obterInstancia().getList().get(position);
        PontoViewHolder gaveta = (PontoViewHolder) holder;

        gaveta.atualizaGaveta(ponto);
    }

    @Override
    public int getItemCount() {
        return PontoDAO.obterInstancia().getList().size();
    }
}
