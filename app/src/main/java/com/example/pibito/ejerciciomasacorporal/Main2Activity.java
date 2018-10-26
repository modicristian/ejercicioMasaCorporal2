package com.example.pibito.ejerciciomasacorporal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView txtResultado,txtEstado,txtIdeal,txtRiesgo;

    public void setTxtResultado(TextView txtResultado) {
        this.txtResultado = txtResultado;
    }

    public void setTxtEstado(TextView txtEstado) {
        this.txtEstado = txtEstado;
    }

    public void setTxtIdeal(TextView txtIdeal) {
        this.txtIdeal = txtIdeal;
    }

    public void setTxtRiesgo(TextView txtRiesgo) {
        this.txtRiesgo = txtRiesgo;
    }

    private String resultado, estado, ideal, riesgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtResultado = findViewById(R.id.txtResultado);
        txtEstado = findViewById(R.id.txtEstado);
        txtIdeal = findViewById(R.id.txtIdeal);
        txtRiesgo = findViewById(R.id.txtRiesgo);

        SharedPreferences sharedPreferences = getSharedPreferences("ValoresResultado", Context.MODE_PRIVATE);

        resultado = sharedPreferences.getString("Resultado","");
        estado = sharedPreferences.getString("Estado","");
        ideal = sharedPreferences.getString("Ideal","");
        riesgo = sharedPreferences.getString("Riesgo","");







    }
}
