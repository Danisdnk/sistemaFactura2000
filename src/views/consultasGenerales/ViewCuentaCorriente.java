package views.consultasGenerales;

import controllers.ControladorComprobantes;
import controllers.ControladorOrdenesDePagos;
import controllers.ControladorProveedor;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        model.addColumn("Nro Documento");
        model.addColumn("Fecha");
        model.addColumn("Monto Total");
        tablaCuentaCorriente.setModel(model);

        
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(!textCuit.getText().isEmpty()){
                    String cuit  = textCuit.getText();
                    if(ControladorProveedor.getInstancia().existsProveedor(cuit)) {
                        setJTableCuentaCorriente(cuit);
                        setJTextData(cuit);
                    }else{
                        JOptionPane.showMessageDialog(
                                consultarButton,
                                "No existe un Proveedor con el cuit ingresado",
                                "Error",
                                JOptionPane.ERROR_MESSAGE); 
                         }
                }else{
                    JOptionPane.showMessageDialog(
                            consultarButton,
                            "Ingrese un CUIT para continuar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        



        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var ViewConsultasGenerales = new ViewConsultasGenerales();
                ViewConsultasGenerales.setVisible(true);

                dispose();
            }
        });

        ordenesDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdenesDePagoFrame op = new OrdenesDePagoFrame();
                op.setVisible(true);
                //dispose();
            }
        });

        consultasGeneralesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewConsultasGenerales cons = new ViewConsultasGenerales();
                cons.setVisible(true);
                dispose();
            }
        });

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView principal = new loginView();
                principal.setVisible(true);
                dispose();
            }
        });

        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provedorView principal = new provedorView();
                principal.setVisible(true);
                dispose();
            }
        });
        DocumentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentosView principal = new DocumentosView();
                principal.setVisible(true);
                dispose();
            }
        });
    }

    private void setJTextData(String cuit) {
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
    }

    private void setJTableCuentaCorriente(String cuit) {



    }

}
