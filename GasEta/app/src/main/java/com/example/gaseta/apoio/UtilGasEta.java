package com.example.gaseta.apoio;

import java.text.DecimalFormat;

public class UtilGasEta {
    public static String calcularMelhorOpcao(String modeloCarro, double precoGasolina, double precoEtanol) {
        String resultado = "";

        double consumoEtanol = 0;
        double consumoGasolina = 0;

        // Definir consumos com base no modelo do carro
        if (modeloCarro.equals("Kwid 1.0 20/21(urbano)")) {
            consumoEtanol = 10.3; // km/l
            consumoGasolina = 14.9; // km/l
        } else if (modeloCarro.equals("Kwid 1.0 20/21(rodoviário)")) {
            consumoEtanol = 10.8; // km/l
            consumoGasolina = 15.6; // km/l
        } else {
            // Lógica para outros modelos, se necessário
            return "Modelo de carro não suportado.";
        }

        // Cálculo de custo total considerando preço e consumo
        double custoTotalEtanol = precoEtanol / consumoEtanol;
        double custoTotalGasolina = precoGasolina / consumoGasolina;

        // Arredondar para a segunda casa decimal
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        custoTotalEtanol = Double.parseDouble(decimalFormat.format(custoTotalEtanol).replace(",","."));
        custoTotalGasolina = Double.parseDouble(decimalFormat.format(custoTotalGasolina).replace(",","."));

        // Comparação para determinar qual combustível é mais vantajoso
        if (custoTotalEtanol < custoTotalGasolina) {
            resultado = "Custo Gasolina: " + custoTotalGasolina +
                    "\nCusto Etanol: " + custoTotalEtanol +
                    "\n\nAbasteça com Etanol";
        } else if (custoTotalEtanol > custoTotalGasolina) {
            resultado = "Custo Gasolina: " + custoTotalGasolina +
                    "\nCusto Etanol: " + custoTotalEtanol +
                    "\n\nAbasteça com Gasolina";
        } else {
            resultado = "Custo Gasolina: " + custoTotalGasolina +
                    "\nCusto Etanol: " + custoTotalEtanol +
                    "\n\nTanto faz";
        }

        return resultado;
    }
}
