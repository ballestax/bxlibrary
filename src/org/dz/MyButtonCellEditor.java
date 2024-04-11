package org.dz;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class MyButtonCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JTextField campo;
    private Boolean currentValue;
    private JButton button;
    protected static final String EDIT = "edit";
    private JTable tabla;
    private ActionListener acList;
    private String acCommand;

    public MyButtonCellEditor(final JTable tabla, final ActionListener listener, final String acCommand) {
        (this.button = new JButton()).setActionCommand("edit");
        this.button.addActionListener(this);
        this.button.setBorderPainted(false);
        this.tabla = tabla;
        this.acList = listener;
        this.acCommand = acCommand;
    }

    @Override
    public boolean isCellEditable(final EventObject e) {
        return !(e instanceof MouseEvent) || ((MouseEvent) e).getClickCount() >= 1;
    }

    @Override
    public Object getCellEditorValue() {
        return this.currentValue;
    }

    @Override
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        this.currentValue = (Boolean) value;
        return this.button;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final int c = this.tabla.getEditingColumn();
        final int f = this.tabla.getEditingRow();
        if (f != -1 && c != -1) {
            this.acList.actionPerformed(new ActionEvent(this, f, this.acCommand));
        }
        this.fireEditingStopped();
    }
}
