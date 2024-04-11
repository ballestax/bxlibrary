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
public class Punto{
    
    public double x;
    public double y;
    private static char ind;
    private char id;
    
    public Punto(final double x, final double y) {
        this.x = x;
        this.y = y;
        ++Punto.ind;
        this.id = Punto.ind;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void trasladar(final double dx, final double dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public void mover(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return Punto.ind + " -> ( " + this.x + " , " + this.y + " )";
    }
    
    public char getID() {
        return this.id;
    }
    
    static {
        Punto.ind = '@';
    }
}

