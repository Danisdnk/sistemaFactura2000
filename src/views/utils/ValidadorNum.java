package views.utils;

import javax.swing.*;

public class ValidadorNum {

    public static void genericValidateJtextNumbers(JTextField inputText, java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if ( Character.isDigit(c) || Character.isISOControl(c) ) {
            inputText.setEditable(true);
        } else {
            inputText.setEditable(false);
        }
    }

}
