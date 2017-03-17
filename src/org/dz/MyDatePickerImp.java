package org.dz;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
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
public class MyDatePickerImp extends JComponent implements ActionListener, KeyListener, PropertyChangeListener {

    private final Date date;

    public MyDatePickerImp() {
        this(new Date(), false);
    }

    public MyDatePickerImp(Date date, boolean show) {
        this.date = date;
        initComponent(show);
    }

    private void initComponent(boolean show) {
        btPick = new JButton();
        btPick.setActionCommand("SHOW_DATEPICKER");
        btPick.addActionListener(this);
        btPick.setIcon(new ImageIcon(Resources.getImagen("icons/calendar_icon.png", MyDatePickerImp.class, 14, 14)));
        btPick.setPreferredSize(new Dimension(16, 16));
        DatePicker = new DatePicker(Date);
        DatePicker.addPropertyChangeListener(this);
        DateField = new JTextField();
        formatDate = new SimpleDateFormat("dd-MM-yyyy");
        if (show) {
            DateField.setText(formatDate.format(Date));
        }
        setLayout(new BoxLayout(this, 0));
        add(DateField);
        add(btPick);
        
        addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SHOW_DATEPICKER")) {
            dialog = new MyDialogEsc();
            dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setPreferredSize(new Dimension(200, 260));
            dialog.add(DatePicker);
            dialog.setUndecorated(true);
            dialog.pack();
            Point loc = ((JComponent) e.getSource()).getLocationOnScreen();
            dialog.setLocation(loc.x - 180, loc.y + 20);
            dialog.addKeyListener(this);
            dialog.setVisible(true);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().startsWith("SEL_DIA_")) {
            Object value = evt.getNewValue();
            if (value != null && (value instanceof GregorianCalendar)) {
                GregorianCalendar fecSel = (GregorianCalendar) value;
                String stDate = formatDate.format(fecSel.getTime());
                DateField.setText(stDate);
                dialog.setVisible(false);
            }
        }
    }

    public String getText() {
        return DateField.getText();
    }

    public void setText(String text) {
        DateField.setText(text);
    }

    public void setEditable(boolean editable) {
        java.awt.Color background = DateField.getBackground();
        DateField.setEditable(editable);
        btPick.setEnabled(editable);
        DateField.setBackground(background);
    }

    public void keyTyped(KeyEvent e) {
//        throw new unsupportedoperationexception("Not supported yet.");
    }

    public void keyPressed(KeyEvent e) {
//        throw new unsupportedoperationexception("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {
//        throw new unsupportedoperationexception("Not supported yet.");
    }
    private JButton btPick;
    private DatePicker DatePicker;
    private JTextField DateField;
    private Date Date;
    private SimpleDateFormat formatDate;
    private MyDialogEsc dialog;

}
