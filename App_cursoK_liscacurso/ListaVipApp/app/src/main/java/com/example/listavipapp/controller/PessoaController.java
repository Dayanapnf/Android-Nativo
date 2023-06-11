package com.example.listavipapp.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.listavipapp.model.Pessoa;
import com.example.listavipapp.view.MainActivity;

public class PessoaController {
    private Context context;

    public PessoaController(Context context){
        this.context = context;
    }
    public void salvar(Pessoa pessoa){
        Toast.makeText(context, "Salvo", Toast.LENGTH_LONG).show();


    }
}
