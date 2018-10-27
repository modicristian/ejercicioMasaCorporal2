package com.example.pibito.ejerciciomasacorporal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView txtResultado,txtEstado,txtIdeal,txtRiesgo;
    private Toolbar toolbar;

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

        Bundle b = new Bundle();
        b = getIntent().getExtras();

        resultado = String.valueOf(b.getInt("RESULTADO"));
        estado = String.valueOf(b.getInt("ESTADO"));
        ideal = String.valueOf(b.getInt("IDEAL"));
        riesgo = String.valueOf(b.getInt("RIESGO"));

        //SharedPreferences sharedPreferences = getSharedPreferences("ValoresResultado", Context.MODE_PRIVATE);
        //resultado = sharedPreferences.getString("Resultado","");
        //estado = sharedPreferences.getString("Estado","");
        //ideal = sharedPreferences.getString("Ideal","");
        //riesgo = sharedPreferences.getString("Riesgo","");

        txtResultado.setText(resultado);
        txtEstado.setText(estado);
        txtIdeal.setText(ideal);
        txtRiesgo.setText(riesgo);

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("CALCULAR LA MASA CORPORAL");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        switch (item.getItemId()){
            case R.id.salir:
                break;
        }
        switch (item.getItemId()){
            case R.id.guardar:
                break;
        }
        return true;
    }
}
