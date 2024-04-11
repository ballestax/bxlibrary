/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author lrod
 */
public class SpinnerNumberModelo extends SpinnerNumberModel
{
    public SpinnerNumberModelo(final int valor, final int minimo, final int maximo, final int step) {
        super(valor, minimo, maximo, step);
    }
    
    public SpinnerNumberModelo(final Number valor, final Comparable minimo, final Comparable maximo, final Number step) {
        super(valor, minimo, maximo, step);
    }
    
    public SpinnerNumberModelo(final double valor, final double minimo, final double maximo, final double step) {
        super(valor, minimo, maximo, step);
    }
    
    @Override
    public Object getNextValue() {
        final Object nv = super.getNextValue();
        if (nv != null) {
            return nv;
        }
        return this.getMinimum();
    }
    
    @Override
    public Object getPreviousValue() {
        final Object pv = super.getPreviousValue();
        if (pv != null) {
            return pv;
        }
        return this.getMaximum();
    }
}
