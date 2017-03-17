package org.dz;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.balx.Matriz;
import org.balx.Punto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LuisR
 */
public class Utiles {

    public Utiles() {
    }

    public static int aleatorio(int min, int max) {
        int intv = max - min;
        return min + (int) (Math.random() * (double) (intv + 1));
    }

    public static int esPar(int num) {
        return num % 2 != 0 ? 1 : 0;
    }

    public static String removeCaracterNull(String s) {
        String sal = "";
        if (s.charAt(s.length() - 1) == 0) {
            sal = s.substring(0, s.length() - 1);
        }
        return sal;
    }

    public static String formatearReal(double numero, int decimales) {
        String sal = "";
        sal = Double.toString(numero);
        int i = 0;
        do {
            if (i >= sal.length()) {
                break;
            }
            if (sal.codePointAt(i) == 46) {
                sal = sal.substring(0, i + decimales + 1);
                break;
            }
            i++;
        } while (true);
        return sal;
    }

    public static int contarCaracteres(String cadena, char caracter) {
        int num = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == caracter) {
                num++;
            }
        }
        return num;
    }

    public static boolean esDigito(char caracter) {
        return caracter >= '0' && caracter <= '9';
    }

    public static boolean esDosPuntos(char caracter) {
        return caracter == ':';
    }

    public static boolean esComa(char caracter) {
        return caracter == ',';
    }

    public static int minimo(int nums[]) {
        if (nums.length >= 0) {
            int min = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (min > nums[i]) {
                    min = nums[i];
                }
            }
            return min;
        } else {
            return 0;
        }
    }

    public static double minimo(double nums[]) {
        if (nums.length >= 0) {
            double min = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (min > nums[i]) {
                    min = nums[i];
                }
            }
            return min;
        } else {
            return 0.0D;
        }
    }

    public static int maximo(int nums[]) {
        if (nums.length >= 0) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (max < nums[i]) {
                    max = nums[i];
                }
            }
            return max;
        } else {
            return 0;
        }
    }

    public static double maximo(double nums[]) {
        if (nums.length >= 0) {
            double max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (max < nums[i]) {
                    max = nums[i];
                }
            }
            return max;
        } else {
            return 0.0D;
        }
    }

    public static int indMaximo(int nums[]) {
        int max = 0;
        int ind = 0;
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                ind = i;
            }
        }
        return ind;
    }

    public static int[] eliminaCeros(int nums[]) {
        ArrayList tmp = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                tmp.add(Integer.valueOf(nums[i]));
            }
        }
        if (!tmp.isEmpty()) {
            int ncero[] = new int[tmp.size()];
            for (int i = 0; i < tmp.size(); i++) {
                ncero[i] = ((Integer) tmp.get(i)).intValue();
            }
            return ncero;
        } else {
            return null;
        }
    }

    public static Color colorAleatorio(int min, int max) {
        if (min >= 0 && max <= 255) {
            return new Color(aleatorio(min, max), aleatorio(min, max), aleatorio(min, max));
        } else {
            return Color.white;
        }
    }

    public static Color listColorAleatorio(int cantidad, int min, int max) {
        int cont = 0;
        if (min >= 0 && max <= 255 && cantidad > 0) {
            while (cont < cantidad) ;
            return new Color(aleatorio(min, max), aleatorio(min, max), aleatorio(min, max));
        } else {
            return Color.white;
        }
    }

    public static Color colorAleatorioAlpha(int min, int max) {
        if (min >= 0 && max <= 255) {
            return new Color(aleatorio(min, max), aleatorio(min, max), aleatorio(min, max), aleatorio(min, max));
        } else {
            return Color.white;
        }
    }

    public static void imprimirMatriz(int mat[][]) {
        System.out.println("------------------------------------------");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print((new StringBuilder()).append(mat[i][j]).append(" ").toString());
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");
    }

    public static void imprimirArray(int array[]) {
        System.out.print((new StringBuilder()).append(array).append(" -> [ ").toString());
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            String s = i != array.length - 1 ? ", " : " ";
            System.out.print(s);
        }
        System.out.println("]");
    }

    public static void imprimirMatriz(double mat[][]) {
        System.out.println("------------------------------------------");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print((new StringBuilder()).append(mat[i][j]).append(" ").toString());
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");
    }

    public static int[][] llenarMatriz(int mat[][], int num) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = num;
            }
        }
        return mat;
    }

    public static void llenarMatrizRf(int mat[][], int num) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = num;
            }
        }
    }

    public static int[] llenarArray(int mat[], int num) {
        for (int i = 0; i < mat.length; i++) {
            mat[i] = num;
        }
        return mat;
    }

    public static int[] llenarArrayAleatoria(int array[], int min, int max) {
        for (int i = 0; i < array.length; i++) {
            array[i] = aleatorio(min, max);
        }
        return array;
    }

    public static int[][] matrizRotada180(int mat[][]) {
        int f = mat.length;
        int c = mat[0].length;
        int mat2[][] = new int[f][c];
        for (int i = 0; i < mat.length; i++) {
            mat2[i] = mat[mat.length - 1 - i];
        }
        return mat2;
    }

    public static Object[][] matrizRotada180(Object mat[][]) {
        int f = mat.length;
        int c = mat[0].length;
        Object mat2[][] = new Object[f][c];
        for (int i = 0; i < mat.length; i++) {
            mat2[i] = mat[mat.length - 1 - i];
        }
        return mat2;
    }

    public static int[][] llenarMatrizAleatoria(int matriz[][], int min, int max) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = aleatorio(min, max);
            }
        }
        return matriz;
    }

    public static Polygon poligonoAleat(int maxLados, int tamMaxX, int tamMaxY) {
        Polygon p = new Polygon();
        int n = aleatorio(3, maxLados);
        for (int i = 0; i < n; i++) {
            p.addPoint(aleatorio(1, tamMaxX), aleatorio(1, tamMaxY));
        }
        return p;
    }

    public static String fecha() {
        Date dt = new Date();
        DateFormat form = DateFormat.getDateTimeInstance();
        String fecha = form.format(dt);
        return fecha;
    }

    public static String hora() {
        GregorianCalendar gc = new GregorianCalendar();
        String sal = "";
        int hora = gc.get(10);
        int minuto = gc.get(12);
        int segundo = gc.get(13);
        sal = (new StringBuilder()).append(sal).append(gc.get(10) >= 10 ? (new StringBuilder()).append(hora).append(":").toString() : (new StringBuilder()).append("0").append(hora).append(":").toString()).toString();
        sal = (new StringBuilder()).append(sal).append(minuto >= 10 ? (new StringBuilder()).append(minuto).append(":").toString() : (new StringBuilder()).append("0").append(minuto).append(":").toString()).toString();
        sal = (new StringBuilder()).append(sal).append(segundo >= 10 ? ((Object) (Integer.valueOf(segundo))) : ((Object) ((new StringBuilder()).append("0").append(segundo).append(",").toString()))).toString();
        return sal;
    }

    public static Punto rotarPunto(Punto p, double angulo) {
        Punto ps = p;
        Matriz rot = Matriz.matrizIdentidad(3);
        rot.setDato(0, 0, Math.cos(angulo));
        rot.setDato(0, 1, Math.sin(angulo));
        rot.setDato(1, 0, -Math.sin(angulo));
        rot.setDato(1, 1, Math.cos(angulo));
        double mat[][] = {{ps.x}, {ps.y}, {0.0D}};
        Matriz punto = new Matriz(mat);
        Matriz sal = Matriz.producto(rot, punto);
        System.out.println(sal);
        ps.x = Math.round(sal.getDato(0, 0));
        ps.y = Math.round(sal.getDato(1, 0));
        return ps;
    }

    public static Punto rotarPunto(Punto p, Punto center, double angulo) {
        Punto ps = p;
        ps.trasladar(center.x, center.y);
        Matriz rot = Matriz.matrizIdentidad(3);
        rot.setDato(0, 0, Math.cos(angulo));
        rot.setDato(0, 1, Math.sin(angulo));
        rot.setDato(1, 0, -Math.sin(angulo));
        rot.setDato(1, 1, Math.cos(angulo));
        double mat[][] = {{ps.x}, {ps.y}, {0.0D}};
        Matriz punto = new Matriz(mat);
        Matriz sal = Matriz.producto(rot, punto);
        System.out.println(sal);
        ps.x = Math.round(sal.getDato(0, 0));
        ps.y = Math.round(sal.getDato(1, 0));
        ps.trasladar(-center.x, -center.y);
        return ps;
    }

    public static ArrayList leerTexto(String ruta) throws FileNotFoundException {
        return leerTexto(new File(ruta));
    }

    public static ArrayList leerTexto(File archivo) throws FileNotFoundException {
        ArrayList lista = new ArrayList<Object>(1000);
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader bfr = new BufferedReader(fr);
            String s = "";
            do {
                s = bfr.readLine();
                if (s != null) {
                    lista.add(s);
                }
            } while (s != null);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public static void escribirTexto(String ruta, ArrayList lista) {
        try {
            FileWriter fw = new FileWriter(ruta);
            BufferedWriter bfw = new BufferedWriter(fw);
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i) != null) {
                    bfw.write((String) lista.get(i));
                    bfw.newLine();
                }
            }
            bfw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String eliminarEntre(String cad, char inicio, char fin) {
        String sal = cad;
        if (cad.indexOf(fin) > 0 && cad.indexOf(inicio) > 0) {
            sal = cad.replace(cad.substring(cad.indexOf(inicio), cad.indexOf(fin) + 1), "");
        }
        return sal;
    }

    public static String eliminarEspacAlFin(String cad) {
        String sal = cad;
        for (char c = sal.charAt(sal.length() - 1); c == ' '; c = sal.charAt(sal.length() - 1)) {
            sal = sal.substring(0, sal.length() - 1);
        }
        return sal;
    }

    public static Point centrar(Dimension fs, Dimension ss) {
        return new Point(ss.width / 2 - fs.width / 2, ss.height / 2 - fs.height / 2);
    }

    public static String formatearMilis(long tiempo) {
        long rhora = 0L;
        long rminuto = 0L;
        long rsegundo = 0L;
        int hora = 0;
        int minuto = 0;
        int segundo = 0;
        int milisegundo = 0;
        hora = (int) (tiempo / 0x36ee80L);
        rhora = tiempo % 0x36ee80L;
        minuto = (int) (rhora / 60000L);
        rminuto = rhora % 60000L;
        segundo = (int) (rminuto / 1000L);
        rsegundo = rminuto % 1000L;
        milisegundo = (int) rsegundo;
        String sal = "";
        sal = (new StringBuilder()).append(sal).append(hora >= 10 ? (new StringBuilder()).append(hora).append(":").toString() : (new StringBuilder()).append("0").append(hora).append(":").toString()).toString();
        sal = (new StringBuilder()).append(sal).append(minuto >= 10 ? (new StringBuilder()).append(minuto).append(":").toString() : (new StringBuilder()).append("0").append(minuto).append(":").toString()).toString();
        sal = (new StringBuilder()).append(sal).append(segundo >= 10 ? (new StringBuilder()).append(segundo).append(",").toString() : (new StringBuilder()).append("0").append(segundo).append(",").toString()).toString();
        sal = (new StringBuilder()).append(sal).append(milisegundo >= 100 ? ((Object) (Integer.valueOf(milisegundo))) : ((Object) (milisegundo >= 10 ? ((Object) ((new StringBuilder()).append("0").append(milisegundo).toString())) : ((Object) ((new StringBuilder()).append("00").append(milisegundo).toString()))))).toString();
        return sal;
    }

    public static String formatearMilis2(long tiempo) {
        long rhora = 0L;
        long rminuto = 0L;
        long rsegundo = 0L;
        int hora = 0;
        int minuto = 0;
        int segundo = 0;
        int milisegundo = 0;
        hora = (int) (tiempo / 0x36ee80L);
        rhora = tiempo % 0x36ee80L;
        minuto = (int) (rhora / 60000L);
        rminuto = rhora % 60000L;
        segundo = (int) (rminuto / 1000L);
        rsegundo = rminuto % 1000L;
        milisegundo = (int) rsegundo;
        String sal = "";
        if (hora > 0) {
            sal = (new StringBuilder()).append(sal).append(hora >= 10 ? (new StringBuilder()).append(hora).append(":").toString() : (new StringBuilder()).append("0").append(hora).append(":").toString()).toString();
        }
        sal = (new StringBuilder()).append(sal).append(minuto >= 10 ? (new StringBuilder()).append(minuto).append(":").toString() : (new StringBuilder()).append("0").append(minuto).append(":").toString()).toString();
        sal = (new StringBuilder()).append(sal).append(segundo >= 10 ? (new StringBuilder()).append(segundo).append("").toString() : (new StringBuilder()).append("0").append(segundo).append("").toString()).toString();
        return sal;
    }

    public static String eliminarAcentos(String texto) {
        String sal = texto;
        sal = sal.replace('\340', 'a');
        sal = sal.replace('\342', 'a');
        sal = sal.replace('\344', 'a');
        sal = sal.replace('\347', 'c');
        sal = sal.replace('\341', 'a');
        sal = sal.replace('\350', 'e');
        sal = sal.replace('\354', 'i');
        sal = sal.replace('\362', 'o');
        sal = sal.replace('\371', 'u');
        sal = sal.replace('\300', 'A');
        sal = sal.replace('\310', 'E');
        sal = sal.replace('\314', 'I');
        sal = sal.replace('\322', 'O');
        sal = sal.replace('\331', 'U');
        sal = sal.replace('\310', 'E');
        sal = sal.replace('\311', 'E');
        sal = sal.replace('\312', 'E');
        sal = sal.replace('\300', 'A');
        sal = sal.replace('\304', 'A');
        sal = sal.replace('\307', 'C');
        sal = sal.replace('\331', 'U');
        sal = sal.replace('\334', 'U');
        sal = sal.replace('\317', 'I');
        return sal;
    }
    
    public static String toHex(Color color) {
        return "#" + toBrowserHexValue(color.getRed()) + toBrowserHexValue(color.getGreen()) + toBrowserHexValue(color.getBlue());
    }

    public static String toHex(int r, int g, int b) {
        return "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
    }

    private static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }
    
    public static boolean crearDirectorio(Path path) {

        boolean creado = false;
        if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(path);
                path = path.toAbsolutePath();
                System.out.println("\n" + path + " directorio creado.");
                return true;
            } catch (NoSuchFileException e) {
                creado = false;
                System.err.println("\nDirectory creation failed:\n" + e);
            } catch (FileAlreadyExistsException e) {
                creado = false;
                System.err.println("\nDirectory creation failed:\n" + e);
            } catch (IOException e) {
                creado = false;
                System.err.println("\nDirectory creation failed:\n" + e);
            }
        }
        return creado;

    }

    
     private static boolean isAñoBisiesto(int año) {
        return (año % 4 == 0) && (año % 100 != 0) || (año % 400 == 0);
    }
     
     /**
     * Verifica que una fecha en formato dd-mm-aaaa sea valida
     *
     * @param formDate
     */
    public static boolean verifyDate(String formDate) {
        int dia = 0, mes = 0, año = 0;
        String[] split = formDate.split("-");
        if (split.length < 3) {
            return false;
        } else {
            try {
                dia = Integer.parseInt(split[0]);
                mes = Integer.parseInt(split[1]);
                año = Integer.parseInt(split[2]);
//                System.err.println(dia+"-"+mes+"-"+año);
            } catch (NumberFormatException e) {
                return false;
            }
            if ((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (año < 1 || año > 3000)) {
                return false;
            } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                return dia <= 31;
            } else if (mes == 2) {
                if (isAñoBisiesto(año)) {  // febrero
                    return dia <= 29;
                } else {
                    return dia <= 28;
                }
            } else {  // Meses de 30 dias
                return dia <= 30;
            }

        }
    }
}
