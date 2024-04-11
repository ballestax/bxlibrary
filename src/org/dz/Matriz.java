/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

/**
 *
 * @author lrod
 */
public class Matriz {
    
    private int filas;
    private int columnas;
    private double[][] matriz;
    
    public Matriz() {
        this.matriz = new double[1][1];
        this.inicializar(0.0);
    }
    
    public Matriz(final double[][] mat) {
        this.filas = mat.length;
        this.columnas = mat[0].length;
        this.matriz = new double[this.filas][this.columnas];
        for (int i = 0; i < this.filas; ++i) {
            for (int j = 0; j < this.columnas; ++j) {
                this.matriz[i][j] = mat[i][j];
            }
        }
    }
    
    public Matriz(final int filas, final int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new double[filas][columnas];
        this.inicializar(0.0);
    }
    
    public Matriz(final int filas, final int columnas, final double valor) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new double[filas][columnas];
        this.inicializar(valor);
    }
    
    public Matriz(final Matriz m) {
        this.filas = m.filas;
        this.columnas = m.columnas;
        this.matriz = new double[this.filas][this.columnas];
        for (int i = 0; i < this.filas; ++i) {
            for (int j = 0; j < this.columnas; ++j) {
                this.matriz[i][j] = m.getDato(i, j);
            }
        }
    }
    
    private void inicializar(final double valor) {
        for (int i = 0; i < this.filas; ++i) {
            for (int j = 0; j < this.columnas; ++j) {
                this.matriz[i][j] = valor;
            }
        }
    }
    
    public void setDato(final int fila, final int columna, final double valor) {
        this.matriz[fila][columna] = valor;
    }
    
    public double getDato(final int fila, final int columna) {
        return this.matriz[fila][columna];
    }
    
    public static Matriz producto(final Matriz a, final Matriz b) {
        final Matriz c = new Matriz(a.filas, b.columnas, 0.0);
        for (int i = 0; i < a.filas; ++i) {
            for (int j = 0; j < b.columnas; ++j) {
                c.setDato(i, j, 0.0);
                for (int k = 0; k < a.columnas; ++k) {
                    final double aux = a.getDato(i, k) * b.getDato(k, j);
                    c.setDato(i, j, c.getDato(i, j) + aux);
                }
            }
        }
        return c;
    }
    
    public static Matriz suma(final Matriz a, final Matriz b) {
        final Matriz c = new Matriz(a.filas, a.columnas, 0.0);
        for (int i = 0; i < a.filas; ++i) {
            for (int j = 0; j < b.columnas; ++j) {
                c.setDato(i, j, a.getDato(i, j) + b.getDato(i, j));
            }
        }
        return c;
    }
    
    @Override
    public String toString() {
        String sal = "";
        for (int i = 0; i < this.filas; ++i) {
            for (int j = 0; j < this.columnas; ++j) {
                sal = sal + this.matriz[i][j] + " ";
            }
            sal += "\n";
        }
        return sal;
    }
    
    public static Matriz matrizIdentidad(final int tam) {
        final Matriz m = new Matriz(tam, tam);
        for (int i = 0; i < tam; ++i) {
            m.setDato(i, i, 1.0);
        }
        return m;
    }
    
    public void triangular(final Matriz a, double dt) {
        int i = 0;
        int j = 0;
        final double[][] mat = a.matriz;
        final int m = a.filas;
        final int n = a.columnas;
        int signo = 1;
        for (i = 1; i < m - 1; ++i) {
            int t = 1;
            if (mat[i][i] == 0.0) {
                do {
                    if (mat[i + t][i] != 0.0) {
                        signo *= -1;
                        for (j = 0; j < n; ++j) {
                            this.intercambiar(mat[i][j], mat[i + t][j]);
                        }
                        Utiles.imprimirMatriz(mat);
                    }
                    ++t;
                } while (mat[i][i] != 0.0 || t == m - i + 1);
            }
            int r = i - 1;
            do {
                ++r;
            } while (mat[i][r] != 0.0 || r == n);
            if (mat[i][r] != 0.0) {
                for (t = i; t < m; ++t) {
                    if (mat[t][r] != 0.0) {
                        final double cs = mat[t][r];
                        for (j = r; j < n; ++j) {
                            mat[t][j] -= mat[i][j] * (cs / mat[i][r]);
                        }
                        Utiles.imprimirMatriz(mat);
                    }
                }
            }
        }
        dt = signo;
        for (i = 1; i < m; ++i) {
            dt *= mat[i][i];
        }
    }
    
    private void intercambiar(double a, double b) {        
        final double aux = a;
        a = b;
        b = aux;
    }
}

