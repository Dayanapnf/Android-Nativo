package com.example.gaseta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaseta.R;
import com.example.gaseta.apoio.UtilGasEta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button buttonCalcular;
    ImageButton imageButtonLimpar;
    FloatingActionButton floatingActionButtonFinalizar;
    FloatingActionButton floatingActionButtonSalvar;
    TextView textViewResultado;
    EditText editTextGasolina;
    EditText editTextEtanol;
    double precoGasolina, precoEtanol;
    String result;
    boolean isDadosOk = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                functionCalcular();
            }
        });
        imageButtonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewResultado.setText("");
                editTextEtanol.setText("");
                editTextGasolina.setText("");



            }
        });
        floatingActionButtonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte sempre!", Toast.LENGTH_LONG ).show();
                finish();
            }
        });
        floatingActionButtonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void inicializarComponentes(){
        editTextEtanol = findViewById(R.id.editTextEtanol);
        editTextGasolina = findViewById(R.id.editTextGasolina);
        textViewResultado = findViewById(R.id.textViewResultado);
        floatingActionButtonFinalizar = findViewById(R.id.floatingActionButtonFinalizar);
        floatingActionButtonSalvar = findViewById(R.id.floatingActionButtonSalvar);
        imageButtonLimpar = findViewById(R.id.imageButtonLimpar);
        buttonCalcular = findViewById(R.id.buttonCalcular);
    }
    public void functionCalcular() {
        // Reinicializa a flag de validação para cada chamada da função
        isDadosOk = true;

        // Verifica se o campo de gasolina está vazio
        if (TextUtils.isEmpty(editTextGasolina.getText())) {
            editTextGasolina.setError("Obrigatório");
            editTextGasolina.requestFocus();
            isDadosOk = false;
        }

        // Verifica se o campo de etanol está vazio
        if (TextUtils.isEmpty(editTextEtanol.getText())) {
            editTextEtanol.setError("Obrigatório");
            editTextEtanol.requestFocus();
            isDadosOk = false;
        }

        // Se os dados estiverem OK, realiza o cálculo
        if (isDadosOk) {
            try {
                precoGasolina = Double.parseDouble(editTextGasolina.getText().toString());
                precoEtanol = Double.parseDouble(editTextEtanol.getText().toString());
                result = UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol);
                textViewResultado.setText(result);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Insira valores válidos nos campos!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
        }
    }

}