/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author LuisR
 */
public abstract class PanelCapturaMod extends PanelCaptura implements PropertyChangeListener {

    public Border bordeEdit = BorderFactory.createLineBorder(Color.blue);

    public void cancelPanel() {
        getRootPane().getParent().setVisible(false);
    }

    @Override
    public void reset() {

    }

}
