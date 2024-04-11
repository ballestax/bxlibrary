package org.dz;


import java.text.DateFormat;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LuisR
 */
public class Fecha {

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        calendar = calendar.getInstance();
        calendar.set(anio, mes - 1, dia);
    }

    public void set(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        calendar.set(anio, mes - 1, dia);
    }

    public String toString() {
        return DateFormat.getDateInstance(2).format(calendar.getTime());
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fecha)) {
            return false;
        }
        Fecha f = (Fecha) obj;
        if (dia != f.dia) {
            return false;
        }
        if (mes != f.mes) {
            return false;
        }
        return anio == f.anio;
    }

    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + dia;
        hash = 17 * hash + mes;
        hash = 17 * hash + anio;
        return hash;
    }
    public int dia;
    public int mes;
    public int anio;
    private Calendar calendar;

}
