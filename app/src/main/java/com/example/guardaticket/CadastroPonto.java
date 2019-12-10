package com.example.guardaticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.guardaticket.model.PontoDAO;
import com.example.guardaticket.model.PontoModel;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;

public class CadastroPonto extends AppCompatActivity {

    private PontoModel ponto;
    private String idPonto;
    private EditText editData;
    private TimePicker timePicker;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ponto);
        timePicker = findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);

        imageView = findViewById(R.id.btnFoto);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamera(v);
            }
        });

        editData = findViewById(R.id.etData);
        idPonto = getIntent().getStringExtra("pontoId");
        if(idPonto == null){
            ponto = new PontoModel();
            Button btnExcluir = findViewById(R.id.btnExcluir);
            btnExcluir.setVisibility(View.INVISIBLE);

            Calendar dataSelecionada = Calendar.getInstance();

            dataSelecionada.set(dataSelecionada.get(Calendar.YEAR), dataSelecionada.get(Calendar.MONTH), dataSelecionada.get(Calendar.DAY_OF_MONTH));

            ponto.setDate(dataSelecionada);

            DateFormat formatter = android.text.format.DateFormat.getDateFormat(getApplicationContext());
            String  dataSelecionadaFormatada = formatter.format(dataSelecionada.getTime());
            editData.setText(dataSelecionadaFormatada);
        }
        else{
            ponto = PontoDAO.obterInstancia().getObjectId(idPonto);

            timePicker.setHour(Integer.parseInt(ponto.getHoras()));
            timePicker.setMinute(Integer.parseInt(ponto.getMinutos()));

            attFotografiaNaTela();

            DateFormat formatter = android.text.format.DateFormat.getDateFormat(getApplicationContext());
            String dataFormatada = formatter.format(ponto.getDate().getTime());
            editData.setText(dataFormatada);
        }
    }

    public void salvar(View v){
        ponto.setHoras(String.format("%02d", timePicker.getHour()));
        ponto.setMinutos(String.format("%02d", timePicker.getMinute()));

        if (editData.getText().equals("")) {
            Toast.makeText(this, "Favor informe a data", Toast.LENGTH_LONG);
            return;
        }

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
                        setResult(202, intent);
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

    public void abrirCamera(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File arquivo = null;
        try{
            arquivo = criaArquivo();

        }catch (IOException ex){
            Toast.makeText(this, "Não foi possível criar arquivo para foto", Toast.LENGTH_LONG).show();
        }

        if(arquivo != null){
            Uri fotoUri = FileProvider.getUriForFile(this, "com.example.guardaticket.fileprovider", arquivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(intent, 1);
        }
    }


    String caminhoDaFoto = null;

    private File criaArquivo() throws IOException{
        String nome = UUID.randomUUID().toString();

        File diretorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File foto = File.createTempFile(nome, ".jpg", diretorio);

        caminhoDaFoto = foto.getAbsolutePath();
        return foto;

    }

    private void attFotografiaNaTela(){
        if(ponto.getFoto() != null){
            imageView.setImageURI(Uri.parse(ponto.getFoto()));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                ponto.setFoto( caminhoDaFoto );
                attFotografiaNaTela();

            }
        }
    }

}
