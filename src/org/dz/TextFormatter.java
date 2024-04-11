/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dz;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author LuisR
 */
public class TextFormatter {

    public static class DoubleDocument extends PlainDocument {

        public void insertString(int offset, String string, AttributeSet attributes) throws BadLocationException {
            if (string == null) {
                return;
            }
            int length = getLength();
            String newValue;
            if (length == 0) {
                newValue = string;
            } else {
                String currentContent = getText(0, length);
                StringBuffer currentBuffer = new StringBuffer(currentContent);
                currentBuffer.insert(offset, string);
                newValue = currentBuffer.toString();
            }
            try {
                checkInput(newValue);
                super.insertString(offset, string, attributes);
            } catch (Exception exception) {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        public void remove(int offset, int length) throws BadLocationException {
            int currentLength = getLength();
            String currentContent = getText(0, currentLength);
            String before = currentContent.substring(0, offset);
            String after = currentContent.substring(length + offset, currentLength);
            String newValue = (new StringBuilder()).append(before).append(after).toString();
            try {
                checkInput(newValue);
                super.remove(offset, length);
            } catch (Exception exception) {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        private double checkInput(String proposedValue) throws NumberFormatException {
            double newValue = 0.0D;
            if (proposedValue.length() > 0) {
                newValue = Double.parseDouble(proposedValue);
            }
            return newValue;
        }
    }

    private static class IntegerDocument extends PlainDocument {

        public void insertString(int offset, String string, AttributeSet attributes) throws BadLocationException {
            if (string == null) {
                return;
            }
            int length = getLength();
            String newValue;
            if (length == 0) {
                newValue = string;
            } else {
                String currentContent = getText(0, length);
                StringBuffer currentBuffer = new StringBuffer(currentContent);
                currentBuffer.insert(offset, string);
                newValue = currentBuffer.toString();
            }
            try {
                checkInput(newValue);
                super.insertString(offset, string, attributes);
            } catch (Exception exception) {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        public void remove(int offset, int length) throws BadLocationException {
            int currentLength = getLength();
            String currentContent = getText(0, currentLength);
            String before = currentContent.substring(0, offset);
            String after = currentContent.substring(length + offset, currentLength);
            String newValue = (new StringBuilder()).append(before).append(after).toString();
            try {
                checkInput(newValue);
                super.remove(offset, length);
            } catch (Exception exception) {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        private long checkInput(String proposedValue) throws NumberFormatException {
            long newValue = 0L;
            if (proposedValue.length() > 0) {
                newValue = Long.parseLong(proposedValue);
            }
            return newValue;
        }

    }

    public TextFormatter() {
    }

    public static javax.swing.text.Document getDoubleLimiter() {
        return new DoubleDocument();
    }

    public static javax.swing.text.Document getIntegerLimiter() {
        return new IntegerDocument();
    }

}
