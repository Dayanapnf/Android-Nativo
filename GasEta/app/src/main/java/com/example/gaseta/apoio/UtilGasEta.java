package com.example.gaseta.apoio;

public class UtilGasEta {
   public static String calcularMelhorOpcao(Double precoGasolina, Double precoEtanol){
       String resultado = "";

        // Verifica se ambos os preços são não negativos
       if (precoGasolina >= 0 && precoEtanol >= 0) {
           // Calcula a relação entre o preço do etanol e da gasolina
           double relacaoPreco = precoEtanol / precoGasolina;

           // Compara a relação de preços e determina a melhor opção
           if (relacaoPreco < 0.7) {
               resultado = "Melhor opção: Etanol";
           } else {
               resultado = "Melhor opção: Gasolina";
           }
       } else {
           resultado = "Os preços não podem ser negativos";
       }


       return resultado;
   }
}
