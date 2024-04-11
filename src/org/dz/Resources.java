/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author lrod
 */
public class Resources {

    public static BufferedImage getImagen(final String archivo, final Class main) {
        BufferedImage imagen = null;
        final URL urlImagen = main.getResource(archivo);
        try {
            imagen = ImageIO.read(urlImagen);
        } catch (IOException e) {  
            System.err.println(e);
        }
        return imagen;
    }

    public static Image getImagenAsStream(final String archivo, final Class main, final int w, final int h) {
        Image imagen = null;
        InputStream inputStream = main.getClassLoader().getResourceAsStream(archivo);
        try {
            imagen = ImageIO.read(inputStream);
            imagen = imagen.getScaledInstance(w, h, 16);
        } catch (IOException e) {            
            System.err.println(e);
        }
        return imagen;
    }
    
    public static Image getImagen(final String archivo, final Class main, final int w, final int h) {
        Image imagen = null;
        final URL urlImagen = main.getResource(archivo);
        try {
            imagen = ImageIO.read(urlImagen);
            imagen = imagen.getScaledInstance(w, h, 16);
        } catch (IOException e) {            
            System.err.println(e);
        }
        return imagen;
    }

    public static Image getImagen(final String archivo, final int w, final int h) {
        Image imagen = null;
        try {
            imagen = ImageIO.read(new File(archivo));
            imagen = imagen.getScaledInstance(w, h, 1);
        } catch (IOException e) {            
        }
        return imagen;
    }

    public static BufferedImage getImagen(final String archivo) {
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(archivo));
        } catch (IOException e) {            
        }
        return imagen;
    }
    
     public static BufferedImage cargarImagen(final String archivo) {
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(archivo));
        }
        catch (IOException e) {
        }
        return imagen;
    }
}
