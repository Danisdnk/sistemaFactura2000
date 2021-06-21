package views.proveedores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.ControladorProveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;

public class provedorView extends JFrame {
    private JPanel vistaProv;
    private JButton proveedoresButton;
    private JButton btnItems;
    private JButton btnconsultas;
    private JButton btnDocs;
    private JButton btnprov;
    private JPanel panelProveedores;
    private JComboBox comboBox1;
    private JLabel labelProveedor;
    private JButton addProveedor;
    private JButton borrarButton;
    private JButton modificarButton;
    private ControladorProveedor controlador;

    public provedorView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 900);
        this.setContentPane(vistaProv);
        this.pack();

        btnconsultas.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    provedorView dialog = new provedorView();
                    JOptionPane.showMessageDialog(
                            addProveedor,
                            "proveedor",
                            "Nuevo proveedor",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                /*addRow(dialog.showDialog());*/ catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }


}


