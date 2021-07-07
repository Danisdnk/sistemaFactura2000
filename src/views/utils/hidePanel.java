package views.utils;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class hidePanel extends JFrame {

    private JPanel hidePanel;
    private JButton Aceptar;

    public hidePanel() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(hidePanel);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        Aceptar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
         dispose();
            }
        });
    }

}
