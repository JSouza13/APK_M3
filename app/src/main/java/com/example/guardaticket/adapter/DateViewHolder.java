package com.example.guardaticket.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guardaticket.ListaPontosCadastrados;
import com.example.guardaticket.R;
import com.example.guardaticket.model.DateModel;

import java.text.DateFormat;

public class DateViewHolder extends RecyclerView.ViewHolder {
    private TextView data;
    private ConstraintLayout clPai;
    private String idData;

    public DateViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListaPontosCadastrados) v.getContext()).viewData(v, idData);
            }
        });

        data = itemView.findViewById(R.id.textData);

        clPai = (ConstraintLayout) itemView;

    }

    public void atualizaGaveta(DateModel date) {
        idData = date.getId();

        DateFormat formatter = android.text.format.DateFormat.getDateFormat(data.getContext());
        String dataFormatada = formatter.format(date.getDate().getTime());
        data.setText(dataFormatada);

    }
}