package views.utils;

import javax.swing.*;

public class InputValidate {

    public static void genericValidateJtextNumbers(JTextField inputText, java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if ( Character.isDigit(c) || Character.isISOControl(c) ) {
            inputText.setEditable(true);
        } else {
            inputText.setEditable(false);
        }
    }

    public static void genericValidateJtext(JTextField inputText, java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if ( Character.isLetter(c) || Character.isISOControl(c) || Character.isWhitespace(c) || !Character.isDigit(c) ) {
            inputText.setEditable(true);
        } else {
            inputText.setEditable(false);
        }
    }

    public static void checkIfEmpty(JTextField inputText, String p) {

        inputText.setText(!p.isEmpty() ? p : "");

    }

}
