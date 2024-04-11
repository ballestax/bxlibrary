package org.dz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;


public class ListSelection extends JPanel implements TableCellRenderer, MouseListener, ActionListener {

    private JLabel label;
    private JButton boton;
    private JPopupMenu pop;
    private Box box;
    private JTable table;
    public static final String AC_TODOS = "ac_todos";
    public static final String AC_NINGUNO = "ac_ninguno";
    public static final String AC_INVERTIR = "ac_invertir";

    public ListSelection(JTable table) {
        this.table = table;
        this.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        this.iniciarComponentes();
        this.setLayout(new BorderLayout());
        this.add(this.box, "Center");
    }

    private void iniciarComponentes() {
        this.label = new JLabel("Sel");
        this.label.setFont(UIManager.getFont("TableHeader.font"));
        this.boton = new JButton();
        this.boton.setContentAreaFilled(false);
        this.boton.setBorderPainted(false);
        this.boton.setMinimumSize(new Dimension(15, 15));
        this.boton.setPreferredSize(new Dimension(15, 15));
        this.boton.setIcon(new ImageIcon(Resources.getImagen("icons/drop_down.png", ListSelection.class, 9, 9)));
        this.box = Box.createHorizontalBox();
        this.box.add(Box.createHorizontalGlue());
        this.box.add(this.label);
        this.box.add(this.boton);
        this.box.add(Box.createHorizontalGlue());
        JMenuItem item1 = new JMenuItem("Todos");
        item1.setActionCommand("ac_todos");
        item1.addActionListener(this);
        JMenuItem item2 = new JMenuItem("Ninguno");
        item2.setActionCommand("ac_ninguno");
        item2.addActionListener(this);
        JMenuItem item3 = new JMenuItem("Invertir");
        item3.setActionCommand("ac_invertir");
        item3.addActionListener(this);
        this.pop = new JPopupMenu();
        this.pop.add(item1);
        this.pop.add(item2);
        this.pop.add(item3);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        Rectangle rect = this.boton.getBounds();
        rect.add(this.label.getBounds());
        if (rect.contains(e.getPoint())) {
            this.pop.show(this.table.getTableHeader(), e.getX(), this.boton.getY() + this.boton.getHeight());
        }

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        String var2 = e.getActionCommand();
        byte var3 = -1;
        switch (var2.hashCode()) {
            case -416059227:
                if (var2.equals("ac_ninguno")) {
                    var3 = 1;
                }
                break;
            case 232602364:
                if (var2.equals("ac_invertir")) {
                    var3 = 2;
                }
                break;
            case 2047733392:
                if (var2.equals("ac_todos")) {
                    var3 = 0;
                }
        }

        int i;
        switch (var3) {
            case 0:
                for (i = 0; i < this.table.getRowCount(); ++i) {
                    this.table.getModel().setValueAt(Boolean.TRUE, i, 0);
                }

                return;
            case 1:
                for (i = 0; i < this.table.getRowCount(); ++i) {
                    this.table.getModel().setValueAt(Boolean.FALSE, i, 0);
                }

                return;
            case 2:
                for (i = 0; i < this.table.getRowCount(); ++i) {
                    Boolean value = (Boolean) this.table.getModel().getValueAt(i, 0);
                    this.table.getModel().setValueAt(!value, i, 0);
                }
        }

    }
}
