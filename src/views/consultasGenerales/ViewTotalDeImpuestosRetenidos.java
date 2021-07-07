package views.consultasGenerales;

import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import models.documento.OrdenPago;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewTotalDeImpuestosRetenidos extends JFrame{


    private JPanel impuestos;
    private JButton volverButton;
    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JTextField textCuit;
    private JButton consultarButton;
    private JTable tableImpuestos;
    private DefaultTableModel model;
    private ControladorProveedor controladorP;
    private ControladorOrdenesDePagos controladorOP;

    public ViewTotalDeImpuestosRetenidos() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(impuestos);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.controladorP = ControladorProveedor.getInstancia();
        this.controladorOP = ControladorOrdenesDePagos.getInstancia();

        model = new DefaultTableModel();
        model.addColumn("Proveedor");
        model.addColumn("Nro Documento");
        model.addColumn("Fecha");
        model.addColumn("IVA retenido");
        model.addColumn("IIBB retenido");
        model.addColumn("Total retenido");
        tableImpuestos.setModel(model);



        consultarButton.addActionListener(e -> {

            if (!textCuit.getText().isEmpty()) {
                String cuit = textCuit.getText();
                if (controladorP.existsProveedorCuit(cuit)) {
                    setJTable(cuit);
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

        volverButton.addActionListener(e -> {
            ViewConsultasGenerales principal = new ViewConsultasGenerales();
            principal.setVisible(true);
            dispose();
        });

        ordenesDePagoButton.addActionListener(e -> {
            OrdenesDePagoFrame principal = new OrdenesDePagoFrame();
            principal.setVisible(true);
            //dispose();
        });

        consultasGeneralesButton.addActionListener(e -> {
            ViewConsultasGenerales principal = new ViewConsultasGenerales();
            principal.setVisible(true);
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
    private void setJTable(String cuit) {

        model.getDataVector().removeAllElements();
        for(OrdenPago op : controladorOP.getOPsByCuit(cuit)){

            model.addRow(new Object[]{
                    op.getProveedor().getNombre(),
                    op.tipo() +(" ")+ op.getNro(),
                    op.getFecha(),
                    op.getRetencionIVA(),
                    op.getRetencionIIBB(),
                    op.getTotalRetenciones()
            });
        }
        model.fireTableDataChanged();
    }
}
