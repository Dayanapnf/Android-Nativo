package com.example.gaseta.model;

public class Combustivel {
    private String nomeDoCombustivel;
    private double precoDoCombustivel;
    private String recomendacaoCombustivel;

    public Combustivel(String nomeDoCombustivel, double precoDoCombustivel) {
    }




    public String getNomeDoCombustivel() {
        return nomeDoCombustivel;
    }

    public void setNomeDoCombustivel(String nomeDoCombustivel) {
        this.nomeDoCombustivel = nomeDoCombustivel;
    }

    public double getPrecoDoCombustivel() {
        return precoDoCombustivel;
    }

    public void setPrecoDoCombustivel(double precoDoCombustivel) {
        this.precoDoCombustivel = precoDoCombustivel;
    }

    public String getRecomendacaoCombustivel() {
        return recomendacaoCombustivel;
    }

    public void setRecomendacaoCombustivel(String recomendacaoCombustivel) {
        this.recomendacaoCombustivel = recomendacaoCombustivel;
    }
}
