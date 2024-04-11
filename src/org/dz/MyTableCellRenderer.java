/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author lrod
 */
public class MyTableCellRenderer extends JLabel implements TableCellRenderer
{
    boolean isBordered;
    
    public MyTableCellRenderer(final boolean isBordered) {
        this.isBordered = true;
        this.isBordered = isBordered;
        this.setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        if (value != null) {
            this.setText(value.toString().toUpperCase());
        }
        if (isSelected) {
            this.setForeground(Color.black);
            this.setBackground(table.getSelectionBackground());
            if (hasFocus) {
                this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
            }
            else {
                this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            }
        }
        else {
            this.setBackground(table.getBackground());
            this.setForeground(Color.black);
            this.setBorder(UIManager.getBorder("Table.cellBorder"));
        }
        return this;
    }
    
}
