package com.example.listavipapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listavipapp.R;
import com.example.listavipapp.controller.CursoController;
import com.example.listavipapp.controller.PessoaController;
import com.example.listavipapp.model.Curso;
import com.example.listavipapp.model.Pessoa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CursoController cursoController;
    PessoaController controler;
    Pessoa pessoa;
    Button btn_limpar;
    Button btn_salvar;
    TextView txt_finalizar;
    List<String> nomesDosCursos;
    ArrayAdapter<String> adapter;
    public EditText editTextPrimeiroNome;
    public EditText editTextSobrenome;
    public EditText editTextTelefone;
    public Spinner spinner;
    public String nomeDoCurso_spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //incializando os componentes
        inicializacao();

        cursoController = new CursoController();
        nomesDosCursos = cursoController.dadosParaSpinner();

        pessoa = new Pessoa();
        controler = new PessoaController(MainActivity.this);
        controler.buscar(pessoa);


        //limpando os dados nos campos edit's
        btn_limpar.setOnClickListener(v -> btn_limpar_campos());

        txt_finalizar.setOnClickListener(v -> txt_finalizar_activity());

        btn_salvar.setOnClickListener(v -> {

            if (editTextPrimeiroNome.getText().toString().isEmpty() ||
                    editTextSobrenome.getText().toString().isEmpty() ||
                    editTextTelefone.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
            } else {
                controler.salvar(this);
            }

        });

        //adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cursoController.dadosParaSpinner());
        //layout
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        //injetando o adapter ao Spinner - Lista Gerada
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nomeDoCurso_spinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //jogando os dados para os campos edit's
        setEdit();

    }

    public void inicializacao() {
        editTextPrimeiroNome = findViewById(R.id.edit_primeironome);
        editTextSobrenome = findViewById(R.id.edit_sobrenome);
        editTextTelefone = findViewById(R.id.edit_telefone);
        btn_limpar = findViewById(R.id.btn_limpar);
        btn_salvar = findViewById(R.id.btn_salvar);
        txt_finalizar = findViewById(R.id.btn_finalizar);
        spinner = findViewById(R.id.spinner_list_curso);
    }

    public void setEdit() {
        editTextPrimeiroNome.setText((pessoa.getPrimeironome()));
        editTextSobrenome.setText((pessoa.getSobrenome()));
        if (nomesDosCursos != null && !nomesDosCursos.isEmpty()) {
            int index = adapter.getPosition(pessoa.getCursoDesejado());
            spinner.setSelection(index);
        }
        editTextTelefone.setText(pessoa.getTelefoneContato());

    }

    //metodo para limpar os campos
    public void btn_limpar_campos() {
        controler.limpar(this);
    }

    public void txt_finalizar_activity() {
        finish();
    }

}