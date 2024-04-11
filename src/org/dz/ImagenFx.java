/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author lrod
 */
public class ImagenFx {

    static final Font FUENTE_DF;

    public static BufferedImage labelImagen(final Image imagen, final String label) {
        return labelImagen(imagen, label, new Font("Tahoma", 1, 12));
    }

    public static BufferedImage labelImagen(final Image imagen, final String label, final Font font) {
        final int w = imagen.getWidth(null);
        final int h = imagen.getHeight(null);
        final BufferedImage imagenL = new BufferedImage(w, h, 2);
        final Graphics2D g = (Graphics2D) imagenL.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font f = font;
        FontMetrics fm;
        Rectangle2D r;
        for (fm = g.getFontMetrics(f), r = fm.getStringBounds(label, g); r.getWidth() > w; r = fm.getStringBounds(label, g)) {
            fm = g.getFontMetrics(f = f.deriveFont(f.getSize() - 1.0f));
        }
        g.setFont(f);
        g.drawImage(imagen, 0, 0, null);
        final int x = (int) (w / 2 - r.getCenterX());
        final int rh = (int) r.getHeight();
        final int rw = (int) r.getWidth();
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(x - 1, h - (rh + rh / 2), rw + 4, rh + 2);
        g.setColor(Color.black);
        g.drawRect(x - 1, h - (rh + rh / 2), rw + 4, rh + 2);
        g.drawString(label, x + 4, h - fm.getDescent() - rh / 2 + 3);
        g.setColor(Color.white);
        g.drawString(label, x + 2, h - fm.getDescent() - rh / 2 + 1);
        return imagenL;
    }

    public static Image rotarImagen(final Image imagen, final double angulo) {
        final int w = imagen.getWidth(null);
        final int h = imagen.getHeight(null);
        final BufferedImage _imagen = new BufferedImage(h, w, 2);
        final Graphics2D g = (Graphics2D) _imagen.getGraphics().create();
        g.setColor(Color.red);
        g.drawRect(0, 0, _imagen.getWidth() - 1, _imagen.getHeight() - 1);
        g.rotate(angulo, w / 2, w / 2);
        g.drawImage(imagen, 0, 0, null);
        return _imagen;
    }

    public static BufferedImage rotarImagen(final BufferedImage imagen, final int angulo, final double x, final double y) {
        final int w = imagen.getWidth(null);
        final int h = imagen.getHeight(null);
        final BufferedImage _imagen = new BufferedImage(h, w, 2);
        final Graphics2D g = (Graphics2D) _imagen.getGraphics().create();
        g.setColor(Color.red);
        g.drawRect(0, 0, _imagen.getWidth() - 1, _imagen.getHeight() - 1);
        final AffineTransform transf = AffineTransform.getRotateInstance(Math.toRadians(angulo), x, y);
        final AffineTransformOp op = new AffineTransformOp(transf, 2);
        op.filter(imagen, _imagen);
        return _imagen;
    }

    public static BufferedImage textoImagen(final String string, final int w, final int h, final Color cLetra, final Color cFondo, final Font font) {
        final BufferedImage imagen = new BufferedImage(w, h, 2);
        final Graphics2D g = (Graphics2D) imagen.getGraphics().create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font f = font;
        FontMetrics fm;
        Rectangle2D r;
        for (fm = g.getFontMetrics(f), r = fm.getStringBounds(string, g); r.getWidth() > w; r = fm.getStringBounds(string, g)) {
            fm = g.getFontMetrics(f = f.deriveFont(f.getSize() - 1.0f));
        }
        g.setFont(f);
        g.setColor(cFondo);
        g.fillRect(0, 0, w, h);
        g.setColor(cLetra);
        g.drawRect(0, 0, w - 1, h - 1);
        final int x = (int) (w / 2 - r.getCenterX());
        final int y = (int) (h / 2 - r.getCenterY());
        g.drawString(string, x, y);
        return imagen;
    }

    public static BufferedImage deslizarImagen(final BufferedImage imagen, final double sx, final double sy, final Polygon pol) {
        int w = imagen.getWidth(null);
        int h = imagen.getHeight(null);
        final double dx = w * sx;
        final double dy = h * sy;
        w += (int) dx;
        h += (int) dy;
        final BufferedImage _imagen = new BufferedImage(w, h, 2);
        final AffineTransform transf1 = AffineTransform.getShearInstance(sx, sy);
        final AffineTransform transf2 = AffineTransform.getTranslateInstance(-dx, -dy / 2.0);
        if (dx < 0.0 || dy < 0.0) {
            transf1.concatenate(transf2);
        }
        final double[] flatmatrix = new double[6];
        transf1.getMatrix(flatmatrix);
        if (pol != null) {
            try {
                double[][] TRANSF = Mat.arrayToMatriz(flatmatrix, 3, 2, 0.0);
                TRANSF = Mat.matrizTraspuesta(TRANSF);
                for (int i = 0; i < pol.npoints; ++i) {
                    final double[][] punto = new double[3][1];
                    punto[0][0] = pol.xpoints[i];
                    punto[1][0] = pol.ypoints[i];
                    final double[][] prod = Mat.multiplicarMatrices(TRANSF, punto);
                    pol.xpoints[i] = (int) prod[0][0];
                    pol.ypoints[i] = (int) prod[1][0];
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        final AffineTransformOp op = new AffineTransformOp(transf1, 2);
        op.filter(imagen, _imagen);
        return _imagen;
    }

    public static BufferedImage escarlarImagen(final BufferedImage imagen, final double fx, final double fy) {
        final int w = imagen.getWidth(null);
        final int h = imagen.getHeight(null);
        final double fac = Math.min(fx, fy);
        final BufferedImage _imagen = new BufferedImage((int) (w * fac), (int) (h * fac), 2);
        final AffineTransform transf = AffineTransform.getScaleInstance(fac, fac);
        final AffineTransformOp op = new AffineTransformOp(transf, 3);
        op.filter(imagen, _imagen);
        return _imagen;
    }

    public static BufferedImage escarlarImagen(final BufferedImage imagen, final int w, final int h, final boolean relacion) {
        final double _w = imagen.getWidth();
        final double _h = imagen.getHeight();
        final double fac = Math.min(w / _w, h / _h);
        final BufferedImage _imagen = new BufferedImage((int) (_w * fac), (int) (_h * fac), 2);
        final AffineTransform transf = AffineTransform.getScaleInstance(fac, fac);
        final AffineTransformOp op = new AffineTransformOp(transf, 1);
        op.filter(imagen, _imagen);
        return _imagen;
    }

    public static BufferedImage escarlarImagen(final BufferedImage imagen, final int w, final int h) {
        return escarlarImagen(imagen, w, h, false);
    }

    public static BufferedImage reflejarImagen(final BufferedImage imagen) {
        final int w = imagen.getWidth(null);
        final int h = imagen.getHeight(null);
        final BufferedImage _imagen = new BufferedImage(w, h * 2, 2);
        final Graphics2D g = _imagen.createGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.scale(1.0, -1.0);
        g.drawImage(imagen, 0, -h - h, null);
        g.scale(1.0, -1.0);
        g.translate(0, h);
        final Color c1 = new Color(1.0f, 1.0f, 1.0f, 0.5f);
        final Color c2 = new Color(1.0f, 1.0f, 1.0f, 0.0f);
        final GradientPaint grd = new GradientPaint(0.0f, 0.0f, c1, 0.0f, (float) (2 * h / 3), c2);
        g.setPaint(grd);
        g.setComposite(AlphaComposite.DstIn);
        g.fillRect(0, 0, _imagen.getWidth(), h);
        g.dispose();
        return _imagen;
    }

    static {
        FUENTE_DF = new Font("arial", 1, 14);
    }
}
