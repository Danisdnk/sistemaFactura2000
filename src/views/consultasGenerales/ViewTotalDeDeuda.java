package views.consultasGenerales;

import controllers.ControladorComprobantes;
import controllers.ControladorProveedor;
import models.dtos.ComprobanteDTO;
import views.documentosRecibidos.DocumentosView;
import views.login.loginView;
import views.ordenesDePago.OrdenesDePagoFrame;
import views.proveedores.provedorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewTotalDeDeuda extends JFrame{

    private JPanel deudaProveedor;

    private JButton consultarButton;
    private JButton volverButton;


    private JTextField textCUIT;
    private JTextField textMonto;
    private JPanel panelCentral;
    private JToolBar barraNavegacion;
    private JButton consultasGeneralesButton;
    private JButton proveedoresButton;
    private JButton DocumentosButton;
    private JButton ordenesDePagoButton;
    private JButton usuariosButton;
    private JButton consultarDeudaButton;
    private JButton cancelarButton;
    private JTextField labelMonto;

    public ViewTotalDeDeuda() {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(deudaProveedor);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


        consultarDeudaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cuit = null;


                if (!textCUIT.getText().isEmpty()){
                    cuit = textCUIT.getText();
                    setJtextArea(cuit);
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

            @Override
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

    private void setJtextArea(String cuit) {

        if (!ControladorProveedor.getInstancia().existsProveedorCuit(cuit)){

            this.labelMonto.setText(" El proveedor correspondiente al cuit ingresado no existe ");
        }else{
            this.labelMonto.setText(String.valueOf(ControladorComprobantes.getInstancia().calcularDeudaDeProveedorByCuit(cuit)));
        }
    }


}
