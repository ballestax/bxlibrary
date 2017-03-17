/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author LuisR
 */
public class GuiUtil {

    public static void centrarPanel(JPanel panel) {
        Dimension scSize = Toolkit.getDefaultToolkit().getScreenSize();
        panel.setSize(scSize);

    }

}
