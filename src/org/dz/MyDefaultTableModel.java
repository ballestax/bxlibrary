/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lrod
 */
public class MyDefaultTableModel extends DefaultTableModel {

    private HashMap<Point, Boolean> celdasEditables;

    public MyDefaultTableModel(final Object[] columnNames, final int i) {
        super(columnNames, i);
        this.celdasEditables = new HashMap<Point, Boolean>();
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
        final Boolean vl = this.celdasEditables.get(new Point(row, column));
        return vl == null || vl;
    }

    public void setCellEditable(final int row, final int col, final boolean valor) {
        this.celdasEditables.put(new Point(row, col), valor);
    }

    public void setColumnEditable(final int col, final boolean valor) {
        for (int i = 0; i < this.getRowCount(); ++i) {
            this.celdasEditables.put(new Point(i, col), valor);
        }
    }

    public void setRowEditable(final int row, final boolean valor) {
        for (int i = 0; i < this.getColumnCount(); ++i) {
            this.celdasEditables.put(new Point(row, i), valor);
        }
    }

    public Object[] getRow(final int row) {
        final List rowVector = this.dataVector.elementAt(row);
        return rowVector.toArray();
    }

    public Object[] getColumn(final int column) {
        final int MAX = this.getRowCount();
        final List colList = Collections.EMPTY_LIST;
        for (int i = 0; i < MAX; ++i) {
            colList.add(this.dataVector.elementAt(i).elementAt(column));
        }
        return colList.toArray();
    }

    public Collection getCollectionColumn(final int column) {
        final int MAX = this.getRowCount();
        final List colList = Collections.EMPTY_LIST;
        for (int i = 0; i < MAX; ++i) {
            colList.add(this.dataVector.elementAt(i).elementAt(column));
        }
        return colList;
    }
}
