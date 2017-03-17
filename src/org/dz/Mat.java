package org.dz;


import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LuisR
 */
public class Mat {

    public static int[] getCifras(int numero) {
        int max = getNumeroCifras(numero);
        int cifras[] = new int[max];
        for (int i = 0; i < max; i++) {
            double p1 = Math.pow(10D, i + 1);
            double p2 = Math.pow(10D, i);
            cifras[max - (i + 1)] = Math.abs((int) (((double) numero % p1) / p2));
        }
        return cifras;
    }

    public static int getNumeroCifras(int numero) {
        return String.valueOf(Math.abs(numero)).length();
    }

    public static int[] invertirArray(int array[]) {
        int max = array.length;
        int array1[] = new int[max];
        for (int i = max - 1; i >= 0; i--) {
            array1[max - (i + 1)] = array[i];
        }
        return array1;
    }

    public static int aleatorio(int min, int max) {
        return Math.min(min, max) + (int) (Math.random() * (double) (Math.abs(max - min) + 1));
    }

    public static double aleatorio(double min, double max) {
        return Math.min(min, max) + Math.random() * Math.abs(max - min);
    }

    public static boolean probabilidad(double p) {
        if (p < 0.0D || p > 1.0D) {
            throw new IllegalArgumentException("0 >= p <=1.0");
        } else {
            return Math.random() <= p;
        }
    }

    public static int maximoArray(int array[]) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int[] arrayAleatorio(int tam, int min, int max) {
        int array[] = new int[tam];
        for (int i = tam - 1; i >= 0; i--) {
            array[i] = aleatorio(min, max);
        }
        return array;
    }

    public static int[][] matrizAleatoria(int filas, int columnas, int min, int max) {
        int mat[][] = new int[filas][columnas];
        for (int i = filas - 1; i >= 0; i--) {
            for (int j = columnas - 1; j >= 0; j--) {
                mat[i][j] = aleatorio(min, max);
            }
        }
        return mat;
    }

    public static double[][] matrizAleatoria(int filas, int columnas, double min, double max) {
        double mat[][] = new double[filas][columnas];
        for (int i = filas - 1; i >= 0; i--) {
            for (int j = columnas - 1; j >= 0; j--) {
                mat[i][j] = aleatorio(min, max);
            }
        }
        return mat;
    }

    public static double[][] multiplicarMatrices(double mat1[][], double mat2[][]) throws Exception {
        if (mat1 == null || mat2 == null) {
            throw new Exception("matrices no pueden ser null");
        }
        if (mat1[0].length != mat2.length) {
            throw new Exception((new StringBuilder()).append("tama\361o de las matrices invalido (").append(mat1.length).append("x").append(mat1[0].length).append(" - ").append(mat2.length).append("x").append(mat2[0].length).append(")").toString());
        }
        double mat3[][] = new double[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                mat3[i][j] = 0.0D;
                for (int k = 0; k < mat1[0].length; k++) {
                    mat3[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return mat3;
    }

    public static double[][] matrizTraspuesta(double mat1[][]) throws Exception {
        if (mat1 == null) {
            throw new Exception("matriz no puede ser null");
        }
        int FIL = mat1.length;
        int COL = mat1[0].length;
        double mat2[][] = new double[COL][FIL];
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < FIL; j++) {
                mat2[i][j] = mat1[j][i];
            }
        }
        return mat2;
    }

    public static double[][] arrayToMatriz(double mat1[], int filas, int columnas, double fixed) throws Exception {
        if (mat1 == null) {
            throw new Exception("array no puede ser null");
        }
        if (filas <= 0 || columnas <= 0) {
            throw new Exception("valor negativo en el tama\361o de la matriz");
        }
        double mat2[][] = new double[filas][columnas];
        int k = 0;
        int MAX = mat1.length;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mat2[i][j] = k >= MAX ? fixed : mat1[k];
                k++;
            }
        }
        return mat2;
    }

    public static double[][] parsearMatriz(String matriz) throws Exception {
        String splitFilas[] = matriz.split(";");
        int FILAS = splitFilas.length;
        int COLUMNAS = splitFilas[0].split(",").length;
        double mat[][] = new double[FILAS][COLUMNAS];
        for (int i = 0; i < splitFilas.length; i++) {
            String splitColumnas[] = splitFilas[i].split(",");
            for (int j = 0; j < splitColumnas.length; j++) {
                try {
                    Double d = Double.valueOf(splitColumnas[j]); 
                    mat[i][j] = d.doubleValue();
                } catch (Exception e) {
                    throw new Exception((new StringBuilder()).append("Excepcion al parsear el String: ").append(e.getMessage()).toString());
                }
            }
        }
        return mat;
    }

    public static Object[] filtrarRepetidos(Object array[]) {
        int TAM = array.length;
        ArrayList lista = new ArrayList(TAM);
        for (int i = TAM - 1; i >= 0; i--) {
            if (!lista.contains(array[i])) {
                lista.add(array[i]);
            }
        }
        return lista.toArray();
    }

    public static int[] filtrarRepetidos(int array[]) {
        int TAM = array.length;
        int c = 0;
        int lista[] = new int[c];
        for (int i = 0; i < TAM; i++) {
            if (Arrays.binarySearch(lista, array[i]) < 0) {
                lista = Arrays.copyOf(lista, ++c);
                lista[c - 1] = array[i];
            }
        }
        return lista;
    }

    public double getDoubleWhitPrecision(double valor, int precision) {
        return valor;
    }

}
