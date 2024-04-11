package org.dz;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class MyButtonCellRenderer extends JButton implements TableCellRenderer {

    public MyButtonCellRenderer(final String text) {
        this.setText(text);
    }

    public MyButtonCellRenderer(final ImageIcon icon) {
        this.setIcon(icon);
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        if (isSelected) {
            this.setForeground(Color.black);
            this.setBackground(table.getSelectionBackground());
            if (hasFocus) {
                this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
            } else {
                this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            }
        } else {
            this.setBackground(table.getBackground());
            this.setForeground(Color.black);
            this.setBorder(UIManager.getBorder("Table.cellBorder"));
        }
        return this;
    }
}
