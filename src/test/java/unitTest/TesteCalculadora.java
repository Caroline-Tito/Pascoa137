package unitTest;

// Blibliotecas

import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Classe
public class TesteCalculadora {
    // Atributos

    // Funções e Métodos

    @Test
    public void testeSomarDoisNumeros (){
        // Configura
        // Valores de entrada
        double num1 = 7;
        double num2 = 5;
        // Valores de saída
        double resultadoEsperado = 12;

        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(num1,num2);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    } //final do teste somar

    // Este é um teste de unidade data driven - direcionado por dados:
    @ParameterizedTest
    @CsvSource(value = {
            "7, 5, 12.0",
            "56, 44, 100.0",
            "10, 0, 10.0",
            "15, -5, 10.0",
            "-8, -6, -14.0"
    }, delimiter = ',')
    public void testeSomarDoisNumerosLendoLista(String txtNum1, String txtNum2, String resultadoEsperado){ //inicio do teste somar
        // Configura
        // Os dados de entrada e o resultado esperado vem da lista

        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1),Integer.valueOf(txtNum2));

        // Valida
        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "csv/massa Somar.csv", numLinesToSkip = 1, delimiter = ',')

    public void testeSomarDoisNumerosLendoArquivo(String txtNum1, String txtNum2, String resultadoEsperado){ //inicio do teste somar
        // Configura
        // Os dados de entrada e o resultado esperado vem da lista

        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1),Integer.valueOf(txtNum2));

        // Valida
        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }
    @Test
    public void testeSubtrairDoisNumeros(){
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 2;
        double resultadoAtual = Calculadora.subtrairDoisNumeros(num1, num2);

        assertEquals(resultadoEsperado, resultadoAtual);

    }
    @Test
    public void testeMultiplicarDoisNumeros(){
        double num1 = 7;
        double num2 = 2;
        double resultadoEsperado = 14;
        double resultadoAtual = Calculadora.multiplicarDoisNumeros(num1, num2);

        assertEquals(resultadoEsperado, resultadoAtual);

    }
    @Test
    public void testeDividirDoisNumerosInteiros(){ //Início do teste dividir inteiro
        //Configura
        int numA = 8;
        int numB = 0;
        String resultadoEsperado = "Não é possível dividir por zero";

        //Executa

        String resultadoAtual = Calculadora.dividirDoisNumerosInteiros(numA, numB);

        //Valida

        assertEquals(resultadoEsperado, resultadoAtual);
        System.out.println(numA + " / " + numB + " = " + resultadoAtual);
        System.out.println("O resultado esperado: " + resultadoEsperado);

    } //fim do teste dividir inteiro

    //Minha solução:
    // @Test
    //public void testeDividirDoisNumeros(){
        //double num1 = 8;
        //double num2 = 2;
        //double resultadoEsperado = 4;
        //double resultadoAtual = Calculadora.dividirDoisNumeros(num1, num2);

        //assertEquals(resultadoEsperado, resultadoAtual);


}
