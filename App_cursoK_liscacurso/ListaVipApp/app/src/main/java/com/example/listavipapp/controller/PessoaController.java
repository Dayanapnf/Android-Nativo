package com.example.listavipapp.controller;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.listavipapp.model.Pessoa;
import com.example.listavipapp.view.MainActivity;

public class PessoaController {

    SharedPreferences.Editor listaVip;
    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_lista_vip";

    public PessoaController(MainActivity mainActivity) {
        // 0 é leitura e esccrita
        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES, 0);
        //cria uma instância de SharedPreferences.Editor para editar as
        // preferências compartilhadas referenciadas por preferences.
        //permitindo que eu use esse objeto posteriormente no método salvar()
        // para adicionar valores aos dados compartilhados.
        listaVip = preferences.edit();
    }


    public void salvar(MainActivity mainActivity) {
        listaVip.putString("primeiroNome", mainActivity.editTextPrimeiroNome.getText().toString());
        listaVip.putString("sobrenome", (mainActivity.editTextSobrenome.getText().toString()));
        listaVip.putString("cursoDesejado", (mainActivity.editTextCursoDesejado.getText().toString()));
        listaVip.putString("telefone", (mainActivity.editTextTelefone.getText().toString()));
        listaVip.apply();

        Toast.makeText(mainActivity, "Salvo", Toast.LENGTH_LONG).show();
    }

    public Pessoa buscar(Pessoa pessoa) {
        //armazenando no objeto os dados via preferences
        pessoa.setPrimeironome(preferences.getString("primeiroNome", ""));
        pessoa.setSobrenome(preferences.getString("sobrenome", ""));
        pessoa.setCursoDesejado(preferences.getString("cursoDesejado", ""));
        pessoa.setTelefoneContato(preferences.getString("telefone", ""));
        return pessoa;
    }

    public void limpar() {
        //limpando os dados da sharedpreference
        listaVip.clear();
        listaVip.apply();
    }
}
