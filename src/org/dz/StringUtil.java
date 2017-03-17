package org.dz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LuisR
 */
public class StringUtil {

    public static void randomString() {
        Random r = new Random();
        int n = r.nextInt(10);

    }

    public static ArrayList<String> readFile(String path) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        ArrayList<String> lista = new ArrayList<>();
        Charset inputCharset = Charset.forName("ISO-8859-1");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), inputCharset));
        String str;
        while ((str = in.readLine()) != null) {
            lista.add(str);
        }
        return lista;
    }
    
    public static ArrayList splitArchivoEnLineas(File archivo, boolean skipLineasVacias)
            throws FileNotFoundException, IOException {
        ArrayList lineas = new ArrayList();
        FileInputStream fis = new FileInputStream(archivo);
        InputStreamReader fr = new InputStreamReader(fis, "UTF-8");
        BufferedReader bfReader = new BufferedReader(fr);
        String linea = null;
        do {
            linea = bfReader.readLine();
            if (!skipLineasVacias) {
                lineas.add(linea);
            } else if (linea != null && !linea.isEmpty()) {
                lineas.add(linea);
            }
        } while (linea != null);
        return lineas;
    }

    public static ArrayList splitArchivoEnLineas(String archivo, boolean skipLineasVacias)
            throws FileNotFoundException, IOException {
        return splitArchivoEnLineas(new File(archivo), skipLineasVacias);
    }
    
    public static String getNumeroFormateado(int num, int pos) {
        int l = org.dzur.Mat.getCifras(num).length;
        if (l >= pos) {
            return String.valueOf(num);
        } else {
            String form = "";
            for (int i = 0; i < pos - l; i++) {
                form += "0";
            }
            return form + num;
        }
    }
    
    public static String[] separarNombre(String nombre) {
        String[] split = nombre.split(" ");
        String nuevoNombre = "";
        for (int i = 0; i < split.length; i++) {
            String string = split[i];
            switch (string.toLowerCase()) {
                case "de":
                case "del":
                case "la":
                case "las":
                case "los":
                case "san":
                    nuevoNombre += string + " ";
                    break;
                default:
                    nuevoNombre += string + "@";
            }
        }

        if (nuevoNombre.charAt(nuevoNombre.length() - 1) == '@') {
            nuevoNombre = nuevoNombre.substring(0, nuevoNombre.length() - 1);
        }
        return nuevoNombre.split("@");
    }

}
