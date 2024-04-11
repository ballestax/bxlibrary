/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author lrod
 */
public class MyListModel extends AbstractListModel{
    
    private ArrayList<Object> lista;
    private ArrayList<Object> listaFiltro;
    private String filtro;
    private boolean filtroActivado;
    
    public MyListModel() {
        this(new ArrayList());
    }
    
    public MyListModel(final ArrayList lista) {
        this.lista = (ArrayList<Object>)lista;
        this.listaFiltro = new ArrayList<Object>();
        this.filtroActivado = false;
    }
    
    @Override
    public int getSize() {
        if (this.lista != null) {
            return this.filtroActivado ? this.listaFiltro.size() : this.lista.size();
        }
        return 0;
    }
    
    @Override
    public Object getElementAt(final int index) {
        return this.filtroActivado ? this.listaFiltro.get(index) : this.lista.get(index);
    }
    
    public synchronized Object setElemento(final int index, final Object value) {
        this.fireContentsChanged(value, index, index);
        return this.lista.set(index, value);
    }
    
    public boolean addElemento(final Object value) {
        final boolean add = this.lista.add(value);
        this.fireIntervalAdded(this, this.lista.size() - 1, this.lista.size() - 1);
        this.refiltrar();
        return add;
    }
    
    public synchronized void addElemento(final int index, final Object value) {
        this.lista.add(index, value);
        this.fireIntervalAdded(this, index, index);
        this.refiltrar();
    }
    
    public synchronized void removeElemento(final Object value) {
        final int index = this.lista.indexOf(value);
        if (index >= 0) {
            this.lista.remove(value);
            this.fireIntervalRemoved(this, index, index);
            this.refiltrar();
        }
    }
    
    public synchronized void aCima(final int ind) {
        if (ind < 0 || ind > this.lista.size()) {
            return;
        }
        final Object tmp = this.lista.get(ind);
        this.lista.remove(ind);
        this.lista.add(0, tmp);
        this.fireContentsChanged(this, ind, ind);
    }
    
    public synchronized void aPiso(final int ind) {
        if (ind < 0 || ind > this.lista.size()) {
            return;
        }
        final Object tmp = this.lista.get(ind);
        final int tam = this.lista.size();
        this.lista.remove(ind);
        this.lista.add(tam - 1, tmp);
        this.fireContentsChanged(this, ind, ind);
    }
    
    public synchronized void intercambiar(final int ind1, final int ind2) {
        try {
            final Object tmp = this.lista.get(ind1);
            this.lista.set(ind1, this.lista.get(ind2));
            this.lista.set(ind2, tmp);
            this.fireContentsChanged(this, ind1, ind2);
        }
        catch (Exception ex) {}
    }
    
    public synchronized void removeElemento(final int index) {
        if (index >= 0 && index < this.lista.size()) {
            this.lista.remove(index);
            this.fireIntervalRemoved(this, index, index);
            this.refiltrar();
        }
    }
    
    public void addLista(final ArrayList lista, final boolean vaciar) {
        if (lista != null) {
            final int size = this.lista.size();
            if (vaciar) {
                this.lista.clear();
            }
            this.lista.addAll(lista);
            this.fireIntervalAdded(this, size, size + lista.size());
            this.refiltrar();
        }
    }
    
    public void addLista(final int index, final ArrayList lista) {
        if (lista != null) {
            this.lista.addAll(index, lista);
            this.fireIntervalAdded(this, index, index + lista.size());
            this.refiltrar();
        }
    }
    
    public void clear() {
        this.fireIntervalRemoved(this, 0, this.lista.size() - 1);
        this.lista.clear();
        this.listaFiltro.clear();
    }
    
    public ArrayList<Object> getLista() {
        return this.filtroActivado ? this.listaFiltro : this.lista;
    }
    
    public ArrayList<Object> getListaFiltro() {
        return this.listaFiltro;
    }
    
    public boolean isVacia() {
        return this.lista.isEmpty();
    }
    
    public void setFiltro(final String filtro) {
        this.filtro = filtro;
        this.refiltrar();
    }
    
    public String getFiltro() {
        return this.filtro;
    }
    
    private void refiltrar() {
        if (this.filtro != null && !this.filtro.isEmpty()) {
            this.listaFiltro.clear();
            final String term = this.filtro.toLowerCase();
            for (int i = 0; i < this.lista.size(); ++i) {
                if (this.lista.get(i).toString().toLowerCase().indexOf(term, 0) != -1) {
                    this.listaFiltro.add(this.lista.get(i));
                }
            }
            this.fireContentsChanged(this, 0, this.getSize());
        }
    }
    
    public void activarFiltro(final boolean activar) {
        if (!(this.filtroActivado = activar)) {
            this.fireContentsChanged(this, 0, this.getSize());
        }        
    }
}