package org.dz;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LuisR
 */
public final class Imagenes {

    public Imagenes() {
    }
    
    public static BufferedImage toBuffereredImage(final Image imagen) {
        BufferedImage bi = null;
        try {
            bi = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), 2);
            final Graphics2D g = bi.createGraphics();
            g.drawImage(imagen, 0, 0, null);
        }
        catch (Exception e) {
            bi = new BufferedImage(100, 100, 2);
            final Graphics2D g2 = bi.createGraphics();
            g2.drawString("Error", 20, 45);
        }
        return bi;
    }
    
    public static byte[] bufferedImageToArrayBytes(final BufferedImage image) {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            final byte[] imagenByte = baos.toByteArray();
            baos.close();
            return imagenByte;
        }
        catch (IOException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Image rotar(Image img, int w, int h, int angulo) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        double rad = Math.toRadians(angulo);
        g.drawImage(img, AffineTransform.getRotateInstance(rad), null);
        return imagen;
    }

    public static Image pintarColorDG(int w, int h, Color color1, Color color2, int orientacion, int velocidad) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        int xP[] = {4, w - 5, w - 5, 4};
        int yP[] = {4, 4, h - 5, h - 5};
        int x2P[] = {7, w - 8, w - 8, 7};
        int y2P[] = {7, 7, h - 8, h - 8};
        int vR = color1.getRed();
        int vG = color1.getGreen();
        int vB = color1.getBlue();
        int vA = color1.getAlpha();
        int dif1 = vR - color2.getRed();
        int dif2 = vG - color2.getGreen();
        int dif3 = vB - color2.getBlue();
        int dif4 = vA - color2.getAlpha();
        int aux[] = {Math.abs(dif1), Math.abs(dif2), Math.abs(dif3), Math.abs(dif4)};
        if (Utiles.eliminaCeros(aux) != null) {
            aux = Utiles.eliminaCeros(aux);
        }
        int minimo = Utiles.minimo(aux);
        if (minimo == 0) {
            minimo = 1;
        }
        float df1 = dif1 / minimo;
        float df2 = dif2 / minimo;
        float df3 = dif3 / minimo;
        float df4 = dif4 / minimo;
        for (int i = 0; i < minimo; i++) {
            vR += Math.round(df1 * -1F);
            vG += Math.round(df2 * -1F);
            vB += Math.round(df3 * -1F);
            vA += Math.round(df4 * -1F);
            g.setColor(new Color(vR, vG, vB, vA));
            if (velocidad > w) {
                velocidad = w;
            }
            if (orientacion == 2) {
                g.fillRect(0, Math.round(i * (h / velocidad)), w, h);
                continue;
            }
            if (orientacion == 1) {
                g.fillRect(Math.round(i * (w / velocidad)), 0, w, h);
            }
        }
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(3F));
        g.drawPolygon(xP, yP, xP.length);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(1.5F));
        g.drawPolygon(x2P, y2P, x2P.length);
        return imagen;
    }

    public static Image pintarColorDG(int w, int h,Color color1, Color color2, int orientacion, int velocidad, String titulo) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        int t = titulo.length();
        int tFuente = 12;
        Font fTit = new Font("Arial", 1, 12);
        int tReslat = t * 12;
        int tA = 14;
        int tL = (int) ((double) w * 0.80000000000000004D);
        int iT = (int) ((double) w * 0.10000000000000001D);
        int lT = (int) ((double) w * 0.080000000000000002D);
        int xP[] = {4, w - 4, w - 4, 4};
        int yP[] = {4 + (tA + 3), 4 + (tA + 3), h - 4, h - 4};
        int x2P[] = {6, w - 7, w - 7, 6};
        int y2P[] = {6 + (tA + 3), 6 + (tA + 3), h - 7, h - 7};
        
        int vR = color1.getRed();
        int vG = color1.getGreen();
        int vB = color1.getBlue();
        int vA = color1.getAlpha();
        int dif1 = vR - color2.getRed();
        int dif2 = vG - color2.getGreen();
        int dif3 = vB - color2.getBlue();
        int dif4 = vA - color2.getAlpha();
        int aux[] = {Math.abs(dif1), Math.abs(dif2), Math.abs(dif3), Math.abs(dif4)};
        if (Utiles.eliminaCeros(aux) != null) {
            aux = Utiles.eliminaCeros(aux);
        }
        int minimo = Utiles.minimo(aux);
        if (minimo == 0) {
            minimo = 1;
        }
        float df1 = dif1 / minimo;
        float df2 = dif2 / minimo;
        float df3 = dif3 / minimo;
        float df4 = dif4 / minimo;
        for (int i = 0; i < minimo; i++) {
            vR += Math.round(df1 * -1F);
            vG += Math.round(df2 * -1F);
            vB += Math.round(df3 * -1F);
            vA += Math.round(df4 * -1F);
            g.setColor(new Color(vR, vG, vB, vA));
            if (orientacion == 2) {
                g.fillRect(0, Math.round(i * (h / velocidad)), w, h);
                continue;
            }
            if (orientacion == 1) {
                g.fillRect(Math.round(i * (w / velocidad)), 0, w, h);
            }
        }
        g.setColor(Color.white);
        g.setStroke(new BasicStroke(2.5F));
        g.drawPolygon(xP, yP, xP.length);
        g.fillRect(4, 3, w - 8, tA);
        g.setColor(Color.orange);
        g.setStroke(new BasicStroke(1.4F));
        g.drawPolygon(x2P, y2P, x2P.length);
        g.setColor(Color.white);
        g.setColor(new Color(255, 255, 255, 200));
        return imagen;
    }

    public static Image pintarCirculos(int w, int h, int espacio) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        if (espacio < 0) {
            espacio = 1;
        }
        if (espacio > 10) {
            espacio = 10;
        }
        int m = Math.max(w + 10, h + 10);
        int xc = w / 2;
        int yc = h / 2;
        int lim = m / 2 / espacio;
        g.setStroke(new BasicStroke(1.5F));
        g.setBackground(new Color(0, 255, 0, 90));
        for (int i = 1; i < lim; i++) {
            g.setColor(Utiles.colorAleatorio(0, 255));
            g.drawOval(xc - espacio * i, yc - espacio * i, i * espacio * 2, i * espacio * 2);
            g.drawRect(xc - espacio * i, yc - espacio * i, i * espacio * 2, i * espacio * 2);
        }
        for (int i = 1; i < 50; i++) {
            g.drawPolygon(Utiles.poligonoAleat(10, 500, 400));
        }
        return imagen;
    }

    public static Image centrarTexto(int w, int h, String s, Font f, Color fondo, Color letra) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        if (s == null) {
            s = "";
        }
        String split[] = s.split(" ");
        int n = split.length;
        int nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = split[i].length();
        }
        int mayor = Utiles.indMaximo(nums);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(f);
        g.setColor(fondo);
        g.fillRect(0, 0, w, h);
        java.awt.font.FontRenderContext frc = g.getFontRenderContext();
        LineMetrics lm = g.getFont().getLineMetrics(s, frc);
        float height = lm.getHeight();
        float ds = lm.getDescent();
        float e = ((float) h - height * (float) n) / (float) (n + 1);
        Rectangle2D bounds = g.getFont().getStringBounds(split[mayor], frc);
        float width = (float) bounds.getWidth();
        do {
            if ((double) (height * (float) n) <= (double) h * 0.80000000000000004D && width <= (float) w) {
                break;
            }
            f = new Font(f.getFontName(), f.getStyle(), f.getSize() - 1);
            g.setFont(f);
            frc = g.getFontRenderContext();
            lm =  g.getFont().getLineMetrics(s, frc);
            height = lm.getHeight();
            ds = lm.getDescent();
            e = ((float) h - height * (float) n) / (float) (n + 1);
            bounds = g.getFont().getStringBounds(split[mayor], frc);
            width = (float) bounds.getWidth();
        } while (f.getSize() != 5);
        for (int i = 0; i < split.length; i++) {
            bounds = g.getFont().getStringBounds(split[i], frc);
            width = (float) bounds.getWidth();
            float x = ((float) w - width) / 2.0F;
            g.setColor(Color.black);
            g.drawString(split[i], x - width * 0.01F, (((float) (i + 1) * e + (float) (i + 1) * height) - ds) + height * 0.05F);
            g.setColor(letra);
            g.drawString(split[i], x, ((float) (i + 1) * e + (float) (i + 1) * height) - ds);
        }
        return imagen;
    }

    public static Image pintarBorde(int w, int h, Color c1, Color c2) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        int xP[] = {4, w - 5, w - 5, 4};
        int yP[] = {4, 4, h - 5, h - 5};
        int x2P[] = {7, w - 8, w - 8, 7};
        int y2P[] = {7, 7, h - 8, h - 8};
        g.setColor(c1);
        g.setStroke(new BasicStroke(3F));
        g.drawPolygon(xP, yP, xP.length);
        g.setColor(c2);
        g.setStroke(new BasicStroke(1.5F));
        g.drawPolygon(x2P, y2P, x2P.length);
        return imagen;
    }

    public static int calcularLargoTMinimo(String s, Font f, int largoMin) {
        boolean band = false;
        Font fuente = f;
        int tam = f.getSize();
        Image imagen = new BufferedImage(1, 1, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        do {
            g.setFont(fuente);
            java.awt.font.FontRenderContext frc = g.getFontRenderContext();
            Rectangle2D bounds = g.getFont().getStringBounds(s, frc);
            float width = (float) bounds.getWidth();
            if (width > (float) largoMin) {
                fuente = new Font(f.getFontName(), f.getStyle(), tam--);
                band = true;
            } else {
                band = false;
            }
        } while (band);
        return tam;
    }

    public static void splitCadenaLimitada() {
        Image imagen = new BufferedImage(1, 1, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
    }

    public static Image centrarTextoLim(int w, int h, String s, Font f, int tamFuenteMin, Color fondo, Color letra) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        String linea[] = new String[2];
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(f);
        java.awt.font.FontRenderContext frc = g.getFontRenderContext();
        LineMetrics lm =  g.getFont().getLineMetrics(s, frc);
        g.setColor(fondo);
        g.fillRect(0, 0, w, h);
        linea = partirCadena(s, 50);
        Rectangle2D bounds = g.getFont().getStringBounds(linea[0], frc);
        float width = (float) bounds.getWidth();
        float height = lm.getHeight();
        float ds = lm.getDescent();
        float e = ((float) h - height * 2.0F) / 3F;
        for (int i = 0; i < linea.length; i++) {
            bounds = g.getFont().getStringBounds(linea[i], frc);
            width = (float) bounds.getWidth();
            float x = ((float) w - width) / 2.0F;
            g.setColor(Color.black);
            g.drawString(linea[i], x - width * 0.01F, (((float) (i + 1) * e + (float) (i + 1) * height) - ds) + height * 0.05F);
            g.setColor(letra);
            g.drawString(linea[i], x, ((float) (i + 1) * e + (float) (i + 1) * height) - ds);
        }
        return imagen;
    }

    public static String[] partirCadena(String s, Font f, int w, int tamFuenteMin) {
        boolean band = true;
        String linea[] = new String[2];
        linea[0] = s;
        linea[1] = "";
        String temp = "";
        String token = " ";
        do {
            if (calcularLargoTMinimo(s, f, w) < tamFuenteMin) {
//                System.out.println((new StringBuilder()).append(calcularLargoTMinimo(s, f, w)).append(" <-> ").append(tamFuenteMin).toString());
                int ind = s.lastIndexOf(token);
                linea[0] = s.substring(0, ind);
                temp = linea[1];
                linea[1] = (new StringBuilder()).append(s.substring(ind + 1)).append(" ").append(temp).toString();
                s = linea[0];
                band = true;
            } else {
                band = false;
            }
        } while (band);
        return linea;
    }

    public static String[] partirCadena(String s, int tMax) {
        boolean band = true;
        String linea[] = new String[2];
        linea[0] = s;
        linea[1] = "";
        String temp = "";
        String token = " ";
        do {
            if (s.length() > tMax) {
                int ind = s.lastIndexOf(token);
                linea[0] = s.substring(0, ind);
                temp = linea[1];
                linea[1] = (new StringBuilder()).append(s.substring(ind + 1)).append(" ").append(temp).toString();
                s = linea[0];
                band = true;
            } else {
                band = false;
            }
        } while (band);
        return linea;
    }

    public static int[] tamaF1o2DCadena(String s, Font f) {
        Image imagen = new BufferedImage(10, 10, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(f);
        java.awt.font.FontRenderContext frc = g.getFontRenderContext();
        LineMetrics lm =  g.getFont().getLineMetrics(s, frc);
        float bot = lm.getLeading() + lm.getDescent();
        Rectangle2D bounds = g.getFont().getStringBounds(s, frc);
        return (new int[]{(int) bounds.getWidth(), (int) bounds.getHeight(), (int) bot});
    }

    private static Image imagenArrayCaracteres(int w, int h, Font f, char c) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(f);
        java.awt.font.FontRenderContext frc = g.getFontRenderContext();
        LineMetrics lm =  g.getFont().getLineMetrics("0", frc);
        Rectangle2D bounds = g.getFont().getStringBounds("0", frc);
        g.setColor(new Color(20, 0, 0, 50));
        g.fillRect(0, 0, w, h);
        g.setColor(Color.green);
        for (int i = 0; i < h; i = (int) ((double) i + bounds.getHeight())) {
            char cs = Utiles.aleatorio(0, 1) != 0 ? '1' : '0';
            g.drawString((new StringBuilder()).append(cs).append("").toString(), 0, i);
        }
        return imagen;
    }

    public static Image imagenMatrix(int w, int h, Font f) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        for (int i = 0; i < 20; i++) {
            g.drawImage(imagenArrayCaracteres(10, h, f, 'a'), (w / 20) * i, 0, null);
        }
        for (int i = 0; i < 30; i++) {
            int x = Utiles.aleatorio(0, w);
            int y = Utiles.aleatorio(0, h / 2);
            int a = Utiles.aleatorio(0, 10);
            Image imgSc = imagenArrayCaracteres(10, h, f, 'a').getScaledInstance(10 + a, h + a, 16);
            g.drawImage(imgSc, x, y, null);
        }
        return imagen;
    }

    public static Image centrarTextoLim(int w, int h, String s, Font f, Color fondo, Color letra, int espacio, int maximo, boolean shading) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        String linea[] = null;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(f);
        java.awt.font.FontRenderContext frc = g.getFontRenderContext();
        g.setColor(fondo);
        g.fillRect(0, 0, w, h);
        linea = partirCadena(s, maximo);
        int largo = w * 1;
        int alto = h * 1;
        int mx = w - largo;
        int my = h - alto;
        for (int i = 0; i < linea.length; i++) {
            linea[i] = Utiles.eliminarAcentos(linea[i]);
            LineMetrics lm =  g.getFont().getLineMetrics(linea[i], frc);
            Rectangle2D bounds = g.getFont().getStringBounds(linea[i], frc);
            float largoCadena = (float) bounds.getWidth();
            float alturaCadena = (float) bounds.getHeight() - lm.getLeading();
            if (linea.length == 1) {
                espacio *= -1;
            }
            if (shading) {
                g.setColor(Color.black);
                g.drawString(linea[i], (float) mx + ((float) (largo / 2) - largoCadena / 2.0F), (int) ((float) (my + espacio * (i + 1)) + alturaCadena * (float) (i + 1)));
            }
            g.setColor(letra);
            g.drawString(linea[i], (float) mx + ((float) (largo / 2) - largoCadena / 2.0F) + 1.0F, (int) ((float) (my + espacio * (i + 1)) + alturaCadena * (float) (i + 1)) + 1);
        }
        return imagen;
    }

    public static Image imagenSpectrum(int w, int h, int nBandas, double datos[]) {
        Image imagen = new BufferedImage(w, h, 2);
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, w, h);
        g.translate(0, h);
        int largoBanda = (w - (nBandas + 2)) / nBandas;
        int espBanda = 1;
        double max = Utiles.maximo(datos);
        double min = Utiles.minimo(datos);
        double rango = Math.abs(max - min);
        System.out.println("imagenSpectrum->");
        for (int i = 0; i < nBandas; i++) {
            int hb = (int) (Math.sin(Math.toRadians(datos[i])) * (double) h);
            g.setColor(Utiles.colorAleatorio(0, 255));
            g.fillRect(espBanda * (i + 1) + largoBanda * i, -hb, largoBanda, hb);
            System.out.println((new StringBuilder()).append("->").append(datos[i]).append(" (").append(hb).append(")").toString());
        }
        return imagen;
    }

    public static Image imagenToGray(Image imagen, String nombre) {
        if (imagen == null) {
            return null;
        } else {
            int w = imagen.getWidth(null);
            int h = imagen.getHeight(null);
            Graphics2D g = (Graphics2D) imagen.getGraphics();
            g.setColor(new Color(128, 128, 128));
            g.setComposite(AlphaComposite.getInstance(6, 0.2F));
            g.fillRect(0, 0, w, h);
            g.setColor(Color.red);
            g.setFont(new Font("arial", 1, 15));
            g.drawString(nombre, 1, h - 20);
            return imagen;
        }
    }

    public static BufferedImage labelImagen(BufferedImage imagen, String nombre) {
        int w = imagen.getWidth(null);
        int h = imagen.getHeight(null);
        int ofs = 1;
        int ajs = 12;
        Graphics2D g = (Graphics2D) imagen.getGraphics();
        Font f = new Font("Arial", 0, 11);
        g.setFont(f);
        int d[] = tamaF1o2DCadena(nombre, f);
        int px = w / 2 - d[0] / 2;
        int py = h - (d[1] + ajs);
        g.setColor(Color.black);
        g.drawString(nombre, px, (py + d[1]) - d[2] - 1);
        g.setColor(Color.white);
        g.drawRect(px - ofs, py - 2 * ofs, d[0] + 2 * ofs, d[1] + 2 * ofs);
        g.drawString(nombre, px + 1, (py + d[1]) - d[2]);
        return imagen;
    }

    public static Image reflejarImagen(BufferedImage src) {
        int h = src.getHeight();
        BufferedImage dst = new BufferedImage(src.getWidth(), h * 2, 2);
        Graphics2D g = dst.createGraphics();
        g.drawImage(src, 0, 0, null);
        g.scale(1.0D, -1D);
        g.drawImage(src, 0, -h - h, null);
        g.scale(1.0D, -1D);
        g.translate(0, h);
        GradientPaint grd = new GradientPaint(0.0F, 0.0F, new Color(1.0F, 1.0F, 1.0F, 0.5F), 0.0F, h, new Color(1.0F, 1.0F, 1.0F, 0.0F));
        g.setPaint(grd);
        g.setComposite(AlphaComposite.DstIn);
        g.fillRect(0, 0, src.getWidth(), h);
        g.dispose();
        return dst;
    }

    public static HashMap getColores() {
        return colores;
    }

    public static ArrayList coloresArray() {
        Iterator it = colores.values().iterator();
        ArrayList cols = new ArrayList();
        for (; it.hasNext(); cols.add((Color) it.next()));
        return cols;
    }
    
    public static boolean saveImage(BufferedImage image, String format, File file) {
        boolean write = false;
        try {
            write = ImageIO.write(image, format, file);
        } catch (IOException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return write;
    }

    public static final int X_ALIG = 1;
    public static final int Y_ALIG = 2;
    private static int color1[];
    private static int color2[];
    public static final Color ROJO;
    public static final Color AMARILLO;
    public static final Color AZUL;
    public static final Color VERDE;
    public static final Color NARANJA;
    public static final Color MORADO;
    public static final Color MARRON;
    public static final Color BLANCO;
    public static final Color NEGRO;
    public static final Color GRIS;
    public static final Color DORADO;
    public static final Color PLATEADO;
    public static final Color ROSADO;
    public static final Color VERDE_O;
    public static final Color AZUL_O;
    public static final Color VERDE_C;
    public static final Color CAFE;
    public static final Color AZUL_D;
    public static final Color CYAN;
    public static final Color AZUL2;
    private static HashMap colores;

    static {
        ROJO = new Color(255, 0, 0);
        AMARILLO = new Color(255, 255, 0);
        AZUL = new Color(0, 0, 255);
        VERDE = new Color(0, 255, 0);
        NARANJA = new Color(255, 127, 0);
        MORADO = new Color(127, 0, 127);
        MARRON = new Color(127, 0, 0);
        BLANCO = new Color(255, 255, 255);
        NEGRO = new Color(0, 0, 0);
        GRIS = new Color(127, 127, 127);
        DORADO = new Color(240, 230, 110);
        PLATEADO = new Color(176, 176, 176);
        ROSADO = new Color(255, 0, 127);
        VERDE_O = new Color(0, 127, 0);
        AZUL_O = new Color(0, 0, 127);
        VERDE_C = new Color(120, 250, 15);
        CAFE = new Color(200, 100, 0);
        AZUL_D = new Color(20, 0, 100);
        CYAN = new Color(0, 255, 255);
        AZUL2 = new Color(70, 170, 190);
        colores = new HashMap();
        colores.put("ROJO", ROJO);
        colores.put("AMARILLO", AMARILLO);
        colores.put("AZUL", AZUL);
        colores.put("VERDE", VERDE);
        colores.put("NARANJA", NARANJA);
        colores.put("MORADO", MORADO);
        colores.put("MARRON", MARRON);
        colores.put("BLANCO", BLANCO);
        colores.put("NEGRO", NEGRO);
        colores.put("GRIS", GRIS);
        colores.put("DORADO", DORADO);
        colores.put("PLATEADO", PLATEADO);
        colores.put("ROSADO", ROSADO);
        colores.put("VERDE_O", VERDE_O);
        colores.put("AZUL_O", AZUL_O);
        colores.put("VERDE_C", VERDE_C);
        colores.put("CAFE", CAFE);
        colores.put("AZUL_D", AZUL_D);
        colores.put("CYAN", CYAN);
        colores.put("AZUL2", AZUL2);
    }
}
