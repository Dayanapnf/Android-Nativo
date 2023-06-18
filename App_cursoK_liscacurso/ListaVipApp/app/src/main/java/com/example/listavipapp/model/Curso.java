package com.example.listavipapp.model;

public class Curso {

    private String nomeDoCurso;

    public Curso(String nomeDoCursoDesejado) {
        this.nomeDoCurso = nomeDoCursoDesejado;
    }

    public String getNomeDoCurso() {
        return nomeDoCurso;
    }
    public void setNomeDoCurso(String nomeDoCurso) {
        this.nomeDoCurso = nomeDoCurso;
    }
}
