package org.dz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import org.balx.Resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LuisR
 */
public class DatePicker extends JPanel implements KeyListener, MouseListener, ActionListener {

    private GregorianCalendar prev;

    class ListadoPop extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (e.getSource().equals((DatePicker.this))) {
                JPopupMenu pop = new JPopupMenu();
                pop.setSize(DatePicker.this.getWidth(), 100);
                Calendar c = GregorianCalendar.getInstance();
                c.set(5, 1);
                for (int i = 0; i < 12; i++) {
                    c.set(2, i);
                    JMenuItem mItem = new JMenuItem(DatePicker.this.formAnio.format(c.getTime()));
                    if (i == getHoy().get(2)) {
                        mItem.setForeground(Color.red);
                    }
                    mItem.setActionCommand((new StringBuilder()).append("MES").append(i >= 10 ? ((Object) (Integer.valueOf(i))) : ((Object) ((new StringBuilder()).append("0").append(i).toString()))).append(mItem.getText()).toString());
                    mItem.addActionListener(DatePicker.this);
                    pop.add(mItem);
                }
                pop.show(DatePicker.this, DatePicker.this.getX(), DatePicker.this.getY() + DatePicker.this.getY());
            } else if (e.getSource().equals(DatePicker.this)) {
                JPopupMenu pop = new JPopupMenu();
                pop.setSize(DatePicker.this.getWidth(), 100);
                Calendar c = Calendar.getInstance();
                c.set(1, Integer.parseInt(DatePicker.this.labelAnio.getText()));
                int aF1o = c.get(1);
                for (int i = aF1o; i > 1990; i--) {
                    c.set(1, i);
                    JMenuItem mItem = new JMenuItem(DatePicker.this.formAnio.format(c.getTime()));
                    if (mItem.getText().equals((new StringBuilder()).append(getHoy().get(1)).append("").toString())) {
                        mItem.setForeground(Color.red);
                    }
                    mItem.setActionCommand((new StringBuilder()).append("ANIO").append(mItem.getText()).toString());
                    mItem.addActionListener(DatePicker.this);
                    pop.add(mItem);
                }
                pop.show(DatePicker.this, DatePicker.this.getX(), DatePicker.this.getY() + DatePicker.this.getY());
            }
        }
    }

    public DatePicker() {
        this(new Date());
    }

    public DatePicker(Date fecha) {
        listo = false;
        pcs = new PropertyChangeSupport(this);
        hoy = new GregorianCalendar();
        hoy.setTime(fecha);
        sel = (GregorianCalendar) hoy.clone();
        prev = (GregorianCalendar) hoy.clone();
        formFecha = new SimpleDateFormat("EEEE, d MMMM YYYY");
        formMes = new SimpleDateFormat("MMMM");
        formAnio = new SimpleDateFormat("YYYY");
        btnDias = new JButton[sel.getActualMaximum(5)];
        marcados = new HashSet<Object>();
        iniciarComponentes();
        crearComponentes();
    }

    public DatePicker(Date date, ActionListener actionListener) {
        this(date);
        this.actionListener = actionListener;
    }

    private void iniciarComponentes() {
        setBorder(BorderFactory.createLineBorder(Color.blue));
        int d = 1;
        Font fuente = new Font("Arial", 0, 14);
        for (int i = 0; i < btnDias.length; i++) {
            btnDias[i] = new JButton((new StringBuilder()).append(i + 1).append("").toString());
            btnDias[i].setFont(fuente);
            if (actionListener != null) {
                btnDias[i].addActionListener(actionListener);
            }
            btnDias[i].setActionCommand((new StringBuilder()).append("SEL_DIA_").append(i + 1).toString());
            btnDias[i].addActionListener(this);
            d++;
        }
        listo = true;

        addKeyListener(this);
    }

    private void crearComponentes() {
        setLayout(new BorderLayout());
        ListadoPop ll = new ListadoPop();
        borde = BorderFactory.createLineBorder(Color.black);
        labelFecha = new JLabel(formFecha.format(sel.getTime()));
        labelFecha.setHorizontalAlignment(0);
        labelFecha.setBorder(borde);
        labelMes = new JLabel(formMes.format(sel.getTime()));
        labelMes.setHorizontalAlignment(0);
        labelMes.setBorder(borde);
        labelMes.addMouseListener(ll);
        labelAnio = new JLabel(formAnio.format(sel.getTime()));
        labelAnio.setHorizontalAlignment(0);
        labelAnio.setBorder(borde);
        labelAnio.addMouseListener(ll);
        Calendar c = Calendar.getInstance();
        c.set(1, Integer.parseInt(labelAnio.getText()));
        int aF1o = c.get(1);
        ArrayList years = new ArrayList();
        ArrayList months = new ArrayList();
        for (int i = aF1o; i > 1900; i--) {
            years.add((new StringBuilder()).append("").append(i).toString());
        }
        c.set(5, 1);
        for (int i = 0; i < 12; i++) {
            c.set(2, i);
            months.add((new StringBuilder()).append("").append(formMes.format(c.getTime())).toString());
        }
        comboMes = new JComboBox<>(months.toArray());
        comboMes.setBorder(borde);
        comboMes.setMaximumRowCount(12);
        comboMes.setFont(new Font("Arial", 0, 11));
        comboMes.setSelectedItem(formMes.format(sel.getTime()));
        comboMes.setActionCommand(AC_SEL_MES);
        comboMes.addActionListener(this);

        modelCbAnio = new DefaultComboBoxModel(years.toArray());
        comboAnio = new JComboBox(modelCbAnio);
        comboAnio.setBorder(borde);
        comboAnio.setMaximumRowCount(12);
        comboAnio.setFont(new Font("Arial", 0, 11));
        comboAnio.setSelectedItem(formAnio.format(sel.getTime()));
        comboAnio.setActionCommand(AC_SEL_ANIO);
        comboAnio.addActionListener(this);
        Dimension DIM = new Dimension(15, 25);
        btMesAnt = new JButton();
        btMesAnt.setPreferredSize(DIM);
        btMesAnt.setMaximumSize(DIM);
        btMesAnt.setIcon(new ImageIcon(Resources.getImagen("icons/arrow-left.png", DatePicker.class, 15, 15)));
        btMesAnt.setActionCommand("ac_mes_anterior");
        btMesAnt.addActionListener(this);
        btMesSig = new JButton();
        btMesSig.setPreferredSize(DIM);
        btMesSig.setMaximumSize(DIM);
        btMesSig.setIcon(new ImageIcon(Resources.getImagen("icons/arrow-right.png", DatePicker.class, 15, 15)));
        btMesSig.setActionCommand("ac_mes_siguiente");
        btMesSig.addActionListener(this);
        btAnioAnt = new JButton();
        btAnioAnt.setPreferredSize(DIM);
        btAnioAnt.setMaximumSize(DIM);
        btAnioAnt.setIcon(new ImageIcon(Resources.getImagen("icons/arrow-left-double.png", DatePicker.class, 15, 15)));
        btAnioAnt.setActionCommand("ac_anio_anterior");
        btAnioAnt.addActionListener(this);
        btAnioSig = new JButton();
        btAnioSig.setPreferredSize(DIM);
        btAnioSig.setMaximumSize(DIM);
        btAnioSig.setIcon(new ImageIcon(Resources.getImagen("icons/arrow-right-double.png", DatePicker.class, 15, 15)));
        btAnioSig.setActionCommand("ac_anio_siguiente");
        btAnioSig.addActionListener(this);
        Calendario = new JPanel();
        gridLayout = new GridLayout(0, 7, 2, 2);
        Calendario.setLayout(gridLayout);
        listo = true;
        organizeCalendar();
        diaPers = getHoy().get(5);
        JPopupMenu popupCalendario = new JPopupMenu();
        popupCalendario.add(new JMenuItem("ir a HOY"));
        Calendario.add(popupCalendario);
        btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getRootPane().getParent().setVisible(false);
            }
        });
        btHoy = new JButton("Hoy");
        btHoy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sel.setTime(new Date());
                setDiaSeleccionado(sel);
            }
        });
        btOk = new JButton("OK");
        JPanel panelNorth = new JPanel(new BorderLayout());
        Box boxControls = new Box(0);
        Box boxSouth = new Box(0);
        boxControls.add(btAnioAnt);
        boxControls.add(btMesAnt);
        boxControls.add(comboMes);
        boxControls.add(comboAnio);
        boxControls.add(btMesSig);
        boxControls.add(btAnioSig);
        panelNorth.add(labelFecha, "North");
        panelNorth.add(boxControls, "Center");
        boxSouth.add(btCancelar);
        boxSouth.add(btHoy);
        add(panelNorth, "North");
        add(Calendario, "Center");
        add(boxSouth, "South");
    }

    private void organizeCalendar() {
        if (listo) {
            Calendario.removeAll();
            Calendario.setLayout(gridLayout);
            GregorianCalendar cal = sel;
            cal.set(5, 1);
            int diaSemana = cal.get(7) - 1;
            int diasMes = cal.getActualMaximum(5);
            for (int k = 0; k < 7; k++) {
                JLabel lbDia = new JLabel(DIAS[k].substring(0, 3));
                lbDia.setFont(new Font("tahoma", 0, 11));
                lbDia.setOpaque(true);
                lbDia.setBackground(Color.LIGHT_GRAY);
                lbDia.setHorizontalTextPosition(0);
                lbDia.setHorizontalAlignment(0);
                Calendario.add(lbDia);
            }
            for (int i = 0; i < diaSemana; i++) {
                Calendario.add(new JLabel(""));
            }
            for (int cont = 0; cont < diasMes; cont++) {
                Calendario.add(btnDias[cont]);
                if (cont + 1 == getHoy().get(5) && cal.get(2) == getHoy().get(2) && cal.get(1) == getHoy().get(1)) {
                    btnDias[cont].setBorder(BorderFactory.createLineBorder(Color.red));
                    btnDias[cont].setForeground(Color.red);
                } else {
                    btnDias[cont].setForeground(Color.black);
                    btnDias[cont].setBorder(BorderFactory.createLineBorder(Color.black));
                }
            }
        }
    }

    public Calendar getHoy() {
        return Calendar.getInstance();
    }

    public void setDiaSeleccionado(GregorianCalendar sel) {

        this.sel = sel;

        if (btnDias.length != sel.getActualMaximum(5)) {
            btnDias = new JButton[sel.getActualMaximum(5)];
            iniciarComponentes();
        }
        if (prev.get(Calendar.MONTH) != sel.get(Calendar.MONTH)
                || prev.get(Calendar.YEAR) != sel.get(Calendar.YEAR)) {
            organizeCalendar();
        }
        prev = (GregorianCalendar) sel.clone();

        labelFecha.setText(formFecha.format(sel.getTime()));

        comboMes.removeActionListener(this);
        comboMes.setSelectedIndex(sel.get(2));
        comboMes.addActionListener(this);

        comboAnio.removeActionListener(this);
        comboAnio.setSelectedItem(formAnio.format(sel.getTime()));
        comboAnio.addActionListener(this);

        int dia = sel.get(5);
        diaPers = dia;
        repaint();
        pcs.firePropertyChange("CambioSeleccion", null, Integer.valueOf(sel.get(7)));
    }

    public Calendar getFechaSeleccionada() {
        return sel;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            getRootPane().getParent().setVisible(false);
        }
    }

    public void keyReleased(KeyEvent KeyEvent) {
    }

    public void mouseClicked(MouseEvent e) {
        CalendarioDia cd = getCalendarioDia(e.getPoint());
        if (e.isPopupTrigger()) {
            mostrarPopUp(e);
        } else if (cd != null && cd.contains(e.getPoint())) {
            sel.set(5, cd.getDia());
            setDiaSeleccionado(sel);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            mostrarPopUp(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            mostrarPopUp(e);
        }
    }

    public void mouseEntered(MouseEvent MouseEvent) {
    }

    public void mouseExited(MouseEvent MouseEvent) {
    }

    public void mostrarPopUp(MouseEvent evt) {
        JPopupMenu pop = new JPopupMenu();
        pop.add(new JMenuItem(new AbstractAction("ir a Hoy") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }));
        pop.show(Calendario, evt.getX(), evt.getY());
    }

    public CalendarioDia getCalendarioDia(Point p) {
        int MAX = rectDias.length - 1;
        for (int i = MAX; i >= 0; i--) {
            if (rectDias[i].contains(p)) {
                return rectDias[i];
            }
        }
        return null;
    }

    public CalendarioDia getVecino(CalendarioDia cd, int dir) {
        if (dir == 2) {
            int ind = Arrays.binarySearch(rectDias, cd);
            if (ind > 0) {
                return rectDias[ind - 1];
            }
        } else if (dir == 4) {
            int ind = Arrays.binarySearch(rectDias, cd);
            if (ind >= 0 && ind < rectDias.length - 1) {
                return rectDias[ind + 1];
            }
        } else if (dir == 1) {
            int ind = Arrays.binarySearch(rectDias, cd);
            if (ind >= 7) {
                return rectDias[ind - 7];
            }
        } else if (dir == 5) {
            int ind = Arrays.binarySearch(rectDias, cd);
            if (ind < rectDias.length - 7) {
                return rectDias[ind + 7];
            }
        }
        return null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ac_mes_anterior")) {
            sel.add(2, -1);
            int year = sel.get(1);
            int size = modelCbAnio.getSize();
            if (Integer.parseInt(modelCbAnio.getElementAt(size - 1).toString()) == year + 1) {
                modelCbAnio.addElement(year);
                comboAnio.removeActionListener(this);
                modelCbAnio.setSelectedItem(year);
                comboAnio.addActionListener(this);
            }
        } else if (e.getActionCommand().equals("ac_mes_siguiente")) {
            sel.add(2, 1);
            int year = sel.get(1);
            if (Integer.parseInt(modelCbAnio.getElementAt(0).toString()) == year - 1) {
                modelCbAnio.insertElementAt(year, 0);
                comboAnio.removeActionListener(this);
                modelCbAnio.setSelectedItem(year);
                comboAnio.addActionListener(this);
            }
        } else if (e.getActionCommand().equals("ac_anio_anterior")) {
            sel.add(1, -1);
            int year = sel.get(1);
            int size = modelCbAnio.getSize();
            if (Integer.parseInt(modelCbAnio.getElementAt(size - 1).toString()) == year + 1) {
                modelCbAnio.addElement(year);
                comboAnio.removeActionListener(this);
                modelCbAnio.setSelectedItem(year);
                comboAnio.addActionListener(this);
            }
        } else if (e.getActionCommand().equals("ac_anio_siguiente")) {
            sel.add(1, 1);
            int year = sel.get(1);
            if (Integer.parseInt(modelCbAnio.getElementAt(0).toString()) == year - 1) {
                modelCbAnio.insertElementAt(year, 0);
                comboAnio.removeActionListener(this);
                modelCbAnio.setSelectedItem(year);
                comboAnio.addActionListener(this);
            }
        } else if (e.getActionCommand().startsWith("MES")) {
            String ms = e.getActionCommand();
            int ps = Integer.parseInt(ms.substring(3, 5));
            sel.set(2, ps);
        } else if (e.getActionCommand().startsWith("ANIO")) {
            String ms = e.getActionCommand();
            int ps = Integer.parseInt(ms.substring(4));
            sel.set(1, ps);
        } else if (e.getActionCommand().startsWith("SEL_DIA_")) {
            String ms = e.getActionCommand();
            int ps = Integer.parseInt(ms.substring(8));
            sel.set(5, ps);
            pcs.firePropertyChange("SEL_DIA_", null, sel);
        } else if (AC_SEL_MES.equals(e.getActionCommand())) {
            int ind = comboMes.getSelectedIndex();
            sel.set(Calendar.MONTH, ind);
            sel.set(Calendar.DATE, Math.min(sel.get(Calendar.DATE), sel.getActualMaximum(Calendar.DAY_OF_MONTH)));
        } else if (AC_SEL_ANIO.equals(e.getActionCommand())) {
            sel.set(Calendar.YEAR, Integer.parseInt(comboAnio.getSelectedItem().toString()));
        }
        sel.set(5, Math.min(diaPers, sel.getActualMaximum(5)));
        setDiaSeleccionado(sel);
    }
    public static final String AC_SEL_ANIO = "AC_SEL_ANIO";
    public static final String AC_SEL_MES = "AC_SEL_MES";

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        if (pcl != null) {
            pcs.addPropertyChangeListener(pcl);
        }
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public void addMarcado(Fecha f) {
        if (!marcados.contains(f)) {
            marcados.add(f);
//            System.out.println((new StringBuilder()).append("marcado:").append(f).toString());
        }
    }

    public void removeMarcado(Fecha f) {
        marcados.remove(f);
        repaint();
    }

    public void limpiarMarcados() {
        marcados.clear();
        repaint();
    }

    private GregorianCalendar hoy;
    private GregorianCalendar sel;
    private CalendarioDia rectDias[];
    private JButton btnDias[];
    private boolean listo;
    private static final String DIAS[] = {"DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
    private JLabel labelFecha;
    private JLabel labelMes;
    private JComboBox comboMes;
    private JLabel labelAnio;
    private JComboBox comboAnio;
    private JButton btMesSig;
    private JButton btMesAnt;
    private JButton btAnioAnt;
    private JButton btAnioSig;
    private JPanel Calendario;
    private JButton btCancelar;
    private JButton btHoy;
    private JButton btOk;
    private Border borde;
    private static final String MES_ANT = "ac_mes_anterior";
    private static final String MES_SIG = "ac_mes_siguiente";
    private static final String ANIO_ANT = "ac_anio_anterior";
    private static final String ANIO_SIG = "ac_anio_siguiente";
    private SimpleDateFormat formFecha;
    private SimpleDateFormat formMes;
    private SimpleDateFormat formAnio;
    private PropertyChangeSupport pcs;
    private int diaPers;
    private HashSet marcados;
    private ActionListener actionListener;
    private LayoutManager gridLayout;
    private DefaultComboBoxModel modelCbAnio;

}
