package com.example.gaseta.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gaseta.R;
import com.example.gaseta.apoio.UtilGasEta;
import com.example.gaseta.model.Combustivel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button buttonCalcular;
    ImageButton imageButtonFinalizar;
    FloatingActionButton floatingActionButtonLimpar;
    TextView textViewResultado;
    EditText editTextGasolina;
    EditText editTextEtanol;
    double precoGasolina, precoEtanol;
    String result, modeloCarro;
    boolean isDadosOk = true;
    Spinner spinnerModeloCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        inicializarComponentes();

        // Inicializa o Spinner
        spinnerModeloCarro = findViewById(R.id.spinnerModeloCarro);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.modelos_carro, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModeloCarro.setAdapter(adapter);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    exibirProgressBar();
                    try {
                        // Oculta o resultado anterior
                        textViewResultado.setText("");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                functionCalcular();
                                ocultarProgressBar();
                            }
                        }, 3000);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Insira valores válidos nos campos!", Toast.LENGTH_LONG).show();
                    } finally {

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();

                }
            }
        });

        imageButtonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte sempre!", Toast.LENGTH_LONG).show();
                finish();

            }
        });

        floatingActionButtonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });


    }

    private void inicializarComponentes() {
        editTextEtanol = findViewById(R.id.editTextEtanol);
        editTextGasolina = findViewById(R.id.editTextGasolina);
        textViewResultado = findViewById(R.id.textViewResultado);
        floatingActionButtonLimpar = findViewById(R.id.floatingActionButtonLimpar);
       // floatingActionButtonSalvar = findViewById(R.id.floatingActionButtonSalvar);
        imageButtonFinalizar = findViewById(R.id.imageButtonFinalizar);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        progressBar = findViewById(R.id.progressBar);
    }

    private void exibirProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void ocultarProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void limparCampos() {
        textViewResultado.setText("");
        editTextEtanol.setText("");
        editTextGasolina.setText("");
    }

    private void functionCalcular() {

        modeloCarro = spinnerModeloCarro.getSelectedItem().toString();
        precoGasolina = Double.parseDouble(editTextGasolina.getText().toString());
        precoEtanol = Double.parseDouble(editTextEtanol.getText().toString());
        result = UtilGasEta.calcularMelhorOpcao(modeloCarro,precoGasolina, precoEtanol);
        textViewResultado.setText(result);


    }
}
