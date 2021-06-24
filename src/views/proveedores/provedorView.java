package views.proveedores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.ControladorProveedor;
import models.proveedor.Rubro;
import views.ordenesDePago.OrdenesDePagoFrame;
import models.proveedor.Proveedor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;

public class provedorView extends JFrame {
    private JPanel vistaProv;
    private JButton ordenesDePagoButton;
    private JPanel panelProveedores;
    private JButton addProveedor;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton itemsServiciosButton;
    private JButton usuariosButton;
    private JLabel labelProveedor;
    private JComboBox dropListaProveedores;
    private JTextField txtNombreFantasia;
    private JTextField txtRazonSocial;
    private JTextField txtCuit;
    private JTextField txtDireccion;
    private JTextField txtResponsabilidadIva;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtIngresosBrutos;
    private JTextField txtInicioActividades;
    private JTextField txtRubro;
    private JButton RemoveProveedor;
    private JButton borrarButton;
    private JButton modificarButton;
    private ControladorProveedor controlador;

    public provedorView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 1000);
        this.setContentPane(vistaProv);
        this.setLocationRelativeTo(null);
        this.controlador= ControladorProveedor.getInstancia();

        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
                //dispose();//esto cierra la ventana anterior
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

        addProveedor.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                Proveedor provModel= new Proveedor(
                        txtNombreFantasia.getText(),
                        txtDireccion.getText(),
                        txtEmail.getText(),
                        txtIngresosBrutos.getText(),
                        txtInicioActividades.getText(),
                        txtRubro.getText(),
                        124,
                        (float) 2000,
                        txtRazonSocial.getText(),
                        txtCuit.getText());

                controlador.agregarProveedor(provModel);
            }
        });
    }


}


