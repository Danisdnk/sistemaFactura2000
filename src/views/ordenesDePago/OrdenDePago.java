package views.ordenesDePago;

import controllers.ControladorOrdenPago;
import models.documento.OrdenPago;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdenDePago extends JDialog implements ActionListener{
    private JPanel opMain;
    private JComboBox ddlFormasPago;
    private JComboBox ddlProveedores;
    private JComboBox ddlFacturas;
    private JTextField txtTotalPagar;
    private JButton btnGuardar;

    private ControladorOrdenPago controlador;
    private OrdenPago op;

    public OrdenDePago(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(opMain);
        this.setSize(400,400);
        this.pack();

        this.controlador = ControladorOrdenPago.getInstancia();

        btnGuardar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.op = new OrdenPago();
        this.controlador.agregarOP(this.op);
        setVisible(false);
        dispose();
    }

    OrdenPago showDialog() {
        setVisible(true);
        return this.op;
    }
}
