package com.example.guardaticket.adapter;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guardaticket.R;
import com.example.guardaticket.RegistroPontos;
import com.example.guardaticket.model.PontoModel;

import java.net.URI;
import java.text.DateFormat;

public class PontoViewHolder extends RecyclerView.ViewHolder {
    private TextView data;
    private TextView hora;
    private ConstraintLayout clPai;
    private ImageView image;
    private String idPonto;

    public PontoViewHolder(@NonNull View itemView){
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegistroPontos) v.getContext()).editPonto(v, idPonto);
            }
        });

        data = itemView.findViewById(R.id.textData);
        hora = itemView.findViewById(R.id.textHora);
        image = itemView.findViewById(R.id.imageTicket);


        clPai = (ConstraintLayout) itemView;

    }

    public void atualizaGaveta(PontoModel ponto){
        idPonto = ponto.getId();

        DateFormat formatter = android.text.format.DateFormat.getDateFormat(data.getContext());
        String dataFormatada = formatter.format(ponto.getDate().getTime());
        data.setText(dataFormatada);

        if(ponto.getFoto() != null){
            image.setImageURI(Uri.parse(ponto.getFoto()));
        }

        hora.setText(ponto.getHoras() + ":" + ponto.getMinutos());

    }
}
