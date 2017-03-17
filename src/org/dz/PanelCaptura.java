/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author LuisR
 */
public abstract class PanelCaptura extends JPanel implements Reseteable {

    public PanelCaptura() {
        bordeError = BorderFactory.createLineBorder(java.awt.Color.RED);
        bordeNormal = UIManager.getBorder("TextField.border");
        pcs = new PropertyChangeSupport(this);
    }

    public void setBordeNormal(Border bordeNormal) {
        this.bordeNormal = bordeNormal;
    }

    public void setBordeError(Border bordeError) {
        this.bordeError = bordeError;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

    public boolean containsListener(PropertyChangeListener pcl) {
        return Arrays.asList(pcs.getPropertyChangeListeners()).contains(pcl);
    }

    public PropertyChangeListener[] getPropertyListeners() {
        return pcs.getPropertyChangeListeners();
    }
    protected Border bordeError;
    protected Border bordeNormal;
    protected PropertyChangeSupport pcs;

    public class Caret implements CaretListener {

        public Caret() {
        }

        @Override
        public void caretUpdate(CaretEvent e) {
            if (e.getSource() instanceof JComponent) {
                JComponent comp = (JComponent) e.getSource();
                if (comp.getBorder().equals(bordeError)) {
                    comp.setBorder(bordeNormal);
                }
            } else {
                System.err.println("Componente no contiene bordes");
            }
        }

    }

}
