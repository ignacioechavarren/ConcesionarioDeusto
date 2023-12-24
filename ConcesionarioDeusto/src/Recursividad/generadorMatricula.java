package Recursividad;

import java.util.Random;

public class generadorMatricula {
    public static void main(String[] args) {
        String matricula = generarMatricula(4, 3);
        System.out.println("Matrícula: " + matricula);
    }

    public static String generarMatricula(int numNumeros, int numLetras) {
        String numeros = "0123456789";
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return generarMatriculaRecursiva(numeros, letras, numNumeros, numLetras, "");
    }

    public static String generarMatriculaRecursiva(String numeros, String letras, int numNumeros, int numLetras, String c) {
        if (numNumeros == 0 && numLetras == 0) {
            return c;
        } else if (numNumeros > 0) {
            int index = (int) (numeros.length() * Math.random());
            String nuevoc = c + numeros.charAt(index);
            return generarMatriculaRecursiva(numeros, letras, numNumeros - 1, numLetras, nuevoc);
        } else {
            int index = (int) (letras.length() * Math.random());
            String nuevoc = c + letras.charAt(index);
            return generarMatriculaRecursiva(numeros, letras, numNumeros, numLetras - 1, nuevoc);
        }
    }
}
