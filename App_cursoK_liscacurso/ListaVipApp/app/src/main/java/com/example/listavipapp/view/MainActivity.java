package com.example.listavipapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listavipapp.R;
import com.example.listavipapp.model.Pessoa;

public class MainActivity extends AppCompatActivity {


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


        //jogando os dados para os campos edit's
        setEdit();

        //limpando os dados nos campos edit's
        btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_limpar_campos();
            }
        });
        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_finalizar_activity();
            }
        });
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextPrimeiroNome.getText().toString().isEmpty() ||
                        editTextSobrenome.getText().toString().isEmpty() ||
                        editTextCursoDesejado.getText().toString().isEmpty() ||
                        editTextTelefone.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Preencha todos os campos",Toast.LENGTH_LONG).show();
                }else{

                    pessoa.setPrimeironome(editTextPrimeiroNome.getText().toString());
                    pessoa.setSobrenome(editTextSobrenome.getText().toString());
                    pessoa.setCursoDesejado(editTextCursoDesejado.getText().toString());
                    pessoa.setTelefoneContato(editTextTelefone.getText().toString());
                    Toast.makeText(MainActivity.this,"Salvo",Toast.LENGTH_LONG).show();
                }
            }
        });


       // Log.i("DADOS PESSOA: ",pessoa.toString());
    }
     public void inicializacao(){
         editTextPrimeiroNome = findViewById(R.id.edit_primeironome);
         editTextSobrenome = findViewById(R.id.edit_sobrenome);
         editTextCursoDesejado = findViewById(R.id.edit_nomedocurso);
         editTextTelefone= findViewById(R.id.edit_telefone);

     }

     public void setEdit(){
        editTextPrimeiroNome.setText(pessoa.getPrimeironome());
        editTextSobrenome.setText(pessoa.getSobrenome());
        editTextCursoDesejado.setText(pessoa.getCursoDesejado());
        editTextTelefone.setText(pessoa.getTelefoneContato());
        btn_limpar = findViewById(R.id.btn_limpar);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_finalizar = findViewById(R.id.btn_finalizar);

    }
    //metodo para limpar os campos
    public void btn_limpar_campos(){
        editTextPrimeiroNome.setText("");
        editTextSobrenome.setText("");
        editTextCursoDesejado.setText("");
        editTextTelefone.setText("");

    }
    public void btn_finalizar_activity(){
        finish();
    }

}