/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.util.Iterator;
import java.io.File;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {

    private ArrayList<String> extension;
    private String descripcion;

    public MyFileFilter(final String[] extension, final String descripcion) {
        this.extension = new ArrayList<String>(Arrays.asList(extension));
        this.descripcion = descripcion;
    }

    public void addExtension(final String extension) {
        this.extension.add(extension);
    }

    @Override
    public boolean accept(final File f) {
        if (f.isDirectory()) {
            return true;
        }
        if (f.isFile()) {
            final Iterator<String> it = this.extension.iterator();
            while (it.hasNext()) {
                if (f.getName().toUpperCase().endsWith(it.next().toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        String ext = "";
        final int t = this.extension.size();
        for (int i = 0; i < t - 1; ++i) {
            ext = ext + this.extension.get(i) + " - ";
        }
        ext += this.extension.get(t - 1);
        return this.descripcion + "(" + ext + ")";
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
}
