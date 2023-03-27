// 1 - Pacote: Conjunto de classes
package br.com.iterasys;

// 2 - Bibliotecas


// 3 - Classe

public class Calculadora { //Inicio da classe
    // 3.1  Atributos - Características - Campos

    // 3.2 Funções e Métodos
    public static double somarDoisNumeros(double num1, double num2) { //inicio da função somar
        return num1 + num2;
    } //final da função somar

    // ToDo: Criar o código para subtrair, multiplica e dividir
    public static double subtrairDoisNumeros(double num1, double num2) { //inicio da função subtrair
        return num1 - num2;
    } //final da função subtrair

    public static double multiplicarDoisNumeros(double num1, double num2) { //inicio da função multiplicar
        return num1 * num2;
    } //final da função multiplicar

    public static String dividirDoisNumerosInteiros(int numA, int numB) {
        try {
            return String.valueOf(numA / numB);
        } catch (Exception e) {
            return "Não é possível dividir por zero";
        }
    }
   // Solução professor
    // public static double dividirDoisNumeros(double num1, double num2){
            //try{
                //if (num1/num2 < Double.MAX_VALUE && num1/num2 > Double.MIN_VALUE){
                   // return num1/num2;
               // } else {
                    //System.out.println("Não é possivel dividir por zero!");
                    //return 0;
                //}

            //}
            //catch (RuntimeException e) {
                //System.out.println("Não foi possível dividir por zero");
                //return 0;
           // }
        //}
        // Minha solução: public static double dividirDoisNumeros(double num1, double num2){ //inicio da função dividir
        //return num1 / num2;

    //}
}