package views.consultasGenerales;

import controllers.ControladorComprobantes;
import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import models.documento.Comprobante;
import models.documento.OrdenPago;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewCuentaCorriente extends JFrame{


    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton cancelarButton;
    private JTable tablaCuentaCorriente;
    private JPanel cuentaCorriente;
    private JTextField textCuit;
    private JButton consultarButton;
    private JLabel labelFacturas;
    private JLabel labelND;
    private JLabel labelNC;
    private JLabel labelOP;
    private JLabel labelEC;
    private DefaultTableModel model;

    public ViewCuentaCorriente(){

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(cuentaCorriente);
        this.setSize(1000,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        labelFacturas.setVisible(false);
        labelEC.setVisible(false);
        labelNC.setVisible(false);
        labelND.setVisible(false);
        labelOP.setVisible(false);

        model = new DefaultTableModel();
        model.addColumn("Proveedor");
        model.addColumn("Nro Documento");
        model.addColumn("Fecha");
        model.addColumn("Monto Neto");
        model.addColumn("Monto Total");
        tablaCuentaCorriente.setModel(model);

        
        consultarButton.addActionListener(e -> {

            if (!textCuit.getText().isEmpty()) {
                String cuit = textCuit.getText();
                if (ControladorProveedor.getInstancia().existsProveedorCuit(cuit)) {
                    setJData(cuit);
                } else {
                    JOptionPane.showMessageDialog(
                            consultarButton,
                            "No existe un Proveedor con el cuit ingresado",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(
                        consultarButton,
                        "Ingrese un CUIT para continuar",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });




        cancelarButton.addActionListener(e -> {
            var ViewConsultasGenerales = new ViewConsultasGenerales();
            ViewConsultasGenerales.setVisible(true);

            dispose();
        });

        ordenesDePagoButton.addActionListener(e -> {
            OrdenesDePagoFrame op = new OrdenesDePagoFrame();
            op.setVisible(true);
            //dispose();
        });

        consultasGeneralesButton.addActionListener(e -> {
            ViewConsultasGenerales cons = new ViewConsultasGenerales();
            cons.setVisible(true);
            dispose();
        });

        usuariosButton.addActionListener(e -> {
            loginView principal = new loginView();
            principal.setVisible(true);
            dispose();
        });

        proveedoresButton.addActionListener(e -> {
            provedorView principal = new provedorView();
            principal.setVisible(true);
            dispose();
        });
        DocumentosButton.addActionListener(e -> {
            DocumentosView principal = new DocumentosView();
            principal.setVisible(true);
            dispose();
        });
    }

    private void setJData(String cuit) {
        labelFacturas.setVisible(true);
        labelOP.setVisible(true);
        labelND.setVisible(true);
        labelNC.setVisible(true);
        labelEC.setVisible(true);

        labelFacturas.setText(String.valueOf(ControladorComprobantes.getInstancia().getFacturasByProveedor(cuit).size()));
        labelOP.setText(String.valueOf(ControladorOrdenesDePagos.getInstancia().getOPsByCuit(cuit).size()));
        labelNC.setText(String.valueOf(ControladorComprobantes.getInstancia().getNCreditoByCuit(cuit).size()));
        labelND.setText(String.valueOf(ControladorComprobantes.getInstancia().getNDebitoByCuit(cuit).size()));
        labelEC.setText(String.valueOf(ControladorComprobantes.getInstancia().calcularDeudaDeProveedorByCuit(cuit)));

        model.getDataVector().removeAllElements();
        for (Comprobante comprobante : ControladorComprobantes.getInstancia().getComprobantesByCuit(cuit)) {

            model.addRow(new Object[]{
                    comprobante.getProveedor().getNombre(),
                    comprobante.tipo() + (" ") + comprobante.getNro(),
                    comprobante.getFecha(),
                    comprobante.getMontoNeto(),
                    detectarMontoTOtal(comprobante.getMontoTotal(), comprobante.tipo())
            });

        }
        model.fireTableDataChanged();
        for (OrdenPago ordenPago : ControladorOrdenesDePagos.getInstancia().getOPsByCuit(cuit)) {

            model.addRow(new Object[]{
                    ordenPago.getProveedor().getNombre(),
                    ("Orden de Pago ") + ordenPago.tipo() + ("") + ordenPago.getNro(),
                    ordenPago.getFecha(),
                    ordenPago.getMontoNeto(),
                    ordenPago.getMontoTotal()
            });
        }
        model.fireTableDataChanged();
    }
    private Float detectarMontoTOtal(Float result, String tipo ) {

        if (tipo.equals("NOTA CREDITO"))
            result = result * -1;
        return result;
        }
}