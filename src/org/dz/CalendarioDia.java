package org.dz;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.dzur.ImagenesFx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LuisR
 */
public class CalendarioDia extends Rectangulo implements Comparable {

    public CalendarioDia(int dia) {
        mouseListeners = new ArrayList<>();
        colorFuente = Color.black;
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }

    public void setSeleccionado(boolean seleccionado) {
        super.setSeleccionado(seleccionado);
    }

    public void repaint() {
        if (g != null) {
            paint(g);
        }
    }

    public void paint(Graphics g) {
        this.g = g.create();
        BufferedImage textoImagen = null;
        try {
            textoImagen = ImagenesFx.textoImagen((new StringBuilder()).append(dia).append("").toString(), width, height, isMarcado() ? colorMarca : colorFuente, isSeleccionado() ? colorSeleccionado : colorFondo, new Font("Arial", 1, 16));
        } catch (Exception e) {
            System.err.println((new StringBuilder()).append("exc: ").append(e.getMessage()).toString());
        }
        this.g.drawImage(textoImagen, x, y, null);
    }

    private void calcularPosString(Graphics g) {
        String diaStr = (new StringBuilder()).append("").append(dia).toString();
        FontMetrics fm = g.getFontMetrics(fuente);
        java.awt.geom.Rectangle2D stringBounds = fm.getStringBounds(diaStr, g);
        locString = new Point(x + 3, (y + height) - 3);
    }

    public int compareTo(CalendarioDia o) {
        if (dia > o.dia) {
            return 1;
        }
        return dia >= o.dia ? 0 : -1;
    }

    public void setResaltado(boolean resaltar) {
        resaltado = resaltar;
    }

    public boolean isResaltado() {
        return resaltado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    @Override
    public int compareTo(Object x0) {
        return compareTo((CalendarioDia) x0);
    }
    private Point locString;
    private int dia;
    private Font fuente;
    private Graphics g;
    private ArrayList mouseListeners;
    private boolean resaltado;
    private boolean marcado;

}
