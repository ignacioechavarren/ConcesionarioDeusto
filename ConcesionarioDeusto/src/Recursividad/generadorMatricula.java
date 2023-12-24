package Recursividad;

import java.util.Random;

public class generadorMatricula {
    public static void main(String[] args) {
        String matricula = generarMatricula(4, 3);
        System.out.println("Matrícula generada: " + matricula);
    }

    public static String generarMatricula(int numNumeros, int numLetras) {
        String numeros = "0123456789";
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return generarMatriculaRecursiva(numeros, letras, numNumeros, numLetras, "");
    }

    public static String generarMatriculaRecursiva(String numeros, String letras, int numNumeros, int numLetras, String current) {
        if (numNumeros == 0 && numLetras == 0) {
            return current;
        } else if (numNumeros > 0) {
            int index = (int) (numeros.length() * Math.random());
            String newCurrent = current + numeros.charAt(index);
            return generarMatriculaRecursiva(numeros, letras, numNumeros - 1, numLetras, newCurrent);
        } else {
            int index = (int) (letras.length() * Math.random());
            String newCurrent = current + letras.charAt(index);
            return generarMatriculaRecursiva(numeros, letras, numNumeros, numLetras - 1, newCurrent);
        }
    }
}
