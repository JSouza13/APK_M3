package com.example.guardaticket.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guardaticket.R;
import com.example.guardaticket.model.DateDAO;
import com.example.guardaticket.model.DateModel;
import com.example.guardaticket.model.PontoDAO;
import com.example.guardaticket.model.PontoModel;

public class DateAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout elemento = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_lista_pontos_cadastrados, parent, false
        );

        DateViewHolder gaveta = new DateViewHolder(elemento);
        return gaveta;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DateModel date = DateDAO.obtemInstancia().getDateList().get(position);
        DateViewHolder gaveta = (DateViewHolder) holder;

        gaveta.atualizaGaveta(date);
    }

    @Override
    public int getItemCount() {
        return DateDAO.obtemInstancia().getDateList().size();
    }
}
