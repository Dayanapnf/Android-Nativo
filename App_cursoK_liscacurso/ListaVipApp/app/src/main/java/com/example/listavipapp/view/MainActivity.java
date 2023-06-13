package com.example.listavipapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listavipapp.R;
import com.example.listavipapp.controller.PessoaController;
import com.example.listavipapp.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_lista_vip";
    SharedPreferences.Editor listaVip;
    PessoaController controler;
    Pessoa pessoa;
    EditText editTextPrimeiroNome;
    EditText editTextSobrenome;
    EditText editTextCursoDesejado;
    EditText editTextTelefone;
    Button btn_limpar;
    Button btn_salvar;
    Button btn_finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //incializando os componentes
        inicializacao();

        pessoa = new Pessoa();
        controler = new PessoaController(MainActivity.this);
        // 0 é leitura e esccrita
        preferences = getSharedPreferences(NOME_PREFERENCES, 0);
        //criando lista para receber os dados
        listaVip = preferences.edit();



        //armazenando no objeto os dados via preferences
        pessoa.setPrimeironome(preferences.getString("primeiroNome", ""));
        pessoa.setSobrenome(preferences.getString("sobrenome", ""));
        pessoa.setCursoDesejado(preferences.getString("cursoDesejado", ""));
        pessoa.setTelefoneContato(preferences.getString("telefone", ""));


        //jogando os dados para os campos edit's
        setEdit();


        //limpando os dados nos campos edit's
        btn_limpar.setOnClickListener(v -> btn_limpar_campos());

        btn_finalizar.setOnClickListener(v -> btn_finalizar_activity());

        btn_salvar.setOnClickListener(v -> {

            if (editTextPrimeiroNome.getText().toString().isEmpty() ||
                    editTextSobrenome.getText().toString().isEmpty() ||
                    editTextCursoDesejado.getText().toString().isEmpty() ||
                    editTextTelefone.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
            } else {
                listaVip.putString("primeiroNome", editTextPrimeiroNome.getText().toString());
                listaVip.putString("sobrenome", (editTextSobrenome.getText().toString()));
                listaVip.putString("cursoDesejado", (editTextCursoDesejado.getText().toString()));
                listaVip.putString("telefone", (editTextTelefone.getText().toString()));
                listaVip.apply();

                //armazenando no objeto os dados via preferences
                pessoa.setPrimeironome(preferences.getString("primeiroNome", ""));
                pessoa.setSobrenome(preferences.getString("sobrenome", ""));
                pessoa.setCursoDesejado(preferences.getString("cursoDesejado", ""));
                pessoa.setTelefoneContato(preferences.getString("telefone", ""));

                controler.salvar(pessoa);
            }

        });

        // Log.i("DADOS PESSOA: ",pessoa.toString());
    }

    public void inicializacao() {
        editTextPrimeiroNome = findViewById(R.id.edit_primeironome);
        editTextSobrenome = findViewById(R.id.edit_sobrenome);
        editTextCursoDesejado = findViewById(R.id.edit_nomedocurso);
        editTextTelefone = findViewById(R.id.edit_telefone);
        btn_limpar = findViewById(R.id.btn_limpar);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_finalizar = findViewById(R.id.btn_finalizar);

    }

    public void setEdit() {
        editTextPrimeiroNome.setText((pessoa.getPrimeironome()));
        editTextSobrenome.setText((pessoa.getSobrenome()));
        editTextCursoDesejado.setText(pessoa.getCursoDesejado());
        editTextTelefone.setText(pessoa.getTelefoneContato());

    }

    //metodo para limpar os campos
    public void btn_limpar_campos() {
        editTextPrimeiroNome.setText("");
        editTextSobrenome.setText("");
        editTextCursoDesejado.setText("");
        editTextTelefone.setText("");
        //limpando os dados da sharedpreference
        listaVip.clear();
        listaVip.apply();
    }

    public void btn_finalizar_activity() {
        finish();
    }

}