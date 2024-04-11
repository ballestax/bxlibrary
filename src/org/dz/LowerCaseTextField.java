package org.dz;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class LowerCaseTextField extends JTextField {

    public LowerCaseTextField() {
    }

    public LowerCaseTextField(final Document doc, final String text, final int columns) {
        super(doc, text, columns);
    }

    public LowerCaseTextField(final int columns) {
        super(columns);
    }

    public LowerCaseTextField(final String text) {
        super(text);
    }

    public LowerCaseTextField(final String text, final int columns) {
        super(text, columns);
    }

    @Override
    protected Document createDefaultModel() {
        return (Document) new LowerCaseTextField.LimitedDocument();
    }

    static class LimitedDocument extends PlainDocument {

        @Override
        public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            super.insertString(offs, str.toLowerCase(), a);
        }
    }
}
