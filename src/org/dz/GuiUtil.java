/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author lrod
 */
public class GuiUtil {
    
private static JFileChooser chooser;
    
    private GuiUtil() {
    }
    
    public static void centrarFrame(final JFrame frame) {
        if (frame != null) {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final GraphicsConfiguration gc = frame.getGraphicsConfiguration();
            final Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
            final Dimension d = new Dimension(screenSize.width - (screenInsets.right + screenInsets.left), screenSize.height - (screenInsets.top + screenInsets.bottom));
            frame.setSize(d);
            frame.setLocationRelativeTo(null);
        }
    }
    
    public static void centrarFrame2(final JFrame frame) {
        if (frame != null) {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final GraphicsConfiguration gc = frame.getGraphicsConfiguration();
            final Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
            final Dimension d = new Dimension(screenSize.width - (screenInsets.right + screenInsets.left), screenSize.height - (screenInsets.top + screenInsets.bottom));
            frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
        }
    }
    
    public static Dimension screenDimension() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final GraphicsConfiguration gc = new JFrame().getGraphicsConfiguration();
        final Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
        final Dimension d = new Dimension(screenSize.width - (screenInsets.right + screenInsets.left), screenSize.height - (screenInsets.top + screenInsets.bottom));
        return d;
    }
    
    public static File getFileChooseer(final String titulo, final String nombre, final String botonText, final String botonTooltip, final FileFilter filtro, final Component parent, final File file) {
        final JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(titulo);
        chooser.setApproveButtonText(botonText);
        chooser.setApproveButtonToolTipText(botonTooltip);
        chooser.setFileSelectionMode(0);
        chooser.addChoosableFileFilter(filtro);
        chooser.setFileFilter(filtro);
        chooser.rescanCurrentDirectory();
        File archivoSel = null;
        if (file == null) {
            archivoSel = new File(chooser.getCurrentDirectory().toString() + File.pathSeparator + nombre);
        }
        else {
            archivoSel = file;
        }
        chooser.setSelectedFile(archivoSel);
        final int result = chooser.showDialog(parent, null);
        return (result == 0) ? chooser.getSelectedFile() : null;
    }
    
    public static File getFileChooseer(final String titulo, final String nombre, final String botonText, final String botonTooltip, final FileFilter filtro, final int selModo, final Component parent, final File file) {
        final JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(titulo);
        chooser.setApproveButtonText(botonText);
        chooser.setApproveButtonToolTipText(botonTooltip);
        chooser.setFileSelectionMode(selModo);
        chooser.addChoosableFileFilter(filtro);
        chooser.setFileFilter(filtro);
        chooser.rescanCurrentDirectory();
        File archivoSel = null;
        if (file == null) {
            archivoSel = new File(chooser.getCurrentDirectory().toString() + File.pathSeparator + nombre);
        }
        else {
            archivoSel = file;
        }
        chooser.setSelectedFile(archivoSel);
        final int result = chooser.showDialog(parent, null);
        return (result == 0) ? chooser.getSelectedFile() : null;
    }
    
    public static File getFileChooseer(final String titulo, final String nombre, final FileFilter filtro, final Component parent) {
        (GuiUtil.chooser = new JFileChooser()).setDialogTitle(titulo);
        GuiUtil.chooser.addChoosableFileFilter(filtro);
        GuiUtil.chooser.setFileFilter(filtro);
        GuiUtil.chooser.rescanCurrentDirectory();
        if (GuiUtil.chooser.getComponentPopupMenu() != null) {
            GuiUtil.chooser.getComponentPopupMenu().add(new JMenuItem((Action)new AbstractAction("Archivos ocultos") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(GuiUtil.chooser.isFileHidingEnabled()){
                        GuiUtil.chooser.setFileHidingEnabled(!GuiUtil.chooser.isFileHidingEnabled());
                    }
                    GuiUtil.chooser.rescanCurrentDirectory();
                }
            }));
        }
        final int result = GuiUtil.chooser.showDialog(parent, null);
        return (result == 0) ? GuiUtil.chooser.getSelectedFile() : null;
    }
    
    public static File[] getFileChooseerDIR(final String titulo, final String nombre, final FileFilter filtro, final Component parent) {
        final JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(titulo);
        chooser.setFileSelectionMode(2);
        chooser.setMultiSelectionEnabled(true);
        chooser.addChoosableFileFilter(filtro);
        chooser.setFileFilter(filtro);
        chooser.rescanCurrentDirectory();
        final int result = chooser.showDialog(parent, null);
        return (File[])((result == 0) ? chooser.getSelectedFiles() : null);
    }
}
