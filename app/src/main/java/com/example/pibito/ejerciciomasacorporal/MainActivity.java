package com.example.pibito.ejerciciomasacorporal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private EditText txtPeso, txtAltura , txtEdad;
    private Spinner spinerGenero;
    private Button btnCalcular;

    public EditText getTxtPeso() {
        return txtPeso;
    }

    public EditText getTxtAltura() {
        return txtAltura;
    }

    public EditText getTxtEdad() {
        return txtEdad;
    }

    public Spinner getSpinerGenero() {
        return spinerGenero;
    }

    public Button getBtnCalcular() {
        return btnCalcular;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        txtEdad = findViewById(R.id.txtEdad);

        spinerGenero = findViewById(R.id.spinnerGenero);

        btnCalcular = findViewById(R.id.btnCalcular);

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

    public void onClickBoton(View v) {

        String peso = txtPeso.getText().toString();
        String altura = txtAltura.getText().toString();
        Long genero = spinerGenero.getSelectedItemId();
        String edad = txtEdad.getText().toString();

        if (peso.length() == 0)
        {
            Toast.makeText(this,"FALTA CARGAR EL PESO",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (altura.length() == 0)
            {
                Toast.makeText(this,"FALTA CARGAR LA ALTURA",Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (genero == 0)
                {
                    Toast.makeText(this,"FALTA ELEGIR EL GENERO",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (edad.length() == 0)
                    {
                        Toast.makeText(this,"FALTA CARGAR EL PESO",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //SharedPreferences preferences = getSharedPreferences("miShared", Context.MODE_PRIVATE);
                        //SharedPreferences.Editor editor = preferences.edit();

                        //editor.putString("Peso",peso);
                        //editor.putString("Altura",altura);
                        //editor.putLong("Genero",genero);
                        //editor.putString("Edad",edad);
                        //editor.commit();

                        new JsonMasa (peso,altura,genero,edad).execute("https://bmi.p.mashape.com/");

                        JsonMasa jsonMasa = new JsonMasa();

                        Intent intent = new Intent(this,Main2Activity.class);

                        intent.putExtra("RESULTADO",jsonMasa.getResultado());
                        intent.putExtra("ESTADO",jsonMasa.getEstado());
                        intent.putExtra("RIESGO",jsonMasa.getRiesgo());
                        intent.putExtra("IDEAL",jsonMasa.getIdeal());

                        this.startActivity(intent);
                    }
                }
            }
        }

    }

    public class JsonMasa extends AsyncTask<String,Void,String>
    {
        String data = "";
        String resultado,estado,riesgo,ideal;

        public String getResultado() {
            return resultado;
        }

        public String getEstado() {
            return estado;
        }

        public String getRiesgo() {
            return riesgo;
        }

        public String getIdeal() {
            return ideal;
        }

        public JsonMasa() {

        }

        JsonMasa (String peso, String altura, Long genero, String edad)
        {
            String generoJson = "";

            if(genero==1){generoJson="m";}
            if(genero==2){generoJson="f";}

            data = "{ \"weight\": { \"value\": \""+peso+"\", \"unit\": \"kg\" }, \"height\": { \"value\":\""+altura+"\", \"unit\": \"cm\" }, \"sex\": \""+generoJson+"\", \"age\":\""+edad+"\",\"waist\": \"34.00\", \"hip\": \"40.00\" }\n";
        }

        @Override
        protected String doInBackground(String... urls)
        {
            InputStream inputStream = null;
            String result = "";
            HttpURLConnection httpcon;

            String data = "{ \"weight\": { \"value\": \"85.00\", \"unit\": \"kg\" }, \"height\": { \"value\": \"169.00\", \"unit\": \"cm\" }, \"sex\": \"m\", \"age\": \"40\", \"waist\": \"34.00\", \"hip\": \"40.00\" }\n";

            try {
                //Connect
                httpcon = (HttpURLConnection) ((new URL(urls[0]).openConnection()));
                httpcon.setDoOutput(true);
                httpcon.setRequestProperty("X-Mashape-Key", "hRtSku8fDqmsh7SAIsKMaGABTE9up1iXrzjjsnJUbVJImKtDfF");
                httpcon.setRequestProperty("Content-Type", "application/json");
                httpcon.setRequestProperty("Accept", "application/json");
                httpcon.setRequestMethod("POST");
                httpcon.connect();

                //Write
                OutputStream os = httpcon.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(data);
                writer.close();
                os.close();

                //Read
                BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(),"UTF-8"));

                String line = null;
                StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                br.close();
                result = sb.toString();

            } catch (Exception e) {
                // ERROR;
                Log.d("InputStream", e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute (String jsonInternet)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(jsonInternet);
                JSONObject jsonbmi = jsonObject.getJSONObject("bmi");

                resultado = jsonbmi.getString("value");
                estado = jsonbmi.getString("status");
                riesgo = jsonbmi.getString("risk");

                ideal = jsonObject.getString("ideal_weight");

                //SharedPreferences sharedPreferences = getSharedPreferences("ValoresResultado",Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreferences.edit();
                //editor.putString("Resultado",resultado);
                //editor.putString("Estado",estado);
                //editor.putString("Riesgo",riesgo);
                //editor.putString("Ideal",ideal);
                //editor.commit();


            }
            catch (Exception e)
            {
                Toast.makeText(MainActivity.this,"HAY UN ERROR",Toast.LENGTH_LONG).show();
            }
        }
    }
}

