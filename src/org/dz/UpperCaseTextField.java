/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 *
 * @author lrod
 */
// 
// Decompiled by Procyon v0.5.36
// 
public class UpperCaseTextField extends JTextField {

    public UpperCaseTextField() {
    }

    public UpperCaseTextField(final Document doc, final String text, final int columns) {
        super(doc, text, columns);
    }

    public UpperCaseTextField(final int columns) {
        super(columns);
    }

    public UpperCaseTextField(final String text) {
        super(text);
    }

    public UpperCaseTextField(final String text, final int columns) {
        super(text, columns);
    }

    @Override
    protected Document createDefaultModel() {
        return (Document) new UpperCaseTextField.LimitedDocument();
    }

    static class LimitedDocument extends PlainDocument {

        @Override
        public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            super.insertString(offs, str.toUpperCase(), a);
        }
    }

}
