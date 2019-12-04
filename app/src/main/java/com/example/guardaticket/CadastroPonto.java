package com.example.guardaticket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


public class CadastroPonto extends AppCompatActivity {

    private PontoModel ponto;
    private String idPonto;
    private EditText editData;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ponto);
        timePicker = findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);

        editData = findViewById(R.id.etData);
        idPonto = getIntent().getStringExtra("pontoId");
        if(idPonto == null){
            ponto = new PontoModel();
            Button btnExcluir = findViewById(R.id.btnExcluir);
            btnExcluir.setVisibility(View.INVISIBLE);
        }
        else{
            ponto = PontoDAO.obterInstancia().getObjectId(idPonto);

            timePicker.setHour(ponto.getHoras());
            timePicker.setMinute(ponto.getMinutos());

            DateFormat formatter = android.text.format.DateFormat.getDateFormat(getApplicationContext());
            String dataFormatada = formatter.format(ponto.getDate().getTime());
            editData.setText(dataFormatada);
        }
    }

    public void salvar(View v){
        ponto.setHoras(timePicker.getHour());
        ponto.setMinutos(timePicker.getMinute());

        if(idPonto == null){
            PontoDAO.obterInstancia().addOnList(ponto);
            setResult(201);
        }else{
            int posicao = PontoDAO.obterInstancia().updateOnList(ponto);
            Intent intent = new Intent();
            intent.putExtra("posicaoDoObjetoEditado", posicao);
            setResult(200, intent);
        }
        finish();
    }

    public void excluir(View v){
        new AlertDialog.Builder(this).setTitle("Tem certeza?").setMessage("Quer Mesmo Excluir?")
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int posicao = PontoDAO.obterInstancia().deleteOnList(ponto);
                        Intent intent = new Intent();
                        intent.putExtra("posicaoDoObjetoExcluido", posicao);
                        finish();
                    }
                }).setNegativeButton("Cancelar", null).show();
    }



    public void selecionarData(View v){
        Calendar dataPadrao = ponto.getDate();
        if(dataPadrao == null){
            dataPadrao = Calendar.getInstance();
        }

        DatePickerDialog datePicker = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar dataSelecionada = Calendar.getInstance();
                dataSelecionada.set(year, month, dayOfMonth);
                ponto.setDate(dataSelecionada);

                DateFormat formatter = android.text.format.DateFormat.getDateFormat(getApplicationContext());
                String  dataSelecionadaFormatada = formatter.format(dataSelecionada.getTime());
                editData.setText(dataSelecionadaFormatada);
            }
        },
                dataPadrao.get(Calendar.YEAR),
                dataPadrao.get(Calendar.MONTH),
                dataPadrao.get(Calendar.DAY_OF_MONTH)
        );
        datePicker.show();
    }
}
