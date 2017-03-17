package org.dz;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LuisR
 */
public class Rectangulo extends Rectangle {

    public Rectangulo() {
        seleccionado = false;
        colorBorde = new Color(70, 0, 35);
        colorFondo = new Color(200, 191, 231);
        colorSeleccionado = new Color(154, 137, 211);
        colorMarca = Color.BLUE;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public void setColorSeleccionado(Color colorSeleccionado) {
        this.colorSeleccionado = colorSeleccionado;
    }

    public void setColorFuente(Color colorFuente) {
        this.colorFuente = colorFuente;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void paint(Graphics g) {
        g.setColor(isSeleccionado() ? colorSeleccionado : colorFondo);
        g.fillRect(x, y, width, height);
        g.setColor(colorBorde);
        g.drawRect(x, y, width - 1, height - 1);
    }
    private boolean seleccionado;
    protected Color colorFondo;
    protected Color colorBorde;
    protected Color colorSeleccionado;
    protected Color colorFuente;
    protected Color colorMarca;

}
