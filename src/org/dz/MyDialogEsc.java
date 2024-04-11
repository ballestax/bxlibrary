package org.dz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author LuisR
 */
public class MyDialogEsc extends JDialog {

    public MyDialogEsc() {
        setResizable(true);
        getContentPane().setLayout(new BorderLayout());
        inicializar();
    }

    public MyDialogEsc(JFrame frame) {
        super(frame, true);
        setResizable(true);
        getContentPane().setLayout(new BorderLayout());
        inicializar();
    }

    public MyDialogEsc(JDialog dialog) {
        super(dialog, true);
        setResizable(true);
        getContentPane().setLayout(new BorderLayout());
        inicializar();
    }

    public void inicializar() {
        requestFocusInWindow();

    }

    @Override
    protected JRootPane createRootPane() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        };
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
}
